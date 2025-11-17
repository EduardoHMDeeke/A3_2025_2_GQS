package com.mycompany.a3_2025_2_gqs.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {

    private static final Logger LOGGER = Logger.getLogger(Conexao.class.getName());

    private static final String URL = "jdbc:mysql://localhost:3306/dbtooltracker";
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Erro ao conectar ao banco de dados", ex);
        }
        return conn;
    }
}
