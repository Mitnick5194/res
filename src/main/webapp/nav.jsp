<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <nav class="nav fl-rl-a">
		<div class="menu-wrap" id="iMenus">
		</div>
	</nav>
   <script>
   		 function drawNavigator(obj){
   			var sb = [];
   			$.each(obj , function(){
   				sb.push("<a class=btn-nav href="+this.url+" id="+this.id+">"+this.name+"</a>")
   			})
   			$("#iMenus").append(sb.join(""));
   			movecomming();
   		}
   		 
   	//移动端进入页面移动菜单 提示用户可以移动
 		function movecomming(){
 			var menu = $("#iMenus");
 			var menus = menu.find("a");
 			var width = menus.length * (menus.eq(0).width()+15);
 			var overflow = width - $(document).width();
 			if(overflow > 0){
 				menu.css("transform", "translateX(-"+overflow+"px)");
 				setTimeout(function(){
 					menu.css("transform", "translateX(-0)");
 				} , 500)
 			}
 		}
   		 
   		var domain = "http://www.ajie18.top:8080/";
   		if(window.location.host.indexOf("ajie18.top") == -1){
   			domain = "http://127.0.0.1:8088/";
   		}
   		var jsonp = document.createElement("script");
   		jsonp.type = "text/javascript";
   		jsonp.src=domain + "res/nav.do?callback=drawNavigator&cookies="+document.cookie; 
   		document.getElementsByTagName("head")[0].appendChild(jsonp); 
   </script>