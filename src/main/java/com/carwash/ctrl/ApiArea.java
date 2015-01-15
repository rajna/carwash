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
 * File name:          ApiArea.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carwash.entity.Area;
import com.carwash.interceptor.Cwp;
import com.carwash.service.AreaServiceI;
import com.carwash.util.JSON;

/**
 * 地区接口
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年12月19日 Time:下午2:32:28
 * <p>
 */
@Controller
@RequestMapping("/api/area")
public class ApiArea
{
	@Autowired
	private AreaServiceI areaService;

	/**
	 * 查询区域列表，用于后台界面
	 * 
	 * @return
	 */
	@RequestMapping("list")
	@ResponseBody
	public JSON list()
	{
		return new JSON(true, "查询成功").append("areas", areaService.find());
	}

	/**
	 * 新增或修改区域，用于后台界面
	 * 
	 * @return
	 */
	@Cwp(1)
	@RequestMapping("post")
	@ResponseBody
	public JSON post(Area area)
	{
		if (area.getName() == null || "".equals(area.getName())) { return new JSON(
				false, "区域名称不能为空"); }
		try
		{
			areaService.saveOrUpdate(area);
			return new JSON(true, "操作成功");
		}
		catch (Exception e)
		{
			return new JSON(false, "操作失败," + e.getMessage());
		}

	}
}
