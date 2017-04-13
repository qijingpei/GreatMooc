package com.greatmooc.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.greatmooc.domain.Chapter;
import com.greatmooc.domain.Video;
import com.greatmooc.dao.VideosDao;
import com.greatmooc.dbmanger.DBConnection;

public class VideoDaoImpl implements VideosDao{
	private Connection con;
	private PreparedStatement stat = null;
	
	public VideoDaoImpl(Connection con){
		this.con = con;
	}
	
	DBConnection db = new DBConnection();
	
	//添加视频
	@Override
	public void add(Video video) {
		String sql = "INSERT INTO video(vid_id,vid_name,vid_num,minu_length,vid_path,chap_id) VALUES (?,?,?,?,?,?)";
		con=db.getConnection();
		try {
			stat = con.prepareStatement(sql);
			stat.setString(1, video.getVid_id());
			stat.setString(2, video.getVid_name());
			stat.setInt(3, video.getVid_num());
			stat.setInt(4, video.getMinu_length());
			stat.setString(5, video.getVid_path());
			stat.setString(6, video.getChapter().getChap_id());
			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void delete(Video video) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Video queryById(Video videoId) {
		String sql = "SELECT *FROM video WHERE vid_id=?";
		Video video = new Video();
		Chapter chapter = new Chapter();
		ResultSet rs=null;
		con=db.getConnection();
		try {
			stat = con.prepareStatement(sql);
			stat.setString(1, videoId.getVid_id());
			rs=stat.executeQuery();
			while(rs.next()){
				chapter.setChap_id(rs.getString("chap_id"));
				video.setVid_id(rs.getString("vid_id"));
				video.setVid_name(rs.getString("vid_name"));
				video.setVid_num(rs.getInt("vid_num"));
				video.setMinu_length(rs.getInt("minu_length"));
				video.setVid_path(rs.getString("vid_path"));
				video.setChapter(chapter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return video;
	}

	@Override
	public void update(Video video) {
		// TODO Auto-generated method stub
		
	}

}
