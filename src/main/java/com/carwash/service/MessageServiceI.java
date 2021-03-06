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
 * File name:          MessageServiceI.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.service;

import java.util.List;

import com.carwash.entity.Message;

/**
 * 客户服务消息接口
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年12月8日 Time:上午9:55:39
 * <p>
 */
public interface MessageServiceI
{
	public void saveOrUpdate(Message message);

	/**
	 * 删除消息
	 * 
	 * @param mid
	 *            消息id
	 */
	public void delete(int mid);

	/**
	 * 查询客户消息
	 * 
	 * @param cid
	 *            客户id
	 */
	public List<Message> findByCid(int cid);

	/**
	 * 查询用户消息
	 * 
	 * @param cid
	 *            用户id
	 */
	public List<Message> findByUid(int uid);

	/**
	 * 设置信息已读状态
	 * 
	 * @param id
	 */
	public void readedMessage(int id);

	/**
	 * 设置客户所有信息已读状态
	 * 
	 * @param id
	 */
	public void readallByCid(int cid);

	/**
	 * 设置用户所有信息已读状态
	 * 
	 * @param id
	 */
	public void readallByUid(int uid);

	/**
	 * 根据信息id查询信息
	 * 
	 * @param id
	 */
	public Message get(int id);
}
