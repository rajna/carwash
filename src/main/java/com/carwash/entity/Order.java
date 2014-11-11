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
 * File name:          Order.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.entity;

import java.util.Date;
import java.util.Set;

/**
 * 订单数据结构
 * <p>
 * Author: ilvelh
 * <p>
 * Date:2014-11-11 Time:下午3:25:44
 * <p>
 */
public class Order {
	private int id;
	private int reservationId; // 预约编号
	private String orderId;
	private Date create_date = new Date();
	private String address; // 服务地址 限制
	private Set<OrderItem> orderItems;
	private Date reservation_date; // 预约服务时间
	private int workerId; // 服务人员编号
	private int supportorId; // 客服人员编号
	private String workerName; // 服务人员名字
	private String supportorName; // 客服人员名字
	private OrderStatus orderStatus = OrderStatus.PROCESSING; // 订单状态
	private Date complete_date; // 订单完结时间
}
