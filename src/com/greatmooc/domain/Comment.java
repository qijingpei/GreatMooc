package com.greatmooc.domain;

import java.sql.Timestamp;

public class Comment {
	private int comId;
	private String userName;
	private String userSimg;
	private String vidId;
	private String comContent;
	private Timestamp comTime;
	private int agreeNum;
	public int getComId() {
		return comId;
	}
	public void setComId(int comId) {
		this.comId = comId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSimg() {
		return userSimg;
	}
	public void setUserSimg(String userSimg) {
		this.userSimg = userSimg;
	}
	public String getVidId() {
		return vidId;
	}
	public void setVidId(String vidId) {
		this.vidId = vidId;
	}
	public String getComContent() {
		return comContent;
	}
	public void setComContent(String comContent) {
		this.comContent = comContent;
	}
	public Timestamp getComTime() {
		return comTime;
	}
	public void setComTime(Timestamp comTime) {
		this.comTime = comTime;
	}
	public int getAgreeNum() {
		return agreeNum;
	}
	public void setAgreeNum(int agreeNum) {
		this.agreeNum = agreeNum;
	}
}
