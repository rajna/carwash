<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>车帮夫后台主页</title>

<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes">
<script src="../../cwresources/components/platform/platform.js">
	
</script>

<link rel="import" href="../mycomponents/m-frame/nav-frame.html">


</head>

<body unresolved>
	<nav-frame> <core-submenu icon="chevron-right" label="产品管理">

	<core-item label="产品列表" tag="productlist" url="productlist"></core-item>
	</core-submenu> 
	<core-submenu icon="chevron-right" label="客户管理"> 
		<core-item
		label="客户列表" tag="customerslist" url="customerslist"></core-item> 
	</core-submenu> 
	<core-submenu
		icon="chevron-right" label="预约管理"> 
		<core-item
		label="预约列表" tag="reservationlist" url="reservationlist">
		</core-item>
	</core-submenu> </nav-frame>

</body>

</html>
