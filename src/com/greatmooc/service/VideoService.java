package com.greatmooc.service;

import java.sql.SQLException;
import java.util.List;

import com.greatmooc.dao.VideoDao;
import com.greatmooc.domain.Chapter;
import com.greatmooc.domain.Video;

public class VideoService {
	private VideoDao videoDao = new VideoDao();
	
	//通过chap_id查找video的个数，判断是否删除
	public int findVideoCountByChapter(String chap_id) {
		try {
			return videoDao.findVideoCountByChap_id(chap_id);
		} catch (SQLException e) {
			throw new RuntimeException("按chap_id查找图书个数出错了："+e);
		}
	}
	public void add(Video video) {
		try {
			videoDao.add(video);
		} catch (SQLException e) {
			throw new RuntimeException("添加视频出错了："+e);
		}
	}
	
	//查找课程下所有的章节号，用来添加视频
	public List<Chapter> findAllChapter(String cou_id) {
		try {
			return videoDao.findAllChapter(cou_id);
		} catch (SQLException e) {
			throw new RuntimeException("查找所有章节时出错了："+e);
		}
	}
}
