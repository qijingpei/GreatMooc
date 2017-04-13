package com.greatmooc.dao;

import java.util.List;

import com.greatmooc.domain.Agree;
import com.greatmooc.domain.Comment;

public interface CommentDao {
	public void add(Comment comment);
	
	public List<Comment> queryAll(Comment comment);
	
	public void delete(Comment comment);
	
	public Comment queryById(Comment comment);
	
	public void update(Comment comment,Agree agree);
	
}
