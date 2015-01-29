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
 * File name:          Order.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.carwash.util.OrderNumberUtil;

/**
 * 订单数据结构
 * <p>
 * Author: ilvelh
 * <p>
 * Date:2014-11-11 Time:下午3:25:44
 * <p>
 */
@Entity
@Table(name = "cw_order")
@org.hibernate.annotations.Table(comment = "订单数据结构模型", appliesTo = "cw_order")
public class Order
{
	private int id;
	private int customerId; // 客户编号
	private int reservationId; // 预约编号
	private String carNo;
	private String orderId = OrderNumberUtil.getOrderNumber();
	private Date create_date = new Date();
	private String address; // 服务地址 限制
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();
	private Date reservation_date; // 预约服务时间
	private int workerId; // 服务人员编号
	private int supportorId; // 客服人员编号
	private String workerName; // 服务人员名字
	private String supportorName; // 客服人员名字
	private OrderStatus orderStatus = OrderStatus.PROCESSING; // 订单状态
	private Date complete_date; // 订单完结时间

	@Id
	@GeneratedValue
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getCarNo()
	{
		return carNo;
	}

	public void setCarNo(String carNo)
	{
		this.carNo = carNo;
	}

	public int getReservationId()
	{
		return reservationId;
	}

	public void setReservationId(int reservationId)
	{
		this.reservationId = reservationId;
	}

	public String getOrderId()
	{
		return orderId;
	}

	public void setOrderId(String orderId)
	{
		this.orderId = orderId;
	}

	public Date getCreate_date()
	{
		return create_date;
	}

	public void setCreate_date(Date create_date)
	{
		this.create_date = create_date;
	}

	@NotBlank(message = "订单地址称不能为空")
	@Length(min = 1, max = 50, message = "订单地址长度应在{min}-{max}之间")
	@Column(nullable = false)
	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "oid")
	public Set<OrderItem> getOrderItems()
	{
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems)
	{
		this.orderItems = orderItems;
	}

	public Date getReservation_date()
	{
		return reservation_date;
	}

	public void setReservation_date(Date reservation_date)
	{
		this.reservation_date = reservation_date;
	}

	public int getWorkerId()
	{
		return workerId;
	}

	public void setWorkerId(int workerId)
	{
		this.workerId = workerId;
	}

	public int getSupportorId()
	{
		return supportorId;
	}

	public void setSupportorId(int supportorId)
	{
		this.supportorId = supportorId;
	}

	public String getWorkerName()
	{
		return workerName;
	}

	public void setWorkerName(String workerName)
	{
		this.workerName = workerName;
	}

	public String getSupportorName()
	{
		return supportorName;
	}

	public void setSupportorName(String supportorName)
	{
		this.supportorName = supportorName;
	}

	public OrderStatus getOrderStatus()
	{
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus)
	{
		this.orderStatus = orderStatus;
		if (orderStatus.equals(OrderStatus.COMPLETED))
		{
			setComplete_date(new Date());
		}
	}

	public Date getComplete_date()
	{
		return complete_date;
	}

	public void setComplete_date(Date complete_date)
	{
		this.complete_date = complete_date;
	}

	public int getCustomerId()
	{
		return customerId;
	}

	public void setCustomerId(int customerId)
	{
		this.customerId = customerId;
	}

}
