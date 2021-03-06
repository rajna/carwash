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
 * File name:          ReservationServiceI.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.service;

import java.util.List;

import com.carwash.entity.Reservation;
import com.carwash.entity.ReservationStatus;

/**
 * 预约服务接口
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年12月8日 Time:下午1:53:49
 * <p>
 */
public interface ReservationServiceI
{
	public void saveOrUpdate(Reservation o);

	public long count(ReservationStatus status);

	/**
	 * 查询预约
	 * 
	 * @return
	 */
	public List<Reservation> find(ReservationStatus status, int pageId);

	/**
	 * 查询预约
	 * 
	 * @param cid
	 *            客户编号
	 * @param rs
	 *            状态
	 */
	public List<Reservation> findByCid(int cid, ReservationStatus status);
	
	public Reservation get(int id);
}
