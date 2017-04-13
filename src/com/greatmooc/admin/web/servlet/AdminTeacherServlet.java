package com.greatmooc.admin.web.servlet;
/*
 * 管理员界面的教师管理
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

import com.greatmooc.domain.School;
import com.greatmooc.domain.Teacher;
import com.greatmooc.service.SchoolService;
import com.greatmooc.service.TeacherService;



public class AdminTeacherServlet extends BaseServlet {
	private TeacherService teacherService=new TeacherService();
	private SchoolService schoolService=new SchoolService();
	/*
	 * 查询所有
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("teacherList", teacherService.findAll());
		return "f:/adminjsps/teacher/list.jsp";	
	}
	
	/*
	 * 加载
	 */
	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("schoolList", schoolService.findAll());
		request.setAttribute("teacher", teacherService.load(request.getParameter("tea_id")));
		return "f:/adminjsps/teacher/desc.jsp";
	}
	
	/*
	 * 查询所有学校
	 */
	public String findAllSchool(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("schoolList", schoolService.findAll());
		return "f:/adminjsps/teacher/add.jsp";
	}
	
	/*
	 * 编辑教师
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Teacher teacher=CommonUtils.toBean(request.getParameterMap(),Teacher.class);
		School school=CommonUtils.toBean(request.getParameterMap(), School.class);
		//System.out.println(school.getSch_id());
		teacher.setSchool(school);
		teacherService.edit(teacher);
		return findAll(request,response);
	}
	
	/*
	 * 删除教师
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tea_id=request.getParameter("tea_id");
		teacherService.delete(tea_id);
		return findAll(request,response);
	}

}
