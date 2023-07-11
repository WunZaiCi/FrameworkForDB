package com.banma.BF.vo;

import java.util.Date;

public class TieZiVO {
	private  int tid; //帖子id
	private  String title;//帖子标题
	private  String username;//发帖人
	private  int huiFuCount;//发帖人
	private  Date updateTime;//更新时间
	
	
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getHuiFuCount() {
		return huiFuCount;
	}
	public void setHuiFuCount(int huiFuCount) {
		this.huiFuCount = huiFuCount;
	}

	
}
