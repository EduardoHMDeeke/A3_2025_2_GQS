package com.mycompany.a3_2025_2_gqs.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/dbtooltracker";
    private static final String USER = "ghff13";
    private static final String PASSWORD = "NEW_SECURE_PASSWORD"; 

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar ao banco de dados: " + ex.getMessage());
        }
        return conn;
    }
}
