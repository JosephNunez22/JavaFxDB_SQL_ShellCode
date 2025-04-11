package org.example.javafxdb_sql_shellcode;

import javafx.scene.image.Image;

import java.util.Objects;

public class Person {

    private transient Image image;
    private static int id = 0;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String password;

    public Person(String name, String email, String address, String phoneNumber) {
        this.email = email;
        this.password = password;
        id++;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.image = new Image(Objects.requireNonNull(getClass().getResource("/images/Default-Profile-Picture-Download-PNG-Image.png")).toExternalForm());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}