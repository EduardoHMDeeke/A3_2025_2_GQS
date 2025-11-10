package com.mycompany.a3_2025_2_gqs.View;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import java.awt.GraphicsEnvironment;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RegistrosAmigos Validation Tests")
@EnabledIf("isGuiTestSupported")
class RegistrosAmigosValidationTest {

    private RegistrosAmigos view;

    private static boolean isGuiTestSupported() {
        return !GraphicsEnvironment.isHeadless();
    }

    @BeforeEach
    void setUp() {
        view = new RegistrosAmigos();
        view.setTestMode(true);
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