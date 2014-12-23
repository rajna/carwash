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
 * File name:          GiftCard.java
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
 * 礼品卡
 * <p>
 * Author: ilvelh
 * <p>
 * Date:2014-11-11 Time:下午4:34:03
 * <p>
 */
@Entity
@Table(name = "cw_giftcard")
@org.hibernate.annotations.Table(comment = "礼品卡数据模型", appliesTo = "cw_giftcard")
public class GiftCard {
	private int id;
	private String redeemCode; // 兑换码
	private String name; // 限制:1-20字
	private double price;
	private double customer_discount; // 客户折扣额度
	private double salesman_disconut; // 销售人员折扣额度
	private boolean inuse = true;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRedeemCode() {
		return redeemCode;
	}

	public void setRedeemCode(String redeemCode) {
		this.redeemCode = redeemCode;
	}
	@NotBlank(message = "礼品卡名不能为空")
	@Length(min=1,max=20,message="礼品卡名长度应在{min}-{max}之间")
	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getCustomer_discount() {
		return customer_discount;
	}

	public void setCustomer_discount(double customer_discount) {
		this.customer_discount = customer_discount;
	}

	public double getSalesman_disconut() {
		return salesman_disconut;
	}

	public void setSalesman_disconut(double salesman_disconut) {
		this.salesman_disconut = salesman_disconut;
	}

	public boolean isInuse() {
		return inuse;
	}

	public void setInuse(boolean inuse) {
		this.inuse = inuse;
	}

}
