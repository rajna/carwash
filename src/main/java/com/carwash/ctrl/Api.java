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
 * File name:          Api.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.ctrl;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carwash.category.CategoryUtil;
import com.carwash.entity.Customer;
import com.carwash.entity.Device;
import com.carwash.service.CustomerServiceI;
import com.carwash.service.RecommendServiceI;
import com.carwash.util.Constant;
import com.carwash.util.JSON;
import com.carwash.util.PhoneMessage;
import com.carwash.util.cache.CodeCache;

/**
 * 客户端api控制器
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年11月15日 Time:下午9:25:53
 * <p>
 */
@Controller
@RequestMapping("/api")
public class Api
{
	@Autowired
	private CustomerServiceI customerService;
	@Autowired
	private RecommendServiceI recommendService;

	/**
	 * 客户通过手机端获取验证码
	 */
	@RequestMapping(value = "customercode")
	@ResponseBody
	public JSON customercode(final String mobile)
	{
		if (mobile == null) { return new JSON(false, "手机号码不能为空"); }
		Pattern p = Pattern.compile(Constant.MOBILEREG);
		Matcher m = p.matcher(mobile);
		if (!m.find()) { return new JSON(false, "手机号码不规范"); }
		Customer customer = customerService.getByMobile(mobile);
		if (customer != null && !customer.isInuse()) { return new JSON(false,
				"该账户已停用"); }
		if (customer == null)
		{
			customer = new Customer(mobile);
			try
			{
				customerService.saveOrUpdate(customer);
			}
			catch (Exception e)
			{
				return new JSON(false, "验证码发送失败");
			}
		}
		// 将发送手机验证码交个异步线程处理
		new Thread(new Runnable()
		{
			public void run()
			{
				PhoneMessage.sendLoginMessage(mobile);
			}
		}).start();
		return new JSON(true, "验证码发送成功").append("leftTime", CodeCache.leftTime);
	}

	/**
	 * 客户手机端登录，返回客户密码
	 */
	@RequestMapping("customerlogin")
	@ResponseBody
	public JSON customerlogin(String mobile, String code, Device device)
	{
		if (mobile == null || code == null) { return new JSON(false, "登录参数不完整"); }
		Pattern p = Pattern.compile(Constant.MOBILEREG);
		Matcher m = p.matcher(mobile);
		if (!m.find()) { return new JSON(false, "手机号码不规范"); }
		if (!CodeCache.verfiy(mobile, code)) { return new JSON(false, "验证码不正确"); }
		Customer customer = customerService.getByMobile(mobile);
		if (customer == null) { return new JSON(false, "该手机号码尚未注册"); }
		if (!customer.isInuse()) { return new JSON(false, "该账户已停用"); }
		String password = UUID.randomUUID().toString().replace("-", "");
		customer.setPassword(password);
		try
		{
			BeanUtils.copyProperties(device, customer);
			customer.setLogin_date(new Date());
			customerService.saveOrUpdate(customer);
		}
		catch (Exception e)
		{
			return new JSON(false, "对不起,登录失败");
		}
		return new JSON(true, "登录成功").append("mobile", mobile)
				.append("password", password)
				.append("carNo", customer.getCarNo())
				.append("name", customer.getName());
	}

	/**
	 * 查询产品分类与首页的推荐信息
	 */
	@RequestMapping("categoriesandrecommends")
	@ResponseBody
	public JSON categoriesandrecommends()
	{
		return new JSON(true, "查询成功").append("categories",
				CategoryUtil.getCategories()).append("recommends",
				recommendService.findInuse());
	}

}
