package com.greatmooc.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greatmooc.domain.School;
import com.greatmooc.service.SchoolService;


import cn.itcast.servlet.BaseServlet;

public class SchoolServlet extends BaseServlet {
	private SchoolService schoolService =  new SchoolService();
	//查找所有的学校，由于学校比较少，就不采用分页
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<School> schools = schoolService.findAll();
		request.setAttribute("schools", schools);
		return "f:/jsps/school/list.jsp";
	}
	//加载单个学校的相信信息
	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sch_id =request.getParameter("sch_id");
		School school = schoolService.load(sch_id);
		request.setAttribute("school", school);
		return "f:/jsps/school/desc.jsp";
	}
}
