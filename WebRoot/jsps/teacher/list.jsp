<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>教师简介</title>
	<meta name="Keywords" content=""/>
	<meta name="description" content=""/>
	<style type="text/css">
	*{margin:0px;padding:0px;}
		*{margin:0px;padding:0px;font-family:Arial;}
		body{background:#EEE;}
		#main{width:1360px;margin-left:50px; min-height:660px;}
		#main ul li{list-style-type:none;width:220px;height:300px;float:left;
			position:relative;box-shadow:0 0 10px #fdffff;margin:48px;}
        #info{opacity:0.1;}
       #info{width:150px;height:120px;background:#deddcd;border:5px solid #fff;
			position:absolute;right:10px;bottom:-50px;transform:rotateY(30deg);
			transition:1s;}
		#info h2{text-align:center;line-height:70px;color:#7a3f3a;font-size:20px;
			font-weight:normal;}
		#info p{padding:0 20px;font-size:14px;line-height:22px;}
		#info a{display:block;width:100px;height:30px;line-height:30px;text-align:center;
			margin:5px auto;border-radius:5px;background:#7a3f3a;color:#fff;
			font-size:14px;text-decoration:none;}

		#main ul li img{opacity:0.5;}
		#main ul li:hover img{opacity:1;}
		#main ul li:hover #info{opacity:1;}
		img{border:8px solid #fff;transition:1s;}
		#main ul li:hover #info{right:00px;bottom:0px;}
	</style>
</head>
<body>
	<div  id="main">
		<ul>   
		<c:forEach items="${teacherList }" var="t">
			 <li>
			 		<a href ="<c:url value='/TeacherServlet?method=load&tea_id=${t.tea_id }'/>">
			 		<!-- 注意！：这里与教学视频不同，不应该使用c标签的url -->
			 		<img width="220px" height="300px" src="${t.tea_bimg }" />
			 		</a>
				<div id="info">
					<h2><a href="<c:url value='/TeacherServlet?method=load&tea_id=${t.tea_id }'/>">${t.tea_name }</a></h2>
					<a href="<c:url value='/TeacherServlet?method=load&tea_id=${t.tea_id }'/>">${t.school.sch_name }</a> 
					<!-- <h3><a href="<c:url value='/TeacherServlet?method=load&tea_id=${t.tea_id }'/>">${t.tea_desc }</a></h3>-->
					<a href="<c:url value='/TeacherServlet?method=load&tea_id=${t.tea_id }'/>">More</a> 
				</div>
				</li>
			 </c:forEach>
		</ul>
	</div>
</body>
</html>
