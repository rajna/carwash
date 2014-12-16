
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
	<div class="c-form">
		<div class="f-inner">
		    <template bind="{{product}}" is="auto-binding" id="add_p_form">
		    <core-ajax id="addProductform" 
		               handleAs="json" 
		               response="{{response}}" 
		               on-core-response="{{handleReg}}" 
		               url="/api/reg" 
		               params="{{product}}" 
		               method="post">
		    </core-ajax>
			<select class="p-select">
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
			<input type="file" name="File" id="p-file">
			</template>
		</div>
	</div>

	<paper-button label="取消" affirmative></paper-button>
	<paper-button label="确定" affirmative autofocus></paper-button> </paper-dialog>

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
		var addProductPanel = document.querySelector('.addProductPanel');

		var addProduct = document.querySelector('.c-p-addProduct');
		addProductPanel.addEventListener('click', function(e) {
			addProduct.toggle();
		});
		
		var p_category = document.querySelector('.p_category');
		var ajax = document.querySelector('.p_list');
		
		p_category.addEventListener("change",function(e){
			var chenked=e.target.getAttribute('name');
			ajax.params={"cid":chenked};
		});
		
		var add_p_form=document.querySelector('#add_p_form');
		var addProductformajax = document.querySelector('.addProductform');

		window.addEventListener('polymer-ready', function() {
			
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
		});
	</script>
	<script src="../../cwresources/js/app.js"></script>

</body>

</html>
