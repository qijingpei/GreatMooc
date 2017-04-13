<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>教师详情</title>
</head>
	
<body style="background-color:#eee;">
<br/><br/>
	<c:forEach items="${teacherList }" var="t">
  	<div style="width:1000px;height:200px;margin-left:168px;background-color:#FFF">
  		<div>
  			<a href="<c:url value='/TeacherServlet?method=load&tea_id=${t.tea_id }'/>">
  			<img width="260px" height="167px" style="margin-left:20px; margin-top:20px;" align="left" alt="简介图" src="${t.tea_bimg }"/>
  			</a>
  		</div>
  		<div>
  			<span style="align:left;">
  				<br/>
  				&nbsp;&nbsp;
  				<a href="<c:url value='/TeacherServlet?method=load&tea_id=${t.tea_id }'/>">${t.tea_name }</a>&nbsp;&nbsp;
  				<a href="<c:url value='/SchoolServlet?method=load&sch_id=${t.school.sch_id }'/>">${t.school.sch_name }</a> 
  				<br/><br/>
  				&nbsp;&nbsp; 介绍：${t.tea_desc}
  			</span>
  		</div>
  	</div>
  	<br/>
  	</c:forEach>
</body>
</html>
