<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width , initial-scale=1">
<title>http模拟请求</title>
<style type="text/css">
.flex{
	display: flex;
	align-items: center;
	white-space: no-wrap;
}

.sec{
	padding: 15px 20px;
	margin-bottom: 10px;
}

#iRequest{
	padding: 15px 0;
	width: 300px;
	background: #00e;
	border-radius: 5px;
	color: #fff;
	text-align: center;
	cursor: pointer;
}
</style>
</head>
<body>	
	<div class="container">
			<section class="sec flex ">
				<span>请输入请求url：</span>
				<textarea id="iURL" placeholder="请输入请求url"></textarea>
			</section>
			
			<section class="sec flex ">
				<span>请输入请求参数：</span>
				<textarea id="iParam" placeholder="请输入请求参数;格式实例：key1=value1&key2=value2"></textarea>
			</section>
			
			<section class="sec flex ">
				<span>cookies</span>
				<textarea id="iCookies"  placeholder="cookies"></textarea>
			</section>
			
			<section class="sec flex ">
				<span>header</span>
				<textarea id="iHeader" placeholder="header"></textarea>
			</section>
			
			<section class="sec flex ">
				<span>请求方式</span>
				<select id="iMethod">
					<option>GET</option>
					<option>POST</option>
				</select>
			</section>
			
			<div id="iRequest">请求</div>
	</div>
	
	<script type="text/javascript" src="http://47.106.211.15/js/jquery-1.9.1.js"></script>
	<script type="text/javascript">
		$("#iRequest").on("click" , function(){
			var url = $("#iURL").val();
			var params = $("#iParam").val();
			var cookies = $("#iCookies").val();
			var headers = $("#iHeader").val();
			var method = $("#iMethod").val();
			if(!url || !url.length){
				alert("请输入正确的url");
				return;
			}
			
			$.ajax({
				url: "aj_submitrequest.do",
				data:{
					url: url,
					params: params,
					cookies: cookies,
					headers: headers,
					method:method
				},
				success: function(data){
					
				}
			})
		})
	</script>
</body>
</html>