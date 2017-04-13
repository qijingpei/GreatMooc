package com.greatmooc.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.greatmooc.domain.Agree;
import com.greatmooc.domain.Comment;
import com.greatmooc.dao.CommentDao;
import com.greatmooc.dbmanger.DBConnection;

public class CommentDaoImpl implements CommentDao {
	private Connection con;
	private PreparedStatement stat = null;
	private PreparedStatement stat_agree = null;
	
	public CommentDaoImpl(Connection con){
		this.con = con;
	}
	
	DBConnection db = new DBConnection();
	//插入一条评论
	@Override
	public void add(Comment comment) {
		String sql_com= "INSERT INTO comment(user_name,user_simg,vid_id,com_content,com_time,agree_num) VALUES (?,?,?,?,?,?)";
		con=db.getConnection();
		try {
			stat = con.prepareStatement(sql_com);
			stat.setString(1, comment.getUserName());
			stat.setString(2, comment.getUserSimg());
			stat.setString(3, comment.getVidId());
			stat.setString(4,comment.getComContent());
			stat.setTimestamp(5, comment.getComTime());
			stat.setInt(6, comment.getAgreeNum());
			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	//查询所有评论
	@Override
	public List<Comment> queryAll(Comment commentId) {
		String sql = "SELECT * FROM comment WHERE vid_id = ? order by com_id desc";
		List<Comment> commentList=new ArrayList<Comment>();
		ResultSet rs=null;
		con=db.getConnection();
		try {
			stat = con.prepareStatement(sql);
			stat.setString(1,commentId.getVidId());
			rs=stat.executeQuery();
			while(rs.next()){
				Comment comment = new Comment();
				comment.setComId(rs.getInt("com_id"));
				comment.setUserName(rs.getString("user_name"));
				comment.setUserSimg(rs.getString("user_simg"));
				comment.setVidId(rs.getString("vid_id"));
				comment.setComContent(rs.getString("com_content"));
				comment.setComTime(rs.getTimestamp("com_time"));
				comment.setAgreeNum(rs.getInt("agree_num"));
				commentList.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commentList;	
	}

	@Override
	public void delete(Comment comment) {
		// TODO Auto-generated method stub

	}
	//根据id查询
	@Override
	public Comment queryById(Comment commentId) {
		String sql = "SELECT * FROM comment WHERE vid_id = ?";
		Comment comment = new Comment();
		ResultSet rs = null;
		con = db.getConnection();
		try {
			stat = con.prepareStatement(sql);
			stat.setString(1,commentId.getVidId());
			rs=stat.executeQuery();
			while(rs.next()){
				comment.setComId(rs.getInt("com_id"));
				comment.setUserName(rs.getString("user_name"));
				comment.setUserSimg(rs.getString("user_simg"));
				comment.setVidId(rs.getString("vid_id"));
				comment.setComContent(rs.getString("com_content"));
				comment.setComTime(rs.getTimestamp("com_time"));
				comment.setAgreeNum(rs.getInt("agree_num"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comment;
	}

	@Override
	public void update(Comment commentId,Agree agree) {
		String sql = "UPDATE comment SET agree_num = (agree_num + 1) WHERE com_id = ?";
		String sql_agree = "INSERT INTO agree(use_id,com_id) VALUES (?,?)";
		con = db.getConnection();
		try {
			stat_agree = con.prepareStatement(sql_agree);
			stat_agree.setString(1, agree.getUseId());
			stat_agree.setInt(2, agree.getComId());
			stat_agree.execute();
			
			stat = con.prepareStatement(sql);
			stat.setInt(1, commentId.getComId());
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.close();
	}

}
