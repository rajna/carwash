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
 * File name:          CategoryUtil.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.category;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * 产品数据工具类
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年11月13日 Time:下午11:06:25
 * <p>
 */
public class CategoryUtil
{
	private static Set<Category> categories = new HashSet<Category>();
	static
	{
		try
		{
			String path = CategoryUtil.class.getClassLoader()
					.getResource("product_category.xml").getFile();
			Document document = new SAXReader().read(path);
			Element rootElement = document.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> elements = rootElement.elements();
			for (Element element : elements)
			{
				@SuppressWarnings("unchecked")
				List<Node> nodes = element.elements();
				if (nodes.size() < 3) { throw new RuntimeException(
						"xml转换错误,节点数据不完整"); }
				String id_string = nodes.get(0).getText();
				int id = 0;
				try
				{
					id = Integer.valueOf(id_string);
				}
				catch (Exception e)
				{
					throw new RuntimeException("xml转换错误," + id_string + "不是整数");
				}
				if (id < 1) { throw new RuntimeException("xml转换错误,id不能小于1,("
						+ id + "小于1)"); }
				String name = nodes.get(1).getText();
				String pic = nodes.get(2).getText();

				if (!categories.add(new Category(id, name, pic))) { throw new RuntimeException(
						"xml转换错误,Id(" + id + ")重复了！"); }
			}
		}
		catch (Exception e)
		{
			throw new RuntimeException(e.getMessage());
		}
	}

	public static Set<Category> getCategories()
	{
		return categories;
	}
}
