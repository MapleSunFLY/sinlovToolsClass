/**
 * 
 */
package com.sinlov.toolsclass.myenum;

/**
 * @author sin-base
 *
 */
public enum EINTMOD {
	
	OPEN(100),
	ClOSE(200),
	;
	
	private final int id;
	EINTMOD(int id) {
		this.id = id;
	}
	public int getValue() {
		return id;
	}
}
