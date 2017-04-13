package com.greatmooc.domain;

import java.sql.Timestamp;

public class Reply {
	private int replId;
	private int comId;
	private String replContent;
	private Timestamp replTime;
	private int agreeNum;
	private String userName;
	private String userSimg;
	public String getUserName() {
		return userName;
	}
	public String getUserSimg() {
		return userSimg;
	}
	public void setUserSimg(String userSimg) {
		this.userSimg = userSimg;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getReplId() {
		return replId;
	}
	public void setReplId(int replId) {
		this.replId = replId;
	}
	public int getComId() {
		return comId;
	}
	public void setComId(int comId) {
		this.comId = comId;
	}
	public String getReplContent() {
		return replContent;
	}
	public void setReplContent(String replContent) {
		this.replContent = replContent;
	}
	public Timestamp getReplTime() {
		return replTime;
	}
	public void setReplTime(Timestamp replTime) {
		this.replTime = replTime;
	}
	public int getAgreeNum() {
		return agreeNum;
	}
	public void setAgreeNum(int agreeNum) {
		this.agreeNum = agreeNum;
	}
}
