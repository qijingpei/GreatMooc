<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>top</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/school/list.css'/>">

</head>
<body>
	<div id="s_main">
		<div class="title">
			<p>合作学校</p>
		</div>
		<div style="margin:0 auto; width:1000px;">
			<div class="sch_list">
			
				<c:forEach items="${schools }" var="school">
				<a class="sch_one" href="<c:url value='/admin/AdminSchoolServlet?method=load&sch_id=${school.sch_id }'/>"> 
					<img src="<c:url value='/${school.sch_simg }'/>" width="164" height="60" alt="${school.sch_name }" title="${school.sch_name }"/>
				</a> 
				</c:forEach>

			</div>
		</div>
	</div>
</body>
</html>