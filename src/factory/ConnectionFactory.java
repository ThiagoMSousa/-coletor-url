package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	// Obtém conexão com o banco de dados
	public static Connection getConnection() throws SQLException {
		return DriverManager
				.getConnection("jdbc:mysql://localhost/dbtesteibm?user=root&password=1234");
	}

}
