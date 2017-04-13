<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分页</title>
<link type="text/css" rel="stylesheet" href="<c:url value='/jsps/pager/pager.css'/>">
</head>
<body>
 <div class="page">
	 
	 <%--计算begin和end,然后用for循环
    1.如果页码总数<=6,则设置begin=1，end=${pb.tp}
    2.设置begin={pb.pc-2},end=${pc.pc+3}
    	如果begin<1;那么让begin=1，end=6;
    	如果end>${pb.tp},那么begin=${pv.tp-5},end=${pb.tp}
     --%>   

    <c:choose>
    	<c:when test="${pb.tp eq 0}">
    		<c:set var="nothing" value="0"/>
    	</c:when>
    	<c:when test="${pb.tp>0 && pb.tp<6 }">
    		<c:set var="begin" value="1"/>
    		<c:set var="end" value="${pb.tp }"/>
    	</c:when>
    	<c:otherwise>
    		<c:set var="begin" value="${pb.pc-2 }"/>
    		<c:set var="end" value="${pb.pc+3 }"/>
    		<c:choose>
    			<c:when test="${begin<1 }">
    				<c:set var="begin" value="1"/>
    				<c:set var="end" value="6"/>
    			</c:when>
    			<c:when test="${end >pb.tp }">
    				<c:set var="begin" value="${pb.tp-5 }"/>
    				<c:set var="end" value="${pb.tp }"/>
    			</c:when>
    		</c:choose>
    	</c:otherwise>
    </c:choose>
    <%--显示分页的菜单栏 --%>
	<c:choose>
		<c:when test="${nothing eq 0 }">
			<img src="<c:url value='/images/noSource.jpg'/>" style="float:left;margin-left:300px;margin-top:36px;"/>
		</c:when>
		<c:otherwise>
			<%--上一页 --%>
			<c:choose>
				<c:when test="${pb.pc eq 1 }">
					<span class="disabled_page">上一页</span>
				</c:when>
				<c:otherwise>
					<a href="${pb.url }&pc=${pb.pc-1}" class="">上一页</a>
				</c:otherwise>
			</c:choose>
			<c:forEach begin="${begin}" end="${end}"  var="i">
		    	<c:choose>
		    		<c:when test="${pb.pc eq i }">
		    			<a class="active" href="#">${i }</a>
		    		</c:when>
		    		<c:otherwise>
		    			<a href="${pb.url }&pc=${i }" class="">${i }</a>
		    		</c:otherwise>
		    	</c:choose>		    	
    		</c:forEach>    		
    		
    		  <%-- 显示点点点 --%>   
		    <c:if test="${end < pb.tp }">
		    	<span class="">...</span> 
		    </c:if>
		    
		            
		     <%--下一页 --%>
		     <c:choose>
		     	<c:when test="${pb.pc eq pb.tp }">
		     		<span class="disabled_page">下一页</span>
		     	</c:when>
		     	<c:otherwise>
		     		<a href="${pb.url }&pc=${pb.pc+1}" class="">下一页</a>
		     	</c:otherwise>
		     </c:choose>
		        
		        
		    
		    <%-- 共N页 到M页 --%>
		    <span>共${pb.tp }页</span>
		</c:otherwise>
	</c:choose>
    
 </div>
</body>
</html>