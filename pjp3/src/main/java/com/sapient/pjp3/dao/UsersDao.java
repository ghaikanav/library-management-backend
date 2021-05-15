package com.sapient.pjp3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
