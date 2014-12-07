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
 * File name:          ApiProduct.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carwash.entity.Product;
import com.carwash.service.ProductServiceI;
import com.carwash.util.JSON;

/**
 * TODO: File comments
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年12月7日 Time:下午10:25:15
 * <p>
 */
@Controller
@RequestMapping("/api/product")
public class ApiProduct {
	@Autowired
	private ProductServiceI productService;

	/**
	 * 通过产品分类cid查询产品列表
	 */
	@RequestMapping("list")
	@ResponseBody
	public JSON list(String cid) {
		int id = 0;
		try {
			id = Integer.valueOf(cid);
		} catch (Exception e) {
		}
		List<Product> products = productService.find(id);
		return new JSON(true, "查询成功").append("products", products);
	}

}
