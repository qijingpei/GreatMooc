package com.greatmooc.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.jdbc.TxQueryRunner;

import com.greatmooc.domain.Admin;



public class AdminDao {
	private QueryRunner qr=new TxQueryRunner();
	
	/*
	 * 按管理员姓名查询
	 */
	public Admin findByAdm_name(String adm_name) {
		String sql="select * from admin where adm_name=?";
		try {
			return qr.query(sql, new BeanHandler<Admin>(Admin.class), adm_name);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * 加载
	 */
	
	public Admin load(String adm_id) {
		String sql="select * from admin where adm_id=?";
		try {
			return qr.query(sql, new BeanHandler<Admin>(Admin.class), adm_id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	


	/*
	 * 编辑
	 */
	public void edit(String adm_id, Map<String, String> edit) {
		String sql="update admin set adm_name=?,adm_pwd=? where adm_id=?";
		Object[] params={edit.get("newname"),edit.get("newpwd"),adm_id};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	/*
	 * 检验是否超级管理员
	 */
	public boolean issuper(int adm_id) {
		String sql="select * from admin where adm_id=? and issuper=true";
		try {
			if((qr.query(sql, new ScalarHandler(),adm_id)!=null))
				return true;
			else 
				return false;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * 查询所有
	 */
	public List<Admin> findAll() {
		String sql="select * from admin";
		try {
			return qr.query(sql, new BeanListHandler<Admin>(Admin.class));
		} catch (SQLException e) {
			throw new RuntimeException("查询所有管理员时出现了错误");
		}
	}

	public void add(Map<String, Object> add) {
		String sql="insert into admin(adm_name,adm_pwd,issuper)  values(?,?,?)";
		
		try {
			Object[] params={add.get("newname"),add.get("newpwd"),add.get("issuper")};
			 qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException("添加管理员时出现了错误");
		}
		
	}

	public void delete(int adm_id) {
		String sql="delete from admin where adm_id=?";	
		try {
			
			 qr.update(sql, adm_id);
		} catch (SQLException e) {
			throw new RuntimeException("删除管理员时出现了错误");
		}
		
	}

	/*
	 * 升级
	 */
	public void levelup(int adm_id) {
		String sql="update admin set issuper=true where adm_id=?";
		try {
			qr.update(sql, adm_id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	/*
	 * 降级
	 */
	public void leveldown(int adm_id) {
		String sql="update admin set issuper=false where adm_id=?";
		try {
			qr.update(sql, adm_id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
