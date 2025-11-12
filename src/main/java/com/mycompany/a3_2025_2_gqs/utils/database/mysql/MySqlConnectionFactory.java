package com.mycompany.a3_2025_2_gqs.backend.utils.database.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class MySqlConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/dbtooltracker";
    private static final String USER = "root";
    private static final String PASSWORD = "12345";

    private MySqlConnectionFactory() {
        // Utility class
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

