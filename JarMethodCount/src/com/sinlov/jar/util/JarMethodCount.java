/*
 * Copyright (c) 2015, Incito Corporation, All Rights Reserved
 */
package com.sinlov.jar.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @description
 * @author sinlov
 * @createDate May 14, 2015
 * @version 1.0
 */
public class JarMethodCount {

	private final static int RESOLEVJAR_SUCCESS = 0;
	private final static int RESOLEVJAR_ERROR = 1;
	private final static String JAR = "jar";
	private final static String DEX = "dex";
	private final static String DIR = "dir";
	private final static String UNKNOWN = "unknown";
	private final static String RESOLE_METHOD_COUNTS= " Methods count : ";
	private final static String RESOLE_ERROR_COMMOND_FAILD= " commond faild!";
	private final static String RESOLE_END= " Resole end";
	private final static String RESOLEVJAR_PATH_ERROR = "File path error";
	private final static String RESOLEVJAR_UNKNOWN_FILE_TPYE = "Unknown File Type";

	public static void main(String[] args) {
//		String[] testPath = {"C:/JarMethodCount/JPushDemo_1.0_VC1_20150513.jar"};
		if (args.length != 1) {
			System.out.println(RESOLEVJAR_PATH_ERROR);
		} else {
			String type = getFileType(args[0]);
			if (type.equalsIgnoreCase(DEX)) {
				resolveDex(args[0]);
			} else if (type.equalsIgnoreCase(JAR)) {
				resolveJar(args[0]);
			} else if (type.equalsIgnoreCase(DIR)) {
				resolveDir(args[0]);
			} else {
				System.err.println(RESOLEVJAR_UNKNOWN_FILE_TPYE);
			}
		}
		System.out.println(RESOLE_END);
	}
	
	/**
	 * 根据扩展名获取文件类型
	 * @description 
	 * @author   sinlov
	 * @createDate May 14, 2015
	 * @param path
	 * @return
	 */
	public static String getFileType(String path) {
		String type = UNKNOWN;
		try {
			File file = new File(path);
			if (file.isDirectory()) {
				type = DIR;
			} else {
				if (checkFileSuffixType(file, DEX)) {
					type = DEX;
				} else if (checkFileSuffixType(file, JAR)) {
					type = JAR;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return type;
	}
	
	private static boolean checkFileSuffixType(File tagetfile, String tagetType){
		return getExtensionName(tagetfile.getName()).equalsIgnoreCase(tagetType);
	}

	/**
	 * 简单的获取扩展名，不完全准确;完全准确的话，可以根据文件流判断
	 * @description 
	 * @author   sinlov
	 * @createDate May 14, 2015
	 * @param filename
	 * @return
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}
	/**
	 * 处理dex文件
	 * @description 
	 * @author   sinlov
	 * @createDate May 14, 2015
	 * @param path
	 */
	public static void resolveDex(String path) {
		FileInputStream fis = null;
		try {
			File file = new File(path);
			fis = new FileInputStream(file);
			byte[] bytes = new byte[1000];
			if (fis.read(bytes) != -1) {
				StringBuilder sb = new StringBuilder();
				for (int i = 91; i > 87; i--) {
					sb.append(Integer.toBinaryString(bytes[i] & 255));
				}
				System.out.println(file.getName() + RESOLE_METHOD_COUNTS + Integer.parseInt(sb.toString(), 2));
			}
			if(null != fis){
				fis.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 解析JAR
	 * @description 
	 * @author   sinlov
	 * @createDate May 14, 2015
	 * @param filePath
	 */
	@SuppressWarnings("unused")
	public static boolean resolveJar(String filePath) {
		FileInputStream fis = null;
		try {
			String path = System.getenv("dx");
			File jarFile = new File(path);
			if(null == jarFile){
				return false;
			}
			ProcessBuilder pb = new ProcessBuilder("dx.bat", "--dex",
					"--output=C://temp.dex", filePath);
			pb.directory(jarFile);
			pb.redirectErrorStream(true);
			Process p = pb.start();
			BufferedInputStream in = new BufferedInputStream(p.getInputStream());
			BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
			String lineStr;
			while ((lineStr = inBr.readLine()) != null) {
				// 获得命令执行后在控制台的输出信息
				System.out.println(lineStr);
			}
			// 检查命令是否执行失败
			if (p.waitFor() != RESOLEVJAR_SUCCESS) {
				// p.exitValue()==0表示正常结束，1：非正常结束
				int rq = p.exitValue();
				if (rq == RESOLEVJAR_ERROR) {
					System.err.println(filePath + RESOLE_ERROR_COMMOND_FAILD);
					inBr.close();
					in.close();
					return false;
				}
			}
			inBr.close();
			in.close();
			File originFile = new File(filePath);
			File file = new File("C://temp.dex");
			fis = new FileInputStream(file);
			byte[] bytes = new byte[1000];
			if (fis.read(bytes) != -1) {
				StringBuilder sb = new StringBuilder();
				for (int i = 91; i > 87; i--) {
					sb.append(String.format("%02x", (bytes[i] & 255)));
				}
				System.out.println(originFile.getAbsolutePath() + RESOLE_METHOD_COUNTS + Integer.parseInt(sb.toString(), 16));
			}
			file.deleteOnExit();
			if(null != fis){
				fis.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 递归处理文件目录
	 * @description 
	 * @author   sinlov
	 * @createDate May 14, 2015
	 * @param path
	 */
	public static void resolveDir(String path) {
		File file = new File(path);
		File[] fileList = file.listFiles();
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isDirectory()) {
				resolveDir(fileList[i].getAbsolutePath());
			} else {
				if (getFileType(fileList[i].getAbsolutePath())
						.equalsIgnoreCase(DEX)) {
					resolveDex(fileList[i].getAbsolutePath());
				} else if (getFileType(fileList[i].getAbsolutePath())
						.equalsIgnoreCase(JAR)) {
					resolveJar(fileList[i].getAbsolutePath());
				}
			}
		}
	}
}
