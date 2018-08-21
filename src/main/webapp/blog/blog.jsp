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
				       	<a class="active">哈哈哈哈哈</a>
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
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
					<p>2</p>
				</div>
				<div class="right-info">
					<p>3</p>
					<p>3</p>
					<p>3</p>
					<p>3</p>
					<p>3</p>
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