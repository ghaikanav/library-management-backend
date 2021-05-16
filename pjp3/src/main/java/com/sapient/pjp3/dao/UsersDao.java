package com.sapient.pjp3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sapient.pjp3.entity.User;
import com.sapient.pjp3.utils.DBUtils;

public class UsersDao {
	public User getUser(Integer id) {
		String sql = " SELECT * FROM USERS WHERE id = ? ";
		Logger log = LoggerFactory.getLogger(BookRequestsDao.class);
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {		
			stmt.setLong(1, id);
			log.info(stmt.toString());
			ResultSet rs =  stmt.executeQuery();   
			rs.next();
			User u = new User();
			u.setId(rs.getInt("id"));
			u.setFullname(rs.getString("fullName"));
			return u;
			
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}
	
	public User getUserByEmail(String email) {
		String sql = " SELECT * FROM USERS WHERE email = ? ";
		Logger log = LoggerFactory.getLogger(BookRequestsDao.class);
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {		
			stmt.setString(1, email);
			log.info(stmt.toString());
			ResultSet rs =  stmt.executeQuery();   
			rs.next();
			User u = new User();
			u.setId(rs.getInt("id"));
			u.setEmail(rs.getString("email"));
			u.setPassword(rs.getString("password"));
			u.setFullname(rs.getString("fullName"));
			return u;
			
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	public User updateUserName(String fullname, int userId) {
		String sql = "UPDATE USERS SET FULL_NAME = ? WHERE id = ?";
		Logger log = LoggerFactory.getLogger(BookRequestsDao.class);
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
		Logger log = LoggerFactory.getLogger(BookRequestsDao.class);
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
		String sql = "insert into USERS values (? , ? , ?, ? , curdate() , 0 , 0 , 0 )";
		
		try (Connection conn = DBUtils.createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {		
				
			stmt.setInt(1,getMaxId());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPassword());
			stmt.setString (4, user.getFullname());
			
			stmt.executeUpdate();
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public int getMaxId() {
		String  sql = "SELECT count(*) as count1 from users";
		try (Connection conn = DBUtils.createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql ,  Statement.RETURN_GENERATED_KEYS);) {		
				
			ResultSet rs =  stmt.executeQuery();   
			if(rs.next()) {

				return rs.getInt("count1");
			}
			
			
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return 0;
		
	}
	
	
	
}
