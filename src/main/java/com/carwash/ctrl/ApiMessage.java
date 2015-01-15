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
 * File name:          ApiMessage.java
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
import com.carwash.entity.Message;
import com.carwash.entity.User;
import com.carwash.interceptor.Cwp;
import com.carwash.interceptor.Interceptor;
import com.carwash.service.MessageServiceI;
import com.carwash.util.Constant;
import com.carwash.util.JSON;

/**
 * 消息类的接口
 * <p>
 * Author: ilvel
 * <p>
 * Date:2015年1月15日 Time:上午10:18:52
 * <p>
 */
@Controller
@RequestMapping("/api/message")
public class ApiMessage
{
	@Autowired
	private MessageServiceI messageService;

	/**
	 * 查询客户消息列表
	 * 
	 * @return
	 */
	@Cwp(0)
	@RequestMapping("clist")
	@ResponseBody
	public JSON clist()
	{
		Customer customer = Interceptor.threadLocalCustomer.get();
		if (customer == null) { return new JSON(false, Constant.ACCOUNTERROR)
				.append("relogin", true); }
		List<Message> messages = messageService.findByCid(customer.getId());
		return new JSON(true, "查询成功").append("messages", messages);
	}

	/**
	 * 用户查询消息列表
	 * 
	 * @return
	 */
	@Cwp(0)
	@RequestMapping("ulist")
	@ResponseBody
	public JSON ulist()
	{
		User user = Interceptor.threadLocalUser.get();
		if (user == null) { return new JSON(false, Constant.ACCOUNTERROR)
				.append("relogin", true); }
		List<Message> messages = messageService.findByUid(user.getId());
		return new JSON(true, "查询成功").append("messages", messages);
	}

	/**
	 * 设置用户所有信息状态为已读
	 * 
	 * @return
	 */
	@Cwp(0)
	@RequestMapping("ureadall")
	@ResponseBody
	public JSON u_readall()
	{
		User user = Interceptor.threadLocalUser.get();
		if (user == null) { return new JSON(false, Constant.ACCOUNTERROR)
				.append("relogin", true); }
		messageService.readallByUid(user.getId());
		return new JSON(true, "消息状态修改成功");
	}

	/**
	 * 设置客户所有信息状态为已读
	 * 
	 * @return
	 */
	@Cwp(0)
	@RequestMapping("creadall")
	@ResponseBody
	public JSON c_readall()
	{
		Customer customer = Interceptor.threadLocalCustomer.get();
		if (customer == null) { return new JSON(false, Constant.ACCOUNTERROR)
				.append("relogin", true); }
		messageService.readallByCid(customer.getId());
		return new JSON(true, "消息状态修改成功");
	}

	/**
	 * 设置客户户某一条信息状态为已读
	 * 
	 * @return
	 */
	@Cwp(0)
	@RequestMapping("cread")
	@ResponseBody
	public JSON c_read(String mid)
	{
		Customer customer = Interceptor.threadLocalCustomer.get();
		if (customer == null) { return new JSON(false, Constant.ACCOUNTERROR)
				.append("relogin", true); }
		int id = 0;
		try
		{
			id = Integer.valueOf(mid);
		}
		catch (Exception e)
		{
		}
		if (id == 0) { return new JSON(false, "消息编号不正确"); }
		Message message = messageService.get(id);
		if (message == null) { return new JSON(false, "消息不存在"); }
		if (message.getCustomerId() != customer.getId()) { return new JSON(
				false, "无权修改该消息状态"); }
		try
		{
			message.setReaded(true);
			messageService.saveOrUpdate(message);
		}
		catch (Exception e)
		{
			return new JSON(false, "消息状态修改失败");
		}
		return new JSON(true, "消息状态修改成功");
	}

	/**
	 * 设置用户某一条信息状态为已读
	 * 
	 * @return
	 */
	@Cwp(0)
	@RequestMapping("uread")
	@ResponseBody
	public JSON u_read(String mid)
	{
		User user = Interceptor.threadLocalUser.get();
		if (user == null) { return new JSON(false, Constant.ACCOUNTERROR)
				.append("relogin", true); }
		int id = 0;
		try
		{
			id = Integer.valueOf(mid);
		}
		catch (Exception e)
		{
		}
		if (id == 0) { return new JSON(false, "消息编号不正确"); }
		Message message = messageService.get(id);
		if (message == null) { return new JSON(false, "消息不存在"); }
		if (message.getUserId() != user.getId()) { return new JSON(false,
				"无权修改该消息状态"); }
		try
		{
			message.setReaded(true);
			messageService.saveOrUpdate(message);
		}
		catch (Exception e)
		{
			return new JSON(false, "消息状态修改失败");
		}
		return new JSON(true, "消息状态修改成功");
	}
}
