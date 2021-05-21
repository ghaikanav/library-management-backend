package com.sapient.pjp3.dao;

import com.sapient.pjp3.entity.Book;
import com.sapient.pjp3.entity.BookCopy;
import com.sapient.pjp3.utils.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BooksDao {
	
	private Book resultSetToBook(ResultSet rs) throws SQLException {
		Book bookResp = new Book();
		bookResp.setIsbn(rs.getLong("isbn"));
		bookResp.setTitle(rs.getString("TITLE"));
		bookResp.setAuthor(rs.getString("AUTHOR"));
		bookResp.setPrice(rs.getDouble("PRICE"));
		bookResp.setRating(rs.getDouble("RATING"));
		bookResp.setQuantity(rs.getInt("QUANTITY"));
		bookResp.setGenre(rs.getString("genre"));
		bookResp.setBookCover(rs.getString("bookCover"));
		bookResp.setTotalIssues(rs.getInt("totalIssues"));
		bookResp.setPublisher(rs.getString("publisher"));
		bookResp.setPublishedDate(rs.getDate("publishedDate"));
		return bookResp;
	}
	
	public List<Book> getMostPopularBooks(){
		List<Book> books = new ArrayList<>();
		
		String sql = "Select * from BOOKS where rating>4.0 order by (rating*totalIssues) desc limit 50";
		Logger log = LoggerFactory.getLogger(BooksDao.class);
		
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
	
			log.info(stmt.toString());
			ResultSet rs =  stmt.executeQuery();
			
			while(rs.next()) {
				books.add(resultSetToBook(rs));
			}
			
			return books;
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return null;
		
		
	}
	
	public List<Book> getBooksByGenre(String genre){
		List<Book> books = new ArrayList<>();
		
		String sql = "Select * from BOOKS where genre = ?";
		Logger log = LoggerFactory.getLogger(BooksDao.class);
		
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
	
			stmt.setString(1, genre);
			log.info(stmt.toString());
			ResultSet rs =  stmt.executeQuery();
			
			while(rs.next()) {
				books.add(resultSetToBook(rs));
			}
			
			return books;
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return null;
		
		
	}
	
	public Book getBookByIsbn(Long isbn) {
		
		String sql = "Select * from BOOKS where isbn = ?";
		Logger log = LoggerFactory.getLogger(BooksDao.class);
		
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
	
			stmt.setLong(1, isbn);
			log.info(stmt.toString());
			ResultSet rs =  stmt.executeQuery();
			
			if(rs.next()) {
				return resultSetToBook(rs);
			}
			
			
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return null;
		
	}
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

	public List<String> getAllGenres() {
		
		List<String> genres = new ArrayList<>();
		
		String sql = "Select DISTINCT genre from BOOKS";
		Logger log = LoggerFactory.getLogger(BooksDao.class);
		
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
	
			log.info(stmt.toString());
			ResultSet rs =  stmt.executeQuery();
			
			while(rs.next()) {
				genres.add(rs.getString("genre"));
			}
			
			return genres;
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return null;
		
	}

	public List<Book> orderBooksByFilter(String filter, String order) {
		List<Book> books = new ArrayList<>();
		
		String sql = "Select * from BOOKS order by "+ filter + " " + order + " limit 100";
		Logger log = LoggerFactory.getLogger(BooksDao.class);
		
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
	
//			stmt.setString(1, filter);
//			stmt.setString(2, order);
			log.info(stmt.toString());
			ResultSet rs =  stmt.executeQuery();
			
			while(rs.next()) {
				books.add(resultSetToBook(rs));
			}
			
			return books;
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return null;
	}

	public boolean checkIfBorrowPossible(Integer userId){
		String sql = "Select * from users where id = ? ;";
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);)
		{
			stmt.setLong(1, userId);
			ResultSet rs =  stmt.executeQuery();
			rs.next();
			if(rs.getInt("currentBorrowedBooks") <= 5){
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public Book borrowBook(Long isbn, Integer userId) {
		String sql = "Select * from book_copies where isbn = ? AND isBorrowed = 0 limit 1 ";
		Logger log = LoggerFactory.getLogger(BooksDao.class);
		
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
	
			stmt.setLong(1, isbn);
			log.info(stmt.toString());
			ResultSet rs =  stmt.executeQuery();

			if(rs.next()) {
				System.out.println(rs.getInt("bookId") + "..." + rs.getLong("isbn"));
				updateBookCopiesTable(rs.getInt("bookId"));
				updateBookIssuesTable(rs.getInt("bookId"), userId, isbn);
				updateBooksTable(isbn);
				updateUsersTableForBorrow(userId);
				return getBookByIsbn(rs.getLong("isbn"));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Executed during Borrowing a book
	 * Update Users table to increment number of current and total borrowed books
	 * @param userId
	 */
	private void updateUsersTableForBorrow(Integer userId){
		String sqlCurrBorrow = "UPDATE users SET currentBorrowedBooks = currentBorrowedBooks + 1 WHERE id = ?";
		String sqlTotalBorrow = "UPDATE users SET totalBorrowedBooks = totalBorrowedBooks + 1 WHERE id = ?";
		try (Connection conn = DBUtils.createConnection();
			 PreparedStatement stmtCurr = conn.prepareStatement(sqlCurrBorrow ,  Statement.RETURN_GENERATED_KEYS);
			 PreparedStatement stmtTotal = conn.prepareStatement(sqlTotalBorrow ,  Statement.RETURN_GENERATED_KEYS);) {

			stmtCurr.setLong(1, userId);
			stmtTotal.setLong(1, userId);

			stmtCurr.executeUpdate();
			stmtTotal.executeUpdate();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private void updateBooksTable(Long isbn) {
		String sql = "UPDATE books SET totalIssues = totalIssues + 1 WHERE isbn = ?";
		try (Connection conn = DBUtils.createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql ,  Statement.RETURN_GENERATED_KEYS);) {		
			stmt.setLong(1, isbn);
			stmt.executeUpdate();   
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return ;
		
	}

	private void updateBookIssuesTable(Integer bookId, Integer userId, Long isbn) {
		String sql = "INSERT into book_issues(bookId, userId, isbn, bookIssueId, fineDue, isFinePaid) "
				+ "VALUES(?, ?, ?, ?, 0, 0) ";
		Logger log = LoggerFactory.getLogger(BooksDao.class);
		
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
	
			stmt.setInt(1, bookId);
			stmt.setInt(2, userId);
			stmt.setLong(3, isbn);
			stmt.setInt(4, getMaxBookIssueId() + 1);
			log.info(stmt.toString());
			stmt.executeUpdate();
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return;
		
	}

	private int getMaxBookIssueId() {
		String sql = "SELECT MAX(bookIssueId) as maxi from book_issues";
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

	private void updateBookCopiesTable(int bookId) {
		String sql = "UPDATE book_copies SET isBorrowed = 1 where bookId = ? ";
		Logger log = LoggerFactory.getLogger(BooksDao.class);
		
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
	
			stmt.setInt(1, bookId);
			log.info(stmt.toString());
			stmt.executeUpdate();
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return;
	}

	public List<Book> getBooksByUser(Integer userId, String sql) {
		List<Book> books = new ArrayList<>();
		
		Logger log = LoggerFactory.getLogger(BooksDao.class);
		
		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
	
			stmt.setInt(1, userId);

			log.info(stmt.toString());
			ResultSet rs =  stmt.executeQuery();
			
			while(rs.next()) {
				books.add(resultSetToBook(rs));
			}
			
			return books;
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return null;
		
	}
	
	public List<Book> getCurrentBooks(Integer userId) {
		String sql = "SELECT * from books,book_issues where "
				+ "books.isbn = book_issues.isbn AND book_issues.returnDate IS NULL AND userId = ?";
		
		return getBooksByUser(userId, sql);
		
	}

	public List<Book> getPreviousBooks(Integer userId) {
		String sql = "SELECT * from books,book_issues where "
				+ "books.isbn = book_issues.isbn AND book_issues.returnDate IS NOT NULL AND userId = ?";
		
		return getBooksByUser(userId, sql);
		
	}

	/**
	 * Check if any copy of the book is not borrowed
	 * @param isbn
	 * @return Boolean True/False
	 */
	public BookCopy checkCopyAvailability(Long isbn){
		String sql = "Select * from book_copies where isbn = ? and isBorrowed = false";
		Logger log = LoggerFactory.getLogger(BooksDao.class);

		try (Connection conn = DBUtils.createConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {

			stmt.setLong(1, isbn);
			log.info(stmt.toString());
			ResultSet rs =  stmt.executeQuery();
			if(rs.next()) {
				BookCopy bookCopy = new BookCopy();
				bookCopy.setIsbn(rs.getLong("isbn"));
				bookCopy.setBorrowed(rs.getBoolean("isBorrowed"));
				System.out.println(bookCopy);
				return bookCopy;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
