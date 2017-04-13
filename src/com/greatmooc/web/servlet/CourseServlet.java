package com.greatmooc.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greatmooc.domain.Chapter;
import com.greatmooc.domain.Course;
import com.greatmooc.domain.User;
import com.greatmooc.service.ChapterService;
import com.greatmooc.service.CourseService;
import com.greatmooc.util.JsonUtil;


import cn.itcast.servlet.BaseServlet;

public class CourseServlet extends BaseServlet {
	private CourseService courseService = new CourseService();
	private ChapterService chapterService = new ChapterService();
	//查找所有课程
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		List<Course> courses = courseService.findAll();
		request.setAttribute("courses", courses);
		request.setAttribute("addType", 1);//addType表示“加载更多按钮”的不同，1表示查找全部课程时的，2表示按分类时的，3表示模糊搜索时的，
		return "f:/jsps/course/list.jsp";
	}
	
	//ajaxAddMore,查找全部课程时，加载更多
	public String ajaxAddMore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.获取参数begin
		 * 2.通过begin调用service的findall方法，将返回的list赋值给Pagebean对象pb
		 * 3.向客户端传递校验结果
		 */
		String begin = request.getParameter("begin");
		String cate_id = request.getParameter("cate_id");
		String cou_name = request.getParameter("cou_name");
		List<Course> courses = new ArrayList<Course>();
		if(cate_id!=null){
			 courses = courseService.ajaxAddMore(Integer.parseInt(begin),"cate_id",cate_id);
		}else if(cou_name!=null){
			courses = courseService.ajaxAddMore(Integer.parseInt(begin),"cou_name",cou_name);
		}else {
			courses = courseService.ajaxAddMore(Integer.parseInt(begin),null,null);
		}

		String coursesJSON = JsonUtil.list2json(courses);
//		System.out.println(coursesJSON);
		if(coursesJSON.equals("[]")){
			response.getWriter().print(false);
		}
		else 
			response.getWriter().print(coursesJSON);
		return null;
	}
	
	//通过cate_id查询课程详细
	public String findByCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cate_id = request.getParameter("cate_id");
		List<Course> courses = courseService.findByCategory(cate_id);
		request.setAttribute("cate_id", cate_id);//"加载更多"时，要用分类id
		request.setAttribute("courses", courses);
		request.setAttribute("addType", 2);//addType表示“加载更多按钮”的不同，1表示查找全部课程时的，2表示按分类时的，3表示模糊搜索时的，
		return "f:/jsps/course/list.jsp";
	}
	
	//通过cou_id查询课程详细
	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cou_id = request.getParameter("cou_id");
		Course course = courseService.load(cou_id);

		//获取session中的user，判断视频是否学习过
		User user = (User) request.getSession().getAttribute("session_user");
		String user_id = null;
		if(user!=null)
			user_id = user.getUser_id();
		List<Chapter> chapters = chapterService.findAll(user_id,cou_id);
		course.setChapters(chapters);
		request.setAttribute("course", course);
		return "f:/jsps/course/desc.jsp";
	}
	
	//通过课程名模糊搜索课程
	public String searchByCou_name(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cou_name = request.getParameter("cou_name");
		List<Course> courses = courseService.searchByCou_name(cou_name);
		request.setAttribute("courses", courses);
		request.setAttribute("cou_name", cou_name);//加载更多时，要用这个参数
		request.setAttribute("addType", 3);//addType表示“加载更多按钮”的不同，1表示查找全部课程时的，2表示按分类时的，3表示模糊搜索时的，
		return "f:/jsps/course/list.jsp";
	}
	
	
}
