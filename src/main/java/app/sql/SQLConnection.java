package app.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class SQLConnection {
	public static Connection con = null;

	public static void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties appProps = new Properties();
			appProps.load(SQLConnection.class.getClassLoader().getResourceAsStream("db.properties"));
			con = DriverManager.getConnection(appProps.getProperty("url"), appProps.getProperty("username"),
					appProps.getProperty("password"));
			System.out.println("Connection Operationnal");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}