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

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import ph.rye.flight.common.Constant;
import ph.rye.flight.controller.util.MetaBuilder;
import ph.rye.flight.model.Flight;
import ph.rye.flight.service.FlightBean;
import ph.rye.saf.ann.JSP;

/**
 * @author royce
 */
@JSP(Constant.Pages.FLIGHT_LIST)
public class LoadFlightListAction extends BaseLoadListAction {


    /** */
    private static final long serialVersionUID = 1L;


    @EJB
    private FlightBean flightBean;


    /** {@inheritDoc} */
    @Override
    public void execute() {
        getRequest().setAttribute(
            Constant.Param.FLIGHT_LIST,
            flightBean.getAllFlights());

        new MetaBuilder<Flight>("Flight")
            .addColumn("ID")
            .addColumn("Flight Origin")
            .addColumn("Flight Destination")
            .addColumn("Price")
            .addColumn("Flight Time")
            .addColumn("Airplane")
            .addColumn("Seating")
            .addColumn("Number of Pilots")
            .addColumn("Pilot Names")
            .build(getRequest(), flightBean.getAllFlights(), (flight) -> {
                final List<Object> objList = new ArrayList<>();
                objList.add(flight.getId());
                objList.add(flight.getFlightOrigin().toString());
                objList.add(flight.getFlightDestination().toString());

                final NumberFormat formatter =
                        NumberFormat.getCurrencyInstance();

                final String moneyString = formatter.format(flight.getPrice());
                objList.add(moneyString);

                synchronized (LoadFlightListAction.class) {
                    objList.add(
                        Constant.Formatter.FLIGHT_DT_FMT
                            .format(flight.getFlightTime()));
                }
                objList.add(flight.getAirplaneDisp());
                objList.add(flight.getAirplaneDetail().getSeatingCapacity());
                objList.add(flight.getPilots().size());
                objList.add(flight.getPilots());
                return objList;
            });

        super.execute();

    }

}
