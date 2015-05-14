package com.sinlov.toolsclass.custommath;

import static org.junit.Assert.*;

import org.junit.Test;

@SuppressWarnings("unused")
public class RandomNumToNumTest {
	RandomNumToNum test = new RandomNumToNum();
	@Test
	public void testGetShortRandomNum() {
//		fail("Not yet implemented");
//		short a = test.getShortRandomNum(0, 20);
		System.out.println("shortNum: "+test.getShortRandomNum(0, 10));
	}

	@Test
	public void testGetIntRandomNum() {
//		fail("Not yet implemented");
		System.out.println("IntNum: "+test.getIntRandomNum(6, 11));
		System.out.println("IntNum: "+test.getIntRandomNum(6, 11));
	}

	@Test
	public void testGetLongRandomNum() {
//		fail("Not yet implemented");
		System.out.println("LongNum: "+test.getLongRandomNum(20, 30));
	}

	@Test
	public void testGetFloatRandomNum() {
//		fail("Not yet implemented");
		System.out.println("FloatNum: "+test.getFloatRandomNum(40, 50));
	}

	@Test
	public void testGetDoubleRandomNum() {
//		fail("Not yet implemented");
		System.out.println("DoubleNum: "+test.getDoubleRandomNum(50, 60));
	}

}
