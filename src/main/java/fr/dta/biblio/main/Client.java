package fr.dta.biblio.main;

public class Client {
	private int id;
	private String lastname;
	private String firstname;
	private Gender gender;
	private Book bookPref;
	
	public Client(String lastname, String firstname) {
		this.setLastname(lastname);
		this.setFirstname(firstname);
	}
	
	public Client(String lastname, String firstname, Gender gender) {
		this(lastname,firstname);
		this.setGender(gender);
	}
	
	public Client(String lastname, String firstname, Gender gender, Book bookPref) {
		this(lastname,firstname,gender);
		this.setBookPref(bookPref);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Book getBookPref() {
		return bookPref;
	}

	public void setBookPref(Book bookPref) {
		this.bookPref = bookPref;
	}

}
