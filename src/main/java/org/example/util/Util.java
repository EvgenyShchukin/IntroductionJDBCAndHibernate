package org.example.util;

import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@UtilityClass
public class Util {
    private final String URL = "jdbc:mysql://localhost:3306/test";
    private final String USER_NAME = "root";
    private final String PASSWORD = "root";

    public Connection getJDBConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Соединение не установлено");
            throw new RuntimeException(e);
        }
        return connection;
    }
}
