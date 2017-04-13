<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>添加分类</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript">
		function checkForm() {
			if(!$("#cname").val()) {
				alert("分类名不能为空！");
				return false;
			}
			if(!$("#pid").val()) {
				alert("一级分类不能为空！");
				return false;
			}
			if(!$("#desc").val()) {
				alert("分类描述不能为空！");
				return false;
			}
			return true;
		}
	</script>
<style type="text/css">
	.Body {
	width: 500px;
	height: 300px;
	color:#EFEFEF;
	background: #333;
	margin: 0px auto;
	margin-top:40px;
	border-radius: 5px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.4);
	padding: 10px 30px 0 30px;
	background: -webkit-radial-gradient(center, ellipse cover, #6A6A6A 0%, #1A1A1B 100%);
	background: -moz-radial-gradient(center, ellipse cover, #6A6A6A 0%, #1A1A1B 100%);
	background: -o-radial-gradient(center, ellipse cover, #6A6A6A 0%, #1A1A1B 100%);
}
</style>
  </head>
  
  <body>
  	
  	<div class="Body">
  		<h3>添加2级分类</h3>

    <p style="font-weight: 900; color: red">${msg }</p>
    <form action="<c:url value='/admin/AdminCategoryServlet'/>" method="post" onsubmit="return checkForm()">
    	<input type="hidden" name="method" value='addChild'/>
    	分类名称：<input type="text" name="cate_name" id="cname"/><br/>
    	一级分类：<select name="pcate_id" id="pid">
    		<option value="">===选择1级分类===</option>
<c:forEach items="${parents }" var="parent">
    		<option value="${parent.cate_id }" <c:if test="${parent.cate_id eq pcate_id }">selected="selected"</c:if> >${parent.cate_name }</option>
</c:forEach>
    	</select><br/>
    	分类描述：<textarea rows="5" cols="50" name="cate_desc" id="desc"></textarea><br/>
    	<input type="submit" value="添加二级分类"/>
    	<input type="button" value="返回" onclick="history.go(-1)"/>
    </form>
  	</div>
    
  </body>
</html>
