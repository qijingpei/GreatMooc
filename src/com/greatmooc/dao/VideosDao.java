package com.greatmooc.dao;


import com.greatmooc.domain.Video;


public interface VideosDao {
	public void add(Video video);
	
	public void delete(Video video);
	
	public Video queryById(Video video);
	
	public void update(Video video);
}
