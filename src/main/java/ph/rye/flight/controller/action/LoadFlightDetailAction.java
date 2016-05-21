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

import ph.rye.common.lang.ObjectUtil;
import ph.rye.flight.common.Constant;
import ph.rye.flight.controller.util.MetaBuilder;
import ph.rye.flight.model.AirportLocation;
import ph.rye.flight.model.Pilot;
import ph.rye.flight.service.AirplaneBean;
import ph.rye.flight.service.PilotBean;
import ph.rye.saf.ann.JSP;

/**
 * @author royce
 */
@JSP(Constant.Pages.FLIGHT_DETAIL)
public class LoadFlightDetailAction extends BaseLoadDetailAction {


    /** */
    private static final long serialVersionUID = 1L;


    @EJB
    private AirplaneBean airplaneBean;

    @EJB
    private PilotBean pilotBean;


    /** {@inheritDoc} */
    @Override
    public void execute() {

        getRequest().setAttribute(
            Constant.Param.AIRPORTS,
            ObjectUtil.toValueDispMap(AirportLocation.class));

        getRequest().setAttribute(
            Constant.Param.AIRPLANE_LIST,
            airplaneBean.getAllEntities());

        new MetaBuilder<Pilot>("Pilot")
            .addColumn("ID", "id")
            .addColumn("Last Name", "lastName")
            .addColumn("First Name", "firstName")
            .addColumn("License", "license")
            .addColumn("Pilot Rank", "rank")
            .build(getRequest(), pilotBean.getAllEntities());

        /* MetaBuilder overridden it with pilot so we restored it manually. */
        getRequest().setAttribute(Constant.Param.ENTITY_NAME, "Flight");

        super.execute();
    }


}
