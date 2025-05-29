package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class DashBoardController {

    @FXML
    private Label welcomeLabel;

    // This method can be used to pass the username (or any other user data) from the login screen.
    public void setWelcomeMessage(String username) {
        welcomeLabel.setText("Welcome " + username + " to your Dashboard!");
    }
    
    // Handle a logout action. For now, it just switches back to the login scene.
    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            Parent loginParent = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
            Scene loginScene = new Scene(loginParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(loginScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
