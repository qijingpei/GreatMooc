package com.greatmooc.domain;

import java.util.List;


public class Chapter {
	private String chap_id;//章节id
	private int chap_num ;//第几章
	private String chap_name;//章节名称
	private int hour_length;//章节的课时时长的小时部分，单位，小时
	private int minu_length;//章节的课时时长的分钟部分，单位，分
	private Course course ;//所属的课程
	private List<Video> videos ;//章节下面的视频列表
	public String getChap_id() {
		return chap_id;
	}
	public void setChap_id(String chap_id) {
		this.chap_id = chap_id;
	}
	public int getChap_num() {
		return chap_num;
	}
	public void setChap_num(int chap_num) {
		this.chap_num = chap_num;
	}
	public String getChap_name() {
		return chap_name;
	}
	public void setChap_name(String chap_name) {
		this.chap_name = chap_name;
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
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public List<Video> getVideos() {
		return videos;
	}
	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
	
}
