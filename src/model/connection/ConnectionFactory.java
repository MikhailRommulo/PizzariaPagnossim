package model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static Connection cnx = null;
	private static String usuario = "root";
	private static String senha = "admin";
	private static String PathBase = "localhost:3306/db_pizzaria?useTimezone=true&serverTimezone=UTC";
	private static final String URL = "jdbc:mysql://" + PathBase ;
	
	public static Connection conectar() throws SQLException {
		try {
			//estabelecendo conexão
			cnx = DriverManager.getConnection(URL, usuario, senha);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cnx;
	}
	
	public static void fechar() {
		try {
			cnx.close();
			cnx = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
