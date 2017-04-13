<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
<head>


<title>main</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
<style type="text/css">
* {
	font-size: 10pt;
	padding: 0px;
	margin: 0px;
}

body {
	text-align: center;
	background-color: #eee;
}
.top {
	height: 150px;
}
.mid {
	height: 100%;
}

.mid_left {
	width: 310px;
	float: left;
	height: 800px;
	margin-left: 60px;
}

.mid_right {
	width: 900px;
	float: left;
	margin-top: 60px;
	margin-left: 20px;
	margin-bottom:40px;
	overflow: hidden;		
}
</style>

</head>
<body>
		<div class="mid">
			<div class="mid_left" style="margin-left:20px;">
				<iframe frameborder="0" width="100%" height="100%" scrolling="no"
					src="<c:url value='/admin/AdminCourseServlet?method=findCategoryAll'/>"
					name="mid_left"></iframe>
			</div>
			<div class="mid_right">
				<iframe frameborder="0" width="980px" scrolling="no" id="mid_right1"
					name="mid_right1" onload="javascript:reinitIframe();"
					src="<c:url value='/admin/AdminCourseServlet?method=findAll'/>"></iframe>
			</div>
		</div>

</body>
</html>
