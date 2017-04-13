<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>一级分类</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" type="text/css"
	href="<c:url value='/jsps/css/category/cate.css'/>">
<script type="text/javascript"
	src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jsps/js/left.js'/>"></script>

</head>

<body>

	<div id="cate_list" class="menu_list">
		<ul>
			<li class="all_cou"><a href="<c:url value='/admin/AdminCourseServlet?method=findAll'/>" target="mid_right1">全部课程</a></li>
			<c:forEach items="${parents }" var="parent">
			<li>
				<p class="cate_1">${parent.cate_name }</p>
				<div class="cate_2">
					<c:forEach items="${parent.children }" var="child"> 
						<a href="<c:url value='/admin/AdminCourseServlet?method=findByCategory&cate_id=${child.cate_id }'/>" target="mid_right1">${child.cate_name }</a>
					</c:forEach>
				</div>
			</li>
			</c:forEach>
	</div>

</body>
</html>
