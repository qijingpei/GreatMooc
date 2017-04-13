package com.greatmooc.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greatmooc.domain.Chapter;
import com.greatmooc.domain.User;
import com.greatmooc.service.ChapterService;


import cn.itcast.servlet.BaseServlet;
public class ChapterServlet extends BaseServlet {
	private ChapterService chapterService = new ChapterService();
	//查找所有章
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取参数 cou_id
		String cou_id = request.getParameter("cou_id");
		//获取session中的user，判断视频是否学习过
		User user = (User) request.getSession().getAttribute("session_user");
		String user_id = null;
		if(user!=null)
			user_id = user.getUser_id();
		List<Chapter> chapters = chapterService.findAll(user_id,cou_id);
		request.setAttribute("chapters", chapters);
		return "";
	}

}

