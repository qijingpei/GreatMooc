package com.greatmooc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greatmooc.domain.Agree;
import com.greatmooc.domain.Comment;
import com.greatmooc.factory.DaoFactory;

/**
 * 点赞
 * @author smartclover
 *
 */
public class SmileOnServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String comId = request.getParameter("comId");
		String vidId = request.getParameter("vidId");
		String userId= request.getParameter("userId");
		Comment comment = new Comment();
		Agree agree = new Agree();
		agree.setComId(Integer.parseInt(comId));
		agree.setUseId(userId);
		List<Comment> commentList = new ArrayList<Comment>();
		if(DaoFactory.getAgreeInstance().isAgree(agree) == false){
			comment.setComId(Integer.parseInt(comId));
			comment.setVidId(vidId);
			DaoFactory.getCommentInstance().update(comment,agree);
		}
		commentList = DaoFactory.getCommentInstance().queryAll(comment);
		request.setAttribute("commnetList", commentList);
		request.getRequestDispatcher("/queryAllComment.action?id="+vidId).forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
