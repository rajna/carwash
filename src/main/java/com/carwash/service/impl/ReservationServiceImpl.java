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
 * File name:          ReservationServiceImpl.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carwash.entity.Reservation;
import com.carwash.entity.ReservationStatus;
import com.carwash.service.BaseDaoI;
import com.carwash.service.ReservationServiceI;
import com.carwash.util.Constant;

/**
 * 预约服务接口实现
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年12月8日 Time:下午1:55:56
 * <p>
 */
@Service("reservationService")
public class ReservationServiceImpl implements ReservationServiceI
{
	@Autowired
	private BaseDaoI<Reservation> rDao;

	@Override
	public void saveOrUpdate(Reservation o)
	{
		rDao.saveOrUpdate(o);
	}

	@Override
	public long count(ReservationStatus status)
	{
		if (status == null) { return rDao
				.count("select count(r.id) from Reservation r"); }
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", status);
		return rDao
				.count("select count(r.id) from Reservation r where r.reservationStatus=:status",
						params);
	}

	@Override
	public List<Reservation> find(ReservationStatus status, int pageId)
	{
		int from = pageId * Constant.SIZEPERPAGE;
		if (status == null) { return rDao.find(
				"from Reservation r Order By r.create_date desc", from,
				Constant.SIZEPERPAGE); }
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", status);
		return rDao
				.find("from Reservation r where r.reservationStatus=:status Order By r.create_date desc",
						params, from, Constant.SIZEPERPAGE);
	}

	@Override
	public List<Reservation> findByCid(int cid, ReservationStatus status)
	{
		List<Reservation> reservations = new ArrayList<Reservation>();
		if (cid == 0 || status == null) { return reservations; }
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cid", cid);
		params.put("status", status);
		StringBuffer hql = new StringBuffer(
				"From Reservation r where r.customer_id=:cid and r.reservationStatus=:status Order By r.create_date desc");
		return rDao.find(hql.toString(), params);
	}

}
