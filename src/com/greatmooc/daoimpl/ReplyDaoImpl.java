package com.greatmooc.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.greatmooc.domain.Reply;
import com.greatmooc.dao.ReplyDao;
import com.greatmooc.dbmanger.DBConnection;

public class ReplyDaoImpl implements ReplyDao {
	private Connection con;
	private PreparedStatement stat = null;
	
	public ReplyDaoImpl(Connection con){
		this.con = con;
	}
	
	DBConnection db = new DBConnection();
	
	//插入一条回复
	@Override
	public void add(Reply reply) {
		String sql = "INSERT INTO reply(com_id, repl_content, repl_time, agree_num, user_name, user_simg) VALUES (?,?,?,?,?,?)";
		con=db.getConnection();
		try {
			stat = con.prepareStatement(sql);
			stat.setInt(1, reply.getComId());
			stat.setString(2, reply.getReplContent());
			stat.setTimestamp(3, reply.getReplTime());
			stat.setInt(4, reply.getAgreeNum());
			stat.setString(5, reply.getUserName());
			stat.setString(6, reply.getUserSimg());
			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	//查询全部回复
	@Override
	public List<Reply> queryAll(Reply replyId) {
		String sql = "SELECT * FROM reply WHERE com_Id = ?";
		List<Reply> replyList=new ArrayList<Reply>();
		ResultSet rs=null;
		con=db.getConnection();
		try {
			stat = con.prepareStatement(sql);
			stat.setInt(1, replyId.getComId());
			rs=stat.executeQuery();
			while(rs.next()){
				Reply reply = new Reply();
				reply.setComId(rs.getInt("com_id"));
				reply.setReplContent(rs.getString("repl_content"));
				reply.setReplTime(rs.getTimestamp("repl_time"));
				reply.setAgreeNum(rs.getInt("agree_num"));
				reply.setUserName(rs.getString("user_name"));
				reply.setUserSimg(rs.getString("user_Simg"));
				replyList.add(reply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return replyList;
	}

}
