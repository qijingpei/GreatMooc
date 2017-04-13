$(function() {
	$("#edit").text("修改");
	$("#formDiv").css("display", "none");
	$("#desc_main").css("display", "");	
	var bool = true;
	// 操作和显示切换
	$("#edit").click(function() {
		if(bool==true){
			$("#edit").text("取消");
			$("#desc_main").css("display", "none");
			$("#formDiv").css("display", "");
			bool = false;
		}else{
			$("#edit").text("修改");
			$("#formDiv").css("display", "none");
			$("#desc_main").css("display", "");	
			bool = true;
		}
	});
	
	$(parent.window).scroll(function(){
		  $("#edit").css("top",$(parent.window).scrollTop()+400);
		});
});
function loadChildren() {
	/*
	1. 获取pid
	2. 发出异步请求，功能之：
	  3. 得到一个数组
	  4. 获取cid元素(<select>)，把内部的<option>全部删除
	  5. 添加一个头（<option>请选择2级分类</option>）
	  6. 循环数组，把数组中每个对象转换成一个<option>添加到cid中
	*/
	// 1. 获取pid
	var pid = $("#pid").val();
	// 2. 发送异步请求
	$.ajax({
		async:true,
		cache:false,
		url:"/mygoods/admin/AdminBookServlet",
		data:{method:"ajaxFindChildren", pid:pid},
		type:"POST",
		dataType:"json",
		success:function(arr) {
			// 3. 得到cid，删除它的内容
			$("#cid").empty();//删除元素的子元素
			$("#cid").append($("<option>==请选择2级分类==</option>"));//4.添加头
			// 5. 循环遍历数组，把每个对象转换成<option>添加到cid中
			for(var i = 0; i < arr.length; i++) {
				var option = $("<option>").val(arr[i].cid).text(arr[i].cname);
				$("#cid").append(option);
			}
		}
	});
}
//修改按钮的点击事件
function editForm() {
	if(confirm("您确定要修改吗？")){
		$("#method").val("edit");
		$("#form").submit();
	}
}
//删除按钮的点击事件
function deleteForm() {
	if(confirm("您确定要删除吗？")){
		$("#method").val("delete");
		$("#form").submit();
	}
}