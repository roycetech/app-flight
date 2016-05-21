/**
 *   Copyright 2016 Royce Remulla
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package ph.rye.flight.ws.rest;

import java.net.URI;
import java.util.List;

import javax.ejb.EJB;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import ph.rye.flight.model.Passenger;
import ph.rye.flight.service.PassengerBean;

/**
 * @author royce
 *
 */
@Path("/passengers")
@Transactional
public class PassengerWS {


    @EJB
    private PassengerBean passengerBean;


    @Context
    private UriInfo uriInfo;


    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Passenger> getPassengers() {
        return passengerBean.getAllPassengers();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("{passengerId}")
    public Passenger getPassenger(final @PathParam("passengerId") Integer passengerId) {

        final Passenger passenger = passengerBean.findById(passengerId);
        if (passenger == null) {
            throw new NotFoundException(
                String.format(
                    "The pssenger with ID %s was not found",
                    passengerId));
        }
        return passenger;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPassenger(final Passenger passenger) {
        passengerBean.addPassenger(passenger);
        final UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        final URI pUri = uriBuilder.path(passenger.getId().toString()).build();
        return Response.created(pUri).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response udpatePassenger(final Passenger passenger) {
        assert passenger != null;
        final Passenger updated = passengerBean.updatePasenger(passenger);

        if (updated == null) {
            throw new NotFoundException(
                String.format(
                    "The pssenger with ID %s was not found",
                    passenger.getId()));
        } else {
            return Response.ok(updated).build();
        }
    }

    @DELETE
    @Path("{passengerId}")
    public Response deletePassenger(final @PathParam("passengerId") Integer passengerId) {

        if (passengerBean.deletePasenger(passengerId)) {
            return Response.noContent().build();

        } else {
            throw new NotFoundException(
                String.format(
                    "The pssenger with ID %s was not found",
                    passengerId));

        }
    }

}
