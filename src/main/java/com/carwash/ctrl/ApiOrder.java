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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.carwash.entity.Customer;
import com.carwash.entity.Order;
import com.carwash.entity.OrderItem;
import com.carwash.entity.OrderStatus;
import com.carwash.entity.Product;
import com.carwash.entity.Reservation;
import com.carwash.entity.Role;
import com.carwash.entity.User;
import com.carwash.interceptor.Cwp;
import com.carwash.interceptor.Interceptor;
import com.carwash.service.CustomerServiceI;
import com.carwash.service.OrderServiceI;
import com.carwash.service.ProductServiceI;
import com.carwash.service.ReservationServiceI;
import com.carwash.service.UserServiceI;
import com.carwash.util.Constant;
import com.carwash.util.JSON;
import com.carwash.util.PhoneMessage;

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
	@Autowired
	private ReservationServiceI reservationService;
	@Autowired
	private CustomerServiceI customerService;

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
	@Cwp(
	{ 1, 3 })
	@RequestMapping("listforrese")
	@ResponseBody
	public JSON listforRese(String rid)
	{
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
	 * 新增订单
	 * 
	 * @param id
	 *            预约编号
	 * @param carNo
	 * @param workerId
	 * @param address
	 * @param orderItems
	 * @return
	 */
	@Cwp(
	{ 1, 3 })
	@RequestMapping("post")
	@ResponseBody
	public JSON post(String id, String carNo, String workerId, String address,
			String orderItems)
	{
		User user = Interceptor.threadLocalUser.get();
		if (user == null) { return new JSON(false, Constant.PERMISSIONDENIED); }
		// 预约编号
		int rid = 0;
		// 工作人员编号
		int wid = 0;
		try
		{
			rid = Integer.valueOf(id);
			wid = Integer.valueOf(workerId);
		}
		catch (Exception e)
		{
		}
		if (rid == 0) { return new JSON(false, "该预约不存在"); }
		if (wid == 0) { return new JSON(false, "该服务人员不存在"); }
		Reservation reservation = reservationService.get(rid);
		User worker = userService.get(wid);
		if (reservation == null) { return new JSON(false, "该预约不存在"); }
		if (worker == null) { return new JSON(false, "该服务人员不存在"); }
		if (carNo == null || "".equals(carNo.trim())) { return new JSON(false,
				"车牌号不存在"); }
		if (address == null || "".equals(address.trim())) { return new JSON(
				false, "订单地址不能为空"); }
		Order order = new Order();
		order.setAddress(address);
		order.setCarNo(carNo);
		order.setCustomerId(reservation.getCustomer_id());
		order.setReservation_date(reservation.getCreate_date());
		order.setReservationId(reservation.getId());
		// 登陆者的id
		order.setSupportorId(user.getId());
		// 登陆者的名称
		order.setSupportorName(user.getName());
		order.setWorkerId(worker.getId());
		order.setWorkerName(worker.getName());
		try
		{
			List<Item> items = JSONArray.parseArray(orderItems, Item.class);
			if (items == null)
			{
				items = new ArrayList<Item>();
			}
			Set<OrderItem> ois = new HashSet<OrderItem>();
			for (Item item : items)
			{
				Product product = productService.get(item.getProductId());
				if (product == null)
				{
					continue;
				}
				OrderItem oi = new OrderItem();
				oi.setAmount(item.getAmount());
				oi.setCategoryId(product.getCategoryId());
				oi.setDescription(product.getDescription());
				oi.setImageLink(product.getImageLink());
				oi.setName(product.getName());
				oi.setPrice(product.getPrice());
				oi.setProductId(product.getId());
				ois.add(oi);
			}
			order.setOrderItems(ois);
			orderService.saveOrUpdate(order);
		}
		catch (Exception e)
		{
			return new JSON(false, "订单创建失败!" + e.getMessage());
		}
		return new JSON(true, "订单创建成功!");
	}

	/**
	 * 更新订单
	 * 
	 * @param id
	 *            订单编号
	 * @param carNo
	 *            车牌号
	 * @param wid
	 *            服务人员编号
	 * @return
	 */
	@Cwp(
	{ 1, 3 })
	@RequestMapping("update")
	@ResponseBody
	public JSON update(String id, String carNo, String workerId,
			String address, String orderStatus, String orderItems)
	{
		// 登录权限校验
		User user = Interceptor.threadLocalUser.get();
		if (user == null) { return new JSON(false, Constant.PERMISSIONDENIED); }
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
			if (address == null || "".equals(address.trim())) { return new JSON(
					false, "订单地址不能为空"); }
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
			order.setAddress(address);
			Set<OrderItem> all_orderitems = order.getOrderItems();
			List<Item> items = JSONArray.parseArray(orderItems, Item.class);
			List<Item> add_orderItems = new ArrayList<Item>();
			List<Item> modify_orderItems = new ArrayList<Item>();
			for (Item item : items)
			{
				if (item.getId() == 0)
				{
					add_orderItems.add(item);
				}
				else
				{
					modify_orderItems.add(item);
				}
			}
			List<Integer> not_delete_item_ids = new ArrayList<Integer>();
			for (Item item : modify_orderItems)
			{
				for (OrderItem oi : all_orderitems)
				{
					if (oi.getId() == item.getId())
					{
						oi.setAmount(item.getAmount());
						if (item.getAmount() != 0)
						{
							not_delete_item_ids.add(oi.getId());
						}
					}
				}
			}
			List<Integer> delete_item_ids = new ArrayList<Integer>();
			for (OrderItem oi : all_orderitems)
			{
				boolean isContain = false;
				for (int not_delete_item_id : not_delete_item_ids)
				{
					if (oi.getId() == not_delete_item_id)
					{
						isContain = true;
					}
				}
				if (!isContain)
				{
					delete_item_ids.add(oi.getId());
				}
			}
			List<OrderItem> delorderItems = new ArrayList<OrderItem>();
			for (OrderItem oi : all_orderitems)
			{
				for (int delete_item_id : delete_item_ids)
				{
					if (oi.getId() == delete_item_id)
					{
						delorderItems.add(oi);
					}
				}
			}
			all_orderitems.removeAll(delorderItems);
			for (Item item : add_orderItems)
			{
				if (item.getAmount() == 0)
				{
					continue;
				}
				Product product = productService.get(item.getProductId());
				if (product == null)
				{
					continue;
				}
				OrderItem oi = new OrderItem();
				oi.setAmount(item.getAmount());
				oi.setCategoryId(product.getCategoryId());
				oi.setDescription(product.getDescription());
				oi.setImageLink(product.getImageLink());
				oi.setName(product.getName());
				oi.setPrice(product.getPrice());
				oi.setProductId(product.getId());
				all_orderitems.add(oi);
			}
			order.setOrderItems(all_orderitems);
			orderService.saveOrUpdate(order);
		}
		catch (Exception e)
		{
			return new JSON(false, "订单修改失败!" + e.getMessage());
		}
		return new JSON(true, "订单修改成功!");
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

	@Cwp(0)
	@RequestMapping("listforworker")
	@ResponseBody
	public JSON listforuser(String t)
	{
		int type = 0;
		try
		{
			type = Integer.valueOf(t);
		}
		catch (Exception e)
		{
		}
		if (type != 0 && type != 1)
		{
			type = 0;
		}
		User user = Interceptor.threadLocalUser.get();
		if (user == null || user.getRole() == null) { return new JSON(false,
				Constant.ACCOUNTERROR).append("relogin", true); }
		if (user.getRole().ordinal() != Role.WORKER.ordinal()) { return new JSON(
				false, "您无权查询任务列表"); }
		List<Order> orders = orderService.findByUid(user.getId(), type);
		return new JSON(true, "查询成功").append("orders", orders);
	}

	@Cwp(0)
	@RequestMapping(value = "checkorder", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject checkorder(String orderstring)
	{
		String error = "订单参数异常";
		User user = Interceptor.threadLocalUser.get();
		if (user == null || user.getRole() == null) { return new JSON(false,
				Constant.ACCOUNTERROR).append("relogin", true); }
		if (user.getRole().ordinal() != Role.WORKER.ordinal()) { return new JSON(
				false, Constant.PERMISSIONDENIED); }
		Order order = null;
		try
		{
			order = JSONObject.parseObject(orderstring, Order.class);
		}
		catch (Exception e)
		{
			return new JSON(false, error);
		}
		if (order == null || order.getId() == 0
				|| order.getOrderItems().isEmpty()) { return new JSON(false,
				error); }
		Order orderInDatabase = orderService.get(order.getId());
		if (orderInDatabase == null) { return new JSON(false, "该订单已不存在"); }
		if (orderInDatabase.getOrderStatus().ordinal() == OrderStatus.COMPLETED
				.ordinal()) { return new JSON(false, "订单已经结算,结算失败"); }
		if (orderInDatabase.getOrderStatus().ordinal() == OrderStatus.CANCELED
				.ordinal()) { return new JSON(false, "订单已经取消,结算失败"); }
		Customer customer = customerService
				.get(orderInDatabase.getCustomerId());
		if (customer == null || customer.getMobile() == null) { return new JSON(
				false, "订单客户数据不存在"); }
		Set<OrderItem> orderItems = order.getOrderItems();
		double tPrice = 0;
		orderInDatabase.getOrderItems().clear();
		for (OrderItem orderItem : orderItems)
		{
			orderItem.setId(0);
			if (orderItem.getProductId() == 0)
			{
				continue;
			}
			Product product = productService.get(orderItem.getProductId());
			if (product == null)
			{
				continue;
			}
			OrderItem oi = new OrderItem();
			oi.setAmount(orderItem.getAmount());
			oi.setPrice(product.getPrice());
			oi.setCategoryId(product.getCategoryId());
			oi.setDescription(product.getDescription());
			oi.setImageLink(product.getImageLink());
			oi.setName(product.getName());
			oi.setProductId(product.getId());
			tPrice += orderItem.getAmount() * product.getPrice();
			orderInDatabase.getOrderItems().add(oi);
		}
		// 将传回来的订单数据的id改成0
		try
		{
			// 异步发送短信至客户手机
			PhoneMessage.sendCheckOrderMessage(tPrice, "18601595393");
			order.setId(0);
			orderService.saveOrUpdate(orderInDatabase);
			return new JSON(true, "验证码发送成功");
		}
		catch (Exception e)
		{
			return new JSON(false, "验证码发送失败");
		}
	}
}
