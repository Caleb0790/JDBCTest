package fr.dta.biblio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.dta.biblio.main.Book;
import fr.dta.biblio.main.Client;

public class DataBaseUtils {

	private static String url = "jdbc:postgresql://localhost:5432/biblio";
	private static String user = "biblio_admin";
	private static String pwd = "biblio_admin";
	public static Connection conn;
	public static PreparedStatement stmt;

	static void dbConnection() {

		try {
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			System.out.println("Data connection failed! Check url, user or password.");
			e.printStackTrace();
		}
	}

	static void close(boolean statement) {

		try {
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (statement) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void dbInit() {

		// Liste de requètes de création des tables
		List<String> sqlInitStmts = new ArrayList<>();
		sqlInitStmts.add("DROP TABLE achete,client,book;");// Les tables sont supprimé avant d'être recrée
		sqlInitStmts.add("CREATE TABLE book(" + "id serial PRIMARY KEY," + "title varchar(255) NOT NULL,"
				+ "author varchar(255));");
		sqlInitStmts.add("CREATE TABLE client(" + "id serial PRIMARY KEY," + "lastname varchar(255) NOT NULL,"
				+ "firstname varchar(255) NOT NULL," + "gender varchar(20),"
				+ "id_bookpref INTEGER REFERENCES book(id));");
		sqlInitStmts.add("CREATE TABLE achete(" + "id serial PRIMARY KEY," + "id_client INTEGER REFERENCES client(id),"
				+ "id_book INTEGER REFERENCES book(id));");

		dbConnection();
		if (conn != null) {
			// Concatene les requetes SQL
			StringBuilder b = new StringBuilder();
			sqlInitStmts.forEach(b::append);

			try {
				stmt = conn.prepareStatement(b.toString());
				stmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(true);
			}

		} else {
			System.out.println("Connection null");
		}
	}

	public static void achat(Client c, Book b) {
		dbConnection();

		if (c != null & b != null) {
			try {
				stmt = conn.prepareStatement("INSERT INTO achete(id_client,id_book) VALUES(?,?)",
						Statement.RETURN_GENERATED_KEYS);

				stmt.setInt(1, c.getId());
				stmt.setInt(2, b.getId());
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(true);
			}
		}

	}

	public static void requete1(Client c) {
		dbConnection();

		try {
			stmt = conn.prepareStatement(
							"SELECT book.title FROM book JOIN achete ON id_book=book.id WHERE id_client=?");
			stmt.setInt(1, c.getId());
			ResultSet resultSet = stmt.executeQuery();
			
			System.out.println(c.getLastname() + " à acheté:");
			while (resultSet.next()) {
				String title = resultSet.getString("title");
				System.out.println("\"" + title + "\"");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		close(true);
	}

	public static void requete2(Book b) {
		dbConnection();

		try {
			stmt = conn.prepareStatement(
					"SELECT client.lastname FROM client JOIN achete ON id_client=client.id WHERE id_book=?");
			stmt.setInt(1, b.getId());
			ResultSet resultSet = stmt.executeQuery();
			
			System.out.println("Le livre \""+b.getTitle() + "\" à été acheté par:");
			while (resultSet.next()) {
				String lastname = resultSet.getString("lastname");
				System.out.println(lastname);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		close(true);
	}
}
