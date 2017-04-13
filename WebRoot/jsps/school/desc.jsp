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

</head>
<body>
	<div id="desc_main">
		<div class="s_bimg">
			<img src="<c:url value='/${school.sch_bimg }'/>" width="1180px" height="280px" alt="${school.sch_name }"/>
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
				<a href="<c:url value='/jsps/course/cou_desc.jsp?cou_id=${course.cou_id }'/>" target="_blank"><img src="<c:url value='/${course.cou_simg }'/>"/></a>
				<p class="cname">
				<a href="<c:url value='/jsps/course/cou_desc.jsp?cou_id=${course.cou_id }'/>" target="_blank">
							${course.cou_name }</a></p>
							<br/>
				<!-- 
				<p class="related">教师：<a href="<c:url value='/jsps/teacher/tea_desc.jsp?&tea_id=${course.teacher.tea_id }'/>"  target="_blank">陈坤</a></p>
				 -->
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
						<a href="<c:url value='/jsps/teacher/tea_desc.jsp?&tea_id=${teacher.tea_id }'/>"  target="_blank"><img src="<c:url value='/${teacher.tea_simg }'/>" width="100px" height="100px"/></a>
					</div>
					<div class="t_intro">
						<h4><a href="<c:url value='/jsps/teacher/tea_desc.jsp?&tea_id=${teacher.tea_id }'/>"  target="_blank">${teacher.tea_name }</a></h4>
						<p>${teacher.tea_desc }</p>
					</div>
				</div>
			</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>