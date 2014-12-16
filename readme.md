车夫帮 接口说明
==========

#### 公共参数
- 手机号码正则表达式:`^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$`
- 服务器api接口:`http://xx.xx.xx.xx:xxxx/carwarsh/`
- 服务器资源路径:`http://xx.xx.xx.xx:xxxx/cwresources/`



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
		+ `{customer}`：JSONObject customer对象模型


#### 3.客户数据查询
- URL:`/api/customer/view`
	* 请求方式:get OR post
	* 参数`{mobile}`：String
	* 参数`{password}`：String
	* 返回json,包含的key-value:
		+ `{success}`,boolean
		+ `{message}`,String
		+ `{customer}`：JSONObject customer对象模型(success=true时存在)
		
		
#### 4.客户预约
- URL:`/api/reservation/post`
	* 请求方式:post
	* 参数`{mobile}`：String
	* 参数`{password}`：String
	* 参数`{service_time}`：String
	* 参数`{address}`：String
	* 参数`{message_text}`：String
	* 参数`{carNo}`：String
	* 参数`{voice}`：file二进制文件,上传
	* 返回json,包含的key-value:
		+ `{success}`,boolean
		+ `{message}`,String

#### 5.客户预约查询
- URL:`/api/reservation/list`
	* 请求方式:post
	* 参数`{mobile}`：String
	* 参数`{password}`：String
	* 参数`{status}`：String(必选)
		+ 枚举类型`(PROCESSING,CANCELED,COMPLETED)`,如果传递其他参数,则查询不到任何数据
	* 返回json,包含的key-value:
		+ `{success}`,boolean
		+ `{message}`,String
		+ `{reservations}`,jsonarray(success=true时存在)
				
#### 6.客户修改姓名与车牌号
- URL:`/api/customer/update`
	* 请求方式:get Or post
	* 参数`{mobile}`：String
	* 参数`{password}`：String
	* 参数`{name}`：String
	* 参数`{carNo}`：String
	* 返回json,包含的key-value:
		+ `{success}`,boolean
		+ `{message}`,String
		+ `{customer}`：JSONObject customer对象模型(success=true时存在)
		
二、产品数据接口
------------		
#### 1.产品分类与手机首界面的推荐信息
- URL:`/api/categoriesandrecommends`
	* 请求方式:get OR post
	* 返回json,包含的key-value:
		+ `{success}`,boolean
		+ `{message}`,String
		+ `{categories}`,jsonarray
		+ `{recommends}`,jsonarray
		
#### 2.产品列表(根据分类查询的列表)
- URL:`/api/product/list`
	* 请求方式:get OR post
	* 参数`{cid}`：int
	* 返回json,包含的key-value:
		+ `{success}`,boolean
		+ `{message}`,String
		+ `{products}`,jsonarray
		
		
二、订单数据接口
#### 1.订单列表(根据客户查询的列表)
- URL:`/api/order/list`
	* 请求方式:get OR post
	* 参数`{mobile}`：String
	* 参数`{password}`：String
	* 参数`{status}`：String(可选,不传递该参数则查询所有)
		+ 枚举类型`(PROCESSING,CANCELED,COMPLETED)`,如果传递其他参数,则查询所有
	* 返回json,包含的key-value:
		+ `{success}`,boolean
		+ `{message}`,String
		+ `{orders}`,jsonarray(success=true时存在)