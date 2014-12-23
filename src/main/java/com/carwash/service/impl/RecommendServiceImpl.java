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
 * File name:          RecommendServiceImpl.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carwash.entity.Recommend;
import com.carwash.service.BaseDaoI;
import com.carwash.service.RecommendServiceI;

/**
 * 客户端推荐图片服务接口实现类
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年12月8日 Time:下午8:30:30
 * <p>
 */
@Service("recommendService")
public class RecommendServiceImpl implements RecommendServiceI {

	@Autowired
	private BaseDaoI<Recommend> rDao;

	@Override
	public void saveOrUpdate(Recommend o) {
		rDao.saveOrUpdate(o);
	}

	@Override
	public List<Recommend> find() {
		return rDao.find("From Recommend r");
	}

	@Override
	public List<Recommend> findInuse() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("inuse", true);
		return rDao.find("From Recommend r where r.inuse=:inuse", params);
	}

}
