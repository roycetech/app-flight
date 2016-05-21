/**
@RequestAction(LoadPilotListAction.class)
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
package ph.rye.flight.controller.action;

import java.text.ParseException;

import javax.ejb.EJB;

import ph.rye.flight.common.Constant;
import ph.rye.flight.common.FlightAppException;
import ph.rye.flight.controller.FlightList;
import ph.rye.flight.model.Airplane;
import ph.rye.flight.model.AirportLocation;
import ph.rye.flight.model.Flight;
import ph.rye.flight.model.Pilot;
import ph.rye.flight.service.AirplaneBean;
import ph.rye.flight.service.FlightBean;
import ph.rye.flight.service.PilotBean;
import ph.rye.saf.EventAction;
import ph.rye.saf.SafConstant;
import ph.rye.saf.ann.AcceptEvent;
import ph.rye.saf.ann.Param;

/**
 * @author royce
 *
 */
@AcceptEvent(SafConstant.Button.SAVE)
public class FlightSaveAction extends EventAction {

    /** */
    private static final long serialVersionUID = 1L;


    @Param
    private String flightOrigin;
    @Param
    private String flightDestination;
    @Param
    private String flightTime;
    @Param
    private Float price;
    @Param
    private Integer airplane;
    @Param
    private Integer[] pilotIds;


    @EJB
    private FlightBean flightBean;
    @EJB
    private AirplaneBean airplaneBean;
    @EJB
    private PilotBean pilotBean;


    /** {@inheritDoc} */
    @Override
    public void execute() {

        final Flight flight = new Flight();
        flight.setFlightOrigin(AirportLocation.valueOf(flightOrigin));
        flight.setFlightDestination(AirportLocation.valueOf(flightDestination));

        if (pilotIds != null) {
            for (final Integer integer : pilotIds) {
                final Pilot pilot = pilotBean.findById(integer);
                flight.getPilots().add(pilot);
            }
        }

        try {
            flight.setFlightTime(
                Constant.Formatter.FLIGHT_DT_FMT.parse(flightTime));
        } catch (final ParseException e) {
            throw new FlightAppException("Invalid date: " + flightTime, e);
        }

        flight.setPrice(price);

        final Airplane airplane = airplaneBean.findById(this.airplane);
        flightBean.addFlight(flight, airplane);

        if (isCreate()) {

            addMessageSuccess(
                String.format(
                    "Flight with id %s has been successfully created",
                    flight.getId()));

            redirect(FlightList.URL);
        }

    }


}
