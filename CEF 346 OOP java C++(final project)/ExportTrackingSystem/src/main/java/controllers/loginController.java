package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import services.AuthService;

public class loginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    // Instance to call the AuthService methods
    private AuthService authService = new AuthService();

    // Called when the "Login" button is clicked.
    @FXML
   
    // Called when the user clicks the "Sign up" hyperlink/button in the login view.
     private void handleLogin(ActionEvent event) {
    String username = usernameField.getText().trim();
    String password = passwordField.getText().trim();

    if (username.isEmpty() || password.isEmpty()) {
        showAlert(Alert.AlertType.ERROR, "Validation Error", "Please enter both username and password.");
        return;
    }

    if (authService.validateLogin(username, password)) {
        showAlert(Alert.AlertType.INFORMATION, "Login Success", "Welcome!");

        // Load the dashboard scene.
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/dashboard.fxml"));
            Parent dashboardParent = loader.load();

            // Get the dashboard controller and pass the username.
            DashBoardController dashboardController = loader.getController();
            dashboardController.setWelcomeMessage(username);

            Scene dashboardScene = new Scene(dashboardParent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(dashboardScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    } else {
        showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid credentials.");
    }
}


    // A helper method to show alerts.
    private void showAlert(Alert.AlertType alertType, String title, String message) {
         Alert alert = new Alert(alertType);
         alert.setTitle(title);
         alert.setHeaderText(null);
         alert.setContentText(message);
         alert.showAndWait();
    }
}
