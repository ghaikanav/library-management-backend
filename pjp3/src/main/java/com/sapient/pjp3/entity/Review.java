package com.sapient.pjp3.entity;

import lombok.Data;

@Data
public class Review {
	private int review_id;
	private int issue_id;
	private int user_id;
	private long isbn;
	private int rating;
	private String review;
}
