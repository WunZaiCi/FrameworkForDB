package com.banma.BF.vo;

import com.banma.BF.entity.HuiFu;
import com.banma.BF.entity.User;

public class HuiFuVO {
	/**
	 * 回复人信息
	 */
	private User user;
	
	/**
	 * 回复内容信息
	 */
	private HuiFu huifu;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public HuiFu getHuifu() {
		return huifu;
	}
	public void setHuifu(HuiFu huifu) {
		this.huifu = huifu;
	}
	
	
}
