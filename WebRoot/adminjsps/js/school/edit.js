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