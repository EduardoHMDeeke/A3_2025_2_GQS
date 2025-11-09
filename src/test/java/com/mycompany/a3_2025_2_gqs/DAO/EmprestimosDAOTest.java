package com.mycompany.a3_2025_2_gqs.DAO;

import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Infra para testes de EmprestimosDAO (SQLite in-memory).
 */
public class EmprestimosDAOTest {

    private Connection keeper;

    @BeforeEach
    void setUp() throws SQLException {
        keeper = newConnection();
        createSchema(keeper);
        clearData(keeper);
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (keeper != null && !keeper.isClosed()) {
            keeper.close();
        }
    }

    private static Connection newConnection() throws SQLException {
        // banco em memória compartilhado entre conexões na mesma JVM
        String url = "jdbc:sqlite:file:memdb?mode=memory&cache=shared";
        return DriverManager.getConnection(url);
    }

    /**
     * Cria a tabela emprestimos usada pelos testes.
     */
    private static void createSchema(Connection c) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS emprestimos ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "idAmigo INTEGER, "
                + "idFerramenta INTEGER, "
                + "dataEmprestimo TIMESTAMP, "
                + "dataDevolucao TIMESTAMP, "
                + "dataDevolvida TIMESTAMP, "
                + "estaEmprestada INTEGER"
                + ")";
        try (Statement st = c.createStatement()) {
            st.execute(sql);
        }
    }

    private static void clearData(Connection c) throws SQLException {
        try (Statement st = c.createStatement()) {
            st.executeUpdate("DELETE FROM emprestimos");
        }
    }
}

