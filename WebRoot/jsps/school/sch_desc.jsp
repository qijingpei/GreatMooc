<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
  <head>
    <title>GreatMooc-课程介绍</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/main.css'/>">
	<script type="text/javascript" src="<c:url value='/jsps/js/course/ifream.js'/>"></script>
  </head>
  <% String sch_id=request.getParameter("sch_id");
  	request.setAttribute("sch_id", sch_id);
  %>
  <body>
  
    <div class="table">
    	<div class="top">
    		<iframe frameborder="0" width="100%" height="100%" scrolling="no" src="<c:url value='/jsps/top.jsp'/>" name="top"></iframe>
    	</div>
    	<div class="mid"  style="min-height:960px;">   
    			<iframe id= "cou_desc" frameborder="0" width="100%" height="100%" scrolling="no" src="<c:url value='/SchoolServlet?method=load&sch_id=${sch_id }'/>" 
    			name="cou_desc" onload="javascript:reinitIframe();"></iframe>
    	</div>
 		<div class="footer">
 			  <iframe style="margin-bottom:10px;" frameborder="0" width="100%" height="100%" scrolling="no" src="<c:url value='/jsps/footer.jsp'/>" name="footer"></iframe> 		
    	</div>
    </div>
    
  </body>
</html>
