<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>add</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/css/common.css'/>">
<style type="text/css">
.addBody{height: 520px;}
</style>

</head>

<body>
	<div class="addBody">
		<div class="msg">
			<h3>添加学校</h3>
		</div>
		<form action="<c:url value='/admin/AdminAddSchoolServlet'/>"
			method="post" enctype="multipart/form-data">
			<div class="inputDiv">
				<label>学校名称:</label> <input class="_name" type="text"
					name="sch_name" />

			</div>
			<div class="imgDiv">
				<label>学校大图:</label> <input type='button' class="imgBtn" /> <input
					type="file" name="sch_bimg" class="file" size="28"
					onchange="document.getElementById('sch_bimg').value=this.value" />
				<input type='text' id='sch_bimg' class='txt' />
			</div>
			<div class="imgDiv">
				<label>学校小图:</label> <input type='button' class="imgBtn" /> <input
					type="file" name='sch_simg' class="file" size="28"
					onchange="document.getElementById('sch_simg').value=this.value" />
				<input type='text' id='sch_simg' class='txt' />

			</div>
			<div class="inputDiv">
				<label>学校简介:</label><br>
				<textarea class="desc" name="sch_desc"></textarea>
			</div>

			<input class="button" type="submit" value="添加"></input>

		</form>
	</div>
</body>
</html>
