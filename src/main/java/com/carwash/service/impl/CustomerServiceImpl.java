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
 * File name:          CustomerServiceImpl.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carwash.entity.Customer;
import com.carwash.service.BaseDaoI;
import com.carwash.service.CustomerServiceI;

/**
 * 客户的数据操作接口实现
 * <p>
 * Author: ilvelh
 * <p>
 * Date:2014年11月15日 Time:下午1:51:55
 * <p>
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerServiceI {
	@Autowired
	private BaseDaoI<Customer> customerDao; 

	@Override
	public void saveOrUpdate(Customer o) {
		customerDao.saveOrUpdate(o);
	}

	@Override
	public Customer getByMobile(String mobile) {
		if (mobile == null) {
			return null;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mobile", mobile);
		return customerDao
				.get("From Customer c where c.mobile=:mobile", params);
	}

}
