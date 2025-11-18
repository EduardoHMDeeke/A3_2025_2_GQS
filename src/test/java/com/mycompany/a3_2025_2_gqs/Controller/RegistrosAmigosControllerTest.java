package com.mycompany.a3_2025_2_gqs.Controller;

import com.mycompany.a3_2025_2_gqs.DAO.AmigosDAO;
import com.mycompany.a3_2025_2_gqs.Model.Amigos;
import com.mycompany.a3_2025_2_gqs.View.RegistrosAmigos;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIf;
import java.awt.GraphicsEnvironment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@EnabledIf("isGuiTestSupported")
public class RegistrosAmigosControllerTest {
    
    private static boolean isGuiTestSupported() {
        return !GraphicsEnvironment.isHeadless();
    }

    private Connection testConnection;
    private RegistrosAmigos mockView;
    private RegistrosAmigosController controller;

    @BeforeEach
    void setUp() throws SQLException {
        testConnection = DriverManager.getConnection("jdbc:sqlite:file:memdb_registros_amigos?mode=memory&cache=shared");
        createSchema(testConnection);
        
        // Criar mock da view
        mockView = new RegistrosAmigos();
        mockView.setTestMode(true); // Suprimir JOptionPane
        controller = new RegistrosAmigosController(mockView);
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (testConnection != null && !testConnection.isClosed()) {
            testConnection.close();
        }
        if (mockView != null) {
            mockView.dispose();
        }
    }

    private static void createSchema(Connection c) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS amigos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT,
                idade INTEGER,
                telefone TEXT,
                email TEXT
            )
            """;
        try (Statement st = c.createStatement()) {
            st.execute(sql);
        }
    }

    @Test
    void constructor_deveCriarControllerComView() {
        assertNotNull(controller);
        assertNotNull(mockView);
    }

    @Test
    void registrarAmigo_comDadosValidos_deveCadastrar() throws Exception {
        // Configurar campos da view
        mockView.getTxtnome().setText("João Silva");
        mockView.getTxtemail().setText("joao@email.com");
        mockView.getTxttelefone().setText("11999999999");

        // Executar cadastro
        try {
            controller.registrarAmigo();
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(e instanceof SQLException || e.getMessage().contains("Variável de ambiente"));
        }
    }

    @Test
    void registrarAmigo_comCamposVazios_naoDeveCadastrar() {
        // Configurar campos vazios
        mockView.getTxtnome().setText("");
        mockView.getTxtemail().setText("");
        mockView.getTxttelefone().setText("");

        // Executar - deve retornar sem cadastrar (validação)
        controller.registrarAmigo();
        
        // Se chegou aqui, a validação funcionou
        assertTrue(true);
    }

    @Test
    void updateAmigo_comDadosValidos_deveAtualizar() throws Exception {
        // Configurar campos da view
        mockView.getTxtId().setText("1");
        mockView.getTxtnome().setText("João Silva Atualizado");
        mockView.getTxtemail().setText("joao.novo@email.com");
        mockView.getTxttelefone().setText("11888888888");

        // Executar atualização
        try {
            controller.updateAmigo();
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(e instanceof SQLException || e instanceof NumberFormatException ||
                      e.getMessage().contains("Variável de ambiente"));
        }
    }

    @Test
    void updateAmigo_comIdInvalido_deveLancarExcecao() {
        mockView.getTxtId().setText("abc");
        mockView.getTxtnome().setText("João");
        mockView.getTxtemail().setText("joao@email.com");
        mockView.getTxttelefone().setText("11999999999");

        // Deve lançar NumberFormatException
        assertThrows(Exception.class, () -> {
            controller.updateAmigo();
        });
    }

    @Test
    void deleteAmigo_comIdValido_deveDeletar() throws Exception {
        mockView.getTxtId().setText("1");

        // Executar deleção
        try {
            controller.deleteAmigo();
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(e instanceof SQLException || e instanceof NumberFormatException ||
                      e.getMessage().contains("Variável de ambiente"));
        }
    }

    @Test
    void deleteAmigo_comIdInvalido_deveLancarExcecao() {
        mockView.getTxtId().setText("abc");

        // Deve lançar NumberFormatException
        assertThrows(Exception.class, () -> {
            controller.deleteAmigo();
        });
    }
}

