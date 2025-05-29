package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.AuthService;

public class SignupController {

    @FXML private TextField usernameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    // Instance of AuthService for signup logic
    private AuthService authService = new AuthService();

    @FXML
    private void handleSignup(ActionEvent event) {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields are required.");
            return;
        }

        if (authService.registerUser(username, email, password)) {
            showAlert(Alert.AlertType.INFORMATION, "Signup Success", "Registration successful!");
            // Switch back to the login screen here.
            System.out.println("Switching to login screen");
        } else {
            showAlert(Alert.AlertType.ERROR, "Signup Failed", "Registration failed. Please try again.");
        }
    }

    @FXML
    private void gotoLogin(ActionEvent event) {
        // Code to switch back to the login scene.
        System.out.println("Switching back to login screen");
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        // Any initialization code can go here.
    }
}
