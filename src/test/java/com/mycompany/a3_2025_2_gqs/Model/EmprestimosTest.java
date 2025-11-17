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

    @Test
    void testGetSetIdFerramentas() {
        emprestimo.setIdFerramentas(12);
        assertEquals(12, emprestimo.getIdFerramentas());
        
        emprestimo.setIdFerramentas(1);
        assertEquals(1, emprestimo.getIdFerramentas());
    }

    @Test
    void testGetSetDataEmprestimo() {
        emprestimo.setDataEmprestimo(dataEmprestimo);
        assertEquals(dataEmprestimo, emprestimo.getDataEmprestimo());
        
        emprestimo.setDataEmprestimo(null);
        assertNull(emprestimo.getDataEmprestimo());
        
        LocalDate outraData = LocalDate.of(2024, 2, 1);
        emprestimo.setDataEmprestimo(outraData);
        assertEquals(outraData, emprestimo.getDataEmprestimo());
    }

    @Test
    void testGetSetDataDevolucao() {
        emprestimo.setDataDevolucao(dataDevolucao);
        assertEquals(dataDevolucao, emprestimo.getDataDevolucao());
        
        emprestimo.setDataDevolucao(null);
        assertNull(emprestimo.getDataDevolucao());
        
        LocalDate outraData = LocalDate.of(2024, 3, 1);
        emprestimo.setDataDevolucao(outraData);
        assertEquals(outraData, emprestimo.getDataDevolucao());
    }

    @Test
    void testGetSetEstaEmprestada() {
        emprestimo.setEstaEmprestada(1);
        assertEquals(1, emprestimo.getEstaEmprestada());
        
        emprestimo.setEstaEmprestada(0);
        assertEquals(0, emprestimo.getEstaEmprestada());
    }

    @Test
    void testGetSetDataDevolvida() {
        LocalDate dataDevolvida = LocalDate.of(2024, 1, 20);
        emprestimo.setDataDevolvida(dataDevolvida);
        assertEquals(dataDevolvida, emprestimo.getDataDevolvida());
        
        emprestimo.setDataDevolvida(null);
        assertNull(emprestimo.getDataDevolvida());
    }

    @Test
    void testTodosOsGettersESetters() {
        emprestimo.setId(1);
        emprestimo.setIdAmigos(5);
        emprestimo.setIdFerramentas(10);
        emprestimo.setDataEmprestimo(dataEmprestimo);
        emprestimo.setDataDevolucao(dataDevolucao);
        emprestimo.setEstaEmprestada(1);
        LocalDate dataDevolvida = LocalDate.of(2024, 1, 18);
        emprestimo.setDataDevolvida(dataDevolvida);

        assertEquals(1, emprestimo.getId());
        assertEquals(5, emprestimo.getIdAmigos());
        assertEquals(10, emprestimo.getIdFerramentas());
        assertEquals(dataEmprestimo, emprestimo.getDataEmprestimo());
        assertEquals(dataDevolucao, emprestimo.getDataDevolucao());
        assertEquals(1, emprestimo.getEstaEmprestada());
        assertEquals(dataDevolvida, emprestimo.getDataDevolvida());
    }

    @Test
    void testDatasNulas() {
        Emprestimos e = new Emprestimos();
        e.setDataEmprestimo(null);
        e.setDataDevolucao(null);
        e.setDataDevolvida(null);
        
        assertNull(e.getDataEmprestimo());
        assertNull(e.getDataDevolucao());
        assertNull(e.getDataDevolvida());
    }
}

