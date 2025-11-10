package com.mycompany.a3_2025_2_gqs.View;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

@DisplayName("RegistrosAmigos Validation Tests")
public class RegistrosAmigosValidationTest {

    private RegistrosAmigos view;

    @BeforeEach
    void setUp() {
        view = new RegistrosAmigos();
        view.setTestMode(true); // Enable test mode to suppress JOptionPane
    }

    @Test
    @DisplayName("Should enable buttons for valid name without numbers")
    void testValidarCampos_ValidName() {
        // Arrange
        view.getTxtnome().setText("João da Silva");
        
        // Act
        view.validarCampos();
        
        // Assert
        assertTrue(view.getUpdate().isEnabled(), 
            "Update button should be enabled for valid name without numbers");
        assertTrue(view.getjBotaoCadastro().isEnabled(), 
            "Cadastro button should be enabled for valid name without numbers");
    }

    @Test
    @DisplayName("Should disable buttons for name with numbers")
    void testValidarCampos_InvalidNameWithNumbers() {
        // Arrange
        view.getTxtnome().setText("João123");
        
        // Act
        view.validarCampos();
        
        // Assert
        assertFalse(view.getUpdate().isEnabled(), 
            "Update button should be disabled for name with numbers");
        assertFalse(view.getjBotaoCadastro().isEnabled(), 
            "Cadastro button should be disabled for name with numbers");
    }

    @Test
    @DisplayName("Should handle empty name field")
    void testValidarCampos_EmptyName() {
        // Arrange
        view.getTxtnome().setText("");
        
        // Act
        view.validarCampos();
        
        // Assert
        assertTrue(view.getUpdate().isEnabled(), 
            "Update button should be enabled for empty name field");
        assertTrue(view.getjBotaoCadastro().isEnabled(), 
            "Cadastro button should be enabled for empty name field");
    }

    @Test
    @DisplayName("Should handle null name field")
    void testValidarCampos_NullName() {
        // Arrange
        view.getTxtnome().setText(null);
        
        // Act
        view.validarCampos();
        
        // Assert
        assertTrue(view.getUpdate().isEnabled(), 
            "Update button should be enabled for null name field");
        assertTrue(view.getjBotaoCadastro().isEnabled(),
            "Cadastro button should be enabled for null name field");
    }

    @Test
    @DisplayName("Should handle names with special characters")
    void testValidarCampos_NameWithSpecialCharacters() {
        // Arrange
        view.getTxtnome().setText("João D'Ávila");
        
        // Act
        view.validarCampos();
        
        // Assert
        assertTrue(view.getUpdate().isEnabled(), 
            "Update button should be enabled for name with special characters");
        assertTrue(view.getjBotaoCadastro().isEnabled(), 
            "Cadastro button should be enabled for name with special characters");
    }

    @Test
    @DisplayName("Should handle names with spaces")
    void testValidarCampos_NameWithSpaces() {
        // Arrange
        view.getTxtnome().setText("Maria da Silva Santos");
        
        // Act
        view.validarCampos();
        
        // Assert
        assertTrue(view.getUpdate().isEnabled(), 
            "Update button should be enabled for name with spaces");
        assertTrue(view.getjBotaoCadastro().isEnabled(), 
            "Cadastro button should be enabled for name with spaces");
    }

    @Test
    @DisplayName("Should handle names with only numbers")
    void testValidarCampos_NameWithOnlyNumbers() {
        // Arrange
        view.getTxtnome().setText("123456");
        
        // Act
        view.validarCampos();
        
        // Assert
        assertFalse(view.getUpdate().isEnabled(), 
            "Update button should be disabled for name with only numbers");
        assertFalse(view.getjBotaoCadastro().isEnabled(), 
            "Cadastro button should be disabled for name with only numbers");
    }

    @Test
    @DisplayName("Should handle mixed valid and invalid characters")
    void testValidarCampos_MixedCharacters() {
        // Arrange
        view.getTxtnome().setText("João123Silva");
        
        // Act
        view.validarCampos();
        
        // Assert
        assertFalse(view.getUpdate().isEnabled(), 
            "Update button should be disabled for name with mixed valid and invalid characters");
        assertFalse(view.getjBotaoCadastro().isEnabled(), 
            "Cadastro button should be disabled for name with mixed valid and invalid characters");
    }

    @Test
    @DisplayName("Should verify initial state after construction")
    void testInitialState() {
        // Act - Ensure validation runs on initial state
        view.validarCampos();
        
        // Assert
        assertTrue(view.getUpdate().isEnabled(), 
            "Update button should be enabled initially");
        assertTrue(view.getjBotaoCadastro().isEnabled(), 
            "Cadastro button should be enabled initially");
        assertFalse(view.getUpdate().isVisible(), 
            "Update button should be invisible initially");
        assertFalse(view.getjBotaoCadastro().isVisible(), 
            "Cadastro button should be invisible initially");
    }

    @Test
    @DisplayName("Should handle very long valid name")
    void testValidarCampos_VeryLongValidName() {
        // Arrange
        String longName = "João".repeat(50); // Create a very long name
        view.getTxtnome().setText(longName);
        
        // Act
        view.validarCampos();
        
        // Assert
        assertTrue(view.getUpdate().isEnabled(), 
            "Update button should be enabled for very long valid name");
        assertTrue(view.getjBotaoCadastro().isEnabled(), 
            "Cadastro button should be enabled for very long valid name");
    }

    @Test
    @DisplayName("Should handle name change from valid to invalid")
    void testValidarCampos_NameChangeValidToInvalid() {
        // Arrange - Start with valid name
        view.getTxtnome().setText("João Silva");
        view.validarCampos();
        
        // Act - Change to invalid name
        view.getTxtnome().setText("João123");
        view.validarCampos();
        
        // Assert
        assertFalse(view.getUpdate().isEnabled(), 
            "Update button should be disabled after changing from valid to invalid name");
        assertFalse(view.getjBotaoCadastro().isEnabled(), 
            "Cadastro button should be disabled after changing from valid to invalid name");
    }

    @Test
    @DisplayName("Should handle name change from invalid to valid")
    void testValidarCampos_NameChangeInvalidToValid() {
        // Arrange - Start with invalid name
        view.getTxtnome().setText("João123");
        view.validarCampos();
        
        // Act - Change to valid name
        view.getTxtnome().setText("Maria Santos");
        view.validarCampos();
        
        // Assert
        assertTrue(view.getUpdate().isEnabled(), 
            "Update button should be enabled after changing from invalid to valid name");
        assertTrue(view.getjBotaoCadastro().isEnabled(), 
            "Cadastro button should be enabled after changing from invalid to valid name");
    }

    @Test
    @DisplayName("Should verify test mode suppresses JOptionPane")
    void testTestModeSuppressesJOptionPane() {
        // Arrange
        view.setTestMode(true);
        view.getTxtnome().setText("Invalid123");
        
        // Act & Assert - Should not throw exception or show dialog
        assertDoesNotThrow(() -> view.validarCampos(),
            "validarCampos should not throw exception when test mode is enabled");
    }
}