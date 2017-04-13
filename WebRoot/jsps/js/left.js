
	$(document).ready(function(){
		$(".cate_2").hide();
		$("#cate_list .cate_2:eq(0)").show();
		$("#cate_list p.cate_1").click(function(){
			$(this).addClass("current").next("div.cate_2").slideToggle(300)
			.parent().siblings().children("div.cate_2").slideUp("slow");
			$(this).parent().siblings().children("p.cate_1").removeClass("current");
		});
		$("#second .cate_2:eq(0)").show();
		$("#second p.cate_1").mouseover(function(){
			$(this).addClass("current").next("div.cate_2").slideDown(500)
			.parent().siblings().children("div.cate_2").slideUp("slow");
			$(this).parent().siblings().children("p.cate_1").removeClass("current");
		});
		
	});