package com.greatmooc.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greatmooc.domain.Comment;
import com.greatmooc.domain.User;
import com.greatmooc.factory.DaoFactory;
import com.greatmooc.util.Time;

public class AddCommentServlet extends HttpServlet{
	Comment comment = new Comment();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String vidId = request.getParameter("vidId");
		String content = request.getParameter("content");
		String userSimg = request.getParameter("userSimg");
		comment.setAgreeNum(0);
		comment.setComContent(content);
		comment.setUserName(userName);
		comment.setVidId(vidId);
		comment.setUserSimg(userSimg);
		comment.setComTime(Time.getTimestamp(new Date()));
		DaoFactory.getCommentInstance().add(comment);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
