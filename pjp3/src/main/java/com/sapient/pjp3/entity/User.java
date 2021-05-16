package com.sapient.pjp3.entity;

import java.sql.Date;
import lombok.Data;

@Data
public class User {
	private int id;
	private String email;
	private String password;
	private String fullname;
	private Date created_at;
	private int total_borrowed_books;
	private int current_borrowed_books;
	private int fine;
}
