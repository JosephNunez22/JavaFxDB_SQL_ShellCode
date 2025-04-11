package org.example.javafxdb_sql_shellcode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.javafxdb_sql_shellcode.db.ConnDbOps;

import java.io.IOException;
import java.util.LinkedList;

public class QueryController {
    private static ConnDbOps cdbop = App.getInstance();
    @FXML
    private BorderPane deletePane;

    @FXML
    private TextField emailTF;

    @FXML
    private VBox bottomVbox;

    @FXML
    private VBox vBox;

    @FXML
    void cancelBtnClicked(ActionEvent event) {
        Stage stage = (Stage)deletePane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void searchBtnClicked(ActionEvent event) {
        try{
            if (emailTF.getText().isBlank()){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Input Error");
                a.setContentText("The Email Field cannot be empty!");
                a.showAndWait();
            } else {
                String name = emailTF.getText().toLowerCase().trim();
                Person user = cdbop.getUserByName(name);
                if (user != null) {
                    addUserCard(user);
                } else {
                    Alert notFound = new Alert(Alert.AlertType.INFORMATION);
                    notFound.setTitle("User Not Found");
                    notFound.setContentText("No user found with the provided name.");
                    notFound.showAndWait();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void addUserCard(Person person) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("user-view-pane.fxml"));
            HBox userCard = (HBox) loader.load();
            UserViewController controller = loader.getController();
            controller.setUserData(person);
            bottomVbox.getChildren().add(userCard);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
