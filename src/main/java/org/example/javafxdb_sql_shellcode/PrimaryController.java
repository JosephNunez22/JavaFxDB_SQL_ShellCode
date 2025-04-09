package org.example.javafxdb_sql_shellcode;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.javafxdb_sql_shellcode.db.ConnDbOps;

import java.sql.*;

public class PrimaryController {
    private static ConnDbOps cdbop = App.getInstance();

    @FXML
    void connectToDatabaseBtnClicked(ActionEvent event) {
        cdbop.connectToDatabase();
    }

    @FXML
    void deleteUserBtnClicked(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("delete-confirmation.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Confirm Deletion");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void displayAllUsersBtnClicked(ActionEvent event) {
        cdbop.listAllUsers();
    }

    @FXML
    void editUserBtnClicked(ActionEvent event) {
        //cdbop.updateUser();
    }

    @FXML
    void exitAppBtnClicked(ActionEvent event) {

    }

    @FXML
    void inserUserToDatabasebtnClicked(ActionEvent event) {
        //cdbop.insertUser();
    }

    @FXML
    void queryByNameBtnClicked(ActionEvent event) {
        //cdbop.queryUserByName();
    }

}

