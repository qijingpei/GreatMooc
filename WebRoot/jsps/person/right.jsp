<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人中心右侧</title>
<style type="text/css">
*{margin:0;padding:0; border:0}
body( font-size:14px;)
a{ text-decoration:none;}
.mycourse{
	width:800px;
	background:#fffeee;	
	margin-left:10%;
}
h2{ padding:20px; font-size:14p;}
.type{
	height:40px;
	background:#EFEFEF;
	color:#000033;
	border-bottom:#99FFFF solid 1px;
}
.c_type{
 color:#000033;
 font-family:"Microsoft YaHei UI", "华文行楷", "华文隶书", "华文宋体";
 font-weight:600;
 text-decoration:none;
 margin-left:20px;
 line-height:40px;
}
.courses{
	min-height:400px;
	border-radius: 1px;
    box-shadow: 1px 1px 15px #ccc;
    margin: 20px 16px 0 4px;
}
.courseOne{
	width:660px;
	height:140px;
	margin:20px 30px;
	background:#FFeeeC;
	padding:20px 30px;
}

.p{
	width:400px;
	float:right;
}
.go{
	height:30px;
	text-decoration:none;
	display:block;
	font-weight:600;
}
</style>
</head>

<body>
<div class="mycourse">
	<h2>我的课程</h2>
	<div class="type">
		<a class="c_type" href="<c:url value='/PersonServlet?method=findFollowCourse'/>">已关注</a>
		<a class="c_type" href="<c:url value='/PersonServlet?method=findLearningCourse'/>">正在学</a>
		<a class="c_type" href="<c:url value='/PersonServlet?method=findFinishedCourse'/>">已完成</a>
	</div>
	<div class="courses">
	<c:forEach items="${courseList}" var="course">
		<div class="courseOne">		
			<a target="_top" href="<c:url value='/jsps/course/cou_desc.jsp?cou_id=${course.cou_id }'/>" class=""><img src="<c:url value='/${course.cou_simg }'/>"/></a>
				<p class="p">
					<br/><br/>
					<a target="_top" href="<c:url value='/jsps/course/cou_desc.jsp?cou_id=${course.cou_id }'/>" class="go">${course.cou_name }</a>
					<!-- 
					<a target="_top" href="<c:url value='/jsps/teacher/tea_desc.jsp?tea_id=${course.teacher.tea_id }'/>" class="go">${course.teacher.tea_name }</a>
					<a target="_top" href="<c:url value='/jsps/school/sch_desc.jsp?sch_id=${course.school.sch_id }'/>" class="go">${course.school.sch_name }</a>
					 -->
					课时：${course.hour_length }小时${course.minu_length}分<br/>
					${course.learn_num}人正在学习
		
				</p>		
		</div>
	</c:forEach>
	</div>
</div>
</body>
</html>

