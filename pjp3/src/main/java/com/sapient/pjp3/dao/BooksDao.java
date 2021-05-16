package com.sapient.pjp3.dao;

import com.sapient.pjp3.entity.Book;
import com.sapient.pjp3.utils.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BooksDao {

	public Book updateBooksDetails(String BOOK_TITLE, String AUTHOR, Double double1, Double double2, Integer QUANTITY, Integer isbn) {
		String sql = "UPDATE BOOKS SET TITLE = ?, AUTHOR = ?, PRICE = ?, RATING = ?, QUANTITY = ? WHERE isbn = ?";
		Logger log = LoggerFactory.getLogger(BooksDao.class);
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			
			stmt.setString(1, BOOK_TITLE);
			stmt.setString(2, AUTHOR);
			stmt.setDouble(3, double1);
			stmt.setDouble(4, double2);
			stmt.setLong(5, QUANTITY);
			stmt.setLong(6, isbn);
			log.info(stmt.toString());
			ResultSet rs =  stmt.executeQuery();
			rs.next();

			Book bookResp = resultSetToBook(rs);
			return bookResp;
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private Book resultSetToBook(ResultSet rs) throws SQLException {
		Book bookResp = new Book();
		bookResp.setIsbn(rs.getLong("isbn"));
		bookResp.setTitle(rs.getString("TITLE"));
		bookResp.setAuthor(rs.getString("AUTHOR"));
		bookResp.setPrice(rs.getDouble("PRICE"));
		bookResp.setRating(rs.getDouble("RATING"));
		bookResp.setQuantity(rs.getInt("QUANTITY"));
		return bookResp;
	}
}
