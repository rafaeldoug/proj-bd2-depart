package br.cesed.si.bd2.projeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	private static final String URL = "jdbc:postgresql://localhost:5432/loja_departamento?userTimezone=true&serverTimezone=UTC";
	private static final String USER = "postgres";
	private static final String PASS = "1234";

	public static Connection createConnection() throws SQLException {

		return DriverManager.getConnection(URL, USER, PASS);
	}


}
