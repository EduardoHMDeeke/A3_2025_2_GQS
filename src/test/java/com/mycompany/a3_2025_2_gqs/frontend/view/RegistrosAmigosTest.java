package com.mycompany.a3_2025_2_gqs.frontend.view;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;

/**
@DisplayName("RegistrosAmigos GUI Tests")
public class RegistrosAmigosTest {

    private FrameFixture window;
    private Robot robot;
    private RegistrosAmigos frame;
    private boolean headless;

    @BeforeEach
    public void setUp() {
        headless = GraphicsEnvironment.isHeadless();
        
        if (headless) {
            // Set headless mode properties
            System.setProperty("java.awt.headless", "true");
            System.setProperty("test.mode", "true");
        }
        
        try {
            robot = BasicRobot.robotWithCurrentAwtHierarchy();
            frame = GuiActionRunner.execute(() -> {
                RegistrosAmigos f = new RegistrosAmigos();
                f.setTestMode(true); // Always enable test mode to suppress dialogs
                return f;
            });
            window = new FrameFixture(robot, frame);
            
            if (!headless) {
                window.show(); // Only show if not headless
            } else {
                // In headless mode, we need to manually make the frame "visible" for testing
                frame.setVisible(true);
            }
        } catch (HeadlessException e) {
            System.out.println("Running in headless mode: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Should initialize all UI components correctly")
    public void testInitialState() {
        // Ensure the frame is fully initialized
        if (robot != null) {
            robot.waitForIdle();
        }

        // Force initial validation state
        GuiActionRunner.execute(() -> {
            if (!frame.getTxtnome().getText().isEmpty()) {
                frame.getTxtnome().setText("");
            }
            frame.validarCampos();
        });
        
        if (robot != null) {
            robot.waitForIdle();
        }

        assertThat(frame.isVisible()).isTrue();
        assertThat(frame.isUndecorated()).isTrue();

        // In headless mode, we test components directly without window fixture
        if (!headless && window != null) {
            // Use window fixture only in non-headless mode
            window.label("jLabel1").requireText("Amigos");
            window.textBox("txtnome").requireVisible().requireEmpty().requireEditable();
            window.textBox("txtemail").requireVisible().requireEmpty().requireEditable();
            window.textBox("txttelefone").requireVisible().requireEmpty().requireEditable();
            window.label("labelNome").requireText("Nome");
            window.label("labelEmail").requireText("Email");
            window.label("labelFone").requireText("Telefone");
            window.button("jDeleteAmigo").requireEnabled().requireVisible();
            window.button("b_voltar").requireEnabled().requireVisible();
        } else {
            // Headless mode: test components directly
            assertThat(frame.getjLabel1().getText()).isEqualTo("Amigos");
            assertThat(frame.getTxtnome().isVisible()).isTrue();
            assertThat(frame.getTxtnome().getText()).isEmpty();
            assertThat(frame.getTxtnome().isEnabled()).isTrue();
            assertThat(frame.getTxtemail().isVisible()).isTrue();
            assertThat(frame.getTxtemail().getText()).isEmpty();
            assertThat(frame.getTxttelefone().isVisible()).isTrue();
            assertThat(frame.getTxttelefone().getText()).isEmpty();
            assertThat(frame.getLabelNome().getText()).isEqualTo("Nome");
            assertThat(frame.getLabelEmail().getText()).isEqualTo("Email");
            assertThat(frame.getLabelFone().getText()).isEqualTo("Telefone");
            assertThat(frame.getjDeleteAmigo().isEnabled()).isTrue();
            assertThat(frame.getjDeleteAmigo().isVisible()).isTrue();
            assertThat(frame.getb_voltar().isEnabled()).isTrue();
            assertThat(frame.getb_voltar().isVisible()).isTrue();
        }

        // Test invisible components using direct getters
        assertThat(frame.getUpdate().isEnabled())
            .as("Update button should be enabled initially with empty text field")
            .isTrue();
        assertThat(frame.getUpdate().isVisible())
            .as("Update button should be invisible initially")
            .isFalse();
        assertThat(frame.getjBotaoCadastro().isEnabled())
            .as("Cadastro button should be enabled initially with empty text field")
            .isTrue();
        assertThat(frame.getjBotaoCadastro().isVisible())
            .as("Cadastro button should be invisible initially")
            .isFalse();
        assertThat(frame.getTxtId().isVisible())
            .as("ID field should be invisible")
            .isFalse();
    }

    @Test
    @DisplayName("Should enable buttons when valid name is entered")
    public void testValidarCampos_WithValidName() {
        // Use direct text setting and trigger validation
        GuiActionRunner.execute(() -> {
            frame.getTxtnome().setText("João Silva");
            frame.validarCampos();
        });
        
        if (robot != null) {
            robot.waitForIdle();
        }
        
        // Check button states
        assertThat(frame.getUpdate().isEnabled()).isTrue();
        assertThat(frame.getjBotaoCadastro().isEnabled()).isTrue();
    }

    @Test
    @DisplayName("Should disable buttons when name contains numbers")
    public void testValidarCampos_WithInvalidName() {
        // Enable test mode to suppress JOptionPane
        frame.setTestMode(true);

        GuiActionRunner.execute(() -> {
            frame.getTxtnome().setText("João123");
            frame.validarCampos();
        });

        if (robot != null) {
            robot.waitForIdle();
        }

        // Check button states
        assertThat(frame.getUpdate().isEnabled()).isFalse();
        assertThat(frame.getjBotaoCadastro().isEnabled()).isFalse();
    }

    @Test
    @DisplayName("Should handle component name assignment correctly")
    public void testComponentNamesAreSet() {
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
        if (window != null) {
            try {
                window.cleanUp();
            } catch (Exception e) {
                // Ignore cleanup errors in headless mode
            }
        }
        if (frame != null) {
            frame.dispose();
        }
    }
} */