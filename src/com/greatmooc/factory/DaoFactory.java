package com.greatmooc.factory;

import com.greatmooc.dao.AgreeDao;
import com.greatmooc.dao.CommentDao;
import com.greatmooc.dao.ReplyDao;
import com.greatmooc.dao.UsersDao;
import com.greatmooc.dao.VideosDao;
import com.greatmooc.proxy.AgreeDaoProxy;
import com.greatmooc.proxy.CommentDaoProxy;
import com.greatmooc.proxy.ReplyDaoProxy;
import com.greatmooc.proxy.UserDaoProxy;
import com.greatmooc.proxy.VideoDaoProxy;

public class DaoFactory {
	
	public static VideosDao getVideoInstance(){
		VideosDao dao = null;
		dao = new VideoDaoProxy();
		return dao;
	}
	
	public static CommentDao getCommentInstance(){
		CommentDao dao = null;
		dao = new CommentDaoProxy();
		return dao;
	}
	public static ReplyDao getReplyInstance(){
		ReplyDao dao = null;
		dao = new ReplyDaoProxy();
		return dao;
	}
	public static UsersDao getUserInstance(){
		UsersDao dao = null;
		dao = new UserDaoProxy();
		return dao;
	}
	public static AgreeDao getAgreeInstance(){
		AgreeDao dao = null;
		dao = new AgreeDaoProxy();
		return dao;
	}
}
