package com.mycompany.a3_2025_2_gqs.View;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OpcoesTest {

    private Opcoes tela;

    @BeforeEach
    public void setup() throws Exception {
        // Swing deve ser criado no EDT
        EventQueue.invokeAndWait(() -> {
            tela = new Opcoes();
            tela.setVisible(false); // Talvez isso funcione?  não sei.
        });
    }

    @AfterEach
    public void cleanup() throws Exception {
        if (tela != null) {
            EventQueue.invokeAndWait(() -> tela.dispose());
        }
    }

    @Test
    public void testTelaCriaSemErros() {
        assertNotNull(tela);
    }

    @Test
    public void testComponentesNaoNulos() {
        assertNotNull(getField(tela, "b_sair"));
        assertNotNull(getField(tela, "jButton1"));
        assertNotNull(getField(tela, "jLabel1"));
    }

    @Test
    public void testBotaoVoltarChamaDispose() throws Exception {
        JButton botaoVoltar = (JButton) getField(tela, "jButton1");
        assertNotNull(botaoVoltar);

        assertTrue(tela.isDisplayable());

        EventQueue.invokeAndWait(() ->
            botaoVoltar.getActionListeners()[0]
                .actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""))
        );

        assertFalse(tela.isDisplayable());
    }

    @Test
    public void testBotaoSairExiste() {
        var botaoSair = (JButton) getField(tela, "b_sair");
        assertNotNull(botaoSair);
    }

    private Object getField(Object obj, String name) {
        try {
            var f = obj.getClass().getDeclaredField(name);
            f.setAccessible(true);
            return f.get(obj);
        } catch (Exception e) {
            return null;
        }
    }
}
