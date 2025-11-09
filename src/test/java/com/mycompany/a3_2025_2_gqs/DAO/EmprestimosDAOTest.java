package com.mycompany.a3_2025_2_gqs.DAO;

import com.mycompany.a3_2025_2_gqs.Model.Emprestimos;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes de integração para EmprestimosDAO usando SQLite in-memory.
 * Até Commit C (inclui teste de insert/listar e buscar por id)
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
        String url = "jdbc:sqlite:file:memdb?mode=memory&cache=shared";
        return DriverManager.getConnection(url);
    }

    private static void createSchema(Connection c) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS emprestimos ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "idAmigo INTEGER, "
                + "idFerramenta INTEGER, "
                + "dataEmprestimo DATE, "
                + "dataDevolucao DATE, "
                + "dataDevolvida DATE, "
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

    // helper para criar um objeto Emprestimos (ajuste se sua model for diferente)
    private Emprestimos novoEmprestimo(int idAmigo, int idFerramenta, LocalDate dtEmp, LocalDate dtDev) {
        // usa o construtor que você informou existir: (int,int,LocalDate,LocalDate,int)
        return new Emprestimos(idAmigo, idFerramenta, dtEmp, dtDev, 1);
    }

    @Test
    void insertBD_e_listarEmprestimos_devePersistirEListar() throws Exception {
        // Inserir
        try (Connection c1 = newConnection()) {
            EmprestimosDAO dao = new EmprestimosDAO(c1);
            LocalDate hoje = LocalDate.now();
            LocalDate seteDias = hoje.plusDays(7);
            Emprestimos e = novoEmprestimo(10, 20, hoje, seteDias);
            dao.insertBD(e);
        }

        // Ler em nova conexão
        ArrayList<Emprestimos> lista;
        try (Connection c2 = newConnection()) {
            EmprestimosDAO dao2 = new EmprestimosDAO(c2);
            lista = dao2.listarEmprestimos();
        }

        assertEquals(1, lista.size(), "Deve haver 1 registro salvo");
        Emprestimos salvo = lista.get(0);
        assertEquals(10, salvo.getIdAmigos());
        assertEquals(20, salvo.getIdFerramentas());
        assertEquals(1, salvo.getEstaEmprestada());
    }

    @Test
    void buscarEmprestimo_deveRetornarPorId() throws Exception {
        int idGerado;

        // Inserir um empréstimo
        try (Connection c1 = newConnection()) {
            EmprestimosDAO dao = new EmprestimosDAO(c1);
            LocalDate hoje = LocalDate.now();
            LocalDate seteDias = hoje.plusDays(7);
            Emprestimos e = novoEmprestimo(1, 2, hoje, seteDias);
            dao.insertBD(e);
        }

        // Buscar o id gerado
        try (Connection c2 = newConnection()) {
            EmprestimosDAO dao2 = new EmprestimosDAO(c2);
            ArrayList<Emprestimos> lista = dao2.listarEmprestimos();
            assertFalse(lista.isEmpty(), "A lista não deve estar vazia");
            idGerado = lista.get(0).getId();
        }

        // Verificar se o buscarEmprestimo() retorna os dados corretos
        try (Connection c3 = newConnection()) {
            EmprestimosDAO dao3 = new EmprestimosDAO(c3);
            Emprestimos encontrado = dao3.buscarEmprestimo(idGerado);

            assertEquals(1, encontrado.getIdAmigos());
            assertEquals(2, encontrado.getIdFerramentas());
            assertEquals(1, encontrado.getEstaEmprestada());
        }
    }
}
