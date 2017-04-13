package com.greatmooc.domain;

import java.util.List;


public class Course {
	private String cou_id;//课程id
	private String cou_name;//课程名
	private Category category;//所属二级分类
	private Teacher teacher;//教师id
	private School school;//学校
	private String cou_desc;//课程概述
	private int learn_num;//学习人数
	private int hour_length;//总课时时时长的小时部分，单位，小时
	private int minu_length;//总课时长的分钟部分，单位，分钟
	private String cou_bimg;//课程大图
	private String cou_simg;//课程小图
	private List<Chapter> chapters;//拥有的章节
	public String getCou_id() {
		return cou_id;
	}
	public void setCou_id(String cou_id) {
		this.cou_id = cou_id;
	}
	public String getCou_name() {
		return cou_name;
	}
	public void setCou_name(String cou_name) {
		this.cou_name = cou_name;
	}
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public String getCou_desc() {
		return cou_desc;
	}
	public void setCou_desc(String cou_desc) {
		this.cou_desc = cou_desc;
	}
	public int getLearn_num() {
		return learn_num;
	}
	public void setLearn_num(int learn_num) {
		this.learn_num = learn_num;
	}
	
	public int getHour_length() {
		return hour_length;
	}
	public void setHour_length(int hour_length) {
		this.hour_length = hour_length;
	}
	public int getMinu_length() {
		return minu_length;
	}
	public void setMinu_length(int minu_length) {
		this.minu_length = minu_length;
	}
	public String getCou_bimg() {
		return cou_bimg;
	}
	public void setCou_bimg(String cou_bimg) {
		this.cou_bimg = cou_bimg;
	}
	public String getCou_simg() {
		return cou_simg;
	}
	public void setCou_simg(String cou_simg) {
		this.cou_simg = cou_simg;
	}
	public List<Chapter> getChapters() {
		return chapters;
	}
	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}
	
}
