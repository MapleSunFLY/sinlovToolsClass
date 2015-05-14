package com.sinlov.toolsclass.custommath;

import static org.junit.Assert.*;

import org.junit.Test;


@SuppressWarnings("unused")
public class TestCustomMath {

	@Test
	public void test() {
//		fail("Not yet implemented");
		ProbabilisticAlgorithm pb = new ProbabilisticAlgorithm();
		System.out.println(pb.probaliisticAlgorithm(60));
	}
	/**
	 * 
	 */
	@Test
	public void testRandomNumToNum() {
		// TODO Auto-generated method stub
		RandomNumToNum randomNum = new RandomNumToNum();
		double a = randomNum.getDoubleRandomNum(0, 10);
		int b = randomNum.getIntRandomNum(10, 15);
		System.out.println(b);
	}

}
