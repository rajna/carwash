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
 * File name:          IndexCtrl.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.ctrl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.carwash.category.Category;
import com.carwash.category.CategoryUtil;

/**
 * 首页的controller
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年12月14日 Time:下午3:52:24
 * <p>
 */
@Controller
@RequestMapping("/manager")
public class ManagerCtrl
{
	@RequestMapping("/index")
	public ModelAndView index()
	{
		return new ModelAndView("/manager/index");
	}

	@RequestMapping("/productlist")
	public ModelAndView productlist(HttpServletRequest request)
	{
		List<Category> categories = CategoryUtil.getCategories();
		ModelAndView mv = new ModelAndView("/manager/productlist");
		mv.addObject("categories", categories);
		return mv;
	}

	@RequestMapping("/customerslist")
	public ModelAndView arealist(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("/manager/customerslist");
		return mv;
	}

	@RequestMapping("/reservationlist")
	public ModelAndView reservationlist(HttpServletRequest request)
	{
		List<Category> categories = CategoryUtil.getCategories();
		ModelAndView mv = new ModelAndView("/manager/productlist");
		mv.addObject("categories", categories);
		return mv;
	}
}
