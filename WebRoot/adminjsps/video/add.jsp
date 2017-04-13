<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加视频</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/css/common.css'/>">
</head>
<body>
<div class="addBody">
  <form action="<c:url value='/admin/AdminAddVideoServlet'/>" method="post" enctype="multipart/form-data">
  	<div class="msg">
	</div>
   	 <div class="inputDiv">
	     <label>视频名称:</label>
         <input class="_name" type="text" name="vid_name"/>
		
	 </div>
     <div class="imgDiv">
	     <label>上传视频:</label>
		  <input type='button' class="imgBtn"/>
          <input type="file" name="cou_bimg" class="file" onchange=
		 "document.getElementById('cou_bimg').value=this.value"/>
		 <input type='text' id='cou_bimg' class='txt'/>
	 </div>
	  <div class="selectDiv">
	 	<select name="chap_id" class="select" style="margin-right:5px;">
			<option>===请选择章节===</option>
			<c:forEach items="${chapters }" var="chapter">
			 			<option value="${chapter.chap_id }" <c:if test="${chapter.chap_id eq chap_id }">selected="selected"</c:if>>${chapter.chap_name }</option>		
			</c:forEach>
		</select>
		<select name="vid_num" class="select">
			<option>==选择视频编号==</option>
			<c:forEach begin="1" end="10" var="i">
				<option>${i }</option>
			</c:forEach>
		</select>
    </div>
    <input type="hidden" name="cou_id" value="${cou_id }"/>
	<input class="button" type="submit" value="添加"></input>
	<input class="button" type="button" value="返回"  onclick="history.go(-1)" ></input>
  </form>
  </div>
</body>
</html>