<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="<c:url value='/jsps/404page/css/style.css'/>" rel="stylesheet" type="text/css" media="all" />
<title>404 错误页面</title>
</head>
<body>
<!-----start-wrap--------->
	<div class="wrap">
		<!-----start-content--------->
		<div class="content">
			<!-----start-logo--------->
			<div class="logo">
				<h1><a href="#"><img src="<c:url value='/jsps//404page/images/logo.png'/>"/></a></h1>
				<span><img src="<c:url value='/jsps//404page/images/signal.png'/>"/>很抱歉！您请求的页面没有找到</span>
			</div>
			<!-----end-logo--------->
			<!-----start-search-bar-section--------->
			<div class="buttom">
				<div class="seach_bar">
					<p>你可以去<span><a href="http://localhost:8080/mooc">主页</a></span> 或者在此搜索</p>
					<!-----start-sear-box--------->
					<div class="search_box">
					<form action="http://www.baidu.com/s?">
					   <input name="wd" type="text" value="搜索" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '搜索';}"><input type="submit" value="">
				    </form>
					 </div>
				</div>
			</div>
			<!-----end-sear-bar--------->
		</div>
		<!----copy-right-------------->
	<p class="copy_right">&#169; Copyright 2015 by<a href="http://localhost:8080/mooc" target="_blank">&nbsp;greatmooc</a> </p>
	</div>
	<!---------end-wrap---------->
</body>
</html>