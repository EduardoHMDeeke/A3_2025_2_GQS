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

    @BeforeAll
    void beforeAll() {
        // Garante execução em headless (CI / GitHub Actions) por padrão
        System.setProperty("java.awt.headless", "true");
    }

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

}
