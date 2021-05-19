package com.sapient.pjp3.entity;

import java.sql.Date;
import lombok.Data;

@Data
public class BookRequest {
	private Integer bookRequestId;
	private Integer userId;
	private String title;
	private String Author;
	private Date requestedAt;

	public Integer getBookRequestId() {
		return bookRequestId;
	}

	public void setBookRequestId(Integer bookRequestId) {
		this.bookRequestId = bookRequestId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public Date getRequestedAt() {
		return requestedAt;
	}

	public void setRequestedAt(Date requestedAt) {
		this.requestedAt = requestedAt;
	}
}
