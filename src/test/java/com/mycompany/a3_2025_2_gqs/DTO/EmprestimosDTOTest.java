package com.mycompany.a3_2025_2_gqs.DTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmprestimosDTOTest {

    private EmprestimosDTO emprestimo;
    private final int ID = 1;
    private final String AMIGO = "João Silva";
    private final String FERRAMENTA = "Martelo";
    private final String DATA_EMPRESTIMO = "2023-10-01";
    private final String DATA_DEVOLUCAO = "2023-10-08";

    @BeforeEach
    void setUp() {
        emprestimo = new EmprestimosDTO(
            ID, 
            AMIGO, 
            FERRAMENTA, 
            DATA_DEVOLUCAO, 
            DATA_EMPRESTIMO
        );
    }

    @Test
    void testConstructorAndGetters() {
        // Verify constructor sets values correctly
        assertEquals(ID, emprestimo.getId());
        assertEquals(AMIGO, emprestimo.getAmigo());
        assertEquals(FERRAMENTA, emprestimo.getFerramenta());
        assertEquals(DATA_DEVOLUCAO, emprestimo.getDataDevolucao());
        assertEquals(DATA_EMPRESTIMO, emprestimo.getDataEmprestimo());
    }

    @Test
    void testSetters() {
        // Test data updates
        int newId = 2;
        String newAmigo = "Maria Santos";
        String newFerramenta = "Chave de Fenda";
        String newDataEmprestimo = "2023-10-02";
        String newDataDevolucao = "2023-10-09";

        emprestimo.setId(newId);
        emprestimo.setAmigo(newAmigo);
        emprestimo.setFerramenta(newFerramenta);
        emprestimo.setDataEmprestimo(newDataEmprestimo);
        emprestimo.setDataDevolucao(newDataDevolucao);

        assertEquals(newId, emprestimo.getId());
        assertEquals(newAmigo, emprestimo.getAmigo());
        assertEquals(newFerramenta, emprestimo.getFerramenta());
        assertEquals(newDataEmprestimo, emprestimo.getDataEmprestimo());
        assertEquals(newDataDevolucao, emprestimo.getDataDevolucao());
    }

    @Test
    void testNullAndEmptyValues() {
        // Test boundary conditions
        emprestimo.setAmigo("");
        emprestimo.setFerramenta(null);
        emprestimo.setDataEmprestimo("");
        emprestimo.setDataDevolucao(null);

        assertEquals("", emprestimo.getAmigo());
        assertNull(emprestimo.getFerramenta());
        assertEquals("", emprestimo.getDataEmprestimo());
        assertNull(emprestimo.getDataDevolucao());
    }

    @Test
    void testIdEdgeCases() {
        // Test ID boundaries
        emprestimo.setId(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, emprestimo.getId());

        emprestimo.setId(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, emprestimo.getId());

        emprestimo.setId(0);
        assertEquals(0, emprestimo.getId());
    }
}