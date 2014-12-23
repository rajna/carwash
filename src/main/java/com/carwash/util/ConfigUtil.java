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
 * File name:          ChinaInitial.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/*
 * File name:          ConfigUtil.java
 * Copyright@Wuxi Information Technology Inc (China)
 * Editor:           JDK1.6.20
 */

/**
 * 读取配置文件内容的工具类
 * <p>
 * Author: ilvelh
 * <p>
 * Date:2012-5-21 Time:上午9:28:17
 * <p>
 */
public class ConfigUtil {
	private static String fromEncoding = "ISO-8859-1";
	private static String toEncoding = "UTF-8";

	public static String getValue(String key) {
		String value = "";
		try {
			value = getProps("config.properties").get(key).toString();
			value = new String(value.getBytes(fromEncoding), toEncoding);
		} catch (Exception e) {
			System.out.println("读取配置文件异常," + e.getMessage());
		}
		return value;
	}

	public static String getValue(String propName, String key) {
		String value = "";
		try {
			value = getProps(propName).get(key).toString();
			value = new String(value.getBytes(fromEncoding), toEncoding);
		} catch (Exception e) {
			System.out.println("读取配置文件异常," + e.getMessage());
		}
		return value;
	}

	private static Properties getProps(String propName)
			throws FileNotFoundException, IOException {
		Properties props = new Properties();
		props.load(ConfigUtil.class.getClassLoader().getResourceAsStream(
				propName));
		return props;
	}
}
