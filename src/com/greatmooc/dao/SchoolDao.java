package com.greatmooc.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.greatmooc.domain.Course;
import com.greatmooc.domain.School;
import com.greatmooc.domain.Teacher;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class SchoolDao {
	private QueryRunner qr = new TxQueryRunner();
	//查找所有学校,每个school里面只有id,name,img,因为只有这三个暂时用得着
	public List<School> findAll() throws SQLException {
		String sql = "select sch_id,sch_name,sch_simg from school order by `orderBy`";
		List<School> schools = qr.query(sql, new BeanListHandler<School>(School.class));
		return schools;
	}
	
	/**
	 * 加载单个学校的详细信息,学校还包括所属他的课程和老师
	 * @param sch_id
	 * @return
	 * @throws SQLException 
	 */
	public School findBySch_id(String sch_id) throws SQLException {
		String sql = "select * from school where sch_id=?";
		School school = qr.query(sql, new BeanHandler<School>(School.class),sch_id);
		sql = "select c.cou_id,c.cou_name,c.cou_simg from course c where sch_id=? order by `orderBy`";
		List<Course> courses = qr.query(sql, new BeanListHandler<Course>(Course.class),sch_id);
		sql = "select t.tea_id,t.tea_name,t.tea_simg from teacher t where sch_id=?";
		List<Teacher> teachers = qr.query(sql, new BeanListHandler<Teacher>(Teacher.class),sch_id);
		
		school.setCourses(courses);
		school.setTeachers(teachers);
		return school;
	}
	/**
	 * 后台的添加school方法
	 * @param school
	 * @return
	 * @throws SQLException 
	 */
	public void add(School school) throws SQLException {
		String sql = "insert into school (sch_id,sch_name,sch_bimg,sch_simg,sch_desc) values(?,?,?,?,?)";
		Object[] params = {school.getSch_id(),school.getSch_name(),
				school.getSch_bimg(),school.getSch_simg(),school.getSch_desc()};
		qr.update(sql,params);
	}
	
	//修改学校信息
	public void edit(School school) throws SQLException {
		String sql = "update school set sch_name=?,sch_desc=? where sch_id=?";
		Object[] params = {school.getSch_name(),school.getSch_desc(),school.getSch_id()};
		if(school.getSch_id()!=null){
			qr.update(sql,params);
		}
	}
	public int findTeacherCountBySch_id(String sch_id) throws SQLException{
		String sql = "select count(*) from teacher where sch_id = ?";
		Number n = (Number) qr.query(sql, new ScalarHandler(), sch_id);
		return n==null?0:n.intValue();
	}

	public void delete(String sch_id) throws SQLException {
		String sql = "delete from school where sch_id=?";
		qr.update(sql,sch_id);
		
	}
}
