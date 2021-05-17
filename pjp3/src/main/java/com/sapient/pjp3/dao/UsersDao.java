package com.sapient.pjp3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sapient.pjp3.entity.User;
import com.sapient.pjp3.utils.DBUtils;

public class UsersDao {
	
	private User resultSetToUser(ResultSet rs) throws SQLException {
		User u = new User();
		u.setId(rs.getInt("id"));
		u.setEmail(rs.getString("email"));
		u.setPassword(rs.getString("password"));
		u.setFullname(rs.getString("fullName"));
		u.setCreatedAt(rs.getDate("createdAt"));
		u.setFine(rs.getInt("fine"));
		u.setCurrentBorrowedBooks(rs.getInt("currentBorrowedBooks"));
		u.setTotalBorrowedBooks(rs.getInt("totalBorrowedBooks"));
		return u;
	}
	
	public User getUser(Integer id) {
		String sql = " SELECT * FROM USERS WHERE id = ? ";
		Logger log = LoggerFactory.getLogger(BookRequestsDao.class);
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {		
			stmt.setLong(1, id);
			log.info(stmt.toString());
			ResultSet rs =  stmt.executeQuery();   
			rs.next();
			
			return resultSetToUser(rs);
			
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}
	
	public User getUserByEmail(String email) {
		String sql = " SELECT * FROM USERS WHERE email = ? ";

		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {		
			stmt.setString(1, email);
			ResultSet rs =  stmt.executeQuery();   
			rs.next();
			
			return 	resultSetToUser(rs);
		
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
			responseUser.setFullname(rs.getString("fullName"));
			return responseUser;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public User payFine(int userId) {
		String sql = "UPDATE USERS SET FINE = 0.0 where id = ? ";
		Logger log = LoggerFactory.getLogger(BookRequestsDao.class);
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			log.info(stmt.toString());
			stmt.setInt(1, userId);
			stmt.executeUpdate();
			
			payFineAtAllIssues(userId);
			
			return getUser(userId);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	public User payFineAtAllIssues(int userId) {
		String sql = "UPDATE book_issues SET fineDue = 0.0, isFinePaid = 1 where userId = ? ";
		Logger log = LoggerFactory.getLogger(BookRequestsDao.class);
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			log.info(stmt.toString());
			stmt.setInt(1, userId);
			stmt.executeUpdate();

			return getUser(userId);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public int addUser(User user)
	{
		if(getUserByEmail(user.getEmail())!=null) {
			return -1;
		}
			
		String sql = "insert into USERS values (? , ? , ?, ? , curdate() , 0 , 0 , 0 )";
		
		try (Connection conn = DBUtils.createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);) {		
				
			stmt.setInt(1,getMaxId()+1);
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPassword());
			stmt.setString (4, user.getFullname());
			
			return stmt.executeUpdate();   
			
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return -1;
	}
	
	public int getMaxId() {
		String  sql = "SELECT MAX(id) as maxi from users";
		try (Connection conn = DBUtils.createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql ,  Statement.RETURN_GENERATED_KEYS);) {		
				
			ResultSet rs =  stmt.executeQuery();   
			if(rs.next()) {

				return rs.getInt("maxi");
			}
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return 0;
		
	}
	
	
	
}
