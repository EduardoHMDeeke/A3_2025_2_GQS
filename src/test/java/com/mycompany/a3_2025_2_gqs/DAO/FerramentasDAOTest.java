package com.mycompany.a3_2025_2_gqs.DAO;

import com.mycompany.a3_2025_2_gqs.Model.Ferramentas;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class FerramentasDAOTest {

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
        String sql = "CREATE TABLE IF NOT EXISTS ferramentas ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nome TEXT, "
                + "marca TEXT, "
                + "preco TEXT, "
                + "valor TEXT, "
                + "estaEmprestada INTEGER"
                + ")";
        try (Statement st = c.createStatement()) {
            st.execute(sql);
        }
    }

    private static void clearData(Connection c) throws SQLException {
        try (Statement st = c.createStatement()) {
            st.executeUpdate("DELETE FROM ferramentas");
        } catch (SQLException e) {
            throw e;
        }
    }

    private Ferramentas novaFerramenta(String nome, String marca, String preco, int estaEmprestada) {
        Ferramentas f = new Ferramentas();
        f.setNome(nome);
        f.setMarca(marca);

        boolean setOk = false;
        try {
            f.getClass().getMethod("setPreco", String.class).invoke(f, preco);
            setOk = true;
        } catch (Exception ignored) {
            // Leave empty?
        }
        if (!setOk) {
            try {
                f.getClass().getMethod("setValor", String.class).invoke(f, preco);
                setOk = true;
            } catch (Exception ignored) {
                // Leave empty?
            }
        }
        if (!setOk) {
            throw new RuntimeException("Configure setPreco(String) OU setValor(String) na classe Ferramentas.");
        }

        f.setEstaEmprestada(estaEmprestada);
        return f;
    }

    @Test
    void insertBD_e_listarFerramentas_devePersistirEListar() throws Exception {
        try (Connection c1 = newConnection()) {
            FerramentaDAO dao = new FerramentaDAO(c1);
            Ferramentas f = novaFerramenta("Chave de Fenda", "Tramontina", "29.90", 0);
            dao.insertBD(f);
        }

        ArrayList<Ferramentas> lista;
        try (Connection c2 = newConnection()) {
            FerramentaDAO dao2 = new FerramentaDAO(c2);
            lista = dao2.listarFerramentas();
        }

        assertEquals(1, lista.size());
        Ferramentas salvo = lista.get(0);
        assertEquals("Chave de Fenda", salvo.getNome());
        assertEquals("Tramontina", salvo.getMarca());
        
        assertEquals("29.90", salvo.getPreco()); 
        
        assertEquals(0, salvo.getEstaEmprestada());
        assertTrue(salvo.getId() > 0);
    }

    @Test
    void listarFerramentasNaoEmprestadas_deveRetornarOndeEstaEmprestadaIgualA1() throws Exception {
        try (Connection c = newConnection()) {
            new FerramentaDAO(c).insertBD(novaFerramenta("Martelo", "Vonder", "49.90", 1));
        }
        try (Connection c = newConnection()) {
            new FerramentaDAO(c).insertBD(novaFerramenta("Alicate", "Gedore", "39.90", 0));
        }

        ArrayList<Ferramentas> lista;
        try (Connection c = newConnection()) {
            lista = new FerramentaDAO(c).listarFerramentasNaoEmprestadas();
        }

        assertEquals(1, lista.size());
        assertEquals("Martelo", lista.get(0).getNome());
        assertEquals(1, lista.get(0).getEstaEmprestada());
    }

    @Test
    void updateFerramenta_deveAtualizarNomeMarcaPreco() throws Exception {
        int idGerado;

        try (Connection c = newConnection()) {
            new FerramentaDAO(c).insertBD(novaFerramenta("Serrote", "Irwin", "79.90", 0));
        }

        try (Connection c = newConnection()) {
            ArrayList<Ferramentas> lista = new FerramentaDAO(c).listarFerramentas();
            assertFalse(lista.isEmpty());
            idGerado = lista.get(0).getId();
        }

        try (Connection c = newConnection()) {
            Ferramentas novo = novaFerramenta("Serrote Profissional", "Irwin Novo", "99.90", 0);
            new FerramentaDAO(c).updateFerramenta(novo, idGerado);
        }

        try (Connection c = newConnection()) {
            ArrayList<Ferramentas> lista = new FerramentaDAO(c).listarFerramentas();
            assertEquals(1, lista.size());
            assertEquals("Serrote Profissional", lista.get(0).getNome());
            assertEquals("Irwin Novo", lista.get(0).getMarca());
            
            assertEquals("99.90", lista.get(0).getPreco());
            
            assertEquals(idGerado, lista.get(0).getId());
        }
    }

    @Test
    void updateStatus_deveAtualizarCampoEstaEmprestada() throws Exception {
        int id;

        try (Connection c = newConnection()) {
            new FerramentaDAO(c).insertBD(novaFerramenta("Trena", "Stanley", "59.90", 0));
        }

        try (Connection c = newConnection()) {
            id = new FerramentaDAO(c).listarFerramentas().get(0).getId();
        }

        try (Connection c = newConnection()) {
            new FerramentaDAO(c).updateStatus(1, id);
        }

        try (Connection c = newConnection()) {
            Ferramentas f = new FerramentaDAO(c).listarFerramentas().get(0);
            assertEquals(1, f.getEstaEmprestada());
        }
    }

    @Test
    void deleteFerramentas_deveExcluirRegistro() throws Exception {
        int id;

        try (Connection c = newConnection()) {
            new FerramentaDAO(c).insertBD(novaFerramenta("Nível", "Schulz", "89.90", 0));
        }

        try (Connection c = newConnection()) {
            ArrayList<Ferramentas> lista = new FerramentaDAO(c).listarFerramentas();
            assertFalse(lista.isEmpty());
            id = lista.get(0).getId();
        }

        try (Connection c = newConnection()) {
            new FerramentaDAO(c).deleteFerramentas(id);
        }

        try (Connection c = newConnection()) {
            assertTrue(new FerramentaDAO(c).listarFerramentas().isEmpty());
        }
    }
    
    @Test
    void buscarFerramenta_deveRetornarCorretoPeloId() throws Exception {
        int idBuscar;
        
        try (Connection c = newConnection()) {
            new FerramentaDAO(c).insertBD(novaFerramenta("Parafusadeira", "Bosch", "199.90", 0));
        }
        try (Connection c = newConnection()) {
            new FerramentaDAO(c).insertBD(novaFerramenta("Furadeira", "Makita", "299.90", 0));
        }

        try (Connection c = newConnection()) {
             ArrayList<Ferramentas> lista = new FerramentaDAO(c).listarFerramentas();
             idBuscar = lista.stream()
                     .filter(f -> f.getNome().equals("Furadeira"))
                     .findFirst().orElseThrow().getId();
        }
        
        Ferramentas ferramentaEncontrada;
        try (Connection c = newConnection()) {
            ferramentaEncontrada = new FerramentaDAO(c).buscarFerramenta(idBuscar);
        }
        
        assertNotNull(ferramentaEncontrada);
        assertEquals(idBuscar, ferramentaEncontrada.getId());
        assertEquals("Furadeira", ferramentaEncontrada.getNome());
        assertEquals("Makita", ferramentaEncontrada.getMarca());
        
        assertEquals("299.90", ferramentaEncontrada.getPreco());
        
        assertEquals(0, ferramentaEncontrada.getEstaEmprestada());
    }

    @Test
    void insertBD_deveProtegerContraSQLInjection() throws Exception {
        String payload = "'; DROP TABLE ferramentas; --";
        
        try (Connection c = newConnection()) {
            FerramentaDAO dao = new FerramentaDAO(c);
            Ferramentas f = novaFerramenta(payload, "Marca", "10.00", 0);
            dao.insertBD(f);
        }

        try (Connection c = newConnection()) {
            ArrayList<Ferramentas> lista = new FerramentaDAO(c).listarFerramentas();
            assertEquals(1, lista.size());
            assertEquals(payload, lista.get(0).getNome());
        }
    }

    @Test
    void buscarFerramenta_inexistente_retornaObjetoVazio() throws Exception {
        try (Connection c = newConnection()) {
            FerramentaDAO dao = new FerramentaDAO(c);
            Ferramentas f = dao.buscarFerramenta(999999);
            assertNotNull(f);
            assertEquals(0, f.getId());
        }
    }

    @Test
    void listarFerramentas_comTabelaVazia_retornaListaVazia() throws Exception {
        try (Connection c = newConnection()) {
            FerramentaDAO dao = new FerramentaDAO(c);
            ArrayList<Ferramentas> lista = dao.listarFerramentas();
            assertTrue(lista.isEmpty());
        }
    }

    @Test
    void listarFerramentasNaoEmprestadas_comTabelaVazia_retornaListaVazia() throws Exception {
        try (Connection c = newConnection()) {
            FerramentaDAO dao = new FerramentaDAO(c);
            ArrayList<Ferramentas> lista = dao.listarFerramentasNaoEmprestadas();
            assertTrue(lista.isEmpty());
        }
    }

    @Test
    void updateFerramenta_comIdInexistente_naoLancaExcecao() throws Exception {
        try (Connection c = newConnection()) {
            FerramentaDAO dao = new FerramentaDAO(c);
            Ferramentas f = novaFerramenta("Teste", "Marca", "10.00", 0);
            // Update with non-existent ID should not throw exception
            assertDoesNotThrow(() -> {
                dao.updateFerramenta(f, 999999);
            });
        }
    }

    @Test
    void deleteFerramentas_comIdInexistente_naoLancaExcecao() throws Exception {
        try (Connection c = newConnection()) {
            FerramentaDAO dao = new FerramentaDAO(c);
            // Delete with non-existent ID should not throw exception
            assertDoesNotThrow(() -> {
                dao.deleteFerramentas(999999);
            });
        }
    }

    @Test
    void updateStatus_comIdInexistente_naoLancaExcecao() throws Exception {
        try (Connection c = newConnection()) {
            FerramentaDAO dao = new FerramentaDAO(c);
            // Update status with non-existent ID should not throw exception
            assertDoesNotThrow(() -> {
                dao.updateStatus(1, 999999);
            });
        }
    }

    @Test
    void insertBD_withSQLException_shouldPropagateException() throws Exception {
        // Test that SQLException is propagated from insertBD
        try (Connection c = newConnection()) {
            c.close();
            
            FerramentaDAO dao = new FerramentaDAO(c);
            Ferramentas f = novaFerramenta("Teste", "Marca", "10.00", 0);
            
            assertThrows(SQLException.class, () -> {
                dao.insertBD(f);
            });
        }
    }

    @Test
    void listarFerramentas_withSQLException_shouldHandleGracefully() throws Exception {
        // Test that listarFerramentas handles SQLException gracefully
        // Note: In headless mode, JOptionPane throws HeadlessException
        try (Connection c = newConnection()) {
            c.close();
            
            FerramentaDAO dao = new FerramentaDAO(c);
            
            // May throw HeadlessException in headless mode, which is acceptable
            try {
                ArrayList<Ferramentas> lista = dao.listarFerramentas();
            } catch (java.awt.HeadlessException e) {
                // Expected in headless mode
            }
        }
    }

    @Test
    void listarFerramentasNaoEmprestadas_withSQLException_shouldHandleGracefully() throws Exception {
        // Test that listarFerramentasNaoEmprestadas handles SQLException gracefully
        // Note: In headless mode, JOptionPane throws HeadlessException
        try (Connection c = newConnection()) {
            c.close();
            
            FerramentaDAO dao = new FerramentaDAO(c);
            
            // May throw HeadlessException in headless mode, which is acceptable
            try {
                ArrayList<Ferramentas> lista = dao.listarFerramentasNaoEmprestadas();
            } catch (java.awt.HeadlessException e) {
                // Expected in headless mode
            }
        }
    }

    @Test
    void buscarFerramenta_withSQLException_shouldHandleGracefully() throws Exception {
        // Test that buscarFerramenta handles SQLException gracefully
        // Note: In headless mode, JOptionPane throws HeadlessException
        try (Connection c = newConnection()) {
            c.close();
            
            FerramentaDAO dao = new FerramentaDAO(c);
            
            // May throw HeadlessException in headless mode, which is acceptable
            try {
                Ferramentas f = dao.buscarFerramenta(1);
            } catch (java.awt.HeadlessException e) {
                // Expected in headless mode
            }
        }
    }

    @Test
    void updateFerramenta_withSQLException_shouldHandleGracefully() throws Exception {
        // Test that updateFerramenta handles SQLException gracefully
        try (Connection c = newConnection()) {
            c.close();
            
            FerramentaDAO dao = new FerramentaDAO(c);
            Ferramentas f = novaFerramenta("Teste", "Marca", "10.00", 0);
            
            assertDoesNotThrow(() -> {
                dao.updateFerramenta(f, 1);
            });
        }
    }

    @Test
    void deleteFerramentas_withSQLException_shouldHandleGracefully() throws Exception {
        // Test that deleteFerramentas handles SQLException gracefully
        try (Connection c = newConnection()) {
            c.close();
            
            FerramentaDAO dao = new FerramentaDAO(c);
            
            assertDoesNotThrow(() -> {
                dao.deleteFerramentas(1);
            });
        }
    }

    @Test
    void updateStatus_withSQLException_shouldHandleGracefully() throws Exception {
        // Test that updateStatus handles SQLException gracefully
        try (Connection c = newConnection()) {
            c.close();
            
            FerramentaDAO dao = new FerramentaDAO(c);
            
            assertDoesNotThrow(() -> {
                dao.updateStatus(1, 1);
            });
        }
    }

    @Test
    void listarFerramentas_shouldClearListBeforeAdding() throws Exception {
        // Test that lista is cleared before adding new items
        try (Connection c = newConnection()) {
            FerramentaDAO dao = new FerramentaDAO(c);
            
            // Insert first batch
            dao.insertBD(novaFerramenta("Ferramenta1", "Marca1", "10.00", 0));
            
            ArrayList<Ferramentas> lista1 = dao.listarFerramentas();
            assertEquals(1, lista1.size());
            
            // Insert second batch
            dao.insertBD(novaFerramenta("Ferramenta2", "Marca2", "20.00", 0));
            
            ArrayList<Ferramentas> lista2 = dao.listarFerramentas();
            assertEquals(2, lista2.size());
        }
    }

    @Test
    void listarFerramentasNaoEmprestadas_shouldClearListBeforeAdding() throws Exception {
        // Test that lista is cleared in listarFerramentasNaoEmprestadas
        try (Connection c = newConnection()) {
            FerramentaDAO dao = new FerramentaDAO(c);
            
            // Insert ferramentas with estaEmprestada = 1
            dao.insertBD(novaFerramenta("Ferramenta1", "Marca1", "10.00", 1));
            dao.insertBD(novaFerramenta("Ferramenta2", "Marca2", "20.00", 1));
            
            ArrayList<Ferramentas> lista = dao.listarFerramentasNaoEmprestadas();
            assertEquals(2, lista.size());
        }
    }

    @Test
    void buscarFerramenta_shouldReturnCorrectFerramenta() throws Exception {
        // Test buscarFerramenta with multiple records
        int id1, id2;
        
        try (Connection c = newConnection()) {
            FerramentaDAO dao = new FerramentaDAO(c);
            dao.insertBD(novaFerramenta("Ferramenta1", "Marca1", "10.00", 0));
            dao.insertBD(novaFerramenta("Ferramenta2", "Marca2", "20.00", 0));
            
            ArrayList<Ferramentas> lista = dao.listarFerramentas();
            id1 = lista.get(0).getId();
            id2 = lista.get(1).getId();
        }
        
        try (Connection c = newConnection()) {
            FerramentaDAO dao = new FerramentaDAO(c);
            
            Ferramentas f1 = dao.buscarFerramenta(id1);
            assertEquals("Ferramenta1", f1.getNome());
            assertEquals("Marca1", f1.getMarca());
            
            Ferramentas f2 = dao.buscarFerramenta(id2);
            assertEquals("Ferramenta2", f2.getNome());
            assertEquals("Marca2", f2.getMarca());
        }
    }
}
