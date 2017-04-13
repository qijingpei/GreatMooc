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

public class CommentListServlet extends HttpServlet{
	User user = new User();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String userId = request.getParameter("userId");
		Comment comment = new Comment();
		Video video = new Video();
		List<Comment> commentList = new ArrayList<Comment>();
		if (null != id) {
			video.setVid_id(id);
			comment.setVidId(id);
			user.setUser_id(userId);
		}
		try {
			commentList = DaoFactory.getCommentInstance().queryAll(comment);
			user = DaoFactory.getUserInstance().queryById(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("commentList", commentList);
		request.setAttribute("video", video);
		request.setAttribute("user", user);
		request.getRequestDispatcher("videoplayer/comment_list.jsp").forward(request, response);
	}
}
