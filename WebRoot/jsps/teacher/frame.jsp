<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 <style type="text/css">
*{margin:0px;padding:0px;}
</style>
    <base href="<%=basePath%>">
    
    <title>GreatMooc--教师简介</title>
    
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
    var iframe = document.getElementById("teacher_frame2");
    try{
        //var bHeight = iframe.contentWindow.document.body.scrollHeight; 
        var bHeight = 0;
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
    <iframe  src="<c:url value='/jsps/top.jsp'/>" width="100%" height="60px"
		frameborder="0" scrolling="no" marginwidth="0" marginheight="0">
	</iframe>
	<!-- reinitIframe()这个函数用来在iframe宽度变化时时对该frame的高度height进行调整 -->
	<iframe id="teacher_frame2"  height="80%" src="<c:url value='/TeacherServlet?method=findAll'/>" 
		frameborder="0" scrolling="no" width="100%" height="80.5%" marginwidth="0" marginheight="0"
		onload="reinitIframe()"	>
	</iframe >
	<iframe  src="<c:url value='/jsps/footer.jsp'/>" width="100%" height="122px"
		frameborder="0" scrolling="no" marginwidth="0" marginheight="0">
	</iframe >
  </body>
</html>
