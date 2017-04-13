<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.greatmooc.domain.*" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>${video.vid_name}</title>
    <style type="text/css">
/*     #a1的这段css必须保留否则无法加载播放器 */
		#a1{
				position:relative;
				z-index: 100;
		}
		#main{
				background-color:#444444;
				
		}
		body{ text-align:center;}
	</style>
  </head>
  
  <body>
  <div id="main">
	<div class="side"></div>
	<center>
		<div id="a1"></div>
	</center>
	<div class="side"></div>
  </div>
  <!-- 视频播放器调用 start -->
	<script type="text/javascript" src="ckplayer/ckplayer.js"></script>
	<script type="text/javascript" src="js/offlights.js"></script>
	<script type="text/javascript">
		var mediaPlayerUrl = '<%=basePath%>${video.vid_path}';
		var flashvars={
	 		f:mediaPlayerUrl,
			c:0,
			b:1,
			my_url:encodeURIComponent(window.location.href)
			};
		var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'transparent'};
		CKobject.embedSWF('ckplayer/ckplayer.swf','a1','ckplayer_a1','940','550',flashvars,params);	
		var box = new LightBox();
		function closelights(){//关灯
			box.Show();
			CKobject._K_('a1').style.width='940px';
			CKobject._K_('a1').style.height='550px';
			CKobject.getObjectById('ckplayer_a1').width=940;
			CKobject.getObjectById('ckplayer_a1').height=550;
		}
		function openlights(){//开灯
			box.Close();
			CKobject._K_('a1').style.width='940px';
			CKobject._K_('a1').style.height='550px';
			CKobject.getObjectById('ckplayer_a1').width=940;
			CKobject.getObjectById('ckplayer_a1').height=550;
		}
	</script>
	<!-- end -->
	<!-- 刷新iframe框架start -->
	<script type="text/javascript">
		function changeSrc(){
			var iframeElement = document.getElementById("refresh");//获取id为iframe的浮动框架
			iframeElement.src = iframeElement.src;
		}
	</script>
	<!-- end -->
	<!-- 自适应iframe框架高度start -->
	<script type="text/javascript" language="javascript">   
		function iFrameHeight() {   
		var ifm= document.getElementById("refresh");   
		var subWeb = document.frames ? document.frames["refresh"].document : ifm.contentDocument;   
		if(ifm != null && subWeb != null) {
		   ifm.height = subWeb.body.scrollHeight;
		}   
	}   
	</script>
	<!-- end -->
	<div id="iframe">
		<iframe id="refresh" src="<%=basePath %>queryAllComment.action?id=${video.vid_id}&userId=<%=request.getParameter("userId") %>" scrolling="no" frameborder="0" onLoad="iFrameHeight()" width="70%"></iframe>
	</div>
  </body>
</html>
