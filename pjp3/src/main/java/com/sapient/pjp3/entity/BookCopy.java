package com.sapient.pjp3.entity;

import java.sql.Date;
import lombok.Data;

@Data
public class BookCopy {
	private Integer bookId;
	private Long isbn;
	private Date addedAt;
	private Boolean isBorrowed;

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public Date getAddedAt() {
		return addedAt;
	}

	public void setAddedAt(Date addedAt) {
		this.addedAt = addedAt;
	}

	public Boolean getBorrowed() {
		return isBorrowed;
	}

	public void setBorrowed(Boolean borrowed) {
		isBorrowed = borrowed;
	}
}
