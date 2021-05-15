package com.sapient.pjp3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sapient.pjp3.entity.Login;
import com.sapient.pjp3.utils.DBUtils;

public class LoginDao {
	public static Login getLogin(String email) {
		String sql = " SELECT * FROM LOGIN WHERE EMAIL= ? ";
		Logger log = LoggerFactory.getLogger(BookRequestDao.class);
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {		
			stmt.setString(1, email);
			log.info(stmt.toString());
			ResultSet rs =  stmt.executeQuery();   
			rs.next();
			Login u = new Login();
			u.setId(rs.getInt("id"));
			u.setEmail(rs.getString("EMAIL"));
			u.setPassword(rs.getString("PASSWORD"));
			return u;
			
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}
}
