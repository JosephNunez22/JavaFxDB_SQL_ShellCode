package org.example.javafxdb_sql_shellcode;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.javafxdb_sql_shellcode.db.ConnDbOps;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class DisplayAllUserController {
    private static final ConnDbOps cdbop = App.getInstance();
    @FXML
    private ScrollPane infiniteSrollingPane;
    @FXML
    private VBox infiniteVboxForDisplay;

    @FXML
    public void initialize() {
        LinkedList<Person> allUsers = cdbop.listAllUsersGUI();
        for (int i = 0; i < Math.min(10, allUsers.size()); i++) {
            addUserCard(allUsers.get(i));
        }
        infiniteSrollingPane.vvalueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.doubleValue() >= infiniteSrollingPane.getVmax() - 0.1) {
                loadMoreUsers(allUsers);
            }
        });
    }
    public void addUserCard(Person person) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("user-view-pane.fxml"));
            HBox userCard = (HBox) loader.load();
            UserViewController controller = loader.getController();
            controller.disableBtn();
            controller.setUserData(person);
            infiniteVboxForDisplay.getChildren().add(userCard);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private int currentIndex = 10;
    private void loadMoreUsers(LinkedList<Person> allUsers) {
        int nextIndex = Math.min(currentIndex + 10, allUsers.size());
        for (int i = currentIndex; i < nextIndex; i++) {
            addUserCard(allUsers.get(i));
        }
        currentIndex = nextIndex;
    }
}
