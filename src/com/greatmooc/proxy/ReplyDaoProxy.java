package com.greatmooc.proxy;

import java.util.List;

import com.greatmooc.domain.Reply;
import com.greatmooc.dao.ReplyDao;
import com.greatmooc.daoimpl.ReplyDaoImpl;
import com.greatmooc.dbmanger.DBConnection;

public class ReplyDaoProxy implements ReplyDao {
	private DBConnection dbc;
	private ReplyDao dao = null;
	
	public ReplyDaoProxy(){
		dbc = new DBConnection();
		dao = new ReplyDaoImpl(dbc.getConnection());
	}
	
	@Override
	public void add(Reply reply) {
		dao.add(reply);
		dbc.close();
	}

	@Override
	public List<Reply> queryAll(Reply reply) {
		List<Reply> list = dao.queryAll(reply);
		dbc.close();
		return list;
	}

}
