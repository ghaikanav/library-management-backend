package com.sapient.pjp3.entity;

import java.sql.Date;
import lombok.Data;

@Data
public class BookCopies {
	private int id;
	private long isbn;
	private Date added_at;
	private boolean is_borrowed;
}
