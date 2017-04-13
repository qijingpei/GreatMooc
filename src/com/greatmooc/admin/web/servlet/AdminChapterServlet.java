package com.greatmooc.admin.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greatmooc.domain.Chapter;
import com.greatmooc.domain.Course;
import com.greatmooc.service.ChapterService;
import com.greatmooc.service.VideoService;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class AdminChapterServlet extends BaseServlet {
	private ChapterService chapterService = new ChapterService();
	//查找和显示所有章节一级章节下的视频
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cou_id = request.getParameter("cou_id");
		List<Chapter> chapters = chapterService.findAll(null,cou_id);
		request.setAttribute("chapters", chapters);
		request.setAttribute("cou_id", cou_id);//添加章节时要用到
		return "f:/adminjsps/chapter/list.jsp";
	}
	
	//添加章节
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map map = request.getParameterMap();
		Chapter chapter = CommonUtils.toBean(map, Chapter.class);
		Course course = CommonUtils.toBean(map, Course.class);
		chapter.setCourse(course);
		chapter.setChap_id(CommonUtils.uuid());
		
		chapterService.add(chapter);
		return findAll(request,response);
	}
	//删除
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String chap_id = request.getParameter("chap_id");
		int count =new VideoService().findVideoCountByChapter(chap_id);
		if(count>0){
			request.setAttribute("msg", "该章节下有视频，不能删除！");
			return "f:/adminjsps/msg.jsp";
		}
		return "f:/adminjsps/chapter/list.jsp";
	}
	//修改之前
	public String editPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String chap_id = request.getParameter("chap_id");
		Chapter chapter = chapterService.load(chap_id);
		request.setAttribute("chapter", chapter);
		
		String cou_id = request.getParameter("cou_id");
		request.setAttribute("cou_id",cou_id);
		return "f:/adminjsps/chapter/edit.jsp";
	}
	
	public String edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map map = request.getParameterMap();
		Chapter chapter = CommonUtils.toBean(map, Chapter.class);
		
		String cou_id = request.getParameter("cou_id");
		request.setAttribute("cou_id",cou_id);
		chapterService.edit(chapter);
		return findAll(request,response);
	}
}
