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

    @Test
    void testConstrutorSemIdade() {
        Amigos a = new Amigos("Maria Santos", "maria@email.com", "11888888888");
        assertEquals("Maria Santos", a.getNome());
        assertEquals("maria@email.com", a.getEmail());
        assertEquals("11888888888", a.getTelefone());
        assertEquals(0, a.getIdade());
    }
    
    @Test
    void testGetSetId() {
        amigo.setId(1);
        assertEquals(1, amigo.getId());
        
        amigo.setId(100);
        assertEquals(100, amigo.getId());
    }

    @Test
    void testGetSetNome() {
        amigo.setNome("Pedro");
        assertEquals("Pedro", amigo.getNome());
        
        amigo.setNome("");
        assertEquals("", amigo.getNome());
        
        amigo.setNome(null);
        assertNull(amigo.getNome());
    }

    @Test
    void testGetSetEmail() {
        amigo.setEmail("pedro@email.com");
        assertEquals("pedro@email.com", amigo.getEmail());
        
        amigo.setEmail("teste@teste.com.br");
        assertEquals("teste@teste.com.br", amigo.getEmail());
    }

    @Test
    void testGetSetTelefone() {
        amigo.setTelefone("11987654321");
        assertEquals("11987654321", amigo.getTelefone());
        
        amigo.setTelefone("(11) 98765-4321");
        assertEquals("(11) 98765-4321", amigo.getTelefone());
    }
}

