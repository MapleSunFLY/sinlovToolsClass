/**
 * 
 */
package com.sinlov.toolsclass.myenum;

/**
 * @author sin-base
 *
 */
public class EnumTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(EINTMOD.OPEN.ordinal());
		System.out.println(EINTMOD.OPEN.getValue());

		System.out.println(EINTMORENUM.UPDATE.intValue());
		System.out.println(EINTMORENUM.UPDATE_FAILED.intValue());
		System.out.println(EINTMORENUM.UPDATE_NEW.intValue());
		System.out.println(Integer.toHexString(ECOLORS.ORANGE.getColorValue()));

		for (EIDS i : new EIDS[] { EIDS.OPEN, EIDS.CLOSE }) {
			System.out.println(i.toString() + " " + i.ordinal() + " "
					+ i.getID());
		}
	}

}
