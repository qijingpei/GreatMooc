package com.greatmooc.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;

import com.greatmooc.domain.School;
import com.greatmooc.domain.Teacher;



/*
 * Teacher的
 * Dao层
 */
public class TeacherDao {
	private QueryRunner qr=new TxQueryRunner();
	
	/*
	 * 插入测试数据(成功)
	 
	public void insert() {
		String sql="insert into teacher values(?,?,?,?,?,?,?)";
		int i=0;
		for(i=0;i<10;i++) {
			Object[] params={i,"teacher_"+i,"","","我是一个高尚的老师","s00001",i+1};
			try {
				qr.update(sql, params);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}*/
	
	/*
	 * 查找所有教师(并给教师匹配对应的学校)
	 */
	public List<Teacher> findAll() {
		try {
			String sql="select * from teacher where del=false order by tea_name";
			//返回一个BeanList类型，其中包含所有的教师对象
			List<Teacher> teacher= qr.query(sql, new BeanListHandler<Teacher>(Teacher.class));
			
			//获取所有学校的信息
			/*
			 * 根据学校判断是否要加where del=false
			 */
			sql="select * from school";
			List<School> school=qr.query(sql, new BeanListHandler<School>(School.class));
			
			//通过sch_id给每个教师匹配学校
			for(Teacher t:teacher) {
				for(School s:school) {
					if(t.getSch_id()!=null && t.getSch_id().equals(s.getSch_id())) //如果sch_id相等
					{
						t.setSchool(s);
					}	//给找到学校的老师教师设置学校				
				}				
			}
			return teacher;			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
	}

	/*
	 * 查询单个老师,返回一个Teacher对象。并匹配其对应的学校
	 */
	public Teacher load(String tea_id) {
		try {
			String sql="select * from teacher where tea_id=? and del=false";		
			Teacher t= qr.query(sql, new BeanHandler<Teacher>(Teacher.class), tea_id);
			//查询其对应的学校
			sql="select * from school where sch_id=?";
			School s=qr.query(sql, new BeanHandler<School>(School.class),t.getSch_id());
			
			t.setSchool(s);
			return t;				
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void add(Teacher t) {
		String sql="insert into teacher values(?,?,?,?,?,?,?,?,?)";
		Object[] params={t.getTea_id(),t.getTea_name(),t.getTea_bimg(),t.getTea_simg(),t.getTea_desc(),t.getSchool().getSch_id(),
				t.getTea_jiyu(),t.getTea_guandian(),"0"};//0表示没有删除
		
		try {
			qr.update(sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	/*
	 * 删除教师
	 */
	public void delete(String tea_id) {
		String sql="update teacher set del=true where tea_id=?";
		try {
			qr.update(sql,tea_id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public void edit(Teacher teacher) {
		String sql="update teacher set tea_name=?,"
				+ "tea_bimg=?,tea_simg=?,tea_desc=?,sch_id=?,tea_jiyu=?,tea_guandian=? where tea_id=?";
		Object[] params={teacher.getTea_name(),teacher.getTea_bimg(),teacher.getTea_simg()
				,teacher.getTea_desc(),teacher.getSchool().getSch_id(),teacher.getTea_jiyu(),teacher.getTea_guandian(),teacher.getTea_id()};		
		try {
			qr.update(sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	/*
	 * 按照学校查询教师
	 */
	public List<Teacher> findBySch_id(String sch_id) throws SQLException {
		String sql = "select tea_id,tea_name from teacher where sch_id=?";
		return qr.query(sql, new BeanListHandler<Teacher>(Teacher.class), sch_id);
	}
}
