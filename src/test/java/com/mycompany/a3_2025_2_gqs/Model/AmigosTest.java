package com.mycompany.a3_2025_2_gqs.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes para a classe Amigos
 */
public class AmigosTest {

    private Amigos amigo;

    @BeforeEach
    void setUp() {
        amigo = new Amigos();
    }

    @Test
    void testConstrutorVazio() {
        Amigos a = new Amigos();
        assertNotNull(a);
        assertEquals(0, a.getId());
        assertNull(a.getNome());
        assertNull(a.getEmail());
        assertNull(a.getTelefone());
        assertEquals(0, a.getIdade());
        assertNull(a.getDiaDoEmprestimo());
    }

    @Test
    void testConstrutorComTodosParametros() {
        Amigos a = new Amigos("João Silva", "joao@email.com", "11999999999", 25);
        assertEquals("João Silva", a.getNome());
        assertEquals("joao@email.com", a.getEmail());
        assertEquals("11999999999", a.getTelefone());
        assertEquals(25, a.getIdade());
    }
    
}

