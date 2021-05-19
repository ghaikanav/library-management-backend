package com.sapient.pjp3.entity;

import java.sql.Date;
import lombok.Data;

@Data
public class User {
	private Integer id;
	private String email;
	private String password;
	private String fullname;
	private Date createdAt;
	private Integer totalBorrowedBooks;
	private Integer currentBorrowedBooks;
	private Integer fine;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getTotalBorrowedBooks() {
		return totalBorrowedBooks;
	}

	public void setTotalBorrowedBooks(Integer totalBorrowedBooks) {
		this.totalBorrowedBooks = totalBorrowedBooks;
	}

	public Integer getCurrentBorrowedBooks() {
		return currentBorrowedBooks;
	}

	public void setCurrentBorrowedBooks(Integer currentBorrowedBooks) {
		this.currentBorrowedBooks = currentBorrowedBooks;
	}

	public Integer getFine() {
		return fine;
	}

	public void setFine(Integer fine) {
		this.fine = fine;
	}
}
