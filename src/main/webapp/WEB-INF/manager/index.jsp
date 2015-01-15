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
<script src="../../cwresources/components/platform/platform.js"></script>

<link rel="import" href="../mycomponents/m-frame/nav-frame.html">
<link href="../../cwresources/components/core-localstorage/core-localstorage.html">
<link href="../../cwresources/components/core-ajax/core-ajax.html" rel="import">
<style>
core-submenu,core-item{
  font-size:13px;
  color:#212121;
}
html /deep/ core-icon {
  color: #aaa;
}
</style>
</head>

<body unresolved class="m_body">
	<nav-frame> <core-submenu icon="expand-more" label="产品管理">

	<core-item label="产品列表" tag="productlist" url="productlist"></core-item>
	</core-submenu> 
	<core-submenu icon="expand-more" label="客户管理"> 
		<core-item
		label="客户列表" tag="customerslist" url="customerslist"></core-item> 
	</core-submenu> 
	<core-submenu icon="expand-more" label="区域管理"> 
		<core-item
		label="区域列表" tag="arealist" url="arealist"></core-item> 
	</core-submenu> 
	<core-submenu
		icon="expand-more" label="预约管理"> 
		<core-item
		label="预约列表" tag="reservationlist" url="reservationlist">
		</core-item>
	</core-submenu> </nav-frame>
	
	
	
	
	
	
	
	
	<load-areaUser></load-areaUser>
	
	<!-- start预先载入地区，服务人员数据 -->
	<polymer-element name="load-areaUser">
		<core-ajax url="../api/area/list" class="area_list" auto  handleAs="json"></core-ajax>
		<core-ajax url="../api/user/workers" class="user_list" auto  handleAs="json"></core-ajax>
    </polymer-element>
    <!-- end预先载入地区，服务人员数据 -->
</body>
    <script>
      Polymer('load-areaUser', {
        ready:function(){
        	var ajaxarealist = document.querySelector('.area_list');
			var ajaxuserlist = document.querySelector('.user_list');
			ajaxarealist.addEventListener("core-response", function(e) {
				    var data=e.detail.response.areas;
				    localStorage.arealist = JSON.stringify(data);
				});
			ajaxuserlist.addEventListener("core-response", function(e) {
				    var data=e.detail.response.workers;
				    localStorage.userlist = JSON.stringify(data);
				});
        }
      });
      
      
      window.addEventListener('polymer-ready', function() {
      	document.querySelector('.m_body').addEventListener("login",function(e){
			   	alert("d");
			});
      });
      
    </script>
    

</html>
