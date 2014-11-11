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
 * File name:          PayRecord.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 消费或充值记录
 * <p>
 * Author: ilvelh
 * <p>
 * Date:2014-11-11 Time:下午4:47:53
 * <p>
 */
@Entity
@Table(name = "cw_payrecord")
@org.hibernate.annotations.Table(comment = "消费或充值记录模型", appliesTo = "cw_payrecord")
public class PayRecord {
	private int id;
	private PayConsume payConsume; // 充值或消费类型 不能为空
	private Date create_date = new Date();
	private String userName;
	private int userId;
	private String customerMobile;
	private int customerId;
	private double money; // 用户实际支付金额
	private double add_credit; // 用户实得账户金额
	private double cash; // 用户现金支付金额
	private double minus_credit;// 用户实得金额

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public PayConsume getPayConsume() {
		return payConsume;
	}

	public void setPayConsume(PayConsume payConsume) {
		this.payConsume = payConsume;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public double getAdd_credit() {
		return add_credit;
	}

	public void setAdd_credit(double add_credit) {
		this.add_credit = add_credit;
	}

	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public double getMinus_credit() {
		return minus_credit;
	}

	public void setMinus_credit(double minus_credit) {
		this.minus_credit = minus_credit;
	}

}
