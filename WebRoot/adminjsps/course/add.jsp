<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>列表</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/css/common.css'/>">
<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/adminjsps/js/course/edit.js'/>"></script>
<style type="text/css">
.addBody{height: 640px;}
</style>
</head>
<body>
<div class="addBody">
  <div class="msg">
		<h3></h3>
  </div>
  <form action="<c:url value='/admin/AdminAddCourseServlet'/>" method="post" enctype="multipart/form-data">
   	 <div class="inputDiv">
	     <label>课程名称:</label>
         <input class="_name" type="text" name="cou_name"/>
		
	 </div>
     <div class="imgDiv">
	     <label>课程大图:</label>
		  <input type='button' class="imgBtn"/>
         <input type="file" name="cou_bimg" class="file" onchange=
		 "document.getElementById('cou_bimg').value=this.value"/>
		 <input type='text' id='cou_bimg' class='txt'/>
	 </div>
	<div class="imgDiv">
	     <label>课程小图:</label>
          <input type='button' class="imgBtn"/>
         <input  type="file" name="cou_simg" class="file" size="28" onchange=
		 "document.getElementById('cou_simg').value=this.value"/>
		 <input type="text"  id='cou_simg' class='txt' />
		 <p>${msg }</p>
	 </div>
	  <div class="selectDiv">
	 	<select class="select" id="sch_id" name="sch_id" style="margin-right:5px;" onchange="loadTeacher()">
			<option>===请选择大学===</option>
			
			<c:forEach items="${schools }" var="school">
				<option value="${school.sch_id }">${school.sch_name }</option>
			</c:forEach>
			
			<option>无</option>
		</select>
		<select class="select" id="tea_id" name="tea_id">
			<option>===请选择教师===</option>
			<option>无</option>
		</select>
		</div>
		<div class="selectDiv">
		<select class="select" id="pcate_id" name="pcate_id" style="margin-right:5px;" onchange="loadChildren()">
			<option>==请选择一级分类==</option>
			<option>无</option>
			
			<c:forEach items="${parents }" var="parent">
			    	<option value="${parent.cate_id }">${parent.cate_name }</option>
			</c:forEach>
			
		</select>
		<select class="select" id="cate_id" name="cate_id">
			<option>==请选择二级分类==</option>			
		</select>
	 </div>
	 <div class="inputDiv">
	     <label>课程简介:</label><br>
		 <textarea  class="desc" name="cou_desc"></textarea>
	 </div>
    
	<input class="button" type="submit" value="添加"></input> 
	  
  </form>
  </div>
</body>
</html>