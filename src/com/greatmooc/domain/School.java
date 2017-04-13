package com.greatmooc.domain;

import java.util.List;

public class School {
	private String sch_id;//学校id
	private String sch_name;//学校名称
	private String sch_bimg;//学校大图
	private String sch_simg;//学校小图【大小图不同，大图是比较个性的，代表学校特色的，小图是标明学校名称】
	private String sch_desc;//学校简介 	
	private List<Course> courses;//该学校有在慕课上多少课程
	private List<Teacher> teachers;//该学校在慕课上有多少教师
	public String getSch_id() {
		return sch_id;
	}
	public void setSch_id(String sch_id) {
		this.sch_id = sch_id;
	}
	public String getSch_name() {
		return sch_name;
	}
	public void setSch_name(String sch_name) {
		this.sch_name = sch_name;
	}
	public String getSch_bimg() {
		return sch_bimg;
	}
	public void setSch_bimg(String sch_bimg) {
		this.sch_bimg = sch_bimg;
	}
	public String getSch_simg() {
		return sch_simg;
	}
	public void setSch_simg(String sch_simg) {
		this.sch_simg = sch_simg;
	}
	public String getSch_desc() {
		return sch_desc;
	}
	public void setSch_desc(String sch_desc) {
		this.sch_desc = sch_desc;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public List<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}
	@Override
	public String toString() {
		return "School [sch_id=" + sch_id + ", sch_name=" + sch_name
				+ ", sch_bimg=" + sch_bimg + ", sch_simg=" + sch_simg
				+ ", sch_desc=" + sch_desc + ", courses=" + courses
				+ ", teachers=" + teachers + "]";
	}
	
}
