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
}

