package com.greatmooc.domain;



public class Teacher {
	private String tea_id;
	private String tea_name;
	private String tea_bimg;
	private String tea_simg;
	private String tea_desc;
	private String sch_id;
	private String tea_jiyu;
	private String tea_guandian;
	
	private School school;//一个学校的对象，由此可以知道这个教师所属的学校
	public String getTea_jiyu() {
		return tea_jiyu;
	}
	public void setTea_jiyu(String tea_jiyu) {
		this.tea_jiyu = tea_jiyu;
	}
	public String getTea_guandian() {
		return tea_guandian;
	}
	public void setTea_guandian(String tea_guandian) {
		this.tea_guandian = tea_guandian;
	}
	
	
	public String getSch_id() {
		return sch_id;
	}
	public void setSch_id(String sch_id) {
		this.sch_id = sch_id;
	}
	
	public String getTea_id() {
		return tea_id;
	}
	public void setTea_id(String tea_id) {
		this.tea_id = tea_id;
	}
	public String getTea_name() {
		return tea_name;
	}
	public void setTea_name(String tea_name) {
		this.tea_name = tea_name;
	}
	public String getTea_bimg() {
		return tea_bimg;
	}
	public void setTea_bimg(String tea_bimg) {
		this.tea_bimg = tea_bimg;
	}
	public String getTea_simg() {
		return tea_simg;
	}
	public void setTea_simg(String tea_simg) {
		this.tea_simg = tea_simg;
	}
	public String getTea_desc() {
		return tea_desc;
	}
	public void setTea_desc(String tea_desc) {
		this.tea_desc = tea_desc;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	@Override
	public String toString() {
		return "Teacher [tea_id=" + tea_id + ", tea_name=" + tea_name
				+ ", tea_bimg=" + tea_bimg + ", tea_simg=" + tea_simg
				+ ", tea_desc=" + tea_desc + ", sch_id=" + sch_id
				+ ", tea_jiyu=" + tea_jiyu + ", tea_guandian=" + tea_guandian
				+ ", school=" + school + "]";
	}

	
	
}
