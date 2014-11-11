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
 * File name:          Reservation.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.entity;

import java.util.Date;

/**
 * 预约
 * <p>
 * Author: ilvelh
 * <p>
 * Date:2014-11-11 Time:下午3:36:57
 * <p>
 */
public class Reservation {
	private int id;
	private int customer_id; // 客户的编号
	private String customer_name;// 客户的名字
	private Date create_date = new Date(); // 预约创建日期
	private Date reservation_date; // 预约服务时间
	private String address; //客户服务地址
	private String message_text;  //用户发起预约时输入的文字描述
	private String message_voice_url; //用户发起预约时的语言留言
}
