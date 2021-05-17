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
	private Boolean isFinePaid;
	
}
