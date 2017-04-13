package com.greatmooc.service;

import java.sql.SQLException;
import java.util.List;

import com.greatmooc.dao.SchoolDao;
import com.greatmooc.domain.School;
import com.greatmooc.exception.DeleteException;



public class SchoolService {
	private SchoolDao schoolDao = new SchoolDao();
	//查找所有的学校
	public List<School> findAll() {
		try {
			return schoolDao.findAll();
		} catch (SQLException e) {
			throw new RuntimeException("查找所有学校时出错了："+e);
		}
	}
	//加载单个学校的详细信息
	public School load(String sch_id) {
		try {
			return schoolDao.findBySch_id(sch_id);
		} catch (SQLException e) {
			throw new RuntimeException("加载学校详细时出错了："+e);
		}
	}
	//后台添加school
	public void add(School school) {
		try {
			schoolDao.add(school);
		} catch (SQLException e) {
			throw new RuntimeException("添加学校出错了："+e);
		}
	}
	//修改学校的信息
	public void edit(School school) {
		try {
			schoolDao.edit(school);
		} catch (SQLException e) {
			throw new RuntimeException("修改学校出错了："+e);
		}
	}
	public void delete(String sch_id) throws DeleteException{
		try {
			int n = schoolDao.findTeacherCountBySch_id(sch_id);
			if(n==0) schoolDao.delete(sch_id);
			else throw new DeleteException("该学校之下有教师，不能删除！");
		} catch (SQLException e) {
			throw new RuntimeException("修改学校出错了："+e);
		}
	}
}
