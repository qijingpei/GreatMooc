<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录</title>

<!--js css-->
<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/user/login.css'/>">
<script type="text/javascript" src="<c:url value='/js/jquery-1.5.1.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jsps/js/user/login.js'/>"></script>
<style type="text/css">
 .adminname{
width: 260px;
border: 1px solid #314D89;
padding: 15px 20px;
cursor: pointer;
}
</style>
</head>

<body>
    <h2>${msg }</h2>
<div class="login">
	<form id="submitBtn" class="login-form" action="<c:url value='/admin/AdminServlet'/>" method="post">
		<input type="hidden" name="method" value="admin_login"/>
	   	<div class="inputDiv">
			<input id="username"  name="adm_name" class="input adminname" value="${errors.username }" placeholder="用户名:"/>
		</div>
		<div class="inputDiv">
        	<input id="password" type="password" name="adm_pwd" class="input" value="${errors.password }" placeholder="密码:"/>
        </div>
        <!-- 
        <div class="inputDiv">
			<input id="verifyCode" type="check" name="verifyCode" class="input" placeholder="验证码:"/>
			<img id="vCode" src="<c:url value='/VerifyCodeServlet'/>"/>
		</div>
		
		<div style="height:40px;">
			<span id="verifyCodeError" class="error">${errors.verifyCode }</span>
			<a class="change" href="javascript:_change()">看不清，换一张</a>
		</div>
		 -->
        <input type="submit" value="进入后台"></input>
	</form>
	<!-- <p><a href="<c:url value='/jsps/user/regist.jsp'/>">免费注册</a></p>
<p><a href="#">忘记密码？</a></p>
 		-->
</div>

</body>
</html>