package com.sapient.pjp3.entity;

import java.sql.Date;
import lombok.Data;

@Data
public class BookRequest {
	private int user_id;
	private String book_title;
	private String author;
	private Date requested_at;
}
