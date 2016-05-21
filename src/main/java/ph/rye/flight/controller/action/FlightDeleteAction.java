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

import java.util.Arrays;

import javax.ejb.EJB;

import ph.rye.flight.common.Constant;
import ph.rye.flight.service.FlightBean;
import ph.rye.saf.EventAction;
import ph.rye.saf.ann.AcceptEvent;
import ph.rye.saf.ann.Param;

/**
 * @author royce
 */
@AcceptEvent(Constant.Button.DELETE)
public class FlightDeleteAction extends EventAction {


    /** */
    private static final long serialVersionUID = 1L;


    @EJB
    private FlightBean flightBean;


    @Param
    private Integer[] flightIds;


    /** {@inheritDoc} */
    @Override
    public void execute() {
        final int result = flightBean.deleteByIds(flightIds);
        super.addMessageSuccess(
            String.format(
                "You have successfully deleted %d record(s) with ids: %s",
                result,
                Arrays.asList(flightIds)));

        reload();
    }


}
