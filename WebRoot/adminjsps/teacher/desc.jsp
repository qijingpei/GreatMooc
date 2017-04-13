<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>教师详细信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		font-size: 10pt;
	}
	div {
		margin:20px;
		border: solid 0px gray;
		width: 150px;
		height: 150px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
</style>
<script type="text/javascript">
	function setMethod(method) {
		var ele = document.getElementById("method");
		ele.value = method;
	}
</script>
  </head>
  
  <body>
  <div style="position: relative;left: 30px;top: 20px;">
  	<div>
 		<img width=100px height=100px src="<c:url value='/${teacher.tea_simg}'/>" border="0"/><br/>小图片
  	</div>
  	<div style="height:450px">
  	  <img width="360" height="400" src="<c:url value='/${teacher.tea_bimg}'/>" border="0"/>大图片<br/><!-- 教师照片 -->
  	</div>
  </div> 
  
  
  <form style="margin:100px;float:right;position: absolute;left: 500px" id="form" action="<c:url value='/admin/AdminTeacherServlet'/>" method="post">
  	<input type="hidden" name="method" value="" id="method"/>
  	<input type="hidden" name="tea_id" value="${teacher.tea_id }"/>
  	<input type="hidden" name="tea_bimg" value="${teacher.tea_bimg }"/>
  	<input type="hidden" name="tea_simg" value="${teacher.tea_simg }"/>
  	教师名称：<input type="text" name="tea_name" value="${teacher.tea_name }"/><br/>
  	教师简介：<input type="text" style="width: 300px; height: 20px;" name="tea_desc" value="${teacher.tea_desc }"/><br/>
  	教师寄语：<input type="text" style="width: 300px; height: 20px;" name="tea_jiyu" value="${teacher.tea_jiyu }"/><br/>
  	<!-- 这里嘉宾观点在界面上显示全部挤到一起去了，还没有解决这个问题 -->
  	嘉宾观点：<input type="text" style="width: 300px; height: 100px;" name="tea_guandian" value="${teacher.tea_guandian }"/><br/>
  	所属学校：<select style="width: 150px; height: 20px;" name="sch_id">
  	<!-- 输出所有学校，并让正确的学校显示出来 -->
  	<c:forEach items="${schoolList }" var="s">
  		<option value="${s.sch_id }" <c:if test="${s.sch_id eq teacher.school.sch_id }" >selected="selected"</c:if>  >${s.sch_name }</option>
  	</c:forEach>
  	</select><br/>
  	
  	<!--  学校：<input type="text" name="school" value="${teacher.school.sch_name}"/><br/> -->
  	
  	
  	<input type="submit" value="删除" onclick="setMethod('delete')"/>
  	<input type="submit" value="完成编辑" onclick="setMethod('edit')"/>
  </form>
  </body>
</html>
