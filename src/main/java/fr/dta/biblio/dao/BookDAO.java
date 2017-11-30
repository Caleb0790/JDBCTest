package fr.dta.biblio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.dta.biblio.main.Book;

public class BookDAO {

	public static boolean createBook(Book book) {
		Connection conn = DataBaseUtils.dbConnection();
		PreparedStatement stmt = null;

		if (conn != null & book != null) {
			try {
				stmt = conn.prepareStatement("INSERT INTO book(title,author) VALUES(?,?)",
						Statement.RETURN_GENERATED_KEYS);

				stmt.setString(1, book.getTitle());
				if (!(book.getAuthor().isEmpty())) {
					stmt.setString(2, book.getAuthor());
				}
				stmt.executeUpdate();
				ResultSet generatedKeys = stmt.getGeneratedKeys();
				generatedKeys.next();
				book.setId(generatedKeys.getInt("id"));
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

		} else	{
			System.out.println("New client creation failed!!");
		}

		return false;
	}

	public static boolean updateBook(Book book) {
		return false;
	}

	public static boolean deleteBook(Book book) {
		return false;
	}
}
