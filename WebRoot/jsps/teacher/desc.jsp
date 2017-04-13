<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
  <title>教师介绍 </title>
  <meta name="generator" content="editplus" />
  <meta name="author" content="" />
  <meta name="keywords" content="" />
  <meta name="description" content="" />
  <style type="text/css">
  body{background:#EEE;}
  .g2{ height:301px; background:#EEE;}
 
 .g2 .faceShow{ top:-6px; left:30px; position:absolute; background:#EEE; width:258px; height:270px; padding:20px; text-align:center;border-radius:6px;}

 .g2 img.face{margin-top:25px;}
 .g2 .name{ line-height:32px; font-size:16px; font-family:'微软雅黑'; padding-top:10px;color:#000;}

 .g2 .introduce p{ color:#000;padding-left:350px;padding-right:30px;font-size:16px; font-family:'微软雅黑';padding-top:30px;}
 
 .g2 span.tt{ color:#000; padding-right:3px; font-size:25px; font-family:'微软雅黑';} 
 .g2 .mdp{ padding:40px 0 15px 0;}
 .g2 .md{ border:2px solid #000; color:#000; font-size:25px; font-family:'微软雅黑'; line-height:33px;  padding:0 13px;}
  .g2 .next{width:60%;border:0px solid #000;margin-left:80px;background:#FFF;}
   .next span{color:#000;font-family:"微软雅黑";font-size:25px;padding-left:10px;}
  .next p{color:#000;font-family:"微软雅黑";font-size:16px;line-height:30px;padding:10px 10px;}
  div span{color:#000;font-family:"微软雅黑";font-size:14px;padding-left:10px;}
  
  </style>
 </head>

 <body>
  <div class="g2">
		
	 <div class="faceShow">
		 <img class="face" width="180" height="180" src="<c:url value='/${teacher.tea_simg }'/>"/>
		 <p class="name">${teacher.tea_name }</p>
	 </div>
	 <div class="introduce">	
		<p>
		  <span class="tt">学校：</span>${teacher.school.sch_name }<br/>
		 
		  <!-- 显示老师的课程信息 -->
		  <!-- 如果这位老师没有授课，那么不显示他的课程信息 -->
		  <!-- 
		   <c:if test="${!empty courseList}">
		  
		  <span>  授课：
		  <c:forEach items="${courseList }" var="cl">
		  ${cl.cou_name}
		  </c:forEach>
		  </span></c:if>
		   -->
		</p>
		<p class="mdp">
			<span class="md">${teacher.tea_jiyu }</span>
		</p>
		<p class="">
		  <span class="tt">简介：</span>${teacher.tea_desc }
		</p>
		<!-- 
		<p class="">
		  <span class="tt">摘要：</span>从1996年便已涉足网络教育的李晓明教授，相信网络教育将成为构成未来教育的一部分。网络教育不仅给学生充分提供了学习的灵活性，并且提高了老师们教育工作的效率。对于网络教育的现状，李晓明教授认为还存在着很多有待解决的挑战和方式创新的空间，学生的个体差异、课堂的互动交流、成果的评估认证，这些方面的问题需要教育机构以更多的实践和摸索来解决。
		</p>
		 -->
		 </br></br> </br></br> </br>
	</div>

 	<div>		
    		<div style="float:left;height:500px"  class="next">
			 <span>嘉宾寄语:</span>			
				<p>${teacher.tea_guandian }</p>			
			</div>
				
		<div style="float:right;width:30%;" >
		<c:forEach items="${courseList }" var="cl">
			<div  style="background:#FFF;width:240px;" >
			<a href="<c:url value='/CourseServlet?method=load&cou_id=${cl.cou_id }'/>">
				<img  src="<c:url value='/${cl.cou_simg }'/>"/><br/>
				<span>
				${cl.cou_name }
				</span>	<br/>
				<span>
				${teacher.tea_name}	
				</span>
			</a>
			</div>
			<br/>
		</c:forEach>
						
		</div>
	</div>
	
    
 </body>
</html>
