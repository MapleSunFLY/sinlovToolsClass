/**
 * 
 */
package com.sinlov.toolsclass.dateexpress;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author erZheng
 * @date 2014-9-25 下午1:24:30
 */
public class DateClass {
	
	/**
	 * this method return a string about now time.
	 * Format is  yyyy-MM-dd HH:mm:ss.Millsecond
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String getNowInfoTime() {
		String millisetime = null;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar nowCal = Calendar.getInstance();
		//make millisecond three-digit number
		if ((nowCal.get(Calendar.MILLISECOND)/100)==0) {
			millisetime = "0"+String.valueOf(nowCal.get(Calendar.MILLISECOND));
		}else if((nowCal.get(Calendar.MILLISECOND)/10)==0){
			millisetime = "0"+"0"+String.valueOf(nowCal.get(Calendar.MILLISECOND));
		}else {
			millisetime = String.valueOf(nowCal.get(Calendar.MILLISECOND));
		}
		
		String nowInfoTime = sdf.format(date) + "." + millisetime;
		return nowInfoTime;
	}
	
	/**
	 * this method return a string about now time by format(yyyy-MM-dd HH:mm:ss.SSS).
	 * @return nowFormatTime String
	 */
	@SuppressWarnings("unused")
	private static String getNowFormatTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String nowFormatTime = sdf.format(date);
		return nowFormatTime;
	}
	
	/**
	 * this method return a string from long time to format (yyyy-MM-dd HH:mm:ss.SSS)
	 * @param inTime
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String getNowFormatTimeByLong(long inTime){
		Date tempDate = new Date(inTime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String time = sdf.format(tempDate);
		return time;
	}
	
	/**
	 * Test data method
	 * @throws ParseException
	 */
	@SuppressWarnings("unused")
	private void testDate() throws ParseException {
		Calendar now = Calendar.getInstance();
		System.out.println("年: " + now.get(Calendar.YEAR));
		System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");
		System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));
		System.out.println("时: " + now.get(Calendar.HOUR_OF_DAY));
		System.out.println("分: " + now.get(Calendar.MINUTE));
		System.out.println("秒: " + now.get(Calendar.SECOND));
		System.out.println("当前时间毫秒数：" + now.getTimeInMillis());
		System.out.println(now.getTime());

		Date d = new Date();
		System.out.println(d);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(d);
		System.out.println("格式化后的日期：" + dateNowStr);
		
		String str = "2012-1-13 17:26:33";	//要跟上面sdf定义的格式一样
		Date today = sdf.parse(str);
		System.out.println("字符串转成日期：" + today);
	}
}
