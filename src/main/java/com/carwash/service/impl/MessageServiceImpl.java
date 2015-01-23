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
 * File name:          MessageServiceImpl.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carwash.entity.Message;
import com.carwash.service.BaseDaoI;
import com.carwash.service.MessageServiceI;

/**
 * 消息服务接口实现
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年12月8日 Time:上午9:58:44
 * <p>
 */

@Service("messageService")
public class MessageServiceImpl implements MessageServiceI
{

	@Autowired
	private BaseDaoI<Message> mDao;

	@Override
	public void saveOrUpdate(Message message)
	{
		mDao.saveOrUpdate(message);
	}

	@Override
	public void delete(int mid)
	{
		Message message = mDao.get("From Message m where id=" + mid);
		if (message != null)
		{
			mDao.delete(message);
		}
	}

	@Override
	public List<Message> findByCid(int cid)
	{
		return mDao.find("From Message m where m.customerId=" + cid
				+ " Order By m.create_date desc");
	}

	@Override
	public List<Message> findByUid(int uid)
	{
		return mDao.find("From Message m where m.userId=" + uid
				+ " Order By m.create_date desc");
	}

	@Override
	public void readedMessage(int id)
	{
		Message m = get(id);
		if (m != null)
		{
			m.setReaded(true);
			saveOrUpdate(m);
		}
	}

	@Override
	public Message get(int id)
	{
		return mDao.get("From Message m where m.id=" + id);
	}

	@Override
	public void readallByCid(int cid)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("readed", true);
		params.put("customerId", cid);
		mDao.executeHql(
				"Update Message m set m.readed =:readed where m.customerId =:customerId",
				params);
	}

	@Override
	public void readallByUid(int uid)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("readed", true);
		params.put("userId", uid);
		mDao.executeHql(
				"Update Message m set m.readed =:readed where m.userId =:userId",
				params);
	}

}
