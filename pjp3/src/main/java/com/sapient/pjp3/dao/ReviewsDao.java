package com.sapient.pjp3.dao;

import com.sapient.pjp3.entity.Review;
import com.sapient.pjp3.entity.User;
import com.sapient.pjp3.utils.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewsDao {
	
	public int getMaxReviewID() throws SQLException, ClassNotFoundException {
		String sql = "SELECT MAX(ID) FROM REVIEWS;";
		Logger log = LoggerFactory.getLogger(ReviewsDao.class);
		Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);
		log.info(stmt.toString());
		ResultSet rs =  stmt.executeQuery();   
		rs.next();
		return rs.getInt(1);
	}
	
	public boolean addReview(Review review) {
		String sql = "INSERT INTO REVIEWS (ID, ISSUE_ID, USER_ID, BOOK_ID, RATING, REVIEW) VALUES (?, ?, ?, ?, ?, ?)";
		Logger log = LoggerFactory.getLogger(ReviewsDao.class);
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {		
			stmt.setInt(1, review.getReviewId());
			stmt.setInt(2, review.getIssueId());
			stmt.setInt(3, review.getUserId());
			stmt.setLong(4, review.getIsbn());
			stmt.setInt(5, review.getRating());
			stmt.setString(6, review.getReview());

			log.info(stmt.toString());
			
			stmt.execute();
		} 
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean updateReview(Review review) {
		String sql = "UPDATE REVIEWS SET RATING = ?, REVIEW = ? WHERE BOOK_ID = ? AND USER_ID = ?";
		Logger log = LoggerFactory.getLogger(ReviewsDao.class);
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {		
			stmt.setLong(1, review.getRating());
			stmt.setString(2, review.getReview());
			stmt.setLong(2, review.getIsbn());
			stmt.setLong(2, review.getUserId());

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
