<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>列表</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/css/chap_all.css'/>">
<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
<style type="text/css">
</style>
<body>
<div id="chap_main" class="chap_main">
	<div class="title">
		<h2>章节管理</h2>
		<a href="<c:url value='/adminjsps/chapter/add.jsp?cou_id=${cou_id }'/>" class="do addChap">添加章节</a>
	</div>
	<div class="chap_list">
	
		<c:forEach items="${chapters }" var="chapter">
	
		<div class="chap_one">
			<div class="chap_in">
				<h3>${chapter.chap_num }.${chapter.chap_name }</h3>
				<span class="doMore">
					<a class="do" href="<c:url value='/admin/AdminChapterServlet?method=delete&chap_id=${chapter.chap_id }'/>">删除</a>					
					<a class="do" href="<c:url value='/admin/AdminChapterServlet?method=editPre&chap_id=${chapter.chap_id }&cou_id=${cou_id }'/>">修改</a>
					<a class="do" href="<c:url value='/admin/AdminVideoServlet?method=addVideoPre&chap_id=${chapter.chap_id }&cou_id=${cou_id }'/>">添加视频</a>
				</span>
			</div>
			<div class="vid_all">
			
				<c:forEach items="${chapter.videos}" var="video">
				<div class="vid_one">
					<h4>${video.vid_num }.${video.vid_name }</h4>
					<span class="doMore">
						<a class="do_s" href="#">修改</a>
						<a class="do_s" href="#">删除</a>
					</span>
				</div>
				</c:forEach>
				
			</div>
		</div>
		
		</c:forEach>
		
	</div>
</div>
</body>
</html>