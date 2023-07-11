package com.banma.BF.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.banma.BF.entity.TieZi;
import com.banma.BF.util.JdbcUtil;

public class TieZiDao {

	/**
	 * 根据板块bid获取 该板块所有帖子
	 * 
	 * @param bid
	 * @return
	 */
	public List<TieZi> getTieZiByBid(int bid) {
		List<TieZi> list = new ArrayList<>();
		try {
			Connection conn = JdbcUtil.getConnection();
			String sql = "select * from tiezi where bid=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, bid);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				TieZi tiezi = new TieZi();
				tiezi.setContent(rs.getString("content"));
				tiezi.setTitle(rs.getString("title"));
				tiezi.setCreateTime(rs.getTimestamp("createTime"));
				tiezi.setUpdateTime(rs.getTimestamp("updateTime"));
				tiezi.setUid(rs.getInt("uid"));
				tiezi.setBid(rs.getInt("bid"));
				tiezi.setTid(rs.getInt("tid"));
				list.add(tiezi);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	
	/**
	 * 根据板块bid获取 该板块所有帖子
	 * 分页查询
	 * @param bid
	 * @param pageIndex
	 * @return
	 */
	public List<TieZi> getTieZiByBid(int bid,int pageIndex) {
		List<TieZi> list = new ArrayList<>();
		if(pageIndex>0) {
			pageIndex--;
		}else {
			pageIndex=0;//默认显示第一页
		}
		
		try {
			Connection conn = JdbcUtil.getConnection();
			String sql = "select * from tiezi where bid=? limit ?,5";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, bid);
			st.setInt(2, (pageIndex)*5);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				TieZi tiezi = new TieZi();
				tiezi.setContent(rs.getString("content"));
				tiezi.setTitle(rs.getString("title"));
				tiezi.setCreateTime(rs.getTimestamp("createTime"));
				tiezi.setUpdateTime(rs.getTimestamp("updateTime"));
				tiezi.setUid(rs.getInt("uid"));
				tiezi.setBid(rs.getInt("bid"));
				tiezi.setTid(rs.getInt("tid"));
				list.add(tiezi);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	
	
	
	
	
	/**
	 * 根据帖子id获取帖子
	 * 
	 * @param tid
	 * @return
	 */
	public TieZi getTieziByTid(int tid) {
		TieZi tiezi = null;
		try {
			Connection conn = JdbcUtil.getConnection();
			String sql = "select * from tiezi where tid=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, tid);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				tiezi = new TieZi();
				tiezi.setContent(rs.getString("content"));
				tiezi.setTitle(rs.getString("title"));
				tiezi.setCreateTime(rs.getTimestamp("createTime"));
				tiezi.setUpdateTime(rs.getTimestamp("updateTime"));
				tiezi.setUid(rs.getInt("uid"));
				tiezi.setBid(rs.getInt("bid"));
				tiezi.setTid(tid);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return tiezi;
	}

	
	
	
	/**
	 * 通过板块bid获取最后更新的帖子
	 * 
	 * @param bid
	 * @return
	 */
	public TieZi getLastUpdateTieziByBid(int bid) {
		TieZi tiezi = null;
		try {
			Connection conn = JdbcUtil.getConnection();
			String sql = "select * from tiezi where bid=? order by updateTime limit 0,1";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, bid);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				tiezi = new TieZi();
				tiezi.setContent(rs.getString("content"));
				tiezi.setTitle(rs.getString("title"));
				tiezi.setCreateTime(rs.getTimestamp("createTime"));
				tiezi.setUpdateTime(rs.getTimestamp("updateTime"));
				tiezi.setUid(rs.getInt("uid"));
				tiezi.setBid(bid);
				tiezi.setTid(rs.getInt("tid"));
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return tiezi;
	}

	
	
	
	/**
	 * 添加帖子
	 * @param tieZi
	 * @return
	 */
	public long addTiezi(TieZi tieZi) {

		try {
			Connection conn = JdbcUtil.getConnection();
			String sql = "insert tiezi (title,createTime,updateTime,content,uid,bid)" 
			+ "values(?,?,?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			java.util.Date date = new java.util.Date();
			java.sql.Timestamp daTimestamp = new java.sql.Timestamp(date.getTime());
			
			st.setString(1, tieZi.getTitle());
			st.setTimestamp(2, daTimestamp);
			st.setTimestamp(3, daTimestamp);
			st.setString(4, tieZi.getContent());
			st.setInt(5, tieZi.getUid());
			st.setInt(6, tieZi.getBid());
			
			int cnt = st.executeUpdate();
			if(cnt!=1) {
				return -1;
			} 
			
			try {
				ResultSet generatedKeys = st.getGeneratedKeys();
				if(generatedKeys.next()) {
					long tid =  generatedKeys.getLong(1);
					st.close();
					return tid;
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
	 * 修改帖子
	 * @param tieZi
	 * @return
	 */
	public Boolean updateTiezi(TieZi tieZi) {

		try {
			Connection conn = JdbcUtil.getConnection();
			String sql = "update tiezi set title=?,updateTime=?,content=? where tid=?";
			PreparedStatement st = conn.prepareStatement(sql);
			
			java.util.Date date = new java.util.Date();
			java.sql.Timestamp daTimestamp = new java.sql.Timestamp(date.getTime());
			st.setString(1, tieZi.getTitle());
			st.setTimestamp(2, daTimestamp);
			st.setString(3, tieZi.getContent());
			st.setInt(4, tieZi.getTid());
			
			int cnt = st.executeUpdate();
			if(cnt!=1) {
				return false;
			} 
			
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 删除帖子
	 * @param tid
	 * @return
	 */
	public Boolean deleteTiezi(int tid ) {
		try {
			Connection conn = JdbcUtil.getConnection();
			String sql = "delete from tiezi where tid=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,tid);
			
			int cnt = st.executeUpdate();
			if(cnt!=1) {
				return false;
			} 
			
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}


}
