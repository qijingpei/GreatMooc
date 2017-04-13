package com.greatmooc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greatmooc.domain.Comment;
import com.greatmooc.domain.Reply;
import com.greatmooc.domain.User;
import com.greatmooc.factory.DaoFactory;

public class ReplyListServlet extends HttpServlet{
	User user = new User();
	Comment comment = new Comment();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Reply reply = new Reply();
		List<Reply> replyList = new ArrayList<Reply>();
		request.setCharacterEncoding("UTF-8");
		int comId = Integer.parseInt(request.getParameter("comId"));
		String userId = request.getParameter("userId");
		reply.setComId(comId);
		comment.setComId(comId);
		user.setUser_id(userId);;
		replyList = DaoFactory.getReplyInstance().queryAll(reply);
		user = DaoFactory.getUserInstance().queryById(user);
		request.setAttribute("replyList", replyList);
		request.setAttribute("user", user);
		request.setAttribute("comment", comment);
		request.getRequestDispatcher("videoplayer/reply_list.jsp").forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
