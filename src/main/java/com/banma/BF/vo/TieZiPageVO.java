package com.banma.BF.vo;

import java.util.List;

import com.banma.BF.entity.BanKuai;
import com.banma.BF.entity.TieZi;
import com.banma.BF.entity.User;

public class TieZiPageVO {
	/**
	 * 所属板块
	 */
	private BanKuai banKuai;
	

	/**
	 * 回复信息列表
	 */
	List<HuiFuVO> list;
	
	/**
	 * 发帖人信息
	 */
	private User user;
	
	/**
	 * 帖子详情
	 */
	private TieZi tiezi;

	
	
	public List<HuiFuVO> getList() {
		return list;
	}

	public void setList(List<HuiFuVO> list) {
		this.list = list;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TieZi getTiezi() {
		return tiezi;
	}

	public void setTiezi(TieZi tiezi) {
		this.tiezi = tiezi;
	}
	

	public BanKuai getBanKuai() {
		return banKuai;
	}

	public void setBanKuai(BanKuai banKuai) {
		this.banKuai = banKuai;
	}
}
