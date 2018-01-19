package ch.akros.jcc.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class GreetingTest {

	@Test
	public void toString_shouldReturnIdAndContent() {
		final int id = 1;
		final String content = "jUnit";
		final Greeting g = new Greeting(id, content);

		assertTrue(g.toString().contains("content=jUnit"));
		assertTrue(g.toString().contains("id=1"));
	}

	@Test
	public void getId_shouldReturnId() {
		final int id = 1;
		final Greeting g = new Greeting(id, StringUtils.EMPTY);

		assertEquals(id, g.getId());
	}

	@Test
	public void getContent_shouldReturnContent() {
		final String content = "jUnit";
		final Greeting g = new Greeting(1, content);

		assertEquals(content, g.getContent());
	}
}
