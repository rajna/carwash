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
import com.carwash.entity.User;

/**
 * 客户缓存
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年12月8日 Time:上午11:01:48
 * <p>
 */
public class Cache
{
	private static Map<String, Customer> customerCaches = new ConcurrentHashMap<String, Customer>();
	private static Map<String, User> userCaches = new ConcurrentHashMap<String, User>();

	public static void putCustomer(Customer customer)
	{
		if (customer == null || customer.getMobile() == null) { return; }
		customerCaches.put(customer.getMobile(), customer);
	}

	public static Customer getCustomer(String mobile)
	{
		return customerCaches.get(mobile);
	}

	public static void putUser(User user)
	{
		if (user == null || user.getMobile() == null) { return; }
		userCaches.put(user.getMobile(), user);
	}

	public static User getUser(String mobile)
	{
		return userCaches.get(mobile);
	}

}
