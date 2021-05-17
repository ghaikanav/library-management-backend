package com.sapient.pjp3.entity;

import java.sql.Date;
import lombok.Data;

@Data
public class BookCopy {
	private Integer bookId;
	private Long isbn;
	private Date addedAt;
	private Boolean isBorrowed;
}
