package com.mycompany.a3_2025_2_gqs.backend.repository;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import com.mycompany.a3_2025_2_gqs.backend.model.Amigos;

/**
 *
 * @author Eduardo Deeke
 */
public class AmigosDAO {

    //executar a conexao
    private final Connection connection;
    PreparedStatement ps;
    ResultSet rs;
    ArrayList<Amigos> lista = new ArrayList<>();

    public AmigosDAO(Connection conection) {
        this.connection = conection;
    }

    //Inserir amigos no banco
    public void insertBD(Amigos amigos) throws SQLException {
        String sql = "INSERT INTO amigos (nome,idade,telefone,email) VALUES (?, ?, ?, ?);";
        ps = connection.prepareStatement(sql);

        ps.setString(1, amigos.getNome());
        ps.setInt(2, amigos.getIdade()); // Using setInt for idade
        ps.setString(3, amigos.getTelefone());
        ps.setString(4, amigos.getEmail());

        ps.execute();
        connection.close();
    }

    public void UpdateAmigos(Amigos amigos, int id) {
        String sql = "UPDATE amigos SET nome = ?, email = ?, telefone = ? " + "WHERE id = ?";
        try {
            ps = connection.prepareStatement(sql);

            //add valores que deseja atualizar
            ps.setString(1, amigos.getNome());
            ps.setString(2, amigos.getEmail());
            ps.setString(3, amigos.getTelefone());

            //Qual o id do registro
            ps.setInt(4, id);

            //executar a query
            ps.execute();
            //fehcar conexao
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AmigosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Add amigos dentro de uma lista 
    public ArrayList<Amigos> listarAmigos() throws SQLException {

        String sql = "SELECT id, nome, idade, telefone, email FROM amigos";
        ArrayList<Amigos> listaLocal = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Amigos amigos = new Amigos();
                amigos.setId(rs.getInt("id"));
                amigos.setNome(rs.getString("nome"));
                amigos.setIdade(rs.getInt("idade"));
                amigos.setTelefone(rs.getString("telefone"));
                amigos.setEmail(rs.getString("email"));

                listaLocal.add(amigos);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "AmigosDAO ListarAmigos()" + erro);

        }
        connection.close();
        return listaLocal;

    }

    public void deleteAmigos(int id) {
        String sql = "DELETE FROM amigos WHERE id = ?";
        try {
            ps = connection.prepareStatement(sql);
            //Qual o id do registro
            ps.setInt(1, id);

            //executar a query
            ps.execute();
            //fehcar conexao
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AmigosDAO.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public Amigos buscarAmigo(int id) throws SQLException {
        Amigos amigos = new Amigos();
        String sql = "SELECT id, nome, idade, telefone, email FROM amigos WHERE id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                amigos.setId(rs.getInt("id"));
                amigos.setNome(rs.getString("nome"));
                amigos.setIdade(rs.getInt("idade"));
                amigos.setTelefone(rs.getString("telefone"));
                amigos.setEmail(rs.getString("email"));
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "AmigosDAO BuscarAmigos()" + erro);

        }
        connection.close();
        return amigos;
    }

}
