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

import static org.fest.assertions.Assertions.assertThat;
import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;

import org.fuin.units4j.Units4JUtils;
import org.junit.Test;

/**
 * Test for {@link Project}.
 */
public class TestProject {

	/** XML Prefix. */
	protected static final String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";

	// CHECKSTYLE:OFF

	@Test
	public final void testCalcName() {

		assertThat(new Project("A").getCalcName()).isEqualTo("a");
		assertThat(new Project("AB").getCalcName()).isEqualTo("ab");
		assertThat(new Project("AbCd").getCalcName()).isEqualTo("ab-cd");
		assertThat(new Project("Ab Cd").getCalcName()).isEqualTo("ab-cd");
		assertThat(new Project("ab cd").getCalcName()).isEqualTo("ab-cd");
		assertThat(new Project("ab Cd ef").getCalcName()).isEqualTo("ab-cd-ef");
		assertThat(new Project("abCd ef").getCalcName()).isEqualTo("ab-cd-ef");

	}

	@Test
	public final void testMarshal() throws Exception {

		assertXMLEqual(XML + "<project title=\"abc\" />",
				Units4JUtils.marshal(new Project("abc"), Project.class));

		assertXMLEqual(XML + "<project title=\"A B C\" />",
				Units4JUtils.marshal(new Project("A B C"), Project.class));

		assertXMLEqual(XML + "<project title=\"A B C\" name=\"abc\" />",
				Units4JUtils
						.marshal(new Project("A B C", "abc"), Project.class));

		final Project project = new Project("My Project");
		project.setGroupId("org.fuin");
		project.setArtifactId("kickstart4j");
		project.setCi("https://fuin-org.ci.cloudbees.com/job/kickstart4j/");
		project.addElement(new Page("One"));
		project.addElement(new Page("Two"));

		System.out.println(Units4JUtils.marshal(project, Project.class, Page.class));
		
		assertXMLEqual(
				XML
						+ "<project title=\"My Project\" groupId=\"org.fuin\" artifactId=\"kickstart4j\" "
						+ "ci=\"https://fuin-org.ci.cloudbees.com/job/kickstart4j/\">"
						+ "<page title=\"One\"/><page title=\"Two\"/>"
						+ "</project>",
				Units4JUtils.marshal(project, Project.class, Page.class));

	}

	@Test
	public final void testUnmarshal() throws Exception {

		// TEST
		final Project testee = Units4JUtils
				.unmarshal(
						"<project title=\"My Project\" groupId=\"org.fuin\" artifactId=\"kickstart4j\" "
								+ "ci=\"https://fuin-org.ci.cloudbees.com/job/kickstart4j/\">"
								+ "<page title=\"One\"/><page title=\"Two\"/>"
								+ "</project>", Project.class, Page.class);

		// VERIFY
		assertThat(testee).isNotNull();
		assertThat(testee.getTitle()).isEqualTo("My Project");
		assertThat(testee.getName()).isNull();
		assertThat(testee.getCalcName()).isEqualTo("my-project");
		assertThat(testee.getGroupId()).isEqualTo("org.fuin");
		assertThat(testee.getArtifactId()).isEqualTo("kickstart4j");
		assertThat(testee.getCi()).isEqualTo(
				"https://fuin-org.ci.cloudbees.com/job/kickstart4j/");
		assertThat(testee.getElements()).containsOnly(new Page("One"), new Page("Two"));

	}

	// CHECKSTYLE:ON

}
