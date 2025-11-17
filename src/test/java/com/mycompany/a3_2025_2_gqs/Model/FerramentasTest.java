package com.mycompany.a3_2025_2_gqs.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes para a classe Ferramentas
 */
public class FerramentasTest {

    private Ferramentas ferramenta;

    @BeforeEach
    void setUp() {
        ferramenta = new Ferramentas();
    }

    @Test
    void testConstrutorVazio() {
        Ferramentas f = new Ferramentas();
        assertNotNull(f);
        assertEquals(0, f.getId());
        assertNull(f.getNome());
        assertNull(f.getMarca());
        assertNull(f.getPreco());
        assertEquals(0, f.getEstaEmprestada());
    }

    @Test
    void testConstrutorComNomeMarcaValor() {
        Ferramentas f = new Ferramentas("Martelo", "Tramontina", "29.90");
        assertEquals("Martelo", f.getNome());
        assertEquals("Tramontina", f.getMarca());
        assertEquals("29.90", f.getPreco());
        assertEquals(0, f.getId());
        assertEquals(0, f.getEstaEmprestada());
    }

    @Test
    void testConstrutorCompleto() {
        Ferramentas f = new Ferramentas(1, "Chave de Fenda", "Vonder", "15.50");
        assertEquals(1, f.getId());
        assertEquals("Chave de Fenda", f.getNome());
        assertEquals("Vonder", f.getMarca());
        assertEquals("15.50", f.getPreco());
        assertEquals(0, f.getEstaEmprestada());
    }

    @Test
    void testGetSetId() {
        ferramenta.setId(10);
        assertEquals(10, ferramenta.getId());
        
        ferramenta.setId(0);
        assertEquals(0, ferramenta.getId());
    }

    @Test
    void testGetSetNome() {
        ferramenta.setNome("Serrote");
        assertEquals("Serrote", ferramenta.getNome());
        
        ferramenta.setNome("");
        assertEquals("", ferramenta.getNome());
        
        ferramenta.setNome(null);
        assertNull(ferramenta.getNome());
    }

    @Test
    void testGetSetMarca() {
        ferramenta.setMarca("Bosch");
        assertEquals("Bosch", ferramenta.getMarca());
        
        ferramenta.setMarca("Makita");
        assertEquals("Makita", ferramenta.getMarca());
    }

    @Test
    void testGetSetValor() {
        ferramenta.setValor("99.99");
        assertEquals("99.99", ferramenta.getPreco());
        
        ferramenta.setValor("0.00");
        assertEquals("0.00", ferramenta.getPreco());
    }

    @Test
    void testGetPreco() {
        ferramenta.setValor("50.00");
        String preco = ferramenta.getPreco();
        assertEquals("50.00", preco);
    }

    @Test
    void testGetSetEstaEmprestada() {
        ferramenta.setEstaEmprestada(0);
        assertEquals(0, ferramenta.getEstaEmprestada());
        
        ferramenta.setEstaEmprestada(1);
        assertEquals(1, ferramenta.getEstaEmprestada());
    }

    @Test
    void testTodosOsGettersESetters() {
        ferramenta.setId(2);
        ferramenta.setNome("Alicate");
        ferramenta.setMarca("Gedore");
        ferramenta.setValor("35.90");
        ferramenta.setEstaEmprestada(1);

        assertEquals(2, ferramenta.getId());
        assertEquals("Alicate", ferramenta.getNome());
        assertEquals("Gedore", ferramenta.getMarca());
        assertEquals("35.90", ferramenta.getPreco());
        assertEquals(1, ferramenta.getEstaEmprestada());
    }

    @Test
    void testEstadoInicial() {
        Ferramentas f = new Ferramentas();
        assertEquals(0, f.getEstaEmprestada(), "Estado inicial deve ser 0 (não emprestada)");
    }
}

