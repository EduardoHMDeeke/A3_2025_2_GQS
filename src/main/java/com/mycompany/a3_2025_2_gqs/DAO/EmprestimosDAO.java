package com.mycompany.a3_2025_2_gqs.DAO;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mycompany.a3_2025_2_gqs.Model.Emprestimos;
import java.time.LocalDateTime;

/**
 * DAO para operações com a tabela emprestimos.
 */
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
            // Suporte para: java.time.LocalDateTime, java.util.Date, java.sql.Timestamp, java.time.LocalDate
            Object dtEmp = emprestimos.getDataEmprestimo();
            if (dtEmp instanceof LocalDateTime) {
                pstmt.setTimestamp(3, Timestamp.valueOf((LocalDateTime) dtEmp));
            } else if (dtEmp instanceof java.time.LocalDate) {
                pstmt.setDate(3, java.sql.Date.valueOf((java.time.LocalDate) dtEmp));
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
            } else if (dtDev instanceof java.time.LocalDate) {
                pstmt.setDate(4, java.sql.Date.valueOf((java.time.LocalDate) dtDev));
            } else if (dtDev instanceof java.util.Date) {
                pstmt.setTimestamp(4, new Timestamp(((java.util.Date) dtDev).getTime()));
            } else if (dtDev instanceof Timestamp) {
                pstmt.setTimestamp(4, (Timestamp) dtDev);
            } else {
                pstmt.setNull(4, Types.TIMESTAMP);
            }

            // Se o modelo já tiver o campo estaEmprestada configurado, usa-o; senão mantém 1 por padrão.
            int estado = (emprestimos.getEstaEmprestada() == 0) ? 0 : 1;
            pstmt.setInt(5, estado);

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            // Propaga a exceção ou trate conforme sua lógica (aqui mostramos uma mensagem)
            JOptionPane.showMessageDialog(null, "Erro ao inserir emprestimo: " + ex.getMessage());
            throw ex;
        }
        // NOTA: não fechamos 'connection' aqui — quem criou a conexão deve geri-la.
    }

    public ArrayList<Emprestimos> listarEmprestimos() throws SQLException {

        // Evitar SELECT * para atender regra do Sonar e melhorar estabilidade (colunas explícitas)
        String sql = "SELECT id, idAmigo, idFerramenta, dataEmprestimo, dataDevolucao, dataDevolvida, estaEmprestada FROM emprestimos";
        ArrayList<Emprestimos> lista = new ArrayList<>();
        // usar try-with-resources para Statement e ResultSet
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Date data = null;
                Date data1 = null;
                Date data2 = null;
                try {
                    data = rs.getDate("dataEmprestimo");
                } catch (SQLException ignore) {
                    // caso a coluna não exista no esquema, manterá null
                }
                try {
                    data1 = rs.getDate("dataDevolucao");
                } catch (SQLException ignore) {
                }
                try {
                    data2 = rs.getDate("dataDevolvida");
                } catch (SQLException ignore) {
                    // coluna pode não existir em alguns esquemas; ignora
                }

                Emprestimos emprestimos = new Emprestimos();

                // tratar nulos antes de chamar o conversor (converterData espera não-nulo)
                if (data1 != null) {
                    emprestimos.setDataDevolucao(com.mycompany.a3_2025_2_gqs.Util.Util.converterData(data1));
                } else {
                    emprestimos.setDataDevolucao(null);
                }

                if (data != null) {
                    emprestimos.setDataEmprestimo(com.mycompany.a3_2025_2_gqs.Util.Util.converterData(data));
                } else {
                    emprestimos.setDataEmprestimo(null);
                }

                // se desejar tratar data2, faça aqui (comentado para preservar compatibilidade)
                // if (data2 != null) { ... }

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
        // Evitar SELECT * e listar colunas explicitamente
        String sql = "SELECT id, idAmigo, idFerramenta, dataEmprestimo, dataDevolucao, dataDevolvida, estaEmprestada FROM emprestimos WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    emprestimos.setId(rs.getInt("id"));
                    emprestimos.setIdFerramentas(rs.getInt("idFerramenta"));
                    emprestimos.setIdAmigos(rs.getInt("idAmigo"));
                    emprestimos.setEstaEmprestada(rs.getInt("estaEmprestada"));
                    Date data = null;
                    Date data1 = null;
                    try {
                        data = rs.getDate("dataEmprestimo");
                    } catch (SQLException ignore) {
                    }
                    try {
                        data1 = rs.getDate("dataDevolucao");
                    } catch (SQLException ignore) {
                    }

                    if (data != null) {
                        emprestimos.setDataEmprestimo(com.mycompany.a3_2025_2_gqs.Util.Util.converterData(data));
                    } else {
                        emprestimos.setDataEmprestimo(null);
                    }
                    if (data1 != null) {
                        emprestimos.setDataDevolucao(com.mycompany.a3_2025_2_gqs.Util.Util.converterData(data1));
                    } else {
                        emprestimos.setDataDevolucao(null);
                    }
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
