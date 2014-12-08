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

import java.util.List;

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
	public List<Message> find(int cid)
	{
		return mDao.find("From Message m where m.customerId=" + cid);
	}

}
