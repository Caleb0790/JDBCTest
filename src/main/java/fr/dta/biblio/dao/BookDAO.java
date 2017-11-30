package fr.dta.biblio.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.dta.biblio.main.Book;

public class BookDAO {

	public static boolean createBook(Book book) {
		DataBaseUtils.dbConnection();

		try {
			if (book != null) {
				DataBaseUtils.stmt = DataBaseUtils.conn.prepareStatement(
						"INSERT INTO book(title,author) VALUES(?,?)",
						Statement.RETURN_GENERATED_KEYS);

				DataBaseUtils.stmt.setString(1, book.getTitle());
				if (!(book.getAuthor().isEmpty())) {
					DataBaseUtils.stmt.setString(2, book.getAuthor());
				}
				DataBaseUtils.stmt.executeUpdate();
				ResultSet generatedKeys = DataBaseUtils.stmt.getGeneratedKeys();
				generatedKeys.next();
				book.setId(generatedKeys.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseUtils.close(true);
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
