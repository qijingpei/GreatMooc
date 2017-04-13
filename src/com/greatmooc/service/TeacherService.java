package com.greatmooc.service;

import java.sql.SQLException;
import java.util.List;

import com.greatmooc.dao.TeacherDao;
import com.greatmooc.domain.Teacher;



public class TeacherService {
	private TeacherDao teacherDao=new TeacherDao();
	
	/*
	 * 查询所有教师
	 */
	public List<Teacher> findAll() {
		//调用Dao的findAll方法
		return teacherDao.findAll();
		
	}

	/*
	 * 查询单个老师信息
	 */
	public Teacher load(String tea_id) {
		return teacherDao.load(tea_id);
	}

	/*
	 * 添加老师
	 */
	public void add(Teacher t) {
		teacherDao.add(t);
		
	}

	/*
	 * 删除教师
	 */
	public void delete(String tea_id) {
		teacherDao.delete(tea_id);
		
	}

	/*
	 * 编辑教师
	 */
	public void edit(Teacher teacher) {
		teacherDao.edit(teacher);
		
	}
	/*
	 * 按学校查询老师
	 */
	public List<Teacher> findBySchool(String sch_id) {
		try {
			return teacherDao.findBySch_id(sch_id);
		} catch (SQLException e) {
			throw new RuntimeException("按sch_id查找教师出错了"+e);
		}
	}
}
