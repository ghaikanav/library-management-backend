package com.sapient.pjp3.dao;

import com.sapient.pjp3.entity.Book;
import com.sapient.pjp3.entity.Review;
import com.sapient.pjp3.entity.User;
import com.sapient.pjp3.utils.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	public Object getReviewsByIsbn(Long isbn) {
		List<Review> reviews = new ArrayList<>();
		
		String sql = "Select * from reviews where isbn = ?";
		Logger log = LoggerFactory.getLogger(BooksDao.class);
		
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
	
			stmt.setLong(1, isbn);
			log.info(stmt.toString());
			ResultSet rs =  stmt.executeQuery();
			
			while(rs.next()) {
				reviews.add(resultSetToReviews(rs));
			}
			
			return reviews;
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return null;
		
	}
	
	private Review resultSetToReviews(ResultSet rs) {
		Review review = new Review();
		try {
			review.setReview(rs.getString("review"));
			review.setRating(rs.getInt("rating"));
			
			return review;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public void addReview(Review review) {
		String sql = "INSERT INTO REVIEWS (issueId, userId, isbn, rating, review) VALUES (?, ?, ?, ?, ?)";
		Logger log = LoggerFactory.getLogger(ReviewsDao.class);
		try (Connection conn = DBUtils.createConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql);) {		
			//stmt.setInt(1, review.getReviewId());
			if(review.getIssueId()!=null)
				stmt.setInt(1, review.getIssueId());
			else
				stmt.setInt(1, 202101);//default value for issueId
			
			stmt.setInt(2, review.getUserId());
			stmt.setLong(3, review.getIsbn());
			stmt.setInt(4, review.getRating());
			stmt.setString(5, review.getReview());

			log.info(stmt.toString());
			
			stmt.executeUpdate();
			
		} 
		catch (Exception ex) {
			ex.printStackTrace();
			
		}
	}
	
	public boolean updateReview(Review review) {
		String sql = "UPDATE reviews SET rating = ?, review = ? WHERE isbn = ? AND reviewId = ?";
		Logger log = LoggerFactory.getLogger(ReviewsDao.class);
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {		
			stmt.setInt(1, review.getRating());
			stmt.setString(2, review.getReview());
			stmt.setLong(3, review.getIsbn());
			stmt.setInt(4, review.getReviewId());

			log.info(stmt.toString());
			stmt.executeUpdate();  
			
			return true;
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
