package com.banma.BF.entity;

public class BanKuai {
	private  int bid;
	private  String bname;
	private  int pid;
	
	@Override
	public String toString() {
		return "BanKuai [bid=" + bid + ", bname=" + bname + ", pid=" + pid + "]";
	}
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
	
	
	
}
