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
package ph.rye.flight.model;

import ph.rye.common.lang.StringUtil;

/**
 * @author royce
 *
 */
public enum AirportLocation {


    London, SanFrancisco, LosAngeles;


    @Override
    public String toString() {
        return StringUtil.camelToTitle(name());
    }

    //    /**
    //     * @param string it must be the enum constant name (e.g.
    //     *            <code>SanFrancisco</code>) and not a display name.
    //     * @return
    //     */
    //    public AirportLocation fromString(final String string) {
    //        final Ano<AirportLocation> retval = new Ano<>();
    //        for (final AirportLocation airport : values()) {
    //            if (airport.name().equals(string)) {
    //                retval.set(airport);
    //            }
    //        }
    //        return retval.get();
    //    }
}
