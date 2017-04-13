$(function() {
	$("#formDiv").css("display", "none");
	$("#desc_main").css("display", "");
	var bool = true;
	// 操作和显示切换
	$("#edit").click(function() {
		if (bool == true) {
			$("#edit").text("取消");
			$("#desc_main").css("display", "none");
			$("#formDiv").css("display", "");
			bool = false;
		} else {
			$("#edit").text("修改信息");
			$("#formDiv").css("display", "none");
			$("#desc_main").css("display", "");
			bool = true;
		}
	});

	$(parent.window).scroll(function() {
		$("#doMore").css("top", $(parent.window).scrollTop() + 360);
	});
});
// 加载学校下面的教师
function loadTeacher() {

	// 1. 获取sch_id
	var sch_id = $("#sch_id").val();
	// 2. 发送异步请求
	$.ajax({
		async : true,
		cache : false,
		url : "/mooc/admin/AdminCourseServlet",
		data : {
			method : "ajaxFindTeacher",
			sch_id : sch_id
		},
		type : "POST",
		dataType : "json",
		success : function(arr) {
			// 3. 得到tea_id，删除它的内容
			$("#tea_id").empty();// 删除元素的子元素
			$("#tea_id").append($("<option>===请选择教师===</option>"));// 4.添加头
			// 5. 循环遍历数组，把每个对象转换成<option>添加到tea_id中
			for ( var i = 0; i < arr.length; i++) {
				var option = $("<option>").val(arr[i].tea_id).text(
						arr[i].tea_name);
				$("#tea_id").append(option);
			}
		}
	});
}
// 加载二级分类
function loadChildren() {
	/*
	 * 1. 获取pid 2. 发出异步请求，功能之： 3. 得到一个数组 4. 获取cid元素(<select>)，把内部的<option>全部删除
	 * 5. 添加一个头（<option>请选择2级分类</option>） 6. 循环数组，把数组中每个对象转换成一个<option>添加到cid中
	 */
	// 1. 获取pcate_id
	var pid = $("#pcate_id").val();
	// 2. 发送异步请求
	$.ajax({
		async : true,
		cache : false,
		url : "/mooc/admin/AdminCourseServlet",
		data : {
			method : "ajaxFindChildren",
			pcate_id : pid
		},
		type : "POST",
		dataType : "json",
		success : function(arr) {
			// 3. 得到cate_id，删除它的内容
			$("#cate_id").empty();// 删除元素的子元素
			$("#cate_id").append($("<option>==请选择二级分类==</option>"));// 4.添加头
			// 5. 循环遍历数组，把每个对象转换成<option>添加到cid中
			for ( var i = 0; i < arr.length; i++) {
				var option = $("<option>").val(arr[i].cate_id).text(
						arr[i].cate_name);
				$("#cate_id").append(option);
			}
		}
	});
}
// 修改按钮的点击事件
function editForm() {
	if (confirm("您确定要修改吗？")) {
		$("#method").val("edit");
		$("#form").submit();
	}
}
// 删除按钮的点击事件
function deleteForm() {
	if (confirm("您确定要删除吗？")) {
		$("#method").val("delete");
		$("#form").submit();
	}
}