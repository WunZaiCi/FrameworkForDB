package com.banma.bean;

import com.banma.annotation.Column;
import com.banma.annotation.Table;

@Table("sys_user")
public class User {
	
	@Column(isId = true,value="user_id")
	private String id;
	
	private String username;
	
	@Column("name")
	private String nickname;
	private String password;
	private String phone;
	
	
	
	 
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", nickname=" + nickname + ", password=" + password
				+ ", phone=" + phone + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
