package com.greatmooc.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.greatmooc.dao.AgreeDao;
import com.greatmooc.dbmanger.DBConnection;
import com.greatmooc.domain.Agree;

public class AgreeDaoImpl implements AgreeDao{
	private Connection con;
	private PreparedStatement stat = null;
	
	public AgreeDaoImpl(Connection con){
		this.con = con;
	}
	
	DBConnection db = new DBConnection();
	
	@Override
	public boolean isAgree(Agree agree) {
		String sql = "SELECT * FROM agree WHERE use_id = ? AND com_id = ?";
		ResultSet rs=null;
		con=db.getConnection();
		try {
			stat = con.prepareStatement(sql);
			stat.setString(1,agree.getUseId());
			stat.setInt(2, agree.getComId());
			rs=stat.executeQuery();
			rs.last();
			
			if(rs.getRow() > 0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args){
		Agree agree = new Agree();
		agree.setComId(8);
		agree.setUseId("11111111");
		DBConnection dbc = new DBConnection();
		new AgreeDaoImpl(dbc.getConnection()).isAgree(agree);
	}
}
