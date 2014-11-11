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
 * File name:          Area.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 区域模型
 * <p>
 * Author: ilvelh
 * <p>
 * Date:2014-11-11 Time:下午4:05:58
 * <p>
 */
@Entity
@Table(name = "cw_area")
@org.hibernate.annotations.Table(comment = "区域模型", appliesTo = "cw_area")
public class Area {
	private int id;
	private String name;
	private double centerX;
	private double centerY;
	private double radius; // 半径 单位:米
	private String description; // 区域描述
	private boolean inuse = true;

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotBlank(message = "区域名不能为空")
	@Length(min=1,max=20,message="区域名长度应在{min}-{max}之间")
	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Column(columnDefinition = "text")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isInuse() {
		return inuse;
	}

	public void setInuse(boolean inuse) {
		this.inuse = inuse;
	}

}
