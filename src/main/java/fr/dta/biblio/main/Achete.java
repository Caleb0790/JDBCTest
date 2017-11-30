package fr.dta.biblio.main;

public class Achete {
	private int id;
	private Client client;
	private Book book;
	
	public Achete(Client client, Book book) {
		this.setClient(client);
		this.setBook(book);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	private void setClient(Client client) {
		this.client = client;
	}

	public Book getBook() {
		return book;
	}

	private void setBook(Book book) {
		this.book = book;
	}
	
}
