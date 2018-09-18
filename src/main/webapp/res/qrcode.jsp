<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>二维码生成器</title>
<style type="text/css">
	body{background: #f0f0f0;}
	.text-wrap{display: inline-block;}
	.main_text {  width: 650px;height: 100%;  outline: 0; resize: none;   font-size: 16px;  padding: 10px; padding-right: 24px;}
	.qr-ret{float: right;	background: #fff;margin-right: 15px;}
	.img_wrap{width: 320px;margin: 0 auto;padding: 60px 0px;}
	.flexbox{display: flex;height: 280px;padding: 10px;}
	#genBtn{ background: #43a047; padding: 10px;border-radius: 3px; color: #fff; margin-left: 60px;	cursor: pointer;}
	.gen-qrcode{width: calc(100% - 1000px);line-height: 280px;}
</style>
</head>
<body>	
	<section class="flexbox">
		<div class="text-wrap">
			<textarea class="main_text" id="iContent" placeholder="请输入需要转换成二维码的内容"></textarea>
		</div>
		<div  class="gen-qrcode"><span  id="genBtn">生成二维码</span></div>
		<div class="qr-ret">
			<div class="img_wrap">
				<img id="iQrCode"  />
			</div>
		</div>
	</section>
	
	<script type="text/javascript">
	(function(){
		var protocol =location.protocol
		var host = location.host;
		var content = location.href;
		console.log(protocol+"//"+host);
		var src = protocol+"//"+host+"/res/genqrcode.do?content="+content;
		iQrCode.setAttribute("src" , src);
		genBtn.onclick = function(){
			var content = iContent.value;
			if(!content){
				alert("请输入需要生成二维码的内容");
				iContent.focus();
				return;
			}
			var src =  protocol+"//"+host+"/res/genqrcode.do?content="+content;
			iQrCode.setAttribute("src" ,src );
		}
	})();
	</script>
</body>
</html>