package com.sapient.pjp3.entity;

import lombok.Data;

@Data
public class Review {
	private Integer reviewId;
	private Integer issueId;
	private Integer userId;
	private Long isbn;
	private Integer rating;
	private String review;
}
