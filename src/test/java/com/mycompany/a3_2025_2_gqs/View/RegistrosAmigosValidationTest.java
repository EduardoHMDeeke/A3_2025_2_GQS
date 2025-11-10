package com.mycompany.a3_2025_2_gqs.View;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import java.awt.GraphicsEnvironment;

@DisplayName("RegistrosAmigos Validation Tests")
@EnabledIfEnvironmentVariable(named = "DISPLAY", matches = ".*", disabledReason = "GUI tests require display")
public class RegistrosAmigosValidationTest {

    private RegistrosAmigos view;

    @BeforeEach
    void setUp() {
        // Skip test setup if running in headless environment
        if (GraphicsEnvironment.isHeadless()) {
            return;
        }
        view = new RegistrosAmigos();
        view.setTestMode(true);
    }

    @Test
    @DisplayName("Should enable buttons for valid name without numbers")
    void testValidarCampos_ValidName() {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Skipping GUI test in headless environment");
            return;
        }
        
        view.getTxtnome().setText("João da Silva");
        view.validarCampos();
        
        assertTrue(view.getUpdate().isEnabled());
        assertTrue(view.getjBotaoCadastro().isEnabled());
    }

    @Test
    @DisplayName("Should disable buttons for name with numbers")
    void testValidarCampos_InvalidNameWithNumbers() {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Skipping GUI test in headless environment");
            return;
        }
        
        view.getTxtnome().setText("João123");
        view.validarCampos();
        
        assertFalse(view.getUpdate().isEnabled());
        assertFalse(view.getjBotaoCadastro().isEnabled());
    }

    @Test
    @DisplayName("Should handle empty name field")
    void testValidarCampos_EmptyName() {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Skipping GUI test in headless environment");
            return;
        }
        
        view.getTxtnome().setText("");
        view.validarCampos();
        
        assertFalse(view.getUpdate().isEnabled());
        assertFalse(view.getjBotaoCadastro().isEnabled());
    }

    @Test
    @DisplayName("Should handle null name field")
    void testValidarCampos_NullName() {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Skipping GUI test in headless environment");
            return;
        }
        
        view.getTxtnome().setText(null);
        view.validarCampos();
        
        assertFalse(view.getUpdate().isEnabled());
        assertFalse(view.getjBotaoCadastro().isEnabled());
    }
}