package com.greatmooc.domain;

public class Admin {
	private int adm_id;//管理员编号
	private String adm_name;//管理员名字
	private String adm_pwd;//管理员密码
	public boolean issuper;//是否超级管理员，只有超级管理员才能添加管理员
	public int getAdm_id() {
		return adm_id;
	}
	public void setAdm_id(int adm_id) {
		this.adm_id = adm_id;
	}
	public String getAdm_name() {
		return adm_name;
	}
	public void setAdm_name(String adm_name) {
		this.adm_name = adm_name;
	}
	public String getAdm_pwd() {
		return adm_pwd;
	}
	public void setAdm_pwd(String adm_pwd) {
		this.adm_pwd = adm_pwd;
	}
	public boolean isIssuper() {
		return issuper;
	}
	public void setIssuper(boolean issuper) {
		this.issuper = issuper;
	}
	@Override
	public String toString() {
		return "Admin [adm_id=" + adm_id + ", adm_name=" + adm_name
				+ ", adm_pwd=" + adm_pwd + ", issuper=" + issuper + "]";
	}
	
	
	
}
