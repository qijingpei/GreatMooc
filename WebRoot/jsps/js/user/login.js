function _change() {
	$("#vCode").attr("src", "/mooc/VerifyCodeServlet?a=" + new Date().getTime());
}
$(function() {	
	/*
	 * 1. 给登录按钮添加submit()事件，完成表单校验
	 */
	$("#submitBtn").submit(function(){
		var bool = true;
		$(".input").each(function() {
			var inputName = $(this).attr("name");
			if(!invokeValidateFunction(inputName)) {
				bool = false;
			}
		});
		return bool;
	});
	
	/*
	 * 2. 输入框得到焦点时隐藏错误信息
	 */
	$(".input").focus(function() {
		var inputName = $(this).attr("name");
		$("#" + inputName + "Error").css("display", "none");
	});
	
	/*
	 * 3. 输入框失去焦点时进行校验
	 */
	$(".input").blur(function() {
		var inputName = $(this).attr("name");
		invokeValidateFunction(inputName);
	});
});

/*
 * 输入input名称，调用对应的validate方法。
 * 例如input名称为：email，那么调用validateEmail()方法。
 */
function invokeValidateFunction(inputName) {
	inputName = inputName.substring(0, 1).toUpperCase() + inputName.substring(1);
	var functionName = "validate" + inputName;
	return eval(functionName + "()");	
}

/*
 * 校验登录名
 */
function validateEmail() {
	var bool = true;
	$("#emailError").css("display", "none");
	var value = $("#email").val();
	if(!value) {// 非空校验
		$("#emailError").css("display", "");
		$("#emailError").text("邮箱不能为空！");
		bool = false;
	}else if(!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(value)){//email格式校验
		$("#emailError").css("display", "");
		$("#emailError").text("邮箱格式错误！");
		bool=false;
	}else{
	//ajax注册校验
		$.ajax({
			//7大参数
			url:"/mooc/UserServlet",//要请求的servlet
			data:{method:"ajaxValidateEmail",email:value},//给服务器的参数
			type:"POST",
			dataType:"json",
			async:false,//是否异步请求，如果是异步请求，则不等服务器返回，我们这个函数就直接向下运行
			cache:false,
			success:function(result){
				if(result)//为true时，数据库结果为0，则用户不存在
					{
						$("#emailError").css("display", "");
						$("#emailError").text("该用户不存在！");
						bool=false;
					}
			}
		});
	};
	//要通过检验，
	return bool;
}

/*
 * 校验密码
 */
function validatePassword() {
	var bool = true;
	$("#passwordError").css("display", "none");
	var value = $("#password").val();
	if(!value) {// 非空校验
		$("#passwordError").css("display", "");
		$("#passwordError").text("密码不能为空！");
		bool = false;
	} else if(value.length < 6 || value.length > 20) {//长度校验
		$("#passwordError").css("display", "");
		$("#passwordError").text("密码长度必须在6 ~ 20之间！");
		bool = false;
	}
	return bool;
}

/*
 * 校验验证码
 */
function validateVerifyCode() {
	var bool = true;
	$("#verifyCodeError").css("display", "none");
	var value = $("#verifyCode").val();
	if(!value) {//非空校验
		$("#verifyCodeError").css("display", "");
		$("#verifyCodeError").text("验证码不能为空！");
		bool = false;
	} else if(value.length != 4) {//长度不为4就是错误的
		$("#verifyCodeError").css("display", "");
		$("#verifyCodeError").text("错误的验证码！");
		bool = false;
	} else {//验证码是否正确
		$.ajax({
			cache: false,
			async: false,
			type: "POST",
			dataType: "json",
			data: {method: "ajaxValidateVerifyCode", verifyCode: value},
			url: "/mooc/UserServlet",
			success: function(flag) {
				if(!flag) {
					$("#verifyCodeError").css("display", "");
					$("#verifyCodeError").text("验证码错误！");
					bool = false;					
				}
			}
		});
	}
	return bool;
}
