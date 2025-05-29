package services;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthService {

    // Validate login credentials by checking the database.
    public boolean validateLogin(String usernameOrEmail, String password) {
        String sql = "SELECT * FROM users WHERE (username = ? OR email = ?) AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, usernameOrEmail);
            pst.setString(2, usernameOrEmail);
            pst.setString(3, password); // In production, compare hashed passwords

            ResultSet rs = pst.executeQuery();
            return rs.next();  // Returns true if a record is found

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Register a new user into the database.
    public boolean registerUser(String username, String email, String password) {
        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, username);
            pst.setString(2, email);
            pst.setString(3, password);  // Remember: password should be hashed in real applications

            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;  // Return true if insertion was successful

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
