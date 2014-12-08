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
 * File name:          CustomerCache.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.ctrl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.carwash.entity.Customer;

/**
 * 客户缓存
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年12月8日 Time:上午11:01:48
 * <p>
 */
public class CustomerCache
{
	private static Map<String, Customer> caches = new ConcurrentHashMap<String, Customer>();

	public static void put(Customer customer)
	{
		if (customer == null || customer.getMobile() == null) { return; }
		caches.put(customer.getMobile(), customer);
	}

	public static Customer get(String mobile)
	{
		return caches.get(mobile);
	}
}
