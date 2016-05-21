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

import javax.ejb.EJB;

import ph.rye.flight.controller.AirplaneList;
import ph.rye.flight.model.Airplane;
import ph.rye.flight.service.AirplaneBean;
import ph.rye.saf.EventAction;
import ph.rye.saf.SafConstant;
import ph.rye.saf.ann.AcceptEvent;
import ph.rye.saf.ann.Param;

/**
 * @author royce
 *
 */
@AcceptEvent(SafConstant.Button.SAVE)
public class AirplaneSaveAction extends EventAction {

    /** */
    private static final long serialVersionUID = 1L;


    @Param
    private String planeMake;
    @Param
    private String modelName;
    @Param
    private Integer seatingCapacity;


    @EJB
    private AirplaneBean airplaneBean;


    /** {@inheritDoc} */
    @Override
    public void execute() {

        final Airplane newAirplane = new Airplane();
        newAirplane.setPlaneMake(planeMake);
        newAirplane.setModelName(modelName);
        newAirplane.setSeatingCapacity(seatingCapacity);

        airplaneBean.addEntity(newAirplane);

        if (isCreate()) {
            addMessageSuccess(
                String.format(
                    "Airplane with id %s has been successfully created",
                    newAirplane.getId()));

            redirect(AirplaneList.URL);
        }


    }

}
