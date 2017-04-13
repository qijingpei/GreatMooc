<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>列表</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/css/common.css'/>">
<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
<style type="text/css">
.addBody{height:240px;}
._name{width:200px;}
.select{
	width:160px;
	height:40px;
	color:#003399;
}
</style>

<body>
<div class="addBody">
  <form action="<c:url value='/admin/AdminChapterServlet'/>" method="post">
		<input type="hidden" name="method" value="edit"/>
		<input type="hidden" name="chap_id" value="${chapter.chap_id }"/>
		<input type="hidden" name="cou_id" value="${cou_id }"/>
	     <label>章节名称:</label>
         <input class="_name" type="text" name="chap_name" value="${chapter.chap_name }"/>	
		<select class="select" name="chap_num" style="margin-right:5px;">
			<option>==选择章节号==</option>
			<c:forEach begin="1" end="10" var="i">
				<option <c:if test='${i eq chapter.chap_num }'>selected='selected'</c:if>>${i }</option>
			</c:forEach>
			

		</select>
    
	<input class="button" type="submit" value="修改"></input>
	<input class="button" type="button" value="返回" onclick="window.history.go(-1);" style="margin-top:10px;"></input>
     
  </form>
  </div>
</body>
</html>