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
package ph.rye.flight.controller.util;

import javax.servlet.http.HttpServletRequest;

import ph.rye.common.lang.ObjectUtil;
import ph.rye.saf.SafConstant;

/**
 * @author royce
 *
 */
public final class ServletUtil {


    private ServletUtil() {}


    public static String getPageMode(final HttpServletRequest request) {
        return ObjectUtil.nvl(
            (String) request.getAttribute(SafConstant.Param.PAGE_MODE),
            request.getParameter(SafConstant.Param.PAGE_MODE));
    }

    public static boolean isCreate(final HttpServletRequest request) {
        return SafConstant.PageMode.Create
            .equals(request.getParameter(SafConstant.Param.PAGE_MODE));
    }

    public static boolean isUpdate(final HttpServletRequest request) {
        return SafConstant.PageMode.Update
            .equals(request.getParameter(SafConstant.Param.PAGE_MODE));
    }

    public static boolean isView(final HttpServletRequest request) {
        return !isCreate(request) && !isUpdate(request);
    }

    public static void setCreateMode(final HttpServletRequest request) {
        setPageMode(request, SafConstant.PageMode.Create);
    }

    public static void setViewMode(final HttpServletRequest request) {
        setPageMode(request, SafConstant.PageMode.View);
    }

    public static void setUpdateMode(final HttpServletRequest request) {
        setPageMode(request, SafConstant.PageMode.Update);
    }

    private static void setPageMode(final HttpServletRequest request,
                                    final SafConstant.PageMode mode) {
        request.setAttribute(SafConstant.Param.PAGE_MODE, mode.toString());
    }


}
