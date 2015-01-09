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
 * File name:          AreaTest.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.carwash.entity.Area;
import com.carwash.service.AreaServiceI;

/**
 * TODO: File comments
 * <p>
 * Author: ilvel
 * <p>
 * Date:2015年1月9日 Time:下午1:58:05
 * <p>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class AreaTest
{
	@Autowired
	private AreaServiceI areaService;
	String[] names = new String[]
	{ "逸景园", "迎龙阁", "阳光雅居", "优族联盟", "银仁花园", "益明花园小区", "映山华庭", "益明金桂苑",
			"阳光城市花园", "永丰大厦", "杨木桥小区", "印象剑桥", "颐景花园 ", "逸景国际", "隐秀苑住宅小区",
			"优.空间", "银河广场", "一栋阳房", "樱花半岛", "月湖山庄", "瑜憬湾", "怡和家园", "育才公寓",
			"扬名新村", "益都苑", "迎溪桥小区", "扬名花园", "月秀花园", "羊腰湾小区", "永泰新村", "银仁御墅花园",
			"咏硕苑", "银河湾花园", "阳光嘉园", "育才苑", "燕尾山庄" };

	@Test
	public void addArea()
	{
		for (String name : names)
		{
			Area area = new Area();
			area.setName(name);
			area.setCenterX(0);
			area.setCenterY(0);
			area.setRadius(20);
			areaService.saveOrUpdate(area);
		}
	}
}
