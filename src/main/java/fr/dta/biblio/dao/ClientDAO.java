package fr.dta.biblio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.dta.biblio.main.Client;

public class ClientDAO {

	public static boolean createClient(Client client) {
		Connection conn = DataBaseUtils.dbConnection();
		PreparedStatement stmt = null;

		if (conn != null & client != null) {
			try {
				stmt = conn.prepareStatement("INSERT INTO client(lastname,firstname,gender) VALUES(?,?,?)",
						Statement.RETURN_GENERATED_KEYS);

				stmt.setString(1, client.getLastname());
				stmt.setString(2, client.getFirstname());
				if (!(client.getGender().toString().isEmpty())) {
					stmt.setString(3, client.getGender().toString());
				}
				stmt.executeUpdate();
				ResultSet generatedKeys = stmt.getGeneratedKeys();
				generatedKeys.next();
				client.setId(generatedKeys.getInt("id"));
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

	public static boolean updateBook(Client client) {
		return false;
	}

	public static boolean deleteBook(Client client) {
		return false;
	}
}
