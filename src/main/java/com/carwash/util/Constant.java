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
 * File name:          Constant.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.util;

/**
 * 常量类
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年11月13日 Time:下午10:06:07
 * <p>
 */
public class Constant {
	public final static boolean isDebug = true;
	/**
	 * 手机号码校验规则
	 */
	public final static String MOBILEREG = "^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$";
	public final static String HTMLCONTENTTYPE = "text/html;charset=UTF-8";
	public final static String JSONCONTENTTYPE = "application/Json;charset=UTF-8";
	public final static String UTF8 = "UTF-8";
	public final static String ACCOUNTERROR = "账号或密码错误";
	public final static String BASESOURCES = "/cwresources";
	public final static String IMAGESSOURCES = "images/";
	public final static String VOICESSOURCES = "voices/";
	//每页查询的数据量
	public final static int SIZEPERPAGE = 10;
}
