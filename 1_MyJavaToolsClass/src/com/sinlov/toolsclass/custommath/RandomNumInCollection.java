package com.sinlov.toolsclass.custommath;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class RandomNumInCollection {
	private int fromIntNum;
	private int toIntNum;
	private int maxNumber;
	
	
	/**
	 * Input fromShortNum, toShortNum to get an random Short integer Number.
	 * <li>Special this method use Integer input
	 * @param fromShortNum
	 * @param toShortNum
	 * @return randomShortNum
	 */
	public int getIntRandomNum(int fromShortNum, int toShortNum) {
		int randomShortNum = ((int)(Math.random()*(toShortNum-fromShortNum))+fromShortNum);
		return randomShortNum;
	}
	
	/**
	 * Input fromIntNum, toIntNum to get an random ArrayList.
	 * 
	 * @param fromIntNum
	 * @param toIntNum
	 * @param lineNumber
	 * @return alist
	 */
	public List<Integer> firstNum(int fromNum, int toNum, int MaxNumber) {
		this.maxNumber = MaxNumber;
		List<Integer> alist = new ArrayList<Integer>();
		for (int i = 0; i < maxNumber; i++) {
			alist.add(i, getIntRandomNum(fromNum, toNum));
		}
		return alist;
	}

	/**
	 * Input ArrayList to Hash Set lose repeat Number
	 * @param aSet
	 * @return repeat
	 */
	public Set<Integer> secoundNum(List<Integer> aSet) {

		Set<Integer> repeat = new HashSet<Integer>();
			repeat.addAll(aSet);
			boolean a = true;
			while (a) {
				if (repeat.size() == maxNumber) {
					a = false;
				}else{
					repeat.add(getIntRandomNum(this.fromIntNum, this.toIntNum));
				}
				
			}
		return repeat;
	}
	
	/**
	 * array list distinct number
	 * @param inList
	 * @return
	 */
	public List<Integer> repect (List<Integer> alist) {
		List<Integer> blist = new ArrayList<Integer>();
		
		//array list distinct number
		boolean a = true;
		while (a) {
			int delectNum = 0;
			for (int i = 0; i < alist.size()-1; i++) {
				for (int j = i+1; j < alist.size(); j++) {
					if (alist.get(i) == alist.get(j)) {
						alist.remove(i);
						delectNum++;
					}
				}
			}
			if (alist.size() == maxNumber) {
				blist.addAll(alist);
				a = false;
			}
			else{
				for (int i = 0; i < delectNum; i++) {
					alist.add(getIntRandomNum(this.fromIntNum, this.toIntNum));
				}
				
			}
		}
		return blist;
	}
	
	/**
	 * Input HashSet to sort number
	 * @param bSet
	 * @return
	 */
	public Set<Integer> lotteryRandomGenerator(Set<Integer> bSet) {

		Set<Integer> lottery = new TreeSet<Integer>();
		lottery.addAll(bSet);
		return lottery;
	}

	/**
	 * print List
	 * @param cSet
	 */
	public void printLotteryList(List<Integer>  cList) {
		Iterator<Integer> i = cList.iterator();
		while (i.hasNext()) {
			System.out.print(i.next() + "\t");
		}
	}
	/**
	 * print Set
	 * @param cSet
	 */
	public void printLotterySet(Set<Integer>  cSet) {
		Iterator<Integer> i = cSet.iterator();
		while (i.hasNext()) {
			System.out.print(i.next() + "\t");
		}
	}
}
