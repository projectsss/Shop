package com.model;

import static org.junit.Assert.*;
import org.junit.Test;

public class CDTest {
	@Test
	public void testGetName() {
		CD cd = new CD();
		cd.setName("Alladin");
		assertEquals("Alladin", cd.getName());

	}

	@Test
	public void testgetGenre() {
		CD cd = new CD();
		cd.setGenre("Film");
		assertEquals("Film", cd.getGenre());
	}

	@Test
	public void testGetType() {
		CD cd = new CD();
		cd.setType("Audio");
		assertEquals("Audio", cd.getType());
	}

	@Test
	public void testSetQuantity() {
		CD cd = new CD();
		cd.setQuantity();
		assertEquals(1, cd.getQuantity());
	}

}
