package com.greatmooc.service;

import java.sql.SQLException;
import java.util.List;

import com.greatmooc.dao.CourseDao;
import com.greatmooc.domain.Course;
import com.greatmooc.exception.CourseException;


public class CourseService {
	private CourseDao courseDao = new CourseDao();
	/*
	 * 查询特定老师教授的课程（齐敬佩修改）
	 */
	public List<Course> findByTea_id(String tea_id) throws CourseException {
		List<Course> courseList=courseDao.findByTea_id(tea_id);
		if(courseList==null) {
			throw new CourseException("这个老师没有教课");
		}
		return courseList;
	}
	
	//查找所有课程
	public List<Course> findAll() {		
		try {
			return courseDao.findAll();
		} catch (SQLException e) {
			throw new RuntimeException("查找所有课程时出错了"+e);
		}
	}
	//按二级分类查找课程
	public List<Course> findByCategory(String cate_id) {
		try {
			return courseDao.findByCate_id(cate_id);
		} catch (SQLException e) {
			throw new RuntimeException("按分类查找课程时出错了"+e);
		}
	}
	//加载课程的详细
	public Course load(String cou_id) {
		try {
			return courseDao.findByCou_id(cou_id);
		} catch (SQLException e) {
			throw new RuntimeException("加载课程详细时出错了"+e);
		}
	}
	//模糊搜索课程
	public List<Course> searchByCou_name(String cou_name) {
		try {//从0开始，先显示12个课程（少于12个，则有多少显示多少）
			return courseDao.searchByCou_name(cou_name);
		} catch (SQLException e) {
			throw new RuntimeException("模糊搜索课程出错了"+e);
		}
	}
	//ajax加载更多
	public List<Course> ajaxAddMore(int begin,String param,String value) {

		try {
			return courseDao.ajaxAddMore(begin,param,value);
		} catch (SQLException e) {
			throw new RuntimeException("ajax加载课程出错了"+e);
		}
	}	
	
	//查找二级分类下的课程个数 【后台管理】
	public int findCourseCountByCategory(String cate_id) {
		try {
			return courseDao.findCourseCountByCategory(cate_id);
		} catch (SQLException e) {
			throw new RuntimeException("查找二级分类下的课程个数时出错了");
		}
	}
	//添加课程
	public void add(Course course) {
		try {
			courseDao.add(course);
		} catch (SQLException e) {
			throw new RuntimeException("添加课程时出错了");
		}
	}
	//修改课程信息
	public void edit(Course course) {
		try {
			courseDao.edit(course);
		} catch (SQLException e) {
			throw new RuntimeException("添加课程时出错了");
		}
	}
	//删除课程
	public void delete(String cou_id) {
		try {
			courseDao.delete(cou_id);
		} catch (SQLException e) {
			throw new RuntimeException("添加课程时出错了");
		}
	}
	
	
}
