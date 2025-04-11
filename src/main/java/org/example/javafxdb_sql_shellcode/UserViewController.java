package org.example.javafxdb_sql_shellcode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;

public class UserViewController {

    @FXML
    private Label addressLbl;

    @FXML
    private Label emailLbl;

    @FXML
    private Label nameLbl;

    @FXML
    private Label phoneLbl;

    @FXML
    private ImageView profilePicIV;

    @FXML
    private Button profileBtn;

    @FXML
    public void initialize() {

    }
    @FXML
    void profilePicBtnClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose New Profile Picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if (selectedFile != null) {
            Image newImage = new Image(selectedFile.toURI().toString());
            profilePicIV.setImage(newImage);
        }
    }
    public void setUserData(Person person) {
        nameLbl.setText(person.getName());
        emailLbl.setText(person.getEmail());
        phoneLbl.setText(person.getPhoneNumber());
        addressLbl.setText(person.getAddress());
        profilePicIV.setImage(person.getImage());
    }
    public void disableBtn(){
        profileBtn.setDisable(true);
    }
}
