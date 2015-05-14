package com.sinlov.toolsclass.custommath;

/**
 * This class is used to random number in case.
 * @author erZheng
 * @date Sep 16, 2014 10:51:30 AM
 */
public class RandomNumToNum {
	
	/**
	 * Input fromShortNum, toShortNum-1 to get an random Short integer Number.
	 * <li>Special this method use Integer input
	 * @param fromShortNum
	 * @param toShortNum
	 * @return randomShortNum
	 */
	public short getShortRandomNum(int fromShortNum, int toShortNum) {
		short randomShortNum = (short)((Math.random()*(toShortNum-fromShortNum))+fromShortNum);
		return randomShortNum;
	}
	
	/**
	 * Input fromIntNum, toIntNum-1 to get an random integer Number.
	 * @param fromIntNum
	 * @param toIntNum
	 * @return randomIntNum
	 */
	public int getIntRandomNum(int fromIntNum, int toIntNum) {
		int randomIntNum = ((int)(Math.random()*(toIntNum-fromIntNum)))+fromIntNum;
		return randomIntNum;
	}
	
	/**
	 * Input fromLongNum, toLongNum-1 to get an random longInteger Number.
	 * @param fromLongNum
	 * @param toLongNum
	 * @return randomLongNum
	 */
	public long getLongRandomNum(long fromLongNum, long toLongNum) {
		long randomLongNum = ((long)(Math.random()*(toLongNum-fromLongNum)))+fromLongNum;
		return randomLongNum;
	}
	
	/**
	 * Input fromFloatNum, toFloatNum-1 to get an random Float Number.
	 * @param fromFloatNum
	 * @param toFloatNum
	 * @return randomFloatNum
	 */
	public double getFloatRandomNum(float fromFloatNum, float toFloatNum) {
		float randomFloatNum = (float)(Math.random()*(toFloatNum-fromFloatNum))+fromFloatNum;
		return randomFloatNum;
	}
	
	/**
	 * Input fromDoubleNum, toDoubleNum-1 to get an random Double Number.
	 * @param fromDoubleNum
	 * @param toDoubleNum
	 * @return randomDoubleNum
	 */
	public double getDoubleRandomNum(double fromDoubleNum, double toDoubleNum) {
		double randomDoubleNum = (Math.random()*(toDoubleNum-fromDoubleNum))+fromDoubleNum;
		return randomDoubleNum;
	}
	
	
}
