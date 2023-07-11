package com.banma.BF.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.banma.BF.entity.BanKuai;
import com.banma.BF.util.JdbcUtil;


public class BanKuaiDao {
	
	
	/**
	 * 通过板块id
	 * @param bid
	 * @return
	 */
	public BanKuai getBanKuaiByBid (int bid) {
		BanKuai bankuai = null;
		try {
			Connection conn = JdbcUtil.getConnection();
			String sql = "select * from bankuai where bid=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, bid);
			ResultSet rs = st.executeQuery();
			
			
			while (rs.next()) {
				bankuai= new BanKuai();
				bankuai.setBname(rs.getString("bname"));
				bankuai.setPid(rs.getInt("pid"));
				bankuai.setBid(bid);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
		return bankuai;
	}
	
	public List<BanKuai> getBanKuaiByPid(int pid){
		List<BanKuai> list = new ArrayList<>();
		try {
			Connection conn = JdbcUtil.getConnection();
			String sql = "select * from bankuai where pid=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, pid);
			ResultSet rs = st.executeQuery();
			
			
			while (rs.next()) { 
				BanKuai banKuai = new BanKuai();
				banKuai.setBname(rs.getString("bname"));
				banKuai.setBid(rs.getInt("bid"));
				banKuai.setPid(rs.getInt("pid"));
				list.add(banKuai);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
		return list;
	}
	
	
	
}
