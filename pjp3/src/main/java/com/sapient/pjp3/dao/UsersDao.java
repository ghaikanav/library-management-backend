package com.sapient.pjp3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sapient.pjp3.entity.Login;
import com.sapient.pjp3.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sapient.pjp3.entity.User;
import com.sapient.pjp3.utils.DBUtils;

public class UsersDao {
	public static User getUser(Integer id) {
		String sql = " SELECT * FROM USERS WHERE id = ? ";
		Logger log = LoggerFactory.getLogger(BookRequestDao.class);
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {		
			stmt.setLong(1, id);
			log.info(stmt.toString());
			ResultSet rs =  stmt.executeQuery();   
			rs.next();
			User u = new User();
			u.setId(rs.getInt("id"));
			u.setFullname(rs.getString("FULL_NAME"));
			return u;
			
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	public User updateUserName(String fullname, int userId) {
		String sql = "UPDATE USERS SET FULL_NAME = ? WHERE id = ?";
		Logger log = LoggerFactory.getLogger(BookRequestDao.class);
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setString(1, fullname);
			stmt.setLong(2, userId);
			log.info(stmt.toString());
			ResultSet rs =  stmt.executeQuery();
			rs.next();

			User responseUser = new User();
			responseUser.setId(rs.getInt("id"));
			responseUser.setFullname(rs.getString("FULL_NAME"));
			return responseUser;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public User updateDueAmount(int userId, int fine) {
		String sql = "UPDATE USERS SET FINE = ? WHERE id = ?";
		Logger log = LoggerFactory.getLogger(BookRequestDao.class);
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setLong(1, fine);
			stmt.setLong(2, userId);
			log.info(stmt.toString());
			ResultSet rs =  stmt.executeQuery();
			rs.next();

			User responseUser = new User();
			responseUser.setId(rs.getInt("id"));
			responseUser.setFullname(rs.getString("FULL_NAME"));
			responseUser.setFine(rs.getInt("FINE"));
			return responseUser;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	public void add(User user)
	{
		String sql = "insert into USERS values ( null , ? , ? , ? ,? , ? )";
		
		try {
			Connection conn = DBUtils.createConnection();
			PreparedStatement stmt = conn.prepareStatement(sql ,  Statement.RETURN_GENERATED_KEYS);
			
		{
				stmt.setString (1, user.getFullname());
				stmt.setDate(2, new java.sql.Date(user.getCreated_at().getTime()));
				stmt.setInt(3 ,user.getTotal_borrowed_books());
				stmt.setInt(4 , user.getCurrent_borrowed_books());
				stmt.setInt(5, user.getFine());
				
				stmt.executeUpdate();
				
				ResultSet keys = stmt.getGeneratedKeys();
				if(keys.next()) {
					user.setId(keys.getInt(1));
				}
				
					}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	
}
