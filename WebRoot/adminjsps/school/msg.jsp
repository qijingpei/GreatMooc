<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>My JSP 'mgs.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" type="text/css" href="styles.css">

</head>
<style type="text/css">
input{background-color:#eee;}
.Body {
	width: 500px;
	height: 200px;
	background: #333;
	margin: 0px auto;
	margin-top:40px;
	border-radius: 5px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.4);
	padding: 10px 30px 0 30px;
	background: -webkit-radial-gradient(center, ellipse cover, #6A6A6A 0%, #1A1A1B 100%);
	background: -moz-radial-gradient(center, ellipse cover, #6A6A6A 0%, #1A1A1B 100%);
	background: -o-radial-gradient(center, ellipse cover, #6A6A6A 0%, #1A1A1B 100%);
}

.msg {
	height: 40px;
	margin-left: 16px;
	color:#EFEFEF;
}
</style>
<body>
	<div class="Body">
		<h2 class="msg">${msg }</h2>
		<a href="<c:url value='/admin/AdminSchoolServlet?method=findAll'/>" style="width:100px;height:60px;border:#aaa solid 1px;background:#DEDEDE; text-decoration:none;">点击查看</a>
	</div>
	
</body>
</html>
