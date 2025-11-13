package com.mycompany.a3_2025_2_gqs.frontend.view;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.awt.GraphicsEnvironment;

import com.mycompany.a3_2025_2_gqs.frontend.view.RegistrosAmigos;
import com.mycompany.a3_2025_2_gqs.frontend.view.XvfbDisplayManager;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RegistrosAmigos Validation Tests")
class RegistrosAmigosValidationTest {

    private RegistrosAmigos view;
    private static boolean xvfbEnabled = false;

    @BeforeAll
    static void setUpXvfb() {
        // Try to start Xvfb if we're in headless mode
        if (GraphicsEnvironment.isHeadless()) {
            xvfbEnabled = XvfbDisplayManager.startXvfb();
            if (xvfbEnabled) {
                // Reset headless state after Xvfb starts
                System.setProperty("java.awt.headless", "false");
            }
        }
    }
    
    @AfterAll
    static void tearDownXvfb() {
        if (xvfbEnabled) {
            XvfbDisplayManager.stopXvfb();
        }
    }

    private static boolean isGuiTestSupported() {
        // If Xvfb is enabled, we can run GUI tests
        if (xvfbEnabled) {
            return true;
        }
        return !GraphicsEnvironment.isHeadless();
    }

    @BeforeEach
    void setUp() {
        // Skip tests if we're truly headless and Xvfb is not available
        if (isGuiTestSupported()) {
            view = new RegistrosAmigos();
            view.setTestMode(true);
        } else {
            org.junit.jupiter.api.Assumptions.assumeFalse(true, 
                "Skipping GUI test: headless environment and Xvfb not available");
        }
    }

    @Test
    @DisplayName("Should enable buttons for valid name without numbers")
    void testValidarCampos_ValidName() {
        view.getTxtnome().setText("João da Silva");
        view.validarCampos();
        
        assertTrue(view.getUpdate().isEnabled());
        assertTrue(view.getjBotaoCadastro().isEnabled());
    }

    @ParameterizedTest
    @DisplayName("Should disable buttons for invalid names (null, empty, or with numbers)")
    @ValueSource(strings = {"João123", "123456", "Test123Test"}) // Names with numbers
    void testValidarCampos_WithInvalidNames(String invalidNameInput) {
        view.getTxtnome().setText(invalidNameInput);
        view.validarCampos();
        
        assertFalse(view.getUpdate().isEnabled(), 
            "Update button should be disabled for input: " + invalidNameInput);
        assertFalse(view.getjBotaoCadastro().isEnabled(), 
            "Cadastro button should be disabled for input: " + invalidNameInput);
    }

    @Test
    @DisplayName("Should enable buttons for empty and null names")
    void testValidarCampos_WithEmptyAndNullNames() {
        // Test empty string
        view.getTxtnome().setText("");
        view.validarCampos();
        assertTrue(view.getUpdate().isEnabled(), "Update should be enabled for empty string");
        assertTrue(view.getjBotaoCadastro().isEnabled(), "Cadastro should be enabled for empty string");
        
        // Test null
        view.getTxtnome().setText(null);
        view.validarCampos();
        assertTrue(view.getUpdate().isEnabled(), "Update should be enabled for null");
        assertTrue(view.getjBotaoCadastro().isEnabled(), "Cadastro should be enabled for null");
    }
}
