package ru.grinick.common;

public class CatalogFactory {
	private static CatalogFactory instance = new CatalogFactory();
	private CatalogFactory() {
		super();
		System.out.println("in CatalogFactory constructor");
	}
	public static CatalogFactory getInstance() {
		return instance;
	}
	private Catalog catalog;
	
	public Catalog getCatalog() {		
		if (catalog != null) return catalog;
		else {
			catalog = new Catalog();
			return catalog;
		}
	}

}
