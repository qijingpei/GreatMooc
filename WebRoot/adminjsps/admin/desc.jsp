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

	<title>修改管理员信息</title>
	
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

.center
{
margin:0 auto;
width:500px;
height:400px;
background-color:#ccc;
border-radius: 10px;/*修改的部分圆角属性*/
}
.center:hover{
	box-shadow: -15px 15px 25px #888888;	
	}
</style>
<body>
  <div class="center">
  	<div style="position:relative;margin:0 auto;top:100px;">
<form style="margin:20px;" action="<c:url value='/admin/AdminServlet'/>" method="post" >
<p style="color: red; font-weight: 900">${msg }</p>
<input type="hidden" name="method" value="edit"/>
<input type="hidden" name="adm_id" value="${admin.adm_id}">
<input type="hidden" name="adm_pwd" value="${admin.adm_pwd}">
新的名字：<input type="text" name="newname" value="${admin.adm_name }"/>${errors.newname }<br/>
旧	  密码：<input type="password" name="oldpwd" /><br/>
新	  密码：<input type="password" name="newpwd1" />${errors.newpwd1 }<br/>
确认新密码：<input type="password" name="newpwd2" /><br/><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input  type="submit" value="完成" onclick="您确认要修改吗"/>
</form>
	</div>
</div>
</body>
</html>