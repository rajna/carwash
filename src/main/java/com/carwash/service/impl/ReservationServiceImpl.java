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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carwash.entity.Reservation;
import com.carwash.service.BaseDaoI;
import com.carwash.service.ReservationServiceI;

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
	public List<Reservation> find()
	{
		return null;
	}

}
