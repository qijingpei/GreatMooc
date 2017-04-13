package com.greatmooc.service;

import java.sql.SQLException;
import java.util.List;

import com.greatmooc.dao.ChapterDao;
import com.greatmooc.domain.Chapter;



public class ChapterService {
	private ChapterDao chapterDao = new ChapterDao();
	//查找所有章
	public List<Chapter> findAll(String user_id,String cou_id) {
		try {
			return chapterDao.findAll(user_id,cou_id);
		} catch (SQLException e) {
			throw new RuntimeException("查找所有章时出错了："+e);
		}
	}
	//通过id加载chapter详细
	public Chapter load(String chap_id) {
		try {
			return chapterDao.load(chap_id);
		} catch (SQLException e) {
			throw new RuntimeException("添加章节时出错了："+e);
		}
	}
	//按cou_id查找章节的个数，判断是否删除
	public int findChapterCountByCourse(String cou_id) {
		try {
			return chapterDao.findChapterCountByCour_id(cou_id);
		} catch (SQLException e) {
			throw new RuntimeException("按cou_id查找章个数时出错了："+e);
		}
	}
	//添加章节
	public void add(Chapter chapter) {
		try {
			chapterDao.add(chapter);
		} catch (SQLException e) {
			throw new RuntimeException("添加章节时出错了："+e);
		}
	}
	//修改章节
	public void edit(Chapter chapter) {
		try {
			chapterDao.edit(chapter);
		} catch (SQLException e) {
			throw new RuntimeException("修改章节时出错了："+e);
		}
	}
	
}
