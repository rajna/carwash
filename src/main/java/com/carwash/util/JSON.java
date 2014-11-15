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
 * File name:          JSON.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.util;

import com.alibaba.fastjson.JSONObject;

/**
 * 返回响应的JSON
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年11月15日 Time:下午10:13:00
 * <p>
 */
public class JSON extends JSONObject {
	private static final long serialVersionUID = -7244129864645852538L;

	public JSON(boolean success, String message) {
		this.put("success", success);
		this.put("message", message);
	}

	public JSON append(String key, Object value) {
		this.put(key, value);
		return this;
	}
}
