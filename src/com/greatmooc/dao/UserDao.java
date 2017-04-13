package com.greatmooc.dao;

import java.sql.SQLException;




import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.greatmooc.domain.User;




import cn.itcast.jdbc.TxQueryRunner;

public class UserDao {
	private QueryRunner qr = new TxQueryRunner();
	//ajax邮箱注册校验
	public boolean ajaxValidateEmail(String email) throws SQLException{
		String sql = "select count(*) from `user` where email=?";
		Number n = (Number) qr.query(sql, new ScalarHandler(),email);
		return n.intValue()==0;//不存在为真，表示可以注册		
	}
	//注册后的添加用户
	public void add(User user) throws SQLException{
		String sql = "insert into `user`(user_id,email,username,`password`,sex,`status`," +
				"activationCode) values (?,?,?,?,?,?,?)" ;
		Object[] params = {user.getUser_id(),user.getEmail(),user.getUsername(),
			user.getPassword(),	user.getSex(),user.isStatus(),user.getActivationCode()};
		qr.update(sql,params);		
	}
	//按激活码查找用户
	public User findByActivationCode(String code) throws SQLException{
		String sql = "select * from `user` where activationCode=?";
		return qr.query(sql, new BeanHandler<User>(User.class),code);
	}
	//修改激活状态
	public void updateStatus(String user_id,boolean status) throws SQLException{
		String sql = "update `user` set `status`=? where user_id=?";
		qr.update(sql,status,user_id);
	}
	public User findByEmail(String email) throws SQLException {
		String sql = "select * from `user` where email=?";		
		return qr.query(sql, new BeanHandler<User>(User.class),email);
	}
	//修改密码时，按用户id和密码查找，若查出记录，则说明原密码正确
	public boolean findByUser_idAndPassword(String user_id, String password) throws SQLException {
		String sql="select count(*) from `user` where user_id=? and `password`=?";
		Number n=(Number) qr.query(sql, new ScalarHandler(),user_id,password);
		return n.intValue()>0;
	}

	//修改密码
	public void updatePassword(String user_id, String newpassword) throws SQLException {
		String sql="update `user` set password=? where user_id=?";
		qr.update(sql, newpassword,user_id);	
	}
	/*
	 * 齐敬佩添加！！的按照id查询用户信息
	 */
	public User findByUser_id(String user_id) {
		String sql="select * from user where user_id=?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class),user_id);
		} catch (SQLException e) {
			throw new RuntimeException(e+"查询用户信息时出错了");
		}
	}
}
