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
<script src="../../cwresources/js/snap.svg-min.js"></script>
<script src="../../cwresources/js/classie.js"></script>
<script src="../../cwresources/js/svgLoader.js"></script>

<link rel="import" href="../mycomponents/m-frame/nav-frame.html">
<!--  <link href="../../cwresources/components/core-localstorage/core-localstorage.html">-->
<link href="../../cwresources/components/core-ajax/core-ajax.html" rel="import">
<link href="../../cwresources/components/paper-input/paper-input.html" rel="import">
<link href="../../cwresources/components/paper-toast/paper-toast.html" rel="import">
<style>
core-submenu,core-item{
  font-size:13px;
  color:#212121;
}
html /deep/ core-icon {
  color: #aaa;
}

.container {
	display: none;
	z-index:1;
}

.container.show {
	display: block;
}

.pageload-overlay {
	position: fixed;
	width: 100%;
	height: 100%;
	top: 0;
	left: 0;
	z-index:2;
	visibility: hidden;
}

#page-2{
	z-index:3;position:relative;
}

.pageload-overlay.show {
	visibility: visible;
}

.pageload-overlay svg {
	position: absolute;
	top: 0;
	left: 0;
	pointer-events: none;
}

.pageload-overlay svg path {
	fill: #4fc3f7;
}

.card{
	max-height: 580px;
	max-width: 512px;
	margin:64px auto 0px auto;
	padding: 20px 16px;
	box-sizing: border-box;
	
}
.header {
	font-size: 2.5em;
	color: #fff;
	font-weight: lighter;
}


.pageload-link {
	padding: 13px 20px;
	color: #fff;
	background: rgba(0,0,0,0.25);
	text-transform: uppercase;
	text-align:center;
	letter-spacing: 1px;
	font-size: 0.6em;
	white-space: nowrap;
	text-decoration:none;
	margin-top:24px;
	display:block;
	width:62px;
	
}


   .login-input /deep/ ::-webkit-input-placeholder {
      color: #4f92b6;
    }

    .login-input /deep/ ::-moz-placeholder {
      color: #4f92b6;
    }

    .login-input /deep/ :-ms-input-placeholder {
      color: #4f92b6;
    }

    .login-input /deep/ .label-text,
    .login-input /deep/ .error {
      color: #4fc3f7;
    }

    .login-input /deep/ .unfocused-underline {
      background-color: #4f92b6;
    }

    .login-input[focused] /deep/ .floated-label .label-text {
      color: #fff;
    }

    .login-input /deep/ .focused-underline {
      background-color: #fff;
    }

    .login-input.invalid /deep/ .floated-label .label-text,
    .login-input /deep/ .error {
      color: #fff;
    }

    .login-input.invalid /deep/ .focused-underline {
      background-color: #fff;
    }

    .login-input /deep/ input{
      color: #424242;
    }
    
    .c-h-logout{
    	position:absolute;
    	bottom:24px;left:12px;
    	color:#333;
    }
    
    .c-h-logout core-submenu{
    	cursor:pointer;
    }
</style>


</head>

<body unresolved class="m_body">
   <paper-toast id="p-a-msg" role="alert">
   </paper-toast>
   <div id="loader" class="pageload-overlay" data-opening="M 0,0 c 0,0 63.5,-16.5 80,0 16.5,16.5 0,60 0,60 L 0,60 Z">
				<svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" viewBox="0 0 80 60" preserveAspectRatio="none">
					<path d="M 0,0 c 0,0 -16.5,43.5 0,60 16.5,16.5 80,0 80,0 L 0,60 Z"/>
				</svg>
	</div><!-- /pageload-overlay -->
	<div id="pagewrap" class="pagewrap">
	<div class="container show" id="page-1">
		<nav-frame> 
		<core-submenu icon="expand-more" label="产品管理">
	
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
		</core-submenu> 
		</nav-frame>
		
		
		<div class="c-h-logout">
		   <template bind="{{logintitle}}" is="auto-binding" id="login_title">
			   <core-submenu
				icon="account-circle" label="{{logintitle}}" onclick="userlogin(this);"> 
				</core-submenu> 
			</template>
		</div>
	</div>
	
	      <div class="container" id="page-2">
				<div class="card">
				    <h1 class="header">车帮夫</h1>
				    <span style="display: block;color: rgb(255, 255, 255);font-size: 18px;opacity: 0.7;">请先登录</span>
				    <core-ajax class="ajaxloginform" 
				               handleAs="json"
				               url="../api/weblogin" 
				               method="post">
				    </core-ajax>
				    <core-ajax class="ajaxlogoutform" 
				               handleAs="json"
				               url="../api/weblogout" 
				               method="post">
				    </core-ajax>
				    <template bind="{{user}}" is="auto-binding" id="login_form">
				    
					<paper-input label="电话" class="login-input" type="number" inputValue="{{user.mobile}}"
						placeholder="电话"></paper-input>
					<paper-input label="密码" class="login-input"  type="password" inputValue="{{user.password}}"
						placeholder="密码"></paper-input>
					</template>
					
					<a class="pageload-link" id="loginbutton">登陆</a>
					
			</div>
			</div><!-- /container -->
	</div>
	
	
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
      
        var pageWrap = document.getElementById( 'pagewrap' ),
					pages = [].slice.call( pageWrap.querySelectorAll( 'div.container' ) ),
					currentPage = 0,
      	            loader = new SVGLoader( document.getElementById( 'loader' ), { speedIn : 400, easingIn : mina.easeinout } );
      	var login_form=document.querySelector('#login_form');
		login_form.user={};
		
		var login_title=document.querySelector('#login_title');
		login_title.logintitle=localStorage.userlogin=="退出系统"?localStorage.userlogin:"登录系统";
		
		
		function showLoginPanel(){
			login_form.user={};
	    	loader.show();
	      	setTimeout( function() {
									//loader.hide();
									classie.removeClass( pages[ currentPage ], 'show' );
									// update..
									currentPage = currentPage ? 0 : 1;
									classie.addClass( pages[ currentPage ], 'show' );
	
								}, 1000 );
		};
		
		function hideLoginPanel(){
			loader.hide();
					classie.removeClass( pages[ currentPage ], 'show' );
					// update..
					currentPage = currentPage ? 0 : 1;
					classie.addClass( pages[ currentPage ], 'show' );
		};
      	//iframe子窗口调用
      	window.login=function(){
      	    showLoginPanel();
	    };
      	
		
		
		var loginbutton=document.getElementById('loginbutton');
		var ajaxloginform=document.querySelector('.ajaxloginform');
		var ajaxlogoutform=document.querySelector('.ajaxlogoutform');
		loginbutton.addEventListener("click", function(e) {
		    ajaxloginform.params={'mobile':login_form.user.mobile,'password':login_form.user.password};;
			ajaxloginform.go();
		});
		
		ajaxloginform.addEventListener("core-response",function(e){
			    var msgtoast= document.querySelector('#p-a-msg');
			    msgtoast.text=e.detail.response.message;
			    if(e.detail.response.success){
			        login_title.logintitle="退出系统";
			        localStorage.userlogin=login_title.logintitle;
			        hideLoginPanel();
			    }else{
			    	login_title.logintitle="登录系统";
			    	localStorage.userlogin=login_title.logintitle;
			    }
			    
			    msgtoast.show();
			});
			
			
		  this.userlogin=function(e) {
		  	if(localStorage["userlogin"]=="退出系统"){
		  		 ajaxlogoutform.go();
		  	}
		  	showLoginPanel();
		  };
      });
    </script>
    

</html>
