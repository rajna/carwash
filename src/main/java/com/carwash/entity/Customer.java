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
 * File name:          Customer.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.carwash.util.Constant;

/**
 * 客户数据模型
 * <p>
 * Author: ilvelh
 * <p>
 * Date:2014-11-11 Time:下午3:33:12
 * <p>
 */
@Entity
@Table(name = "cw_customer")
@org.hibernate.annotations.Table(comment = "客户数据模型", appliesTo = "cw_customer")
public class Customer {
	private int id;
	private String name;
	private String mobile; // 手机号码不能重复
	private String password = UUID.randomUUID().toString();
	private Date create_date = new Date(); // 用户注册时间
	private String reffer_work_id; // 推荐人工号
	private double credit; // 用户账户余额
	private boolean inuse = true;

	public Customer() {
		super();
	}

	public Customer(String mobile) {
		super();
		this.mobile = mobile;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank(message = "手机号码不能为空")
	@Pattern(regexp = Constant.MOBILEREG, message = "手机号码不符合规则")
	@Column(unique = true, nullable = false)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getReffer_work_id() {
		return reffer_work_id;
	}

	public void setReffer_work_id(String reffer_work_id) {
		this.reffer_work_id = reffer_work_id;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public boolean isInuse() {
		return inuse;
	}

	public void setInuse(boolean inuse) {
		this.inuse = inuse;
	}

}
