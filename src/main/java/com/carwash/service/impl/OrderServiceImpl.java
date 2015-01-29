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

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carwash.entity.Order;
import com.carwash.entity.OrderStatus;
import com.carwash.service.BaseDaoI;
import com.carwash.service.OrderServiceI;
import com.carwash.util.DateUtil;

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

	@Override
	public List<Order> findByUid(int uid, int type)
	{
		StringBuffer hql = new StringBuffer(
				"From Order o where o.workerId=:workerId and o.orderStatus=:orderStatus ");
		Calendar c_now = Calendar.getInstance();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("workerId", uid);
		params.put("orderStatus", OrderStatus.PROCESSING);
		if (type != 1 && type != 0)
		{
			type = 0;
		}
		if (type == 0)
		{
			c_now.add(Calendar.DATE, -7);
			Date start = c_now.getTime();
			start = DateUtil.todayStart(start);
			Date end = new Date();
			end = DateUtil.todayEnd(end);
			params.put("start", start);
			params.put("end", end);
			hql.append(" and o.reservation_date >=:start and o.reservation_date <=:end ");
		}
		else
		{
			c_now.add(Calendar.DATE, +1);
			Date start = c_now.getTime();
			start = DateUtil.todayStart(start);
			params.put("start", start);
			hql.append(" and o.reservation_date >=:start ");
		}
		hql.append(" ORDER BY o.reservation_date DESC");
		return oDao.find(hql.toString(), params);
	}

	@Override
	public Order get(String orderId)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderId", orderId);
		return oDao.get("From Order o where o.orderId=:orderId", params);
	}
}
