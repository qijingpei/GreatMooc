package com.greatmooc.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;

import com.greatmooc.dao.UserDao;
import com.greatmooc.domain.User;
import com.greatmooc.exception.UserException;

import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;


public class UserService {
	private UserDao userDao = new UserDao();
	//注册时ajax检测邮箱是否已经注册过
	public boolean ajaxValidateEmail(String email){
		try {
			return userDao.ajaxValidateEmail(email);
		} catch (SQLException e) {
			throw new RuntimeException("ajax检测邮箱是否已经注册过这里出错了"+e);
		}
	}
	//注册功能
	public void regist(User user){
		/*
		 * 1.补全add方法中的user属性
		 * 2.调用UserDao的add方法添加到数据库
		 * 3.发送激活邮件
		 */
		//1.补全user_id，status=0，activationCode
		user.setUser_id(CommonUtils.uuid());
		user.setStatus(false);//表示未激活
		user.setActivationCode(CommonUtils.uuid()+CommonUtils.uuid());
		//2.添加
		try {
			userDao.add(user);
		} catch (SQLException e) {
			throw new RuntimeException("注册后添加用户这里出错了"+e);
		}
		//3.发送激活邮件
		Properties pops = new Properties();
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("email_template.properties");
		try {
			pops.load(input);
		} catch (IOException e) {
			throw new RuntimeException("加载email配置文件这里出错了"+e);
		}
		//3.1登录到邮箱服务器得到session
		String host = pops.getProperty("host");//邮箱服务器的主机名
		String name = pops.getProperty("username");//登录名
		String pass = pops.getProperty("password");//登录密码
		//创建session
		Session session = MailUtils.createSession(host, name, pass);
		//3.2创建Mail对象
		String from = pops.getProperty("from");//邮件发件人
		String to = user.getEmail();//收件人
		String subject = pops.getProperty("subject");//邮件的主题
		//MessageFormat.format方法会把第一个参数的占位符{0}，用第二个参数填充
		String content = MessageFormat.format(pops.getProperty("content"), user.getActivationCode());

		Mail mail = new Mail(from,to,subject,content);//用这4个参数来创建mail对象
		//3.3发送邮件
		try {
			MailUtils.send(session, mail);
		} catch (MessagingException e) {
			throw new RuntimeException("发送激活邮件时出错了"+e);
		} catch (IOException e) {
			throw new RuntimeException("发送激活邮件时出错了"+e);
		}		
	}
	//通过激活码来激活账号
	public void activation(String code) throws UserException{
		try {
			User user = userDao.findByActivationCode(code);
			if(user == null){
				throw new UserException("无效的激活码，请不要乱搞，你们城里人真会玩！");
			}
			if(user.isStatus() == true){
				throw new UserException("您已经激活过了，请不要二次激活好吗？！");
			}
			userDao.updateStatus(user.getUser_id(), true);
		} catch (SQLException e) {
			throw new RuntimeException("按激活码查找用户时出错了"+e);
		}
		
	}
	//通过邮箱查找用户
	public User login(String email) {
		try {
			return userDao.findByEmail(email);
		} catch (SQLException e) {
			throw new RuntimeException("按邮箱查找用户时出错了"+e);
		}
	}
	
	public void updatePassword(String user_id, String password,
			String newpassword) throws UserException {
		/*
		 * 1.通过userdao方法验证原密码是否正确
		 * 	若不正确，抛出异常
		 * 2.修改密码
		 */
		try {
			boolean b=userDao.findByUser_idAndPassword(user_id, password);//b为true说明密码正确
			if(!b) throw new UserException("原密码错误！");
			else userDao.updatePassword(user_id,newpassword);
		} catch (SQLException e) {
			throw new RuntimeException("修改密码时按用户id和密码查找出错了："+e);
		}
		
	}
	/*
	 * 齐敬佩添加的按照id查询用户信息
	 */
	public User findByUser_id(String user_id) {
		return userDao.findByUser_id(user_id);
	}
	
	
}
