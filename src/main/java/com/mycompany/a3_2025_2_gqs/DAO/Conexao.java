package com.mycompany.a3_2025_2_gqs.DAO;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.*;

/**
 *
 * @author Guilherme
 */
public class Conexao {

    public Connection getConnection() {
        Connection conn = null;
        try {
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtooltracker", "ghff13", "GOSTOSO4ever");
            System.out.println("Conexão bem-sucedida!");

        } catch (SQLException ex) {
            System.out.println("Erro ao conectar ao banco de dados: " + ex.getMessage());

        }

        return conn;
    }
}
