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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.carwash.entity.Customer;
import com.carwash.service.CustomerServiceI;
import com.carwash.util.Constant;
import com.carwash.util.Mail;
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
@RequestMapping("api")
public class Api {
	@Autowired
	private CustomerServiceI customerService;
	
	/**
	 * 客户通过手机端获取验证码
	 */
	@RequestMapping("customercode")
	@ResponseBody
	public JSONObject customercode(String mobile) {
		JSONObject json = new JSONObject();
		Pattern p = Pattern.compile(Constant.MOBILEREG);
		Matcher m = p.matcher(mobile);
		if (!m.find()) {
			json.put("success", false);
			json.put("message", "手机号码不规范");
			return json;
		}
		Customer customer = customerService.getByMobile(mobile);
		if (customer == null) {
			customer = new Customer(mobile);
			try {
				customerService.saveOrUpdate(customer);
			} catch (Exception e) {
				json.put("success", false);
				json.put("message", "发送验证码失败");
				return json;
			}
		}
		Mail.sendCode(mobile, CodeCache.generate(mobile));
		json.put("success", true);
		json.put("message", "发送验证码成功");
		json.put("leftTime", CodeCache.leftTime);
		return json;
	}

	@RequestMapping("customerlogin")
	@ResponseBody
	public JSONObject customerLogin(String mobile, String code) {
		return null;
	}
}
