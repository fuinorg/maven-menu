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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;

import org.fuin.objects4j.common.Contract;
import org.fuin.objects4j.common.Nullable;

/**
 * Defines a container.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class MenuElementContainer extends MenuElement {

	@Valid
	@XmlAnyElement(lax = true)
	private List<MenuElement> elements;

	/**
	 * Protected default constructor for deserialization.
	 */
	protected MenuElementContainer() {
		super();
	}

	/**
	 * Constructor only with title.
	 * 
	 * @param title
	 *            Title.
	 */
	public MenuElementContainer(@NotNull final String title) {
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
	public MenuElementContainer(@NotNull final String title,
			@Nullable final String name) {
		super(title, name);
	}

	/**
	 * Returns the elements of the container.
	 * 
	 * @return Unmodifiable list.
	 */
	@Nullable
	public final List<MenuElement> getElements() {
		if (elements == null) {
			return null;
		}
		return Collections.unmodifiableList(elements);
	}

	/**
	 * Adds an element to the container.
	 * 
	 * @param elementToAdd
	 *            Element to add.
	 */
	public final void addElement(@NotNull final MenuElement elementToAdd) {
		Contract.requireArgNotNull("elementToAdd", elementToAdd);
		if (elements == null) {
			elements = new ArrayList<MenuElement>();
		}
		if (elements.contains(elementToAdd)) {
			throw new IllegalArgumentException(
					"The element is already contained in the list: "
							+ elementToAdd);
		}
		elements.add(elementToAdd);
		Collections.sort(elements);
	}

	/**
	 * Adds a list of elements to the container.
	 * 
	 * @param elementsToAdd
	 *            Elements to add.
	 */
	public final void addElements(@NotNull final List<MenuElement> elementsToAdd) {
		Contract.requireArgNotNull("elementsToAdd", elementsToAdd);
		if (elements == null) {
			elements = new ArrayList<MenuElement>();
		}
		for (MenuElement elementToAdd : elementsToAdd) {
			if (elements.contains(elementToAdd)) {
				throw new IllegalArgumentException(
						"The element is already contained in the list: "
								+ elementToAdd);
			}
			elements.add(elementToAdd);
		}
		Collections.sort(elements);
	}

	/**
	 * Removes an existing element from the container.
	 * 
	 * @param element
	 *            Element to remove.
	 */
	public final void removeElement(@NotNull final MenuElement element) {
		Contract.requireArgNotNull("element", element);
		if ((elements == null) || !elements.contains(element)) {
			throw new IllegalArgumentException(
					"The element is not in the list: " + element);
		}
		elements.remove(element);
	}

	/**
	 * Locates a direct child by it's name.
	 * 
	 * @param name
	 *            Name of the element to find.
	 * 
	 * @return Element or null if none was found.
	 * 
	 * @param <T> Element type
	 */
	@SuppressWarnings("unchecked")
	@Nullable
	public final <T extends MenuElement> T findByName(@NotNull final String name) {
		Contract.requireArgNotNull("name", name);
		if (elements == null) {
			return null;
		}
		for (final MenuElement element : elements) {
			if (element.getCalcName().equals(name)) {
				return (T) element;
			}
		}
		return null;
	}

	/**
	 * Locates a direct child by it's title.
	 * 
	 * @param title
	 *            Title of the element to find.
	 * 
	 * @return Element or null if none was found.
	 * 
	 * @param <T> Element type
	 */
	@SuppressWarnings("unchecked")
	@Nullable
	public final <T extends MenuElement> T findByTitle(
			@NotNull final String title) {
		Contract.requireArgNotNull("title", title);
		if (elements == null) {
			return null;
		}
		for (final MenuElement element : elements) {
			if (element.getTitle().equals(title)) {
				return (T) element;
			}
		}
		return null;
	}

}
