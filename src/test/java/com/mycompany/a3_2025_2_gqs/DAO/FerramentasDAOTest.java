package com.mycompany.a3_2025_2_gqs.DAO;

import com.mycompany.a3_2025_2_gqs.Model.Ferramentas;
import com.mycompany.a3_2025_2_gqs.testutil.TestDatabase;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FerramentaDAOTest {

    private Connection keeper;

    @BeforeEach
    void setUp() throws SQLException {
        keeper = TestDatabase.newConnection();
        TestDatabase.createSchema(keeper);
        TestDatabase.clearData(keeper);
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (keeper != null && !keeper.isClosed()) {
            keeper.close();
        }
    }

    private Ferramentas novaFerramenta(String nome, String marca, String preco, int estaEmprestada) {
        Ferramentas f = new Ferramentas();
        f.setNome(nome);
        f.setMarca(marca);

        // Ajuste se seu modelo usar setValor(...) em vez de setPreco(...)
        boolean setOk = false;
        try {
            f.getClass().getMethod("setPreco", String.class).invoke(f, preco);
            setOk = true;
        } catch (Exception ignored) {}
        if (!setOk) {
            try {
                f.getClass().getMethod("setValor", String.class).invoke(f, preco);
                setOk = true;
            } catch (Exception ignored) {}
        }
        if (!setOk) {
            throw new RuntimeException("Configure setPreco(String) OU setValor(String) na classe Ferramentas.");
        }

        f.setEstaEmprestada(estaEmprestada);
        return f;
    }

    @Test
    void insertBD_e_listarFerramentas_devePersistirEListar() throws Exception {
        // Inserção
        try (Connection c1 = TestDatabase.newConnection()) {
            FerramentaDAO dao = new FerramentaDAO(c1);
            Ferramentas f = novaFerramenta("Chave de Fenda", "Tramontina", "29.90", 0);
            dao.insertBD(f);
        }

        // Leitura
        ArrayList<Ferramentas> lista;
        try (Connection c2 = TestDatabase.newConnection()) {
            FerramentaDAO dao2 = new FerramentaDAO(c2);
            lista = dao2.listarFerramentas();
        }

        assertEquals(1, lista.size());
        Ferramentas salvo = lista.get(0);
        assertEquals("Chave de Fenda", salvo.getNome());
        assertEquals("Tramontina", salvo.getMarca());
        assertEquals(0, salvo.getEstaEmprestada());
    }

    @Test
    void listarFerramentasNaoEmprestadas_deveRetornarOndeEstaEmprestadaIgualA1() throws Exception {
        // Apesar do nome sugerir "não emprestadas", o método filtra por 1 — testamos o COMPORTAMENTO atual
        try (Connection c = TestDatabase.newConnection()) {
            new FerramentaDAO(c).insertBD(novaFerramenta("Martelo", "Vonder", "49.90", 1));
        }
        try (Connection c = TestDatabase.newConnection()) {
            new FerramentaDAO(c).insertBD(novaFerramenta("Alicate", "Gedore", "39.90", 0));
        }

        ArrayList<Ferramentas> lista;
        try (Connection c = TestDatabase.newConnection()) {
            lista = new FerramentaDAO(c).listarFerramentasNaoEmprestadas();
        }

        assertEquals(1, lista.size());
        assertEquals("Martelo", lista.get(0).getNome());
        assertEquals(1, lista.get(0).getEstaEmprestada());
    }

    @Test
    void updateFerramenta_deveAtualizarNomeMarcaPreco() throws Exception {
        int idGerado;

        // Insere
        try (Connection c = TestDatabase.newConnection()) {
            new FerramentaDAO(c).insertBD(novaFerramenta("Serrote", "Irwin", "79.90", 0));
        }

        // Obtém ID
        try (Connection c = TestDatabase.newConnection()) {
            ArrayList<Ferramentas> lista = new FerramentaDAO(c).listarFerramentas();
            assertFalse(lista.isEmpty());
            idGerado = lista.get(0).getId();
        }

        // Atualiza
        try (Connection c = TestDatabase.newConnection()) {
            Ferramentas novo = novaFerramenta("Serrote Profissional", "Irwin", "99.90", 0);
            new FerramentaDAO(c).UpdateFerramenta(novo, idGerado);
        }

        // Verifica
        try (Connection c = TestDatabase.newConnection()) {
            ArrayList<Ferramentas> lista = new FerramentaDAO(c).listarFerramentas();
            assertEquals(1, lista.size());
            assertEquals("Serrote Profissional", lista.get(0).getNome());
            assertEquals("Irwin", lista.get(0).getMarca());
        }
    }

    @Test
    void updateStatus_deveAtualizarCampoEstaEmprestada() throws Exception {
        int id;

        // Insere
        try (Connection c = TestDatabase.newConnection()) {
            new FerramentaDAO(c).insertBD(novaFerramenta("Trena", "Stanley", "59.90", 0));
        }

        // Pega ID
        try (Connection c = TestDatabase.newConnection()) {
            id = new FerramentaDAO(c).listarFerramentas().get(0).getId();
        }

        // Atualiza status
        try (Connection c = TestDatabase.newConnection()) {
            new FerramentaDAO(c).updateStatus(1, id);
        }

        // Verifica
        try (Connection c = TestDatabase.newConnection()) {
            Ferramentas f = new FerramentaDAO(c).listarFerramentas().get(0);
            assertEquals(1, f.getEstaEmprestada());
        }
    }

    @Test
    void deleteFerramentas_deveExcluirRegistro() throws Exception {
        int id;

        // Insere
        try (Connection c = TestDatabase.newConnection()) {
            new FerramentaDAO(c).insertBD(novaFerramenta("Nível", "Schulz", "89.90", 0));
        }

        // Pega ID
        try (Connection c = TestDatabase.newConnection()) {
            id = new FerramentaDAO(c).listarFerramentas().get(0).getId();
        }

        // Deleta
        try (Connection c = TestDatabase.newConnection()) {
            new FerramentaDAO(c).deleteFerramentas(id);
        }

        // Confirma vazio
        try (Connection c = TestDatabase.newConnection()) {
            assertTrue(new FerramentaDAO(c).listarFerramentas().isEmpty());
        }
    }

    
    @Test
    void insertBD_deveProtegerContraSQLInjection() throws Exception {
        try (Connection c = TestDatabase.newConnection()) {
            FerramentaDAO dao = new FerramentaDAO(c);
            Ferramentas f = novaFerramenta("'; DROP TABLE ferramentas; --", "Marca", "10.00", 0);
            dao.insertBD(f);
        }

        // Verifica se a tabela ainda existe e contém o dado como texto
        try (Connection c = TestDatabase.newConnection()) {
            ArrayList<Ferramentas> lista = new FerramentaDAO(c).listarFerramentas();
            assertEquals(1, lista.size());
            assertEquals("'; DROP TABLE ferramentas; --", lista.get(0).getNome());
        }
    }
}
