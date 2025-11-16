package com.mycompany.a3_2025_2_gqs.DAO;

import com.mycompany.a3_2025_2_gqs.Model.Amigos;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
                + "nome TEXT, "
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
        } catch (SQLException e) {
            throw e;
        }
    }

    private Amigos novoAmigo(String nome, int idade, String telefone, String email) {
        Amigos amigo = new Amigos();
        amigo.setNome(nome);
        amigo.setIdade(idade);
        amigo.setTelefone(telefone);
        amigo.setEmail(email);
        return amigo;
    }

    @Test
    void insertBD_e_listarAmigos_devePersistirEListar() throws Exception {
        try (Connection c1 = newConnection()) {
            AmigosDAO dao = new AmigosDAO(c1);
            Amigos amigo = novoAmigo("Alice Smith", 25, "1199887766", "alice@example.com");
            dao.insertBD(amigo);
        }

        ArrayList<Amigos> lista;
        try (Connection c2 = newConnection()) {
            AmigosDAO dao2 = new AmigosDAO(c2);
            lista = dao2.listarAmigos();
        }

        assertEquals(1, lista.size());
        Amigos salvo = lista.get(0);
        assertEquals("Alice Smith", salvo.getNome());
        assertEquals(25, salvo.getIdade());
        assertEquals("1199887766", salvo.getTelefone());
        assertEquals("alice@example.com", salvo.getEmail());
        assertTrue(salvo.getId() > 0);
    }

    @Test
    void UpdateAmigos_deveAtualizarNomeEmailTelefone() throws Exception {
        int idGerado;

        // Insert initial data
        try (Connection c = newConnection()) {
            new AmigosDAO(c).insertBD(novoAmigo("David Wilson", 35, "1199554433", "david@example.com"));
        }

        // Get the generated ID
        try (Connection c = newConnection()) {
            ArrayList<Amigos> lista = new AmigosDAO(c).listarAmigos();
            assertFalse(lista.isEmpty());
            idGerado = lista.get(0).getId();
        }

        // Update the record
        try (Connection c = newConnection()) {
            Amigos atualizado = novoAmigo("David Wilson Atualizado", 36, "1199443322", "david.novo@example.com");
            new AmigosDAO(c).UpdateAmigos(atualizado, idGerado);
        }

        // Verify the update
        try (Connection c = newConnection()) {
            ArrayList<Amigos> lista = new AmigosDAO(c).listarAmigos();
            assertEquals(1, lista.size());
            Amigos amigoAtualizado = lista.get(0);
            
            assertEquals("David Wilson Atualizado", amigoAtualizado.getNome());
            assertEquals("david.novo@example.com", amigoAtualizado.getEmail());
            assertEquals("1199443322", amigoAtualizado.getTelefone());
            // Note: idade should remain unchanged since UpdateAmigos doesn't update it
            assertEquals(35, amigoAtualizado.getIdade());
            assertEquals(idGerado, amigoAtualizado.getId());
        }
    }

    @Test
    void deleteAmigos_deveExcluirRegistro() throws Exception {
        int id;

        // Insert data
        try (Connection c = newConnection()) {
            new AmigosDAO(c).insertBD(novoAmigo("Frank Miller", 45, "1199221100", "frank@example.com"));
        }

        // Get the ID
        try (Connection c = newConnection()) {
            ArrayList<Amigos> lista = new AmigosDAO(c).listarAmigos();
            assertFalse(lista.isEmpty());
            id = lista.get(0).getId();
        }

        // Delete the record
        try (Connection c = newConnection()) {
            new AmigosDAO(c).deleteAmigos(id);
        }

        // Verify deletion
        try (Connection c = newConnection()) {
            assertTrue(new AmigosDAO(c).listarAmigos().isEmpty());
        }
    }

    @Test
    void buscarAmigo_deveRetornarCorretoPeloId() throws Exception {
        int idBuscar;

        // Insert multiple records
        try (Connection c = newConnection()) {
            new AmigosDAO(c).insertBD(novoAmigo("Grace Lee", 32, "1199110099", "grace@example.com"));
        }
        try (Connection c = newConnection()) {
            new AmigosDAO(c).insertBD(novoAmigo("Henry King", 38, "1199009988", "henry@example.com"));
        }

        // Find the ID of the second record
        try (Connection c = newConnection()) {
            ArrayList<Amigos> lista = new AmigosDAO(c).listarAmigos();
            idBuscar = lista.stream()
                    .filter(a -> a.getNome().equals("Henry King"))
                    .findFirst().orElseThrow().getId();
        }

        // Search for the specific record
        Amigos amigoEncontrado;
        try (Connection c = newConnection()) {
            amigoEncontrado = new AmigosDAO(c).buscarAmigo(idBuscar);
        }

        assertNotNull(amigoEncontrado);
        assertEquals(idBuscar, amigoEncontrado.getId());
        assertEquals("Henry King", amigoEncontrado.getNome());
        assertEquals("henry@example.com", amigoEncontrado.getEmail());
        assertEquals("1199009988", amigoEncontrado.getTelefone());
        assertEquals(38, amigoEncontrado.getIdade());
    }

    /**
    @Test
    void listarAmigos_variosRegistros_deveRetornarTodos() throws Exception {
        // Insert multiple records
        try (Connection c = newConnection()) {
            AmigosDAO dao = new AmigosDAO(c);
            dao.insertBD(novoAmigo("Amigo 1", 20, "111111111", "amigo1@test.com"));
            dao.insertBD(novoAmigo("Amigo 2", 25, "222222222", "amigo2@test.com"));
            dao.insertBD(novoAmigo("Amigo 3", 30, "333333333", "amigo3@test.com"));
        }

        ArrayList<Amigos> lista;
        try (Connection c = newConnection()) {
            lista = new AmigosDAO(c).listarAmigos();
        }

        assertEquals(3, lista.size());
    } */

    @Test
    void insertBD_deveProtegerContraSQLInjection() throws Exception {
        String payload = "'; DROP TABLE amigos; --";

        try (Connection c = newConnection()) {
            AmigosDAO dao = new AmigosDAO(c);
            Amigos amigo = novoAmigo(payload, 99, "1199887766", "injection@test.com");
            dao.insertBD(amigo);
        }

        try (Connection c = newConnection()) {
            ArrayList<Amigos> lista = new AmigosDAO(c).listarAmigos();
            assertEquals(1, lista.size());
            assertEquals(payload, lista.get(0).getNome());
        }
    }

    @Test
    void UpdateAmigos_somenteCamposEspecificos_deveAtualizarApenasNomeEmailTelefone() throws Exception {
        int idGerado;

        // Insert initial record
        try (Connection c = newConnection()) {
            new AmigosDAO(c).insertBD(novoAmigo("Original Name", 40, "1199999999", "original@email.com"));
        }

        // Get the ID
        try (Connection c = newConnection()) {
            idGerado = new AmigosDAO(c).listarAmigos().get(0).getId();
        }

        // Update with partial data (only nome, email, telefone)
        try (Connection c = newConnection()) {
            Amigos atualizacao = new Amigos();
            atualizacao.setNome("Nome Atualizado");
            atualizacao.setEmail("novo@email.com");
            atualizacao.setTelefone("1188888888");
            // Note: idade is not set, so it won't be updated
            new AmigosDAO(c).UpdateAmigos(atualizacao, idGerado);
        }

        // Verify only the specified fields were updated
        try (Connection c = newConnection()) {
            Amigos amigo = new AmigosDAO(c).listarAmigos().get(0);
            assertEquals("Nome Atualizado", amigo.getNome());
            assertEquals("novo@email.com", amigo.getEmail());
            assertEquals("1188888888", amigo.getTelefone());
            // idade should remain the original value since UpdateAmigos doesn't update it
            assertEquals(40, amigo.getIdade());
        }
    }

    @Test
    void buscarAmigo_comIdInexistente_deveRetornarAmigoVazio() throws Exception {
        Amigos amigoEncontrado;
        try (Connection c = newConnection()) {
            amigoEncontrado = new AmigosDAO(c).buscarAmigo(999); // Non-existent ID
        }

        assertNotNull(amigoEncontrado);
        assertEquals(0, amigoEncontrado.getId()); // Should return default Amigos object
        assertNull(amigoEncontrado.getNome());
        assertNull(amigoEncontrado.getEmail());
        assertNull(amigoEncontrado.getTelefone());
        assertEquals(0, amigoEncontrado.getIdade());
    }
}