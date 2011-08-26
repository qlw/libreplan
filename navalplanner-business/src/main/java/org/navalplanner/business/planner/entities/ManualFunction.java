/*
 * This file is part of NavalPlan
 *
 * Copyright (C) 2011 Igalia, S.L.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.navalplanner.business.planner.entities;


/**
 * Manual allocation function, it used to represent when user has done a manual
 * allocation.
 *
 * @author Manuel Rego Casasnovas <rego@igalia.com>
 */
public class ManualFunction extends AssignmentFunction {

    public static ManualFunction create() {
        return create(new ManualFunction());
    }

    @Override
    public String getName() {
        return AssignmentFunctionName.MANUAL.toString();
    }

    @Override
    public void applyTo(ResourceAllocation<?> resourceAllocation) {
        // Do nothing
    }

}