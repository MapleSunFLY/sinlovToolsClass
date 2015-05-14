package com.sinlov.toolsclass.security;
import java.math.BigInteger;
import java.util.Random;


public class RSA {
	@SuppressWarnings("unused")
	private BigInteger primes = BigInteger.ZERO;
	@SuppressWarnings("unused")
	private BigInteger n, ran, d = BigInteger.ZERO;
	private BigInteger e = BigInteger.ZERO;
	@SuppressWarnings("unused")
	private String m;
	Random rnd = new Random();
	private int numBit = 10;
	
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
}
