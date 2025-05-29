package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Replace with your own database connection details
    private static final String URL = "jdbc:mysql://127.0.0.7:3306/exportTrackingDB";
    private static final String USER = "root";
    private static final String PASSWORD = "brandontk3@";  // change to your actual password
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // For JDBC 4 and above, the driver loads automatically,
                // but it doesn’t hurt to specify it:
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                // In a real application, log this error or show a user-friendly message.
            }
        }
        return connection;
    }
}
