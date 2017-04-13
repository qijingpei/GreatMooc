package com.greatmooc.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greatmooc.domain.Category;
import com.greatmooc.service.CategoryService;


import cn.itcast.servlet.BaseServlet;

public class CategoryServlet extends BaseServlet {
	private CategoryService categoryService = new CategoryService();
	//查找所有分类
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.调用service的FindAll方法
		 * 2.将返回的list保存在request中
		 * 3.转发到显示分类的界面
		 */
		List<Category> parents = categoryService.findAll();
		request.setAttribute("parents", parents);
		return "f:/jsps/category/cate.jsp";
	}
}
