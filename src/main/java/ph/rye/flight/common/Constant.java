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
package ph.rye.flight.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author royce
 *
 */
public class Constant {

    public static final String PAGE_PREFIX = "views/";
    //    public static final String PAGE_PREFIX = "WEB-INF/views/";

    public static final String PERSISTENCE_UNIT = "playground-jpa";


    public static class Formatter {

        public static final DateFormat DOB =
                new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());

        public static final DateFormat FLIGHT_DT_FMT =
                new SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.getDefault());


    }

    public class Pages {

        public static final String HOME = PAGE_PREFIX + "main_menu.jsp";


        public static final String FLIGHT_LIST =
                PAGE_PREFIX + "flight_list.jsp";

        public static final String FLIGHT_DETAIL =
                PAGE_PREFIX + "flight_form.jsp";


        //        public static final String PILOT_LIST = PAGE_PREFIX + "pilot_list.jsp";

        public static final String PILOT_DETAIL =
                PAGE_PREFIX + "pilot_form.jsp";

        public static final String AIRPLANE_DETAIL =
                PAGE_PREFIX + "airplane_form.jsp";

        public static final String PASSENGER_DETAIL =
                PAGE_PREFIX + "passenger_form.jsp";


        public static final String ENTITY_LIST =
                PAGE_PREFIX + "entity_list.jsp";

        //        public static final String ENTITY_DETAIL =
        //                PAGE_PREFIX + "entity_detail.jsp";

    }


    public class Param {

        public static final String PILOT_LIST = "pilotList";
        public static final String FLIGHT_LIST = "flightList";
        public static final String PASSENGER_LIST = "passengerList";
        public static final String AIRPLANE_LIST = "airplaneList";

        public static final String PILOTS_COUNT = "pilotsCount";
        public static final String FLIGHTS_COUNT = "flightsCount";
        public static final String PASSENGERS_COUNT = "passengersCount";
        public static final String AIRPLANES_COUNT = "airplanesCount";

        public static final String PILOT_RANKS = "pilotRanks";
        public static final String AIRPORTS = "airports";
        public static final String GENDERS = "genders";
        public static final String FLIGHT_CLASSES = "flightClasses";

        public static final String ENTITY_NAME = "entityName";
        public static final String ENTITY_LIST_URL = "entityListUrl";

    }

    public class Button {
        public static final String ADD = "BtnAdd";
        public static final String DELETE = "BtnDelete";
    }


}
