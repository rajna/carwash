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
import com.carwash.interceptor.Cwp;
import com.carwash.interceptor.Interceptor;
import com.carwash.service.ReservationServiceI;
import com.carwash.util.Constant;
import com.carwash.util.JSON;
import com.carwash.util.VoiceUtil;

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

	@Cwp
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
		// TODO校验是否填写车牌
		String message_voice_url = VoiceUtil.saveToDisk(request, voiceFile);
		reservation.setCustomer_id(customer.getId());
		reservation.setCustomer_mobile(customer.getMobile());
		reservation.setCustomer_name(customer.getName());
		reservation.setInuse(true);
		reservation.setMessage_voice_url(message_voice_url);
		String address = reservation.getAddress();
		if (address == null || "".equals(address))
		{
			address = "未填写地址";
			reservation.setAddress(address);
		}
		try
		{
			reservationService.saveOrUpdate(reservation);
			return new JSON(true, "预约成功");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return new JSON(false, "预约失败");
	}
}
