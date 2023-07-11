package com.banma.BF.vo;

import java.util.List;

import com.banma.BF.entity.BanKuai;

public class ListPageVO {
	
	
	private List<TieZiVO> tiezi;
	private BanKuai banKuai;

	public BanKuai getBanKuai() {
		return banKuai;
	}

	public void setBanKuai(BanKuai banKuai) {
		this.banKuai = banKuai;
	}

	public List<TieZiVO> getTiezi() {
		return tiezi;
	}

	public void setTiezi(List<TieZiVO> tiezi) {
		this.tiezi = tiezi;
	}
}
