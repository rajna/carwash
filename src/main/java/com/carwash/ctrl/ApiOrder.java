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
 * File name:          ApiOrder.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carwash.entity.Customer;
import com.carwash.entity.Order;
import com.carwash.entity.OrderStatus;
import com.carwash.interceptor.Cwp;
import com.carwash.interceptor.Interceptor;
import com.carwash.service.OrderServiceI;
import com.carwash.util.Constant;
import com.carwash.util.JSON;

/**
 * 用户订单web接口
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年12月8日 Time:上午11:14:53
 * <p>
 */
@Controller
@RequestMapping("/api/order")
public class ApiOrder
{
	@Autowired
	private OrderServiceI orderService;

	@Cwp(0)
	@RequestMapping("list")
	@ResponseBody
	public JSON list(String status)
	{
		Customer customer = Interceptor.threadLocalCustomer.get();
		if (customer == null) { return new JSON(false, Constant.ACCOUNTERROR)
				.append("relogin", true); }
		OrderStatus os = null;

		if (status != null)
		{
			status = status.toUpperCase().trim();
			try
			{
				os = OrderStatus.valueOf(status);
			}
			catch (Exception e)
			{
			}
		}

		return new JSON(true, "查询成功").append("orders",
				orderService.findByCid(customer.getId(), os));
	}

	/**
	 * 通过预约编号查询订单
	 * 
	 * @param rid
	 * @return
	 */
	@Cwp(1)
	@RequestMapping("listforrese")
	@ResponseBody
	public JSON listforRese(String rid)
	{
		// TODO 权限判断
		int resId = 0;
		try
		{
			resId = Integer.valueOf(rid);
		}
		catch (Exception e)
		{
		}
		List<Order> orders = orderService.findByRid(resId);
		return new JSON(true, "查询成功").append("orders", orders);
	}
}
