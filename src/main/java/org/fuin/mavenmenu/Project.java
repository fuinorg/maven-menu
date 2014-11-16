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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.fuin.objects4j.common.Nullable;

/**
 * Defines a project.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "project")
public class Project extends MenuElementContainer {

	@Nullable
	@XmlAttribute
	private String groupId;

	@Nullable
	@XmlAttribute
	private String artifactId;

	@Nullable
	@XmlAttribute
	private String ci;

	/**
	 * Protected default constructor for deserialization.
	 */
	protected Project() {
		super();
	}

	/**
	 * Constructor only with title.
	 * 
	 * @param title
	 *            Title.
	 */
	public Project(@NotNull final String title) {
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
	public Project(@NotNull final String title, @Nullable final String name) {
		super(title, name);
	}
	
	/**
	 * Returns the Maven group ID.
	 * 
	 * @return groupId
	 */
	@Nullable
	public final String getGroupId() {
		return groupId;
	}

	/**
	 * Sets the Maven group ID.
	 * 
	 * @param groupId
	 *            ID to set.
	 */
	public final void setGroupId(@Nullable final String groupId) {
		this.groupId = groupId;
	}

	/**
	 * Returns the Maven artifact ID.
	 * 
	 * @return artifactId
	 */
	public final String getArtifactId() {
		return artifactId;
	}

	/**
	 * Sets the Maven artifact ID.
	 * 
	 * @param artifactId
	 *            ID to set.
	 */
	public final void setArtifactId(@Nullable final String artifactId) {
		this.artifactId = artifactId;
	}

	/**
	 * Returns the URL of the CI server.
	 * 
	 * @return CI server URL.
	 */
	@Nullable
	public final String getCi() {
		return ci;
	}

	/**
	 * Sets the URL of the CI server.
	 * 
	 * @param ci
	 *            CI server URL to set.
	 */
	public final void setCi(@Nullable final String ci) {
		this.ci = ci;
	}

	@Override
	public final String toString() {
		return getTitle();
	}

}
