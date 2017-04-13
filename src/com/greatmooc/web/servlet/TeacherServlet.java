package com.greatmooc.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;

import com.greatmooc.domain.Teacher;
import com.greatmooc.exception.CourseException;
import com.greatmooc.service.CourseService;
import com.greatmooc.service.TeacherService;



/*
 * 教师模块
 * servlet层
 */
public class TeacherServlet extends BaseServlet{
	private TeacherService teacherService=new TeacherService();
	private CourseService courseService=new CourseService();
	
	/*
	 * 查询所有
	 */		
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.调用service得到所有教师对象
		 * 2.保存到request域
		 * 3.转发到teacher/list.jsp
		 */
		request.setAttribute("teacherList", teacherService.findAll());
		//list.jsp更新页面，成了list_new.jsp
		return "f:/jsps/teacher/list_new.jsp";
	}
	
	/*
	 * 加载单个教师
	 */
	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.获取tea_id
		 * 2.使用tea_id调用service方法获得Teacher对象
		 * 3.把Teacher对象保存到request域中
		 * 		————新加：查询这个老师教的课，最多显示3个在页面中
		 * 4.转发到load.jsp页面显示
		 */
		String tea_id=request.getParameter("tea_id");
		Teacher teacher=teacherService.load(tea_id);
		request.setAttribute("teacher", teacher);
		
		/*
		 * 查询这个老师教授的课程,返回的是List<Course>
		 * 		>如果查询到的Course为空，那么这个老师还没有教课
		 */
		try {
			request.setAttribute("courseList", courseService.findByTea_id(tea_id));
		} catch (CourseException e) {//Course为空时
			request.setAttribute("courseList",null );//设置为空
			return "f:/jsps/teacher/desc.jsp";//转发
		}
		
		return "f:/jsps/teacher/desc.jsp";
		
		
	}
	

}
