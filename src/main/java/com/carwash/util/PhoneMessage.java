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
 * File name:          PhoneMessage.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import com.carwash.util.cache.CodeCache;

/**
 * 短信发送平台
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年12月12日 Time:下午5:45:09
 * <p>
 */
public class PhoneMessage
{

	private static boolean send(String mobile, String content)
	{
		if (mobile == null || content == null || content.trim().equals("")) { return false; }
		Pattern p = Pattern.compile(Constant.MOBILEREG);
		Matcher m = p.matcher(mobile);
		if (!m.find()) { return false; }
		// 测试阶段使用
		if (!("18601595393".equals(mobile) || "13057333810".equals(mobile))) { return false; }
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://smsapi.c123.cn/OpenPlatform");
		try
		{
			String request = target.path("OpenApi")
					.queryParam("action", "sendOnce")
					.queryParam("ac", "1001@501025490001")
					.queryParam("authkey", "4750F5014042D4FBB30C0DF1CBB66405")
					.queryParam("cgid", "52").queryParam("c", content)
					.queryParam("m", mobile).request().get(String.class);
			return request.contains("result=\"1\"");
		}
		catch (Exception e)
		{
		}
		return false;
	}

	public static boolean sendLoginMessage(String mobile)
	{
		String code = CodeCache.generate(mobile);
		if (code == null || code.length() != 4) { return false; }
		String content = "尊敬的客户，您的登录验证码为:" + code + ", 如非本人操作请忽略!";
		return send(mobile, content);
	}

	public static boolean sendConfirmOrderMessage(String mobile)
	{
		String code = CodeCache.generate(mobile);
		if (code == null || code.length() != 4) { return false; }
		String content = "尊敬的客户，您的订单修改验证码为:" + code + ", 如非本人操作请忽略!";
		return send(mobile, content);
	}
}
