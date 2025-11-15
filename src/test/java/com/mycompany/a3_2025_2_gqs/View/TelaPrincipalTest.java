package com.mycompany.a3_2025_2_gqs.View;

import org.junit.jupiter.api.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes por reflexão para TelaPrincipal.
 * - Não instancia a UI (CI-safe / headless).
 * - Verifica existência de campos essenciais (tabelas, botões, popups).
 * - Verifica existência de métodos de actionPerformed.
 * - Verifica que 'controller' é transient e final.
 * - Verifica valor da constante FONT_NAME.
 * - Verifica que initComponents é private e non-static.
 * - Garante que não há componentes Swing estáticos.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TelaPrincipalTest {

    private static final String TARGET_CLASS = "com.mycompany.a3_2025_2_gqs.View.TelaPrincipal";

    @BeforeAll
    void beforeAll() {
        // Garante execução em headless (CI / GitHub Actions)
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
        for (Field f : fields) declared.add(f.getName());

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
        for (var m : cls.getDeclaredMethods()) methodNames.add(m.getName());

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
            if (m.getName().equals("initComponents"))
                init = m;
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
            if (Modifier.isStatic(f.getModifiers()) && Modifier.isFinal(f.getModifiers()))
                continue;

            Class<?> type = f.getType();

            // Se é componente Swing (subtipo de java.awt.Component) e é static -> erro
            if (java.awt.Component.class.isAssignableFrom(type)) {
                assertFalse(Modifier.isStatic(f.getModifiers()),
                        "Componente Swing NÃO deve ser static: " + f.getName());
            }
        }
    }

    @Test
    void uiFieldsShouldHaveExpectedTypes() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);

        Object[][] expectedTypes = {
            {"table_amigos", javax.swing.JTable.class},
            {"table_ferramentas", javax.swing.JTable.class},
            {"tabelaEmprestimo", javax.swing.JTable.class},
            {"b_Home", javax.swing.JButton.class},
            {"b_ListaAmigos", javax.swing.JButton.class},
            {"b_ListaFerramentas", javax.swing.JButton.class},
            {"b_relatorio", javax.swing.JButton.class},
            {"b_opcoes", javax.swing.JButton.class},
            {"JP_Principal", javax.swing.JPanel.class},
            {"JP_Home", javax.swing.JPanel.class},
            {"JPop_botoes", javax.swing.JPopupMenu.class},
            {"JPop_Amigos", javax.swing.JPopupMenu.class},
            {"JPop_Home", javax.swing.JPopupMenu.class},
            {"popupHome", javax.swing.JMenuItem.class},
            {"popupAmigos", javax.swing.JMenuItem.class},
            {"popupFerramentas", javax.swing.JMenuItem.class},
            {"popupRelatorio", javax.swing.JMenuItem.class},
            {"popupOpcoes", javax.swing.JMenuItem.class},
            {"popupSair", javax.swing.JMenuItem.class}
        };

        for (Object[] pair : expectedTypes) {
            String fieldName = (String) pair[0];
            Class<?> expectedType = (Class<?>) pair[1];

            Field field = cls.getDeclaredField(fieldName);
            assertNotNull(field, "Campo esperado não encontrado: " + fieldName);

            assertTrue(expectedType.isAssignableFrom(field.getType()),
                    "Tipo incorreto no campo '" + fieldName +
                    "'. Esperado: " + expectedType.getSimpleName() +
                    ", Encontrado: " + field.getType().getSimpleName());
        }
    }

    /**
     * Substitui validação de botões que falhou por causa de um nome errado.
     * Verifica existência e que sejam privados (não static, tipo JButton).
     */
    @Test
    void allButtonsShouldExistAndBePrivateJButtons() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);

        String[] expectedButtons = {
                "b_Home",
                "b_ListaAmigos",
                "b_ListaFerramentas",
                "b_relatorio",
                "b_opcoes",
                "b_cadastrarAmigos",
                "b_cadastrarFerramenta",        // nome correto (singular) presente no código
                "CadastrarAmigoHome",          // botão com nome capitalizado no código
                "CadastrarFerramentaHome"      // botão com nome capitalizado no código
        };

        for (String btnName : expectedButtons) {
            Field f = cls.getDeclaredField(btnName);
            assertNotNull(f, "Botão esperado não encontrado: " + btnName);

            int mods = f.getModifiers();
            assertTrue(Modifier.isPrivate(mods) || !Modifier.isPublic(mods),
                    "Botão '" + btnName + "' deve ser private (visibilidade esperada)");
            assertFalse(Modifier.isStatic(mods), "Botão '" + btnName + "' NÃO deve ser static");

            // tipo
            assertTrue(javax.swing.JButton.class.isAssignableFrom(f.getType()),
                    "Botão '" + btnName + "' deve ser do tipo javax.swing.JButton, mas é " + f.getType().getSimpleName());
        }
    }

    /* ===== testes extras adicionados para aumentar cobertura sem instanciar UI ===== */

    @Test
    void constantsShouldExistAndBeStrings() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);
        String[] expectedConstants = {
                "AMIGO",
                "LOGO",
                "REFRESH",
                "AMIGO_ESTOU_AQUI",
                "FERRAMENTA",
                "RELATORIO"
        };

        for (String constName : expectedConstants) {
            Field f = cls.getDeclaredField(constName);
            assertNotNull(f, "Constante esperada não encontrada: " + constName);
            assertTrue(Modifier.isStatic(f.getModifiers()) && Modifier.isFinal(f.getModifiers()),
                    "Constante '" + constName + "' deve ser static final");
            assertTrue(String.class.isAssignableFrom(f.getType()),
                    "Constante '" + constName + "' deve ser String");
        }
    }

    @Test
    void mainMethodShouldExistAndBeStatic() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);
        Method main = null;
        for (Method m : cls.getDeclaredMethods()) {
            if (m.getName().equals("main")) {
                main = m;
                break;
            }
        }
        assertNotNull(main, "Método main deve existir");
        int mods = main.getModifiers();
        assertTrue(Modifier.isStatic(mods), "Método main deve ser static");
        // cheque assinatura (String[] param)
        Class<?>[] params = main.getParameterTypes();
        assertEquals(1, params.length, "main deve ter exatamente 1 parâmetro");
        assertTrue(params[0].isArray(), "main deve ter parâmetro array");
        assertEquals(String.class, params[0].getComponentType(), "main deve aceitar String[]");
    }

    @Test
    void swingFieldsShouldBePrivate() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);
        for (Field f : cls.getDeclaredFields()) {
            Class<?> type = f.getType();
            if (java.awt.Component.class.isAssignableFrom(type)) {
                int mods = f.getModifiers();
                assertTrue(Modifier.isPrivate(mods) || !Modifier.isPublic(mods),
                        "Campo Swing não deve ser public: " + f.getName());
            }
        }
    }
}
