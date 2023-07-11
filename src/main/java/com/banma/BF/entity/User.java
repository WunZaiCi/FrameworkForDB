package com.banma.BF.entity;

import java.util.Date;

/**
 * 用户实体类
 * @author WHASSUPMAN
 *
 */
public class User {
	private  int uid; //用户id
	private  String username; //用户名
	private  String password;//密码
	private  String sex;//性别
	private  String headImage; //头像
	private  Date createTime;//创建时间
	private  Date updateTime;//更新时间
	
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", sex=" + sex
				+ ", headImage=" + headImage + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
	
}
