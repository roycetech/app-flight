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

import ph.rye.flight.controller.PilotList;
import ph.rye.flight.model.Pilot;
import ph.rye.flight.model.PilotRank;
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
public class PilotSaveAction extends EventAction {

    /** */
    private static final long serialVersionUID = 1L;


    @Param
    private String lastName;
    @Param
    private String firstName;
    @Param
    private Integer license;
    @Param
    private String rank;


    @EJB
    private PilotBean pilotBean;


    /** {@inheritDoc} */
    @Override
    public void execute() {

        final Pilot newPilot = new Pilot();
        newPilot.setLastName(lastName);
        newPilot.setFirstName(firstName);
        newPilot.setLicense(license);
        newPilot.setRank(PilotRank.valueOf(rank));

        pilotBean.addEntity(newPilot);

        if (isCreate()) {

            addMessageSuccess(
                String.format(
                    "Pilot with id %s has been successfully created",
                    newPilot.getId()));

            redirect(PilotList.URL);
        }

    }


}
