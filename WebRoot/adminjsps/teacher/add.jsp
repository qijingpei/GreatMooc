<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加图书</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<style> 
	body{
		background-color:#eee;
	}
.center
{
margin:0 auto;
width:500px;
height:400px;
background-color:#ccc;

}
.center:hover{
	box-shadow: -15px 15px 25px #888888;	
	}
</style>

  </head>
  
  <body>
  <div class="center">
  	<div style="position:relative;left:20px;">
    <h1>添加教师</h1>
    <p style="font-weight: 900; color: red">${msg }</p>
    <form  action="<c:url value='/admin/AdminAddTeacherServlet'/>" method="post" enctype="multipart/form-data">
    	教师姓名：<input style="width: 150px; height: 20px;" type="text" name="tea_name"/><br/>
    	大图介绍：<input style="width: 223px; height: 20px;" type="file" name="tea_bimg"/><br/>
    	小图头像：<input style="width: 223px; height: 20px;" type="file" name="tea_simg"/><br/>
    	教师简介：<input style="width: 200px; height: 20px;" type="text" name="tea_desc"/><br/>
    	教师寄语：<input type="text" style="width: 300px; height: 20px;" name="tea_jiyu"/><br/>
  	<!-- 这里嘉宾观点在界面上显示全部挤到一起去了，还没有解决这个问题 -->
  		嘉宾观点：<input type="text" style="width: 300px; height: 100px;" name="tea_guandian"/><br/>
    	所属学校：<select style="width: 150px; height: 20px;" name="sch_id">
    	<c:forEach items="${schoolList }" var="school">
    		<option value="${school.sch_id }">${school.sch_name }</option>
    	</c:forEach>
    	</select>
    	<br/>
    	<input type="submit" value="添加教师"/>
    </form>
    </div>
    </div>
  </body>
</html>
