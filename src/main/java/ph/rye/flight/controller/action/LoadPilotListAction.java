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
import ph.rye.flight.controller.util.MetaBuilder;
import ph.rye.flight.model.Pilot;
import ph.rye.flight.service.PilotBean;
import ph.rye.saf.ann.JSP;

/**
 * @author royce
 *
 */
@JSP(Constant.Pages.ENTITY_LIST)
public class LoadPilotListAction extends BaseLoadListAction {


    /** */
    private static final long serialVersionUID = 1L;


    @EJB
    private PilotBean pilotBean;


    /** {@inheritDoc} */
    @Override
    public void execute() {
        getRequest().setAttribute(
            Constant.Param.PILOT_LIST,
            pilotBean.getAllEntities());

        new MetaBuilder<Pilot>("Pilot")
            .addColumn("ID", "id")
            .addColumn("Last Name", "lastName")
            .addColumn("First Name", "firstName")
            .addColumn("License", "license")
            .addColumn("Pilot Rank", "rank")
            .build(getRequest(), pilotBean.getAllEntities());

        super.execute();
    }


}
