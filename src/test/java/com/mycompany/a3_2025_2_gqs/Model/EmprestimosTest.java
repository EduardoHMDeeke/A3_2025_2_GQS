package com.mycompany.a3_2025_2_gqs.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes para a classe Emprestimos
 */
public class EmprestimosTest {

    private Emprestimos emprestimo;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    @BeforeEach
    void setUp() {
        emprestimo = new Emprestimos();
        dataEmprestimo = LocalDate.of(2024, 1, 15);
        dataDevolucao = LocalDate.of(2024, 1, 22);
    }

    @Test
    void testConstrutorVazio() {
        Emprestimos e = new Emprestimos();
        assertNotNull(e);
        assertEquals(0, e.getId());
        assertEquals(0, e.getIdAmigos());
        assertEquals(0, e.getIdFerramentas());
        assertNull(e.getDataEmprestimo());
        assertNull(e.getDataDevolucao());
        assertEquals(0, e.getEstaEmprestada());
        assertNull(e.getDataDevolvida());
    }

    @Test
    void testConstrutorComId() {
        Emprestimos e = new Emprestimos(1, 10, 20, dataEmprestimo, dataDevolucao);
        assertEquals(1, e.getId());
        assertEquals(10, e.getIdAmigos());
        assertEquals(20, e.getIdFerramentas());
        assertEquals(dataEmprestimo, e.getDataEmprestimo());
        assertEquals(dataDevolucao, e.getDataDevolucao());
    }

    @Test
    void testConstrutorSemId() {
        Emprestimos e = new Emprestimos(5, 15, dataEmprestimo, dataDevolucao, 1);
        assertEquals(0, e.getId());
        assertEquals(5, e.getIdAmigos());
        assertEquals(15, e.getIdFerramentas());
        assertEquals(dataEmprestimo, e.getDataEmprestimo());
        assertEquals(dataDevolucao, e.getDataDevolucao());
        assertEquals(1, e.getEstaEmprestada());
    }

    @Test
    void testGetSetId() {
        emprestimo.setId(100);
        assertEquals(100, emprestimo.getId());
        
        emprestimo.setId(0);
        assertEquals(0, emprestimo.getId());
    }
    
    @Test
    void testGetSetIdAmigos() {
        emprestimo.setIdAmigos(7);
        assertEquals(7, emprestimo.getIdAmigos());
        
        emprestimo.setIdAmigos(0);
        assertEquals(0, emprestimo.getIdAmigos());
    }
}

