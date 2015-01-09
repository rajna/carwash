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
 * File name:          UserServiceImpl.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carwash.entity.Role;
import com.carwash.entity.User;
import com.carwash.service.BaseDaoI;
import com.carwash.service.UserServiceI;

/**
 * 用户的数据操作接口实现
 * <p>
 * Author: ilvelh
 * <p>
 * Date:2014年11月13日 Time:下午11:17:52
 * <p>
 */
@Service("userService")
public class UserServiceImpl implements UserServiceI
{
	@Autowired
	private BaseDaoI<User> userDao;

	@Override
	public void saveOrUpdate(User o)
	{
		userDao.saveOrUpdate(o);
	}

	@Override
	public User get(String mobile)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mobile", mobile);
		return userDao.get("From User u where u.mobile=:mobile", params);
	}

	@Override
	public User get(int id)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return userDao.get("From User u where u.id=:id", params);
	}

	@Override
	public List<User> workers()
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("inuse", true);
		params.put("role", Role.WORKER);
		return userDao.find(
				"From User u where u.inuse=:inuse and u.role=:role ", params);
	}

}
