/**
 * Copyright (C) 2014 Michael Schnell. All rights reserved. <http://www.fuin.org/>
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library. If not, see <http://www.gnu.org/licenses/>.
 */
package org.fuin.mavenmenu;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.fuin.objects4j.common.Nullable;

/**
 * Defines a menu structure.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "menu")
public class Menu extends MenuElementContainer {

	/**
	 * Protected default constructor for deserialization.
	 */
	protected Menu() {
		super();
	}

	/**
	 * Constructor only with title.
	 * 
	 * @param title
	 *            Title.
	 */
	public Menu(@NotNull final String title) {
		super(title, null);
	}

	/**
	 * Constructor with title and name.
	 * 
	 * @param title
	 *            Title.
	 * @param name
	 *            Name.
	 */
	public Menu(@NotNull final String title, @Nullable final String name) {
		super(title, name);
	}
	
}
