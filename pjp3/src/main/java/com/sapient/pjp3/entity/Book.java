package com.sapient.pjp3.entity;


import java.sql.Date;
import lombok.Data;

@Data
public class Book {
	private Long isbn;
	private String title;
	private String author;
	private Date publishedDate;
	private Double price;
	private Double rating;
	private String genre;
	private Integer quantity;
	private String bookCover;
	private Integer totalIssues;
	private String publisher;

}


