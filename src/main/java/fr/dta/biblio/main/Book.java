package fr.dta.biblio.main;

public class Book {
	private int id;
	private String title;
	private String author;
	
	public Book(String title) {
		this.title=title;
	}
	
	public Book(String title, String author) {
		this(title);
		this.author=author;
	}
		
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
