/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.javafxdb_sql_shellcode.db;

import org.example.javafxdb_sql_shellcode.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import static java.sql.DriverManager.getConnection;

/**
 *
 * @author MoaathAlrajab
 */
public class ConnDbOps {
    final String MYSQL_SERVER_URL = "jdbc:mysql://nuneja38000.mysql.database.azure.com/";
    final String DB_URL = MYSQL_SERVER_URL + "nuneja38000";
    final String USERNAME = "nuneja38";
    final String PASSWORD = "FARM123$";

    public boolean connectToDatabase() {
        boolean hasRegistredUsers = false;


        //Class.forName("com.mysql.jdbc.Driver");
        try {
            //First, connect to MYSQL server and create the database if not created
            Connection conn = getConnection(MYSQL_SERVER_URL, USERNAME, PASSWORD);
            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS nuneja38");
            statement.close();
            conn.close();

            //Second, connect to the database and create the table "users" if cot created
            conn = getConnection(DB_URL, USERNAME, PASSWORD);
            statement = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INT( 10 ) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                    + "name VARCHAR(200) NOT NULL,"
                    + "email VARCHAR(200) NOT NULL UNIQUE,"
                    + "phone VARCHAR(200),"
                    + "address VARCHAR(200),"
                    + "password VARCHAR(200) NOT NULL"
                    + ")";
            statement.executeUpdate(sql);

            //check if we have users in the table users
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM users");

            if (resultSet.next()) {
                int numUsers = resultSet.getInt(1);
                if (numUsers > 0) {
                    hasRegistredUsers = true;
                }
            }

            statement.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hasRegistredUsers;
    }
    public LinkedList<Person> queryUserByNameGUI(String name) {
        LinkedList<Person> users = new LinkedList<>();
        try {
            Connection conn = getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM users WHERE LOWER(name) = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name.toLowerCase().trim());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Person person = new Person(
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("address")
                );
                users.add(person);
            }
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void queryUserByName(String name) {
        try {
            Connection conn = getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM users WHERE name = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name.toLowerCase().trim());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email + ", Phone: " + phone + ", Address: " + address);
            }

            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listAllUsers() {
        try {
            Connection conn = getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM users ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email + ", Phone: " + phone + ", Address: " + address);
            }

            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Person> listAllUsersGUI() {
        LinkedList<Person> userList = new LinkedList<>();
        try {
            Connection conn = getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM users";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                Person user = new Person(name, email, address, phone);
                userList.add(user);
            }
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public void insertUser(String name, String email, String phone, String address, String password) {
        try {
            Connection conn = getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "INSERT INTO users (name, email, phone, address, password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, password);
            int row = preparedStatement.executeUpdate();
            if (row > 0) {
                System.out.println("A new user was inserted successfully.");
            }
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteUserByEmail(String email) {
        try {
            Connection conn = getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "DELETE FROM users WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected > 0 ? "User deleted." : "No user found.");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateUser(String email, String name, String phone, String address, String password) {
        try {
            Connection conn = getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "UPDATE users SET name = ?, phone = ?, address = ?, password = ? WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, phone);
            stmt.setString(3, address);
            stmt.setString(4, password);
            stmt.setString(5, email);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected > 0 ? "User updated." : "No user found.");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Person getUserByEmail(String email) {
        Person person = null;
        try {
            Connection conn = getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String password = rs.getString("password");
                person = new Person(name, email, phone, address); // Add more fields if needed
                person.setPassword(password);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }
    public Person getUserByName(String name) {
        Person person = null;
        try {
            Connection conn = getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM users WHERE name = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");

                person = new Person(name, email, phone, address);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }
}
