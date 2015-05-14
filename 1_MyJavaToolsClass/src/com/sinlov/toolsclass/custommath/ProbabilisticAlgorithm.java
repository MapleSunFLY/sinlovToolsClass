package com.sinlov.toolsclass.custommath;
/**
 * This is one method that use probabilistic.
 * @author erZheng
 * @date Sep 16, 2014 10:53:15 AM
 */
public class ProbabilisticAlgorithm {
	/**
	 * input percentage to return percentage% probabilistic.
	 * <i>
	 * <li> true to get probabilistic
	 * <li> false to lose probabilistic
	 * </i>
	 * @param int percentage 
	 * @return boolean eventTrue
	 * @author erzheng
	 */
	public boolean probaliisticAlgorithm(int percentage) {		
		boolean probabilistic = !((int)(Math.random()*100)>percentage);
		return probabilistic;
	}
}
