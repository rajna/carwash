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
 * File name:          VoiceUtil.java
 * Copyright@blog.ilvelh.com(China)
 * Editor:           JDK1.7_40
 */
package com.carwash.util;

import java.io.File;
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
public class VoiceUtil
{
	/**
	 * 获取资源服务器基本路径
	 * 
	 * @param request
	 * @return
	 */
	private static File getResourcePath(HttpServletRequest request)
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
	 * 上传用户音频数据
	 * 
	 * @param request
	 * @param voiceFile
	 * @return 音频数据相对路径
	 */
	public static String saveToDisk(HttpServletRequest request,
			MultipartFile voiceFile)
	{
		if (request == null || voiceFile == null) { return null; }
		try
		{
			InputStream is = voiceFile.getInputStream();
			if (is.available() == 0)
			{
				is.close();
				return null;
			}
			String extension = FilenameUtils.getExtension(voiceFile
					.getOriginalFilename());
			String fileName = System.currentTimeMillis() + "." + extension;

		}
		catch (Exception e)
		{
		}

		if (voiceFile != null)
		{
			String extension = FilenameUtils.getExtension(voiceFile
					.getOriginalFilename());
			String fileName = System.currentTimeMillis() + "." + extension;
			try
			{

			}
			catch (Exception e)
			{
			}
		}

		return null;
	}
}
