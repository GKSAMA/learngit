package com.qianfeng.entity;

import java.util.Date;

public class User {
	private Integer id;
	private String userName;
	private String password;
	private String email;
	private Integer isLock;
	private Date regDate;
	private Integer failure_num;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getIsLock() {
		return isLock;
	}
	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Integer getFailure_num() {
		return failure_num;
	}
	public void setFailure_num(Integer failure_num) {
		this.failure_num = failure_num;
	}
	
	
}
