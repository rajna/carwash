
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
<link href="../mycomponents/customercard/customer-table.html" rel="import">

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

	<core-ajax auto url="../api/area/list" class="p_list"  handleAs="json"></core-ajax>

	<template repeat="{{data}}">
	<div>{{name}}</div>
	</template>

	<core-scroll-header-panel condenses keepCondensedHeader
		condensedHeaderHeight="80" mode="cover"> <core-toolbar
		id="mainheader">
	<div class="bottom indent bottom-text" self-end>
		<div class="c_m_title">区域列表</div>
		<div class="subtitle">区域信息查看</div>
		
	</div>
	<div flex=""></div>
	</core-toolbar>
	<div class="content c-product-main" >
	    <div class="fab red c-fab-fixed">
		      <core-icon icon="add"></core-icon>
		      <paper-ripple class="circle recenteringTouch" fit></paper-ripple>
		    </div>
		
		<template id="tableTemplate" bind> <customer-table
			data="{{data}}" columns="{{columns}}" sortColumn="id"
			sortDescending="false"></customer-table> </template>

	</div>
	</core-scroll-header-panel>
	<script>
		
		window.addEventListener('polymer-ready', function() {
			var ajaxlist = document.querySelector('.p_list');
			var msgtoast= document.querySelector('#p-a-msg');
			
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
			
			//start提交添加表单
			
			
			var p_confirm_button = document.querySelector('.p_confirm');
			var addformajax = document.querySelector('.addform');
			p_confirm_button.addEventListener("click", function(e) {
				addformajax.params=add_p_form.item;
				addformajax.go();
				
			});
			//end提交添加表单
			
			//start
			addformajax.addEventListener("core-response",function(e){
			    formData=new FormData();
			    
			    if(!e.detail.response.success){
			    	if(e.detail.response.relogin){
			           //iframe调用父窗口的方法
			        	parent.login(); 
			        }
			    }
			    
			    msgtoast.text=e.detail.response.message;
			    msgtoast.show();
			    if(e.detail.response.success){
			    	setTimeout( function() {
									ajaxlist.go();
								}, 1000 );
			    }
			});
			//end
			
			
			//start获取列表
			ajaxlist.addEventListener("core-response", function(e) {
			  
			   
				var columns = [{
					name : 'id',
					title : '编号'
				}, {
					name : 'name',
					title : '名字'
				}, {
					name : 'centerX',
					title : 'X'
				}, {
					name : 'centerY',
					title : 'Y'
				}, {
					name : 'radius',
					title : '半径'
				}, {
					name : 'description',
					title : '区域描述'
				}];
				document.getElementById('tableTemplate').model = {
					data : e.detail.response.areas,
					columns : columns
				};
			});
			//end获取列表
			
			
		});
	</script>
	<script src="../../cwresources/js/app.js"></script>

</body>

</html>
