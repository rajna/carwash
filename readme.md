车夫帮 接口说明
==========

#### 公共参数
- 手机号码正则表达式:`^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$`



一、客户数据接口
------------

#### 1.客户获取验证码
- URL:`/api/customercode`
	* 请求方式:get OR post
	* 参数`{mobile}`：String
	* 返回json,包含的key-value:
		+ `{success}`,boolean类型
		+ `{message}`,String类型
		+ `{leftTime}`,int类型,只有success为true的时候才会有该数据

		
#### 2.客户登录
- URL:`/api/customerlogin`
	* 请求方式:get OR post
	* 参数`{mobile}`：String
	* 参数`{code}`：String
	* 返回json,包含的key-value:
		+ `{success}`,boolean
		+ `{message}`,String
		+ `{password}`,String,只有success为true的时候才会有该数据
		+ `{mobile}`,String,只有success为true的时候才会有该数据