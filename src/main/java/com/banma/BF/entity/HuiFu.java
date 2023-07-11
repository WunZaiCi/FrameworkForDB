package com.banma.BF.entity;

import java.util.Date;

public class HuiFu {
	private  int hid;
	private  String content;
	private  int tid;
	private  int uid;
	private  Date createTime;
	private  Date updateTime;
	
	
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
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
		return "HuiFu [hid=" + hid + ", content=" + content + ", tid=" + tid + ", uid=" + uid + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
	
}
