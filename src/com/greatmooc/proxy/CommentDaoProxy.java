package com.greatmooc.proxy;

import java.util.List;

import com.greatmooc.domain.Agree;
import com.greatmooc.domain.Comment;
import com.greatmooc.dao.CommentDao;
import com.greatmooc.daoimpl.CommentDaoImpl;
import com.greatmooc.dbmanger.DBConnection;

public class CommentDaoProxy implements CommentDao {
	private DBConnection dbc;
	private CommentDao dao = null;
	
	public CommentDaoProxy(){
		dbc = new DBConnection();
		dao = new CommentDaoImpl(dbc.getConnection());
	}
	
	@Override
	public void add(Comment comment) {
		dao.add(comment);
		dbc.close();	
	}

	@Override
	public List<Comment> queryAll(Comment comment) {
		List<Comment> list = dao.queryAll(comment);
		dbc.close();
		return list;
	}

	@Override
	public void delete(Comment comment) {
		// TODO Auto-generated method stub

	}

	@Override
	public Comment queryById(Comment comment) {
		dbc.close();
		return dao.queryById(comment);
	}

	@Override
	public void update(Comment comment,Agree agree) {
		dao.update(comment,agree);
	}

}
