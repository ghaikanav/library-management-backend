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
}
