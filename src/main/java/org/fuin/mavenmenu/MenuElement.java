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
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import org.apache.commons.lang3.StringUtils;
import org.fuin.objects4j.common.Contract;
import org.fuin.objects4j.common.NeverNull;
import org.fuin.objects4j.common.NotEmpty;
import org.fuin.objects4j.common.Nullable;

/**
 * Element that has a title and name and defines it's equals and hash code based
 * on the name.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class MenuElement implements Comparable<MenuElement> {

	@Nullable
	@XmlAttribute
	private String name;

	@NotEmpty
	@XmlAttribute
	private String title;

	/**
	 * Package visible default constructor for deserialization.
	 */
	protected MenuElement() {
		super();
	}

	/**
	 * Constructor with title and name.
	 * 
	 * @param title
	 *            Title.
	 * @param name
	 *            Name.
	 */
	protected MenuElement(@NotNull final String title,
			@Nullable final String name) {
		super();
		Contract.requireArgNotEmpty("title", title);
		this.title = title;
		this.name = name;
	}

	/**
	 * Returns the name.
	 * 
	 * @return Name.
	 */
	@Nullable
	public final String getName() {
		return name;
	}

	/**
	 * Returns the name if set or a name that is derived from the title.
	 * 
	 * @return Name with camel case and spaces converted into '-'. All other
	 *         characters will be removed.
	 */
	@NeverNull
	public final String getCalcName() {
		if (name == null) {
			final String[] parts = removeEmptyElements(StringUtils
					.splitByCharacterTypeCamelCase(title));
			return StringUtils.join(parts, '-');
		}
		return name;
	}

	private String[] removeEmptyElements(final String[] parts) {
		if (parts == null) {
			return null; 
		}
		final List<String> list = new ArrayList<String>();
		for (String part : parts) {
			if (!part.equals(" ")) {
				list.add(part.toLowerCase());
			}
		}
		return list.toArray(new String[list.size()]);
	}

	/**
	 * Returns the title.
	 * 
	 * @return Title.
	 */
	@NeverNull
	public final String getTitle() {
		return title;
	}


	@Override
	public final int compareTo(final MenuElement other) {
		return title.compareTo(other.title);
	}

	// CHECKSTYLE:OFF Generated code
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuElement other = (MenuElement) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	// CHECKSTYLE:ON

	
}
