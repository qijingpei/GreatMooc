package com.greatmooc.domain;

import java.util.List;

public class Category {
	private String cate_id;//分类id
	private String cate_name;//分类名称
	private Category parent ;//父分类
	private String cate_desc;//分类描述
	private List<Category> children;//子分类
	public String getCate_id() {
		return cate_id;
	}
	public void setCate_id(String cate_id) {
		this.cate_id = cate_id;
	}
	public String getCate_name() {
		return cate_name;
	}
	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}
	public Category getParent() {
		return parent;
	}
	public void setParent(Category parent) {
		this.parent = parent;
	}
	public String getCate_desc() {
		return cate_desc;
	}
	public void setCate_desc(String cate_desc) {
		this.cate_desc = cate_desc;
	}
	public List<Category> getChildren() {
		return children;
	}
	public void setChildren(List<Category> children) {
		this.children = children;
	}
	
}
