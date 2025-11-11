package com.mycompany.a3_2025_2_gqs.backend.utils.database.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class MySqlConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/dbtooltracker";
    private static final String USER = "ghff13";
    private static final String PASSWORD = "LJL!(D`ooACL<3P>6?^9u:nPl\\lgc'@jGUJo0cDLfM\\c--\\,V.2Nkb;KaK3*X^pm";

    private MySqlConnectionFactory() {
        // Utility class
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

