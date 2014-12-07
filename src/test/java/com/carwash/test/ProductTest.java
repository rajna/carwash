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
 * File name:          ProductTest.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.test;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.simpl.RAMJobStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.carwash.category.Category;
import com.carwash.category.CategoryUtil;
import com.carwash.entity.Product;
import com.carwash.service.ProductServiceI;

/**
 * TODO: File comments
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年12月7日 Time:下午9:41:26
 * <p>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class ProductTest {
	@Autowired
	private ProductServiceI productService;

	/**
	 * 新增产品数据至数据库用于测试
	 */
	@Test
	public void generateProduct() {
		// 先查询出分类
		List<Category> categories = CategoryUtil.getCategories();
		for (Category c : categories) {
			for (int i = 0; i < 30; i++) {
				String image = "images/product/1 ("
						+ (new Random().nextInt(102) + 1) + ").jpg";
				Product product = new Product();
				product.setCategoryId(c.getId());
				product.setDescription("产品测试数据");
				product.setImageLink(image);
				product.setInuse(true);
				product.setName(c.getName() + "分类产品" + (i + 1));
				product.setPrice(new Random().nextInt(500) + 40);
				productService.saveOrUpdate(product);
			}
		}
	}
	
	
	@Test
	public void findProduct(){
		int size = productService.find().size();
		System.out.println(size);
	}
}
