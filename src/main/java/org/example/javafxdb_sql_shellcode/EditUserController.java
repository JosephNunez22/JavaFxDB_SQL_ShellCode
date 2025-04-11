package org.example.javafxdb_sql_shellcode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.javafxdb_sql_shellcode.db.ConnDbOps;

import java.io.IOException;

public class EditUserController {
    private static final ConnDbOps cdbop = App.getInstance();

    @FXML
    private TextField addressTF;

    @FXML
    private CheckBox changeEmailCB;

    @FXML
    private TextField fullNameTF;

    @FXML
    private TextField nEmailTF;

    @FXML
    private TextField oEmailTF;

    @FXML
    private TextField passwordTF;

    @FXML
    private TextField phoneTF;

    @FXML
    private VBox nEmailVbox;

    @FXML
    private BorderPane editBp;

    @FXML
    private VBox bottomVbox;
    private Person person;

    public void setUserData(Person person) {
        this.person = person;
        fullNameTF.setText(person.getName());
        phoneTF.setText(person.getPhoneNumber());
        addressTF.setText(person.getAddress());
        oEmailTF.setText(person.getEmail());
        passwordTF.setText(person.getPassword()); // Only if you store it this way
        addUserCard(person); // Now it’s safe to call
    }

    @FXML
    public void initialize() {
        changeEmailCB.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            nEmailVbox.setVisible(isSelected);
            nEmailVbox.setDisable(!isSelected);
        });
    }
    public void addUserCard(Person person) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("user-view-pane.fxml"));
            HBox userCard = (HBox) loader.load();
            UserViewController controller = loader.getController();
            controller.disableBtn();
            controller.setUserData(person);
            bottomVbox.getChildren().add(userCard);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void cancelBtnClicked(ActionEvent event) {
        Stage stage = (Stage) editBp.getScene().getWindow();
        stage.close();
    }
    @FXML
    void confirmBtnClicked(ActionEvent event) {
        String email = oEmailTF.getText().toLowerCase().trim();
        if (changeEmailCB.isSelected()){
            email = nEmailTF.getText().toLowerCase().trim();
        }
        if (fullNameTF.getText().isEmpty() || phoneTF.getText().isEmpty() || addressTF.getText().isEmpty() || email.isEmpty()) {
            System.out.println("All fields must be filled!");
            return; // Don't proceed if validation fails
        }
        if (!email.contains("@")) {
            System.out.println("Please enter a valid email.");
            return;
        }
        cdbop.updateUser(fullNameTF.getText().toLowerCase().trim()
                , email
                , phoneTF.getText().toLowerCase().trim()
                , addressTF.getText().toLowerCase().trim()
                , passwordTF.getText().trim());
    }
}
