<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">

	<title>查看所有管理员</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {background: rgb(255,255,255);}
</style>
</head>
  <h3 align="center">管理员列表</h3>
  <table border="1" width="70%" align="center">
  	<tr>
		<th>管理员姓名</th>
		<th>是否为超级管理员</th>
		<th>操作</th>
	</tr>
<c:forEach items="${adminList}" var="admin" >
	<tr>	
		<td>${admin.adm_name }</td>
		<td>${admin.issuper }</td>
		<td>
			<a href="<c:url value='/admin/AdminServlet?method=delete&adm_id=${admin.adm_id }&sessionadmin_id=${session_admin.adm_id }'/>">删除</a>
			<a href="<c:url value='/admin/AdminServlet?method=levelup&adm_id=${admin.adm_id }'/>">提升为超级管理员</a>
			<a href="<c:url value='/admin/AdminServlet?method=leveldown&adm_id=${admin.adm_id }'/>">降至普通管理员</a>
		</td>
	</tr>	
</c:forEach>
	<tr>
		<td></td>
		<td></td>
		<td><a href="<c:url value='/adminjsps/admin/add.jsp'/>">添加管理员</a></td>
	</tr>
  </table>
<body>
	
</body>
</html>