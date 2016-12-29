package ru.grinick.common;

import java.util.Date;
import java.util.UUID;

public class Book {
	
	private UUID id;
	private String name;
	private String author;
	private String dt;
	private int type;
	
	public Book() {
		super();
	}
	
	public Book(UUID id, String name, String author, String dt, int type) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.dt = dt;
		this.type = type;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public String toString() {
		return "{ name: " + this.name + " , author: " + this.author + " , date = " + this.dt + " } ";
	}
}
