package com.greatmooc.domain;


public class Video {
	private String vid_id;//视频id
	private String vid_name;//视频名称
	private int vid_num;//视频在章下的编号
	private int minu_length;//视频的时长，单位分钟
	private String vid_path;//视频路径
	private int like_num;//点赞数
	private Chapter chapter;//所属章节
	
	private boolean learned;//session里的用户是否学过，video表中没有对应的属性，只要user-video有记录就它设置为false。
	public String getVid_id() {
		return vid_id;
	}
	public void setVid_id(String vid_id) {
		this.vid_id = vid_id;
	}
	public String getVid_name() {
		return vid_name;
	}
	public void setVid_name(String vid_name) {
		this.vid_name = vid_name;
	}
	public int getVid_num() {
		return vid_num;
	}
	public void setVid_num(int vid_num) {
		this.vid_num = vid_num;
	}
	public int getMinu_length() {
		return minu_length;
	}
	public void setMinu_length(int minu_length) {
		this.minu_length = minu_length;
	}
	public String getVid_path() {
		return vid_path;
	}
	public void setVid_path(String vid_path) {
		this.vid_path = vid_path;
	}
	public int getLike_num() {
		return like_num;
	}
	public void setLike_num(int like_num) {
		this.like_num = like_num;
	}
	public Chapter getChapter() {
		return chapter;
	}
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
	public boolean isLearned() {
		return learned;
	}
	public void setLearned(boolean learned) {
		this.learned = learned;
	}
	@Override
	public String toString() {
		return "Video [vid_id=" + vid_id + ", vid_name=" + vid_name
				+ ", vid_num=" + vid_num + ", minu_length=" + minu_length
				+ ", vid_path=" + vid_path + ", like_num=" + like_num
				+ ", chapter=" + chapter + ", learned=" + learned + "]";
	}
	
}
