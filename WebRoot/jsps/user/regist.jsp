<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/user/regist.css'/>"/>
	<script type="text/javascript" src="<c:url value='/js/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jsps/js/user/regist.js'/>"></script>

  </head>
 <body>
 <div class="whole">
  <form id="submitBtn" action="<c:url value='/UserServlet'/>" method="post"><!--可让用户输入的表单-->
  	<input type="hidden" name="method" value="regist"/>
    <div id="email-element">
	     <label for="email">电子邮箱:</label>
         <input id="email" class="input" type="text" name="email" value="${form.email }"/>
		 <span class="spanError" id="emailError">${errors.email }</span>
	 </div>
     <div id="uname-element">
	     <label for="uname">昵称:</label><!--为input元素定义标签-->
         <input id="username" class="input" type="text" name="username" value="${form.username }"/><!--规定用户可输入数据的输入字段-->
		 <span class="spanError" id="usernameError">${errors.username }</span>
	 </div>
	 <div id="upass-element">
	     <label for="upass">密码:</label><!--for是属性标签顾名思义是为了实现什么-->
         <input id="password" class="input" type="password" name="password" value="${form.password }"/>
		 <span class="spanError" id="passwordError">${errors.password }</span>
	 </div>
	 <div id="repass-element">
	     <label for="repass">重复密码:</label><!--for是属性标签顾名思义是为了实现什么-->
         <input id="confirmpass" class="input" type="password" name="confirmpass" value="${form.confirmpass }"/>
		 <span class="spanError" id="confirmpassError">${errors.confirmpass }</span>
	 </div>
       <div>
	     <label>性别：</label>
		     <input  class="gender" id="male" type="radio" name="sex" value="男"checked="checked"></input><!--checked="checked"是默认第一个选项。-->
		 <label class="gender" for="male">男</label>
		     <input class="gender" id="female" type="radio" name="sex" value="女"></input>
		 <label class="gender" for="female">女</label>
	   </div>
       
	   
	
	 <div class="check">
	 <label for="check">验证码:</label>
	 <input id="verifyCode" class="input" type="text" name="verifyCode">
	 
	 <span class="verifyCodeImg">
          <img id="vCode" width="100px" height="40px" src="<c:url value='/VerifyCodeServlet'/>"/>
     </span>
     <span class="spanError" id="verifyCodeError">${errors.verifyCode }</span>
	 <a href="javascript:_change()">看不清，换一张</a>
	 </div>
	  <div id="btn-element">
		<input  id="submitButton" type="submit" value="注册"></input><!--提交表单时会发生submit函数。-->
		
		</div>
     
  </form>
  </div>
 </body>
</html>

