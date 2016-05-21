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

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ph.rye.flight.model.Pilot;
import ph.rye.flight.service.PilotBean;

/**
 * @author royce
 *
 */
@Path("/pilots")
public class PilotWS {


    @EJB
    private PilotBean pilotBean;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pilot> getPilots() {
        return pilotBean.getAllEntities();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{pilotId}")
    public Pilot getPilot(final @PathParam("pilotId") Integer pilotId) {
        return pilotBean.findById(pilotId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/query")
    public List<Pilot> getPilotQuery(@QueryParam("notIn") final List<Integer> notIn) {
        return pilotBean
            .getPilotsExcept(notIn.toArray(new Integer[notIn.size()]));
    }

}

