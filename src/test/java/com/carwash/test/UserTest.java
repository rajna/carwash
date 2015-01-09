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
 * File name:          UserTest.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.test;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.carwash.entity.Area;
import com.carwash.entity.Role;
import com.carwash.entity.User;
import com.carwash.service.AreaServiceI;
import com.carwash.service.UserServiceI;

/**
 * TODO: File comments
 * <p>
 * Author: ilvel
 * <p>
 * Date:2015年1月9日 Time:下午1:55:18
 * <p>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class UserTest
{
	@Autowired
	private UserServiceI userService;
	@Autowired
	private AreaServiceI areaService;

	@Test
	public void addUser()
	{
		List<Area> areas = areaService.find();
		Role[] roles = Role.values();
		for (int i = 0; i < 100; i++)
		{
			Role role = roles[i % roles.length];
			User user = new User();
			user.setAreaId(areas.get(i % areas.size()).getId());
			user.setName("gw" + i);
			user.setPassword("123456");
			user.setCreate_date(new Date());
			user.setMobile((14555555555L - i) + "");
			user.setRole(role);
			user.setWorkId(UUID.randomUUID().toString().replace("-", "")
					.substring(0, 10));
			userService.saveOrUpdate(user);
		}
	}
}
