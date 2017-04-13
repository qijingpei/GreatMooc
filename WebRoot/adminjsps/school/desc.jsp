<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>top</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/school/desc.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/css/common.css'/>">
<script type="text/javascript"
	src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/adminjsps/js/school/edit.js'/>"></script>
<style type="text/css">
body {
	color:#000;
}
.addBody{height:470px;}
.doMore{
	position: fixed;
	width: 80px;
	height:122px;
	overflow:hidden;
	right: 4px;
	top:360px;
	background-color: #FF0000;
	border-radius: 5px;
	box-shadow: 1px 1px 10px #ccc;	
}
.do{
	width: 80px;
	height: 40px;
	border-bottom:#fff solid 2px;
	font-family: "Microsoft YaHei UI", "华文行楷", "华文隶书", "华文宋体";
	font-size: 15px;
	text-align: center;
	line-height: 40px;
	font-weight: 900;
	cursor: pointer;
}
.do a{	color: #FFF;}
.button {
	width: 140px;
	height: 40px;
	margin: 30px 0 0px 100px;
	font-size: 18px;
	font-weight: bold;
	color: #636363;
}
</style>
</head>
<body>
	<div id="desc_main">
		<div class="s_bimg">
			<img src="<c:url value='/${school.sch_bimg }'/>" alt="${school.sch_name }"/>
		</div>
		<div class="desc_contain">
			<div class="s_intro">
				<div class="s_simg">
					<img src="<c:url value='/${school.sch_simg }'/>" width="164px" height="60px"/>				
				</div>
				<div class="intro">
					<h2>${school.sch_name }</h2>
					<p>${school.sch_desc }</p>
				</div>
			</div>
			<h3 class="kecheng">
				<p>课程列表</p>
			</h3>
			<div class="courses">
			
			<c:forEach items="${school.courses }" var="course">
				<div class="course">
				<a href="#"><img src="<c:url value='/${course.cou_simg }'/>"/></a>
				<p class="cname">
				<a href="<c:url value='/jsps/course/cou_desc.jsp?cou_id=${course.cou_id }'/>" target="_blank">
							${course.cou_name }</a></p>
				<p class="related">教师：<a href="#">陈坤</a></p>
				<p class="related"><span>${course.learn_num }</span>正在学习 &nbsp;&nbsp; <span>${course.hour_length }课时</span></p>	
				</div>
			</c:forEach>
				
			</div>
			<h3 class="kecheng" style="height:40px; line-height:40px;">
				<p>教师列表</p>
			</h3>
			<div class="teachers">
			
			<c:forEach items="${school.teachers }" var="teacher">
				<div class="teacher">
					<div class="t_simg">
						<a href="#"><img src="<c:url value='/${teacher.tea_simg }'/>" width="100px" height="100px"/></a>
					</div>
					<div class="t_intro">
						<h4><a href="#">${teacher.tea_name }</a></h4>
						<p>${teacher.tea_desc }</p>
					</div>
				</div>
			</c:forEach>
			</div>
		</div>
	</div>
	
	<div id="formDiv" class="addBody" style="padding-top:30px;">
		<div class="msg">
			<h3></h3>
		</div>
		<form action="<c:url value='/admin/AdminSchoolServlet'/>" method="post">
			<input type="hidden" name="method" value="edit"/>
			<input type="hidden" name="sch_id" value="${school.sch_id }"/>
			<div class="inputDiv">
				<label>学校名称:</label> <input class="_name" type="text"
					name="sch_name" value="${school.sch_name }"/>

			</div>

			<div class="inputDiv">
				<label>学校简介:</label><br>
				<textarea class="desc" name="sch_desc" >${school.sch_desc }</textarea>
			</div>

			<input class="button" type="submit" value="修改"></input>

		</form>
	</div>
	
	
	<div id="doMore" class="doMore">
		<p class="do" ><a href="#" id="edit">修改信息</a></p>
		<p class="do" ><a href="<c:url value='/admin/AdminSchoolServlet?method=delete&sch_id=${school.sch_id }'/>" id="delete">删除学校</a></p>
		<p class="do" ><a href="#" id="turn">返回顶部</a></p>
	</div>
	<script type="text/javascript">
window.onload=function(){
var turn=document.getElementById('turn');
turn.onclick=function()
{
 $(parent.window).scrollTo(0,0);

}

}
</script>
</body>
</html>