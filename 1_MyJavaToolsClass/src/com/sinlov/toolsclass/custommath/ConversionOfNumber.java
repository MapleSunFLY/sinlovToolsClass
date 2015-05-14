package com.sinlov.toolsclass.custommath;

import java.math.BigInteger;

public class ConversionOfNumber {
	@SuppressWarnings("unused")
	private void getConversionOfNumber() {
		//10进制转16进制
		Integer.toHexString(20);
		//10进制转2进制
		Integer.toBinaryString(10);
		//16进制转10进制
		Integer.parseInt("10", 16);
		//还有一种通用的方法
		//其中str为需要转换的字符串，int1为str的所属进制，int2为所需转为的进制
		String val = "123";
		int radix = 10;
		int int2 = 16;
		BigInteger bigInt = new BigInteger(val, radix);
		System.err.println(bigInt.toString(int2));
	}
}
