
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
<link href="../mycomponents/productcard/p-table.html" rel="import">

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
		    
			<select class="p-select" selectedIndex={{product.categoryId}}>
			<c:forEach items="${categories }" var="category">
					<option  name='${category.id}'>${category.name }</option>
			</c:forEach>
			</select>
			
			<paper-input label="名称" inputValue="{{product.name}}"
				placeholder="名称" floatingLabel></paper-input>
			<paper-input label="价格"  type="number" step="0.1" inputValue="{{product.price}}"
				placeholder="价格" floatingLabel></paper-input>
			
			<paper-input label="描述"  inputValue="{{product.description}}"
				placeholder="描述" floatingLabel></paper-input>
			
			</template>
			
			<input type="file" name="image" bind={{product.imageLink}} class="p_file">
	</div>

	<paper-button label="取消" affirmative></paper-button>
	<paper-button label="确定" affirmative autofocus class="p_confirm"></paper-button> 
	</paper-dialog>

	<core-ajax auto url="../api/product/list" class="p_list"
		params='{"cid":"1"}' handleAs="json"></core-ajax>

	<template repeat="{{data}}">
	<div>{{name}}</div>
	</template>

	<core-scroll-header-panel condenses keepCondensedHeader
		condensedHeaderHeight="80" mode="cover"> <core-toolbar
		id="mainheader">
	<div class="bottom indent bottom-text" self-end>
		<div class="c_m_title">产品列表</div>
		<div class="subtitle">产品信息查看</div>
		
	</div>
	<div flex=""></div>
	<paper-menu-button icon="more-vert" halign="right" noTransition>
	<paper-item class="addProductPanel">添加产品</paper-item> </paper-menu-button> </core-toolbar>
	<div class="content c-product-main" >
	    <div class="fab red c-fab-fixed">
		      <core-icon icon="add"></core-icon>
		      <paper-ripple class="circle recenteringTouch" fit></paper-ripple>
		    </div>
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
			data="{{data}}" columns="{{columns}}" categories="{{categories}}" sortColumn="id"
			sortDescending="false"></p-table> </template>

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
			fab_fixed.addEventListener("click", function(e) {
			   var formData = new FormData();
			   add_p_form.product={};
				addProduct.toggle();
			});
			var addProductPanel = document.querySelector('.addProductPanel');
			var addProduct = document.querySelector('.c-p-addProduct');
			addProductPanel.addEventListener("click", function(e) {
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
			    msgtoast.text=e.detail.response.message;
			    if(!e.detail.response.success){
			        //添加失败
			        //var evt = document.createEvent( 'HTMLEvents' );
			        // initEvent接受3个参数：
			        // 事件类型，是否冒泡，是否阻止浏览器的默认行为
			        //evt.initEvent("userlogin", true, true); 
			        //document.querySelector('.p_body').dispatchEvent(evt);
			        if(e.detail.response.relogin){
			        	parent.login(); 
			        }
			    }
			    
			    msgtoast.show();
			    ajax.go();
			});
			//end
			
			
			
			//start获取列表
			ajax.addEventListener("core-response", function(e) {
			   var categories=[];
			   var p_select=document.querySelector('.p-select');
			   var options=p_select.getElementsByTagName("option");
			   for(var i=0;i<options.length;i++){
			    var c={};
			    c.id=options[i].attributes.getNamedItem("name").value;
			    c.name=options[i].innerHTML;
			    categories.push(c);
			   }
			   
				var columns = [{
					name : 'id',
					title : '编号'
				}, {
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
					columns : columns,
					categories:categories
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
