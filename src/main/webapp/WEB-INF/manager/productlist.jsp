
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
<link href="../mycomponents/productcard/p-table.html" rel="import">
<link href="../mycomponents/productcard/p-form.html" rel="import">

<link rel='stylesheet' href='../../cwresources/css/carhome.css'>
<style>
.c-p-caption paper-radio-button{
	display:inline-block;
}
.p-select{
	width:100%;
}
</style>
</head>


<body unresolved>

	<paper-dialog heading="添加" class="c-p-addProduct"
		transition="paper-dialog-transition-center">
			<p-form></p-form>
	<!--  <div class="c-form">
		<div class="f-inner">
		    <core-ajax class="addProductform" 
		               handleAs="json"
		               url="../api/product/post" 
		               method="post">
		    </core-ajax>
		    <template bind="{{product}}" is="auto-binding" id="add_p_form">
		    
			<select class="p-select" selectedIndex={{product.categoryId}}>
			<c:forEach items="${categories }" var="category">
					<option  name='${category.id }'>${category.name }</option>
			</c:forEach>
			</select>
			
			<paper-input label="名称" inputValue="{{product.name}}"
				placeholder="名称" floatingLabel></paper-input>
			<paper-input label="价格" min="0" inputValue="{{product.price}}"
				placeholder="价格" floatingLabel></paper-input>
			
			<paper-input label="描述" inputValue="{{product.description}}"
				placeholder="描述" floatingLabel></paper-input>
			
			</template>
			<input type="file" bind="{{product.image}}" class="p_file">
		</div>
	</div>

	<paper-button label="取消" affirmative></paper-button>
	<paper-button label="确定" affirmative autofocus class="p_confirm"></paper-button> 
	-->
	</paper-dialog>

	<core-ajax auto url="../api/product/list" class="p_list"
		params='{"cid":"1"}' handleAs="json"></core-ajax>

	<template repeat="{{data}}">
	<div>{{name}}</div>
	</template>

	<core-scroll-header-panel condenses keepCondensedHeader
		condensedHeaderHeight="80"> <core-toolbar
		id="mainheader">
	<div class="bottom indent bottom-text" self-end>
		<div class="c_m_title">产品列表</div>
		<div class="subtitle">产品信息查看</div>

	</div>
	<div flex=""></div>
	<paper-menu-button icon="more-vert" halign="right" noTransition>
	<paper-item class="addProductPanel">添加产品</paper-item> </paper-menu-button> </core-toolbar>
	<div class="content c-product-main">
		<div class="c-p-caption">
			<div horizontal layout>
			    <paper-radio-group selected="${categories[0].id }" class="p_category">
			    <c:forEach items="${categories }" var="category">
			       <paper-radio-button name='${category.id }' label='${category.name }'>
					</paper-radio-button>
				</c:forEach>
				</paper-radio-group>
			</div>
		</div>
		<template id="tableTemplate" bind> <p-table
			data="{{data}}" columns="{{columns}}" sortColumn="p50"
			sortDescending="false"></p-table> </template>

	</div>
	</core-scroll-header-panel>
	<script>
		
		window.addEventListener('polymer-ready', function() {
			
			//start显示产品添加表单
			var addProductPanel = document.querySelector('.addProductPanel');
			var addProduct = document.querySelector('.c-p-addProduct');
			addProductPanel.addEventListener("click", function(e) {
				addProduct.toggle();
			});
			//end显示产品添加表单
			
			//start提交添加表单
			/*
			var add_p_form=document.querySelector('#add_p_form');
			add_p_form.product={};
			var p_confirm_button = document.querySelector('.p_confirm');
			var addProductformajax = document.querySelector('.addProductform');
			p_confirm_button.addEventListener("click", function(e) {
				addProductformajax.params=add_p_form.product;
				console.log(addProductformajax.params);
				addProductformajax.go();
			});
			
			//end提交添加表单
			
			//start
			var p_file=document.querySelector('.p_file');
			p_file.addEventListener("change",function(e, detail, sender){
				var formData = new FormData();
		        for (var i = 0, f; f = e.target.files[i]; ++i) {
		          formData.append(e.target.name,f,f.name);
		        }
		        add_p_form.product.image=e.target.value;
		        addProductformajax.body = formData;
		        console.log(addProductformajax.body);
		        // Override default type set by core-ajax.
		        // Allow browser to set the mime multipart content type itself. 
		        addProductformajax.contentType = null;
			});
			//end
			*/
			
			//start获取列表
			var ajax = document.querySelector('.p_list');
			ajax.addEventListener("core-response", function(e) {
				var columns = [ {
					name : 'imageLink',
					title : '图片'
				}, {
					name : 'name',
					title : '名称'
				}, {
					name : 'price',
					title : '价格'
				}, {
					name : 'category',
					title : '分类'
				}, {
					name : 'description',
					title : '描述'
				}, {
					name : 'action',
					title : '动作'
				} ];
				document.getElementById('tableTemplate').model = {
					data : e.detail.response.products,
					columns : columns
				};
			});
			//end获取列表
			
			//start根据类别切换列表
			var p_category = document.querySelector('.p_category');
			p_category.addEventListener("change",function(e){
				var chenked=e.target.getAttribute('name');
				ajax.params={"cid":chenked};
			});
			//end根据类别切换列表
		});
	</script>
	<script src="../../cwresources/js/app.js"></script>

</body>

</html>
