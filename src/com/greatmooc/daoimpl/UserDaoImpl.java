package com.greatmooc.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.greatmooc.domain.User;
import com.greatmooc.dao.UsersDao;
import com.greatmooc.dbmanger.DBConnection;

public class UserDaoImpl implements UsersDao{
	private Connection con;
	private PreparedStatement stat = null;
	
	public UserDaoImpl(Connection con){
		this.con = con;
	}
	
	DBConnection db = new DBConnection();
	
	@Override
	public User queryById(User userId) {
		String sql = "SELECT * FROM user WHERE user_id = ?";
		User user = new User();
		ResultSet rs = null;
		con=db.getConnection();
		try {
			stat = con.prepareStatement(sql);
			stat.setString(1,userId.getUser_id());
			rs=stat.executeQuery();
			while(rs.next()){
				user.setUsername(rs.getString("username"));
				user.setUser_img(rs.getString("user_img"));
				
			}
			user.setUser_id(userId.getUser_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.close();
		return user;
	}

}
