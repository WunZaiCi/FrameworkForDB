package com.banma.BF.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.banma.BF.entity.User;
import com.banma.BF.util.JdbcUtil;

public class UserDao {
	
	/**
	 * 根据用户id  获得用户实例
	 * @param uid
	 * @return
	 */
	public User getUser(int uid) {
		User user = null;
		try {
			Connection conn = JdbcUtil.getConnection();
			String sql = "select * from user where uid=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, uid);
			ResultSet rs = st.executeQuery();
			
			
			while (rs.next()) {
				user= new User();
				user.setUsername(rs.getString("username")) ;
				user.setCreateTime(rs.getTimestamp("createTime"));
				user.setHeadImage(rs.getString("headImage"));
				user.setSex(rs.getString("sex")) ;
				user.setUid(uid) ;
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return user;
	}
	
	
	/**
	 * 根据用户名和密码得到用户实例
	 * @param username
	 * @param password
	 * @return
	 */
	public User getUserByUserNameAndPW(String username,String password) {
		User user = null;
		try {
			Connection conn = JdbcUtil.getConnection();
			String sql = "select * from user where username=? and password=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, username);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			
			 
			while (rs.next()) {
				user= new User();
				user.setUsername(rs.getString("username")) ;
				user.setCreateTime(rs.getTimestamp("createTime"));
				user.setHeadImage(rs.getString("headImage"));
				user.setSex(rs.getString("sex")) ;
				user.setUid(rs.getInt("uid")) ;
			}
			rs.close();
			st.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return user;
	}
	
	  
	
	public Boolean addUser(User user) {
		
		try {
			Connection conn = JdbcUtil.getConnection();
			String sql = "insert user (   username,password,sex,headImage,createTime)"
					+ "values(?,?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, user.getUsername());
			st.setString(2, user.getPassword());
			st.setString(3, user.getSex());
			st.setString(4, user.getHeadImage());
			
			java.util.Date date = new java.util.Date();
			java.sql.Timestamp dateTimestamp = new java.sql.Timestamp(date.getTime());
			st.setTimestamp(5, dateTimestamp);
			st.executeUpdate();
			st.close();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}