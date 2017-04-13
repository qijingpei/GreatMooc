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

	<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.pagination.js"></script>
	<link rel="stylesheet" href="css/pagination.css" />
	<link type="text/css" rel="stylesheet" href="css/comment_list.css">
	<!-- 发表评论start -->	
	<script type="text/javascript">
		function validate() {
		    var vidid = '${video.vid_id}';
			var username = '${user.username}';
			var usersimg = '${user.user_img}';
			var content = $("#js-pl-textarea").val();
			$.post("addComment.action?vidId=" + vidid + "&content=" + content
					+"&userName="+ username +"&userSimg=" + usersimg, null, callback);
		}
	
		function callback(data) {
			parent.changeSrc();
		}
	</script>
	<!-- end -->
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
</head>
<body>
	<div id="disArea" class="lists-container">
		<ul class="course-menu course-video-menu clearfix">
			<li class="course-menu-item">
				<a class="active" href="javascript:void(0)" id="plMenu">评论</a>
			</li>
		</ul>
		<div id="pl-content" class="list-tab-con">
			<div class="publish clearfix" id="discus-publish">
				<div class="publish-wrap publish-wrap-pl">
					<div class="pl-input-wrap">
						<form id="add">
							<div id="js-pl-input-fake" class="pl-input-inner">
								<textarea id="js-pl-textarea" class="js-placeholder"
									placeholder="扯淡、吐槽、表扬、鼓励……想说啥就说啥！"></textarea>
								<span class="num-limit"><span id="js-pl-limit">0</span>/300</span>
							</div>
							<div class="pl-input-btm input-btm clearfix">
								<input type="button" id="js-pl-submit" class="r course-btn"
									value="发表评论" onclick="validate()">
							</div>
						</form>
					</div>
				</div>
			</div>
			<div id="plLoadListData">
				<div class="pl-container">
					<!-- 		     <p class="pl-none">此节暂无同学评论!</p> -->
				</div>
				<div class="page pl-list-page"></div>
			</div>
		</div>
	</div>
	<!--评论窗口 -->
	<div id="Searchresult">分页初始化完成后这里的内容会被替换。</div>
	<div id="hiddenresult" style="display:none;">
	<%
		List<Comment> commentList = (ArrayList<Comment>) request
				.getAttribute("commentList");
		for(int i = 0;i < commentList.size(); i ++){
	%>
	<div class="result qa-comment js-qa-comment " data-cid="89922" style="height:140px">
		<div class="qa-comment-wrap clearfix ">
			<div class="qa-comment-author">
				 <img title="user_message"
					src="<%=basePath%><%=commentList.get(i).getUserSimg() %>"
					width="40" height="40"> <span class="qa-comment-nick"><%=commentList.get(i).getUserName() %></span>
				
			</div>
			<div class="qa-comment-d ">
				<div class="qa-comment-inner">
					<div class="qa-comment-c imgPreview">
						<div class="rich-text">
							<p><%=commentList.get(i).getComContent()%></p>
						</div>
					</div>
					<div class="qa-comment-addon">
						<span class="qa-comment-time"><%=commentList.get(i).getComTime()%></span>
						<div class="qa-comment-addon-r" style="top:-45px;">
							<div>
							         <span class="js-qa-tr-num"><a  href="<%=basePath %>queryAllReply.action?comId=<%=commentList.get(i).getComId() %>&userId=${user.user_id}" style="cursor:pointer;"><img src="images/11.png"></img>回复</a></span>
							</div>
							<a href="<%=basePath %>smileOn.action?comId=<%=commentList.get(i).getComId()%>&vidId=${video.vid_id}&userId=${user.user_id}"><img src="images/22.png"></img><%=commentList.get(i).getAgreeNum() %></a>
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
</body>
</html>
