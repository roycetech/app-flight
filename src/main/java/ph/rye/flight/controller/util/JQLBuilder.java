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

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import ph.rye.common.builder.SQLClauseBuilder;

/**
 * @author royce
 *
 */
@SuppressWarnings({
        "PMD.ShortMethodName",
        "PMD.TooManyMethods" })
public class JQLBuilder {


    /** */
    @SuppressWarnings("PMD.AvoidStringBufferField" /* Warning on type javadoc. */)
    protected transient StringBuilder strBuilder = new StringBuilder();


    private final Map<String, Character> tableAliasMap = new HashMap<>();


    /**
     * @param strings alias or expression.
     */
    public JQLBuilder select(final String... strings) {
        assert strings == null || strings.length == 0 || strings.length == 1;

        strBuilder.append("SELECT ");
        if (strings != null && strings.length == 1) {
            strBuilder.append(strings[0]).append(' ');
        }
        return this;
    }

    public JQLBuilder count(final String param) {
        strBuilder.append(String.format("COUNT(%s) ", param));
        return this;
    }

    public JQLBuilder from(final String table, final String alias) {
        strBuilder
            .append("FROM ")
            .append(table)
            .append(' ')
            .append(alias)
            .append(' ');
        return this;
    }

    public JQLBuilder from(final String table) {
        strBuilder
            .append("FROM ")
            .append(table)
            .append(' ')
            .append(table.toUpperCase(Locale.getDefault()).charAt(0))
            .append(' ');
        return this;
    }

    public JQLBuilder alias(final String alias) {
        strBuilder.append(alias).append(' ');
        return this;
    }

    public JQLBuilder where(final String where) {
        strBuilder
            .append(pad(4))
            .append("WHERE ")
            .append(pad(2))
            .append(where)
            .append(' ');
        return this;
    }

    public JQLBuilder where(final SQLClauseBuilder sqlClauseBld) {
        strBuilder.append(pad(4)).append("WHERE ").append(pad(2)).append(
            sqlClauseBld.getClause());
        return this;
    }

    public JQLBuilder and(final String and, final String... restOfAnd) {
        strBuilder.append('\n').append(pad(8)).append("AND ").append(and);

        for (final String string : restOfAnd) {
            strBuilder
                .append('\n')
                .append(pad(8))
                .append("AND ")
                .append(string);
        }
        strBuilder.append('\n');
        return this;
    }

    public String build() {
        return strBuilder.toString();
    }

    String pad(final int spaces) {
        return spaces == 0 ? "" : String.format("%-" + spaces + "s", " ");
    }

    /**
     * @param string
     * @return
     */
    public JQLBuilder deleteFrom(final String string) {
        final char alias = string.toLowerCase(Locale.getDefault()).charAt(0);
        tableAliasMap.put(string, alias);
        strBuilder
            .append("DELETE FROM ")
            .append(string)
            .append(' ')
            .append(alias)
            .append(' ');
        return this;
    }

    /**
     * @param string
     * @return
     */
    public JQLBuilder in(final String string) {
        strBuilder.append("IN ").append(string);
        return this;
    }


}
