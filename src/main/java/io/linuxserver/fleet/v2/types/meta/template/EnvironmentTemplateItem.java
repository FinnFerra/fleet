/*
 * Copyright (c)  2020 LinuxServer.io
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.linuxserver.fleet.v2.types.meta.template;

public class EnvironmentTemplateItem extends AbstractTemplateItem<String, EnvironmentTemplateItem> {

    private final String exampleValue;

    public EnvironmentTemplateItem(final String name, final String description, final String exampleValue) {
        super(name, description);
        this.exampleValue = exampleValue;
    }

    public final String getEnv() {
        return getName();
    }

    public final String getExampleValue() {
        return exampleValue;
    }
}
