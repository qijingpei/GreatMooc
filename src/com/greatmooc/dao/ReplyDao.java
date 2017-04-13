package com.greatmooc.dao;

import java.util.List;

import com.greatmooc.domain.Reply;

public interface ReplyDao {
	public void add(Reply reply);
	public List<Reply> queryAll(Reply reply);
}
