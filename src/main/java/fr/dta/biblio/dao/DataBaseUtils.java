package fr.dta.biblio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

	static Connection dbConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			System.out.println("Data connection failed!");
			e.printStackTrace();
		}
		return conn;

	}

	public static void dbInit() {
		Connection conn=dbConnection();
		
		//Liste de requètes de création des tables 
		List<String> sqlInitStmts= new ArrayList<>();
		sqlInitStmts.add("DROP TABLE achete,client,book;");//Les tables sont supprimé avant d'être recrée
		sqlInitStmts.add("CREATE TABLE book(" + 
							"id serial PRIMARY KEY,"+
							"title varchar(255) NOT NULL," + 
							"author varchar(255));");
		sqlInitStmts.add("CREATE TABLE client(" + 
							"id serial PRIMARY KEY," +
							"lastname varchar(255) NOT NULL," + 
							"firstname varchar(255) NOT NULL,"+
							"gender varchar(20),"+
							"id_bookpref INTEGER REFERENCES book(id));");
		sqlInitStmts.add("CREATE TABLE achete(" +
							"id serial PRIMARY KEY,"+
							"id_client INTEGER REFERENCES client(id),"+
							"id_book INTEGER REFERENCES book(id));");

		if (conn != null) {
		   StringBuilder b = new StringBuilder();
		   sqlInitStmts.forEach(b::append);
		   PreparedStatement stmt=null;
			try {			
					stmt = conn.prepareStatement(b.toString());
					stmt.executeUpdate();
					

			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(stmt!=null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
		}else {System.out.println("Connection null");}
	}
	
	public static void achat(Client c, Book b) {
		Connection conn = DataBaseUtils.dbConnection();
		PreparedStatement stmt = null;

		if (conn != null & c != null & b!=null) {
			try {
				stmt = conn.prepareStatement("INSERT INTO achete(id_client,id_book) VALUES(?,?)",
						Statement.RETURN_GENERATED_KEYS);

				stmt.setInt(1, c.getId());
				stmt.setInt(2, b.getId());
				stmt.executeUpdate();
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

	}
}
