package org.example.javafxdb_sql_shellcode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.javafxdb_sql_shellcode.db.ConnDbOps;

public class DeleteConfirmationController {
    private static ConnDbOps cdbop = App.getInstance();
    @FXML
    private TextField emailTF;

    @FXML
    private AnchorPane deletePane;

    @FXML
    void cancelBtnClicked(ActionEvent event) {
        Stage stage = (Stage)deletePane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void deleteBtnClicked(ActionEvent event) {
        try{
            if (emailTF.getText().isBlank()){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Input Error");
                a.setContentText("The Email Field cannot be empty!");
                a.showAndWait();
            } else {
                cdbop.deleteUserByEmail(emailTF.getText().toLowerCase().trim());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
