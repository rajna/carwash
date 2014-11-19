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
 * File name:          Mail.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.carwash.util.cache.CodeCache;

/**
 * 邮件工具，测试使用
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年11月15日 Time:下午4:30:41
 * <p>
 */
public class Mail
{
	// 发送邮件的服务器的IP和端口
	private static String mailServerHost;
	private static String mailServerPort = "25";
	// 登陆邮件发送服务器的用户名和密码
	private static String userName;
	private static String password;
	private static MimeMessage message = null;
	static
	{
		mailServerHost = ConfigUtil.getValue("mailhost");
		mailServerPort = ConfigUtil.getValue("mailport");
		userName = ConfigUtil.getValue("mailuser");
		password = ConfigUtil.getValue("mailpwd");

		Properties pro = new Properties();
		pro.put("mail.smtp.host", mailServerHost);
		pro.put("mail.smtp.port", mailServerPort);
		pro.put("mail.smtp.auth", "true");
		// 创建一个密码验证器
		MyAuthenticator authenticator = new MyAuthenticator(userName, password);
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session session = Session.getDefaultInstance(pro, authenticator);
		message = new MimeMessage(session);
	}

	/**
	 * 发送邮件
	 * 
	 * @param isHtml
	 *            boolean 是否包含html样式的邮件
	 * @param subject
	 *            String 邮件主题
	 * @param content
	 *            String 邮件内容
	 * @param mails
	 *            String... 邮件发送的地址 可变参数
	 */
	private static void sendMail(boolean isHtml, String subject,
			String content, String... mails)
	{
		// Message message = getMessage();
		if (message == null) { return; }
		try
		{
			// 创建邮件发送者地址
			Address from = new InternetAddress(userName);
			// 设置邮件消息的发送者
			message.setFrom(from);
			int length = mails.length;
			Address[] toAdds = new Address[length];
			// 创建邮件的接收者地址，并设置到邮件消息中
			for (int i = 0; i < length; i++)
			{
				toAdds[i] = new InternetAddress(mails[i]);
			}
			message.setRecipients(MimeMessage.RecipientType.TO, toAdds);
			// 设置邮件消息发送的时间
			message.setSentDate(new Date());
			// 设置邮件消息的主题
			message.setSubject(subject);

			if (isHtml)
			{
				// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
				Multipart mainPart = new MimeMultipart();
				// 创建一个包含HTML内容的MimeBodyPart
				BodyPart html = new MimeBodyPart();
				// 设置HTML内容
				html.setContent(content, Constant.HTMLCONTENTTYPE);
				mainPart.addBodyPart(html);
				// 将MiniMultipart对象设置为邮件内容
				message.setContent(mainPart);
			}
			else
			{
				// 设置邮件消息的主要内容
				message.setText(content);
			}
			// 发送邮件
			Transport.send(message);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void sendCode(String mobile)
	{
		String code = CodeCache.generate(mobile);
		String mail = "carwash2014@126.com";
		if("18900000000".equals(mobile)){
			mail = "1036272678@qq.com";
		}
		sendMail(false, " 验证码:" + code, "已发送到您的手机(" + mobile + ")验证码为:"
				+ code+",请勿回复", mail);
	}

	static class MyAuthenticator extends Authenticator
	{
		String userName = null;
		String password = null;

		public MyAuthenticator()
		{
		}

		public MyAuthenticator(String username, String password)
		{
			this.userName = username;
			this.password = password;
		}

		protected PasswordAuthentication getPasswordAuthentication()
		{
			PasswordAuthentication pa = new PasswordAuthentication(userName,
					password);
			return pa;
		}
	}

}
