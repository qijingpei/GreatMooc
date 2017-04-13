package com.greatmooc.admin.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greatmooc.domain.School;
import com.greatmooc.exception.DeleteException;
import com.greatmooc.service.SchoolService;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;


public class AdminSchoolServlet extends BaseServlet {

	private SchoolService schoolService =  new SchoolService();
	//查找所有的学校，由于学校比较少，就不采用分页
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<School> schools = schoolService.findAll();
		request.setAttribute("schools", schools);
		return "f:/adminjsps/school/list.jsp";
	}
	//加载单个学校的相信信息
	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sch_id =request.getParameter("sch_id");
		School school = schoolService.load(sch_id);
		request.setAttribute("school", school);
		return "f:/adminjsps/school/desc.jsp";
	}
	public String edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map map = request.getParameterMap();
		School school = CommonUtils.toBean(map, School.class);
		if(school!=null){
			schoolService.edit(school);
		}
		return load(request,response);
	}

	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sch_id = request.getParameter("sch_id");
		try {
			schoolService.delete(sch_id);
		} catch (DeleteException e) {
			request.setAttribute("msg", "该学校下有教师，不能删除！");
			return load(request,response);
		}
		return findAll(request,response);
	}
}
