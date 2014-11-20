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
 * File name:          GeocoderSearchResponse.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.test;

import java.net.URL;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * 百度反向解析的数据
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年11月21日 Time:上午1:27:53
 * <p>
 */
public class GeocoderSearchResponse {
	private GeocoderSearchResponse() {
	};

	private boolean ok;
	private String message;
	private String address;

	protected void setOk(boolean ok) {
		this.ok = ok;
	}

	protected void setMessage(String message) {
		this.message = message;
	}

	protected void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the status
	 */
	public boolean isOk() {
		return this.ok;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	public static GeocoderSearchResponse init(String xmlFromBaidu) {
		GeocoderSearchResponse gsr = new GeocoderSearchResponse();
		try {
			// Document document = new SAXReader().read(new
			// ByteArrayInputStream(xmlFromBaidu.getBytes("UTF-8")));
			Document document = new SAXReader()
					.read(new URL(
							"http://api.map.baidu.com/geocoder/v2/?ak=aPYf7c5CP9knCkGUINPqIupT&output=xml&location=39.983424,116.322987"));
			System.out.println(document.asXML());
			// Element root = document.getRootElement();
			Node statusNode = document
					.selectSingleNode("/GeocoderSearchResponse/status");
			if (statusNode == null) {
				return null;
			}
			gsr.setOk("0".equals(statusNode.getText()));
			Node messageNode = document
					.selectSingleNode("/GeocoderSearchResponse/message");
			if(messageNode!=null){
				gsr.setMessage(messageNode.getText());
			}
			Node formatted_address = document
					.selectSingleNode("/GeocoderSearchResponse/result/formatted_address");
			System.out.println(formatted_address.getText());
			if(formatted_address!=null){
				gsr.setAddress(formatted_address.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		GeocoderSearchResponse
				.init("<?xml version=\"1.0\" encoding=\"utf-8\" ?> <GeocoderSearchResponse> <status>200</status><message>APP不存在，AK有误请检查再重试</message>  </GeocoderSearchResponse>");
	}
}
