package com.greatmooc.proxy;

import java.util.List;

import com.greatmooc.domain.Video;
import com.greatmooc.dao.VideosDao;
import com.greatmooc.daoimpl.VideoDaoImpl;
import com.greatmooc.dbmanger.DBConnection;

public class VideoDaoProxy implements VideosDao{
	private DBConnection dbc;
	private VideosDao dao = null;
	
	public VideoDaoProxy(){
		dbc = new DBConnection();
		dao = new VideoDaoImpl(dbc.getConnection());
	}
	
	@Override
	public void add(Video video) {
		dao.add(video);
		dbc.close();	
	}


	@Override
	public void delete(Video video) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Video queryById(Video video) {
		dbc.close();
		return dao.queryById(video);
	}

	@Override
	public void update(Video video) {
		// TODO Auto-generated method stub
		
	}

}
