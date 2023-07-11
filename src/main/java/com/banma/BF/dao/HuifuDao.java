package com.banma.BF.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.banma.BF.entity.HuiFu;
import com.banma.BF.util.JdbcUtil;

public class HuifuDao {
	
	/**
	 * 根据帖子id获得恢回复列表
	 * @param tid
	 * @return
	 */
	public List<HuiFu> getHuifuByTid(int tid) {
		List<HuiFu> list = new ArrayList<>(); 
		try {
			Connection conn = JdbcUtil.getConnection();
			String sql = "select * from huifu where tid=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, tid);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				HuiFu huifu = new HuiFu();
				huifu.setContent(rs.getString("content"));
				huifu.setCreateTime(rs.getTimestamp("createTime"));
				huifu.setUpdateTime(rs.getTimestamp("updateTime"));
				huifu.setUid(rs.getInt("uid"));
				huifu.setHid(rs.getInt("hid"));
				huifu.setTid(rs.getInt("tid"));
				list.add(huifu);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	
	/**
	 * 根据回复id 获得回复
	 * @param hid
	 * @return
	 */
	public HuiFu getHuifuByHid(int hid) {
		HuiFu huifu = null;
		try {
			Connection conn = JdbcUtil.getConnection();
			String sql = "select * from huifu where hid=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, hid);
			ResultSet rs = st.executeQuery();
			
			
			while (rs.next()) {
				huifu= new HuiFu();
				huifu.setContent(rs.getString("content"));
				huifu.setCreateTime(rs.getTimestamp("createTime"));
				huifu.setUpdateTime(rs.getTimestamp("updateTime"));
				huifu.setUid(rs.getInt("uid"));
				huifu.setHid(rs.getInt("hid"));
				huifu.setTid(rs.getInt("tid"));
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return huifu;
	}
	
	
	/**
	 * 添加回复
	 * @param huifu
	 * @return
	 */
	public long addHuifu(HuiFu huifu) {

		try {
			Connection conn = JdbcUtil.getConnection();
			String sql = "insert huifu (content,tid,uid,createTime,updateTime)" 
			+ "values(?,?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(sql);
			java.util.Date date = new java.util.Date();
			java.sql.Timestamp daTimestamp = new java.sql.Timestamp(date.getTime());
			
			st.setString(1, huifu.getContent());
			st.setInt(2, huifu.getTid());
			st.setInt(3, huifu.getUid());
			st.setTimestamp(4, daTimestamp);
			st.setTimestamp(5, daTimestamp);
			
			int cnt = st.executeUpdate();
			if(cnt!=1) {
				return -1;
			} 
			
			try {
				ResultSet generatedKeys = st.getGeneratedKeys();
				if(generatedKeys.next()) {
					long hid =  generatedKeys.getLong(1);
					st.close();
					return hid;
				}else {
					return -1;
				}
			} catch (Exception e) {
				
			}
			 
			return -1;
		} catch (SQLException e2) {
			throw new RuntimeException(e2);
		}

	}
	
	/**
	 * 删除回复
	 * @param tid
	 * @return
	 */
	public Boolean deleteHuifu(int hid ) {
		try {
			Connection conn = JdbcUtil.getConnection();
			String sql = "delete from huifu where hid=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,hid);
			
			int cnt = st.executeUpdate();
			if(cnt!=1) {
				return false;
			} 
			
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}


	
	public boolean updateHuifu(HuiFu huiFu) {
		try {
			Connection conn = JdbcUtil.getConnection();
			String sql = "update huifu set content=?,updateTime=? where hid=?";
			PreparedStatement st = conn.prepareStatement(sql);
			
			java.util.Date date = new java.util.Date();
			java.sql.Timestamp daTimestamp = new java.sql.Timestamp(date.getTime());
			st.setString(1, huiFu.getContent());
			st.setTimestamp(2, daTimestamp);
			st.setInt(3, huiFu.getHid());
			
			int cnt = st.executeUpdate();
			if(cnt!=1) {
				return false;
			} 
			
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public List<HuiFu> getHuifuByTid(int tid, int page) {
		List<HuiFu> list = new ArrayList<>(); 
		if(page>0) {
			page--;
		}else {
			page=0;//默认显示第一页
		}
		
		try {
			Connection conn = JdbcUtil.getConnection();
			String sql = "select * from huifu where tid=? limit ?,5";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, tid);
			st.setInt(2, (page)*5);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				HuiFu huifu = new HuiFu();
				huifu.setContent(rs.getString("content"));
				huifu.setCreateTime(rs.getTimestamp("createTime"));
				huifu.setUpdateTime(rs.getTimestamp("updateTime"));
				huifu.setUid(rs.getInt("uid"));
				huifu.setHid(rs.getInt("hid"));
				huifu.setTid(rs.getInt("tid"));
				list.add(huifu);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
}
