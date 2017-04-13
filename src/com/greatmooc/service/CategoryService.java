package com.greatmooc.service;

import java.sql.SQLException;
import java.util.List;

import com.greatmooc.dao.CategoryDao;
import com.greatmooc.domain.Category;

public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();
	//查找所有分类
	public List<Category> findAll() {
		try {
			return categoryDao.findAll();
		} catch (SQLException e) {
			throw new RuntimeException("查找所有分类时出错了");
		}
	}
	//后台管理的查找所有一级分类
	public List<Category> findParents() {
		try {
			return categoryDao.findParents();
		} catch (SQLException e) {
			throw new RuntimeException("查找所有一级分类时出错了");
		}
	}
	//后台管理的添加一级分类
	public void add(Category category) {
		try {
			categoryDao.add(category);
		} catch (SQLException e) {
			throw new RuntimeException("添加分类时出错了");
		}
		
	}
	//后台管理的修改分类之前的加载分类信息
	public Category load(String cate_id) {		
		try {
			return categoryDao.findByCate_id(cate_id);
		} catch (SQLException e) {
			throw new RuntimeException("按id查找分类时出错了");
		}
	}
	//后台管理的修改分类
	public void edit(Category category) {
		try {
			categoryDao.edit(category);
		} catch (SQLException e) {
			throw new RuntimeException("修改分类时出错了");
		}
	}
	//查找一级分类下的二级分类的个数，判断是否删除
	public int findChildrenCount(String pcate_id) {
		try {
			return categoryDao.findChildrenCount(pcate_id);
		} catch (SQLException e) {
			throw new RuntimeException("查找一级分类下的二级分类的个数时出错了");
		}
	}
	//删除分类
	public void delete(String cate_id) {
		try {
			categoryDao.delete(cate_id);
		} catch (SQLException e) {
			throw new RuntimeException("删除分类时出错了");
		}
	}
	public List<Category> findChildren(String pcate_id) {
			return categoryDao.findChildren(pcate_id);	
	}
	
}
