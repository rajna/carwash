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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.carwash.entity.Customer;
import com.carwash.entity.Order;
import com.carwash.entity.OrderItem;
import com.carwash.entity.OrderStatus;
import com.carwash.entity.Product;
import com.carwash.entity.User;
import com.carwash.interceptor.Cwp;
import com.carwash.interceptor.Interceptor;
import com.carwash.service.OrderServiceI;
import com.carwash.service.ProductServiceI;
import com.carwash.service.UserServiceI;
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
	@Autowired
	private UserServiceI userService;
	@Autowired
	private ProductServiceI productService;

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
	// @Cwp(1)
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

	/**
	 * 更新订单
	 * 
	 * @param oid
	 *            订单编号
	 * @param carNo
	 *            车牌号
	 * @param wid
	 *            服务人员编号
	 * @return
	 */
	// @Cwp(1)
	@RequestMapping("update")
	@ResponseBody
	public JSON update(String id, String carNo, String workerId,
			String orderStatus, String orderItems)
	{
		// TODO 登录权限校验
		int oid = 0;
		int wid = 0;
		OrderStatus oStatus = null;
		if (orderStatus == null || "".equals(orderStatus.trim())) { return new JSON(
				false, "不存在该状态"); }
		try
		{
			oStatus = OrderStatus.valueOf(orderStatus);
			if (oStatus == null) { return new JSON(false, "不存在该状态"); }
			if (carNo == null || "".equals(carNo.trim())) { return new JSON(
					false, "车牌号不存在"); }
			if (id == null) { return new JSON(false, "订单编号不存在(null)"); }
			oid = Integer.valueOf(id);
			Order order = orderService.get(oid);
			if (order == null) { return new JSON(false, "该订单不存在"); }
			wid = Integer.valueOf(workerId);
			User worker = userService.get(wid);
			if (worker == null) { return new JSON(false, "该服务人员不存在"); }
			order.setOrderStatus(oStatus);
			order.setCarNo(carNo);
			order.setWorkerId(wid);
			order.setWorkerName(worker.getName());
			List<Item> items = JSONArray.parseArray(orderItems, Item.class);
			List<Integer> add_product_ids = new ArrayList<Integer>();
			List<Integer> modify_product_ids = new ArrayList<Integer>();
			for (Item item : items)
			{
				if (item.getId() == 0)
				{
					add_product_ids.add(item.getProductId());
				}
				else
				{
					modify_product_ids.add(item.getProductId());
				}
			}
			// 查询出待增加的产品
			List<Product> products = productService.find(add_product_ids);
			// 修改的订单子项id
			for (int modify_id : modify_product_ids)
			{
				for (OrderItem oi : order.getOrderItems())
				{
					if (modify_id == oi.getId())
					{
						
					}
				}
			}

			orderService.saveOrUpdate(order);
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * 前台传递的订单子项对象
	 * 
	 */
	public static class Item
	{
		private int id;
		private int productId;
		private int amount;

		public int getId()
		{
			return id;
		}

		public void setId(int id)
		{
			this.id = id;
		}

		public int getProductId()
		{
			return productId;
		}

		public void setProductId(int productId)
		{
			this.productId = productId;
		}

		public int getAmount()
		{
			return amount;
		}

		public void setAmount(int amount)
		{
			this.amount = amount;
		}

	}

}
