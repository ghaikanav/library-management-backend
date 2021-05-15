package com.sapient.pjp3.entity;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Issue {
	private int id;
	private int user_id;
	private int book_id;
	private long isbn;
	private Date borrow_date;
	private Date return_date;
	private float fine_due;
	private boolean is_fine_paid;
	
}
