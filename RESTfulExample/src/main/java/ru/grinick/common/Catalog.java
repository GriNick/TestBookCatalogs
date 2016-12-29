package ru.grinick.common;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Catalog {
	private List<Book> allBooks = new ArrayList<Book>();
	BookFactory factory = BookFactory.getInstance();
	private void generateDummyCatalog() {
		System.out.println("in generateDummyCatalog");
		allBooks.add(factory.createBook("Book1", "Author1", "2003-01-01", 2));
		allBooks.add(factory.createBook("Book2", "Author2", "2004-01-01", 1));
		allBooks.add(factory.createBook("Book3", "Author3", "2005-01-01", 2));
		allBooks.add(factory.createBook("Book4", "Author4", "2006-01-01", 1));
		allBooks.add(factory.createBook("Book5", "Author5", "2007-01-01", 1));		
	}
	public Catalog() {
		super();
		System.out.println("in Catalog constructor");
		generateDummyCatalog();
	}
	public int addBook(Book nBook) {
	      boolean bookExists = false;
	      for(Book book: allBooks){
	         if(book.getId() == nBook.getId()){
	            bookExists = true;
	            break;
	         }
	      }		
	      if(!bookExists){
	    	 if (nBook.getId() == null) {
	    		 nBook.setId(UUID.randomUUID());
	    	 }
	         allBooks.add(nBook);
	         return 1 ;
	      }	  
	      return 0;
	}
	
	public int deleteBook(UUID id) {
	      for(int i=0; i < allBooks.size() ; i++){
	         if(allBooks.get(i).getId().equals(id) ) {
	            allBooks.remove(i);		
	            return 1;
	         }
	      }		
	      return 0;		
	}
	
	public int updateBook(Book uBook) {
	      for(Book book: allBooks){
	         if(book.getId().equals(uBook.getId()) ){

	        	 if (!book.getAuthor().equals(uBook.getAuthor())) {
	        		 book.setAuthor(uBook.getAuthor());
	        	 }
	        	 if (!book.getName().equals(uBook.getName())) {
	        		 book.setName(uBook.getName());
	        	 }	
	        	 if (!book.getDt().equals(uBook.getDt())) {
	        		 book.setDt(uBook.getDt());
	        	 }
	        	 if (book.getType() != uBook.getType()) {
	        		 book.setType(uBook.getType());
	        	 }	        	 
	            return 1;
	         }
	      }		
	      return 0;		
	}
	public Book getBook(UUID id) {
		for(Book book: allBooks) {
			System.out.println("current uuid = " + book.getId().toString());
			if (book.getId().equals(id)) {
				return book;
			}
		}
		System.out.println("cannot find the book with uuid ="+ id.toString());
		return null;
	}
	
	public List<Book> getBooks(int type) {
		if (type == 0) {
			return allBooks;
		}
		List<Book> result = new ArrayList();
		for(Book book: allBooks){
			if (book.getType() == type ) {
				result.add(book);
			}
		}
		return result;
	}
}
