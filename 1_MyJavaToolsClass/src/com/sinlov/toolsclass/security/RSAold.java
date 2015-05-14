/* 
 * All rights Reserved, Designed By Android_Robot
 * @Title:  RSAUtil.java 
 * @Package com.sinlov.toolsclass.security
 * @Copyright:  XXX Co., Ltd. Copyright YYYY-YYYY,  All rights reserved 
 * @Description:  TODO<Plase input what is this to do> 
 * @author:  ErZheng 
 * @data:  2014年12月12日 上午1:09:02 
 * @version:  V1.0 
 */
package com.sinlov.toolsclass.security;

import java.math.BigInteger;
import java.util.Random;

/**   
 * @ClassName:  RSAUtil   
 * @Description:TODO(what to do)   
 * @author: ErZheng  
 * @date:   2014年12月12日 上午1:09:02   
 *      
 */
public class RSAold {
	private static RSAold mRSAUtil;
	/**	十六进制下数字到字符的映射数组	*/
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", 
		"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" }; 
	@SuppressWarnings("unused")
	private BigInteger primes = BigInteger.ZERO;
	private static BigInteger n;
	private BigInteger ran;
	private BigInteger d = BigInteger.ZERO;
	private static BigInteger e = BigInteger.ZERO;
	@SuppressWarnings("unused")
	private String m;
	Random rnd = new Random();
	private int numBit = 10;
	
	private RSAold() {
	}
	public RSAold getInstance(){
		if (mRSAUtil == null) {
			return new RSAold();
		}else {
			return mRSAUtil;
		}
	}
	
	/**
	 *得到生成随机数的位数0~n,
	 * @param 得到numBit输入的整数
	 * @return 返回numBit整数
	 */
	public int getNumBit(int n) {
		numBit = n;
		return numBit;
	}
	
	/**
	 * 得到随机的素数,
	 * @param 传入随机数rnd
	 * @return 返回一个素数
	 */
	public BigInteger getPrimes(Random rnd) {
		return primes = BigInteger.probablePrime(numBit, rnd);
	}
	
	/**
	 * 通过P,Q计算n值
	 * @param 一个素数p
	 * @param 一个素数q
	 * @return 返回P*Q的值n
	 */
	public BigInteger getN(BigInteger p, BigInteger q) {
		return n = p.multiply(q);
	}
	
	/**
	 * 通过P,Q计算ran值
	 * @param 一个素数p
	 * @param 一个素数q
	 * @return 返回(P-1)*(Q-1)的值ran
	 */
	public BigInteger getRan(BigInteger p, BigInteger q) {
		/*long r = (p.intValue() - 1)*(q.intValue() - 1);
		String tr = String.valueOf(r);*/
		ran = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		
		return ran;
	}
	
	/**
	 * 通过求出来的ran计算公钥e
	 * @param ran
	 * @return 返回公钥的值e
	 */
	public BigInteger getE(BigInteger ran) {
		
		BigInteger temp = null; //中间变量
		e = BigInteger.ZERO;
		do{
			temp = BigInteger.probablePrime(numBit, rnd);
			
			/*
			 * 随机生成一个素数，看他是否与ran的公约数为1，如果为1，e=temp退出循环
			 */
			if((temp.gcd(ran)).equals(BigInteger.ONE))
				e = temp;
		} while(!((temp.gcd(ran)).equals(BigInteger.ONE)));
		
		return e;
	}
	
	/**
	 * 求密钥d,通过公式 d = e（负一次幂）mod ran
	 * @param 公钥e
	 * @param OL函数ran
	 * @return 密钥d
	 */
	public BigInteger getKey(BigInteger e, BigInteger ran) {
		 d = e.modInverse(ran);
		 return d;
	}
	
	/**
	 * 对明文进行加密，通过公式 密文=(明文（e次幂） mod m) 
	 * @param 明文em
	 * @param 公钥e
	 * @param 模数n
	 * @return 加密后的密文encodeM
	 */
	public static BigInteger[] encodeRSA(byte[][] em, BigInteger e, BigInteger n) {
		
		BigInteger[] encodeM = new BigInteger[em.length];
		
		for(int i=0; i < em.length; i++) {
			encodeM[i] = new BigInteger(em[i]);
            encodeM[i] = encodeM[i].modPow(e, n);
        }
        
        return encodeM;
	}
	
	/**
	 * 对密文进行解密，通过公式 明文 = （密文（d次幂）mod m）
	 * @param 密文encodeM
	 * @param 密钥d
	 * @param 模数n
	 * @return 解密后的明文dencodeM
	 */
	public  static byte[][] dencodeRSA(BigInteger[] encodeM, BigInteger d, BigInteger n) {
		if (encodeM == null) return null;
        
        byte[][] dencodeM = new byte[encodeM.length][];
        int i;
        int lung=encodeM.length;
        
        for (i = 0; i < lung; i++) {
            dencodeM[i] = encodeM[i].modPow(d,n).toByteArray();
        }
        return dencodeM;
	}
	
	/**
	 * 将数组byte[]arrayByte,转化为二维数组
	 * @param arrayByte
	 * @param numBytes
	 * @return 
	 */
	public static byte[][] byteToEm(byte[] arrayByte, int numBytes) {
        int dab = (arrayByte.length-1) / numBytes +1,
        iab = 0;
        
        byte[][] arrayEm = new byte[dab][];
        int i,j; 
        
        for (i=0; i < dab-1; i++) {
            arrayEm[i] = new byte[numBytes];
            
            for (j=0; j < numBytes; j++, iab++) {
                arrayEm[i][j] = arrayByte[iab];
            }
        }
        
        i = dab-1; 
        
        arrayEm[i] = new byte[arrayByte.length-iab];
        
        for (j=0; j < arrayEm[i].length; j++, iab++) {
            arrayEm[i][j] = arrayByte[iab];
        }
        return arrayEm;
    }
    
	/**
	 * 
	 * 将二维数组转化为一维数组
	 * @param arraySenS
	 * @return
	 */
    public static byte[] StringToByte(byte[][] arraySenS) {
        
        if (arraySenS == null ) return null;
        int i, dab = 0;  
        
        for (i = 0; i < arraySenS.length; i++) {
            if (arraySenS[i] == null) return null;
            dab = dab+arraySenS[i].length;
        }
        int iab = 0; 
        byte[] arrayByte = new byte[dab];
        int j; 
        
        for (i=0; i < arraySenS.length; i++) {
            for (j=0; j < arraySenS[i].length; j++, iab++) {
                arrayByte[iab] = arraySenS[i][j];
            }
        }  
        return arrayByte;
    }
	
	/** 
	 * change byte array to HexString
	 * 转换字节数组为16进制字串 
	 * @param b  字节数组 
	 * @return 十六进制字串 
	 */ 
	public static String byteArrayToHexString(byte[] b) { 
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
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String encondM = "123";
		
		int numeroByte = (10-1) / 8;
		byte[] arraySendM = encondM.getBytes();
		byte[][] encodSendM = RSA.byteToEm(arraySendM,numeroByte);
		BigInteger[] encodingM = RSA.encodeRSA(encodSendM, e, n);
	}
}
