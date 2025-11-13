package com.mycompany.a3_2025_2_gqs.backend.repository;

import org.junit.jupiter.api.*;

import com.mycompany.a3_2025_2_gqs.backend.model.Amigos;
import com.mycompany.a3_2025_2_gqs.backend.repository.AmigosDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes de integração para AmigosDAO usando SQLite in-memory.
 *
 * @author Eduardo Deeke
 */
public class AmigosDAOTest {

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
        String sql = "CREATE TABLE IF NOT EXISTS amigos ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nome TEXT NOT NULL, "
                + "idade INTEGER, "
                + "telefone TEXT, "
                + "email TEXT"
                + ")";
        try (Statement st = c.createStatement()) {
            st.execute(sql);
        }
    }

    private static void clearData(Connection c) throws SQLException {
        try (Statement st = c.createStatement()) {
            st.executeUpdate("DELETE FROM amigos");
        }
    }

    private Amigos novoAmigo(String nome, String email, String telefone, int idade) {
        return new Amigos(nome, email, telefone, idade);
    }

    @Test
    void insertBD_e_listarAmigos_devePersistirEListar() throws Exception {
        try (Connection c1 = newConnection()) {
            AmigosDAO dao = new AmigosDAO(c1);
            Amigos amigo = novoAmigo("Alice Smith", "alice@example.com", "1199887766", 25);
            dao.insertBD(amigo);
        }

        ArrayList<Amigos> lista;
        try (Connection c2 = newConnection()) {
            AmigosDAO dao2 = new AmigosDAO(c2);
            lista = dao2.listarAmigos();
        }

        assertEquals(1, lista.size(), "Deve haver 1 registro salvo");
        Amigos salvo = lista.get(0);
        assertEquals("Alice Smith", salvo.getNome());
        assertEquals("alice@example.com", salvo.getEmail());
        assertEquals("1199887766", salvo.getTelefone());
        assertEquals(25, salvo.getIdade());
        assertTrue(salvo.getId() > 0);
    }

    @Test
    void buscarAmigo_deveRetornarPorId() throws Exception {
        int idGerado;

        try (Connection c1 = newConnection()) {
            AmigosDAO dao = new AmigosDAO(c1);
            Amigos amigo = novoAmigo("Bob Johnson", "bob@example.com", "1199776655", 30);
            dao.insertBD(amigo);
        }

        try (Connection c2 = newConnection()) {
            AmigosDAO dao2 = new AmigosDAO(c2);
            ArrayList<Amigos> lista = dao2.listarAmigos();
            assertFalse(lista.isEmpty(), "A lista não deve estar vazia");
            idGerado = lista.get(0).getId();
        }

        try (Connection c3 = newConnection()) {
            AmigosDAO dao3 = new AmigosDAO(c3);
            Amigos encontrado = dao3.buscarAmigo(idGerado);

            assertEquals("Bob Johnson", encontrado.getNome());
            assertEquals("bob@example.com", encontrado.getEmail());
            assertEquals("1199776655", encontrado.getTelefone());
            assertEquals(30, encontrado.getIdade());
            assertEquals(idGerado, encontrado.getId());
        }
    }

    @Test
    void UpdateAmigos_deveAtualizarDados() throws Exception {
        int idGerado;

        try (Connection c1 = newConnection()) {
            AmigosDAO dao = new AmigosDAO(c1);
            Amigos amigo = novoAmigo("Carlos Silva", "carlos@example.com", "1199665544", 35);
            dao.insertBD(amigo);
        }

        try (Connection c2 = newConnection()) {
            AmigosDAO dao2 = new AmigosDAO(c2);
            ArrayList<Amigos> lista = dao2.listarAmigos();
            assertFalse(lista.isEmpty(), "Deve existir pelo menos um registro");
            idGerado = lista.get(0).getId();
        }

        try (Connection c3 = newConnection()) {
            AmigosDAO dao3 = new AmigosDAO(c3);
            Amigos atualizado = novoAmigo("Carlos Silva Junior", "carlos.junior@example.com", "1199554433", 36);
            dao3.UpdateAmigos(atualizado, idGerado);
        }

        try (Connection c4 = newConnection()) {
            AmigosDAO dao4 = new AmigosDAO(c4);
            Amigos amigo = dao4.buscarAmigo(idGerado);
            assertEquals("Carlos Silva Junior", amigo.getNome());
            assertEquals("carlos.junior@example.com", amigo.getEmail());
            assertEquals("1199554433", amigo.getTelefone());
        }
    }

    @Test
    void deleteAmigos_deveExcluirRegistro() throws Exception {
        int id;

        try (Connection c = newConnection()) {
            AmigosDAO dao = new AmigosDAO(c);
            Amigos amigo = novoAmigo("Diana Costa", "diana@example.com", "1199443322", 28);
            dao.insertBD(amigo);
        }

        try (Connection c = newConnection()) {
            AmigosDAO dao = new AmigosDAO(c);
            ArrayList<Amigos> lista = dao.listarAmigos();
            assertFalse(lista.isEmpty());
            id = lista.get(0).getId();
        }

        try (Connection c = newConnection()) {
            AmigosDAO dao = new AmigosDAO(c);
            dao.deleteAmigos(id);
        }

        try (Connection c = newConnection()) {
            AmigosDAO dao = new AmigosDAO(c);
            assertTrue(dao.listarAmigos().isEmpty());
        }
    }

    @Test
    void insertBD_deveProtegerContraSQLInjection() throws Exception {
        String payload = "'; DROP TABLE amigos; --";
        
        try (Connection c1 = newConnection()) {
            AmigosDAO dao = new AmigosDAO(c1);
            Amigos amigo = novoAmigo(payload, "test@example.com", "1199332211", 25);
            dao.insertBD(amigo);
        }

        try (Connection c2 = newConnection()) {
            AmigosDAO dao2 = new AmigosDAO(c2);
            ArrayList<Amigos> lista = dao2.listarAmigos();
            assertEquals(1, lista.size());
            assertEquals(payload, lista.get(0).getNome());
        }
    }

    @Test
    void listarAmigos_deveRetornarListaVaziaQuandoNaoHaRegistros() throws Exception {
        try (Connection c = newConnection()) {
            AmigosDAO dao = new AmigosDAO(c);
            ArrayList<Amigos> lista = dao.listarAmigos();
            assertTrue(lista.isEmpty(), "Lista deve estar vazia quando não há registros");
        }
    }

    @Test
    void insertBD_comIdadeZero_deveFuncionar() throws Exception {
        try (Connection c1 = newConnection()) {
            AmigosDAO dao = new AmigosDAO(c1);
            Amigos amigo = novoAmigo("Eva Santos", "eva@example.com", "1199221100", 0);
            dao.insertBD(amigo);
        }

        try (Connection c2 = newConnection()) {
            AmigosDAO dao2 = new AmigosDAO(c2);
            ArrayList<Amigos> lista = dao2.listarAmigos();
            assertEquals(1, lista.size());
            assertEquals(0, lista.get(0).getIdade());
        }
    }
}
