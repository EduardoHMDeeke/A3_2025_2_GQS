package com.mycompany.a3_2025_2_gqs.DAO;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mycompany.a3_2025_2_gqs.Model.Emprestimos;
import java.time.LocalDateTime;

public class EmprestimosDAO {

    private final Connection connection;
    PreparedStatement ps;
    ResultSet rs;
    ArrayList<Emprestimos> listaEmp = new ArrayList<>();

    public EmprestimosDAO(Connection connection) {
        this.connection = connection;
    }

    // Inserir emprestimo no banco (usando PreparedStatement parametrizado para evitar SQL injection)
    public void insertBD(Emprestimos emprestimos) throws SQLException {

        String sql = "INSERT INTO emprestimos (idAmigo, idFerramenta, dataEmprestimo, dataDevolucao, estaEmprestada) VALUES (?, ?, ?, ?, ?)";

        // Usar try-with-resources para garantir fechamento do PreparedStatement
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, emprestimos.getIdAmigos());
            pstmt.setInt(2, emprestimos.getIdFerramentas());

            // Converte possíveis tipos de data para java.sql.Timestamp
            // Suporte para: java.time.LocalDateTime, java.util.Date, java.sql.Timestamp
            Object dtEmp = emprestimos.getDataEmprestimo();
            if (dtEmp instanceof LocalDateTime) {
                pstmt.setTimestamp(3, Timestamp.valueOf((LocalDateTime) dtEmp));
            } else if (dtEmp instanceof java.util.Date) {
                pstmt.setTimestamp(3, new Timestamp(((java.util.Date) dtEmp).getTime()));
            } else if (dtEmp instanceof Timestamp) {
                pstmt.setTimestamp(3, (Timestamp) dtEmp);
            } else {
                // Se não souber o formato, seta NULL para evitar inserção de strings concatenadas
                pstmt.setNull(3, Types.TIMESTAMP);
            }

            Object dtDev = emprestimos.getDataDevolucao();
            if (dtDev instanceof LocalDateTime) {
                pstmt.setTimestamp(4, Timestamp.valueOf((LocalDateTime) dtDev));
            } else if (dtDev instanceof java.util.Date) {
                pstmt.setTimestamp(4, new Timestamp(((java.util.Date) dtDev).getTime()));
            } else if (dtDev instanceof Timestamp) {
                pstmt.setTimestamp(4, (Timestamp) dtDev);
            } else {
                pstmt.setNull(4, Types.TIMESTAMP);
            }

            pstmt.setInt(5, 1); // estaEmprestada padrão = 1

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            // Propaga a exceção ou trate conforme sua lógica (aqui mostramos uma mensagem)
            JOptionPane.showMessageDialog(null, "Erro ao inserir emprestimo: " + ex.getMessage());
            throw ex;
        }
        // NOTA: não fechamos 'connection' aqui — quem criou a conexão deve geri-la.
    }

    public ArrayList<Emprestimos> listarEmprestimos() throws SQLException {

        String sql = "SELECT * FROM emprestimos";
        ArrayList<Emprestimos> lista = new ArrayList<>();
        // usar try-with-resources para Statement e ResultSet
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Date data = rs.getDate("dataEmprestimo");
                Date data1 = rs.getDate("dataDevolucao");
                Date data2 = rs.getDate("dataDevolvida");

                Emprestimos emprestimos = new Emprestimos();
                emprestimos.setDataDevolucao(com.mycompany.a3_2025_2_gqs.Util.Util.converterData(data1));
                emprestimos.setDataEmprestimo(com.mycompany.a3_2025_2_gqs.Util.Util.converterData(data));
                // emprestimos.setDataDevolvida(Util.Util.converterData(data2));
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
        // NOTA: não fechamos 'connection' aqui — quem criou a conexão deve geri-la.
        return lista;

    }
    // Método preservando assinatura original: atualiza apenas o flag estaEmprestada
    public void updateEmprestimos(int estaEmprestada, int id) {
        String sql = "UPDATE emprestimos SET estaEmprestada = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, estaEmprestada);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro em update emprestimos: " + ex.getMessage());
        }
        // NOTA: não fechamos 'connection' aqui — quem criou a conexão deve geri-la.
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
        String sql = "SELECT * FROM emprestimos WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    emprestimos.setId(rs.getInt("id"));
                    emprestimos.setIdFerramentas(rs.getInt("idFerramenta"));
                    emprestimos.setIdAmigos(rs.getInt("idAmigo"));
                    emprestimos.setEstaEmprestada(rs.getInt("estaEmprestada"));
                    Date data = rs.getDate("dataEmprestimo");
                    Date data1 = rs.getDate("dataDevolucao");
                    emprestimos.setDataEmprestimo(com.mycompany.a3_2025_2_gqs.Util.Util.converterData(data));
                    emprestimos.setDataDevolucao(com.mycompany.a3_2025_2_gqs.Util.Util.converterData(data1));
                }
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "EmprestimoDAO buscarEmprestimo() - " + erro.getMessage());
            throw erro;
        }
        // NOTA: não fechamos 'connection' aqui — quem criou a conexão deve geri-la.
        return emprestimos;
    }
}
