<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<style type="text/css">
	body {background: rgb(255,255,255);}
</style>
<head>
	<base href="<%=basePath%>">

	<title>My JSF 'add.jsp' starting page</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
  <style type="text/css">
	body {background: rgb(255,255,255);}
</style>
<body>
<h1>添加管理员</h1>
    <p style="font-weight: 900; color: red">${msg }</p>
    <form action="<c:url value='/admin/AdminServlet' />" method="post" >
    <input type="hidden" name="method" value="add" />
    	管理员名称：<input style="width: 150px; height: 20px;" type="text" name="newname"/>${errors.newname }<br/>
    	密            码：<input style="width: 150px; height: 20px;" type="text" name="newpwd1"/>${errors.newpwd1 }<br/>
    	确    认密码：<input style="width: 150px; height: 20px;" type="text" name="newpwd2"/><br/>
    	是否为超级管理员：<input type="checkbox" name="issuper"/><br/>

    	<br/>
    	<input type="submit" value="添加管理员"/>
    </form>
</body>
</html>