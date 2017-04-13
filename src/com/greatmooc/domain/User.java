package com.greatmooc.domain;

public class User {
	//数据库对应的属性
	private String user_id;//用户编号，
	private String email;//注册邮箱
	private String password;//密码
	private String username;//昵称
	private String sex;//性别
	private String user_desc;//个人签名	
	private String user_img;//用户的图像地址
	private boolean status;//激活状态
	private String activationCode;//激活码
	//剩下的表单属性
	private String confirmpass;//确认密码
	private String verifyCode;//验证码
	//修改密码
	private String newpassword;//新密码
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUser_desc() {
		return user_desc;
	}
	public void setUser_desc(String user_desc) {
		this.user_desc = user_desc;
	}
	
	public String getUser_img() {
		return user_img;
	}
	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getActivationCode() {
		return activationCode;
	}
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	public String getConfirmpass() {
		return confirmpass;
	}
	public void setConfirmpass(String confirmpass) {
		this.confirmpass = confirmpass;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", email=" + email + ", password="
				+ password + ", username=" + username + ", sex=" + sex
				+ ", user_desc=" + user_desc + ", user_img=" + user_img
				+  ", status=" + status
				+ ", activationCode=" + activationCode + ", confirmpass="
				+ confirmpass + ", verifyCode=" + verifyCode + ", newpassword="
				+ newpassword + "]";
	}
		
}
