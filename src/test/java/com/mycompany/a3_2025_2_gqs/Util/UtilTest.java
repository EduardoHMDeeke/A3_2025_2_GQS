package com.mycompany.a3_2025_2_gqs.Util;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Calendar;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes para a classe Util
 */
public class UtilTest {

    @Test
    void testObtemNumComTextoComNumeros() {
        int resultado = Util.obtemNum("abc123def456");
        assertEquals(123456, resultado);
    }

    @Test
    void testObtemNumComApenasNumeros() {
        int resultado = Util.obtemNum("12345");
        assertEquals(12345, resultado);
    }

    @Test
    void testObtemNumComApenasTexto() {
        int resultado = Util.obtemNum("abc");
        assertEquals(0, resultado);
    }

    @Test
    void testObtemNumComTextoVazio() {
        int resultado = Util.obtemNum("");
        assertEquals(0, resultado);
    }

    @Test
    void testObtemNumComNumerosEspalhados() {
        int resultado = Util.obtemNum("a1b2c3d4");
        assertEquals(1234, resultado);
    }

    @Test
    void testObtemNumComZero() {
        int resultado = Util.obtemNum("0");
        assertEquals(0, resultado);
    }

    @Test
    void testConverterDataStringParaLocalDate() {
        LocalDate resultado = Util.converterData("15/01/2024");
        assertNotNull(resultado);
        assertEquals(2024, resultado.getYear());
        assertEquals(1, resultado.getMonthValue());
        assertEquals(15, resultado.getDayOfMonth());
    }

    @Test
    void testConverterDataLocalDateParaString() {
        LocalDate data = LocalDate.of(2024, 3, 20);
        String resultado = Util.converterData(data);
        assertEquals("20/03/2024", resultado);
    }

    @Test
    void testConverterDataDateParaLocalDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.JANUARY, 15);
        Date date = calendar.getTime();
        
        LocalDate resultado = Util.converterData(date);
        assertNotNull(resultado);
        assertEquals(2024, resultado.getYear());
        assertEquals(1, resultado.getMonthValue());
        assertEquals(15, resultado.getDayOfMonth());
    }

    @Test
    void testConverterDataDateComMesDiferente() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.DECEMBER, 31);
        Date date = calendar.getTime();
        
        LocalDate resultado = Util.converterData(date);
        assertEquals(2024, resultado.getYear());
        assertEquals(12, resultado.getMonthValue());
        assertEquals(31, resultado.getDayOfMonth());
    }

    @Test
    void testVerificarNumnoTextoComNumeros() {
        boolean resultado = Util.verficarNumnoTexto("abc123");
        assertFalse(resultado, "Deve retornar false quando há números no texto");
    }

    @Test
    void testVerificarNumnoTextoSemNumeros() {
        boolean resultado = Util.verficarNumnoTexto("abc");
        assertTrue(resultado, "Deve retornar true quando não há números no texto");
    }

    @Test
    void testVerificarNumnoTextoVazio() {
        boolean resultado = Util.verficarNumnoTexto("");
        assertTrue(resultado, "Texto vazio não tem números, deve retornar true");
    }

    @Test
    void testVerificarNumnoTextoApenasNumeros() {
        boolean resultado = Util.verficarNumnoTexto("12345");
        assertFalse(resultado, "Texto com apenas números deve retornar false");
    }

    @Test
    void testConverterPrecoComValorNormal() {
        BigDecimal preco = new BigDecimal("99.99");
        String resultado = Util.converterPreco(preco);
        assertNotNull(resultado);
        assertTrue(resultado.contains("R$"));
        assertTrue(resultado.contains("99"));
    }

    @Test
    void testConverterPrecoComValorZero() {
        BigDecimal preco = new BigDecimal("0.00");
        String resultado = Util.converterPreco(preco);
        assertNotNull(resultado);
        assertTrue(resultado.contains("R$"));
    }

    @Test
    void testConverterPrecoComValorGrande() {
        BigDecimal preco = new BigDecimal("1234.56");
        String resultado = Util.converterPreco(preco);
        assertNotNull(resultado);
        assertTrue(resultado.contains("R$"));
    }

    @Test
    void testConverterPrecoComValorDecimal() {
        BigDecimal preco = new BigDecimal("10.50");
        String resultado = Util.converterPreco(preco);
        assertNotNull(resultado);
        assertTrue(resultado.contains("R$"));
    }

    @Test
    void testConverterDataStringFormatoInvalido() {
        assertThrows(Exception.class, () -> {
            Util.converterData("2024-01-15");
        }, "Deve lançar exceção para formato inválido");
    }

    @Test
    void testObtemNumComCaracteresEspeciais() {
        int resultado = Util.obtemNum("!@#123$%^456&*");
        assertEquals(123456, resultado);
    }

    @Test
    void testConverterDataLocalDateNull() {
        assertThrows(Exception.class, () -> {
            Util.converterData((LocalDate) null);
        }, "Deve lançar exceção para LocalDate null");
    }
}

