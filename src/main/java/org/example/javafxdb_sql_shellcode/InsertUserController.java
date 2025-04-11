package org.example.javafxdb_sql_shellcode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.javafxdb_sql_shellcode.db.ConnDbOps;

public class InsertUserController {
    private static final ConnDbOps cdbop = App.getInstance();

    @FXML
    private TextField addressTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField fullNameTF;

    @FXML
    private TextField passwordTF;

    @FXML
    private TextField phoneTF;

    @FXML
    private BorderPane registerUserBP;

    @FXML
    public void initialize() {
        fullNameTF.setDisable(false);
        emailTF.setDisable(false);
        phoneTF.setDisable(false);
        addressTF.setDisable(false);
        passwordTF.setDisable(false);

        fullNameTF.setEditable(true);
        emailTF.setEditable(true);
        phoneTF.setEditable(true);
        addressTF.setEditable(true);
        passwordTF.setEditable(true);
    }

    @FXML
    void cancelBtnClicked(ActionEvent event) {
        Stage stage = (Stage) registerUserBP.getScene().getWindow();
        stage.close();
    }

    @FXML
    void confirmBtnClicked(ActionEvent event) {
        cdbop.insertUser(fullNameTF.getText().toLowerCase().trim()
                , emailTF.getText().toLowerCase().trim()
                , phoneTF.getText().toLowerCase().trim()
                , addressTF.getText().toLowerCase().trim()
                , passwordTF.getText().trim());
    }
}
