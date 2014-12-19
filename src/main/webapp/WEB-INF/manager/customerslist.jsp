
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
<link href="../mycomponents/customercard/customer-table.html" rel="import">

<link rel="import" href="../../cwresources/components/paper-toast/paper-toast.html">

<link rel='stylesheet' href='../css/carhome.css'>
<style>
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
	 
	<paper-dialog heading="添加" class="c-p-addProduct"
		transition="paper-dialog-transition-center">
	<div class="c-form">
		    <core-ajax class="addProductform" 
		               handleAs="json"
		               url="../api/product/post" 
		               method="post">
		    </core-ajax>
		    <template bind="{{product}}" is="auto-binding" id="add_p_form">
		    
			<select class="p-select" selectedIndex={{cutomer.categoryId}}>
					<option  name='${cutomer.id}'>${cutomer.name }</option>
			</select>
			
			<paper-input label="名字" inputValue="{{cutomer.name}}"
				placeholder="名字" floatingLabel></paper-input>
			<paper-input label="车牌号"  type="number" inputValue="{{cutomer.carNo}}"
				placeholder="车牌号" floatingLabel></paper-input>
			<paper-input label="电话号"  type="number"  inputValue="{{customer.mobile}}"
				placeholder="电话号" floatingLabel></paper-input>
			<paper-input label="账户余额"  type="credit"  inputValue="{{customer.credit}}"
				placeholder="账户余额" floatingLabel></paper-input>
			
			</template>
			
			<input type="file" name="image" bind={{product.imageLink}} class="p_file">
	</div>

	<paper-button label="取消" affirmative></paper-button>
	<paper-button label="确定" affirmative autofocus class="p_confirm"></paper-button> 
	</paper-dialog>

	<core-ajax auto url="../api/customer/list" class="p_list"  handleAs="json"></core-ajax>

	<template repeat="{{data}}">
	<div>{{name}}</div>
	</template>

	<core-scroll-header-panel condenses keepCondensedHeader
		condensedHeaderHeight="80" mode="cover"> <core-toolbar
		id="mainheader">
	<div class="bottom indent bottom-text" self-end>
		<div class="c_m_title">客户列表</div>
		<div class="subtitle">客户信息查看</div>
		
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
			var ajax = document.querySelector('.p_list');
			var formData = new FormData();
			
			var add_p_form=document.querySelector('#add_p_form');
			add_p_form.product={};
			//start显示产品添加表单
			var fab_fixed=document.querySelector('.c-fab-fixed');
			var addProduct = document.querySelector('.c-p-addProduct');
			fab_fixed.addEventListener("click", function(e) {
			   var formData = new FormData();
			   add_p_form.product={};
				addProduct.toggle();
			});
			
			//end显示产品添加表单
			
			//start提交添加表单
			
			
			var p_confirm_button = document.querySelector('.p_confirm');
			var addProductformajax = document.querySelector('.addProductform');
			p_confirm_button.addEventListener("click", function(e) {
				var p=add_p_form.product;
				if(!p.categoryId){
					p.categoryId=0;
				}
				formData.append("categoryId",p.categoryId+1);
				formData.append("name",p.name);
				formData.append("price",p.price);
				formData.append("description",p.description);
				addProductformajax.body = formData;
		        // Override default type set by core-ajax.
		        // Allow browser to set the mime multipart content type itself. 
		        addProductformajax.contentType = null;
				addProductformajax.go();
				
			});
			//end提交添加表单
			
			//start
			var p_file=document.querySelector('.p_file');
			p_file.addEventListener("change",function(e, detail, sender){
		        for (var i = 0, f; f = e.target.files[i]; ++i) {
		          formData.append(e.target.name,f,f.name);
		        }
		        add_p_form.product.imageLink=e.target.value;
			});
			document.querySelector('.p_body').addEventListener("product-refresh",function(e){
			    ajax.go();
			});
			addProductformajax.addEventListener("core-response",function(e){
			    formData=new FormData();
			    var msgtoast= document.querySelector('#p-a-msg');
			    if(e.detail.response.success){
			    	msgtoast.text=e.detail.response.message;
			    }else{
			    	msgtoast.text="添加失败";
			    }
			    
			    msgtoast.show();
			    ajax.go();
			});
			//end
			
			
			//start获取列表
			ajax.addEventListener("core-response", function(e) {
			  
			   
				var columns = [{
					name : 'id',
					title : '编号'
				}, {
					name : 'name',
					title : '名字'
				}, {
					name : 'carNo',
					title : '车牌号'
				}, {
					name : 'mobile',
					title : '电话'
				}, {
					name : 'credit',
					title : '用户账户余额'
				}, {
					name : 'inuse',
					title : '状态'
				}];
				document.getElementById('tableTemplate').model = {
					data : e.detail.response.customers,
					columns : columns
				};
			});
			//end获取列表
			
			
		});
	</script>
	<script src="../../cwresources/js/app.js"></script>

</body>

</html>
