<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
<head>


<title>GreatMooc-后台管理系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>
<script type="text/javascript">
	function reinitIframe() {
		var iframe = document.getElementById("body");
		try {
			//var bHeight = iframe.contentWindow.document.body.scrollHeight;
			var bHeight =0;
			var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
			var height = Math.max(bHeight, dHeight);
			iframe.height = height;
		} catch (ex) {
		}
	}
	window.onload = function() {
		window.setInterval("reinitIframe()", 500);
	};
</script>
<style type="text/css">
* {
	font-size: 10pt;
	padding: 0px;
	margin: 0px;
}

body {
	text-align: center;
	background-color: #ddd;
}

.table {
	height: 100%;
}

.top {
	height: 150px;
}

.body {
	height: 100%;
}
</style>
</head>

<body>

	<div class="table">
		<div class="top">
			<iframe frameborder="0" width="100%" height="100%" scrolling="no"
				src="<c:url value='/adminjsps/top.jsp'/>" name="top"></iframe>
		</div>
		<div class="body">

			<iframe id="body" frameborder="0" width="100%" height="100%"
				scrolling="no" src="<c:url value='/adminjsps/body.jsp'/>"
				onload="javascript:reinitIframe();" name="body"></iframe>
		</div>
	</div>

</body>
</html>
