package fr.dta.biblio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.dta.biblio.main.Achete;

public class AcheteDAO {
	public static boolean createAchete(Achete achete) {
		Connection conn = DataBaseUtils.dbConnection();
		PreparedStatement stmt = null;

		if (conn != null & achete != null) {
			try {
				stmt = conn.prepareStatement("INSERT INTO achete(id_client,id_book) VALUES(?,?)",
						Statement.RETURN_GENERATED_KEYS);

				stmt.setInt(1, achete.getClient().getId());
				stmt.setInt(2, achete.getBook().getId());
				stmt.executeUpdate();
				ResultSet generatedKeys = stmt.getGeneratedKeys();
				generatedKeys.next();
				achete.setId(generatedKeys.getInt("id"));
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

	public static boolean updateAchete(Achete achete) {
		return false;
	}

	public static boolean deleteAchete(Achete achete) {
		return false;
	}
}
