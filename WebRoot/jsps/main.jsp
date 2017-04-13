<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
  <head>

    
    <title>Great Mooc</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/main.css'/>">
	<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
  </head>
  
  <body>
  
    <div class="table">
    	<div class="top">
    		<iframe frameborder="0" width="100%" height="100%" scrolling="no" src="<c:url value='/jsps/top.jsp'/>" name="top"></iframe>
    	</div>
    	<div class="mid">
    		<div class="mid_left">
    			<iframe frameborder="0" width="100%" height="100%" scrolling="no" src="<c:url value='/CategoryServlet?method=findAll'/>" name="mid_left"></iframe>
    		</div>
    		<div class="mid_right">
    			<iframe frameborder="0" width="980px" scrolling="no" id="mid_right1" name="mid_right1" 
    			onload="javascript:reinitIframe();"
    			src="<c:url value='/CourseServlet?method=findAll'/>" ></iframe> 			  
    		</div>
    	</div>
 		<div class="footer">
 			  <iframe style="margin-bottom:10px;" frameborder="0" width="100%" height="100%" scrolling="no" src="<c:url value='/jsps/footer.jsp'/>" name="footer"></iframe> 		
    	</div>
    </div>
    
  </body>
</html>
