package com.greatmooc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.greatmooc.domain.Category;
import com.greatmooc.domain.Course;
import com.greatmooc.domain.School;
import com.greatmooc.domain.Teacher;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class CourseDao {
	private QueryRunner qr = new TxQueryRunner();
	//查找所有课程,先只显示12个，剩下的通过点击加载更多加载
	public List<Course> findAll() throws SQLException {
		//先用左外连接查询
		String sql = "select c.cou_id,c.cou_name,c.learn_num," +
				"c.hour_length,c.cou_simg," +"ca.cate_id,ca.cate_name," +
				"t.tea_id,t.tea_name,t.tea_simg," +
				"s.sch_id,s.sch_name,s.sch_simg FROM course c "+
			"LEFT OUTER JOIN category ca ON c.cate_id=ca.cate_id " +
			"LEFT OUTER JOIN teacher t ON c.tea_id=t.tea_id " +
			"LEFT OUTER JOIN school s ON c.sch_id=s.sch_id " +
			" order by c.orderBy limit ?,?";
		List<Map<String, Object>> mapList =  qr.query(sql, new MapListHandler(),0,9);
		List<Course> courses = toCourseList(mapList);				
		return courses;
	}
	/**
	 * 
	 * @param begin
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	public List<Course> ajaxAddMore(int begin,String param,String value) throws SQLException {
		String sql = "select c.cou_id,c.cou_name,c.learn_num," +
				"c.hour_length,c.cou_simg," +"ca.cate_id,ca.cate_name," +
				"t.tea_id,t.tea_name,t.tea_simg," +
				"s.sch_id,s.sch_name,s.sch_simg FROM course c "+
			"LEFT OUTER JOIN category ca ON c.cate_id=ca.cate_id " +
			"LEFT OUTER JOIN teacher t ON c.tea_id=t.tea_id " +
			"LEFT OUTER JOIN school s ON c.sch_id=s.sch_id " ;
		if(param!=null&&value!=null){
			if(param.equals("cate_id")&&value!=null){
				sql=sql+"where c.cate_id = ? ";
			}else if(param.equals("cou_name")&&value!=null){
				sql=sql+"where c.cou_name like ? ";
			}
		}
		sql = sql+ " order by c.orderBy limit ?,?";
			
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		if(param==null||value==null){
			mapList=  qr.query(sql, new MapListHandler(),begin,6);
		}else {
			mapList=  qr.query(sql, new MapListHandler(),value,begin,6);
		}
		List<Course> courses = toCourseList(mapList);				
		return courses;
	}
	//按分类id查找课程
	public List<Course> findByCate_id(String cate_id) throws SQLException {
		//先用左外连接查询
		String sql = "select c.cou_id,c.cou_name,c.learn_num," +
				"c.hour_length,c.cou_simg," +"ca.cate_id,ca.cate_name," +
				"t.tea_id,t.tea_name,t.tea_simg," +
				"s.sch_id,s.sch_name,s.sch_simg FROM course c "+
			"LEFT OUTER JOIN category ca ON c.cate_id=ca.cate_id " +
			"LEFT OUTER JOIN teacher t ON c.tea_id=t.tea_id " +
			"LEFT OUTER JOIN school s ON c.sch_id=s.sch_id " +
			"where c.cate_id=? order by c.orderBy limit ?,?";
		List<Map<String, Object>> mapList =  qr.query(sql, new MapListHandler(),cate_id,0,9);
		List<Course> courses = toCourseList(mapList);				
		return courses;
	}
	
	/**
	 * 按cou_id查找课程,在显示课程详细的界面中并没有分类的相关内容，那就不查，只查教师和学校
	 * @param cou_id
	 * @return
	 * @throws SQLException
	 */
	public Course findByCou_id(String cou_id) throws SQLException {
//		String sql = "select c.*,t.tea_id,t.tea_name,t.tea_simg," +
//				"s.sch_id,s.sch_name,s.sch_simg,ca.cate_id,ca.cate_name" +
//				" from course c,teacher t,school s ,category ca where cou_id=?" +
//				" and c.tea_id=t.tea_id and c.sch_id=s.sch_id and c.cate_id=ca.cate_id";
		//先用左外连接查询
		String sql = "select c.*,ca.cate_id,ca.cate_name,ca.pcate_id," +
				"t.tea_id,t.tea_name,t.tea_simg,t.tea_desc," +
			"s.sch_id,s.sch_name,s.sch_simg FROM course c "+
		"LEFT OUTER JOIN category ca ON c.cate_id=ca.cate_id " +
		"LEFT OUTER JOIN teacher t ON c.tea_id=t.tea_id " +
		"LEFT OUTER JOIN school s ON c.sch_id=s.sch_id " +
		"WHERE c.cou_id=?";
		Map<String, Object> map =  qr.query(sql, new MapHandler(),cou_id);
		Course course = CommonUtils.toBean(map, Course.class);
		
		/*
		 * 取出课程所属的二级分类，还有对应的一级分类id，映射到对应的对象，并建立他们之间的关系
		 * 【后台管理】的修改课程信息中，修改课程的分类时要用到
		 */
		Category category = CommonUtils.toBean(map, Category.class);
		String pcate_id = (String) map.get("pcate_id");
		if(category!=null){
			if(pcate_id!=null){
				Category parent = new Category();
				parent.setCate_id(pcate_id);
				category.setParent(parent);
			}
			course.setCategory(category);
		}
		Teacher teacher = CommonUtils.toBean(map, Teacher.class);
		if(teacher!=null)
			course.setTeacher(teacher);
		School school = CommonUtils.toBean(map, School.class);
		if(school!=null)
			course.setSchool(school);
		return course;
	} 
	/**
	 * 模糊搜索课程，把关键字一个个分开加上任意匹配符%
	 * @param cou_name
	 * @return
	 * @throws SQLException 
	 */
	public List<Course> searchByCou_name(String cou_name) throws SQLException {
		//先用左外连接查询
		String sql = "select c.cou_id,c.cou_name,c.learn_num," +
				"c.hour_length,c.cou_simg," +"ca.cate_id,ca.cate_name," +
				"t.tea_id,t.tea_name,t.tea_simg," +
				"s.sch_id,s.sch_name,s.sch_simg FROM course c "+
			"LEFT OUTER JOIN category ca ON c.cate_id=ca.cate_id " +
			"LEFT OUTER JOIN teacher t ON c.tea_id=t.tea_id " +
			"LEFT OUTER JOIN school s ON c.sch_id=s.sch_id " +
			"where cou_name like '%"+cou_name+"%' order by c.orderBy limit ?,?";
		List<Map<String, Object>> mapList =  qr.query(sql, new MapListHandler(),0,9);
		List<Course> courses = toCourseList(mapList);				
		return courses;
	}
	
	//查找二级分类下的课程个数，【后台管理】
	public int findCourseCountByCategory(String cate_id) throws SQLException {
		String sql = "select count(*) from course where cate_id=?";
		Number n = (Number) qr.query(sql, new ScalarHandler(),cate_id);
		return n==null?0:n.intValue();
	}
	//添加课程
	public void add(Course course) throws SQLException {
		String sql= "insert into course (cou_id,cou_name,cate_id,tea_id,sch_id," +
				"cou_desc,cou_bimg,cou_simg) values(?,?,?,?,?,?,?,?)";
		Object[] params = {course.getCou_id(),course.getCou_name(),course.getCategory().getCate_id(),
				course.getTeacher().getTea_id(),course.getSchool().getSch_id(),
				course.getCou_desc(),course.getCou_bimg(),course.getCou_simg()};
		qr.update(sql,params);	
	}
	//修改课程
	public void edit(Course course) throws SQLException {
		String sql= "update course set cou_name=?,cate_id=?,tea_id=?,sch_id=?," +
				"cou_desc=? where cou_id = ?";
		Object[] params = {course.getCou_name(),course.getCategory().getCate_id(),
				course.getTeacher().getTea_id(),course.getSchool().getSch_id(),
				course.getCou_desc(),course.getCou_id()};
		qr.update(sql,params);	
	}
	//删除课程
	public void delete(String cou_id) throws SQLException {
		String sql = "delete from course where cou_id=?";
		qr.update(sql,cou_id);
	}
	
	private List<Course> toCourseList(List<Map<String, Object>> mapList) {
		List<Course> courses = new ArrayList<Course>();
		for(Map<String, Object> map:mapList){
			Course course = toCourse(map);
			courses.add(course);
		}
		return courses;
	}

	private Course toCourse(Map<String, Object> map) {
		Course course = CommonUtils.toBean(map, Course.class);
		Category category = CommonUtils.toBean(map, Category.class);
		if(category!=null&&category.getCate_id()!=null) 
			course.setCategory(category);
		Teacher teacher = CommonUtils.toBean(map, Teacher.class);
		if(teacher!=null&&teacher.getTea_id()!=null)
			course.setTeacher(teacher);
		School school = CommonUtils.toBean(map, School.class);
		if(school!=null&&school.getSch_id()!=null)
			course.setSchool(school);
		return course;
	}
	/*
	 * 通过教师编号查询课程 （齐敬佩修改）
	 */
	public List<Course> findByTea_id(String tea_id) {
		String sql="select * from course where tea_id=? limit 0,3";
		try {
			return qr.query(sql, new BeanListHandler<Course>(Course.class), tea_id);
		} catch (SQLException e) {
			throw new RuntimeException(e+" findByTea_id()通过教师编号查询课程方法出错 ");
		}
	}
	
	
		
	
	
}

