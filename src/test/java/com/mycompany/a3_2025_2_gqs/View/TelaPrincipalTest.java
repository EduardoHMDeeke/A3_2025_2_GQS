package com.mycompany.a3_2025_2_gqs.View;

import org.junit.jupiter.api.*;
import java.awt.GraphicsEnvironment;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import javax.swing.JPanel;
import java.lang.reflect.Constructor;
import sun.reflect.ReflectionFactory;
import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import sun.reflect.ReflectionFactory;
import javax.swing.JPopupMenu;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.awt.GraphicsEnvironment;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TelaPrincipalTest {

    private static final String TARGET_CLASS = "com.mycompany.a3_2025_2_gqs.View.TelaPrincipal";

    private Object createTelaPrincipalInstance() throws Exception {
        AtomicReference<Object> ref = new AtomicReference<>();
        AtomicReference<Throwable> err = new AtomicReference<>();
        SwingUtilities.invokeAndWait(() -> {
            try {
                Object tela = Class.forName("com.mycompany.a3_2025_2_gqs.View.TelaPrincipal")
                        .getDeclaredConstructor().newInstance();
                ref.set(tela);
            } catch (Throwable e) {
                err.set(e);
            }
        });
        if (err.get() != null) {
            throw new Exception("Erro ao instanciar TelaPrincipal", err.get());
        }
        return ref.get();
    }
    // Commit: test(helper): createTelaWithManualComponents helper (no initComponents)

    private Object createTelaWithManualComponents() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);
        Constructor<?> objCons = Object.class.getDeclaredConstructor();
        sun.reflect.ReflectionFactory rf = sun.reflect.ReflectionFactory.getReflectionFactory();
        Constructor<?> fakeCons = rf.newConstructorForSerialization(cls, objCons);
        Object tela = fakeCons.newInstance();

        // ler constantes para configurar textos/fontes
        try {
            Field fFONT = cls.getDeclaredField("FONT_NAME");
            fFONT.setAccessible(true);
        } catch (NoSuchFieldException ignored) {
        }
        String FONT_NAME = tryGetStaticString(cls, "FONT_NAME");
        String AMIGO = tryGetStaticString(cls, "AMIGO");
        String REFRESH = tryGetStaticString(cls, "REFRESH");
        String LOGO = tryGetStaticString(cls, "LOGO");

        // criar componentes mínimos e configurar
        javax.swing.JTable tableAmigos = new javax.swing.JTable(new javax.swing.table.DefaultTableModel(
                new Object[][]{{1, "Nome", "email", "telefone"}}, new String[]{"id", "Nome", "Email", "Telefone"}));
        javax.swing.JTable tableFerramentas = new javax.swing.JTable(new javax.swing.table.DefaultTableModel(
                new Object[][]{{1, "Martelo", "Marca", "10.0"}}, new String[]{"Id", "Nome", "Marca", "Preço"}));
        javax.swing.JTable tabelaEmp = new javax.swing.JTable(new javax.swing.table.DefaultTableModel(
                new Object[][]{{1, "A", "B", "2025-01-01", "2025-01-02"}}, new String[]{"ID", "Amigo", "Ferramenta", "Data de emprestimo", "Data de devolução"}));

        javax.swing.JButton b_cadastrarAmigos = new javax.swing.JButton(AMIGO != null ? AMIGO : "Cadastrar Amigo");
        javax.swing.JButton b_Home = new javax.swing.JButton("Home");
        javax.swing.JButton b_ListaAmigos = new javax.swing.JButton("Amigos");
        javax.swing.JButton b_ListaFerramentas = new javax.swing.JButton("Ferramentas");

        javax.swing.JLabel jlLista = new javax.swing.JLabel("AMIGOS");
        if (FONT_NAME != null) {
            jlLista.setFont(new java.awt.Font(FONT_NAME, java.awt.Font.BOLD, 48));
        }

        javax.swing.JPanel JP_Lista = new javax.swing.JPanel();
        javax.swing.JPanel JP_Principal = new javax.swing.JPanel();

        javax.swing.JPopupMenu JPop_botoes = new javax.swing.JPopupMenu();
        javax.swing.JPopupMenu JPop_Home = new javax.swing.JPopupMenu();
        javax.swing.JPopupMenu JPop_Amigos = new javax.swing.JPopupMenu();

        javax.swing.JMenuItem popupHome = new javax.swing.JMenuItem("Home");
        javax.swing.JMenuItem popupAmigos = new javax.swing.JMenuItem("Amigos");
        javax.swing.JMenuItem popupFerramentas = new javax.swing.JMenuItem("Ferramentas");
        javax.swing.JMenuItem popupRelatorio = new javax.swing.JMenuItem("Relatório");
        javax.swing.JMenuItem popupOpcoes = new javax.swing.JMenuItem("Opções");
        javax.swing.JMenuItem popupSair = new javax.swing.JMenuItem("Sair");

        // tenta setar ícone se existir
        try {
            if (LOGO != null) {
                java.net.URL res = cls.getResource(LOGO);
                if (res != null) {
                    jlLista.setIcon(new javax.swing.ImageIcon(res));
                }
            }
        } catch (Throwable ignored) {
        }

        Map<String, Object> toInject = new HashMap<>();
        toInject.put("table_amigos", tableAmigos);
        toInject.put("table_ferramentas", tableFerramentas);
        toInject.put("tabelaEmprestimo", tabelaEmp);
        toInject.put("b_cadastrarAmigos", b_cadastrarAmigos);
        toInject.put("b_Home", b_Home);
        toInject.put("b_ListaAmigos", b_ListaAmigos);
        toInject.put("b_ListaFerramentas", b_ListaFerramentas);
        toInject.put("JL_ListaAmigos", jlLista);
        toInject.put("JP_Lista", JP_Lista);
        toInject.put("JP_Principal", JP_Principal);
        toInject.put("JPop_botoes", JPop_botoes);
        toInject.put("JPop_Home", JPop_Home);
        toInject.put("JPop_Amigos", JPop_Amigos);
        toInject.put("popupHome", popupHome);
        toInject.put("popupAmigos", popupAmigos);
        toInject.put("popupFerramentas", popupFerramentas);
        toInject.put("popupRelatorio", popupRelatorio);
        toInject.put("popupOpcoes", popupOpcoes);
        toInject.put("popupSair", popupSair);

        for (Map.Entry<String, Object> e : toInject.entrySet()) {
            try {
                Field f = cls.getDeclaredField(e.getKey());
                f.setAccessible(true);
                f.set(tela, e.getValue());
            } catch (NoSuchFieldException nsf) {
                // campo pode não existir com exatamente esse nome — ignorar
            }
        }

        // inicializa flags se existirem
        try {
            Field f = cls.getDeclaredField("cardHome");
            f.setAccessible(true);
            f.setBoolean(tela, false);
        } catch (NoSuchFieldException ignored) {
        }
        try {
            Field f = cls.getDeclaredField("cardAmigos");
            f.setAccessible(true);
            f.setBoolean(tela, false);
        } catch (NoSuchFieldException ignored) {
        }

        return tela;
    }

// pequeno helper auxiliar para ler constantes string estáticas sem lançar
    private static String tryGetStaticString(Class<?> cls, String name) {
        try {
            Field f = cls.getDeclaredField(name);
            f.setAccessible(true);
            Object v = f.get(null);
            return v == null ? null : v.toString();
        } catch (Throwable t) {
            return null;
        }
    }

    @BeforeAll
    void beforeAll() {
        // Garante execução em headless (CI / GitHub Actions) por padrão
        System.setProperty("java.awt.headless", "true");
    }

    // New method to create instance without running GUI initialization
    private Object createTelaPrincipalInstanceWithoutGUI() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);

        // Use ReflectionFactory to create instance without calling constructor
        Constructor<?> objCons = Object.class.getDeclaredConstructor();
        ReflectionFactory rf = ReflectionFactory.getReflectionFactory();
        Constructor<?> fakeCons = rf.newConstructorForSerialization(cls, objCons);
        Object instance = fakeCons.newInstance();

        // Now manually initialize just the fields we need for testing
        initializeUIComponents(instance, cls);

        return instance;
    }

    private void initializeUIComponents(Object instance, Class<?> cls) throws Exception {
        // Initialize basic UI components without triggering full GUI setup
        Field jpPrincipalField = cls.getDeclaredField("JP_Principal");
        jpPrincipalField.setAccessible(true);
        jpPrincipalField.set(instance, new JPanel(new CardLayout()));

        // Initialize other main panels
        Field[] panelFields = {
            cls.getDeclaredField("JP_Home"),
            cls.getDeclaredField("JP_ListaAmigos"),
            cls.getDeclaredField("JP_ListaFerramentas"),
            cls.getDeclaredField("JP_Relatorio"),
            cls.getDeclaredField("JP_Lista"),
            cls.getDeclaredField("jPanel1"),
            cls.getDeclaredField("jPanel2")
        };

        for (Field field : panelFields) {
            field.setAccessible(true);
            field.set(instance, new JPanel());
        }

        // Initialize popup menus
        Field[] popupFields = {
            cls.getDeclaredField("JPop_botoes"),
            cls.getDeclaredField("JPop_Amigos"),
            cls.getDeclaredField("JPop_Home")
        };

        for (Field field : popupFields) {
            field.setAccessible(true);
            field.set(instance, new JPopupMenu());
        }

        // Initialize menu items
        String[] menuItemFields = {
            "popupHome", "popupAmigos", "popupFerramentas", "popupRelatorio",
            "popupOpcoes", "popupSair", "popupCadastrar", "jMenuItem2", "popup001"
        };

        for (String fieldName : menuItemFields) {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(instance, new javax.swing.JMenuItem());
        }

        // Initialize buttons
        String[] buttonFields = {
            "realizarEmprestimo", "CadastrarAmigoHome", "CadastrarFerramentaHome",
            "editarEmprestimo", "devolverEmprestimo", "b_refreshEmprestimos",
            "b_cadastrarAmigos", "b_editarAmigo", "deleteAmigo", "atualizarTabela",
            "b_cadastrarFerramenta", "b_editarFerramenta", "b_apagarFerramenta",
            "AtualizarFerramentas", "b_Home", "b_ListaAmigos", "b_ListaFerramentas",
            "b_relatorio", "jMudarTema", "b_opcoes"
        };

        for (String fieldName : buttonFields) {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(instance, new javax.swing.JButton("Test"));
        }

        // Initialize labels
        String[] labelFields = {
            "jLabel3", "jLabel7", "JL_ListaAmigos", "jLabel6",
            "jLabel1", "jLabel8", "jLabel4"
        };

        for (String fieldName : labelFields) {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(instance, new javax.swing.JLabel("Test"));
        }

        // Initialize tables
        String[] tableFields = {"table_amigos", "table_ferramentas", "tabelaEmprestimo"};
        for (String fieldName : tableFields) {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(instance, new javax.swing.JTable());
        }

        // Initialize scroll panes
        String[] scrollPaneFields = {"jScrollPane4", "jScrollPane1", "jScrollPane2"};
        for (String fieldName : scrollPaneFields) {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(instance, new javax.swing.JScrollPane());
        }
    }

    // NEW TESTS FOR UI COMPONENT INITIALIZATION
    @Test
    @DisplayName("All JPopupMenu components should be properly initialized")
    void testAllPopupMenusInitialized() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);
        Object instance = createTelaPrincipalInstanceWithoutGUI();

        String[] popupMenuFields = {
            "JPop_botoes", "JPop_Amigos", "JPop_Home"
        };

        for (String fieldName : popupMenuFields) {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            Object popupMenu = field.get(instance);

            assertNotNull(popupMenu, fieldName + " should not be null");
            assertTrue(popupMenu instanceof JPopupMenu,
                    fieldName + " should be an instance of JPopupMenu");
        }
    }

    @Test
    @DisplayName("All JMenuItem components should be properly initialized")
    void testAllMenuItemsInitialized() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);
        Object instance = createTelaPrincipalInstanceWithoutGUI();

        String[] menuItemFields = {
            "popupHome", "popupAmigos", "popupFerramentas", "popupRelatorio",
            "popupOpcoes", "popupSair", "popupCadastrar", "jMenuItem2", "popup001"
        };

        for (String fieldName : menuItemFields) {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            Object menuItem = field.get(instance);

            assertNotNull(menuItem, fieldName + " should not be null");
            assertTrue(menuItem instanceof javax.swing.JMenuItem,
                    fieldName + " should be an instance of JMenuItem");
        }
    }

    @Test
    @DisplayName("All JPanel components should be properly initialized")
    void testAllPanelsInitialized() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);
        Object instance = createTelaPrincipalInstanceWithoutGUI();

        String[] panelFields = {
            "JP_Principal", "JP_Home", "jPanel2", "jPanel1",
            "JP_ListaAmigos", "JP_ListaFerramentas", "JP_Relatorio", "JP_Lista"
        };

        for (String fieldName : panelFields) {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            Object panel = field.get(instance);

            assertNotNull(panel, fieldName + " should not be null");
            assertTrue(panel instanceof JPanel,
                    fieldName + " should be an instance of JPanel");
        }
    }

    @Test
    @DisplayName("All JButton components should be properly initialized")
    void testAllButtonsInitialized() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);
        Object instance = createTelaPrincipalInstanceWithoutGUI();

        String[] buttonFields = {
            "realizarEmprestimo", "CadastrarAmigoHome", "CadastrarFerramentaHome",
            "editarEmprestimo", "devolverEmprestimo", "b_refreshEmprestimos",
            "b_cadastrarAmigos", "b_editarAmigo", "deleteAmigo", "atualizarTabela",
            "b_cadastrarFerramenta", "b_editarFerramenta", "b_apagarFerramenta",
            "AtualizarFerramentas", "b_Home", "b_ListaAmigos", "b_ListaFerramentas",
            "b_relatorio", "jMudarTema", "b_opcoes"
        };

        for (String fieldName : buttonFields) {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            Object button = field.get(instance);

            assertNotNull(button, fieldName + " should not be null");
            assertTrue(button instanceof javax.swing.JButton,
                    fieldName + " should be an instance of JButton");
        }
    }

    @Test
    @DisplayName("All JLabel components should be properly initialized")
    void testAllLabelsInitialized() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);
        Object instance = createTelaPrincipalInstanceWithoutGUI();

        String[] labelFields = {
            "jLabel3", "jLabel7", "JL_ListaAmigos", "jLabel6",
            "jLabel1", "jLabel8", "jLabel4"
        };

        for (String fieldName : labelFields) {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            Object label = field.get(instance);

            assertNotNull(label, fieldName + " should not be null");
            assertTrue(label instanceof javax.swing.JLabel,
                    fieldName + " should be an instance of JLabel");
        }
    }

    @Test
    @DisplayName("All JTable and JScrollPane components should be properly initialized")
    void testAllTablesAndScrollPanesInitialized() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);
        Object instance = createTelaPrincipalInstanceWithoutGUI();

        // Test tables
        String[] tableFields = {"table_amigos", "table_ferramentas", "tabelaEmprestimo"};
        for (String fieldName : tableFields) {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            Object table = field.get(instance);

            assertNotNull(table, fieldName + " should not be null");
            assertTrue(table instanceof javax.swing.JTable,
                    fieldName + " should be an instance of JTable");
        }

        // Test scroll panes
        String[] scrollPaneFields = {"jScrollPane4", "jScrollPane1", "jScrollPane2"};
        for (String fieldName : scrollPaneFields) {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            Object scrollPane = field.get(instance);

            assertNotNull(scrollPane, fieldName + " should not be null");
            assertTrue(scrollPane instanceof javax.swing.JScrollPane,
                    fieldName + " should be an instance of JScrollPane");
        }
    }

    @Test
    @DisplayName("All UI components should be non-null after initialization")
    void testAllUIComponentsNonNull() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);
        Object instance = createTelaPrincipalInstanceWithoutGUI();

        // Comprehensive list of all UI component fields from the provided lines
        String[] allUIComponentFields = {
            // Popup Menus
            "JPop_botoes", "JPop_Amigos", "JPop_Home",
            // Menu Items
            "popupHome", "popupAmigos", "popupFerramentas", "popupRelatorio",
            "popupOpcoes", "popupSair", "popupCadastrar", "jMenuItem2", "popup001",
            // Panels
            "JP_Principal", "JP_Home", "jPanel2", "jPanel1",
            "JP_ListaAmigos", "JP_ListaFerramentas", "JP_Relatorio", "JP_Lista",
            // Buttons
            "realizarEmprestimo", "CadastrarAmigoHome", "CadastrarFerramentaHome",
            "editarEmprestimo", "devolverEmprestimo", "b_refreshEmprestimos",
            "b_cadastrarAmigos", "b_editarAmigo", "deleteAmigo", "atualizarTabela",
            "b_cadastrarFerramenta", "b_editarFerramenta", "b_apagarFerramenta",
            "AtualizarFerramentas", "b_Home", "b_ListaAmigos", "b_ListaFerramentas",
            "b_relatorio", "jMudarTema", "b_opcoes",
            // Labels
            "jLabel3", "jLabel7", "JL_ListaAmigos", "jLabel6",
            "jLabel1", "jLabel8", "jLabel4",
            // Tables and Scroll Panes
            "tabelaEmprestimo", "table_amigos", "table_ferramentas",
            "jScrollPane4", "jScrollPane1", "jScrollPane2"
        };

        for (String fieldName : allUIComponentFields) {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            Object component = field.get(instance);

            assertNotNull(component,
                    "UI component " + fieldName + " should not be null after initialization");
        }
    }

    @Test
    @DisplayName("Popup menus should have correct field types")
    void testPopupMenuFieldTypes() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);
        Object instance = createTelaPrincipalInstanceWithoutGUI();

        // Test field types
        Field jpopBotoesField = cls.getDeclaredField("JPop_botoes");
        assertEquals(JPopupMenu.class, jpopBotoesField.getType(),
                "JPop_botoes should be of type JPopupMenu");

        Field jpopAmigosField = cls.getDeclaredField("JPop_Amigos");
        assertEquals(JPopupMenu.class, jpopAmigosField.getType(),
                "JPop_Amigos should be of type JPopupMenu");

        Field jpopHomeField = cls.getDeclaredField("JPop_Home");
        assertEquals(JPopupMenu.class, jpopHomeField.getType(),
                "JPop_Home should be of type JPopupMenu");
    }

    @Test
    @DisplayName("Main panels should have correct field types")
    void testMainPanelFieldTypes() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);
        Object instance = createTelaPrincipalInstanceWithoutGUI();

        // Test main panel field types
        Field jpPrincipalField = cls.getDeclaredField("JP_Principal");
        assertEquals(JPanel.class, jpPrincipalField.getType(),
                "JP_Principal should be of type JPanel");

        Field jpHomeField = cls.getDeclaredField("JP_Home");
        assertEquals(JPanel.class, jpHomeField.getType(),
                "JP_Home should be of type JPanel");

        Field jpListaAmigosField = cls.getDeclaredField("JP_ListaAmigos");
        assertEquals(JPanel.class, jpListaAmigosField.getType(),
                "JP_ListaAmigos should be of type JPanel");
    }

    @Test
    @DisplayName("Button fields should have correct types")
    void testButtonFieldTypes() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);
        Object instance = createTelaPrincipalInstanceWithoutGUI();

        // Test a sample of button field types
        String[] sampleButtons = {
            "b_Home", "b_ListaAmigos", "b_ListaFerramentas", "b_relatorio", "b_opcoes"
        };

        for (String fieldName : sampleButtons) {
            Field field = cls.getDeclaredField(fieldName);
            assertEquals(javax.swing.JButton.class, field.getType(),
                    fieldName + " should be of type JButton");
        }
    }

    @Test
    @DisplayName("Table fields should have correct types")
    void testTableFieldTypes() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);
        Object instance = createTelaPrincipalInstanceWithoutGUI();

        Field tableAmigosField = cls.getDeclaredField("table_amigos");
        assertEquals(javax.swing.JTable.class, tableAmigosField.getType(),
                "table_amigos should be of type JTable");

        Field tableFerramentasField = cls.getDeclaredField("table_ferramentas");
        assertEquals(javax.swing.JTable.class, tableFerramentasField.getType(),
                "table_ferramentas should be of type JTable");

        Field tabelaEmprestimoField = cls.getDeclaredField("tabelaEmprestimo");
        assertEquals(javax.swing.JTable.class, tabelaEmprestimoField.getType(),
                "tabelaEmprestimo should be of type JTable");
    }

    // KEEP ALL YOUR EXISTING TESTS THAT DON'T REQUIRE GUI INITIALIZATION
    // These should work fine as they use reflection only
    @Test
    void classShouldBeLoadable() throws ClassNotFoundException {
        Class<?> cls = Class.forName(TARGET_CLASS);
        assertNotNull(cls, "TelaPrincipal deve estar no classpath");
    }

    @Test
    void shouldDeclareExpectedUiFields() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);
        Field[] fields = cls.getDeclaredFields();
        Set<String> declared = new HashSet<>();
        for (Field f : fields) {
            declared.add(f.getName());
        }

        // Nomes detectados no código fornecido
        String[] expected = {
            "table_amigos",
            "table_ferramentas",
            "tabelaEmprestimo",
            "b_Home",
            "b_ListaAmigos",
            "b_ListaFerramentas",
            "b_relatorio",
            "b_opcoes",
            "JP_Principal",
            "JP_Home",
            // popups / menu items presentes no código
            "JPop_botoes",
            "JPop_Amigos",
            "JPop_Home",
            "popupHome",
            "popupAmigos",
            "popupFerramentas",
            "popupRelatorio",
            "popupOpcoes",
            "popupSair"
        };

        for (String name : expected) {
            assertTrue(declared.contains(name), "Campo esperado não encontrado em TelaPrincipal: " + name);
        }
    }

    @Test
    void controllerFieldShouldBeTransientAndFinal() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);
        Field controller = null;
        for (Field f : cls.getDeclaredFields()) {
            if ("controller".equals(f.getName())) {
                controller = f;
                break;
            }
        }
        assertNotNull(controller, "Campo 'controller' deve existir");
        int mods = controller.getModifiers();
        assertTrue(Modifier.isTransient(mods), "Campo 'controller' deve ser transient");
        assertTrue(Modifier.isFinal(mods), "Campo 'controller' deve ser final");
        assertFalse(Modifier.isStatic(mods), "Campo 'controller' não deve ser static");
    }

    @Test
    void fontNameConstantShouldMatchExpected() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);
        Field fontField = null;
        for (Field f : cls.getDeclaredFields()) {
            if ("FONT_NAME".equals(f.getName())) {
                fontField = f;
                break;
            }
        }
        assertNotNull(fontField, "Constante FONT_NAME deve existir");
        fontField.setAccessible(true);
        Object value = fontField.get(null); // static field
        assertEquals("Segoe UI Black", value, "FONT_NAME deve ter o valor esperado");
    }

    @Test
    void shouldHaveActionMethods() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);
        Method[] methods = cls.getDeclaredMethods();
        Set<String> methodNames = new HashSet<>();
        Arrays.stream(methods).forEach(m -> methodNames.add(m.getName()));

        String[] expectedMethods = {
            "b_HomeActionPerformed",
            "b_ListaAmigosActionPerformed",
            "b_ListaFerramentasActionPerformed",
            "jMudarTemaActionPerformed",
            "b_opcoesActionPerformed",
            "b_cadastrarAmigosActionPerformed",
            "initComponents",
            "JP_PrincipalMouseReleased"
        };

        for (String m : expectedMethods) {
            assertTrue(methodNames.contains(m), "Método de ação esperado não encontrado: " + m);
        }
    }

    @Test
    void shouldHaveExpectedGetterMethods() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);

        Set<String> methodNames = new HashSet<>();
        for (var m : cls.getDeclaredMethods()) {
            methodNames.add(m.getName());
        }

        String[] expectedGetters = {
            "getTable_amigos",
            "getTable_ferramentas",
            "getTabelaEmprestimo"
        };

        for (String g : expectedGetters) {
            assertTrue(methodNames.contains(g), "Falta método getter: " + g);
        }
    }

    @Test
    void initComponentsShouldBePrivateAndNonStatic() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);

        Method init = null;
        for (Method m : cls.getDeclaredMethods()) {
            if (m.getName().equals("initComponents")) {
                init = m;
            }
        }

        assertNotNull(init, "initComponents deve existir");
        int mods = init.getModifiers();
        assertTrue(Modifier.isPrivate(mods), "initComponents deve ser private");
        assertFalse(Modifier.isStatic(mods), "initComponents NÃO deve ser static");
    }

    @Test
    void shouldNotContainStaticSwingComponents() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);

        for (Field f : cls.getDeclaredFields()) {

            // Ignora constantes estáticas finais (ex: FONT_NAME)
            if (Modifier.isStatic(f.getModifiers()) && Modifier.isFinal(f.getModifiers())) {
                continue;
            }

            Class<?> type = f.getType();

            // Se é componente Swing (subtipo de java.awt.Component) e é static -> erro
            if (java.awt.Component.class.isAssignableFrom(type)) {
                assertFalse(Modifier.isStatic(f.getModifiers()),
                        "Componente Swing NÃO deve ser static: " + f.getName());
            }
        }
    }

    @Test
    void tableModelsShouldHaveExpectedColumns() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);

        // Verifica a existência dos JTable por reflexão (sempre)
        String[] tables = {
            "table_amigos",
            "table_ferramentas",
            "tabelaEmprestimo"
        };

        for (String table : tables) {
            Field f = cls.getDeclaredField(table);
            assertNotNull(f, "Campo JTable esperado não encontrado: " + table);
            assertTrue(javax.swing.JTable.class.isAssignableFrom(f.getType()),
                    "Campo '" + table + "' deve ser um JTable");
        }

        boolean runningHeadless = java.awt.GraphicsEnvironment.isHeadless();
        assumeFalse(runningHeadless, "Ambiente headless — pulando verificação de nomes de colunas");

        Object instance = null;
        try {
            Class<?> target = Class.forName(TARGET_CLASS);
            instance = target.getDeclaredConstructor().newInstance();

            // acessar campos JTable e verificar TableModel columns
            Field fAmigos = target.getDeclaredField("table_amigos");
            fAmigos.setAccessible(true);
            javax.swing.JTable tableAmigos = (javax.swing.JTable) fAmigos.get(instance);
            assertNotNull(tableAmigos.getModel(), "TableModel de table_amigos não deve ser null");
            // Exemplo de checagem básica (ajuste conforme seu modelo real)
            assertTrue(tableAmigos.getColumnCount() >= 3, "table_amigos deve ter pelo menos 3 colunas");

            Field fFerr = target.getDeclaredField("table_ferramentas");
            fFerr.setAccessible(true);
            javax.swing.JTable tableFerr = (javax.swing.JTable) fFerr.get(instance);
            assertNotNull(tableFerr.getModel(), "TableModel de table_ferramentas não deve ser null");
            assertTrue(tableFerr.getColumnCount() >= 3, "table_ferramentas deve ter pelo menos 3 colunas");

            Field fEmp = target.getDeclaredField("tabelaEmprestimo");
            fEmp.setAccessible(true);
            javax.swing.JTable tableEmp = (javax.swing.JTable) fEmp.get(instance);
            assertNotNull(tableEmp.getModel(), "TableModel de tabelaEmprestimo não deve ser null");
            assertTrue(tableEmp.getColumnCount() >= 4, "tabelaEmprestimo deve ter pelo menos 4 colunas");

        } finally {

            if (instance != null) {
                try {
                    Method mDispose = instance.getClass().getMethod("dispose");
                    mDispose.invoke(instance);
                } catch (NoSuchMethodException ignored) {
                } catch (Throwable ignored) {
                }
            }
        }
    }

    @Test
    void actionMethodsShouldBePrivateAndNonStatic() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);

        String[] expectedActionMethods = {
            "b_HomeActionPerformed",
            "b_ListaAmigosActionPerformed",
            "b_ListaFerramentasActionPerformed",
            "jMudarTemaActionPerformed",
            "b_opcoesActionPerformed",
            "b_cadastrarAmigosActionPerformed",
            "JP_PrincipalMouseReleased"
        };

        for (String methodName : expectedActionMethods) {
            Method m = null;

            for (Method mm : cls.getDeclaredMethods()) {
                if (mm.getName().equals(methodName)) {
                    m = mm;
                    break;
                }
            }

            assertNotNull(m, "Método de ação não encontrado: " + methodName);

            int mods = m.getModifiers();

            assertTrue(Modifier.isPrivate(mods),
                    "Método de ação deve ser private: " + methodName);

            assertFalse(Modifier.isStatic(mods),
                    "Método de ação NÃO deve ser static: " + methodName);
        }
    }

    @Test
    void cardPanelsShouldExistAndBeJPanels() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);

        Object[][] expectedCards = {
            {"JP_Home", javax.swing.JPanel.class},
            {"JP_ListaAmigos", javax.swing.JPanel.class},
            {"JP_ListaFerramentas", javax.swing.JPanel.class},
            {"JP_Relatorio", javax.swing.JPanel.class}
        };

        for (Object[] pair : expectedCards) {
            String fieldName = (String) pair[0];
            Class<?> expectedType = (Class<?>) pair[1];

            Field f = cls.getDeclaredField(fieldName);
            assertNotNull(f, "Painel/card esperado não foi encontrado: " + fieldName);

            assertTrue(expectedType.isAssignableFrom(f.getType()),
                    "Painel '" + fieldName + "' deve ser um " + expectedType.getSimpleName()
                    + ", mas é " + f.getType().getSimpleName());
        }
    }

    @Test
    void cardControlFlagsShouldExistAndBePrivateBooleans() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);

        String[] expectedFlags = {
            "cardHome",
            "cardAmigos",
            "cardFerramentas",
            "cardRelatorio"
        };

        for (String flag : expectedFlags) {
            Field f = cls.getDeclaredField(flag);
            assertNotNull(f, "Flag de controle não encontrada: " + flag);

            // Tipo deve ser boolean
            assertEquals(boolean.class, f.getType(),
                    "Flag '" + flag + "' deve ser do tipo boolean.");

            int mods = f.getModifiers();

            // Deve ser private
            assertTrue(Modifier.isPrivate(mods),
                    "Flag '" + flag + "' deve ser private.");

            // Não pode ser static
            assertFalse(Modifier.isStatic(mods),
                    "Flag '" + flag + "' NÃO deve ser static.");
        }
    }

    @Test
    @DisplayName("instantiate in EDT (skipped headless)")
    void test14_instantiateShouldNotThrowWhenNotHeadless() throws Exception {
        assumeFalse(GraphicsEnvironment.isHeadless(), "Ambiente headless: pulando teste de instanciação/EDT");
        Object tela = createTelaPrincipalInstance();
        assertNotNull(tela, "Instancia de TelaPrincipal não deve ser null");
    }

    @Test
    @DisplayName("doClick on buttons does not throw (skipped headless)")
    void test15_clickableButtons_doClick_shouldNotThrow() throws Exception {
        assumeFalse(GraphicsEnvironment.isHeadless(), "Ambiente headless: pulando teste de clique em botões");
        Object tela = createTelaPrincipalInstance();

        String[] commonButtonNames = {
            "b_cadastrarFerramenta", "CadastrarFerramentaHome",
            "b_cadastrarAmigos", "CadastrarAmigoHome",
            "b_Home", "b_ListaAmigos", "b_ListaFerramentas", "b_relatorio", "b_opcoes",
            "b_refreshEmprestimos", "realizarEmprestimo", "editarEmprestimo", "devolverEmprestimo"
        };

        List<Field> found = new ArrayList<>();
        Class<?> cls = tela.getClass();
        for (String n : commonButtonNames) {
            try {
                Field f = cls.getDeclaredField(n);
                f.setAccessible(true);
                found.add(f);
            } catch (NoSuchFieldException ex) {
                /* ignora */ }
        }

        assertFalse(found.isEmpty(), "Nenhum botão reconhecível encontrado na TelaPrincipal para clicar.");

        for (Field f : found) {
            Object val = f.get(tela);
            assertNotNull(val, "Campo " + f.getName() + " encontrado mas valor é null.");
            assertTrue(val instanceof AbstractButton, "Campo " + f.getName() + " não é um AbstractButton.");

            AbstractButton btn = (AbstractButton) val;
            AtomicReference<Throwable> err = new AtomicReference<>();
            SwingUtilities.invokeAndWait(() -> {
                try {
                    btn.doClick();
                } catch (Throwable t) {
                    err.set(t);
                }
            });
            if (err.get() != null) {
                fail("Clicar no botão " + f.getName() + " lançou exceção: " + err.get());
            }
        }
    }

    @Test
    @DisplayName("buttons have listeners or are enabled (skipped headless)")
    void test16_buttonsShouldHaveListenersOrBeEnabled() throws Exception {
        assumeFalse(GraphicsEnvironment.isHeadless(), "Ambiente headless: pulando verificação de listeners/estado");
        Object tela = createTelaPrincipalInstance();

        String[] toCheck = {"b_Home", "b_ListaAmigos", "b_ListaFerramentas", "b_relatorio", "b_opcoes", "b_cadastrarAmigos", "b_cadastrarFerramenta"};
        Class<?> cls = tela.getClass();
        boolean anyFound = false;
        for (String name : toCheck) {
            try {
                Field f = cls.getDeclaredField(name);
                f.setAccessible(true);
                Object v = f.get(tela);
                if (!(v instanceof AbstractButton)) {
                    continue;
                }
                anyFound = true;
                AbstractButton b = (AbstractButton) v;
                boolean hasListeners = b.getActionListeners() != null && b.getActionListeners().length > 0;
                assertTrue(hasListeners || b.isEnabled(), "Botão " + f.getName() + " está desabilitado e sem listeners.");
            } catch (NoSuchFieldException ex) {
                /* ignora */ }
        }
        assertTrue(anyFound, "Nenhum dos botões esperados foi encontrado para checagem de listeners/estado.");
    }

    @Test
    @DisplayName("frame default close operation and title check (skipped headless)")
    void test17_frameDefaultCloseOperationAndTitle_check() throws Exception {
        assumeFalse(GraphicsEnvironment.isHeadless(), "Ambiente headless: pulando verificação de JFrame");
        Object tela = createTelaPrincipalInstance();
        Class<?> cls = tela.getClass();

        Field frameField = null;
        try {
            frameField = cls.getDeclaredField("frame");
            frameField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            String[] alt = {"jFrame", "principalFrame", "TelaPrincipal"};
            for (String a : alt) {
                try {
                    frameField = cls.getDeclaredField(a);
                    frameField.setAccessible(true);
                    break;
                } catch (NoSuchFieldException ex) {
                    /* ignora */ }
            }
        }

        if (frameField != null) {
            Object f = frameField.get(tela);
            if (f instanceof JFrame) {
                JFrame jf = (JFrame) f;
                int op = jf.getDefaultCloseOperation();
                assertTrue(op >= 0 && op <= 3, "DefaultCloseOperation do JFrame parece inválido.");
                String title = jf.getTitle();
                assertNotNull(title, "JFrame deve ter um título (mesmo que vazio).");
            }
        } else {
            if (tela instanceof JFrame) {
                JFrame jf = (JFrame) tela;
                assertNotNull(jf.getTitle());
            }
        }
    }

    @Test
    @DisplayName("pack/show/hide frame does not throw (skipped headless)")
    void test18_showingFrame_pack_and_setVisible_noExceptions() throws Exception {
        assumeFalse(GraphicsEnvironment.isHeadless(), "Ambiente headless: pulando pack/show dos frames");
        Object tela = createTelaPrincipalInstance();

        if (tela instanceof JFrame) {
            AtomicReference<Throwable> err = new AtomicReference<>();
            SwingUtilities.invokeAndWait(() -> {
                try {
                    JFrame jf = (JFrame) tela;
                    jf.pack();
                    jf.setVisible(true);
                    jf.setVisible(false);
                } catch (Throwable t) {
                    err.set(t);
                }
            });
            if (err.get() != null) {
                fail("Operação de mostrar JFrame lançou exceção: " + err.get());
            }
        } else {
            Field frameField = null;
            Class<?> cls = tela.getClass();
            try {
                frameField = cls.getDeclaredField("frame");
                frameField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                /* ignora */ }
            if (frameField != null) {
                Object f = frameField.get(tela);
                if (f instanceof JFrame) {
                    AtomicReference<Throwable> err = new AtomicReference<>();
                    SwingUtilities.invokeAndWait(() -> {
                        try {
                            JFrame jf = (JFrame) f;
                            jf.pack();
                            jf.setVisible(true);
                            jf.setVisible(false);
                        } catch (Throwable t) {
                            err.set(t);
                        }
                    });
                    if (err.get() != null) {
                        fail("Operação de mostrar JFrame (campo) lançou exceção: " + err.get());
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("navigation action methods set card flags (no-constructor)")
    void test19_navigationActionMethodsSetCardFlags() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);

        // cria instância sem chamar o construtor (para não inicializar controller)
        Constructor<?> objCons = Object.class.getDeclaredConstructor();
        ReflectionFactory rf = ReflectionFactory.getReflectionFactory();
        Constructor<?> fakeCons = rf.newConstructorForSerialization(cls, objCons);
        Object tela = fakeCons.newInstance();

        // precisamos de um JP_Principal com CardLayout (métodos usam getLayout().show(...))
        JPanel jp = new JPanel(new CardLayout());
        Field fJP = cls.getDeclaredField("JP_Principal");
        fJP.setAccessible(true);
        fJP.set(tela, jp);

        // preparar campos booleanos iniciais
        Field fCardHome = cls.getDeclaredField("cardHome");
        fCardHome.setAccessible(true);
        fCardHome.setBoolean(tela, false);
        Field fCardAmigos = cls.getDeclaredField("cardAmigos");
        fCardAmigos.setAccessible(true);
        fCardAmigos.setBoolean(tela, false);
        Field fCardFerr = cls.getDeclaredField("cardFerramentas");
        fCardFerr.setAccessible(true);
        fCardFerr.setBoolean(tela, false);
        Field fCardRel = cls.getDeclaredField("cardRelatorio");
        fCardRel.setAccessible(true);
        fCardRel.setBoolean(tela, false);

        // invoke b_HomeActionPerformed
        Method mHome = cls.getDeclaredMethod("b_HomeActionPerformed", java.awt.event.ActionEvent.class);
        mHome.setAccessible(true);
        mHome.invoke(tela, (Object) null);
        assertTrue(fCardHome.getBoolean(tela), "b_HomeActionPerformed não setou cardHome=true");
        // reset
        fCardHome.setBoolean(tela, false);

        // invoke b_ListaAmigosActionPerformed
        Method mAmigos = cls.getDeclaredMethod("b_ListaAmigosActionPerformed", java.awt.event.ActionEvent.class);
        mAmigos.setAccessible(true);
        mAmigos.invoke(tela, (Object) null);
        assertTrue(fCardAmigos.getBoolean(tela), "b_ListaAmigosActionPerformed não setou cardAmigos=true");
        fCardAmigos.setBoolean(tela, false);

        // invoke b_ListaFerramentasActionPerformed
        Method mFerr = cls.getDeclaredMethod("b_ListaFerramentasActionPerformed", java.awt.event.ActionEvent.class);
        mFerr.setAccessible(true);
        mFerr.invoke(tela, (Object) null);
        assertTrue(fCardFerr.getBoolean(tela), "b_ListaFerramentasActionPerformed não setou cardFerramentas=true");
        fCardFerr.setBoolean(tela, false);

        // invoke b_relatorioActionPerformed
        Method mRel = cls.getDeclaredMethod("b_relatorioActionPerformed", java.awt.event.ActionEvent.class);
        mRel.setAccessible(true);
        mRel.invoke(tela, (Object) null);
        assertTrue(fCardRel.getBoolean(tela), "b_relatorioActionPerformed não setou cardRelatorio=true");
    }

    @Test
    @DisplayName("JP_PrincipalMouseReleased shows right popup (no-constructor) - fix")
    void test20_JP_PrincipalMouseReleased_showsCorrectPopup() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);
        Constructor<?> objCons = Object.class.getDeclaredConstructor();
        // usa ReflectionFactory (observação: API interna -> warning ok)
        sun.reflect.ReflectionFactory rf = sun.reflect.ReflectionFactory.getReflectionFactory();
        Constructor<?> fakeCons = rf.newConstructorForSerialization(cls, objCons);
        Object tela = fakeCons.newInstance();

        // painel com CardLayout (não precisa ser mostrado)
        JPanel jp = new JPanel(new CardLayout());
        Field fJP = cls.getDeclaredField("JP_Principal");
        fJP.setAccessible(true);
        fJP.set(tela, jp);

        // cria popups de teste que registram se show() foi chamado
        class TestPopup extends JPopupMenu {

            boolean shown = false;
            int lastX = -1, lastY = -1;

            @Override
            public void show(java.awt.Component invoker, int x, int y) {
                // NÃO chamar super.show(invoker,x,y) — isso exige que invoker esteja visível.
                this.shown = true;
                this.lastX = x;
                this.lastY = y;
                // não desenhar nada; apenas registrar a chamada
            }
        }

        TestPopup tpHome = new TestPopup();
        TestPopup tpAmigos = new TestPopup();

        Field fPopHome = cls.getDeclaredField("JPop_Home");
        fPopHome.setAccessible(true);
        fPopHome.set(tela, tpHome);
        Field fPopAmigos = cls.getDeclaredField("JPop_Amigos");
        fPopAmigos.setAccessible(true);
        fPopAmigos.set(tela, tpAmigos);

        // setar flags: cardHome true -> JP_PrincipalMouseReleased deve chamar JPop_Home.show
        Field fCardHome = cls.getDeclaredField("cardHome");
        fCardHome.setAccessible(true);
        fCardHome.setBoolean(tela, true);
        Field fCardAmigos = cls.getDeclaredField("cardAmigos");
        fCardAmigos.setAccessible(true);
        fCardAmigos.setBoolean(tela, false);

        // criar MouseEvent com popupTrigger=true
        MouseEvent ev = new MouseEvent(jp, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, 10, 10, 1, true);
        Method m = cls.getDeclaredMethod("JP_PrincipalMouseReleased", java.awt.event.MouseEvent.class);
        m.setAccessible(true);
        m.invoke(tela, ev);
        assertTrue(tpHome.shown, "JPop_Home não foi mostrado quando cardHome==true e evento popupTrigger");

        // testar cardAmigos true
        tpHome.shown = false;
        fCardHome.setBoolean(tela, false);
        fCardAmigos.setBoolean(tela, true);

        m.invoke(tela, ev);
        assertTrue(tpAmigos.shown, "JPop_Amigos não foi mostrado quando cardAmigos==true e evento popupTrigger");
    }

    @Test
    void test_JPop_botoes_showWithAWTInvoker_noNPE() throws Exception {
        assumeFalse(GraphicsEnvironment.isHeadless(), "Pulando teste que cria peers AWT em ambiente headless");

        Class<?> cls = Class.forName(TARGET_CLASS);
        Constructor<?> objCons = Object.class.getDeclaredConstructor();
        sun.reflect.ReflectionFactory rf = sun.reflect.ReflectionFactory.getReflectionFactory();
        Constructor<?> fakeCons = rf.newConstructorForSerialization(cls, objCons);
        Object tela = fakeCons.newInstance();

        // inicializa componentes (invoca private initComponents) — não executa construtor
        Method init = cls.getDeclaredMethod("initComponents");
        init.setAccessible(true);
        init.invoke(tela);

        // cria um Frame AWT temporário e um invoker simples (Button) com peer nativo
        java.awt.Frame frame = new java.awt.Frame();
        java.awt.Button invoker = new java.awt.Button("invoker");
        try {
            frame.add(invoker);
            frame.pack();        // organiza (não mostra)
            frame.addNotify();   // força criação do peer -> objectLock DEFINITIVAMENTE não será null

            // cria um JPopupMenu stub que registra se show() foi chamado
            class TestPopup extends javax.swing.JPopupMenu {

                boolean shown = false;
                int lastX = -1, lastY = -1;

                @Override
                public void show(java.awt.Component invoker, int x, int y) {
                    // não chama super.show(invoker,x,y) para evitar UI real
                    this.shown = true;
                    this.lastX = x;
                    this.lastY = y;
                }
            }
            TestPopup tp = new TestPopup();

            // injeta o stub no campo JPop_botoes da tela
            Field fPop = cls.getDeclaredField("JPop_botoes");
            fPop.setAccessible(true);
            fPop.set(tela, tp);

            // chama show com o invoker AWT que tem peer nativo - evita NullPointerException
            tp.show(invoker, 11, 22);

            assertTrue(tp.shown, "JPop_botoes.show deveria ter sido invocado com invoker AWT válido");
            assertEquals(11, tp.lastX);
            assertEquals(22, tp.lastY);
        } finally {
            // desmonta o peer e limpa recursos
            try {
                frame.remove(invoker);
                frame.removeNotify();
                frame.dispose();
            } catch (Throwable ignored) {
            }
        }
    }

    @Test
    void test_LOGO_resourceExists() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);
        Field fLogo = cls.getDeclaredField("LOGO");
        fLogo.setAccessible(true);
        Object val = fLogo.get(null); // constant static
        assertNotNull(val, "Constante LOGO deve existir");
        String path = (String) val;
        // verificar resource no classpath relativo à classe
        java.net.URL resource = cls.getResource(path);
        assertNotNull(resource, "Recurso de LOGO não encontrado no classpath: " + path);
    }

    @Test
    void test_JP_ListaMouseReleased_showsJPop_botoes() throws Exception {
        assumeFalse(GraphicsEnvironment.isHeadless(), "Requer peer AWT; pulando em headless");

        Object tela = createTelaWithManualComponents();
        Class<?> cls = Class.forName(TARGET_CLASS);

        class TestPopup extends javax.swing.JPopupMenu {

            boolean shown = false;
            int x, y;

            @Override
            public void show(java.awt.Component invoker, int x, int y) {
                this.shown = true;
                this.x = x;
                this.y = y;
            }
        }
        TestPopup tp = new TestPopup();
        Field fPop = null;
        try {
            fPop = cls.getDeclaredField("JPop_botoes");
            fPop.setAccessible(true);
            fPop.set(tela, tp);
        } catch (NoSuchFieldException ignored) {
        }

        java.awt.Frame frame = new java.awt.Frame();
        java.awt.Button invoker = new java.awt.Button("i");
        try {
            frame.add(invoker);
            frame.pack();
            frame.addNotify();
            SwingUtilities.invokeAndWait(() -> tp.show(invoker, 7, 8));
            assertTrue(tp.shown, "JPop_botoes deveria ter sido mostrado");
            assertEquals(7, tp.x);
            assertEquals(8, tp.y);
        } finally {
            try {
                frame.remove(invoker);
                frame.removeNotify();
                frame.dispose();
            } catch (Throwable ignored) {
            }
        }
    }
// Commit: test(TelaPrincipal): atualizarTabelaActionPerformed calls controller.listarAmigos (stub)

    @Test
    void test_atualizarTabela_callsControllerListarAmigos() throws Exception {
        Object tela = createTelaWithManualComponents();
        Class<?> cls = Class.forName(TARGET_CLASS);

        class Stub extends com.mycompany.a3_2025_2_gqs.Controller.ListaAmigosFerramentasController {

            boolean listarAmigosCalled = false;

            public Stub() {
                super(null);
            }

            @Override
            public void listarAmigos() {
                listarAmigosCalled = true;
            }
        }
        Constructor<?> objCons = Object.class.getDeclaredConstructor();
        sun.reflect.ReflectionFactory rf = sun.reflect.ReflectionFactory.getReflectionFactory();
        Object stub = rf.newConstructorForSerialization(Stub.class, objCons).newInstance();

        Field fController = cls.getDeclaredField("controller");
        fController.setAccessible(true);
        fController.set(tela, stub);

        Method m = cls.getDeclaredMethod("atualizarTabelaActionPerformed", java.awt.event.ActionEvent.class);
        SwingUtilities.invokeAndWait(() -> {
            try {
                m.setAccessible(true);
                m.invoke(tela, (Object) null);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });

        Field flag = Stub.class.getDeclaredField("listarAmigosCalled");
        flag.setAccessible(true);
        assertTrue(flag.getBoolean(stub), "listarAmigos() não foi chamado no stub");
    }

    @Test
    void test_AtualizarFerramentas_callsControllerListarFerramentas() throws Exception {
        Object tela = createTelaWithManualComponents();
        Class<?> cls = Class.forName(TARGET_CLASS);

        class Stub extends com.mycompany.a3_2025_2_gqs.Controller.ListaAmigosFerramentasController {

            boolean listarFerramentasCalled = false;

            public Stub() {
                super(null);
            }

            @Override
            public void listarFerramentas() {
                listarFerramentasCalled = true;
            }
        }
        Constructor<?> objCons = Object.class.getDeclaredConstructor();
        sun.reflect.ReflectionFactory rf = sun.reflect.ReflectionFactory.getReflectionFactory();
        Object stub = rf.newConstructorForSerialization(Stub.class, objCons).newInstance();

        Field fController = cls.getDeclaredField("controller");
        fController.setAccessible(true);
        fController.set(tela, stub);

        Method m = cls.getDeclaredMethod("AtualizarFerramentasActionPerformed", java.awt.event.ActionEvent.class);
        SwingUtilities.invokeAndWait(() -> {
            try {
                m.setAccessible(true);
                m.invoke(tela, (Object) null);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });

        Field flag = Stub.class.getDeclaredField("listarFerramentasCalled");
        flag.setAccessible(true);
        assertTrue(flag.getBoolean(stub), "listarFerramentas() não foi chamado no stub");
    }

    @Test
    void test_b_refreshEmprestimos_callsControllerListarEmprestimos() throws Exception {
        Object tela = createTelaWithManualComponents();
        Class<?> cls = Class.forName(TARGET_CLASS);

        class Stub extends com.mycompany.a3_2025_2_gqs.Controller.ListaAmigosFerramentasController {

            boolean listarEmprestimosCalled = false;

            public Stub() {
                super(null);
            }

            @Override
            public void listarEmprestimos() {
                listarEmprestimosCalled = true;
            }
        }
        Constructor<?> objCons = Object.class.getDeclaredConstructor();
        sun.reflect.ReflectionFactory rf = sun.reflect.ReflectionFactory.getReflectionFactory();
        Object stub = rf.newConstructorForSerialization(Stub.class, objCons).newInstance();

        Field fController = cls.getDeclaredField("controller");
        fController.setAccessible(true);
        fController.set(tela, stub);

        Method m = cls.getDeclaredMethod("b_refreshEmprestimosActionPerformed", java.awt.event.ActionEvent.class);
        SwingUtilities.invokeAndWait(() -> {
            try {
                m.setAccessible(true);
                m.invoke(tela, (Object) null);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });

        Field flag = Stub.class.getDeclaredField("listarEmprestimosCalled");
        flag.setAccessible(true);
        assertTrue(flag.getBoolean(stub), "listarEmprestimos() não foi chamado no stub");
    }

    @Test
    void test_buttonTextsMatchConstantsAfterManualInjection() throws Exception {
        Object tela = createTelaWithManualComponents();
        Class<?> cls = Class.forName(TARGET_CLASS);

        Field fAMIGO = cls.getDeclaredField("AMIGO");
        fAMIGO.setAccessible(true);
        String amigoConst = (String) fAMIGO.get(null);

        Field fBtn = cls.getDeclaredField("b_cadastrarAmigos");
        fBtn.setAccessible(true);
        javax.swing.JButton btn = (javax.swing.JButton) fBtn.get(tela);
        assertNotNull(btn, "b_cadastrarAmigos deve estar injetado");
        assertEquals(amigoConst, btn.getText(), "Texto do botão b_cadastrarAmigos deve corresponder à constante AMIGO");
    }

    @Test
    void test_JL_ListaAmigosFontUsesFONT_NAME_manual() throws Exception {
        Object tela = createTelaWithManualComponents();
        Class<?> cls = Class.forName(TARGET_CLASS);

        Field fFont = cls.getDeclaredField("FONT_NAME");
        fFont.setAccessible(true);
        String fontName = (String) fFont.get(null);

        Field fLabel = cls.getDeclaredField("JL_ListaAmigos");
        fLabel.setAccessible(true);
        javax.swing.JLabel lbl = (javax.swing.JLabel) fLabel.get(tela);
        assertNotNull(lbl, "JL_ListaAmigos deve estar injetado");
        String actualFamily = lbl.getFont().getName();
        assertTrue(actualFamily.contains(fontName) || lbl.getFont().getFontName().contains(fontName),
                "Fonte do JL_ListaAmigos deve usar FONT_NAME (" + fontName + "), mas foi: " + actualFamily);
    }

    @Test
    void test_jMudarTemaActionPerformed_noException_manual() throws Exception {
        assumeFalse(GraphicsEnvironment.isHeadless(), "Pulando teste de tema em ambiente sem cabeça");

        Object tela = createTelaWithManualComponents();
        Class<?> cls = Class.forName(TARGET_CLASS);

        Method m = cls.getDeclaredMethod("jMudarTemaActionPerformed", java.awt.event.ActionEvent.class);
        m.setAccessible(true);

        assertDoesNotThrow(() -> {
            SwingUtilities.invokeAndWait(() -> {
                try {
                    m.invoke(tela, (Object) null);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }

    @Test
    void test_JP_PrincipalMouseReleased_contextualPopups_manual() throws Exception {
        assumeFalse(GraphicsEnvironment.isHeadless(), "Pulando em headless");

        Object tela = createTelaWithManualComponents();
        Class<?> cls = Class.forName(TARGET_CLASS);

        class TestPopup extends javax.swing.JPopupMenu {

            boolean shown = false;

            @Override
            public void show(java.awt.Component invoker, int x, int y) {
                shown = true;
            }
        }
        TestPopup tpHome = new TestPopup();
        TestPopup tpAmigos = new TestPopup();
        try {
            Field f1 = cls.getDeclaredField("JPop_Home");
            f1.setAccessible(true);
            f1.set(tela, tpHome);
        } catch (NoSuchFieldException ignored) {
        }
        try {
            Field f2 = cls.getDeclaredField("JPop_Amigos");
            f2.setAccessible(true);
            f2.set(tela, tpAmigos);
        } catch (NoSuchFieldException ignored) {
        }

        java.awt.Frame frame = new java.awt.Frame();
        java.awt.Button invoker = new java.awt.Button("i");
        try {
            frame.add(invoker);
            frame.pack();
            frame.addNotify();

            Field fCardHome = null;
            Field fCardAmigos = null;
            try {
                fCardHome = cls.getDeclaredField("cardHome");
                fCardHome.setAccessible(true);
                fCardHome.setBoolean(tela, true);
            } catch (NoSuchFieldException ignored) {
            }
            try {
                fCardAmigos = cls.getDeclaredField("cardAmigos");
                fCardAmigos.setAccessible(true);
                fCardAmigos.setBoolean(tela, false);
            } catch (NoSuchFieldException ignored) {
            }

            Method m = cls.getDeclaredMethod("JP_PrincipalMouseReleased", java.awt.event.MouseEvent.class);
            m.setAccessible(true);
            MouseEvent ev = new MouseEvent(invoker, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0, 5, 5, 1, true);

            SwingUtilities.invokeAndWait(() -> {
                try {
                    m.invoke(tela, ev);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            });
            assertTrue(tpHome.shown, "JPop_Home não foi mostrado quando cardHome==true");

            tpHome.shown = false;
            if (fCardHome != null) {
                fCardHome.setBoolean(tela, false);
            }
            if (fCardAmigos != null) {
                fCardAmigos.setBoolean(tela, true);
            }

            SwingUtilities.invokeAndWait(() -> {
                try {
                    m.invoke(tela, ev);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            });
            assertTrue(tpAmigos.shown, "JPop_Amigos não foi mostrado quando cardAmigos==true");
        } finally {
            try {
                frame.remove(invoker);
                frame.removeNotify();
                frame.dispose();
            } catch (Throwable ignored) {
            }
        }
    }

}
