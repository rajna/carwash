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

import com.carwash.util.cache.CodeCache;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * 短信发送平台
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年12月12日 Time:下午5:45:09
 * <p>
 */
public class PhoneMessage {

	private static Client client = Client.create();

	private static boolean send(final String mobile, final String content) {
		System.out.println(content);
		// 发送邮件
		String mail = "carwash2014@126.com";
		if ("18900000000".equals(mobile)) {
			mail = "1036272678@qq.com";
		}
		Mail.sendMail(false, content, content, mail);
		// 结束
		if (mobile == null || content == null || content.trim().equals("")) {
			return false;
		}
		Pattern p = Pattern.compile(Constant.MOBILEREG);
		Matcher m = p.matcher(mobile);
		if (!m.find()) {
			return false;
		}
		// 测试阶段使用
		if (Constant.isDebug) {
			return Constant.isDebug;
		}
		WebResource wr = client
				.resource("http://smsapi.c123.cn/OpenPlatform/OpenApi");
		try {
			String request = wr.queryParam("action", "sendOnce")
					.queryParam("ac", "1001@501025490001")
					.queryParam("authkey", "4750F5014042D4FBB30C0DF1CBB66405")
					.queryParam("cgid", "52").queryParam("c", content)
					.queryParam("m", mobile).get(String.class);
			return request.contains("result=\"1\"");
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean sendLoginMessage(String mobile) {
		String code = CodeCache.generate(mobile);
		if (code == null || code.length() != 4) {
			return false;
		}
		String content = "尊敬的客户，您的登录验证码为:" + code + ", 如非本人操作请忽略!";
		return send(mobile, content);
	}

	public static boolean sendConfirmOrderMessage(String mobile) {
		String code = CodeCache.generate(mobile);
		if (code == null || code.length() != 4) {
			return false;
		}
		String content = "尊敬的客户，您的订单修改验证码为:" + code + ", 如非本人操作请忽略!";
		return send(mobile, content);
	}

	public static void main(String[] args) {
		sendConfirmOrderMessage("13057333810");
	}
}
