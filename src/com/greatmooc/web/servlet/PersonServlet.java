package com.greatmooc.web.servlet;
/*
 * 我的课程：查找“已关注”、“学习中”、“已学完”三种类型的课程
 */
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;

import com.greatmooc.domain.Chapter;
import com.greatmooc.domain.Course;
import com.greatmooc.domain.User;
import com.greatmooc.service.ChapterService;
import com.greatmooc.service.CourseService;
import com.greatmooc.service.PersonService;
import com.greatmooc.service.SchoolService;
import com.greatmooc.service.TeacherService;



public class PersonServlet extends BaseServlet {
	private PersonService personService=new PersonService();
	private TeacherService teacherService =new TeacherService();
	private SchoolService schoolService=new SchoolService();
	private CourseService courseService=new CourseService();
	private ChapterService chapterService=new ChapterService();
	
	/*
	 * 查询“已关注课程”（非 Javadoc）
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public String findFollowCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user=(User) request.getSession().getAttribute("session_user");
		String user_id=user.getUser_id();		
		request.setAttribute("courseList",personService.findFollowCourse(user_id));
		return "f:/jsps/person/right.jsp";
	}
	
	/*
	 * 查询“正在学习的课程”
	 */
	public String findLearningCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user=(User) request.getSession().getAttribute("session_user");
		String user_id=user.getUser_id();
		request.setAttribute("courseList", personService.findLearningCourse(user_id));
		return "f:/jsps/person/right.jsp";
	}
	
	/*
	 * 查询“已学完的课程”
	 */
	public String findFinishedCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user=(User) request.getSession().getAttribute("session_user");
		String user_id=user.getUser_id();		
		request.setAttribute("courseList", personService.findFinishedCourse(user_id));
		request.setAttribute("type","true");
		return "f:/jsps/person/right.jsp";
	}
	
	/*
	 * 删除“已关注课程”或者“学习中课程”
	 * 
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cou_id=request.getParameter("cou_id");
		String user_id=request.getParameter("user_id");
		personService.delete(user_id,cou_id);
		
		return findFollowCourse(request, response);
	}
	
	/*
	 * 关注课程:就是往user_course数据库表添加合适的纪录
	 * 		>如果没登陆提醒登陆(已经通过过滤器来完成了)
	 */
	public String notice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user_id=request.getParameter("user_id");
		String cou_id=request.getParameter("cou_id");
		//关注课程
		personService.notice(user_id,cou_id);
		Course course = courseService.load(cou_id);

		//获取session中的user，判断视频是否学习过
		User user = (User) request.getSession().getAttribute("session_user");
		user_id = null;
		if(user!=null)
			user_id = user.getUser_id();
		List<Chapter> chapters = chapterService.findAll(user_id,cou_id);
		course.setChapters(chapters);
		request.setAttribute("course", course);
		return "f:/jsps/course/desc.jsp";	
	}
}
