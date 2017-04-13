<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>菜单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/menu/mymenu.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/menu/mymenu.css'/>" type="text/css" media="all">
<script language="javascript">
/*
 * bar1：必须与对象名相同！不要问为什么！！！
   ITCAST网络图书商城：大标题
 */
var bar1 = new Q6MenuBar("bar1", "GreatMooc在线学习平台");
function load() {
	/*
	设置本色方案！
	配置方案一共4种：0、1、2、3
	*/
	bar1.colorStyle = 2;
	/*
	指定图片目录
	*/
	bar1.config.imgDir = "<c:url value='/menu/img/'/>";
	/*
	菜单之间是否相互排斥
	*/
	bar1.config.radioButton=true;
	/*
	分类管理：指定要添加的菜单名称（如果这个名称的菜单已经存在，不会重复添加）
	查看分类：指定要添加的菜单项名称
	<c:url value='/adminjsps/admin/category/list.jsp'/>：指定菜单项时要请求的地址
	body：结果的显示框架页名称
	*/
	bar1.add("分类管理", "所有分类", "<c:url value='/adminjsps/admin/unfinsh.jsp'/>", "body");
	bar1.add("分类管理", "所有分类", "<c:url value='/adminjsps/admin/unfinsh.jsp'/>", "body");

	bar1.add("课程管理", "所有课程", "<c:url value='/adminjsps/admin/unfinsh.jsp'/>", "body");
	bar1.add("课程管理", "所有课程", "<c:url value='/adminjsps/admin/unfinsh.jsp'/>", "body");
	
	bar1.add("教师管理", "查看教师", "<c:url value='/admin/AdminTeacherServlet?method=findAll'/>", "body");
	bar1.add("教师管理", "添加教师", "<c:url value='/admin/AdminTeacherServlet?method=findAllSchool'/>", "body");

	bar1.add("学校管理", "所有学校", "<c:url value='/adminjsps/admin/unfinsh.jsp'/>", "body");
	bar1.add("学校管理", "添加学校", "<c:url value='/adminjsps/admin/unfinsh.jsp'/>", "body");
	
	bar1.add("论坛管理", "发布公告", "<c:url value='/adminjsps/admin/unfinsh.jsp'/>", "body");
	bar1.add("论坛管理", "删除帖子", "<c:url value='/adminjsps/admin/unfinsh.jsp'/>", "body");
	
	bar1.add("其他事情", "就等你来添加了！", "<c:url value='/adminjsps/admin/unfinsh.jsp'/>", "body");

	// 获取div元素
	var d = document.getElementById("menu");
	// 把菜单对象转换成字符串，赋给<div>元素做内容
	d.innerHTML = bar1.toString();
}
</script>

</head>

<body onload="load()" style="margin: 0px; background: rgb(254,238,189);">
<div id="menu"></div>

</body>
</html>
