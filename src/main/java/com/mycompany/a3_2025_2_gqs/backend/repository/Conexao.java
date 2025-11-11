package com.mycompany.a3_2025_2_gqs.backend.repository;
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
            
    conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/dbtooltracker",
        "ghff13",
        "LJL!(D`ooACL<3P>6?^9u:nPl\\lgc'@jGUJo0cDLfM\\c--\\,V.2Nkb;KaK3*X^pm"
    );

        } catch (SQLException ex) {
            System.out.println("Erro ao conectar ao banco de dados: " + ex.getMessage());

        }

        return conn;
    }
}
