<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>教师界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		font-size: 10pt;
	}
	.icon {
		margin:10px;
		border: solid 2px gray;
		width: 160px;
		height: 180px;
		text-align: center;
		float: left;
	}
</style>
  </head>
  
  <body>
<!--检验教师列表是否为空：如果空则提示 <c:if test="teacherList==null">现在还没有教师，等待管理员添加</c:if> -->
<c:forEach items="${teacherList }" var="teacher">
  <div class="icon">
    <a href="<c:url value='/admin/AdminTeacherServlet?method=load&tea_id=${teacher.tea_id }'/>"><img width="130px" height="130px" src="<c:url value='/${teacher.tea_simg }'/>" border="0"/></a>
      <br/>
	${teacher.school.sch_name } </br>
   	<a href="<c:url value='/admin/AdminTeacherServlet?method=load&tea_id=${teacher.tea_id }'/>">${teacher.tea_name }</a>
  </div>
</c:forEach>

  
  </body>
 
</html>

