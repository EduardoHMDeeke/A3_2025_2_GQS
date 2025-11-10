package com.mycompany.a3_2025_2_gqs.DAO;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import com.mycompany.a3_2025_2_gqs.Model.Ferramentas;

public class FerramentaDAO {

    private final Connection connection;
    PreparedStatement ps;
    ResultSet rs;
    ArrayList<Ferramentas> lista = new ArrayList<>();

    public FerramentaDAO(Connection connection) {
        this.connection = connection;
    }

    // Inserir Ferramenta
    public void insertBD(Ferramentas ferramenta) throws SQLException {
        String sql = "INSERT INTO ferramentas (nome, marca, preco, estaEmprestada) VALUES (?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, ferramenta.getNome());
            ps.setString(2, ferramenta.getMarca());
            ps.setString(3, ferramenta.getPreco()); 
            ps.setInt(4, ferramenta.getEstaEmprestada());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FerramentaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            connection.close();
        }
    }

    // Atualizar Ferramenta
    public void updateFerramenta(Ferramentas ferramenta, int id) {
        String sql = "UPDATE ferramentas SET nome = ?, marca = ?, preco = ? WHERE id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, ferramenta.getNome());
            ps.setString(2, ferramenta.getMarca());
            ps.setString(3, ferramenta.getPreco());
            ps.setInt(4, id);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FerramentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                Logger.getLogger(FerramentaDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    // Listar todas as ferramentas
    public ArrayList<Ferramentas> listarFerramentas() throws SQLException {
        String sql = "SELECT id, nome, marca, preco, estaEmprestada FROM ferramentas";
        lista.clear();
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ferramentas ferramenta = new Ferramentas();
                ferramenta.setId(rs.getInt("id"));
                ferramenta.setNome(rs.getString("nome"));
                ferramenta.setMarca(rs.getString("marca"));
                ferramenta.setValor(rs.getString("preco"));
                ferramenta.setEstaEmprestada(rs.getInt("estaEmprestada"));
                lista.add(ferramenta);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar ferramentas: " + erro);
        } finally {
            connection.close();
        }
        return lista;
    }

    // Listar ferramentas não emprestadas
    public ArrayList<Ferramentas> listarFerramentasNaoEmprestadas() throws SQLException {
        String sql = "SELECT id, nome, marca, preco, estaEmprestada FROM ferramentas WHERE estaEmprestada = 1";
        lista.clear();
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ferramentas ferramenta = new Ferramentas();
                ferramenta.setId(rs.getInt("id"));
                ferramenta.setNome(rs.getString("nome"));
                ferramenta.setMarca(rs.getString("marca"));
                ferramenta.setValor(rs.getString("preco"));
                ferramenta.setEstaEmprestada(rs.getInt("estaEmprestada"));
                lista.add(ferramenta);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar ferramentas não emprestadas: " + erro);
        } finally {
            connection.close();
        }
        return lista;
    }

    // Buscar ferramenta específica
    public Ferramentas buscarFerramenta(int id) throws SQLException {
        Ferramentas ferramenta = new Ferramentas();
        String sql = "SELECT id, nome, marca, preco, estaEmprestada FROM ferramentas WHERE id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                ferramenta.setId(rs.getInt("id"));
                ferramenta.setNome(rs.getString("nome"));
                ferramenta.setMarca(rs.getString("marca"));
                ferramenta.setValor(rs.getString("preco"));
                ferramenta.setEstaEmprestada(rs.getInt("estaEmprestada"));
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar ferramenta: " + erro);
        } finally {
            connection.close();
        }
        return ferramenta;
    }

    // Deletar ferramenta
    public void deleteFerramentas(int id) {
        String sql = "DELETE FROM ferramentas WHERE id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FerramentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                Logger.getLogger(FerramentaDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    // Atualizar status
    public void updateStatus(int estaEmprestada, int id) {
        String sql = "UPDATE ferramentas SET estaEmprestada = ? WHERE id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, estaEmprestada);
            ps.setInt(2, id);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FerramentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                Logger.getLogger(FerramentaDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
