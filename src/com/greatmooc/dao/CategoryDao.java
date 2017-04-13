package com.greatmooc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.greatmooc.domain.Category;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class CategoryDao {
	private QueryRunner qr = new TxQueryRunner();
	/**
	 * 查找所有分类（包括一级分类和二级分类）
	 * @return
	 * @throws SQLException
	 */
	public List<Category> findAll() throws SQLException {
		/*
		 * 1.从数据库中查找出所有的一级分类，即pcate_id=null的分类
		 * 2.用MapList来接收返回的数据
		 * 3.把MapList转换成一个个单独的map
		 * 4.把map映射为category
		 * 5.查找一级分类下的子分类，并把他们设置给以及分类
		 */
		String sql = "select * from category where pcate_id is null order by `orderBy`";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler());
		List<Category> parentList = toCategoryList(mapList);
		//通过cate_id查找和设置一级分类的子分类
		for(Category parent:parentList){
			String pcate_id = parent.getCate_id();
			parent.setChildren(findChildren(pcate_id));
		}

		return parentList;
	}

	//为一级分类查找子分类
	public List<Category> findChildren(String pcate_id) {
		try {
			String sql = "select * from category where pcate_id=?";			
			List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(),pcate_id);
			if(mapList!=null){
			List<Category> children = toCategoryList(mapList);
			return children;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException("为一级分类查找所有的子分类时出错了："+e);
		}
	}
	//查找所有一级分类，【后台管理】
	public List<Category> findParents() throws SQLException {
		String sql = "select * from category where pcate_id is null order by `orderBy`";
		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler());
		List<Category> parents = toCategoryList(mapList);
		return parents;
	}
	/**
	 * 添加一级二级分类，【后台管理】
	 * @param category
	 * @throws SQLException 
	 */
	public void add(Category category) throws SQLException {
		String sql = "insert into category (cate_id,cate_name,pcate_id,cate_desc) values(?,?,?,?)";
		String pcate_id = null;
		if(category.getParent()!=null)
			pcate_id = category.getParent().getCate_id();
		qr.update(sql,category.getCate_id(),category.getCate_name(),pcate_id,category.getCate_desc());	
	}
	//修改分类之前的，加载分类信息，按id查找，【后台管理】
	public Category findByCate_id(String cate_id) throws SQLException {
		String sql = "select * from category where cate_id=?";
		Map<String , Object> map = qr.query(sql,new MapHandler(),cate_id);
		Category category = CommonUtils.toBean(map, Category.class);
		String pcate_id  = (String) map.get("pcate_id");
		if(pcate_id!=null){
			Category parent = new Category();
			parent.setCate_id(pcate_id);
			category.setParent(parent);
		}
		return category;
	}
	//修改分类  【后台管理】
	public void edit(Category category) throws SQLException {
		String sql = "update category set cate_name=?, pcate_id=?,cate_desc=? where cate_id=?";
		String pcate_id = null;
		if(category.getParent()!=null)
			pcate_id = category.getParent().getCate_id();
		Object[] params = {category.getCate_name(),pcate_id,
				category.getCate_desc(),category.getCate_id()};
		qr.update(sql,params);		
	}
	
	//查找一级分类下的二级分类的个数  【后台管理】
	public int findChildrenCount(String pcate_id) throws SQLException {
		String sql = "select count(*) from category where pcate_id=?";
		Number n = (Number) qr.query(sql, new ScalarHandler(),pcate_id);
		return n==null?0:n.intValue();
	}
	
	//删除分类 【后台管理】
	public void delete(String cate_id) throws SQLException {
		String sql = "delete from category where cate_id=?";
		qr.update(sql,cate_id);
	}

	

	//把mapList转化为categorylist
	private List<Category> toCategoryList(List<Map<String, Object>> mapList) {
		List<Category> categoryList = new ArrayList<Category>();
		for(Map<String, Object> map:mapList){
			Category category = toCategory(map);//把map转换为category
			categoryList.add(category);
		}
		return categoryList;
	}

	//把map映射给category
	private Category toCategory(Map<String, Object> map) {
		//将map映射为category，只丢失了pcate_id；
		Category category =CommonUtils.toBean(map, Category.class);
		//把pcate_id取出来，创建Category的parent对象，用pcate_id设置其cate_id，再把parent设置给category
		String pcate_id = (String) map.get("pcate_id");
		if(pcate_id!=null){
			Category parent = new Category();
			parent.setCate_id(pcate_id);
			category.setParent(parent);
		}
		return category;
	}


}
