package com.sapient.pjp3.entity;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
	private int id;
	private String fullname;
	private Date created_at;
	private int total_borrowed_books;
	private int current_borrowed_books;
	private int fine;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public int getTotal_borrowed_books() {
		return total_borrowed_books;
	}
	public void setTotal_borrowed_books(int total_borrowed_books) {
		this.total_borrowed_books = total_borrowed_books;
	}
	public int getCurrent_borrowed_books() {
		return current_borrowed_books;
	}
	public void setCurrent_borrowed_books(int current_borrowed_books) {
		this.current_borrowed_books = current_borrowed_books;
	}
	public int getFine() {
		return fine;
	}
	public void setFine(int fine) {
		this.fine = fine;
	}
}
