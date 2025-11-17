package com.mycompany.a3_2025_2_gqs.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.mycompany.a3_2025_2_gqs.Model.Emprestimos;
import com.mycompany.a3_2025_2_gqs.Util.Util;

public class EmprestimosDAO {

    private final Connection connection;
    ArrayList<Emprestimos> listaEmp = new ArrayList<>();

    public EmprestimosDAO(Connection connection) {
        this.connection = connection;
    }
    
    private LocalDate getAndConvertDate(ResultSet rs, String columnName) {
        try {
            Date sqlDate = rs.getDate(columnName);
            return (sqlDate != null) ? (LocalDate) Util.converterData(sqlDate) : null;
        } catch (SQLException e) {
            Logger.getLogger(EmprestimosDAO.class.getName()).log(Level.WARNING, "Coluna de data não encontrada ou erro de leitura: " + columnName, e);
            return null;
        }
    }

    public void insertBD(Emprestimos emprestimos) throws SQLException {
        String sql = "INSERT INTO emprestimos (idAmigo, idFerramenta, dataEmprestimo, dataDevolucao, estaEmprestada) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, emprestimos.getIdAmigos());
            pstmt.setInt(2, emprestimos.getIdFerramentas());

            Object dtEmp = emprestimos.getDataEmprestimo();
            if (dtEmp instanceof LocalDateTime localDateTime) {
                pstmt.setTimestamp(3, Timestamp.valueOf((LocalDateTime) dtEmp));
            } else if (dtEmp instanceof java.time.LocalDate localDate) {
                pstmt.setDate(3, java.sql.Date.valueOf((java.time.LocalDate) dtEmp));
            } else if (dtEmp instanceof java.util.Date date) {
                pstmt.setTimestamp(3, new Timestamp(((java.util.Date) dtEmp).getTime()));
            } else if (dtEmp instanceof Timestamp timestamp) {
                pstmt.setTimestamp(3, (Timestamp) dtEmp);
            } else {
                pstmt.setNull(3, Types.TIMESTAMP);
            }

            Object dtDev = emprestimos.getDataDevolucao();
            if (dtDev instanceof LocalDateTime) {
                pstmt.setTimestamp(4, Timestamp.valueOf((LocalDateTime) dtDev));
            } else if (dtDev instanceof java.time.LocalDate) {
                pstmt.setDate(4, java.sql.Date.valueOf((java.time.LocalDate) dtDev));
            } else if (dtDev instanceof java.util.Date) {
                pstmt.setTimestamp(4, new Timestamp(((java.util.Date) dtDev).getTime()));
            } else if (dtDev instanceof Timestamp) {
                pstmt.setTimestamp(4, (Timestamp) dtDev);
            } else {
                pstmt.setNull(4, Types.TIMESTAMP);
            }

            int estado = (emprestimos.getEstaEmprestada() == 0) ? 0 : 1;
            pstmt.setInt(5, estado);

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir emprestimo: " + ex.getMessage());
            throw ex;
        }
    }

    public ArrayList<Emprestimos> listarEmprestimos() throws SQLException {
        String sql = "SELECT id, idAmigo, idFerramenta, dataEmprestimo, dataDevolucao, dataDevolvida, estaEmprestada FROM emprestimos";
        ArrayList<Emprestimos> lista = new ArrayList<>();
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Emprestimos emprestimos = new Emprestimos();

                emprestimos.setDataEmprestimo(getAndConvertDate(rs, "dataEmprestimo"));
                emprestimos.setDataDevolucao(getAndConvertDate(rs, "dataDevolucao"));
                
                emprestimos.setId(rs.getInt("id"));
                emprestimos.setIdAmigos(rs.getInt("idAmigo"));
                emprestimos.setIdFerramentas(rs.getInt("idFerramenta"));
                emprestimos.setEstaEmprestada(rs.getInt("estaEmprestada"));

                lista.add(emprestimos);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "EmprestimosDAO listarEmprestimos() - " + erro.getMessage());
            throw erro;
        }
        return lista;
    }

    public void updateEmprestimos(int estaEmprestada, int id) {
        String sql = "UPDATE emprestimos SET estaEmprestada = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, estaEmprestada);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro em update emprestimos: " + ex.getMessage());
        }
    }

    public void updateEmprestimos(int estaEmprestada, java.util.Date dataDevolvida, int id) {
        String sql = "UPDATE emprestimos SET estaEmprestada = ?, dataDevolvida = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, estaEmprestada);
            if (dataDevolvida != null) {
                pstmt.setTimestamp(2, new Timestamp(dataDevolvida.getTime()));
            } else {
                pstmt.setNull(2, Types.TIMESTAMP);
            }
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro em update emprestimos com data: " + ex.getMessage());
        }
    }

    public Emprestimos buscarEmprestimo(int id) throws SQLException {
        Emprestimos emprestimos = new Emprestimos();
        String sql = "SELECT id, idAmigo, idFerramenta, dataEmprestimo, dataDevolucao, dataDevolvida, estaEmprestada FROM emprestimos WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    emprestimos.setId(rs.getInt("id"));
                    emprestimos.setIdFerramentas(rs.getInt("idFerramenta"));
                    emprestimos.setIdAmigos(rs.getInt("idAmigo"));
                    emprestimos.setEstaEmprestada(rs.getInt("estaEmprestada"));
                    
                    emprestimos.setDataEmprestimo(getAndConvertDate(rs, "dataEmprestimo"));
                    emprestimos.setDataDevolucao(getAndConvertDate(rs, "dataDevolucao"));
                }
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "EmprestimoDAO buscarEmprestimo() - " + erro.getMessage());
            throw erro;
        }
        return emprestimos;
    }
}
