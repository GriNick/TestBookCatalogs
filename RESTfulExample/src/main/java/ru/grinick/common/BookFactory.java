package ru.grinick.common;

import java.util.UUID;

public class BookFactory {
	private static BookFactory instance = new BookFactory();

	private BookFactory() {		
		super();
		System.out.println("in BookFactory constructor");
	}
	public static BookFactory getInstance() {
		return instance;
	}

	public Book createBook(String name, String author, String dt, int type) {
		Book result = new Book(UUID.randomUUID(), name, author, dt, type);
		return result;
	}
	
}
