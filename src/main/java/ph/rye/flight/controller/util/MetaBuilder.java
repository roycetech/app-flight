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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import ph.rye.common.loop.Iter;
import ph.rye.common.loop.IterM;
import ph.rye.flight.common.Constant;
import ph.rye.saf.util.BeanUtil;

/**
 * @author royce
 *
 */
public class MetaBuilder<T> {


    private final String entityName;


    /** Store column field name to column description. */
    private final Map<String, String> columnMap = new LinkedHashMap<>();
    private final Map<String, Function<Object, String>> formatMap =
            new HashMap<>();

    public MetaBuilder(final String entityName) {
        this.entityName = entityName;
    }

    public MetaBuilder<T> addColumn(final String description) {

        return addColumn(description, null, null);
    }

    public MetaBuilder<T> addColumn(final String description,
                                    final String propertyName) {
        return addColumn(description, propertyName, null);
    }

    public MetaBuilder<T> addColumn(final String description,
                                    final String propertyName,
                                    final Function<Object, String> dobFormatter) {
        assert !columnMap.containsKey(description);
        if (dobFormatter != null) {
            formatMap.put(description, dobFormatter);
        }

        columnMap.put(description, propertyName);
        return this;
    }


    public void build(final HttpServletRequest request,
                      final List<T> entityList) {

        build(request, entityList, (entity) -> {
            final List<Object> valueList = new ArrayList<>();

            IterM.of(columnMap).each((key, value) -> {
                final Function<Object, String> formatter = formatMap.get(key);

                final Object propValue = BeanUtil.getProperty(entity, value);
                if (formatter == null) {
                    valueList.add(propValue);
                } else {
                    valueList.add(formatter.apply(propValue));
                }
            });

            return valueList;
        });
    }

    public void build(final HttpServletRequest request,
                      final List<T> entityList,
                      final Function<T, List<Object>> function) {

        request.setAttribute(Constant.Param.ENTITY_NAME, entityName);
        request.setAttribute("columnCount", columnMap.size());
        request.setAttribute("columnSet", columnMap.keySet());

        final List<Object> entityListList = new ArrayList<>();
        request.setAttribute("entityList", entityListList);

        Iter.each(
            entityList,
            (entity) -> entityListList.add(function.apply(entity)));
    }

}

