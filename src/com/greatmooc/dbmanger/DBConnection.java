package com.greatmooc.dbmanger;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	private Connection con = null;
	private String driver = null;
	private String url = null;
	private String user = null;
	private String password = null;
	
	public DBConnection(){
		this.readValue();
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,user,password);
		} catch (Exception e) {
		}
	}
	
	public void readValue(){
		Properties props = new Properties();
		InputStream in;
		try {
			in = this.getClass().getResourceAsStream("/DB.properties");
			props.load(in);
			this.driver = props.getProperty("driver");
			this.url = props.getProperty("url");
			this.user = props.getProperty("user");
			this.password = props.getProperty("password");
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection(){
		return con;
	}
	
	public void close(){
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
