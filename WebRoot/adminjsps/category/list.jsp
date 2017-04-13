<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>分类列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/adminjsps/css/category/list.css'/>">
</head>

<body>
	<h2 style="text-align: center;">分类列表</h2>
	<table align="center" border="1" cellpadding="0" cellspacing="0">
		<caption class="captionAddOneLevel">
			<a href="<c:url value='/adminjsps/category/add.jsp'/>">添加一级分类</a>
		</caption>
		<tr class="trTitle">
			<th>分类名称</th>
			<th>描述</th>
			<th>操作</th>
		</tr>

		<c:forEach items="${parents }" var="parent">
			<tr class="trOneLevel">
				<td width="200px;">${parent.cate_name }</td>
				<td>${parent.cate_desc }</td>
				<td width="200px;">
					<a href="<c:url value='/admin/AdminCategoryServlet?method=addChildPre&pcate_id=${parent.cate_id }'/>">添加二级分类</a>
					<a href="<c:url value='/admin/AdminCategoryServlet?method=editParentPre&cate_id=${parent.cate_id }'/>">修改</a>
					<a onclick="return confirm('您是否真要删除该一级分类？')"
					href="<c:url value='/admin/AdminCategoryServlet?method=deleteParent&cate_id=${parent.cate_id }'/>">删除</a>
				</td>
			</tr>
			<c:forEach items="${parent.children }" var="child">
				<tr class="trTwoLevel">
					<td>${child.cate_name }</td>
					<td>${child.cate_desc }</td>
					<td width="200px;" align="right"><a
						href="<c:url value='/admin/AdminCategoryServlet?method=editChildPre&cate_id=${child.cate_id }'/>">修改</a>
						<a onclick="return confirm('您是否真要删除该二级分类？')"
						href="<c:url value='/admin/AdminCategoryServlet?method=deleteChild&cate_id=${child.cate_id }'/>">删除</a>
					</td>
				</tr>
			</c:forEach>
		</c:forEach>


	</table>
</body>
</html>
