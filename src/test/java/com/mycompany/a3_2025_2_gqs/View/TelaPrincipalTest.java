package com.mycompany.a3_2025_2_gqs.View;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TelaPrincipalTest {

    private static final String TARGET_CLASS = "com.mycompany.a3_2025_2_gqs.View.TelaPrincipal";

    @BeforeAll
    static void beforeAll() {
        System.setProperty("java.awt.headless", "true");
    }

    @Test
    void classShouldBeLoadable() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);
        assertNotNull(cls);
    }

    @Test
    void shouldDeclareExpectedUiFields() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);
        Set<String> declared = new HashSet<>();
        for (Field f : cls.getDeclaredFields()) {
            declared.add(f.getName());
        }

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
            "JP_Home"
        };

        for (String name : expected) {
            assertTrue(declared.contains(name),
                    "Expected field not found: " + name);
        }
    }

    @Test
    void controllerFieldShouldBeTransientAndFinal() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);

        Field controller = null;
        for (Field f : cls.getDeclaredFields()) {
            if (f.getName().equals("controller")) {
                controller = f;
            }
        }

        assertNotNull(controller, "controller field must exist");

        int mods = controller.getModifiers();

        assertTrue(Modifier.isTransient(mods), "controller must be transient");
        assertTrue(Modifier.isFinal(mods), "controller must be final");
        assertFalse(Modifier.isStatic(mods), "controller must NOT be static");
    }

    @Test
    void fontNameConstantShouldMatchExpected() throws Exception {
        Class<?> cls = Class.forName(TARGET_CLASS);

        Field fontField = null;
        for (Field f : cls.getDeclaredFields()) {
            if (f.getName().equals("FONT_NAME")) {
                fontField = f;
            }
        }

        assertNotNull(fontField, "FONT_NAME constant must exist");

        fontField.setAccessible(true);
        Object value = fontField.get(null);

        assertEquals("Segoe UI Black", value);
    }

}
