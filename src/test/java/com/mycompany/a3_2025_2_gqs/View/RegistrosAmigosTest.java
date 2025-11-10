package com.mycompany.a3_2025_2_gqs.View;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import java.awt.GraphicsEnvironment;

@DisplayName("RegistrosAmigos GUI Tests")
@EnabledIfEnvironmentVariable(named = "DISPLAY", matches = ".*", disabledReason = "GUI tests require display")
public class RegistrosAmigosTest {

    private FrameFixture window;
    private Robot robot;
    private RegistrosAmigos frame;

    @BeforeEach
    public void setUp() {
        // Skip test setup if running in headless environment
        if (GraphicsEnvironment.isHeadless()) {
            return;
        }
        
        robot = BasicRobot.robotWithCurrentAwtHierarchy();
        frame = GuiActionRunner.execute(() -> new RegistrosAmigos());
        window = new FrameFixture(robot, frame);
        window.show();
    }

    @Test
    @DisplayName("Should initialize all UI components correctly")
    public void testInitialState() {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Skipping GUI test in headless environment");
            return;
        }

        // Ensure the frame is fully initialized
        robot.waitForIdle();
        
        // Force initial validation state
        GuiActionRunner.execute(() -> {
            if (!frame.getTxtnome().getText().isEmpty()) {
                frame.getTxtnome().setText("");
            }
            frame.validarCampos();
        });
        robot.waitForIdle();
        
        assertThat(frame.isVisible()).isTrue();
        assertThat(frame.isUndecorated()).isTrue();
        
        // Test visible components with window fixture
        window.label("jLabel1").requireText("Amigos");
        window.textBox("txtnome").requireVisible().requireEmpty().requireEditable();
        window.textBox("txtemail").requireVisible().requireEmpty().requireEditable();
        window.textBox("txttelefone").requireVisible().requireEmpty().requireEditable();
        
        window.label("labelNome").requireText("Nome");
        window.label("labelEmail").requireText("Email");
        window.label("labelFone").requireText("Telefone");
        
        window.button("jDeleteAmigo").requireEnabled().requireVisible();
        window.button("b_voltar").requireEnabled().requireVisible();
        
        // Test invisible components using direct getters
        assertThat(frame.getUpdate().isEnabled()).isFalse();
        assertThat(frame.getUpdate().isVisible()).isFalse();
        
        assertThat(frame.getjBotaoCadastro().isEnabled()).isFalse();
        assertThat(frame.getjBotaoCadastro().isVisible()).isFalse();
        
        assertThat(frame.getTxtId().isVisible()).isFalse();
    }

    @Test
    @DisplayName("Should enable buttons when valid name is entered")
    public void testValidarCampos_WithValidName() {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Skipping GUI test in headless environment");
            return;
        }

        // Use direct text setting and trigger validation
        GuiActionRunner.execute(() -> {
            frame.getTxtnome().setText("João Silva");
            frame.validarCampos();
        });
        
        robot.waitForIdle();
        
        // Check button states
        assertThat(frame.getUpdate().isEnabled()).isTrue();
        assertThat(frame.getjBotaoCadastro().isEnabled()).isTrue();
    }

    @Test
    @DisplayName("Should disable buttons when name contains numbers")
    public void testValidarCampos_WithInvalidName() {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Skipping GUI test in headless environment");
            return;
        }

        // Enable test mode to suppress JOptionPane
        frame.setTestMode(true);
        
        GuiActionRunner.execute(() -> {
            frame.getTxtnome().setText("João123");
            frame.validarCampos();
        });
        
        robot.waitForIdle();
        
        // Check button states
        assertThat(frame.getUpdate().isEnabled()).isFalse();
        assertThat(frame.getjBotaoCadastro().isEnabled()).isFalse();
    }

    @Test
    @DisplayName("Should handle component name assignment correctly")
    public void testComponentNamesAreSet() {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Skipping GUI test in headless environment");
            return;
        }

        // Verify all components have names set
        assertThat(frame.getTxtnome().getName()).isNotNull();
        assertThat(frame.getTxtemail().getName()).isNotNull();
        assertThat(frame.getTxttelefone().getName()).isNotNull();
        assertThat(frame.getTxtId().getName()).isNotNull();
        assertThat(frame.getUpdate().getName()).isNotNull();
        assertThat(frame.getjBotaoCadastro().getName()).isNotNull();
        assertThat(frame.getjDeleteAmigo().getName()).isNotNull();
        assertThat(frame.getb_voltar().getName()).isNotNull();
    }

    @AfterEach
    public void tearDown() {
        if (window != null && !GraphicsEnvironment.isHeadless()) {
            window.cleanUp();
        }
    }
}