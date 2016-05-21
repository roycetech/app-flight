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
import ph.rye.flight.controller.PassengerList;
import ph.rye.flight.model.FlightClass;
import ph.rye.flight.model.Gender;
import ph.rye.flight.model.Passenger;
import ph.rye.flight.service.PassengerBean;
import ph.rye.saf.EventAction;
import ph.rye.saf.SafConstant;
import ph.rye.saf.ann.AcceptEvent;
import ph.rye.saf.ann.Param;

/**
 * @author royce
 *
 */
@AcceptEvent(SafConstant.Button.SAVE)
public class PassengerSaveAction extends EventAction {

    /** */
    private static final long serialVersionUID = 1L;


    //    private final static OneLogger LOG1 = OneLogger.getInstance();


    @Param
    private String lastName;
    @Param
    private String firstName;
    @Param
    private String gender;
    @Param
    private String dob;
    @Param
    private String flightClass;


    @EJB
    private PassengerBean passengerBean;


    /** {@inheritDoc} */
    @Override
    public void execute() {

        final Passenger passenger = new Passenger();
        passenger.setLastName(lastName);
        passenger.setFirstName(firstName);
        passenger.setGender(Gender.valueOf(gender));
        passenger.setFlightClass(FlightClass.valueOf(flightClass));


        try {
            passenger.setDob(Constant.Formatter.DOB.parse(dob));
        } catch (final ParseException e) {
            throw new FlightAppException("Invalid date: " + dob, e);
        }

        passengerBean.addPassenger(passenger);

        if (isCreate()) {

            addMessageSuccess(
                String.format(
                    "Passenger with id %s has been successfully created",
                    passenger.getId()));

            redirect(PassengerList.URL);
        }
    }

}
