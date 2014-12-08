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
 * File name:          Recommend.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 手机端界面推荐图片与其他信息
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年11月25日 Time:上午11:46:22
 * <p>
 */
@Entity
@Table(name = "cw_recommend")
@org.hibernate.annotations.Table(comment = "手机端界面推荐图片与其他信息", appliesTo = "cw_recommend")
public class Recommend {
	private int id;
	// 推荐图片的相对地址
	private String pic;
	// 手机端跳转的weburl
	private String url;
	// 产品编号,用于手机端跳转使用
	private int pid;
	private boolean inuse = true;

	public Recommend(String pic, String url, int pid, boolean inuse) {
		super();
		this.pic = pic;
		this.url = url;
		this.pid = pid;
		this.inuse = inuse;
	}

	public Recommend(int id, String pic, String url, int pid, boolean inuse) {
		super();
		this.id = id;
		this.pic = pic;
		this.url = url;
		this.pid = pid;
		this.inuse = inuse;
	}

	public Recommend() {
		super();
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public boolean isInuse() {
		return inuse;
	}

	public void setInuse(boolean inuse) {
		this.inuse = inuse;
	}

}
