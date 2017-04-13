<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<style type="text/css">
*{margin:0px;padding:0px;}
   body{
 background:#fffeee;
 }
</style>
	<title>我的课程</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">

function reinitIframe(){
         var iframe = document.getElementById("person_left");
         try{
             var bHeight = iframe.contentWindow.document.body.scrollHeight;   
             var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
             var height = Math.max(bHeight, dHeight);
             iframe.height =  height;
         }catch (ex){}
         }
window.onload = function(){
	window.setInterval("reinitIframe()",500);
};

function reinitIframe2(){
    var iframe = document.getElementById("person_right");
    try{
        var bHeight = iframe.contentWindow.document.body.scrollHeight;   
        var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
        var height = Math.max(bHeight, dHeight);
        iframe.height =  height;
    }catch (ex){}
    }
window.onload = function(){
window.setInterval("reinitIframe()",500);
};
</script>
</head>
  
<body>
	<!-- 顶部导航 -->
	<iframe src="<c:url value='/jsps/top.jsp'/> " width="100%" align="top" frameborder="0" height="60px"
		scrolling="no" name="menu" >	
	</iframe>
	<div>
	<!-- 左部菜单 -->	
	<iframe id="person_left" src="<c:url value='/UserServlet?method=findByUser_id'/>" width="25%"  align="left" 
		frameborder="0" height="100%" name="person_left" scrolling="no" marginheight="20px" onload="reinitIframe();">		
	</iframe>
	</div>
	
	<div>
	<!-- 右部菜单 -->
	<!-- reinitIframe()这个函数用来在iframe宽度变化时时对该frame的高度height进行调整 -->
	<!-- 下面这个链接应该改为：PersonServlet?method=findFollowCourse -->
	<iframe  id="person_right" width="75%"  height="100%"  align="right" frameborder="0" scrolling="no"
		src="<c:url value='/PersonServlet?method=findFollowCourse' />" name="person_right" onload="reinitIframe2();">
	</iframe>
	</div>
	</div>
	<div><br/></div>
	<!-- 底部导航 -->
	<iframe width="100%" align="bottom" frameborder="0" height="122px"
		src="<c:url value='/jsps/footer.jsp'/>" name="footer" scrolling="no">	
	</iframe>

</body>
</html>