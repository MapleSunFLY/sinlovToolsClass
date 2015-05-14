/**
 * 
 */
package com.sinlov.toolsclass.myenum;

/**
 * @author sin-base
 *
 */
public enum EIDS {
	
	OPEN,
	CLOSE,
	;
	//Start IDS
	private static final int BASE_ORDINAL = 1000;

	public int getID() {
		return ordinal() + BASE_ORDINAL;
	}
}
