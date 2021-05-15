package com.sapient.pjp3.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sapient.pjp3.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sapient.pjp3.utils.DBUtils;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public class BookRequestDao {
	public static Boolean create(Integer USER_ID,String BOOK_TITLE,String AUTHOR,Date date) {
		String sql = "INSERT INTO BOOK_REQUESTS(USER_ID, BOOK_TITLE, AUTHOR) values ( ?, ?, ?)";
		Logger log = LoggerFactory.getLogger(BookRequestDao.class);
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			
			stmt.setInt(1, USER_ID);
			stmt.setString(2, BOOK_TITLE);
			stmt.setString(3, AUTHOR);
			log.info(conn.toString());
			log.info(stmt.toString());
			stmt.execute();
		} 
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}


	public List<Book> getBooksByKeyword(String keyword) {
		String sql = "SELECT * FROM BOOKS WHERE LOWER(TITLE) LIKE LOWER(?)";
		Logger log = LoggerFactory.getLogger(BookRequestDao.class);
		List<Book> books = new ArrayList<>();
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {

			stmt.setString(1, "%"+keyword+"%");
			log.info(conn.toString());
			log.info(stmt.toString());
			ResultSet rs = stmt.executeQuery();
			do {
				Book book = new Book();
				book.setQuantity(rs.getInt("quantity"));
				book.setBook_cover(rs.getString("book_cover"));
				book.setAuthor(rs.getString("author"));
				book.setGenre(rs.getString("genre"));
				book.setIsbn(rs.getLong("isbn"));
				book.setPrice(rs.getFloat("price"));
				book.setPublished_date(rs.getDate("published_date"));
				book.setRating(rs.getFloat("rating"));
				book.setPublisher(rs.getString("publisher"));
				book.setTotal_issues(rs.getInt("total_issues"));
				book.setTitle(rs.getString("title"));
				books.add(book);
			} while(rs.next());
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return books;
	}
}
