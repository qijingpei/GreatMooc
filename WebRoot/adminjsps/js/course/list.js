$(function() {
	$("#addMore").click(function() {
		var count =$(".course").size();
		var html="";
		$.ajax({
			url:"/mooc/CourseServlet",
			data:{method:"ajaxAddMore",begin:count},
			type:"POST",
			dataType:"json",
			async:false,
			cache:false,
			success:function(courses){
				if(!courses){
					$("#addMore").text("暂时没有更多内容!");
					$("#addMore").unbind("click");
				}else{
					for(var i=0;i<courses.length;i++){
						html="<div class='course'>" +
						"<a href='/mooc/admin/AdminCourseServlet?method=load&cou_id="+courses[i].cou_id+"' target='body'>" +
								"<img src='/mooc/"+courses[i].cou_simg +"'/></a>"+
						"<p class='cname'>" +
						"<a href='/mooc/admin/AdminCourseServlet?method=load&cou_id="+courses[i].cou_id+"' target='body'>" +
						courses[i].cou_name+"</a></p>"+
						"<p class='related'>教师：<a href='#'>"+courses[i].teacher.tea_name+"</a></p>"+
						"<p class='related'><a href='#'>"+courses[i].school.sch_name +"</a></p>"+	
						"<p class='related'><span>"+courses[i].learn_num+"</span>正在学习&nbsp;&nbsp;"+
						"<span>"+courses[i].hour_length+"课时</span></p>"+
								"</div>";
						$(".list").append(html);
					}
				}//else
			}
		});//ajax
		
	});//click事件
	$("#addMoreByCate").click(function() {
		var count =$(".course").size();
		var cateid=$("input[name='cate_id']").val() ;
		var html="";
		$.ajax({
			url:"/mooc/CourseServlet",
			data:{method:"ajaxAddMore",begin:count,cate_id:cateid},
			type:"POST",
			dataType:"json",
			async:false,
			cache:false,
			success:function(courses){
				if(!courses){
					$("#addMoreByCate").text("暂时没有更多内容!");
					$("#addMoreByCate").unbind("click");
				}else{
					for(var i=0;i<courses.length;i++){
						html="<div class='course'>" +
						"<a href='#'><img src='/mooc/"+courses[i].cou_simg +"'/></a>"+
						"<p class='cname'><a href='#'>"+ courses[i].cou_name+"</a></p>"+
						"<p class='related'>教师：<a href='#'>"+courses[i].teacher.tea_name+"</a></p>"+
						"<p class='related'><a href='#'>"+courses[i].school.sch_name +"</a></p>"+	
						"<p class='related'><span>"+courses[i].learn_num+"</span>正在学习&nbsp;&nbsp;"+
						"<span>"+courses[i].hour_length+"课时</span></p>"+
								"</div>";
						$(".list").append(html);
					}
				}//else
			}
		});//ajax
		
	});//click事件
	$("#addMoreByCname").click(function() {
		var count =$(".course").size();
		var cname=$("input[name='cou_name']").val() ;
		var html="";
		$.ajax({
			url:"/mooc/CourseServlet",
			data:{method:"ajaxAddMore",begin:count,cou_name:cname},
			type:"POST",
			dataType:"json",
			async:false,
			cache:false,
			success:function(courses){
				if(!courses){
					$("#addMoreByCname").text("暂时没有更多内容!");
					$("#addMoreByCname").unbind("click");
				}else{
					
					for(var i=0;i<courses.length;i++){
						html="<div class='course'>" +
						"<a href='/mooc/jsps/course/cou_desc.jsp?cou_id="+courses[i].cou_id+"' target='_blank'>" +
								"<img src='/mooc/"+courses[i].cou_simg +"'/></a>"+
						"<p class='cname'>" +
						"<a href='/mooc/jsps/course/cou_desc.jsp?cou_id="+courses[i].cou_id+"' target='_blank'>"+ 
						courses[i].cou_name+"</a></p>"+
						"<p class='related'>教师：<a href='#'>"+courses[i].teacher.tea_name+"</a></p>"+
						"<p class='related'><a href='#'>"+courses[i].school.sch_name +"</a></p>"+	
						"<p class='related'><span>"+courses[i].learn_num+"</span>正在学习&nbsp;&nbsp;"+
						"<span>"+courses[i].hour_length+"课时</span></p>"+
								"</div>";
						$(".list").append(html);
					}
				}//else
			}
		});//ajax
		
	});//click事件
})

