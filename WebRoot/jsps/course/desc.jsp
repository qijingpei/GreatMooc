<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GreatMooc-课程介绍</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/course/desc.css'/>">
</head>

<body>
<div id="desc_main">
	<div class="c_desc">
		<div class="c_img"><img src="<c:url value='/${course.cou_bimg }'/>" alt="${course.cou_name }"/></div>
		<div class="c_state">
			<ul>
				<li><span>学习人数：</span><b>${course.learn_num }</b></li>
				<li><span>课程时长：</span><b>${course.hour_length }</b>小时<b>${course.minu_length }</b>分</li>
				<li><span>已学时长：</span><b>0</b>小时<b>0</b>分</li>
			</ul>
		</div>
		<div class="c_do">
			<div style="background-color:#000000;">
				<a href="<c:url value='/PersonServlet?method=notice&user_id=${session_user.user_id }&cou_id=${course.cou_id }'/>" 
				onclick="confirm('关注成功！')" class="concern"><img src="<c:url value='/jsps/images/concern.png'/>"/><span>关注课程</span></a>
			</div>
			<div style="clear:both;">
				<a  href="#aaa" class="start">开始学习</a>
			</div>
		</div>
	</div>
	<div class="c_info">
		<div class="s_img">
			<a href="<c:url value='/SchoolServlet?method=load&sch_id=${course.school.sch_id }'/>" title="${course.school.sch_name }"><img src="<c:url value='/${course.school.sch_simg }'/>"/></a>
		</div>
		<div class="c_intro">
			<h3>课程介绍</h3>
			<p>${course.cou_desc }</p>
		</div>
	</div>
	<div class="c_related">
		<div class="chapters" id="aaa" >
				<c:forEach items="${course.chapters }" var="chapter">
				<div class="chapter">
					<p class="charter-name">
					  <img  src="<c:url value='/jsps/images/logo_s.png'/>"/>
					  第${chapter.chap_num }章: ${chapter.chap_name }
					</p>
					<ul>
						<c:forEach items="${chapter.videos }" var="video">
<!-- userid -->						 <li><a href="playVideo.action?cou_id=${course.cou_id }&id=${video.vid_id}&userId=${sessionScope.session_user.getUser_id() }">${video.vid_num }: ${video.vid_name }</a></li>
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
			<a class="tea_img" href="<c:url value='/TeacherServlet?method=load&tea_id=${course.teacher.tea_id }'/>">
				<img src="<c:url value='/${course.teacher.tea_simg }'/>" width="100px" height="100px"/>
				<h3>${course.teacher.tea_name }</h3>
			</a>
			<p>
				${course.teacher.tea_desc }
			</p>
		</div>
	</div>
</div>
</body>
</html>