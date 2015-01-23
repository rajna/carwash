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
 * File name:          MessageTest.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.carwash.entity.Customer;
import com.carwash.entity.Message;
import com.carwash.entity.User;
import com.carwash.service.CustomerServiceI;
import com.carwash.service.MessageServiceI;
import com.carwash.service.UserServiceI;
import com.carwash.util.DateUtil;

/**
 * TODO: File comments
 * <p>
 * Author: ilvel
 * <p>
 * Date:2015年1月23日 Time:上午11:54:25
 * <p>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class MessageTest
{
	@Autowired
	private MessageServiceI messageService;
	@Autowired
	private UserServiceI userService;
	@Autowired
	private CustomerServiceI customerService;

	@Test
	public void testAdd()
	{
		List<User> users = userService.find();
		for (User u : users)
		{
			for (int i = 0; i < 1000; i++)
			{
				Date date = DateUtil.randomDate("2013-01-01 00:00:00",
						"2015-01-22 23:59:59", "yyyy-MM-dd HH:mm:ss");
				Message message = new Message();
				message.setUserId(u.getId());
				message.setContent(i + "这是发送给用户的系统消息。。。。。。。。");
				message.setCreate_date(date);
				message.setReaded(false);
				messageService.saveOrUpdate(message);
			}
		}
		List<Customer> customers = customerService.find();
		for (Customer c : customers)
		{
			for (int i = 0; i < 1000; i++)
			{
				Date date = DateUtil.randomDate("2013-01-01 00:00:00",
						"2015-01-22 23:59:59", "yyyy-MM-dd HH:mm:ss");
				Message message = new Message();
				message.setCustomerId(c.getId());
				message.setContent(i + "这是发送给客户的系统消息。。。。。。。。");
				message.setCreate_date(date);
				message.setReaded(false);
				messageService.saveOrUpdate(message);
			}
		}
	}
}
