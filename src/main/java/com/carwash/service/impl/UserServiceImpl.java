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

import org.springframework.beans.factory.annotation.Autowired;

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
public class UserServiceImpl implements UserServiceI {
	@Autowired
	private BaseDaoI<User> userDao;

	@Override
	public void saveOrUpdate(User o) {
		userDao.saveOrUpdate(o);
	}

}
