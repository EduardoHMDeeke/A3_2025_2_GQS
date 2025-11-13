
package com.mycompany.a3_2025_2_gqs.View;


import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class TelaPrincipalTest {

    @BeforeAll
    static void beforeAll() {
        
        System.setProperty("java.awt.headless", "true");
    }

    @Test
    void classShouldBeLoadable() throws ClassNotFoundException {
        
        Class<?> cls = Class.forName("com.mycompany.a3_2025_2_gqs.View.TelaPrincipal");
        assertNotNull(cls, "TelaPrincipal should be present on the classpath");
    }
}

