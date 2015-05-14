/**
 * 
 */
package com.sinlov.toolsclass.myenum;

/**
 * @author sin-base
 *
 */
public enum ECOLORS {
	
	YELLOW, 
	ORANGE,
	;

	public int getColorValue() {
		switch (this) {
		case YELLOW:
			return 0xffffff00;
		case ORANGE:
			return 0xffffa500;
		}
		return 0;
	}

	public static int getStaticColorValue(ECOLORS ecolors) {
		switch (ecolors) {
		case YELLOW:
			return 0xffffff00;
		case ORANGE:
			return 0xffffa500;
		}
		return 0;
	}
}