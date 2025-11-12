package com.mycompany.a3_2025_2_gqs.backend.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import com.mycompany.a3_2025_2_gqs.backend.model.Ferramentas;

public class FerramentaDAO {

    private static final String COL_NOME = "nome";
    private static final String COL_MARCA = "marca";
    private static final String COL_PRECO = "preco";
    private static final String COL_EMPRESTADA = "estaEmprestada";
    private static final String COL_ID = "id"; 

    private final Connection connection;
    ArrayList<Ferramentas> lista = new ArrayList<>();

    public FerramentaDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertBD(Ferramentas ferramenta) throws SQLException {
        String sql = "INSERT INTO ferramentas (nome, marca, preco, estaEmprestada) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) { 
            ps.setString(1, ferramenta.getNome());
            ps.setString(2, ferramenta.getMarca());
            ps.setString(3, ferramenta.getPreco()); 
            ps.setInt(4, ferramenta.getEstaEmprestada());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FerramentaDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } 
    }

    public void updateFerramenta(Ferramentas ferramenta, int id) {
        String sql = "UPDATE ferramentas SET nome = ?, marca = ?, preco = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, ferramenta.getNome());
            ps.setString(2, ferramenta.getMarca());
            ps.setString(3, ferramenta.getPreco());
            ps.setInt(4, id);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FerramentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Ferramentas> listarFerramentas() throws SQLException {
        String sql = "SELECT id, nome, marca, preco, estaEmprestada FROM ferramentas";
        lista.clear();
        try (PreparedStatement ps = connection.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Ferramentas ferramenta = new Ferramentas();
                ferramenta.setId(rs.getInt(COL_ID));
                ferramenta.setNome(rs.getString(COL_NOME));
                ferramenta.setMarca(rs.getString(COL_MARCA)); 
                ferramenta.setValor(rs.getString(COL_PRECO)); 
                ferramenta.setEstaEmprestada(rs.getInt(COL_EMPRESTADA)); 
                lista.add(ferramenta);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar ferramentas: " + erro);
        }
        return lista;
    }

    public ArrayList<Ferramentas> listarFerramentasNaoEmprestadas() throws SQLException {
        String sql = "SELECT id, nome, marca, preco, estaEmprestada FROM ferramentas WHERE estaEmprestada = 1";
        lista.clear();
        try (PreparedStatement ps = connection.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Ferramentas ferramenta = new Ferramentas();
                ferramenta.setId(rs.getInt(COL_ID));
                ferramenta.setNome(rs.getString(COL_NOME));
                ferramenta.setMarca(rs.getString(COL_MARCA)); 
                ferramenta.setValor(rs.getString(COL_PRECO)); 
                ferramenta.setEstaEmprestada(rs.getInt(COL_EMPRESTADA)); 
                lista.add(ferramenta);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar ferramentas nÃ£o emprestadas: " + erro);
        }
        return lista;
    }

    public Ferramentas buscarFerramenta(int id) throws SQLException {
        Ferramentas ferramenta = new Ferramentas();
        String sql = "SELECT id, nome, marca, preco, estaEmprestada FROM ferramentas WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) { 
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ferramenta.setId(rs.getInt(COL_ID));
                    ferramenta.setNome(rs.getString(COL_NOME));
                    ferramenta.setMarca(rs.getString(COL_MARCA)); 
                    ferramenta.setValor(rs.getString(COL_PRECO));
                    ferramenta.setEstaEmprestada(rs.getInt(COL_EMPRESTADA)); 
                }
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar ferramenta: " + erro);
        }
        return ferramenta;
    }

    public void deleteFerramentas(int id) {
        String sql = "DELETE FROM ferramentas WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FerramentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateStatus(int estaEmprestada, int id) {
        String sql = "UPDATE ferramentas SET estaEmprestada = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, estaEmprestada);
            ps.setInt(2, id);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FerramentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
