/*
 *	               _ooOoo_ 
 *	              o8888888o 
 *	              88" . "88 
 *	              (| -_- |) 
 *	              O\  =  /O 
 *	           ____/`---'\____ 
 *	         .'  \\|     |//  `. 
 *	        /  \\|||  :  |||//  \ 
 *	       /  _||||| -:- |||||-  \ 
 *	       |   | \\\  -  /// |   | 
 * 	       | \_|  ''\---/''  |   | 
 * 	       \  .-\__  `-`  ___/-. / 
 * 	    ___ `. .'  /--.--\  `. . __ 
 *	  ."" '<  `.___\_<|>_/___.'  >'"". 
 *	 | | :  `- \`.;`\ _ /`;.`/ - ` : | | 
 *	 \  \ `-.   \_ __\ /__ _/   .-` /  / 
 *======`-.____`-.___\_____/___.-`____.-'====== 
 *                   `=---=' 
 *^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ 
 *佛祖保佑       永无BUG 
 * File name:         DateUtil.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类
 * <p>
 * Author: ilveh
 * <p>
 * Date:2013-7-22 Time:下午4:22:21
 * <p>
 */
public class DateUtil {
	/**
	 * 获取本年数据
	 */
	public static int currentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * 获取当月月份 已经加1
	 */
	public static int currentMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取当月的日期
	 */
	public static int currentDATE() {
		return Calendar.getInstance().get(Calendar.DATE);
	}

	/**
	 * 获取当前的小时 24小时制
	 */
	public static int currentHour() {
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 比较2个日期是否是同一天
	 * 
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return false;
		}
		if (format(date1).equals(format(date2))) {
			return true;
		}
		return false;
	}

	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @param pattern
	 *            字符串格式 如果patten为null 则默认yyyy-MM-dd HH:mm:ss
	 * @return 日期字符串
	 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		try {
			return new java.text.SimpleDateFormat(pattern).format(date);
		} catch (Exception e) {

		}
		return null;

	}

	/**
	 * 将Date类型转换为字符串 yyyy-MM-dd
	 * 
	 * @param date
	 *            日期类型
	 * @return 日期字符串
	 */
	public static String format(Date date) {
		return format(date, "yyyy-MM-dd");
	}

	/**
	 * 将Date类型转换为字符串 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            日期类型
	 * @return 日期字符串
	 */
	public static String formatToSecond(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将Date类型转换为字符串 yyyy-MM-dd HH:mm:ss SSS
	 * 
	 * @param date
	 *            日期类型
	 * @return 日期字符串
	 */
	public static String formatToMilli(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss SSS");
	}

	/**
	 * 将Date类型转换为字符串 yyyyMMddHHmmssSSS
	 * 
	 * @param date
	 *            日期类型
	 * @return 日期字符串
	 */
	public static String formatToString(Date date) {
		return format(date, "yyyyMMddHHmmssSSS");
	}

	/**
	 * 将字符串转换为Date类型
	 * 
	 * @param date
	 *            字符串类型
	 * @param pattern
	 *            格式字符 如果为null 则默认yyyy-MM-dd HH:mm:ss
	 * @return 日期类型
	 * @throws ParseException
	 */
	public static Date format(String date, String pattern) {
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		if (date == null || date.equals("") || date.equals("null")) {
			return null;
		}
		Date d = null;
		try {
			d = new SimpleDateFormat(pattern).parse(date);
		} catch (Exception e) {
			System.out.println("转换失败:date=" + date + "\tpattern=" + pattern);
		}
		return d;
	}

	/**
	 * 将字符串转换为Date类型 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            字符串类型
	 * @return 日期类型
	 */
	public static Date format(String date) {
		return format(date, null);
	}

	/**
	 * Calendar 转成日期
	 * 
	 * @param c
	 * @param pattern
	 *            格式化字符 如果为空 则默认yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date toDate(Calendar c, String pattern) {
		Date date = c.getTime();
		String str = format(date);
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		try {
			date = new java.text.SimpleDateFormat(pattern).parse(str);
		} catch (ParseException pe) {
			System.out.println("操作失败:" + c + "日期格式转换失败!" + pe.getMessage());
		}
		return date;
	}

	/**
	 * 比较2个日期
	 * 
	 * @param star
	 *            Date
	 * @param end
	 *            Date
	 * @param pattern
	 *            格式化字符 ,如果为空 则默认yyyy-MM-dd HH:mm:ss
	 * @return boolean
	 * @throws ParseException
	 * 
	 */
	public static boolean compareDate(Date star, Date end, String pattern)
			throws ParseException {
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		star = DateUtil.format(DateUtil.format(star, pattern), pattern);
		end = DateUtil.format(DateUtil.format(end, pattern), pattern);
		return star.equals(end);
	}

	/**
	 * 随机生成日期,用于测试数据
	 * 
	 * @param sdate
	 *            开始日期
	 * @param edate
	 *            结束日期
	 * @return 可能为null
	 */
	public static Date generateDate(Date sdate, Date edate) {
		if (sdate == null || edate == null) {
			System.out.println("传入的参数开始日期与结束日期不能为空");
			return null;
		}
		if (sdate.getTime() >= edate.getTime()) {
			System.out.println("结束日期不能在开始日期之前");
			return null;
		}
		long date = random(sdate.getTime(), edate.getTime());
		return new Date(date);
	}

	/**
	 * 获取日期当天的开始时间,如果2014-09-12 15:22:54 则返回数据位2014-09-12 00:00:00
	 */
	public static Date todayStart(Date date) {
		Calendar currentDate = new GregorianCalendar();
		currentDate.setTime(date);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		currentDate.set(Calendar.MILLISECOND, 0);
		return currentDate.getTime();
	}

	/**
	 * 获取日期当天的结束时间,如果2014-09-12 15:22:54 则返回数据位2014-09-12 23:59:59:999
	 */
	public static Date todayEnd(Date date) {
		Calendar currentDate = new GregorianCalendar();
		currentDate.setTime(date);
		currentDate.set(Calendar.HOUR_OF_DAY, 23);
		currentDate.set(Calendar.MINUTE, 59);
		currentDate.set(Calendar.SECOND, 59);
		currentDate.set(Calendar.MILLISECOND, 999);
		return currentDate.getTime();
	}

	/**
	 * 获取当前系统时间与传入时间的相隔秒数
	 * 
	 * @param date
	 * 
	 * @param isLast
	 *            是否要将该日期转换成日期的最后时间(日期不变 时间 改成23:59:59:999)
	 * @return
	 */
	public static int getDiffTime(Date date, boolean isLast) {
		if (isLast) {
			date = todayEnd(date);
		}
		int diff = Math
				.abs((int) ((date.getTime() - System.currentTimeMillis()) / 1000));
		return diff;
	}

	/**
	 * 获取当前系统时间与传入时间的相隔天/时/分/秒
	 * 
	 * @param date
	 * 
	 * @param isLast
	 *            是否要将该日期转换成日期的最后时间(日期不变 时间 改成23:59:59:999)
	 * @return
	 */
	public static String getDiffTimeString(Date date, boolean isLast) {
		if (date == null) {
			return "未知";
		}
		int diff = getDiffTime(date, isLast);
		int day = diff / (24 * 60 * 60);
		int hour = (diff / (60 * 60) - day * 24);
		int min = ((diff / 60) - day * 24 * 60 - hour * 60);
		int sec = diff - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60;
		return day + "天" + hour + "时" + min + "分" + sec + "秒";
	}

	public static void main(String[] args) {
		Date date = format("2014-09-10 17:14:00");
		System.out.println(getDiffTimeString(date, true));
	}

	/**
	 * 
	 * @param beginDate
	 *            开始日期的字符
	 * @param endDate
	 *            结束日期的字符
	 * @param pattern
	 *            格式化传入的日期格式 如果为null则pattern为"yyyy-MM-dd HH:mm:ss"
	 * @return 可能为null
	 */
	public static Date randomDate(String beginDate, String endDate,
			String pattern) {
		if (beginDate == null || "".equals(beginDate.trim()) || endDate == null
				|| "".equals(endDate.trim())) {
			System.out.println("传入的参数开始日期与结束日期不能为空");
			return null;
		}
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		try {
			Date sdate = format(beginDate, pattern);
			Date edate = format(endDate, pattern);
			return generateDate(sdate, edate);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 递归的产生开始时间与结束时间之间的long类型数据
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	private static long random(long begin, long end) {
		long rtn = begin + (long) (Math.random() * (end - begin));
		// 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
		if (rtn == begin || rtn == end) {
			return random(begin, end);
		}
		return rtn;
	}

}
