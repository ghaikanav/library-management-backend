package com.sapient.pjp3.entity;


import java.sql.Date;
import lombok.Data;

@Data
public class Book {
	private long isbn;
	private String title;
	private String author;
	private Date published_date;
	private float price;
	private float rating;
	private String genre;
	private int quantity;
	private String book_cover;
	private int total_issues;
	private String publisher;

}


