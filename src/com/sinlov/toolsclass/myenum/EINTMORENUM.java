/**
 * 
 */
package com.sinlov.toolsclass.myenum;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author sin-base
 *
 */
public enum EINTMORENUM {

	UPDATE, 
	UPDATE_FAILED, 
	UPDATE_NEW, 
	;
	
	private static final int START_VALUE = 100;
	
	private static Map<Integer, EINTMORENUM> ss = new TreeMap<Integer, EINTMORENUM>();
	private int value;

	static {
		for (int i = 0; i < values().length; i++) {
			values()[i].value = START_VALUE + i;
			ss.put(values()[i].value, values()[i]);
		}
	}

	public static EINTMORENUM fromInt(int i) {
		return ss.get(i);
	}

	public int intValue() {
		return value;
	}
}
