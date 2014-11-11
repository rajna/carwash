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
 * File name:          PayRecord.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.entity;

import java.util.Date;

/**
 * 消费或充值记录
 * <p>
 * Author: ilvelh
 * <p>
 * Date:2014-11-11 Time:下午4:47:53
 * <p>
 */
public class PayRecord {
	private int id;
	private PayConsume payConsume; //充值或消费类型 不能为空
	private Date create_date = new Date();
	private String userName;
	private int userId;
	private String customerMobile;
	private int customerId;
	private double money; // 用户实际支付金额
	private double add_credit; // 用户实得账户金额
	private double cash; //用户现金支付金额
	private double minus_credit;// 用户实得金额
	
}
