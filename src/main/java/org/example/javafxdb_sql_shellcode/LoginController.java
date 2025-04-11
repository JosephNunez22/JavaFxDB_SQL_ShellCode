package org.example.javafxdb_sql_shellcode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.javafxdb_sql_shellcode.db.ConnDbOps;

import java.io.IOException;

public class LoginController {
    private static final ConnDbOps cdbop = App.getInstance();
    @FXML
    private TextField emailTF;

    @FXML
    private TextField passwordTF;

    @FXML
    void loginBtnClicked(ActionEvent event) {
        String email = emailTF.getText().toLowerCase().trim();
        String password = passwordTF.getText().trim();
        Person user = cdbop.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful!");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("edit-user.fxml"));
                Parent root = loader.load();
                EditUserController controller = loader.getController();
                controller.setUserData(user);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Login failed
            System.out.println("Invalid email or password.");
        }
    }
}
