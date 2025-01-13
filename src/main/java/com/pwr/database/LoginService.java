package com.pwr.database;

import com.pwr.database.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginService {
    public static User login(Connection connection){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String userName = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1,userName);
            statement.setString(2,password);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String role = resultSet.getString("role");
                return new User(resultSet.getInt("user_id"),userName,role);
            }
            else {
                throw new IllegalArgumentException("Invalid username or password");
            }
        } catch (SQLException e) {
            System.out.println("Error with connection to db: " + e.getMessage());
            return null;
        }
    }


}
