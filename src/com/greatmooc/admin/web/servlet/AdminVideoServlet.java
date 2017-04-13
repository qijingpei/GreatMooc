package com.greatmooc.admin.web.servlet;



import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greatmooc.domain.Chapter;
import com.greatmooc.service.VideoService;

import cn.itcast.servlet.BaseServlet;

public class AdminVideoServlet extends BaseServlet {
	private VideoService videoService = new VideoService();
	public String addVideoPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String cou_id = request.getParameter("cou_id");
		String chap_id =request.getParameter("chap_id");
		List<Chapter> chapters = videoService.findAllChapter(cou_id);
		request.setAttribute("chapters", chapters);
		request.setAttribute("chap_id", chap_id);	
		request.setAttribute("cou_id", cou_id);
		return "f:/adminjsps/video/add.jsp";
	}
}
