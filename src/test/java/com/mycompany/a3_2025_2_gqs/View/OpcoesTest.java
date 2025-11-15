package com.mycompany.a3_2025_2_gqs.View;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OpcoesTest {

    private Opcoes tela;

    @BeforeEach
    public void setup() {
        // Força permitir Swing mesmo em ambiente headless
        System.setProperty("java.awt.headless", "false");

        tela = new Opcoes();
    }

    @AfterEach
    public void cleanup() {
        if (tela != null) tela.dispose();
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
    public void testBotaoVoltarChamaDispose() {
        var botaoVoltar = (javax.swing.JButton) getField(tela, "jButton1");
        assertNotNull(botaoVoltar);

        assertTrue(tela.isDisplayable());

        botaoVoltar.getActionListeners()[0]
            .actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""));

        assertFalse(tela.isDisplayable());
    }

    @Test
    public void testBotaoSairExiste() {
        var botaoSair = (javax.swing.JButton) getField(tela, "b_sair");
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
