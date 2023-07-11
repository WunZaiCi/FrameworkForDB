package com.banma.BF.vo;

import java.util.List;


/**
 * 板块页面实体类
 * 复合对象
 * @author WHASSUPMAN
 *
 */
public class BanKuaiVO {
	
	private  int bid;
	private  String bname;
	private  int pid;
	private  List<BanKuaiVO> cList;//子版块对象
	private  int tieCount;//帖子数量
	private  TieZiVO lastTieZi;//最后发帖回帖的对象
	
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public List<BanKuaiVO> getcList() {
		return cList;
	}
	public void setcList(List<BanKuaiVO> cList) {
		this.cList = cList;
	}
	public int getTieCount() {
		return tieCount;
	}
	public void setTieCount(int tieCount) {
		this.tieCount = tieCount;
	}
	public TieZiVO getLastTieZi() {
		return lastTieZi;
	}
	public void setLastTieZi(TieZiVO lastTieZi) {
		this.lastTieZi = lastTieZi;
	}
	
}
