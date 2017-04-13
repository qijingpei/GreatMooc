package com.greatmooc.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.greatmooc.domain.Chapter;
import com.greatmooc.domain.Video;

import cn.itcast.jdbc.TxQueryRunner;

public class VideoDao {
	private QueryRunner qr = new TxQueryRunner();

	public int findVideoCountByChap_id(String chap_id) throws SQLException {
		String sql = "select count(*) from video where chap_id=?";
		Number n = (Number) qr.query(sql, new ScalarHandler(),chap_id);
		return n==null?0:n.intValue();
	}
	//后台添加视频
	public void add(Video video) throws SQLException {
		String sql = "insert into `video`(vid_id,vid_name,vid_num,vid_path,chap_id) values(?,?,?,?,?)";
		Object[] params = {video.getVid_id(),video.getVid_name(),video.getVid_num(),
				video.getVid_path(),video.getChapter().getChap_id()};
		qr.update(sql,params);
	}
	
	//通过cou_id查找课程下所有的章节号，用来添加视频
	public List<Chapter> findAllChapter(String cou_id) throws SQLException {
		String sql= "select chap_id,chap_name from chapter where cou_id=?";
		List<Chapter> chapters = qr.query(sql, new BeanListHandler<Chapter>(Chapter.class),cou_id);
		return chapters;
		
	}
}
