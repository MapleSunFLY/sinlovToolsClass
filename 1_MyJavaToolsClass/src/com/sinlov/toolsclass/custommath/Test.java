package com.sinlov.toolsclass.custommath;

import java.math.BigDecimal;;
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "15";
		String b = "9";
		String c = "0";
		String d = "0";
		BigDecimal tempNumber1 = new BigDecimal(a);
		BigDecimal tempNumber2 = new BigDecimal(b);
		BigDecimal bdc = new BigDecimal(c);
		BigDecimal bdd = new BigDecimal(d);
		
		
		tempNumber1 = BigDecimal.valueOf(tempNumber1.doubleValue() % tempNumber2.doubleValue());
		System.out.println(tempNumber1);
		
		double ab = Double.valueOf(a);
		double bb = Double.valueOf(b);
//		System.out.println(bda.divideToIntegralValue(bdb));
		System.out.println(ab%bb);
//		System.out.println(bdc.divide(bda));
		System.out.println(bdc.toString()+"=0 is "+bdc.equals(bdd));
	}

}
