<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页</title>
</head>
<body>

	<c:if test="${not empty info }">
		<h1>${info }</h1>
	</c:if>
	<form id="iForm" action="login.do"  method="post" onsubmit="return check()">
		<input type="hidden" value="${ref }" name="ref"/>
		<input type="hidden" value="login" name="op"/>
		用户名：<input type="text" name="name" id="iName" /><br>
		密码：<input type="password" name="password" id="iPassword"/><br>
		<input type="submit" id="iLogin" value="登录"/>
	</form>
	
	<script type="text/javascript">
	function check(){
		var name = iName.value;
		var password = iPassword.value;
		if(!name){
			alert("请输入用户名");
			return false;
		}
		if(!password){
			alert("请输入密码");
			return false;
		}
	}
/* 	iLogin.onclick = function(){
		/* location.href = "login.do?name="+name+"&password="+password+"&op=login"; */
	/*	iForm.submit();
	} */
		
	</script>
</body>
</html>