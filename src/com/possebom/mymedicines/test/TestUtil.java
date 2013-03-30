package com.possebom.mymedicines.test;

import android.test.AndroidTestCase;

import com.possebom.mymedicines.util.Utils;

public class TestUtil extends AndroidTestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testUtils() {
		Utils util = new Utils();
		assertNotNull(util);
		int x = Utils.parseInt("10", 0);
		assertEquals(x, 10);
		int y = Utils.parseInt("X", 0);
		assertEquals(y, 0);
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
