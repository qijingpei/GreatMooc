<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
<style type="text/css">
*{margin:0;padding:0; border:0}
body{ font-size:14px;}
.user{
width:300px;
height:500px;
margin-left:20%;
}
.user-info{
height:200px;
width:280px;
padding:20px 0 0 20px;
}

.user-img{
border-radius:50px;
float:left;	
}

.name{
	width:100px;
	margin:20px;
	float:left
}
.word{
	clear:both;
	padding-top:20px;
	height:80px;
}
.my{
	height:230px;
	width:280px;
}
ul{list-style:none;
}

.Li{
	height:50px;
	width:250px;
	line-height:50px;
	border-top:#00FFFF solid 2px;
}
.Li img{
position:relative;
left:40px;
top:5px;}
.Li a{
text-decoration:none;
margin-left:50px;
font-family:"新宋体";
color:#003333;
font-weight:600;
}


.edit{width:300px;height:380px;
margin-top:20px;
margin-left:0px;
background:#eeffff;
 padding:30px 10px 0 10px;
 	
}
.inputDiv{position:relative;}
._name{width:200px;height:40px;border:1px solid #314D89;cursor:pointer;border-radius:4px 4px 0 0;}
label{display:inline-block;width:40px;text-align:center;color:#003333;}
.imgDiv{
	position:relative;
	width:300px;
	margin:30px 0;
}
.imgBtn{
	width:144px;
	height:34px;
	background:url(/mooc/jsps/images/上传文件.jpg) center no-repeat ;
	border:0;
}
.file{ position:absolute; top:0px; left:100px; height:34px; filter:alpha(opacity:0);opacity: 0;width:126px }
.txt{ height:30px;
 border:1px solid #cdcdcd;
 position:relative;
 width:143px;
 left:86px;
 }

.button{width:200px; height:40px;
  margin:30px 0 0px 40px;
  font-size:18px;font-weight:bold;color:#636363;
  }
.button:hover{background:-webkit-linear-gradient(top,#cfcfcf 0%,#f2f2f2 100%);
-webkit-animation:login 1500ms infinite alternate ease-in-out;}

</style>
<script type="text/javascript">
$(function() {
	$("#editDiv").css("display", "none");
	$("#user").css("display", "");
	// 操作和显示切换
	$("#edit").click(function() {
			$("#user").css("display", "none");
			$("#editDiv").css("display", "");
	});
	$("#cancel").click(function() {
		$("#editDiv").css("display", "none");
			$("#user").css("display", "");
	});
			
});
</script>

</head>

<body>
<div id="user" class="user">
	<div class="user-info">
		<img  class="user-img" width="100px" height="100px" src="<c:url value='/${session_user.user_img }'/>"/><!-- 头像 -->
		<h3 class="name">${session_user.username } </h3><!-- 昵称 -->
		<p class="word"><!-- 签名 -->
			<c:if test="${empty session_user.user_desc }">这位同学很懒，什么也没写</c:if>
			<c:if test="${!empty session_user.user_desc }">${session_user.user_desc }</c:if></p>
	</div>
	<div class="my">
		<ul >
			<li class="Li"><img src="<c:url value='/jsps/images/logo1.png'/>"/><a href="#">学习计划</a></li>
			<li class="Li"><img src="<c:url value='/jsps/images/logo1.png'/>"/><a target="person_right" href="<c:url value='/PersonServlet?method=findFollowCourse'/>">已关注课程</a></li>
			<li class="Li"><img src="<c:url value='/jsps/images/logo1.png'/>"/><a target="person_right" href="<c:url value='/PersonServlet?method=findLearningCourse'/>">正在学习课程</a></li>
			<li class="Li"><img src="<c:url value='/jsps/images/logo1.png'/>"/><a target="person_right" href="<c:url value='/PersonServlet?method=findFinishedCourse'/>">已完成课程</a></li>		
		
		   	<li class="Li" style="border-bottom:#00FFFF solid 2px; height:70px; line-height:70px;"><a href="#" id="edit">设置个人信息</a></li>
		   	${msg }	
		</ul>
	</div>
</div>

<div id="editDiv" class="edit">
 	<form action="<c:url value='/EditPersonServlet'/>" method="post" enctype="multipart/form-data">
	 <input type="hidden" name="user_id" value="${session_user.user_id}" />
	<div class="inputDiv">
	     <label>昵称:</label>
         <input class="_name" type="text" name="username" value="${session_user.username}"/>
		
	 </div>
	 <div class="inputDiv">
	     <label>个性签名:</label>
         <input class="_name" type="text" name="user_desc" value="${session_user.user_desc}"/>
		
	 </div>
	
     <div class="imgDiv">
	     <label style="width:80px;">上传头像:</label>
		  <input type='button' class="imgBtn"/>
         <input type="file" name="user_img" class="file" onchange=
		 "document.getElementById('user_img').value=this.value"/>
		 <input type='text' id='user_img' class='txt'/>
	 </div>
	 
	 
	<input id="button" class="button" type="submit" value="修改"></input>
	</form>
	
	<input id="cancel" class="button"  type="submit" value="取消" onclick="history.go(-1);"></input>
</div>
</body>
</html>

