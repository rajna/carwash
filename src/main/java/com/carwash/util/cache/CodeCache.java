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
 * File name:          CodeCache.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.util.cache;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 验证码缓存工具
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年11月15日 Time:下午2:18:12
 * <p>
 */
public class CodeCache {
	private static Object lockObj = new Object();
	/**
	 * code在缓存中存活的时间,单位秒
	 */
	public final static int leftTime = 30 * 60 * 1000;
	/**
	 * 随机生成code的位数
	 */
	private static int codeLength = 4;
	/**
	 * 校验次数
	 */
	public static int times = 5;
	/**
	 * 缓存数量，超过该数量将执行代码回收
	 */
	private static int maxNumber = 2000;
	private static Map<String, Code> maps = new ConcurrentHashMap<String, CodeCache.Code>();
	private static Map<String, Integer> timeMaps = new ConcurrentHashMap<String, Integer>();

	/**
	 * 通过手机号码生成验证码并且放入缓存
	 * 
	 */
	public static String generate(String mobile) {
		synchronized (lockObj) {
			if (maps.size() > maxNumber) {
				Set<String> keySet = maps.keySet();
				for (String key : keySet) {
					Code code = maps.get(key);
					if (System.currentTimeMillis() - code.getTimeMillis() <= leftTime) {
						continue;
					}
					maps.remove(key);
				}
			}
		}
		Code code = new Code();
		maps.put(mobile, code);
		return code.getCode_string();
	}

	/**
	 * 设置校验次数,超过次数将验证码清空
	 */
	public static boolean verfiy(String mobile, String code) {
		if (code == null) {
			return false;
		}
		if (code.equals(get(mobile))) {
			timeMaps.remove(mobile);
			remove(mobile);
			return true;
		}
		Integer integer = timeMaps.get(mobile);
		if (integer == null) {
			integer = 1;
		} else {
			integer = integer + 1;
		}
		if (integer > times) {
			remove(mobile);
			timeMaps.remove(mobile);
		} else {
			timeMaps.put(mobile, integer);
		}
		return false;
	}

	/**
	 * 根据手机号查询验证码
	 */
	private static String get(String mobile) {
		Code code = maps.get(mobile);
		if (code == null) {
			return null;
		}
		if (System.currentTimeMillis() - code.getTimeMillis() > leftTime) {
			return null;
		}
		return code.getCode_string();
	}

	/**
	 * 移除掉手机号码的验证码
	 * 
	 */
	public static void remove(String mobile) {
		maps.remove(mobile);
	}

	private static class Code {
		private String code_string = randomCode(codeLength);
		private Long timeMillis = System.currentTimeMillis();

		public Long getTimeMillis() {
			return timeMillis;
		}

		public String getCode_string() {
			return code_string;
		}

	}

	/**
	 * 生成随机size的验证码
	 */
	private static String randomCode(int size) {
		StringBuffer codeBuffer = new StringBuffer();
		for (int i = 0; i < size; i++) {
			codeBuffer.append(new Random().nextInt(10));
		}
		return codeBuffer.toString();
	}

}
