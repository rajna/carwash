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
 * File name:          CustomerServiceI.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.service;

import com.carwash.entity.Customer;

/**
 * 客户的数据操作接口
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年11月15日 Time:下午1:49:16
 * <p>
 */
public interface CustomerServiceI {
	/**
	 * 客户数据的保存与修改
	 * 
	 */
	public void saveOrUpdate(Customer o);

	/**
	 * 通过手机号码查询客户
	 */
	public Customer getByMobile(String mobile);

}
