<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>top</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="background: rgb(78,78,78);color: white;">
<h1 style="text-align: center; ">GreatMooc在线学习平台后台管理</h1>
<p style="font-size: 11pt;">管理员：${session_admin.adm_name }   		
<a href="<c:url value='/admin/AdminServlet?method=load&adm_id=${ session_admin.adm_id}' />"  target="body" style="color=blue">修改密码</a>
<!-- 下面这个“查看所有”链接只给管理员看呦~ -->
<c:if test="${session_admin.issuper == true}"><a href="<c:url value='/admin/AdminServlet?method=findAll&adm_id=${ session_admin.adm_id}' />"  target="body" style="color=blue">查看所有管理员</a></c:if>
<a href="<c:url value='/admin/AdminServlet?method=quit' />" target="_top">退出</a>
</p>


  </body>
</html>
