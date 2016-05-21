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
 */
public enum PilotRank {


    Captain, FirstOfficer, JuniorOfficer;

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return StringUtil.camelToTitle(name());
    }

    //    public static Map<String, String> toValueDispMap() {
    //        final Map<String, String> retval = new LinkedHashMap<>();
    //        for (final PilotRank rank : PilotRank.values()) {
    //            retval.put(rank.name(), rank.toString());
    //        }
    //        return retval;
    //
    //    }

    //    public String[] toStringArray() {
    //        final List<String> list = new ArrayList<String>();
    //        for (final PilotRank rank : values()) {
    //            list.add(StringUtil.camelToTitle(rank.name()));
    //        }
    //        return list.toArray(new String[list.size()]);
    //    }

}
