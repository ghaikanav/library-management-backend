package com.sapient.pjp3.dao;

import com.sapient.pjp3.entity.Review;
import com.sapient.pjp3.entity.User;
import com.sapient.pjp3.utils.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReviewDao {

	public boolean updateReview(Review review) {
		String sql = "UPDATE REVIEWS SET RATING = ?, REVIEW = ? WHERE BOOK_ID = ? AND USER_ID = ?";
		Logger log = LoggerFactory.getLogger(BookRequestDao.class);
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {		
			stmt.setLong(1, review.getRating());
			stmt.setString(2, review.getReview());
			stmt.setLong(2, review.getIsbn());
			stmt.setLong(2, review.getUser_id());

			log.info(stmt.toString());
			ResultSet rs =  stmt.executeQuery();   
			rs.next();
			return true;
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
