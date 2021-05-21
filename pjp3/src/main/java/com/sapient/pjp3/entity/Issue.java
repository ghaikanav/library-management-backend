package com.sapient.pjp3.entity;

import java.sql.Date;
import lombok.Data;

@Data
public class Issue {
	private Integer issueId;
	private Integer userId;
	private Integer bookId;
	private Long isbn;
	private Date borrowDate;
	private Date returnDate;
	private Double fineDue;

	public Integer getIssueId() {
		return issueId;
	}

	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

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

	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Double getFineDue() {
		return fineDue;
	}

	public void setFineDue(Double fineDue) {
		this.fineDue = fineDue;
	}

	public Boolean getFinePaid() {
		return isFinePaid;
	}

	public void setFinePaid(Boolean finePaid) {
		isFinePaid = finePaid;
	}

	private Boolean isFinePaid;
	
}
