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

import ph.rye.flight.common.Constant;
import ph.rye.flight.controller.PilotDetail;
import ph.rye.flight.controller.util.ServletUtil;
import ph.rye.saf.EventAction;
import ph.rye.saf.ann.AcceptEvent;

/**
 * @author royce
 */
@AcceptEvent(Constant.Button.ADD)
public class PilotAddAction extends EventAction {


    /** */
    private static final long serialVersionUID = 1L;


    /** {@inheritDoc} */
    @Override
    public void execute() {
        ServletUtil.setCreateMode(getRequest());
        redirect(PilotDetail.URL);
    }


}
