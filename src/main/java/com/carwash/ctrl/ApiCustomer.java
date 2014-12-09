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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carwash.entity.Customer;
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

	@Cwp
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
			return new JSON(true, "更新成功")
					.append("mobile", customer.getMobile())
					.append("password", customer.getPassword())
					.append("carNo", carNo).append("name", name);
		}
		catch (Exception e)
		{
			return new JSON(false, "更新失败");
		}
	}

}
