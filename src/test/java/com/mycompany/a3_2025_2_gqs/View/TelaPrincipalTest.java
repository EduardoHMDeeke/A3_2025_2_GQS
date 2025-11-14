package com.mycompany.a3_2025_2_gqs.View;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
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
        Field[] fields = cls.getDeclaredFields();
        Set<String> declared = new HashSet<>();
        for (Field f : fields) declared.add(f.getName());

        // Campos principais detectados no código da TelaPrincipal
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
                    "Expected field not found in TelaPrincipal: " + name);
        }
    }
}
