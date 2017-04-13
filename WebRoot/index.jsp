<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Mooc</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css"
	href="<c:url value='jsps/css/index.css'/>">

</head>

<body>
	<div id="Search">
		<form action="<c:url value='/jsps/course/searchRst.jsp'/>"
			method="get">
			<input type="text" name="cou_name" id="txt" /> <input type="submit"
				value="搜索" />
		</form>
		<div class="index">
			<p>
				<a href="<c:url value='/jsps/main.jsp'/>">查看全部课程</a>
			</p>
		</div>
	</div>
</body>
</html>
