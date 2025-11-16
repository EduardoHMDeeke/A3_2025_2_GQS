package com.mycompany.a3_2025_2_gqs.Controller;

import com.mycompany.a3_2025_2_gqs.DAO.AmigosDAO;
import com.mycompany.a3_2025_2_gqs.DAO.Conexao;
import com.mycompany.a3_2025_2_gqs.DAO.EmprestimosDAO;
import com.mycompany.a3_2025_2_gqs.DAO.FerramentaDAO;
import com.mycompany.a3_2025_2_gqs.DTO.EmprestimosDTO;
import com.mycompany.a3_2025_2_gqs.Model.Amigos;
import com.mycompany.a3_2025_2_gqs.Model.Emprestimos;
import com.mycompany.a3_2025_2_gqs.Model.Ferramentas;
import com.mycompany.a3_2025_2_gqs.View.TelaPrincipal;
import org.junit.jupiter.api.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ListaAmigosFerramentasControllerTest {

    private Connection keeper;
    private TestTelaPrincipal mockView;
    private ListaAmigosFerramentasController controller;

    // Create a test-specific subclass that returns compatible types
    static class TestTelaPrincipal extends TelaPrincipal {
        private final JTable tableAmigos;
        private final JTable tableFerramentas;
        private final JTable tabelaEmprestimo;

        public TestTelaPrincipal() {
            // Don't call super constructor to avoid GUI initialization
            this.tableAmigos = createTestTable(new String[]{"ID", "Nome", "Email", "Telefone"});
            this.tableFerramentas = createTestTable(new String[]{"ID", "Nome", "Marca", "Preço"});
            this.tabelaEmprestimo = createTestTable(new String[]{"ID", "Amigo", "Ferramenta", "Data Emprestimo", "Data Devolução"});
        }

        private JTable createTestTable(String[] columns) {
            DefaultTableModel model = new DefaultTableModel(columns, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Make table non-editable
                }
            };
            return new JTable(model);
        }

        @Override
        public JTable getTable_amigos() {
            return tableAmigos;
        }

        @Override
        public JTable getTable_ferramentas() {
            return tableFerramentas;
        }

        @Override
        public JTable getTabelaEmprestimo() {
            return tabelaEmprestimo;
        }
    }

    @BeforeEach
    void setUp() throws SQLException {
        // Set headless mode to prevent GUI initialization
        System.setProperty("java.awt.headless", "true");
        
        keeper = newConnection();
        createSchema(keeper);
        clearData(keeper);
        
        mockView = new TestTelaPrincipal();
        controller = new ListaAmigosFerramentasController(mockView);
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
        // Create amigos table
        String sqlAmigos = "CREATE TABLE IF NOT EXISTS amigos ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nome TEXT, "
                + "email TEXT, "
                + "telefone TEXT"
                + ")";
        
        // Create ferramentas table
        String sqlFerramentas = "CREATE TABLE IF NOT EXISTS ferramentas ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nome TEXT, "
                + "marca TEXT, "
                + "preco TEXT, "
                + "valor TEXT, "
                + "estaEmprestada INTEGER"
                + ")";
        
        // Create emprestimos table
        String sqlEmprestimos = "CREATE TABLE IF NOT EXISTS emprestimos ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "idAmigos INTEGER, "
                + "idFerramentas INTEGER, "
                + "dataEmprestimo TEXT, "
                + "dataDevolucao TEXT, "
                + "estaEmprestada INTEGER"
                + ")";

        try (Statement st = c.createStatement()) {
            st.execute(sqlAmigos);
            st.execute(sqlFerramentas);
            st.execute(sqlEmprestimos);
        }
    }

    private static void clearData(Connection c) throws SQLException {
        try (Statement st = c.createStatement()) {
            st.executeUpdate("DELETE FROM amigos");
            st.executeUpdate("DELETE FROM ferramentas");
            st.executeUpdate("DELETE FROM emprestimos");
        }
    }

    private Amigos createAmigo(String nome, String email, String telefone) {
        Amigos amigo = new Amigos();
        amigo.setNome(nome);
        amigo.setEmail(email);
        amigo.setTelefone(telefone);
        return amigo;
    }

    private Ferramentas createFerramenta(String nome, String marca, String preco, int estaEmprestada) {
        Ferramentas ferramenta = new Ferramentas();
        ferramenta.setNome(nome);
        ferramenta.setMarca(marca);
        
        // Try to set preco using reflection to handle both setPreco and setValor
        boolean setOk = false;
        try {
            ferramenta.getClass().getMethod("setPreco", String.class).invoke(ferramenta, preco);
            setOk = true;
        } catch (Exception ignored) {}
        if (!setOk) {
            try {
                ferramenta.getClass().getMethod("setValor", String.class).invoke(ferramenta, preco);
                setOk = true;
            } catch (Exception ignored) {}
        }
        
        ferramenta.setEstaEmprestada(estaEmprestada);
        return ferramenta;
    }

    private Emprestimos createEmprestimo(int idAmigo, int idFerramenta, String dataEmprestimo, String dataDevolucao, int estaEmprestada) {
        Emprestimos emprestimo = new Emprestimos();
        emprestimo.setIdAmigos(idAmigo);
        emprestimo.setIdFerramentas(idFerramenta);
        emprestimo.setEstaEmprestada(estaEmprestada);
        
        // Use reflection to set date fields safely
        setFieldViaReflection(emprestimo, "setDataEmprestimo", dataEmprestimo);
        setFieldViaReflection(emprestimo, "setDataDevolucao", dataDevolucao);
        
        return emprestimo;
    }

    private void setFieldViaReflection(Object obj, String methodName, String value) {
        try {
            Method method = obj.getClass().getMethod(methodName, String.class);
            method.invoke(obj, value);
        } catch (Exception e) {
            // Try alternative method names if the primary one fails
            try {
                // Try with different capitalization
                String altMethodName = methodName.contains("Emprestimo") ? "setDataEmprestimo" : "setDataDevolucao";
                Method method = obj.getClass().getMethod(altMethodName, String.class);
                method.invoke(obj, value);
            } catch (Exception e2) {
                System.err.println("Warning: Could not find method " + methodName + " in " + obj.getClass().getSimpleName());
            }
        }
    }

    // Helper method to insert data using test connection directly
    private void insertTestData() throws SQLException {
        // Insert amigos
        AmigosDAO amigosDAO = new AmigosDAO(keeper);
        amigosDAO.insertBD(createAmigo("João Silva", "joao@email.com", "11999999999"));
        amigosDAO.insertBD(createAmigo("Maria Santos", "maria@email.com", "11888888888"));

        // Insert ferramentas
        FerramentaDAO ferramentaDAO = new FerramentaDAO(keeper);
        ferramentaDAO.insertBD(createFerramenta("Martelo", "Vonder", "49.90", 0));
        ferramentaDAO.insertBD(createFerramenta("Chave de Fenda", "Tramontina", "29.90", 1));
    }

    /** @Test
    void listarAmigos_devePopularTabelaComAmigos() throws SQLException {
        // Insert test data
        insertTestData();

        // Execute method under test
        controller.listarAmigos();

        // Verify results
        JTable table = mockView.getTable_amigos();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        assertEquals(2, model.getRowCount());
        assertEquals("João Silva", model.getValueAt(0, 1));
        assertEquals("joao@email.com", model.getValueAt(0, 2));
        assertEquals("Maria Santos", model.getValueAt(1, 1));
    } */

    /** @Test
    void listarAmigos_comTabelaVazia_deveManterTabelaVazia() throws SQLException {
        // No data inserted - table should be empty

        // Execute method under test
        controller.listarAmigos();

        // Verify results
        JTable table = mockView.getTable_amigos();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        assertEquals(0, model.getRowCount());
    } */

    /** @Test
    void listarFerramentas_devePopularTabelaComFerramentas() throws SQLException {
        // Insert test data
        insertTestData();

        // Execute method under test
        controller.listarFerramentas();

        // Verify results
        JTable table = mockView.getTable_ferramentas();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        assertEquals(2, model.getRowCount());
        assertEquals("Martelo", model.getValueAt(0, 1));
        assertEquals("Vonder", model.getValueAt(0, 2));
        assertEquals("Chave de Fenda", model.getValueAt(1, 1));
    } */

    /** @Test
    void listarEmprestimos_devePopularTabelaComEmprestimosAtivos() throws SQLException {
        // Insert amigos and ferramentas first
        AmigosDAO amigosDAO = new AmigosDAO(keeper);
        amigosDAO.insertBD(createAmigo("Carlos", "carlos@email.com", "11777777777"));
        
        ArrayList<Amigos> amigos = amigosDAO.listarAmigos();
        int amigoId = amigos.get(0).getId();

        FerramentaDAO ferramentaDAO = new FerramentaDAO(keeper);
        ferramentaDAO.insertBD(createFerramenta("Serrote", "Irwin", "79.90", 1));
        
        ArrayList<Ferramentas> ferramentas = ferramentaDAO.listarFerramentas();
        int ferramentaId = ferramentas.get(0).getId();

        // Insert emprestimo
        EmprestimosDAO emprestimosDAO = new EmprestimosDAO(keeper);
        emprestimosDAO.insertBD(createEmprestimo(amigoId, ferramentaId, "2024-01-01", "2024-01-15", 1));

        // Execute method under test
        controller.listarEmprestimos();

        // Verify results
        JTable table = mockView.getTabelaEmprestimo();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        assertEquals(1, model.getRowCount());
        assertEquals("Carlos", model.getValueAt(0, 1));
        assertEquals("Serrote", model.getValueAt(0, 2));
    } */

    /** @Test
    void listarEmprestimos_comEmprestimoInativo_naoDeveMostrarNaTabela() throws SQLException {
        // Insert amigos and ferramentas first
        AmigosDAO amigosDAO = new AmigosDAO(keeper);
        amigosDAO.insertBD(createAmigo("Ana", "ana@email.com", "11666666666"));
        
        ArrayList<Amigos> amigos = amigosDAO.listarAmigos();
        int amigoId = amigos.get(0).getId();

        FerramentaDAO ferramentaDAO = new FerramentaDAO(keeper);
        ferramentaDAO.insertBD(createFerramenta("Alicate", "Gedore", "39.90", 0));
        
        ArrayList<Ferramentas> ferramentas = ferramentaDAO.listarFerramentas();
        int ferramentaId = ferramentas.get(0).getId();

        // Insert inactive emprestimo
        EmprestimosDAO emprestimosDAO = new EmprestimosDAO(keeper);
        emprestimosDAO.insertBD(createEmprestimo(amigoId, ferramentaId, "2024-01-01", "2024-01-15", 0));

        // Execute method under test
        controller.listarEmprestimos();

        // Verify results - should be empty because estaEmprestada = 0
        JTable table = mockView.getTabelaEmprestimo();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        assertEquals(0, model.getRowCount());
    } */
}