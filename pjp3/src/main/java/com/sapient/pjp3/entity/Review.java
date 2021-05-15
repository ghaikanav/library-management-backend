package com.sapient.pjp3.entity;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Review {
	private int id;
	private int issue_id;
	private int user_id;
	private long isbn;
	private int rating;
	private String review;
	
}
