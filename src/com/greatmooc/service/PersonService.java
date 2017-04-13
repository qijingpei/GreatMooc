package com.greatmooc.service;
//person的service层
import java.util.List;

import com.greatmooc.dao.PersonDao;
import com.greatmooc.domain.Course;
import com.greatmooc.domain.User;





public class PersonService {
	private PersonDao personDao=new PersonDao();

	/*
	 * 查询“已关注课程”
	 */
	public List<Course> findFollowCourse(String user_id) {
		return personDao.findFollowCourse(user_id);
	}

	/*
	 * 查询“正在学习的课程”
	 */
	public List<Course> findLearningCourse(String user_id) {
		return personDao.findLearningCourse(user_id);
	}

	/*
	 * 查询“已学完”的课程
	 */
	public List<Course> findFinishedCourse(String user_id) {
		return personDao.findFinishedCourse(user_id);
	}

	/*
	 * 用户自己编辑用户信息（头像、昵称、个性签名）
	 */
	public void edit(User user) {
		personDao.edit(user);
			
	}

	/*
	 * 按用户id查询用户，返回User对象
	 */
	public User findByUser_id(String user_id) {
		//System.out.println("2"+personDao.findByUser_id(user_id));
		return personDao.findByUser_id(user_id);
	}

	/*
	 * 删除关注课程和学习中的课程
	 */
	public void delete(String user_id, String cou_id) {
		personDao.delete(user_id,cou_id);
		
	}

	/*
	 * 关注课程
	 */
	public void notice(String user_id, String cou_id) {
		personDao.notice(user_id,cou_id);
		
	}

	/*
	 * 开始学习课程
	 */
	public void startLearning(String userId, String cou_id) {
		String user_id=userId;
		personDao.startLearning(user_id,cou_id);		
	}
}
