package com.greatmooc.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greatmooc.domain.Reply;
import com.greatmooc.factory.DaoFactory;
import com.greatmooc.util.Time;

public class AddReplyServlet extends HttpServlet{
	Reply reply = new Reply();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String comId = request.getParameter("comId");
		String content = request.getParameter("content");
		String userSimg = request.getParameter("userSimg");
		String userId = request.getParameter("userId");
		reply.setAgreeNum(0);
		reply.setReplContent(content);
		reply.setUserName(userName);
		reply.setComId(Integer.parseInt(comId));
		reply.setUserSimg(userSimg);
		reply.setReplTime(Time.getTimestamp(new Date()));
		DaoFactory.getReplyInstance().add(reply);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
