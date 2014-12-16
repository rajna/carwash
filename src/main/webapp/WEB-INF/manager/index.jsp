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
	</core-submenu> <core-submenu icon="chevron-right" label="区域管理"> <core-item
		label="区域列表" tag="arealist" url="arealist"></core-item> </core-submenu> <core-submenu
		icon="chevron-right" label="预约管理"> <core-item
		label="预约列表" tag="reservationlist" url="reservationlist"></core-item>
	</core-submenu> </nav-frame>
	<!--
  <core-drawer-panel id="drawerPanel">

  <core-header-panel drawer id="c_h_sidemenu" mode="seamed">
    <core-toolbar id="navheader">
      <span><img src="img/car-logo.png" class="c_h_logo"></span>
    </core-toolbar>

    <core-menu selected="0">
    
      <core-submenu icon="chevron-right" label="产品管理">
      
        <core-item icon="settings" label="Settings"><a href="#settings" target="_self"></a></core-item>
    <core-item icon="account-box" label="Account"><a href="product.html"></a></core-item>
        
      </core-submenu>
      
      <core-submenu icon="chevron-right" label="区域管理">
      
        <core-item label="区域列表"></core-item>
        
      </core-submenu>
      
    </core-menu>
   </core-header-panel>

  <core-scroll-header-panel main condenses keepCondensedHeader condensedHeaderHeight="80">
    <core-toolbar id="mainheader">
      <paper-icon-button 
        id="navicon" icon="menu"></paper-icon-button>
      <div class="bottom indent bottom-text" self-end>
        <div class="c_m_title">产品列表</div>
        <div class="subtitle">产品信息查看</div>
      </div>
    </core-toolbar>
    <div class="content">
      
    </div>
  </core-scroll-header-panel>

</core-drawer-panel>

  
<script src="js/app.js"></script>
-->
</body>

</html>
