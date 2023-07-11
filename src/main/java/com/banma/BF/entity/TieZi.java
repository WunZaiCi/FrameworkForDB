package com.banma.BF.entity;

import java.util.Date;
/**
 * 帖子的实体类
 * @author WHASSUPMAN
 *
 */
public class TieZi {
	private  int tid; //帖子id
	private  String title;//帖子标题
	private  String content;//帖子内容
	private  int uid;//用户id
	private  int bid;//板块id
	private  Date createTime;//创建时间
	private  Date updateTime;//更新时间
	
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
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
		return "TieZi [tid=" + tid + ", title=" + title + ", content=" + content + ", uid=" + uid + ", bid=" + bid
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
	
}
