
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>cleancar</title>
<meta charset='utf8'>
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes">
<script src="../../cwresources/components/platform/platform.js"></script>
<link rel="import"
	href="../../cwresources/components/core-scroll-header-panel/core-scroll-header-panel.html">
<link rel="import"
	href="../../cwresources/components/core-toolbar/core-toolbar.html">

<link href="../../cwresources/components/core-item/core-item.html"
	rel="import">
<link
	href="../../cwresources/components/core-header-panel/core-header-panel.html"
	rel="import">
<link href="../../cwresources/components/core-icons/core-icons.html"
	rel="import">
<link
	href="../../cwresources/components/paper-icon-button/paper-icon-button.html"
	rel="import">
<link
	href="../../cwresources/components/paper-checkbox/paper-checkbox.html"
	rel="import">
<link
	href="../../cwresources/components/paper-radio-group/paper-radio-group.html"
	rel="import">
<link href="../../cwresources/components/paper-item/paper-item.html"
	rel="import">

<link
	href="../../cwresources/components/paper-menu-button/paper-menu-button.html"
	rel="import">
<link
	href="../../cwresources/components/paper-dialog/paper-dialog-transition.html"
	rel="import">
<link href="../../cwresources/components/paper-dialog/paper-dialog.html"
	rel="import">

<link href="../../cwresources/components/paper-button/paper-button.html"
	rel="import">
<link href="../../cwresources/components/paper-input/paper-input.html"
	rel="import">
<link href="../../cwresources/components/core-ajax/core-ajax.html"
	rel="import">
<link rel="import" href="../../cwresources/components/paper-ripple/paper-ripple.html">
<link rel="import" href="../../cwresources/components/paper-toggle-button/paper-toggle-button.html">
<link href="../../cwresources/components/core-animated-pages/core-animated-pages.html" rel="import">
<link href="../../cwresources/components/core-animated-pages/transitions/cross-fade.html" rel="import">
<link href="../../cwresources/components/core-animated-pages/transitions/slide-from-right.html" rel="import">
<link href="../mycomponents/reservationcard/reservation-table.html" rel="import">

<link rel="import" href="../../cwresources/components/paper-toast/paper-toast.html">


<link rel='stylesheet' href='../css/carhome.css'>
<style>
.c-p-add{
  width: 50%;
      min-width: 350px;
}
.c-p-caption paper-radio-button{
	display:inline-block;
}
.p-select{
	width:100%;
	border:none;
	border:1px solid #757575;
	font-size:18px;
	color:#757575;
	background:none;
}

.p_file{
	font-size:18px;
	color:#757575;
	margin-top:8px;
}
.c-fab-fixed{
	position:fixed;
	right: 24px;
	bottom:24px;
}
core-animated-pages {
      position: absolute;
      top: 0px;
      right: 0;
      bottom: 0;
      left: 0;
      overflow: hidden;
    }

    section > div {
      height: 100%;
      color: white;
    }
</style>
</head>


<body unresolved class="p_body">
     
     <paper-toast id="p-a-msg" role="alert">
	 </paper-toast>
	 
	<paper-dialog heading="添加" class="c-p-add"
		transition="paper-dialog-transition-center">
	<div class="c-form">
		    <core-ajax class="addform" 
		               handleAs="json"
		               url="../api/area/post" 
		               method="post"
		               >
		    </core-ajax>
		    <template bind="{{item}}" is="auto-binding" id="add_p_form">
			
			<paper-input label="名称" inputValue="{{item.name}}"
				placeholder="名称" floatingLabel  required></paper-input>
			
			<paper-input label="X" inputValue="{{item.centerX}}"
				placeholder="X" floatingLabel></paper-input>
			<paper-input label="Y"  inputValue="{{item.centerY}}"
				placeholder="Y" floatingLabel></paper-input>
			<paper-input label="半径"  inputValue="{{item.radius}}"
				placeholder="半径" floatingLabel></paper-input>
			<paper-input label="区域描述"  inputValue="{{item.description}}"
				placeholder="区域描述" floatingLabel></paper-input>
			</template>
	</div>

	<paper-button label="取消" affirmative></paper-button>
	<paper-button label="确定" affirmative autofocus class="p_confirm"></paper-button> 
	</paper-dialog>

	
	
	<core-animated-pages  transitions="slide-from-right">
    <section>
              <core-ajax auto url="../api/reservation/all" class="p_list"  handleAs="json"></core-ajax>
		      <core-scroll-header-panel condenses keepCondensedHeader
				condensedHeaderHeight="80" mode="cover"> 
				<core-toolbar id="mainheader">
				<div class="bottom indent bottom-text" self-end>
					<div class="c_m_title">预约列表</div>
					<div class="subtitle">预约信息查看</div>
				</div>
				<div flex=""></div>
				</core-toolbar>
				<div class="content c-product-main" >
				    <div class="fab red c-fab-fixed">
					      <core-icon icon="add"></core-icon>
					      <paper-ripple class="circle recenteringTouch" fit></paper-ripple>
					    </div>
					
					<template id="tableTemplate" bind> 
					   <reservation-table
						data="{{data}}" columns="{{columns}}" rowStatus={{rowStatus}} sortColumn="id"
						sortDescending="true"></reservation-table> 
				   </template>
				   
				   <div horizontal layout>
					  <div class="button raised more">
					      <div class="center" fit>加载更多</div>
					      <paper-ripple fit></paper-ripple>
					    </div>
					   <div flex></div>
					  <div>
					  	<template id="pageTemplate" bind> 
						  <div style="color:#646464;font-size:14px;line-height:32px;">共{{pages}}页，第{{curpage}}页</div>
					   </template>
					  </div>
					</div>
				</div>
				</core-scroll-header-panel>
    </section>
    <section>
      <div layout vertical center center-justified>
          <core-scroll-header-panel condenses
				condensedHeaderHeight="80" mode="cover">
				<core-toolbar id="mainheader"  style="background-color:#e91e63;">
				<div class="bottom indent bottom-text" self-end>
					<div class="c_m_title">新建订单</div>
					<div class="subtitle">预约详情</div>
				</div>
				<div flex=""></div>
				</core-toolbar>
		  </core-scroll-header-panel>
      </div>
    </section>
    </core-animated-pages>
	<script>
    var up = true;
    var max = 1;
    function stuff() {
      var p = document.querySelector('core-animated-pages');
      if (up && p.selected === max || !up && p.selected === 0) {
        up = !up;
      }
      if (up) {
        p.selected += 1;
      } else {
        p.selected -= 1;
      }
    }
    
    document.querySelector('.p_body').addEventListener("orderlist-show",function(e){
			    stuff();
	});
    </script>
	<script>
		
		window.addEventListener('polymer-ready', function() {
			var ajaxlist = document.querySelector('.p_list');
			var tableTemplate=document.getElementById('tableTemplate');
			var pageTemplate=document.getElementById('pageTemplate')
			
			var add_p_form=document.querySelector('#add_p_form');
			add_p_form.item={};
			//start显示添加表单
			var fab_fixed=document.querySelector('.c-fab-fixed');
			var addPanel = document.querySelector('.c-p-add');
			fab_fixed.addEventListener("click", function(e) {
			   add_p_form.item={};
				addPanel.toggle();
			});
			
			//end显示添加表单
			
			
			
			var listdata=[];
			 var page=1;
			 var pages=0;
			var rowStatus="PROCESSING";
			
			var columns = [{
					name : 'id',
					title : '编号'
				}, {
					name : 'customer_mobile',
					title : '客户电话'
				}, {
					name : 'customer_name',
					title : '客户姓名'
				}, {
					name : 'carNo',
					title : '车牌'
				}, {
					name : 'address',
					title : '地址'
				}, {
					name : 'create_date',
					title : '创建时间'
				}, {
					name : 'message_text',
					title : '客户留言'
				}, {
					name : 'message_voice_url',
					title : '客户语音'
				}, {
					name : 'action',
					title : '操作'
				}];
			
			//start获取列表
			ajaxlist.addEventListener("core-response", function(e) {
			    var newdata=e.detail.response.reservations;
			    pages=e.detail.response.pages;
			    for ( var i=0 ; i < newdata.length ; ++i ){
			    	listdata.push(newdata[i]);
			    }
			    
			    pageTemplate.model={
			    	pages:pages,
					curpage:page
			    }
				
				tableTemplate.model = {
					data : listdata,
					columns : columns,
					rowStatus:rowStatus
				};
			});
			//end获取列表
			
			//start加载更多
			var moreButton = document.querySelector('.more');
			moreButton.addEventListener("click", function(e) {
			   rowStatus=tableTemplate.model.rowStatus;
			  
			   if(!ajaxlist.params){
			   		page=2;
			   		ajaxlist.params={'pid':page};
			   }else{
			   		var pid=ajaxlist.params;
			   		if(page<pages){
			   			page=pid.pid+1;
			   		};
			   		
			   		ajaxlist.params={'pid':page};
			   }
			  
			});
			//end
			
			
		});
	</script>
	<script src="../../cwresources/js/app.js"></script>

</body>

</html>
