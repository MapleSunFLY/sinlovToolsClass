package com.sinlov.toolsclass.security;

import java.security.MessageDigest;


/**
 * MD5 encryption
 * <li> MD5Util.createPassword("want to encrypt")
 * <li> MD5Util.authenticatePassword("password", "youInputSting"))
 * <li> If you want more security please remember encrypted code's length, when authenticating that to check out.
 * @author erZheng
 * @date Dec 11, 2014 2:51:21 PM
 */
public class MD5Util {
	private static MD5Util mMDMd5Util;
	
	private MD5Util() {
	}
	
	public MD5Util getInstance(){
		if (mMDMd5Util == null) {
			return new MD5Util();
		}else
			return mMDMd5Util;
	}

	/**	十六进制下数字到字符的映射数组	*/
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", 
		"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" }; 

	/** 
	 * inputString encryption
	 * @param inputString
	 * @return 
	 */ 
	public String createPassword(String inputString){ 
		return encodeByMD5(inputString); 
	} 
	/** 
	 * authenticate password
	 * @param cryptographicpassword	password 
	 * @param authenticatePassword	input password 
	 * @return 
	 */ 
	public static boolean authenticatePassword(String cryptographicpassword, String authenticatePassword){ 
		if (cryptographicpassword.equals(encodeByMD5(authenticatePassword))){ 
			return true; 
		} else { 
			return false; 
		} 
	} 

	/** 
	 * code sting to MD5 code
	 * @param originString 
	 * @return 
	 */ 
	private static String encodeByMD5(String originString) { 
		if (originString != null){ 
			try { 
				MessageDigest md = MessageDigest.getInstance("MD5"); 
				byte[] results = md.digest(originString	.getBytes()); 
				String resultString = byteArrayToHexString(results); 
				return resultString.toUpperCase(); 
			} catch (Exception ex) { 
				ex.printStackTrace(); 
			} 
		} 
		return null; 
	} 

	/** 
	 * change byte array to HexString
	 * 转换字节数组为16进制字串 
	 * @param b  字节数组 
	 * @return 十六进制字串 
	 */ 
	private static String byteArrayToHexString(byte[] b) { 
		StringBuffer resultSb = new StringBuffer(); 
		for (int i = 0; i < b.length; i++) { 
			resultSb.append(byteToHexString(b[i])); 
		} 
		return resultSb.toString(); 
	} 

	/** 
	 * become one byte to HexString
	 * 将一个字节转化成16进制形式的字符串 
	 * @param b 
	 * @return 
	 */ 
	private static String byteToHexString(byte b) { 
		int n = b; 
		if (n < 0) 
			n = 256 + n; 
		int d1 = n / 16; 
		int d2 = n % 16; 
		return hexDigits[d1] + hexDigits[d2]; 
	} 
}
