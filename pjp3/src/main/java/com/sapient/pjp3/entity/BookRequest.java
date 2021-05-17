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
}
