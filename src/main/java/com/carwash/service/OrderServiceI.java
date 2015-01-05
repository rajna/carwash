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
 * File name:          OrderServiceI.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.service;

import java.util.List;

import com.carwash.entity.Order;
import com.carwash.entity.OrderStatus;

/**
 * 订单服务接口
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年12月8日 Time:上午10:05:55
 * <p>
 */
public interface OrderServiceI
{
	public void saveOrUpdate(Order order);

	/**
	 * 查询客户订单
	 */
	public List<Order> findByCid(int cid, OrderStatus status);

	/**
	 * 通过预约id查询订单
	 */
	public List<Order> findByRid(int rid);

	/**
	 * 查询订单
	 */
	public Order get(int id);
}
