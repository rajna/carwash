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
 * File name:          ApiUser.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.ctrl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carwash.entity.User;
import com.carwash.interceptor.Cwp;
import com.carwash.interceptor.Interceptor;
import com.carwash.service.UserServiceI;
import com.carwash.util.Constant;
import com.carwash.util.JSON;

/**
 * TODO: File comments
 * <p>
 * Author: ilvel
 * <p>
 * Date:2015年1月9日 Time:下午1:46:00
 * <p>
 */
@Controller
@RequestMapping("/api/user")
public class ApiUser
{
	@Autowired
	private UserServiceI userService;

	@Cwp(1)
	@RequestMapping("workers")
	@ResponseBody
	public JSON workers()
	{
		List<User> workers = userService.workers();
		List<User> respUsers = new ArrayList<User>();
		for (User u : workers)
		{
			User respUser = new User();
			BeanUtils.copyProperties(u, respUser);
			respUser.setPassword(null);
			respUsers.add(respUser);
		}
		return new JSON(true, "查询成功").append("workers", respUsers);
	}
}
