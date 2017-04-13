<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>C-desc</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/jsps/css/course/desc.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/adminjsps/css/common.css'/>">
<script type="text/javascript"
	src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/adminjsps/js/course/edit.js'/>"></script>
<style type="text/css">
.addBody{height:470px;}
.doMore{
	position: fixed;
	width: 80px;
	height:122px;
	right: 4px;
	top:360px;
	background-color: #FF0000;
	border-radius: 5px;
	box-shadow: 1px 1px 10px #ccc;	
}
.do{
	width: 80px;
	height: 40px;
	border-bottom:#fff solid 2px;
	font-family: "Microsoft YaHei UI", "华文行楷", "华文隶书", "华文宋体";
	font-size: 15px;
	text-align: center;
	line-height: 40px;
	font-weight: 900;
	cursor: pointer;
}
.do a{	color: #FFF;}
.button {
	width: 140px;
	height: 40px;
	margin: 30px 0 0px 100px;
	font-size: 18px;
	font-weight: bold;
	color: #636363;
}
</style>
</head>

<body>
	<div id="desc_main">
		<div class="c_desc">
			<div class="c_img">
				<img src="<c:url value='/${course.cou_bimg }'/>" alt="${course.cou_name }" />
			</div>
			<div class="c_state">
				<ul>
					<li><span>学习人数：</span><b>${course.learn_num }</b></li>
					<li><span>课程时长：</span><b>${course.hour_length }</b>小时<b>${course.minu_length
							}</b>分</li>
					<li><span>已学时长：</span><b>0</b>小时<b>0</b>分</li>
				</ul>
			</div>
			<div class="c_do">
				<div style="background-color:#000000;">
					<a href="#" class="concern"><img
						src="<c:url value='/jsps/images/concern.png'/>" /><span>关注课程</span></a>
				</div>
				<div style="clear:both;">
					<a href="#" class="start">开始学习</a>
				</div>
			</div>
		</div>
		<div class="c_info">
			<div class="s_img">
				<a href="#" title="${course.school.sch_name }"><img
					src="<c:url value='/${course.school.sch_simg }'/>" alt="${course.school.sch_name }"/></a>
			</div>
			<div class="c_intro">
				<h3>课程介绍</h3>
				<p>${course.cou_desc }</p>
			</div>
		</div>
		<div class="c_related">
			<div class="chapters">

				<c:forEach items="${course.chapters }" var="chapter">
				<div class="chapter">
					<p class="charter-name">
					  <img  src="<c:url value='/jsps/images/logo_s.png'/>"/>
					  第${chapter.chap_num }章: ${chapter.chap_name }
					</p>
					<ul>
						<c:forEach items="${chapter.videos }" var="video">
<!-- userid -->						 <li><a href="<c:url value='/playVideo.action?id=${video.vid_id}&userId=11111111'/>">${video.vid_num }: ${video.vid_name }</a></li>
						 <c:choose>
						 	<c:when test="${video.learned eq true }">
						 		<span class="learn">已学习</span>
						 	</c:when>
						 	<c:otherwise>
						 		<span  class="learn">未学习</span>
						 	</c:otherwise>
						 </c:choose>
						 
						</c:forEach>
					 </ul>
				  </div>
				 </c:forEach>
			</div>
			<div class="c_teacher">
				<h2>授课教师</h2>
				<a class="tea_img" href="#"> <img
					src="<c:url value='/${course.teacher.tea_simg }'/>" width="100px"
					height="100px" />
					<h3>${course.teacher.tea_name }</h3>
				</a>
				<p>${course.teacher.tea_desc }</p>
			</div>
		</div>
	</div>

	<div id="formDiv" class="addBody" style="padding-top:30px;">
		<form action="<c:url value='/admin/AdminCourseServlet'/>" method="post">

			<input type="hidden" name="cou_id"  value="${course.cou_id }"/>
			<input type="hidden" name="method"  value="edit"/>
			
			<div class="inputDiv">
				<label>课程名称:</label> 
				<input class="_name" type="text" name="cou_name" value="${course.cou_name }"/>
			</div>
			<div class="selectDiv">
				<select class="select" id="sch_id" name="sch_id" style="margin-right:5px;" onchange="loadTeacher()">
					<option>===请选择大学===</option>
					<option>无</option>
					
					<c:forEach items="${schools }" var="school">
					<option value="${school.sch_id }" <c:if test="${course.school.sch_id eq school.sch_id}">selected='selected'</c:if>>${school.sch_name }</option>
					</c:forEach>
					
				</select> 
				<select class="select" id="tea_id" name="tea_id">
					<option>===请选择教师===</option>
					<option>无</option>
					<c:forEach items="${teachers }" var="teacher">
						<option value="${teacher.tea_id }" <c:if test="${course.teacher.tea_id eq teacher.tea_id }">selected='selected'</c:if>>${teacher.tea_name }</option>
					</c:forEach>
					 
				</select>
			</div>
			<div class="selectDiv">
				<select class="select"  id="pcate_id" name="pcate_id" style="margin-right:5px;" onchange="loadChildren()">
					<option>==请选择一级分类==</option>
					<c:forEach items="${parents }" var="parent">
			    		<option value="${parent.cate_id }" <c:if test="${course.category.parent.cate_id eq parent.cate_id }">selected='selected'</c:if>>${parent.cate_name }</option>
					</c:forEach>
				</select> 
				<select class="select" id="cate_id" name="cate_id">
					<option>==请选择二级分类==</option>
					<option>无</option>
					<c:forEach items="${children }" var="child">
						<option value="${child.cate_id }" <c:if test="${course.category.cate_id eq child.cate_id}">selected='selected'</c:if>>${child.cate_name }</option>
					</c:forEach>
				</select>
			</div>
			<div class="inputDiv">
				<label>课程简介:</label><br>
				<textarea class="desc" name="cou_desc">${course.cou_desc }</textarea>
			</div>

			<input class="button" type="submit" value="修改"></input> 
			<input class="button" type="button" value="返回首页"
				onclick="window.history.go(-2);" style="margin-left:20px;"></input>


		</form>
	</div>

	<div id="doMore" class="doMore">
		<p class="do" ><a href="#" id="edit">修改信息</a></p>
		<p class="do" ><a href="<c:url value='/admin/AdminCourseServlet?method=delete&cou_id=${course.cou_id }'/>" id="delete">删除课程</a></p>
		<p class="do" style="border:0;"><a href="<c:url value='/admin/AdminChapterServlet?method=findAll&cou_id=${course.cou_id }'/>">章节操作</a></p>
	</div>
</body>
</html>