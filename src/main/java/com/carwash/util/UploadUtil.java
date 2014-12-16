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
 * File name:          UploadUtil.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 音频保存到服务器
 * <p>
 * Author: ilvel
 * <p>
 * Date:2014年12月8日 Time:下午2:16:26
 * <p>
 */
public class UploadUtil
{
	/**
	 * 获取资源服务器基本路径
	 * 
	 * @param request
	 * @return
	 */
	private static File getResource(HttpServletRequest request)
	{
		String projectDir = request.getSession().getServletContext()
				.getRealPath("/");
		File resourceDir = new File(new File(projectDir).getParent()
				+ Constant.BASESOURCES);
		if (!resourceDir.exists())
		{
			resourceDir.mkdirs();
		}
		return resourceDir;
	}

	/**
	 * 上传产品图片到本地
	 */
	public static String saveProductImageToDisk(HttpServletRequest request,
			MultipartFile imageFile)
	{
		String imageName = null;
		if (request == null || imageFile == null) { return null; }
		try
		{
			InputStream is = imageFile.getInputStream();
			if (is.available() == 0)
			{
				is.close();
				return null;
			}
			File imageDir = new File(getResource(request),
					Constant.IMAGESSOURCES + "product");
			if (!imageDir.exists())
			{
				imageDir.mkdirs();
			}
			String extension = FilenameUtils.getExtension(imageFile
					.getOriginalFilename());
			imageName = System.currentTimeMillis() + "." + extension;
			File file = new File(imageDir, imageName);
			FileOutputStream fos = new FileOutputStream(file);
			byte[] bytes = new byte[1024];
			@SuppressWarnings("unused")
			int len;
			while ((len = is.read(bytes)) != -1)
			{
				fos.write(bytes);
			}
			fos.close();
			is.close();
		}
		catch (Exception e)
		{
			return null;
		}
		return Constant.IMAGESSOURCES + "product/" + imageName;
	}

	/**
	 * 上传用户音频数据
	 * 
	 * @param request
	 * @param voiceFile
	 * @return 音频数据相对路径
	 */
	public static String saveVoiceToDisk(HttpServletRequest request,
			MultipartFile voiceFile)
	{
		String voiceName = null;
		if (request == null || voiceFile == null) { return null; }
		try
		{
			InputStream is = voiceFile.getInputStream();
			if (is.available() == 0)
			{
				is.close();
				return null;
			}
			File voiceDir = new File(getResource(request),
					Constant.VOICESSOURCES);
			if (!voiceDir.exists())
			{
				voiceDir.mkdirs();
			}
			String extension = FilenameUtils.getExtension(voiceFile
					.getOriginalFilename());
			voiceName = System.currentTimeMillis() + "." + extension;
			File file = new File(voiceDir, voiceName);
			FileOutputStream fos = new FileOutputStream(file);
			byte[] bytes = new byte[1024];
			@SuppressWarnings("unused")
			int len;
			while ((len = is.read(bytes)) != -1)
			{
				fos.write(bytes);
			}
			fos.close();
			is.close();
		}
		catch (Exception e)
		{
			return null;
		}
		return Constant.VOICESSOURCES + voiceName;
	}
}
