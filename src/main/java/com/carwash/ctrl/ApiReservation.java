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
 * File name:          ApiReservation.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.ctrl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.carwash.entity.Customer;
import com.carwash.entity.Reservation;
import com.carwash.entity.ReservationStatus;
import com.carwash.interceptor.Cwp;
import com.carwash.interceptor.Interceptor;
import com.carwash.service.CustomerServiceI;
import com.carwash.service.ReservationServiceI;
import com.carwash.util.Constant;
import com.carwash.util.JSON;
import com.carwash.util.UploadUtil;

/**
 * 预约服务web接口
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年12月8日 Time:下午1:53:32
 * <p>
 */
@Controller
@RequestMapping("/api/reservation")
public class ApiReservation
{
	@Autowired
	private ReservationServiceI reservationService;
	@Autowired
	private CustomerServiceI customerService;

	/**
	 * 客户创建预约
	 */
	@Cwp(0)
	@RequestMapping(value = "post", method = RequestMethod.POST)
	@ResponseBody
	public JSON post(
			HttpServletRequest request,
			Reservation reservation,
			@RequestParam(required = false, value = "voice") MultipartFile voiceFile)
	{
		Customer customer = Interceptor.threadLocalCustomer.get();
		if (customer == null) { return new JSON(false, Constant.ACCOUNTERROR)
				.append("relogin", true); }
		String message_voice_url = UploadUtil.saveVoiceToDisk(request,
				voiceFile);
		reservation.setMessage_voice_url(message_voice_url);
		reservation.setCustomer_id(customer.getId());
		reservation.setCustomer_mobile(customer.getMobile());
		reservation.setCustomer_name(customer.getName());
		reservation.setReservationStatus(ReservationStatus.PROCESSING);
		String address = reservation.getAddress();
		if (address == null || "".equals(address.trim()))
		{
			address = "未填写地址";
			reservation.setAddress(address);
		}
		try
		{
			String carNo = reservation.getCarNo();
			boolean carNoIsChange = carNo != null
					&& !carNo.equals(customer.getCarNo());
			if (carNoIsChange)
			{
				customer.setCarNo(carNo);
				customerService.saveOrUpdate(customer);
			}
			reservationService.saveOrUpdate(reservation);
			return new JSON(true, "预约成功");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return new JSON(false, "预约失败");
	}

	/**
	 * 客户端查询预约接口
	 */
	@Cwp(0)
	@RequestMapping("list")
	@ResponseBody
	public JSON list(String status)
	{
		Customer customer = Interceptor.threadLocalCustomer.get();
		if (customer == null) { return new JSON(false, Constant.ACCOUNTERROR)
				.append("relogin", true); }
		ReservationStatus os = null;
		if (status != null)
		{
			status = status.toUpperCase().trim();
			try
			{
				os = ReservationStatus.valueOf(status);
			}
			catch (Exception e)
			{
			}
		}
		return new JSON(true, "查询成功").append("reservations",
				reservationService.findByCid(customer.getId(), os));
	}

	/**
	 * 后台查询接口
	 */
	// @Cwp(1)
	// 如果
	@RequestMapping("all")
	@ResponseBody
	public JSON all(String status, String pid)
	{
		// TODO 校验登录
		ReservationStatus os = null;
		if (status != null)
		{
			status = status.toUpperCase().trim();
			try
			{
				os = ReservationStatus.valueOf(status);
			}
			catch (Exception e)
			{
			}
		}
		int pageId = 0;
		try
		{
			pageId = Integer.valueOf(pid);
		}
		catch (Exception e)
		{
		}
		long total = reservationService.count(os);
		long temp = total % Constant.SIZEPERPAGE;
		long pages = total / Constant.SIZEPERPAGE;
		pages = (temp == 0) ? pages : (pages + 1);
		return new JSON(true, "查询成功").append("reservations",
				reservationService.find(os, pageId)).append("pages", pages);
	}

	/**
	 * 取消预约
	 */
	@Cwp(0)
	@RequestMapping("cancle")
	@ResponseBody
	public JSON cancle(String rid)
	{
		Customer customer = Interceptor.threadLocalCustomer.get();
		if (customer == null) { return new JSON(false, Constant.ACCOUNTERROR)
				.append("relogin", true); }
		int id = 0;
		try
		{
			id = Integer.valueOf(rid);
		}
		catch (Exception e)
		{
		}
		if (id == 0) { return new JSON(false, "预约编号不存在"); }
		Reservation reservation = reservationService.get(id);
		if (reservation == null) { return new JSON(false, "该预约不存在"); }
		if (reservation.getCustomer_id() != customer.getId()) { return new JSON(
				false, "您无权取消该预约"); }
		reservation.setReservationStatus(ReservationStatus.CANCELED);
		try
		{
			reservationService.saveOrUpdate(reservation);
		}
		catch (Exception e)
		{
			return new JSON(false, "预约取消失败");
		}
		return new JSON(true, "预约已取消");
	}

}
