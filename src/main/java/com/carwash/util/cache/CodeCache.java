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

import com.carwash.util.Mail;

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
	private static int validateTime = 120;
	/**
	 * 随机生成code的位数
	 */
	private static int codeLength = 4;
	/**
	 * 缓存数量，超过该数量将执行代码回收
	 */
	private static int maxNumber = 2000;
	private static Map<String, Code> maps = new ConcurrentHashMap<String, CodeCache.Code>();

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
					if (System.currentTimeMillis() - code.getTimeMillis() <= validateTime) {
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
	 * 根据手机号查询验证码
	 */
	public static String get(String mobile) {
		Code code = maps.get(mobile);
		if (code == null) {
			return null;
		}
		if (System.currentTimeMillis() - code.getTimeMillis() > validateTime) {
			return null;
		}
		return code.getCode_string();
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

	public static void main(String[] args) throws InterruptedException {
		String mobile = "abcde123456";
		Mail.sendCode(mobile, generate(mobile));
	}
}
