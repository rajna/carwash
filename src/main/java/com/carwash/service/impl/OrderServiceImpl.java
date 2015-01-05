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
 * File name:          OrderServiceImpl.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carwash.entity.Order;
import com.carwash.entity.OrderStatus;
import com.carwash.service.BaseDaoI;
import com.carwash.service.OrderServiceI;

/**
 * 订单服务接口实现
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年12月8日 Time:上午10:13:53
 * <p>
 */
@Service("orderService")
public class OrderServiceImpl implements OrderServiceI
{
	@Autowired
	private BaseDaoI<Order> oDao;

	@Override
	public void saveOrUpdate(Order order)
	{
		oDao.saveOrUpdate(order);
	}

	@Override
	public List<Order> findByCid(int cid, OrderStatus status)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cid", cid);
		StringBuffer hql = new StringBuffer(
				"From Order o where o.customerId=:cid ");
		if (status != null)
		{
			params.put("status", status);
			hql.append(" and o.orderStatus=:status ");
		}
		hql.append(" Order By o.create_date desc");
		return oDao.find(hql.toString(), params);
	}

	@Override
	public List<Order> findByRid(int rid)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rid", rid);
		return oDao.find(
				" From Order o where o.reservationId=:rid ORDER BY o.id DESC",
				params);
	}

	@Override
	public Order get(int id)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return oDao.get(" From Order o where o.id=:id", params);
	}

}
