package com.mycompany.a3_2025_2_gqs.DAO; // Place this in your test folder structure

import com.mycompany.a3_2025_2_gqs.Model.Amigos;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Eduardo Deeke
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AmigosDAOTest {

    // --- Test Data ---
    private static final int TEST_ID = 1;
    private static final String TEST_NAME = "Alice Smith";
    private static final String TEST_EMAIL = "alice@example.com";
    private static final String TEST_PHONE = "1199887766";
    private static final int TEST_IDADE = 25;
    private static final String TEST_DATE = "2025-10-27";
    private static final String NEW_NAME = "Bob Johnson";
    private static final int NEW_IDADE = 40;

    // --- 1. Test Constructors ---

    @Test
    void testNoArgsConstructor() {
        // ARRANGE & ACT
        Amigos amigo = new Amigos();

        // ASSERT
        // All fields should be default initialized (0 for int, null for String)
        assertNotNull(amigo);
        assertEquals(0, amigo.getId());
        assertNull(amigo.getNome());
    }

    @Test
    void testFullArgsConstructor() {
        // ARRANGE & ACT
        Amigos amigo = new Amigos(TEST_NAME, TEST_EMAIL, TEST_PHONE, TEST_IDADE);

        // ASSERT
        assertEquals(TEST_NAME, amigo.getNome(), "Name should match constructor input.");
        assertEquals(TEST_EMAIL, amigo.getEmail(), "Email should match constructor input.");
        assertEquals(TEST_PHONE, amigo.getTelefone(), "Phone should match constructor input.");
        assertEquals(TEST_IDADE, amigo.getIdade(), "Idade should match constructor input.");
        assertEquals(0, amigo.getId(), "ID should be default 0.");
        assertNull(amigo.getDiaDoEmprestimo(), "DiaDoEmprestimo should be null by default.");
    }

    @Test
    void testPartialArgsConstructor() {
        // ARRANGE & ACT
        Amigos amigo = new Amigos(TEST_NAME, TEST_EMAIL, TEST_PHONE);

        // ASSERT
        assertEquals(TEST_NAME, amigo.getNome());
        assertEquals(TEST_EMAIL, amigo.getEmail());
        assertEquals(TEST_PHONE, amigo.getTelefone());
        assertEquals(0, amigo.getIdade(), "Idade should be default 0.");
    }

    // --- 2. Test Getters and Setters ---

    @Test
    void testGettersAndSetters() {
        // ARRANGE
        Amigos amigo = new Amigos();

        // ACT - Set all values
        amigo.setId(TEST_ID);
        amigo.setNome(TEST_NAME);
        amigo.setEmail(TEST_EMAIL);
        amigo.setTelefone(TEST_PHONE);
        amigo.setIdade(TEST_IDADE);
        amigo.setDiaDoEmprestimo(TEST_DATE);

        // ASSERT - Verify all getters return the set values
        assertEquals(TEST_ID, amigo.getId(), "The ID getter/setter failed.");
        assertEquals(TEST_NAME, amigo.getNome(), "The Nome getter/setter failed.");
        assertEquals(TEST_EMAIL, amigo.getEmail(), "The Email getter/setter failed.");
        assertEquals(TEST_PHONE, amigo.getTelefone(), "The Telefone getter/setter failed.");
        assertEquals(TEST_IDADE, amigo.getIdade(), "The Idade getter/setter failed.");
        assertEquals(TEST_DATE, amigo.getDiaDoEmprestimo(), "The DiaDoEmprestimo getter/setter failed.");

        // ACT - Test update with new values
        amigo.setNome(NEW_NAME);
        amigo.setIdade(NEW_IDADE);

        // ASSERT
        assertEquals(NEW_NAME, amigo.getNome(), "Updating the Name failed.");
        assertEquals(NEW_IDADE, amigo.getIdade(), "Updating the Idade failed.");
    }

    // --- 3. Test toString() Method ---

    @Test
    void testToString() {
        // ARRANGE
        Amigos amigo = new Amigos();
        amigo.setNome(TEST_NAME);

        // ACT
        String result = amigo.toString();

        // ASSERT
        // Verify the output contains the class name and the name field as implemented
        assertTrue(result.contains("Amigos{"), "toString should contain the class start.");
        assertTrue(result.contains("nome=" + TEST_NAME), "toString should display the name field.");
    }
}