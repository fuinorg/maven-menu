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

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.fest.assertions.Assertions.assertThat;

import java.io.IOException;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.apache.commons.io.IOUtils;
import org.fuin.units4j.Units4JUtils;
import org.junit.Test;

/**
 * Test for {@link Menu}.
 */
public class TestMenu {

	/** XML Prefix. */
	protected static final String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";

	// CHECKSTYLE:OFF

	@Test
	public final void testCalcName() {

		assertThat(new Menu("A").getCalcName()).isEqualTo("a");
		assertThat(new Menu("AB").getCalcName()).isEqualTo("ab");
		assertThat(new Menu("AbCd").getCalcName()).isEqualTo("ab-cd");
		assertThat(new Menu("Ab Cd").getCalcName()).isEqualTo("ab-cd");
		assertThat(new Menu("ab cd").getCalcName()).isEqualTo("ab-cd");
		assertThat(new Menu("ab Cd ef").getCalcName()).isEqualTo("ab-cd-ef");
		assertThat(new Menu("abCd ef").getCalcName()).isEqualTo("ab-cd-ef");

	}

	@Test
	public final void testMarshal() throws Exception {

		assertXMLEqual(XML + "<menu title=\"abc\" />",
				Units4JUtils.marshal(new Menu("abc"), Menu.class));

		assertXMLEqual(XML + "<menu title=\"A B C\" />",
				Units4JUtils.marshal(new Menu("A B C"), Menu.class));

		assertXMLEqual(XML + "<menu title=\"A B C\" name=\"abc\" />",
				Units4JUtils.marshal(new Menu("A B C", "abc"), Menu.class));

	}

	@Test
	public final void testUnmarshal() throws Exception {

		// TEST
		final Menu testee = Units4JUtils.unmarshal(
				"<menu title=\"My Menu\" />", Menu.class);

		// VERIFY
		assertThat(testee).isNotNull();
		assertThat(testee.getTitle()).isEqualTo("My Menu");
		assertThat(testee.getName()).isNull();
		assertThat(testee.getCalcName()).isEqualTo("my-menu");

	}
	
	@Test
	public final void testUnmarshalExample() throws IOException {
		
		// TEST
		final String xml = IOUtils.toString(this.getClass().getResourceAsStream("/example.xml"));
		final Menu testee = Units4JUtils.unmarshal(xml, Menu.class, Project.class, Page.class);
		
		// VERIFY
		assertThat(testee).isNotNull();
		final Set<ConstraintViolation<Object>> violations = Units4JUtils.validate(testee);
		assertThat(violations).isEmpty();		
		assertThat(testee.getTitle()).isEqualTo("Menu");
		assertThat(testee.getName()).isEqualTo("root");
		
		final Menu menuA = testee.findByTitle("A");
		assertThat(menuA).isNotNull();
		assertThat(menuA.getName()).isNull();
		assertThat(menuA.getCalcName()).isEqualTo("a");

		final Project projectKickstart4J = menuA.findByTitle("Kickstart4J");
		assertThat(projectKickstart4J).isNotNull();
		assertThat(projectKickstart4J.getName()).isNull();
		assertThat(projectKickstart4J.getCalcName()).isEqualTo("kickstart-4-j");
		assertThat(menuA.findByTitle("More")).isInstanceOf(Page.class);
		assertThat(projectKickstart4J.findByTitle("Background")).isInstanceOf(Page.class);
		assertThat(projectKickstart4J.findByTitle("Examples")).isInstanceOf(Page.class);
		
		final Menu menuSrcMixins4J = testee.findByTitle("SrcMixins4J");
		assertThat(menuSrcMixins4J).isNotNull();
		assertThat(menuSrcMixins4J.getName()).isNull();
		assertThat(menuSrcMixins4J.getCalcName()).isEqualTo("src-mixins-4-j");
		assertThat(menuSrcMixins4J.findByTitle("Examples")).isInstanceOf(Project.class);

		final Project projectParent = menuSrcMixins4J.findByTitle("Parent");
		assertThat(projectParent).isNotNull();
		assertThat(projectParent.getName()).isNull();
		assertThat(projectParent.getCalcName()).isEqualTo("parent");
		assertThat(projectParent.getGroupId()).isEqualTo("org.fuin.srcmixins4j");
		assertThat(projectParent.getArtifactId()).isEqualTo("srcmixins4j-eclipse-parent");
		assertThat(projectParent.getCi()).isEqualTo("https://fuin-org.ci.cloudbees.com/view/srcmixins4j/job/srcmixins4j-eclipse/");
		assertThat(projectParent.findByTitle("Feature")).isInstanceOf(Project.class);
		assertThat(projectParent.findByTitle("Plugin")).isInstanceOf(Project.class);
		assertThat(projectParent.findByTitle("Repository")).isInstanceOf(Project.class);
		
	}

	// CHECKSTYLE:ON

}
