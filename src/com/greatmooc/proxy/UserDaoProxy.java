package com.greatmooc.proxy;

import com.greatmooc.domain.User;
import com.greatmooc.dao.UsersDao;
import com.greatmooc.daoimpl.UserDaoImpl;
import com.greatmooc.dbmanger.DBConnection;

public class UserDaoProxy implements UsersDao{
	private DBConnection dbc;
	private UsersDao dao = null;
	
	public UserDaoProxy(){
		dbc = new DBConnection();
		dao = new UserDaoImpl(dbc.getConnection());
	}
	@Override
	public User queryById(User user) {
		return dao.queryById(user);
	}
	
}
