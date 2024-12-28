package com.pwr.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBase {
    private String dataBaseURL;
    private String dataBaseUserName;
    private String dataBasePassword;
    private Connection connection;
    public DataBase() {
        try {
            Properties properties = new Properties();
            FileInputStream input = new FileInputStream("database/db-config.properties");
            properties.load(input); // reads data from database config file
            dataBaseURL = properties.getProperty("db.url");
            dataBaseUserName = properties.getProperty("db.username");
            dataBasePassword = properties.getProperty("db.password");

            connection = DriverManager.getConnection(dataBaseURL,dataBaseUserName,dataBasePassword);
        } catch (IOException e) {
            System.out.println("Problem with reading config file: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Problem with connection to DB: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
