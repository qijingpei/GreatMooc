<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>列表</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/course/list.css'/>">
<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jsps/js/course/list.js'/>"></script>

</head>
<body>
<c:if test="${addType eq 3 }">
<h2 style="margin:0 0 10px 30px;">关键词:&nbsp;<b style="color:red;font-weight:800;font-size:18px;">${cou_name }</b>&nbsp;搜索结果</h2>
</c:if>
<div class="list">	

<c:forEach items="${courses }" var="course">
	<div class="course">
		<a href="<c:url value='/jsps/course/cou_desc.jsp?cou_id=${course.cou_id }'/>"  target="_blank"><img src="<c:url value='/${course.cou_simg }'/>" width="240px" height="135px"/></a>
		<p class="cname"><a href="<c:url value='/jsps/course/cou_desc.jsp?cou_id=${course.cou_id }'/>" target="_blank">${course.cou_name }</a></p>
		<p class="related">教师：<a href="<c:url value='/jsps/teacher/tea_desc.jsp?&tea_id=${course.teacher.tea_id }'/>"  target="_blank">${course.teacher.tea_name }</a></p>
		<p class="related"><a href="<c:url value='/jsps/school/sch_desc.jsp?sch_id=${course.school.sch_id }'/>" target="sch_list">${course.school.sch_name }</a></p>
		<p class="related"><span>${course.learn_num }</span>正在学习 &nbsp;&nbsp; <span>${course.hour_length }课时</span></p>	
	</div>
</c:forEach>
</div>
<c:choose>
		<c:when test="${addType eq 1 }">
			<span id="addMore" class="add">点击加载更多</span>
		</c:when>
		<c:when test="${addType eq 2 }">
			<span id="addMoreByCate" class="add">点击加载更多</span>
			<input type="hidden" name="cate_id" value="${cate_id }"/>
		</c:when>
		<c:when test="${addType eq 3 }">
			<span id="addMoreByCname" class="add">点击加载更多</span>
			<input type="hidden" name="cou_name" value="${cou_name }"/>
		</c:when>
	</c:choose>



</body>
</html>