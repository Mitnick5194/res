<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width , initial-scale=1">
<title>博客专栏</title>
<link href="../css/common.css" rel="stylesheet" type="text/css">

<style type="text/css">
/* 如果不设置overflow:hidden 会出现横向滚动条 */
html,body{overflow:hidden;overflow-y:auto;}
	.min-width-client-header{
		height: 50px;
		line-height: 50px;
		background: #fff;
	}
	.min-width-client-header .tag-btn{
	    margin: 10px;
	    padding: 6px 7px;
	    border: 1px solid #eee;
		background-color: #fafafa;
		border-radius: 3px;
	}
	.min-width-client-header .tag-btn:focus{
		outline: none;
	}
	.min-width-client-header .tag-btn .bar-icon{
		width: 22px;
		height: 2px;
		border-radius: 1px;
		display: block;
		background-color: #bbb;
	}
	.min-width-client-header .tag-btn .bar-icon + .bar-icon{
		 margin-top: 5px;
	}
	.nav{
		background-color: #eee;
		height: 50px;
		line-height: 50px;
	}
	.nav ul{
		list-style: none;
	}
	.nav li{
		    display: inline-block;
		    height: 35px;
		    line-height: 35px;
		    padding: 0 10px;
	}
	.container{
		margin-top: 15px;
		text-align: center;
		position: relative;
	}	
	.left-tag{
		width: 250px;
		position: absolute;
		top: -65px;
		left: -250px;
		transition: all .25s ease-out;
		z-index: 1001; 
	}
	.left-tag.slide-control{
		left: 0;
	}
	.container{
		position: relative;
		left: 0;
		transition: all .25s ease-out;
	}
	.container.active{
		left: 200px;
	}
	
	
	.right-info{
		display: none;
	}
	
	.list-group{
		border: 1px solid #eee;
		border-radius: 3px; 	
		backgound-color: #fff;
	}
	.list-group a{
		display: inline-block;
		width: 100%;
		padding: 10px 0;
		text-align: center;
	}
	.list-group a + a{
		border-top: 1px solid #eee;
	}
	.list-group .active{
		background-color: #337ab7;
		color: #fff;
	}
	
	
	 @media screen and  (min-width: 768px){
		.nav{
			width: 750px;
			background: red;
		}
		
	}	@media screen and  (min-width: 992px){
		.nav{
			width: 970px;
			background-color: #eee;
		}
	}  @media screen and  (min-width: 1200px){
		.min-width-client-header{
			display: none;
		}
		
		.nav{
			width: calc(100% - 120px);
			width:-webkit-calc(100% - 120px);
			background-color: #eee;
		}
		.container{
			width: 1170px;
			display: flex;
		}
		.left-tag{
			width: 200px;
			position: unset;
		}
		.right-info{
			width: 200px;
			float: right;
			display: inline-block;
		}
		.center-main{
			width: calc(100% - 600px);
		}
	}  
	 
		.fl-rl-a{
			padding-right: 15px;
			padding-left: 15px;
			margin-right: auto;
			margin-left: auto;
		}
	
	
</style>
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
		<nav class="nav fl-rl-a">
			<ul class="nav_ul">
				<li>菜单1</li>
				<li>菜单2</li>
				<li>菜单3</li>
				<li>菜单4</li>
				<li>菜单5</li>
				<!-- <li>菜单6</li>
				<li>菜单7</li> -->
			</ul>
		</nav>
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
	<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
	<script>
		var tagBtn = $("#slideOutTag");
		var containner = $("#iContainner");
		tagBtn.on("click" , function(){
			containner.toggleClass("active");
		})
	</script>
</body>
</html>