<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>列表</title>

<!--js css-->
<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/user/login.css'/>">
<script type="text/javascript" src="<c:url value='/jsps/js/jquery-1.5.1.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jsps/js/login.js'/>"></script>

 <style type="text/css">
  *{margin:0px;padding:0px;}
 body{background:#EEE;}
  .main{
  margin-left:0px;
  margin-top:50px;
 
  }
  .top{
  border-bottom:1px solid #000000;
  width:100%;
  height:50px;
margin-left:20px;
  
  }
  .main .top span{
  font-family:"微软雅黑";
  font-size:25px;
  padding-left:20px;
  padding-top:10px;
   }
   .image img{
   margin-left:500px;
   }
   .medium{
  width:1360;

  }
.medium h1{
  font-family:"微软雅黑";
  font-size:20px;
  padding-top:20px;
  padding-left:600px;
  }
  .medium p{
  font-family:"微软雅黑";
  font-size:20px;
  padding-left:320px;
  padding-top:30px;
  }
  </style>
 </head>

 <body>
   <div class="main">
      <div class="top">
	  <img  src="<c:url value='/jsps/images/logo.png'/>"></img>
      <span>邮箱验证:</span>
      </div>
	  <div class="image">
	  <img src="<c:url value='/jsps/images/email.png'/>"></img>
	  </div>
	  <div class="medium">
	  <h1>注册成功!</h1>
	  <p> 我们已经向您的注册邮箱发送了验证邮件，请到您的注册邮箱激活您的账号！</p>
	  </div>
  </div>
 </body>
</html>