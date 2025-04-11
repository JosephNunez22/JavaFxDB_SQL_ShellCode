package org.example.javafxdb_sql_shellcode;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Scanner;
import javafx.scene.paint.Color;
import org.example.javafxdb_sql_shellcode.db.ConnDbOps;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static ConnDbOps cdbop;

    public static ConnDbOps getInstance() {
        if (cdbop == null) {
            synchronized (ConnDbOps.class) {
                if (cdbop == null) {
                    cdbop = new ConnDbOps();
                }
            }
        }
        return cdbop;
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 401, 400);
        stage.setScene(scene);
        stage.show();
    }
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        return fxmlLoader.load();
    }
    public static void main(String[] args) {
        cdbop = new ConnDbOps();
        Scanner scan = new Scanner(System.in);

        char input;
        do {
            System.out.println(" ");
            System.out.println("=============== Menu ================");
            System.out.println("| To start GUI,           press 'g' |");
            System.out.println("| To connect to DB,       press 'd' |");
            System.out.println("| To display all users,   press 'a' |");
            System.out.println("| To insert to the DB,    press 'i' |");
            System.out.println("| To query by name,       press 'q' |");
            System.out.println("| To edit user,           press 'm' |"); // Edit user GUI Run
            System.out.println("| To delete user,         press 'x' |"); // Delete user GUI Run
            System.out.println("| To exit,                press 'e' |");
            System.out.println("=====================================");
            System.out.print("Enter your choice: ");
            input = scan.next().charAt(0);
            switch (input) {
                case 'g':
                     launch(args); //GUI
                    break;
                case 'd':
                    cdbop.connectToDatabase(); //Your existing method
                    break;
                case 'a':
                    cdbop.listAllUsers(); //all users in DB
                    break;
                case 'i':
                    System.out.print("Enter Name: ");
                    String name = scan.nextLine();
                    if (name.trim().isEmpty()) {
                        System.out.println("Name cannot be empty.");
                        break; // Stop execution of case 'i' if name is empty
                    }
                    System.out.print("Enter Email: ");
                    String email = scan.nextLine();
                    if (email.trim().isEmpty() || !email.contains("@") || !email.contains(".")) {
                        System.out.println("Please enter a valid email address.");
                        break;
                    }
                    System.out.print("Enter Phone: ");
                    String phone = scan.nextLine();
                    if (phone.trim().isEmpty() || !phone.matches("\\d{10}")) {
                        System.out.println("Please enter a valid phone number (10 digits).");
                        break;
                    }
                    System.out.print("Enter Address: ");
                    String address = scan.nextLine();
                    if (address.trim().isEmpty()) {
                        System.out.println("Address cannot be empty.");
                        break;
                    }
                    System.out.print("Enter Password: ");
                    String password = scan.nextLine();
                    if (password.trim().isEmpty()) {
                        System.out.println("Password cannot be empty.");
                        break;
                    }
                    cdbop.insertUser(name.toLowerCase().trim(), email.toLowerCase().trim(), phone.trim(), address.trim(), password.trim());
                    System.out.println("User inserted successfully!");
                    break;
                case 'q':
                    System.out.print("Enter the name to query: ");
                    String queryName = scan.next();
                    cdbop.queryUserByName(queryName.toLowerCase().trim()); //Your queryUserByName method
                    break;
                case 'm': // edit
                    System.out.print("Enter the email of the user to edit: ");
                    String editEmail = scan.next().toLowerCase().trim();
                    System.out.print("Enter new Name: ");
                    String newName = scan.next().trim();
                    System.out.print("Enter new Phone: ");
                    String newPhone = scan.next().trim();
                    System.out.print("Enter new Address: ");
                    String newAddress = scan.next().trim();
                    System.out.print("Enter new Password: ");
                    String newPassword = scan.next().trim();
                    System.out.print("Enter a new email, leave blank if you don't want to modify email");
                    if (!scan.next().isBlank()){
                        editEmail = scan.next().toLowerCase().trim();
                    }
                    cdbop.updateUser(editEmail, newName, newPhone, newAddress, newPassword);
                    break;
                case 'x': // delete
                    System.out.print("Enter the email of the user to delete: ");
                    String deleteEmail = scan.next();
                    cdbop.deleteUserByEmail(deleteEmail.toLowerCase().trim());
                    break;
                case 'e':
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            System.out.println(" ");
        } while (input != 'e');
        scan.close();
    }
}
