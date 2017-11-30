package fr.dta.biblio.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.dta.biblio.main.Client;

public class ClientDAO {

	public static boolean createClient(Client client) {
		DataBaseUtils.dbConnection();

			try {
				if (client != null) {
					DataBaseUtils.stmt = DataBaseUtils.conn.prepareStatement(
							"INSERT INTO client(lastname,firstname,gender,id_bookpref) VALUES(?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);
	
					DataBaseUtils.stmt.setString(1, client.getLastname());
					DataBaseUtils.stmt.setString(2, client.getFirstname());
					if (!(client.getGender().toString().isEmpty())) {
						DataBaseUtils.stmt.setString(3, client.getGender().toString());
					}
					DataBaseUtils.stmt.setInt(4, client.getBookPref().getId());
					
					DataBaseUtils.stmt.executeUpdate();
					
					ResultSet generatedKeys = DataBaseUtils.stmt.getGeneratedKeys();
					generatedKeys.next();
					client.setId(generatedKeys.getInt("id"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DataBaseUtils.close(true);
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
