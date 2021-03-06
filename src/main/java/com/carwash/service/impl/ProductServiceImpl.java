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
 * File name:          ProductServiceImpl.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carwash.entity.Product;
import com.carwash.service.BaseDaoI;
import com.carwash.service.ProductServiceI;

/**
 * 产品的服务类
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年12月7日 Time:下午9:34:11
 * <p>
 */
@Service("productService")
public class ProductServiceImpl implements ProductServiceI
{
	@Autowired
	private BaseDaoI<Product> pDao;

	@Override
	public void saveOrUpdate(Product o)
	{
		pDao.saveOrUpdate(o);
	}

	@Override
	public List<Product> find(int categoryId)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("inuse", true);
		params.put("categoryId", categoryId);
		return pDao
				.find("From Product p where p.categoryId=:categoryId and p.inuse=:inuse Order By p.id desc",
						params);
	}

	@Override
	public List<Product> find()
	{
		return pDao.find("From Product p Order By p.id desc");
	}

	@Override
	public List<Product> find(List<Integer> ids)
	{
		StringBuffer ids_string = new StringBuffer("(");
		for (int id : ids)
		{
			ids_string.append(id + ",");
		}
		ids_string.append("0)");
		return pDao.find("From Product p where p.id in " + ids_string);
	}

	@Override
	public Product get(int id)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return pDao.get("From Product p where p.id=:id", params);
	}
}
