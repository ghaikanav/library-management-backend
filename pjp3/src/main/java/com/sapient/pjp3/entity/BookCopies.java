package com.sapient.pjp3.entity;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BookCopies {
	private int id;
	private long isbn;
	private Date added_at;
	private boolean is_borrowed;
}
