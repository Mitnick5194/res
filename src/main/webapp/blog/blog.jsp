<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width , initial-scale=1">
<title>博客专栏</title>
<link href="../css/common.css" rel="stylesheet" type="text/css">
<link href="css/blog.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<style type="text/css">

	
</style>
	<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
</head>
<body>
	<div class="main">
		<nav class="min-width-client-header">
			<button id="slideOutTag" class="tag-btn">
				<span class="bar-icon"></span>
				<span class="bar-icon"></span>
				<span class="bar-icon"></span>
			</button>
		</nav>
		<!-- <nav class="nav fl-rl-a">
			<div class="menu-wrap" id="iMenus">
				<a class="btn-nav">菜单1</a>
				<a class="btn-nav">菜单2</a>
				<a class="btn-nav">菜单3</a>
				<a class="btn-nav">菜单4</a>
				<a class="btn-nav">菜单5</a>
				<a class="btn-nav">菜单6</a>
				<a class="btn-nav">菜单7</a>
				<a class="btn-nav">菜单8</a>
				<a class="btn-nav">菜单9</a>
				<a class="btn-nav">菜单10</a>
				<a class="btn-nav">菜单11</a>
				<a class="btn-nav">菜单12</a>
				<a class="btn-nav">菜单13</a>
				<a class="btn-nav">菜单14</a>
				<a class="btn-nav">菜单15</a>
				<a class="btn-nav">菜单16</a>
			</div>
		</nav> -->
		<jsp:include page="/nav.jsp"></jsp:include>
		<div id="iContainner" class="container fl-rl-a">
				<div id="iTags" class="left-tag">
		        	<div class="list-group">
				       	<a class="active">标签分类</a>
						<a>java(15)</a>
						<a >c(11)</a>
						<a >c++(4)</a>
						<a>php(3)</a>
						<a>ubuntu(21)</a>
						<a>linux(1)</a>
						<a>pyhont(2)</a>
						<a>tomcat(1)</a>
						<a>other(4)</a>
						<a>更多标签</a>
		    		</div>
				</div>
				<div class="center-main">
					<section class="content-dv">
			              <h2 class="content-dv-title" title="">
			              <a href="content.do?id=admin164a87c8e68&amp;&amp;op=visit">Linux 中内存 buffer 和 cache 的区别</a>
			              </h2>
			              <div class="summary">
							     细心的朋友会注意到,当你在Linux下频繁存取文件后,物理内存会很快被用光,当程序结束后,内存不会被正常释放,
							     而是一直作为caching.这个问题,貌似有不少人在问,不过都没有看到有什么很好解决的办法.那么我来谈谈这个问题。
						  </div>
						  <dl class="list-userbar">
						  		<dt><a href="https://www.baidu.com"><img class="user-header" src="../images/myQRCode.jpg" /></a></dt>
						  		<dd><a href="https://www.baidu.com">独孤怎会求败</a></dd>
						  		<dt class="interval"></dt>
						  		<dd class="col-gray">8小时前</dd>
						  		<dt class="interval"></dt>
						  		<dd class="tag"><a href="https://www.baidu.com">移动开发</a></dd>
						  		<dd class="interactive floatR">
						  			<a class="col-gray" href="https://www.baidu.com" ><span class="read-num">1000</span>阅读</a>
						  			<div class="interval"></div>
						  			<a class="col-gray" href="https://www.baidu.com"><span class="common-num">1</span>评价</a>
						  		</dd>
						  </dl>
		            </section>
					<section class="content-dv">
			              <h2 class="content-dv-title" title="">
			              <a href="content.do?id=admin164a87c8e68&amp;&amp;op=visit">Linux 中内存 buffer 和 cache 的区别</a>
			              </h2>
			              <div class="summary">
							     细心的朋友会注意到,当你在Linux下频繁存取文件后,物理内存会很快被用光,当程序结束后,内存不会被正常释放,
							     而是一直作为caching.这个问题,貌似有不少人在问,不过都没有看到有什么很好解决的办法.那么我来谈谈这个问题。
						  </div>
						  <dl class="list-userbar">
						  		<dt><a href="https://www.baidu.com"><img class="user-header" src="../images/myQRCode.jpg" /></a></dt>
						  		<dd><a href="https://www.baidu.com">独孤怎会求败</a></dd>
						  		<dt class="interval"></dt>
						  		<dd class="col-gray">8小时前</dd>
						  		<dt class="interval"></dt>
						  		<dd class="tag"><a href="https://www.baidu.com">移动开发</a><a href="https://www.baidu.com">移动开发</a><a href="https://www.baidu.com">移动开发</a><a href="https://www.baidu.com">移动开发</a></dd>
						  		<dd class="interactive floatR">
						  			<a class="col-gray" href="https://www.baidu.com" ><span class="read-num">1000</span>阅读</a>
						  			<div class="interval"></div>
						  			<a class="col-gray" href="https://www.baidu.com"><span class="common-num">1</span>评价</a>
						  		</dd>
						  </dl>
		            </section>
					<section class="content-dv">
			              <h2 class="content-dv-title" title="">
			              <a href="content.do?id=admin164a87c8e68&amp;&amp;op=visit">Linux 中内存 buffer 和 cache 的区别</a>
			              </h2>
			              <div class="summary">
							     细心的朋友会注意到,当你在Linux下频繁存取文件后,物理内存会很快被用光,当程序结束后,内存不会被正常释放,
							     而是一直作为caching.这个问题,貌似有不少人在问,不过都没有看到有什么很好解决的办法.那么我来谈谈这个问题。
						  </div>
						  <dl class="list-userbar">
						  		<dt><a href="https://www.baidu.com"><img class="user-header" src="../images/myQRCode.jpg" /></a></dt>
						  		<dd><a href="https://www.baidu.com">独孤怎会求败</a></dd>
						  		<dt class="interval"></dt>
						  		<dd class="col-gray">8小时前</dd>
						  		<dt class="interval"></dt>
						  		<dd class="tag"><a href="https://www.baidu.com">移动开发</a></dd>
						  		<dd class="interactive floatR">
						  			<a class="col-gray" href="https://www.baidu.com" ><span class="read-num">1000</span>阅读</a>
						  			<div class="interval"></div>
						  			<a class="col-gray" href="https://www.baidu.com"><span class="common-num">1</span>评价</a>
						  		</dd>
						  </dl>
		            </section>
					<section class="content-dv">
			              <h2 class="content-dv-title" title="">
			              <a href="content.do?id=admin164a87c8e68&amp;&amp;op=visit">Linux 中内存 buffer 和 cache 的区别</a>
			              </h2>
			              <div class="summary">
							     细心的朋友会注意到,当你在Linux下频繁存取文件后,物理内存会很快被用光,当程序结束后,内存不会被正常释放,
							     而是一直作为caching.这个问题,貌似有不少人在问,不过都没有看到有什么很好解决的办法.那么我来谈谈这个问题。
						  </div>
						  <dl class="list-userbar">
						  		<dt><a href="https://www.baidu.com"><img class="user-header" src="../images/myQRCode.jpg" /></a></dt>
						  		<dd><a href="https://www.baidu.com">独孤怎会求败</a></dd>
						  		<dt class="interval"></dt>
						  		<dd class="col-gray">8小时前</dd>
						  		<dt class="interval"></dt>
						  		<dd class="tag"><a href="https://www.baidu.com">移动开发</a></dd>
						  		<dd class="interactive floatR">
						  			<a class="col-gray" href="https://www.baidu.com" ><span class="read-num">1000</span>阅读</a>
						  			<div class="interval"></div>
						  			<a class="col-gray" href="https://www.baidu.com"><span class="common-num">1</span>评价</a>
						  		</dd>
						  </dl>
		            </section>
				</div>
				<div class="right-info">
					<div class="login-part">
						<div class="user-info">
							<h2>账号登录</h2>
							<input type="text" name="username" id="iUserName" placeholder="请输入用户名/邮箱/手机号" />
							<input type="password" name="password" id="iPassowrd" placeholder="请输入密码" />
							<div class="forget-password">
								<span>
									<input type="checkbox" id="iRememberMe"/>
									<label class="rememberMe-label" for="iRememberMe">下次自动登录</label>
								</span>
								<span class="forget-password-sp"><a href="#">忘记密码</a></span>
							</div>
							<div class="loginBtn">登录</div>
							<div class="register-dv">还没有账号？ <a class="register" href="#">立刻注册</a></div>
						</div>
					</div>
				</div>
			</div>
	</div>
	<script>
		var tagBtn = $("#slideOutTag");
		var containner = $("#iContainner");
		tagBtn.on("click" , function(){
			containner.toggleClass("active");
		})
		
		
	/* 	var jsonp = $("<script/>").attr("type" , "text/javascript").attr("src" ,"http://127.0.0.1:8088/res/nav.do?callback=test" );
		$("head").eq(0).append(jsonp); */
	</script>
	
</body>
</html>