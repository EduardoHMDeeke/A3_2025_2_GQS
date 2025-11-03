package com.mycompany.a3_2025_2_gqs.DAO;


import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import com.mycompany.a3_2025_2_gqs.Model.*;

public class FerramentaDAO {

    //executar a conexao
    private final Connection connection;
    PreparedStatement ps;
    ResultSet rs;
    ArrayList<Ferramentas> lista = new ArrayList<>();

    public FerramentaDAO(Connection conection) {
        this.connection = conection;
    }

    //Inserir Ferramenta no Banco de dados (Create) 
    public void insertBD(Ferramentas ferramenta) throws SQLException {
        String sql = "INSERT INTO ferramentas (nome, marca, preco, estaEmprestada) VALUES ('" + ferramenta.getNome() + "', '" + ferramenta.getMarca() + "', '" + ferramenta.getPreco() + "', '" + ferramenta.getEstaEmprestada() + "') ";
        ps = connection.prepareStatement(sql);
        ps.execute();
        connection.close();
    }

    //Atualizar Ferramenta Banco de dados (Upadate)
    public void UpdateFerramenta(Ferramentas ferramenta, int id) {
        String sql = "UPDATE ferramentas SET nome = ?, marca = ?, preco = ? " + "WHERE id = ?";
        try {
            ps = connection.prepareStatement(sql);

            //add valores que deseja atualizar
            ps.setString(1, ferramenta.getNome());
            ps.setString(2, ferramenta.getMarca());
            ps.setString(3, ferramenta.getPreco());

            //Qual o id do registro
            ps.setInt(4, id);

            //executar a query
            ps.execute();

            //fechar conexao
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FerramentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Add uma Ferramenta dentro de uma lista 
    public ArrayList<Ferramentas> listarFerramentas() throws SQLException {

        String sql = "SELECT * FROM ferramentas";

        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Ferramentas ferramenta = new Ferramentas();
                ferramenta.setNome(rs.getString("nome"));
                ferramenta.setMarca(rs.getString("marca"));
                ferramenta.setValor(rs.getString("preco"));
                ferramenta.setId(rs.getInt("id"));
                ferramenta.setEstaEmprestada(rs.getInt("estaEmprestada"));
                lista.add(ferramenta);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "FerramentaDAO ListarFeerramentas()" + erro);

        }
        connection.close();
        return lista;

    }

    public ArrayList<Ferramentas> listarFerramentasNaoEmprestadas() throws SQLException {

        String sql = "SELECT * FROM ferramentas WHERE estaEmprestada = 1";

        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Ferramentas ferramenta = new Ferramentas();
                ferramenta.setNome(rs.getString("nome"));
                ferramenta.setMarca(rs.getString("marca"));
                ferramenta.setValor(rs.getString("preco"));
                ferramenta.setId(rs.getInt("id"));
                ferramenta.setEstaEmprestada(rs.getInt("estaEmprestada"));
                lista.add(ferramenta);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "FerramentaDAO ListarFeerramentas()" + erro);

        }
        connection.close();
        return lista;

    }

    //Deleta uma ferramenta
    public void deleteFerramentas(int id) {
        String sql = "DELETE FROM ferramentas WHERE id = ?";
        try {
            ps = connection.prepareStatement(sql);
            //Qual o id do registro
            ps.setInt(1, id);

            //executar a query
            ps.execute();
            //fehcar conexao
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FerramentaDAO.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public Ferramentas buscarFerramenta(int id) throws SQLException {
        Ferramentas ferramentas = new Ferramentas();
        String sql = "SELECT * FROM ferramentas WHERE id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            ferramentas.setNome(rs.getString("nome"));

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "FerramentaDAO buscarFerramentas" + erro);

        }
        connection.close();
        return ferramentas;
    }

    public void updateStatus(int estaEmprestada, int id) {
        String sql = "UPDATE ferramentas SET estaEmprestada = ? " + "WHERE id = ?";
        try {
            ps = connection.prepareStatement(sql);

            //add valores que deseja atualizar
            ps.setInt(1, estaEmprestada);

            //Qual o id do registro
            ps.setInt(2, id);

            //executar a query
            ps.execute();

            //fechar conexao
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FerramentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
} //Fim classe
