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
 * Test for {@link Page}.
 */
public class TestPage {

	/** XML Prefix. */
	protected static final String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";

	// CHECKSTYLE:OFF

	@Test
	public final void testCalcName() {

		assertThat(new Page("A").getCalcName()).isEqualTo("a");
		assertThat(new Page("AB").getCalcName()).isEqualTo("ab");
		assertThat(new Page("AbCd").getCalcName()).isEqualTo("ab-cd");
		assertThat(new Page("Ab Cd").getCalcName()).isEqualTo("ab-cd");
		assertThat(new Page("ab cd").getCalcName()).isEqualTo("ab-cd");
		assertThat(new Page("ab Cd ef").getCalcName()).isEqualTo(
				"ab-cd-ef");
		assertThat(new Page("abCd ef").getCalcName())
				.isEqualTo("ab-cd-ef");

	}

	@Test
	public final void testMarshal() throws Exception {

		assertXMLEqual(XML + "<page title=\"abc\" />",
				Units4JUtils.marshal(new Page("abc"), Page.class));
		
		assertXMLEqual(XML + "<page title=\"A B C\" />",
				Units4JUtils.marshal(new Page("A B C"), Page.class));
		
		assertXMLEqual(XML + "<page title=\"A B C\" name=\"abc\" />",
				Units4JUtils.marshal(new Page("A B C", "abc"), Page.class));
		
	}

	@Test
	public final void testUnmarshal() throws Exception {

		// TEST
		final Page testee = Units4JUtils.unmarshal(
				"<page title=\"My Page\" />", Page.class);

		// VERIFY
		assertThat(testee).isNotNull();
		assertThat(testee.getTitle()).isEqualTo("My Page");
		assertThat(testee.getName()).isNull();
		assertThat(testee.getCalcName()).isEqualTo("my-page");

	}

	// CHECKSTYLE:ON

}
