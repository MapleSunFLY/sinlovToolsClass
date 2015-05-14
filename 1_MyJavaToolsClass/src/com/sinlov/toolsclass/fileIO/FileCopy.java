/**
 * 
 */
package com.sinlov.toolsclass.fileIO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 * @author erZheng
 * @date Oct 20, 2014 4:59:03 PM
 */
public class FileCopy {

	// 源文件夹
	static String url1 = "";
	// 目标文件
	static String url2 = "";
	// 创建日志全局缓存输出对象
	static BufferedWriter bw;

	public static void main(String args[]) throws IOException {
		// 实例化日志输出对象
		FileWriter fw = new FileWriter("File/GetFileCopy.log");
		// 实例化缓存输入对象
		bw = new BufferedWriter(fw);
		// FileOutputStream output = new
		// FileOutputStream("File/GetFileCopy.log");
		// BufferedOutputStream outBuff = new BufferedOutputStream(output);
		// 完成路径初始化操作
		int num= init(bw);
		// 追加文件名
		url2 = url2 + "/" + (new File(url1)).getName();
		if (num==2) {
			// 创建时间对象
			Date now = new Date();
			// 设置时间显示格式年月日时分秒
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy/MM/dd HH:mm:ss");// 可以方便地修改日期格式
			// 接收时间
			String time = dateFormat.format(now);
			System.out.println(time + "\t\t正在将文件:\t\t\t"
					+ new File(url1).getName() + "\t\t\t\t复制到\t" + url2);
			// 把信息写入日志中
			bw.write(time + "\t\t正在将文件:\t\t\t" + new File(url1).getName()
					+ "\t\t\t\t复制到\t" + url2);
			// 换行
			bw.newLine();
			copyFile(new File(url1), new File(url2));	
		}else {
			// 创建目标文件夹
			(new File(url2)).mkdirs();
			// 获取源文件夹当前下的文件或目录
			File[] file = (new File(url1)).listFiles();
			for (int i = 0; i < file.length; i++) {
				// 如果第一个获取的i是文件就 复制文件
				if (file[i].isFile()) {
					// 创建时间对象
					Date now = new Date();
					// 设置时间显示格式年月日时分秒
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy/MM/dd HH:mm:ss");// 可以方便地修改日期格式
					// 接收时间
					String time = dateFormat.format(now);
					// 打印拷贝信息
					System.out.println(time + "\t\t正在将文件:\t\t\t"
							+ file[i].getName() + "\t\t\t\t复制到\t" + url2);
					// 把信息写入日志中
					bw.write(time + "\t\t正在将文件:\t\t\t" + file[i].getName()
							+ "\t\t\t\t复制到\t" + url2);
					// 换行
					bw.newLine();
					// 拷贝文件，传入源文件的文件名，目标文件夹名/源文件名
					copyFile(file[i],
							new File(url2 + File.separator + file[i].getName()));
				}
				// 如果读取的是文件夹
				if (file[i].isDirectory()) {
					// 创建时间对象
					Date now = new Date();
					// 设置时间格式
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy/MM/dd HH:mm:ss");// 可以方便地修改日期格式
					// 定义字符串接收时间
					String time = dateFormat.format(now);
					// 获取文件夹目录 ：url1/文件夹名称；
					String sourceDir = url1 + File.separator + file[i].getName();
					// 获取文件夹目录 ：url2/文件夹名称
					String targetDir = url2 + File.separator + file[i].getName();
					// 打印复制消息
					System.out.println(time + "\t\t正在将文件夹:\t\t" + sourceDir
							+ "\t\t\t\t复制到\t" + targetDir);
					// 把消息写入到日志文件中
					bw.write(time + "\t\t正在将文件夹:\t\t\t" + sourceDir
							+ "\t\t\t\t复制到\t" + targetDir);
					// 换行
					bw.newLine();
					// 调用复制文件夹方法
					copyDirectiory(sourceDir, targetDir);
				}
			}
		}
	
		// 清楚缓存
		bw.flush();
		System.out.println("恭喜你！已经成功复制！");
		// 关闭缓存读取器
		bw.close();
		// 关闭输出流
		fw.close();

	}

	/**
	 * 复制文件
	 * 
	 * @param sourceFile
	 * @param targetFile
	 * @throws IOException
	 */
	public static void copyFile(File sourceFile, File targetFile)
			throws IOException {
		// 新建文件输入流并对它进行缓冲
		FileInputStream input = new FileInputStream(sourceFile);
		BufferedInputStream inBuff = new BufferedInputStream(input);
		// 新建文件输出流并对它进行缓冲
		FileOutputStream output = new FileOutputStream(targetFile);
		BufferedOutputStream outBuff = new BufferedOutputStream(output);
		// 缓冲数组并且定义缓冲范围
		byte[] b = new byte[1024 * 5];
		// 定义int接受复制的字节
		int len;
		while ((len = inBuff.read(b)) != -1) {
			outBuff.write(b, 0, len);
		}
		// 刷新此缓冲的输出流
		outBuff.flush();
		// 关闭输出输入流以及缓冲区
		inBuff.close();
		outBuff.close();
		output.close();
		input.close();
	}

	/**
	 * 复制文件夹
	 * 
	 * @param sourceDir
	 * @param targetDir
	 * @throws IOException
	 */
	public static void copyDirectiory(String sourceDir, String targetDir)
			throws IOException {
		// 新建目标目录
		(new File(targetDir)).mkdirs();
		// 获取源文件夹当前下的文件或目录
		File[] file = (new File(sourceDir)).listFiles();
		// 循环获取文件夹下所有文件
		for (int i = 0; i < file.length; i++) {
			// 如果是文件
			if (file[i].isFile()) {
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy/MM/dd HH:mm:ss");// 可以方便地修改日期格式
				String time = dateFormat.format(now);
				// 源文件即url1当前文件下的文件名
				File sourceFile = file[i];
				// 目标文件url2当前文件下的绝对路径名/文件名
				File targetFile = new File(
						new File(targetDir).getAbsolutePath() + File.separator
								+ file[i].getName());
				// 打印复制的消息
				System.out.println(time + "\t\t正在将文件:\t\t\t"
						+ file[i].getName() + "\t\t\t\t复制到\t" + targetFile);
				// 将复制的消息写入到日志文件中
				bw.write(time + "\t\t正在将文件:\t\t\t" + file[i].getName()
						+ "\t\t\t\t复制到\t" + targetFile);
				// 换行
				bw.newLine();
				// 调用拷贝文件的方法
				copyFile(sourceFile, targetFile);
			}
			// 如果是文件夹
			if (file[i].isDirectory()) {
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy/MM/dd HH:mm:ss");
				String time = dateFormat.format(now);
				// 准备复制的源文件夹当前目录下的文件夹名称
				String dir1 = sourceDir + "/" + file[i].getName();
				// 准备复制的目标文件夹当前目录下的文件夹名称
				String dir2 = targetDir + "/" + file[i].getName();
				// 打印复制消息
				System.out.println(time + "\t\t正在将文件夹:\t\t\t"
						+ file[i].getName() + "\t\t\t\t复制到\t" + dir2);
				// 写入到日志文件中
				bw.write(time + "\t\t正在将文件夹:\t\t\t" + file[i].getName()
						+ "\t\t\t\t复制到\t" + dir2);
				// 换行
				bw.newLine();
				// 调用拷贝文件方法
				copyDirectiory(dir1, dir2);
			}
		}
	}

	/**
	 * 初始化操作！
	 * 
	 * @throws IOException
	 */
	public static int init(BufferedWriter fw) throws IOException {
		int num=0;
		System.out
				.println("************************************************************************************************");
		System.out
				.println("***********************************欢迎来到白起极速拷贝系统***********************************");
		System.out
				.println("************************************************************************************************");
		System.out.println();
		Scanner input = new Scanner(System.in);
		while (true) {
			System.out.println("请源文件路径：1.选择文件夹\t2.选择文件");
			String choice = input.next();
			if (choice.equals("1")) {
				System.out.println("**********请选择源文件路径************");
				JFileChooser fileChooser = new JFileChooser("D:\\");
				// 设置筛选条件为只能选择文件夹
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				// 打开视图管理器
				int returnVal = fileChooser.showOpenDialog(fileChooser);
				// 如果选择了文件夹
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					// 获取文件夹的绝对路径
					url1 = fileChooser.getSelectedFile().getAbsolutePath();
				}
					Date now = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy/MM/dd HH:mm:ss:SSSS");// 可以方便地修改日期格式
					String time = dateFormat.format(now);
					// 打印源文件消息
					System.out.println(time + "\t已选择源文件夹:\t" + url1);
					// 日志记录
					fw.write(time + "\t已选择源文件夹:\t" + url1);
					// 换行
					fw.newLine();
					num=1;
					break;
				
			}else if (choice.equals("2")) {
				JFileChooser fileChooser = new JFileChooser("D:\\");
				// 设置筛选条件为只能选择文件夹
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				// 打开视图管理器
				int returnVal = fileChooser.showOpenDialog(fileChooser);
				// 如果选择了文件夹
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					// 获取文件夹的绝对路径
					url1 = fileChooser.getSelectedFile().getAbsolutePath();
				}
					Date now = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy/MM/dd HH:mm:ss:SSSS");// 可以方便地修改日期格式
					String time = dateFormat.format(now);
					// 打印源文件消息
					System.out.println(time + "\t已选择源文件:\t" + url1);
					// 日志记录
					fw.write(time + "\t已选择源文件:\t" + url1);
					// 换行
					fw.newLine();
					num=2;
					break;
				
			}else {
				 System.out.println("输入错误！请重新选择！");
				 }
		}
		while (true) {
			System.out.println("请目标文件路径：1.选择文件夹\t2.输入文件夹路径");
			String choice = input.next();
			if (choice.equals("1")) {
				System.out.println("**********请选择源文件路径************");
				JFileChooser fileChooser = new JFileChooser("D:\\");
				// 设置筛选条件为只能选择文件夹
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				// 打开视图管理器
				int returnVal = fileChooser.showOpenDialog(fileChooser);
				// 如果选择了文件夹
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					// 获取文件夹的绝对路径
					url2 = fileChooser.getSelectedFile().getAbsolutePath();
				}
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy/MM/dd HH:mm:ss:SSSS");// 可以方便地修改日期格式
				String time = dateFormat.format(now);
				// 打印源文件消息
				System.out.println(time + "\t已选择源文件夹:\t" + url2);
				// 日志记录
				fw.write(time + "\t已选择源文件夹:\t" + url2);
				// 换行
				fw.newLine();
				break;
			}else if (choice.equals("2")) {
				System.out.println("**********请输入文件夹路径************");
				 url2=input.next();
				 File file = new File(url2);
				 if (file.exists()) {
						Date now = new Date();
						SimpleDateFormat dateFormat = new SimpleDateFormat(
								"yyyy/MM/dd HH:mm:ss:SSSS");// 可以方便地修改日期格式
						String time = dateFormat.format(now);
						// 打印源文件消息
						System.out.println(time + "\t已选目标源文件:\t" + url2);
						// 日志记录
						fw.write(time + "\t已选择目标文件:\t" + url2);
						// 换行
						fw.newLine();
						break;
				 }else {
					System.out.println("你输入的路径不存在！请重新输入!");
				}			
			}else {
				 System.out.println("输入错误！请重新选择！");
				 }
		}
		input.close();
		return num;
	}
}