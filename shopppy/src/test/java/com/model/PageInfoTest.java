package com.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class PageInfoTest {

	PageInfo pageInfo = new PageInfo();
	ArrayList<CD> cdList = new ArrayList<CD>();
	CD cd1 = new CD();
	CD cd2 = new CD();

	@Before
	public void setCD() {
		cd1.setType("Video");
		cd1.setGenre("Film");
		cd1.setName("Matrix");
		cd2.setType("Video");
		cd2.setGenre("Film");
		cd2.setName("Mortal Kombat");
		cdList.add(cd1);
		cdList.add(cd2);
		Store.addProduct(cd1);
		Store.addProduct(cd2);
	}

	@Test
	public void testSetPageSize() {
		pageInfo.setPageSize();
		assertTrue("Correct number of pages", pageInfo.getPageCount() == 1);
	}
}
