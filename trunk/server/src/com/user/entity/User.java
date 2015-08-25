package com.user.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	
	private Integer userId;    //自增主键
	private String userNo;     //用户编码
	private String userName;   //用户名称
	private String userStatus; //是否在职 1在职 0离职
	
	public User() {
		super();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

}
