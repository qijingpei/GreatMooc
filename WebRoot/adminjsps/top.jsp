<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>top</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/adminjsps/css/top.css'/>">

</head>

<body>
	<div id="top">
		<ul>
			<li><a href="<c:url value='/adminjsps/course/main.jsp'/>" target="body">课程管理</a>
				<ul>
					<li><a href="<c:url value='/adminjsps/course/main.jsp'/>" target="body">查看全部</a></li>
					<li><a href="<c:url value='/admin/AdminCourseServlet?method=addPre'/>"  target="body">添加课程</a></li>
				</ul></li>
			<li><a href="<c:url value='/admin/AdminCategoryServlet?method=findAll'/>" target="body">分类管理</a>
				<ul>
					<li><a href="<c:url value='/admin/AdminCategoryServlet?method=findAll'/>" target="body">查看所有</a></li>
					<li><a href="<c:url value='/adminjsps/category/add.jsp'/>" target="body">添加一级分类</a></li>

				</ul></li>
			<li><a href="<c:url value='/admin/AdminSchoolServlet?method=findAll'/>" target="body">学校管理</a>
				<ul>
					<li><a href="<c:url value='/admin/AdminSchoolServlet?method=findAll'/>" target="body">查找全部</a></li>
					<li><a href="<c:url value='/adminjsps/school/add.jsp'/>" target="body">添加学校</a></li>
				</ul></li>

			<li><a href="<c:url value='/admin/AdminTeacherServlet?method=findAll' />" target="body">教师管理</a>
				<ul>
					<li><a href="<c:url value='/admin/AdminTeacherServlet?method=findAll' />" target="body" >查看所有</a></li>
					<li><a href="<c:url value='/admin/AdminTeacherServlet?method=findAllSchool' />" target="body" >添加教师</a></li>
				</ul></li>
			<li><a target="body" href="<c:url value='/admin/AdminServlet?method=findAll'/>">管理员:${session_admin.adm_name}</a>
				<ul>
					<li><a target="body" href="<c:url value='/admin/AdminServlet?method=findAll'/>">查看所有管理员</a></li>
					<li><a target="body" href="<c:url value='/adminjsps/admin/desc.jsp'/>">修改密码</a></li>
					<li><a target="_top" href="<c:url value='/admin/AdminServlet?method=quit' />">退出</a></li>
				</ul>
			</li>			
		</ul>
	</div>
</body>
</html>
