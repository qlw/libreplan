/*
 * This file is part of ###PROJECT_NAME###
 *
 * Copyright (C) 2009 Fundación para o Fomento da Calidade Industrial e
 *                    Desenvolvemento Tecnolóxico de Galicia
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

package org.navalplanner.web.common.components.finders;

import java.util.List;

import org.navalplanner.business.resources.daos.IResourceDAO;
import org.navalplanner.business.resources.entities.Resource;
import org.navalplanner.business.resources.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Susana Montes Pedreira <smontes@wirelessgalicia.com> Implements a
 *         {@link IFinder} class for providing {@link Worker} elements
 */
@Repository
public class ResourceFinder extends Finder implements IFinder {

    @Autowired
    private IResourceDAO resourceDAO;

    @Transactional(readOnly = true)
    public List<Resource> getAll() {
        return resourceDAO.getRealResources();
    }

    @Override
    public String _toString(Object value) {
        final Resource resource = (Resource) value;
        return (resource != null) ? resource.getShortDescription() : "";
    }
}
