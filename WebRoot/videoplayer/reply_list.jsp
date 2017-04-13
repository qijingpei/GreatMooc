<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.greatmooc.domain.*" isELIgnored="false"%>
<%@ page import="com.greatmooc.util.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>reply list</title>
	<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.pagination.js"></script>
	<link rel="stylesheet" href="css/pagination.css" />
	<link type="text/css" rel="stylesheet" href="css/comment_list.css">
	<!-- 分页 start-->	
	<script type="text/javascript">
	$(function(){
		//回调函数的作用是显示对应分页的列表项内容
		//回调函数在用户每次点击分页链接的时候执行
		//参数page_index{int整型}表示当前的索引页
		var initPagination = function() {
			var num_entries = $("#hiddenresult div.result").length;
			// 创建分页
			$("#Pagination").pagination(num_entries, {
				num_edge_entries: 1, //边缘页数
				num_display_entries: 4, //主体页数
				callback: pageselectCallback,
				items_per_page:5 //每页显示1项
			});
		 }();
		 
			function pageselectCallback(page_index, jq){
				var num_entries = $("#hiddenresult div.result").length;
				// 从表单获取每页的显示的列表项数目
				var max_elem = Math.min((page_index+1) * 5, num_entries);
				
				$("#Searchresult").html("");
				// 获取加载元素
				for(var i=page_index*5;i<max_elem;i++){
					$("#Searchresult").append($("#hiddenresult div.result:eq("+i+")").clone());
				}
				//阻止单击事件
				return false;
			}
		});
	</script>
	<!-- 分页end -->
	<!-- 回复 start -->
	<script type="text/javascript">
		function validate() {
		    var comId = '${comment.comId}';
		    var userId = '${user.user_id}';
			var username = '${user.username}';
			var usersimg = '${user.user_img}';
			var content = $("#js-pl-textarea_reply").val();
			$.post("addReply.action?comId=" + comId + "&content=" + content
					+"&userName="+ username +"&userSimg=" + usersimg+"&userId="+userId, null, callback);
		}
		function callback(){
			alert("success");
			parent.changeSrc();
		}
	</script>
	<!-- end -->	
  </head>
  
  <body>
	<div id="Searchresult">分页初始化完成后这里的内容会被替换。</div>
	<div id="hiddenresult" style="display:none;">
	<%
		List<Reply> replyList = (ArrayList<Reply>) request
				.getAttribute("replyList");
		for(int i = 0;i < replyList.size(); i ++){
	%>
	<div class="result qa-comment js-qa-comment " data-cid="89922" style="height:140px">
		<div class="qa-comment-wrap clearfix ">
			<div class="qa-comment-author">
				<a href="#" title="user_message"> <img
					src="<%=basePath+replyList.get(i).getUserSimg() %>"
					width="40" height="40"> <span class="qa-comment-nick"><%=replyList.get(i).getUserName() %></span>
				</a>
			</div>
			<div class="qa-comment-d ">
				<div class="qa-comment-inner">
					<div class="qa-comment-c imgPreview">
						<div class="rich-text">
							<p><%=replyList.get(i).getReplContent() %></p>
						</div>
					</div>
					<div class="qa-comment-addon">
						<span class="qa-comment-time"><%=replyList.get(i).getReplTime()%></span>
						<div class="qa-comment-addon-r">
							<img src="images/22.png"></img><a href="<%=basePath %>smileOn.action?comId=<%=replyList.get(i).getComId()%>&vidId=${video.vid_id}&userId=${user.user_id}"><%=replyList.get(i).getAgreeNum() %></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
			<%
		}
	%>
	</div>	
    <div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
	<div id="divOne_1" style="display: block">
		<div id="disArea" class="lists-container">
			<ul class="course-menu course-video-menu clearfix">
				<li class="course-menu-item">
					<a class="active" href="javascript:void(0)" id="plMenu">回复</a>
				</li>
			</ul>
			<div id="pl-content" class="list-tab-con">
				<div class="publish clearfix" id="discus-publish">
					<div class="publish-wrap publish-wrap-pl">
						<div class="pl-input-wrap">
							<form id="add">
								<div id="js-pl-input-fake" class="pl-input-inner">
									<textarea id="js-pl-textarea_reply" class="js-placeholder"
										placeholder="有啥想回复的请直说吧......"></textarea>
									<span class="num-limit"><span id="js-pl-limit">0</span>/300</span>
								</div>
								<div class="pl-input-btm input-btm clearfix">
									<input type="button" id="js-pl-submit" class="r course-btn"
										value="回复" onclick="validate()">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
  </body>
</html>
