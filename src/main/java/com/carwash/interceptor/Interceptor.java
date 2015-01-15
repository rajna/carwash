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
 * File name:          Interceptor.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.carwash.ctrl.CustomerCache;
import com.carwash.entity.Customer;
import com.carwash.entity.Role;
import com.carwash.entity.User;
import com.carwash.service.CustomerServiceI;
import com.carwash.util.Constant;
import com.carwash.util.JSON;

/**
 * spring mvc 拦截器
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年11月18日 Time:下午4:54:53
 * <p>
 */
public class Interceptor implements HandlerInterceptor
{
	private CustomerServiceI customerService;
	public final static ThreadLocal<Customer> threadLocalCustomer = new ThreadLocal<Customer>();
	public final static ThreadLocal<User> threadLocalUser = new ThreadLocal<User>();

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj) throws Exception
	{
		response.setContentType(Constant.HTMLCONTENTTYPE);
		request.setCharacterEncoding(Constant.UTF8);
		response.setCharacterEncoding(Constant.UTF8);
		HandlerMethod handler = (HandlerMethod) obj;
		Cwp cwp = handler.getMethodAnnotation(Cwp.class);
		if (cwp == null || cwp.value().length == 0) { return true; }
		int[] values = cwp.value();
		// 校验访问的方法是否是客户端api调用
		boolean isFromApi = false;
		for (int v : values)
		{
			if (v == 0)
			{
				isFromApi = true;
				break;
			}
		}
		if (isFromApi)
		{
			// 来自客户端的访问
			Object mObj = request.getParameter("mobile");
			Object pObj = request.getParameter("password");
			if (mObj == null || pObj == null)
			{
				response.getWriter().write(
						new JSON(false, Constant.ACCOUNTERROR).append(
								"relogin", true).toJSONString());
				return false;
			}
			Customer customer = CustomerCache.get(mObj.toString());
			if (customer == null)
			{
				// 缓存中未取到用户的话直接从数据库中查询
				customer = customerService.getByMobile(mObj.toString());
				CustomerCache.put(customer);
			}
			if (customer == null
					|| !pObj.toString().equals(customer.getPassword()))
			{
				response.getWriter().write(
						new JSON(false, Constant.ACCOUNTERROR).append(
								"relogin", true).toJSONString());
				return false;
			}
			threadLocalCustomer.set(customer);
			return true;
		}
		// 来是网页版访问
		User user = (User) request.getSession().getAttribute("loginuser");
		if (user == null)
		{
			response.getWriter().write(
					new JSON(false, Constant.UNLOGIN).append("relogin", true)
							.toJSONString());
			return false;
		}
		Role role = user.getRole();
		if (role == null)
		{
			response.getWriter().write(
					new JSON(false, Constant.PERMISSIONDENIED).toJSONString());
			return false;
		}
		// 由于cwp里规定权限里包含了0,0代表客户端，所以需要加1开始计算
		int myValue = role.ordinal() + 1;
		for (int v : values)
		{
			if (v == myValue)
			{
				threadLocalUser.set(user);
				return true;
			}
		}
		response.getWriter().write(
				new JSON(false, Constant.PERMISSIONDENIED).toJSONString());
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception
	{

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception
	{

	}

	public CustomerServiceI getCustomerService()
	{
		return customerService;
	}

	public void setCustomerService(CustomerServiceI customerService)
	{
		this.customerService = customerService;
	}

}
