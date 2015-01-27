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

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.carwash.category.Category;
import com.carwash.category.CategoryUtil;
import com.carwash.entity.Product;
import com.carwash.interceptor.Cwp;
import com.carwash.service.ProductServiceI;
import com.carwash.util.JSON;
import com.carwash.util.UploadUtil;

/**
 * 产品web接口
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年12月7日 Time:下午10:25:15
 * <p>
 */
@Controller
@RequestMapping("/api/product")
public class ApiProduct
{
	@Autowired
	private ProductServiceI productService;

	/**
	 * 新增產品 用于后台
	 */
	@Cwp(1)
	@RequestMapping(value = "post", method = RequestMethod.POST)
	@ResponseBody
	public JSON post(
			HttpServletRequest request,
			Product product,
			@RequestParam(required = false, value = "image") MultipartFile imageFile)
	{
		try
		{
			String imageLink = UploadUtil.saveProductImageToDisk(request,
					imageFile);
			if (imageLink != null)
			{
				product.setImageLink(imageLink);
			}
			productService.saveOrUpdate(product);
			return new JSON(true, "操作成功");
		}
		catch (Exception e)
		{
			return new JSON(false, "操作失败");
		}

	}

	@Cwp(
	{ 1, 2, 3, 4 })
	@RequestMapping("listforweb")
	@ResponseBody
	public JSON listforweb(String cid)
	{
		return list(cid);
	}

	/**
	 * 通过产品分类cid查询产品列表
	 */
	@RequestMapping("list")
	@ResponseBody
	public JSON list(String cid)
	{
		int id = 0;
		try
		{
			id = Integer.valueOf(cid);
		}
		catch (Exception e)
		{
		}
		List<Product> products = productService.find(id);
		for (Product p : products)
		{
			String cateogoryName = "未知分类";
			Category category = CategoryUtil.getCategory(p.getCategoryId());
			if (category != null)
			{
				cateogoryName = category.getName();
			}
			p.setCategory(cateogoryName);
		}
		return new JSON(true, "查询成功").append("products", products);
	}

}
