package com.greatmooc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greatmooc.domain.Comment;
import com.greatmooc.domain.User;
import com.greatmooc.domain.Video;
import com.greatmooc.factory.DaoFactory;
import com.greatmooc.service.PersonService;

public class PlayVideoServlet extends HttpServlet{
	Video video = new Video();
	Comment comment = new Comment();
	List<Comment> commentList = new ArrayList<Comment>();
	User user = new User();
	//齐敬佩添加：
	private PersonService personService=new PersonService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String userId = request.getParameter("userId");
		
		int videoId = -1;
		if (null != id) {
			video.setVid_id(id);
			comment.setVidId(id);
			user.setUser_id(userId);
		}
		try {
			video = DaoFactory.getVideoInstance().queryById(video);
			commentList = DaoFactory.getCommentInstance().queryAll(comment);
			user = DaoFactory.getUserInstance().queryById(user);
			/*
			 * 齐敬佩修改：往user_course表中插入数据
			 * 
			 */
			//管理员账户的话就不插入数据了
			if(request.getSession().getAttribute("session_admin")==null) {
				String cou_id=request.getParameter("cou_id");
				personService.startLearning(userId,cou_id);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("commentList", commentList);
		request.setAttribute("video", video);
		request.setAttribute("user", user);
		request.getRequestDispatcher("videoplayer/video_player.jsp").forward(request, response);
	}
	
}
