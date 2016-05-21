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
package ph.rye.flight.controller.action;

import javax.ejb.EJB;

import ph.rye.flight.common.Constant;
import ph.rye.flight.service.AirplaneBean;
import ph.rye.flight.service.FlightBean;
import ph.rye.flight.service.PassengerBean;
import ph.rye.flight.service.PilotBean;
import ph.rye.saf.LoadAction;

/**
 * @author royce
 *
 */
public class LoadHomeAction extends LoadAction {


    /** */
    private static final long serialVersionUID = 1L;


    @EJB
    private PilotBean pilotBean;

    @EJB
    private FlightBean flightBean;

    @EJB
    private AirplaneBean airplaneBean;

    @EJB
    private PassengerBean passengerBean;


    /** {@inheritDoc} */
    @Override
    public void execute() {
        getRequest().setAttribute(
            Constant.Param.PILOTS_COUNT,
            pilotBean.getEntityCount());

        getRequest().setAttribute(
            Constant.Param.FLIGHTS_COUNT,
            flightBean.getFlightCount());

        getRequest().setAttribute(
            Constant.Param.AIRPLANES_COUNT,
            airplaneBean.getEntityCount());

        getRequest().setAttribute(
            Constant.Param.PASSENGERS_COUNT,
            passengerBean.getPassengerCount());

        forward(Constant.Pages.HOME);
    }


}
