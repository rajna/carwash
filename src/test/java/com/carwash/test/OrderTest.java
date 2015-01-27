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
 * File name:          OrderTest.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.test;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.carwash.entity.Customer;
import com.carwash.entity.Order;
import com.carwash.entity.OrderItem;
import com.carwash.entity.OrderStatus;
import com.carwash.entity.Product;
import com.carwash.service.CustomerServiceI;
import com.carwash.service.OrderServiceI;
import com.carwash.service.ProductServiceI;
import com.carwash.util.DateUtil;

/**
 * TODO: File comments
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年12月8日 Time:下午12:47:56
 * <p>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class OrderTest
{
	@Autowired
	private OrderServiceI orderService;
	@Autowired
	private CustomerServiceI customerService;
	@Autowired
	private ProductServiceI productService;

	private static String randomCarNo()
	{
		String c = (char) (new Random().nextInt(26) + 65) + "";
		return "苏" + c + (new Random().nextInt(10))
				+ (new Random().nextInt(10)) + (new Random().nextInt(10))
				+ (new Random().nextInt(10)) + (new Random().nextInt(10));
	}

	@Test
	public void generateOrder()
	{
		List<Customer> customers = customerService.find();
		for (Customer c : customers)
		{
			if (c.getCarNo() == null)
			{
				c.setCarNo(randomCarNo());
			}
		}
		List<Product> products = productService.find();
		for (Customer c : customers)
		{
			// 每个用户创建的订单数
			int orderSize = new Random().nextInt(30) + 20;
			for (int i = 0; i < orderSize; i++)
			{

				Order order = new Order();
				order.setCustomerId(c.getId());
				order.setCarNo(c.getCarNo());
				order.setAddress(c.getMobile() + ":地址" + (i + 1));
				order.setOrderId(UUID.randomUUID().toString().replace("-", "")
						.toLowerCase());
				int sSize = OrderStatus.values().length;
				OrderStatus os = OrderStatus.values()[new Random().nextInt(100)
						% sSize];
				os = OrderStatus.PROCESSING;
				order.setOrderStatus(os);
				order.setReservation_date(DateUtil.randomDate("2015-01-21",
						"2015-02-12", "yyyy-MM-dd"));
				order.setReservationId(1);
				order.setSupportorId(1);
				order.setSupportorName("客服姓名");
				order.setWorkerId(12);
				order.setWorkerName("服务人员姓名");
				int itemSize = new Random().nextInt(3) + 1;
				for (int j = 0; j < itemSize; j++)
				{
					OrderItem oi = new OrderItem();
					oi.setAmount(new Random().nextInt(3) + 1);
					// 随机查询产品
					Product product = products.get(new Random()
							.nextInt(products.size()));
					oi.setCategoryId(product.getCategoryId());
					oi.setDescription(product.getDescription());
					oi.setName(product.getName());
					oi.setImageLink(product.getImageLink());
					oi.setPrice(product.getPrice());
					oi.setProductId(product.getId());
					order.getOrderItems().add(oi);
				}
				orderService.saveOrUpdate(order);
			}
		}
	}
}
