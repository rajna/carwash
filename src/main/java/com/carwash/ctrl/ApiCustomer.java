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
 * File name:          ApiCustomer.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.ctrl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.carwash.entity.Customer;
import com.carwash.entity.User;
import com.carwash.interceptor.Cwp;
import com.carwash.interceptor.Interceptor;
import com.carwash.service.CustomerServiceI;
import com.carwash.util.Constant;
import com.carwash.util.JSON;

/**
 * 客户服务web接口
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年12月9日 Time:上午9:40:12
 * <p>
 */
@Controller
@RequestMapping("/api/customer")
public class ApiCustomer
{
	@Autowired
	private CustomerServiceI customerService;

	@Cwp(0)
	@RequestMapping("view")
	@ResponseBody
	public JSON view()
	{
		Customer customer = Interceptor.threadLocalCustomer.get();
		if (customer == null) { return new JSON(false, Constant.ACCOUNTERROR)
				.append("relogin", true); }
		customer = customerService.get(customer.getId());
		if (customer == null) { return new JSON(false, "账户不存在").append(
				"relogin", true); }
		return new JSON(true, "查询成功").append("customer",
				JSONObject.toJSON(customer));
	}

	/**
	 * 查询客户，用于后台界面
	 * 
	 * @return
	 */
	@Cwp(1)
	@RequestMapping("list")
	@ResponseBody
	public JSON list()
	{
		return new JSON(true, "查询成功").append("customers",
				customerService.find());
	}

	/**
	 * 新增客户，用于后台界面
	 * 
	 * @return
	 */
	@Cwp(1)
	@RequestMapping("post")
	@ResponseBody
	public JSON post(Customer customer)
	{
		User user = Interceptor.threadLocalUser.get();
		if (user == null) { return new JSON(false, Constant.PERMISSIONDENIED); }
		String mobile = customer.getMobile();
		if (mobile == null) { return new JSON(false, "手机号码不能为空"); }
		Pattern p = Pattern.compile(Constant.MOBILEREG);
		Matcher m = p.matcher(mobile);
		if (!m.find()) { return new JSON(false, "手机号码不规范"); }
		String carNo = customer.getCarNo();
		String name = customer.getName();
		if (carNo == null || "".equals(carNo.trim())) { return new JSON(false,
				"请填写车牌号"); }
		if (name == null || "".equals(name)) { return new JSON(false, "请填写客户姓名"); }
		Customer byMobile = customerService.getByMobile(mobile);
		if (byMobile != null) { return new JSON(false, "该手机号码已经被使用"); }
		try
		{
			customer.setReffer_work_id(user.getId() + "");
			customerService.saveOrUpdate(customer);
			return new JSON(true, "新增客户成功");
		}
		catch (Exception e)
		{
			return new JSON(false, "新增客户失败" + e.getMessage());
		}
	}

	/**
	 * 客户更新车号与姓名
	 */
	@Cwp(0)
	@RequestMapping("update")
	@ResponseBody
	public JSON update(String carNo, String name)
	{
		Customer customer = Interceptor.threadLocalCustomer.get();
		if (customer == null) { return new JSON(false, Constant.ACCOUNTERROR)
				.append("relogin", true); }
		customer.setCarNo(carNo);
		customer.setName(name);
		try
		{
			customerService.saveOrUpdate(customer);
			return new JSON(true, "更新成功").append("customer",
					JSONObject.toJSON(customer));
		}
		catch (Exception e)
		{
			return new JSON(false, "更新失败");
		}
	}

}
