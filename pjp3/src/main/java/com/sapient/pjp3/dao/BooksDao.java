package com.sapient.pjp3.dao;

import com.sapient.pjp3.entity.Book;
import com.sapient.pjp3.utils.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BooksDao {

	public Book updateBooksDetails(String BOOK_TITLE, String AUTHOR, float PRICE, float RATING, Integer QUANTITY, Integer isbn) {
		String sql = "UPDATE BOOKS SET TITLE = ?, AUTHOR = ?, PRICE = ?, RATING = ?, QUANTITY = ? WHERE isbn = ?";
		Logger log = LoggerFactory.getLogger(BooksDao.class);
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			
			stmt.setString(1, BOOK_TITLE);
			stmt.setString(2, AUTHOR);
			stmt.setDouble(3, PRICE);
			stmt.setDouble(4, RATING);
			stmt.setLong(5, QUANTITY);
			stmt.setLong(6, isbn);
			log.info(stmt.toString());
			ResultSet rs =  stmt.executeQuery();
			rs.next();

			Book bookResp = new Book();
			bookResp.setIsbn(rs.getInt("isbn"));
			bookResp.setTitle(rs.getString("TITLE"));
			bookResp.setAuthor(rs.getString("AUTHOR"));
			bookResp.setPrice(rs.getInt("PRICE"));
			bookResp.setRating(rs.getFloat("RATING"));
			bookResp.setQuantity(rs.getInt("QUANTITY"));
			return bookResp;
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
