package com.greatmooc.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.jdbc.TxQueryRunner;

import com.greatmooc.domain.Course;
import com.greatmooc.domain.User;





/*
 * person的数据层
 */
public class PersonDao {
	private QueryRunner qr=new TxQueryRunner();
	/*
	 * 查找“已关注课程”
	 */
	public List<Course> findFollowCourse(String user_id) {
		String sql="select * from course where cou_id in(select cou_id from user_course where user_id=? and follow=true ) ";
		try {
			return qr.query(sql, new BeanListHandler<Course>(Course.class),user_id);			
		} catch (SQLException e) {
			throw new RuntimeException(e+"查找已关注课程时出现了问题！");
		}
	}
	/*
	 * 查找“正在学习的课程”
	 */
	public List<Course> findLearningCourse(String user_id) {
		String sql="select * from course where cou_id in(select cou_id from user_course where user_id=? and type=false and follow=false) ";
		try {
			return qr.query(sql, new BeanListHandler<Course>(Course.class),user_id);
		} catch (SQLException e) {
			throw new RuntimeException(e+"查找正在学习的课程时出现了问题！");
		}
	}
	/*
	 * 查找“已学完的课程”
	 */
	public List<Course> findFinishedCourse(String user_id) {
		String sql="select * from course where cou_id in(select cou_id from user_course where user_id=? and type=true) ";
		try {
			return qr.query(sql, new BeanListHandler<Course>(Course.class),user_id);
		} catch (SQLException e) {
			throw new RuntimeException(e+"查找已学完的课程时出现了问题！");
		}
	}
	
	/*
	 * 更改用户的信息（头像、昵称、个人签名）
	 */
	public void edit(User user) {		
		String sql="update user set user_img=?,username=?,user_desc=? where user_id=?";
		try {
			Object[] params={user.getUser_img(),user.getUsername(),user.getUser_desc(),user.getUser_id()};
			//System.out.println(params);
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e+"更改用户的信息（头像、昵称、个人签名）时出现了问题！");
		}
		
	}
	/*
	 * 按用户id查询用户，返回User对象
	 */
	public User findByUser_id(String user_id) {
		String sql="select * from user where user_id=?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class),user_id);
		} catch (SQLException e) {
			throw new RuntimeException(e+"按用户id查询用户，返回User对象时出现了问题！");
		}
	}
	
	/*
	 * 删除关注课程或学习中课程
	 */
	public void delete(String user_id, String cou_id) {
		String sql="delete from user_course where user_id=? and cou_id=?";
		try {
			//System.out.println("user_id="+user_id);
		//	System.out.println("cou_id="+cou_id);
			Object[] params={user_id,cou_id};
			qr.update(sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e+"删除关注课程或学习中课程时出现了问题！");
		}
		
	}
	
	/*
	 * 关注课程
	 */
	public void notice(String user_id, String cou_id) {
		//1.查看user_course表中有没有这人这课的记录
		//  >有的话更新
		//	>没有的话插入
		String sql="select * from user_course where user_id=? and cou_id=? ";
		try {
			Object o=qr.query(sql, new ScalarHandler(),user_id,cou_id);
			//不为空，更新
			if(o !=null) {
				sql="update user_course set follow=true where user_id=? and cou_id=?";
				qr.update(sql,user_id,cou_id);
			}
			//为空，插入
			if(o ==null) {
				sql="insert into user_course values(?,?,?,?)";
				Object[] params={user_id,cou_id,true,false};
				qr.update(sql, params);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e+"关注课程时出错了");
		}
		
	}
	/*
	 * 开始学习课程
	 */
	public void startLearning(String user_id, String cou_id) {
		//1.查看user_course表中有没有这人这课的记录
				//  >有的话更新
				//	>没有的话插入
				String sql="select * from user_course where user_id=? and cou_id=? ";
				try {
					Object o=qr.query(sql, new ScalarHandler(),user_id,cou_id);
					//不为空，更新
					if(o !=null) {
						sql="update user_course set follow=false,type=false where user_id=? and cou_id=?";
						qr.update(sql,user_id,cou_id);
					}
					//为空，插入
					if(o ==null) {
						sql="insert into user_course values(?,?,?,?)";
						Object[] params={user_id,cou_id,false,false};
						qr.update(sql, params);
					}
				} catch (SQLException e) {
					throw new RuntimeException(e+"开始学习课程时出错了");
				}
		
	}
	
}	
