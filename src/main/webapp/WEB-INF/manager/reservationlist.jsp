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

<link href="../../cwresources/components/paper-menu-button/paper-menu-button.html" rel="import">
<link href="../../cwresources/components/paper-item/paper-item.html" rel="import">
<link href="../mycomponents/reservationcard/reservation-table.html" rel="import">
<link href="../mycomponents/reservationcard/order-table.html" rel="import">
<link href="../mycomponents/productcard/orderp-table.html" rel="import">
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
    
    .c-shopcard{
    	border-radius: 2px;
		box-shadow: 0 2px 5px 0 rgba(0,0,0,.26);
		background-color:#fff;
    }
    .shopwrap{
    	width: 304px;
		margin: 0 auto;
		padding: 40px 24px 48px;
    }
    .shopitem{
		padding: 12px 0px;
		color:#757575;
		font-size:14px;
	}
	.shopitem img{
		padding-right:6px;
	}
	.order-category{
		position:absolute;
		top:0px;
		left:0px;
		width:100%;
	}
	.order-category paper-item{
		color:#616161;
	}
	
	.order-category core-toolbar{
		border-bottom: 1px solid #bdbdbd;
		background-color:#b9f6ca;
		z-index:500;
		width:45%;
	}
	
	.order-category span{
		color:#656565;
	}
	
	paper-icon-button::shadow core-icon,paper-menu-button::shadow core-icon {
        fill: #656565;
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
				<div flex></div>
				</core-toolbar>
				<div class="content c-product-main" >
					<template id="tableTemplate" bind> 
					   <reservation-table
						data="{{data}}" columns="{{columns}}" rowStatus={{rowStatus}} reservationId={{reservationId}} sortColumn="id"
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
    <core-ajax url="../api/order/listforrese" class="o_list"  handleAs="json"></core-ajax>
          <core-scroll-header-panel condenses>
				
				<core-toolbar class="tall mainheader" style="background-color:#e91e63;">
					<div class="bottom indent bottom-text" self-end>
						<div class="c_m_title">新建订单</div>
						<div class="subtitle">订单详情</div>
					</div>
					<div flex></div>
						 <paper-icon-button icon="close" onclick="back();"></paper-icon-button>
				</core-toolbar>
				<div class="content c-product-main" style="margin-top:256px;">
				      <div class="fab blue c-fab-fixed">
					      <core-icon icon="add"></core-icon>
					      <paper-ripple class="circle recenteringTouch" fit></paper-ripple>
					</div>
					<template id="orderListTemplate" bind> 
					   <order-table
						data="{{data}}" columns="{{columns}}" itemcolumns={{itemcolumns}} shopitem={{shopitem}}  sortColumn="id"
						sortDescending="true">
						</order-table> 
				    </template>
				</div>
		  </core-scroll-header-panel>
    </section>
        <section>
	    <core-ajax url="../api/order/listforrese" class="o_list"  handleAs="json"></core-ajax>
	          <core-scroll-header-panel condenses style="background-color:#b9f6ca;">
					<core-toolbar class="tall" style="background-color:#b9f6ca;border-bottom: 1px solid #bdbdbd;height:103px;z-index:500;">
						<div flex></div>
					    <paper-icon-button icon="close" onclick="back();"></paper-icon-button>
					</core-toolbar>
					<div class="content c-product-main" style="margin-top:64px;padding:0 0 0 0;z-index:1000;background-color:#b9f6ca;">
					    <div horizontal layout flex>
						  <div style="width:45%;margin-left:15px;padding-top:64px;height:580px;overflow:auto;">
						    <core-ajax auto url="../api/product/list" class="category_list" params='{"cid":"1"}' handleAs="json"></core-ajax>
						    <div class="order-category">
						  	<core-toolbar>
						  		<template id="categoryTitle" bind="{{categorytitle}}" is="auto-binding"> 
						  	    	<span class="o-c-title">{{categorytitle}}</span>
						  	    </template>
								<paper-menu-button class="o-c-menu" icon="arrow-drop-down" halign="right" noTransition>
								    <c:forEach items="${categories }" var="category">
								      <paper-item name="${category.id }" label="${category.name }" onclick="getCateroty(this);"></paper-item>
										</paper-radio-button>
									</c:forEach> 
						        </paper-menu-button>
							</core-toolbar>
							</div>
						  	<template id="categoryTemplate" bind> 
						  	<orderp-table data="{{data}}" columns="{{columns}}" newCategory="{{newCategory}}" isAdd="{{isAdd}}" sortColumn="id" sortDescending="false">
							</orderp-table> 
							</template>
						  </div>
						  <div style="width:55%;">
						    <div class="c-shopcard">
						  	<core-toolbar class="tall mainheader" style="background-color:#1de9b6;">
								<div class="bottom indent bottom-text" self-end>
									<div class="c_m_title">已购商品</div>
									<div class="subtitle">订单明细</div>
								</div>
								<div flex></div>
							</core-toolbar>
							<div flex>
							<template id="shopTemplatecopy" bind="{{shopitemcopy}}" is="auto-binding"> 
							</template>
							<template id="shopTemplate" bind="{{shopitem}}" is="auto-binding"> 
							<div class="shopwrap">
							<template repeat="{{item in shopitem.orderItems}}" >
							<div horizontal layout class="shopitem">
							  <div><img style="width:40px;" src="{{'../../cwresources/'+item.imageLink}}"/></div>
							  <div flex style="line-height:28px;">{{item.name}} 价格:{{item.price}}</div>
							  <div><paper-input label="数量" placeholder="{{item.amount}}" style="width:40px;padding:0px;display:inline-block;"></paper-input>个</div>
							</div>
							
							</template>
							<paper-input label="车牌" placeholder="车牌:{{shopitem.carNo}}" floatingLabel></paper-input>
							<paper-input label="地址" placeholder="地址:{{shopitem.address}}" floatingLabel></paper-input>
							<paper-input label="创建时间" placeholder="创建时间:{{shopitem.create_date}}" floatingLabel></paper-input>
							<paper-input label="客服" placeholder="客服:{{shopitem.supportorName}}" floatingLabel></paper-input>
							<paper-input label="服务人员" placeholder="服务人员:{{shopitem.workerName}}" floatingLabel></paper-input>
							</div>
							
							</template>
							</div>
							</div>
						  </div>
						</div>
					</div>
			  </core-scroll-header-panel>
	    </section>
    </core-animated-pages>
	
	<script>
		window.addEventListener('polymer-ready', function() {
			Array.prototype.remove=function(dx) 
			{ 
			    if(isNaN(dx)||dx>this.length){return false;} 
			    for(var i=0,n=0;i<this.length;i++) 
			    { 
			        if(this[i]!=this[dx]) 
			        { 
			            this[n++]=this[i] 
			        } 
			    } 
			    this.length-=1 
			} 
		
			var ajaxlist = document.querySelector('.p_list');
			var ajaxorderlist = document.querySelector('.o_list');
			var tableTemplate=document.getElementById('tableTemplate');
			var pageTemplate=document.getElementById('pageTemplate');
			var shopTemplate=document.getElementById('shopTemplate');
			var shopTemplatecopy=document.getElementById('shopTemplatecopy');
			
			var categoryTemplate=document.getElementById('categoryTemplate');
			var categoryTitle=document.getElementById('categoryTitle');
			var orderListTemplate=document.getElementById('orderListTemplate');
			
			categoryTitle.categorytitle="选择产品类别";
			
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
			
			
			//start预约列表
			var listdata=[];
			 var page=1;
			 var pages=0;
			var rowStatus="PROCESSING";
			var reservationId=null;
			
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
			
			
			ajaxlist.addEventListener("core-response", function(e) {
			    var newdata=e.detail.response.reservations;
			    pages=e.detail.response.pages;
			    for ( var i=0 ; i < newdata.length; ++i ){
			    	listdata.push(newdata[i]);
			    }
			    
			    pageTemplate.model={
			    	pages:pages,
					curpage:page
			    }
				
				tableTemplate.model = {
					data : listdata,
					columns : columns,
					rowStatus:rowStatus,
					reservationId:reservationId
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
			
			//start订单列表获取
			    var up = true;
			    var max = 2;
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
			    };
			    
			    this.back=function() {
			      up=false;
			      stuff();
			    };
			    
			    document.querySelector('.p_body').addEventListener("orderlist-show",function(e){
			    	reservationId=tableTemplate.model.reservationId;
			    	ajaxorderlist.params={'rid':reservationId};
			    	ajaxorderlist.go();
					stuff();
				});
				
				var ordercolumns = [{
					name : 'orderItems',
					title : '购买商品'
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
					name : 'workerName',
					title : '服务人员姓名'
				}, {
					name : 'supportorName',
					title : '客服'
				}, {
					name : 'action',
					title : '操作'
				}];
				
				var itemcolumns = [{
					name : 'imageLink',
					title : '图片'
				}, {
					name : 'name',
					title : '名字'
				}, {
					name : 'price',
					title : '价格'
				}, {
					name : 'amount',
					title : '数量'
				}];
				
				ajaxorderlist.addEventListener("core-response", function(e) {
				    var newdata=e.detail.response.orders;
				    
					orderListTemplate.model = {
						data : newdata,
						columns : ordercolumns,
						itemcolumns:itemcolumns
					};
				});
			   //end订单列表获取
			   
			   //start修改订单
			   var categoryajax = document.querySelector('.category_list');
			   var editeitem=null;
			    document.querySelector('.p_body').addEventListener("editeorder-show",function(e){
			    	var shopitem=orderListTemplate.model.shopitem;
			    	up=true;
					stuff();
					console.log(shopitem);
					shopTemplate.shopitem=shopitem;
					shopTemplatecopy.shopitemcopy=shopitem;
				});
				
				
				categoryajax.addEventListener("core-response", function(e) {
				var categorycolumns = [{
					name : 'imageLink',
					title : '图片'
				}, {
					name : 'name',
					title : '名称'
				}, {
					name : 'price',
					title : '价格'
				}, {
					name : 'action',
					title : '添加'
				} ];
				categoryTemplate.model = {
					data : e.detail.response.products,
					columns : categorycolumns,
					newCategory:{},
					isAdd:true
				};
				});
			   //end修改订单
			   
			   //start根据类别切换
			   this.getCateroty=function(e){
			   	var cid=e.getAttribute('name');
			   	var cname=e.getAttribute('label');
			   	categoryTitle.categorytitle=cname;
			   	categoryajax.params={"cid":cid};
			   }
			   //end根据类别切换
			   
			   
			   //start修改购物车
			   document.querySelector('.p_body').addEventListener("order-refresh",function(e){
			    	var isAdd=categoryTemplate.model.isAdd;
			    	var newCategory=categoryTemplate.model.newCategory;
					var productitems=shopTemplate.shopitem.orderItems;
			    	var isIn=false;
			    	for(var i=0;i<productitems.length;i++){
			    		if(newCategory.name==productitems[i].name){
			    			isIn=true;
			    			if(isAdd){
			    				productitems[i].amount+=1;
			    			}else{
			    				if(productitems[i].amount==1){
			    					productitems.remove(i);
			    				}else{
			    					productitems[i].amount-=1;
			    				}
			    			}
			    			
			    		}
			    	};
			    	
			    	if(!isIn){
			    		var newItem={
			    				amount:1,
			    				categoryId:newCategory.categoryId,
			    				description:newCategory.description,
			    				imageLink:newCategory.imageLink,
			    				name:newCategory.name,
			    				price:newCategory.price,
			    				productId:newCategory.id
			    			};
			    	    if(isAdd){
			    	    	shopTemplate.shopitem.orderItems.push(newItem);
			    	    }
			    	}else{
			    		
			    	}
				});
			   //end修改购物车
		});
	</script>
	<script src="../../cwresources/js/app.js"></script>

</body>

</html>