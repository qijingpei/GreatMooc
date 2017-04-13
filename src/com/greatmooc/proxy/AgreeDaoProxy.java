package com.greatmooc.proxy;

import com.greatmooc.dao.AgreeDao;
import com.greatmooc.daoimpl.AgreeDaoImpl;
import com.greatmooc.dbmanger.DBConnection;
import com.greatmooc.domain.Agree;

public class AgreeDaoProxy implements AgreeDao{
	private DBConnection dbc;
	private AgreeDao dao = null;
	
	public AgreeDaoProxy(){
		dbc = new DBConnection();
		dao = new AgreeDaoImpl(dbc.getConnection());
	}
	@Override
	public boolean isAgree(Agree agree) {
		return dao.isAgree(agree);
	}

}
