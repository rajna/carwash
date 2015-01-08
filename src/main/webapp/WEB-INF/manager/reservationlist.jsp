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
.e-fab-fixed-false{
	position:fixed;
	right: 24px;
	top:96px;
	color:#757575;
	
	transition: transform 500ms cubic-bezier(0.4,0,.2,1) 500ms,background-color 500ms cubic-bezier(0.4,0,.2,1);
	-webkit-transition: -webkit-transform 500ms cubic-bezier(0.4,0,.2,1) 500ms,background-color 500ms cubic-bezier(0.4,0,.2,1);
	-webkit-transform: scale(0);
	transform: scale(0);
	-webkit-transform-origin: 50% 50%;
	transform-origin: 50% 50%;
}

.e-fab-fixed-true{
    position:fixed;
	right: 24px;
	top:96px;
	color:#757575;
	
	transition: transform 500ms cubic-bezier(0.4,0,.2,1) 500ms,background-color 500ms cubic-bezier(0.4,0,.2,1);
	-webkit-transition: -webkit-transform 500ms cubic-bezier(0.4,0,.2,1) 500ms,background-color 500ms cubic-bezier(0.4,0,.2,1);
	-webkit-transform: scale(1);
	transform: scale(1);
	-webkit-transform-origin: 50% 50%;
	transform-origin: 50% 50%;
	
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
		width:100%;
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
	
	<core-animated-pages  transitions="slide-from-right">
    <section>
              <core-ajax url="../api/reservation/all" class="p_list"  handleAs="json"></core-ajax>
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
					  <div class="button raised more" style="margin-top:12px;">
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
        
        <div style="width:394px;margin-left:15px;position:absolute;top:64px;left:0px;z-index:2;">
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
							<div style="height:580px;overflow:auto;margin-top:64px;">
							  	<template id="categoryTemplate" bind> 
							  	<orderp-table data="{{data}}" columns="{{columns}}" newCategory="{{newCategory}}" isAdd="{{isAdd}}" sortColumn="id" sortDescending="false">
								</orderp-table> 
								</template>
							</div>
						  </div>
	    <core-ajax url="../api/order/listforrese" class="o_list"  handleAs="json"></core-ajax>
	          <core-scroll-header-panel condenses style="background-color:#b9f6ca;z-index:1;">
	                 
					<core-toolbar class="tall" style="background-color:#b9f6ca;border-bottom: 1px solid #bdbdbd;height:128px;z-index:500;">
						<div flex></div>
					    <paper-icon-button icon="close" onclick="back();"></paper-icon-button>
					    <template id="sumbitButton" bind="{{isHidden}}" is="auto-binding">
					     <div class="fab grey e-fab-fixed-{{isHidden}}" onclick="submitwediteorder();">
					      <core-icon icon="check"></core-icon>
					      <paper-ripple class="circle recenteringTouch" fit></paper-ripple>
					     </div>
					     </template>
					</core-toolbar>
					<div class="content c-product-main" style="margin-top:64px;padding:0 0 0 0;z-index:1000;background-color:#b9f6ca;">
					    
					    <div horizontal layout flex style="padding-bottom:64px;">
						  
						  <div style="width:55%;margin-left:45%;">
						    <core-ajax url="../api/order/update" id="ajanxupdateorder" handleAs="json"></core-ajax>
						    <core-ajax url="../api/order/post" id="ajanxaddorder" handleAs="json"></core-ajax>
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
							  <div><paper-input onchange="showsubmit();" label="数量" type="number" min="0" step="1" placeholder="{{item.amount}}" value={{item.amount}} style="width:40px;padding:0px;display:inline-block;"></paper-input>个</div>
							</div>
							
							</template>
							<paper-input label="车牌" onchange="showsubmit();" placeholder="车牌" value={{shopitem.carNo}} floatingLabel></paper-input>
							<paper-input label="地址" onchange="showsubmit();" placeholder="地址" value={{shopitem.address}} floatingLabel></paper-input>
							<paper-input label="服务人员" onchange="showsubmit();" placeholder="服务人员" value={{shopitem.workerName}} floatingLabel></paper-input>
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
		    var Serialize=function(obj){
		    	switch(obj.constructor){     
				        case Object:     
				            var str = "{";     
				            for(var o in obj){     
				                str += o + ":" + Serialize(obj[o]) +",";     
				            }     
				            if(str.substr(str.length-1) == ",")     
				                str = str.substr(0,str.length -1);     
				             return str + "}";     
				             break;     
				         case Array:                 
				             var str = "[";     
				             for(var o in obj){  
				             	if(Serialize(obj[o])){
				             		str += Serialize(obj[o]) +",";   
				             	}   
				                   
				             }  
				             if(str.substr(str.length-1) == ","){
				             	str = str.substr(0,str.length-1);
				             }    
				             return str + "]";     
				             break;     
				         case Boolean:     
				             return "\"" + obj.toString() + "\"";     
				             break;     
				         case Date:     
				             return "\"" + obj.toString() + "\"";     
				             break;     
				         case Function: 
				             return ""   
				             break;     
				         case Number:     
				             return "\"" + obj.toString() + "\"";     
				             break;      
				         case String:     
				             return "\"" + obj.toString() + "\"";     
				             break;         
				     }     
		    }
		    var cloneAll=function(obj){   
			  var o;  
			    switch(typeof obj){  
			    case 'undefined': break;  
			    case 'string'   : o = obj + '';break;  
			    case 'number'   : o = obj - 0;break;  
			    case 'boolean'  : o = obj;break;  
			    case 'object'   :  
			        if(obj === null){  
			            o = null;  
			        }else{  
			            if(obj instanceof Array){  
			                o = [];  
			                for(var i = 0, len = obj.length; i < len; i++){  
			                    o.push(cloneAll(obj[i]));  
			                }  
			            }else{  
			                o = {};  
			                for(var k in obj){  
			                    o[k] = cloneAll(obj[k]);  
			                }  
			            }  
			        }  
			        break;  
			    default:          
			        o = obj;break;  
			    }  
			    return o;   
			}
			
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
		    var msgtoast= document.querySelector('#p-a-msg');
		    var ajanxupdateorder=document.getElementById('ajanxupdateorder');
		    var ajanxaddorder=document.getElementById('ajanxaddorder');
			var ajaxlist = document.querySelector('.p_list');
			var ajaxorderlist = document.querySelector('.o_list');
			var tableTemplate=document.getElementById('tableTemplate');
			var pageTemplate=document.getElementById('pageTemplate');
			var shopTemplate=document.getElementById('shopTemplate');
			var shopTemplatecopy=document.getElementById('shopTemplatecopy');
			var sumbitButton=document.getElementById('sumbitButton');
			
			var categoryTemplate=document.getElementById('categoryTemplate');
			var categoryTitle=document.getElementById('categoryTitle');
			var orderListTemplate=document.getElementById('orderListTemplate');
			
			categoryTitle.categorytitle="选择产品类别";
			sumbitButton.isHidden=false;
			
			
			//start预约列表
			var listdata=[];
			var page=0;
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
			
			ajaxlist.params={'pid':0,'status':"PROCESSING"};
			ajaxlist.go();
			ajaxlist.addEventListener("core-response", function(e) {
			    var newdata=e.detail.response.reservations;
			    var curpage=page+1;
			    pages=e.detail.response.pages;
			    for ( var i=0 ; i < newdata.length; ++i ){
			    	listdata.push(newdata[i]);
			    }
			    
			    pageTemplate.model={
			    	pages:pages,
					curpage:curpage
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
			  
			   //if(!ajaxlist.params){
			   //		page=1;
			   //		ajaxlist.params={'pid':page};
			   //}else{
			   		var params=ajaxlist.params;
			   		var pid=params.pid;
			   		if(page<pages-1){
			   			page=pid+1;
			   			console.log(page);
			   			console.log(pages);
			   		    ajaxlist.params={'pid':page,'status':rowStatus};
			   		    ajaxlist.go();
			   		};
			   		
			  
			});
			//end
			
			//start根据类别加载
			document.querySelector('.p_body').addEventListener("orderlist-category",function(e){
			    	rowStatus=tableTemplate.model.rowStatus;
			    	listdata=[];
			    	page=0;
			        pages=0;
			    	ajaxlist.params={'pid':0,'status':rowStatus};
			    	ajaxlist.go();
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
			      sumbitButton.isHidden=false;
			      shopTemplate.shopitem={};
			      shopTemplate.shopitem=cloneAll(shopTemplatecopy.shopitemcopy);
			      up=false;
			      stuff();
			    };
			    
			    this.showsubmit=function(){
			    	sumbitButton.isHidden=true;
			    };
			    
			    
			    
			    document.querySelector('.p_body').addEventListener("orderlist-show",function(e){
			    	reservationId=tableTemplate.model.reservationId;
			    	ajaxorderlist.params={'rid':reservationId};
			    	ajaxorderlist.go();
					stuff();
				});
				
				
				
				
				
				var ordercolumns = [{
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
				},{
					name : 'orderItems',
					title : '购买商品'
				}];
				
				var itemcolumns = [{
					name : 'imageLink',
					title : '图片'
				}, {
					name : 'name',
					title : '名字'
				}, {
					name : 'price',
					title : '价格',
					more:'元'
				}, {
					name : 'amount',
					title : '数量',
					more:'个'
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
			   var ajaxtype="";
			   var categoryajax = document.querySelector('.category_list');
			   var editeitem=null;
			    document.querySelector('.p_body').addEventListener("editeorder-show",function(e){
			        ajaxtype="update";
			        var shopitem={};
			    	shopitem=orderListTemplate.model.shopitem;
			    	shopTemplate.shopitem=shopitem;
					shopTemplatecopy.shopitemcopy=cloneAll(shopitem);
			    	up=true;
					stuff();
					
				});
				
				var fab_fixed=document.querySelector('.c-fab-fixed');
				
				//增加订单
				fab_fixed.addEventListener("click", function(e) {
				    ajaxtype="add";
				    var shopitem={};
				    shopitem.id=tableTemplate.model.reservationId;
				    
				    shopTemplate.shopitem=shopitem;
				    shopTemplate.shopitem.orderItems=shopTemplate.shopitem.orderItems?shopTemplate.shopitem.orderItems:[];
				    up=true;
					stuff();
				});
				
				this.submitwediteorder=function(){
				    var items=cloneAll(shopTemplate.shopitem.orderItems);
					      var orderItems=[];
					      for(var i=0;i<items.length;i++){
					        var order={};
					      	if(items[i]!=undefined){
					      		order.id=items[i].id?items[i].id:0;
					      		order.productId=items[i].productId;
					      		order.amount=items[i].amount;
					      	}
					      	orderItems.push(order);
					      }
			    	if(ajaxtype=="update"){
			    		
					      ajanxupdateorder.params=cloneAll(shopTemplate.shopitem);
					      ajanxupdateorder.params.orderItems=Serialize(orderItems);
					      ajanxupdateorder.params.workerId=1;
					      ajanxupdateorder.go();
			    	};
			    	if(ajaxtype=="add"){
			    		  ajanxaddorder.params=cloneAll(shopTemplate.shopitem);
					      ajanxaddorder.params.orderItems=Serialize(orderItems);
					      ajanxaddorder.params.workerId=1;
					      ajanxaddorder.go();
			    	}
				      
			    };
				
				
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
			   
			   //start响应订单修改
			   ajanxupdateorder.addEventListener("core-response", function(e) {
			   	ajaxorderlist.go();
			   	back();
			   	
			    if(e.detail.response.success){
			    	msgtoast.text=e.detail.response.message;
			    }else{
			    	msgtoast.text="添加失败";
			    }
			    msgtoast.show();
			   });
			   //end响应订单修改
			   
			   //start响应订单添加
			   ajanxaddorder.addEventListener("core-response", function(e) {
			    reservationId=tableTemplate.model.reservationId;
			    ajaxorderlist.params={'rid':reservationId};
			    ajaxorderlist.go();
			   	back();
			   	
			    if(e.detail.response.success){
			    	msgtoast.text=e.detail.response.message;
			    }else{
			    	msgtoast.text="添加失败";
			    }
			    msgtoast.show();
			   });
			   //end响应订单添加
			   
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
			        sumbitButton.isHidden=true;
			    	var isAdd=categoryTemplate.model.isAdd;
			    	var newCategory=categoryTemplate.model.newCategory;
					var productitems=shopTemplate.shopitem.orderItems;
			    	var isIn=false;
			    	if(productitems){
			    		for(var i=0;i<productitems.length;i++){
				    		if(newCategory.name==productitems[i].name){
				    			isIn=true;
				    			if(isAdd){
				    				productitems[i].amount+=1;
				    			}else{
				    				if(productitems[i].amount<=1){
				    					productitems[i].amount=0;
				    				}else{
				    					productitems[i].amount-=1;
				    				}
				    			}
				    			
				    		}
				    	};
			    	}
			    	
			    	
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
			    	}
				});
			   //end修改购物车
		});
	</script>
	<script src="../../cwresources/js/app.js"></script>

</body>

</html>