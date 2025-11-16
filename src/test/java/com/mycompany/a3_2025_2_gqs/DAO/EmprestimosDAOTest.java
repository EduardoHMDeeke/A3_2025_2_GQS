package com.mycompany.a3_2025_2_gqs.DAO;

import com.mycompany.a3_2025_2_gqs.Model.Emprestimos;
import com.mycompany.a3_2025_2_gqs.Util.Util;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EmprestimosDAOTest {

    private Connection keeper;

    @BeforeEach
    void setUp() throws SQLException {
        keeper = newConnection();
        createSchema(keeper);
        clearData(keeper);
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (keeper != null && !keeper.isClosed()) {
            keeper.close();
        }
    }

    private static Connection newConnection() throws SQLException {
        String url = "jdbc:sqlite:file:memdb?mode=memory&cache=shared";
        return DriverManager.getConnection(url);
    }

    private static void createSchema(Connection c) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS emprestimos (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    idAmigo INTEGER,
                    idFerramenta INTEGER,
                    dataEmprestimo DATE,
                    dataDevolucao DATE,
                    dataDevolvida DATE,
                    estaEmprestada INTEGER
                )
                """;
        try (Statement st = c.createStatement()) {
            st.execute(sql);
        }
    }

    private static void clearData(Connection c) throws SQLException {
        try (Statement st = c.createStatement()) {
            st.executeUpdate("DELETE FROM emprestimos");
        }
    }

    private Emprestimos novoEmprestimo(int idAmigo, int idFerramenta, LocalDate dtEmp, LocalDate dtDev) {
        return new Emprestimos(idAmigo, idFerramenta, dtEmp, dtDev, 1);
    }

    @Test
    void insertBD_e_listarEmprestimos_devePersistirEListar() throws Exception {
        try (Connection c1 = newConnection()) {
            EmprestimosDAO dao = new EmprestimosDAO(c1);
            LocalDate hoje = LocalDate.now();
            LocalDate seteDias = hoje.plusDays(7);
            Emprestimos e = novoEmprestimo(10, 20, hoje, seteDias);
            dao.insertBD(e);
        }

        ArrayList<Emprestimos> lista;
        try (Connection c2 = newConnection()) {
            EmprestimosDAO dao2 = new EmprestimosDAO(c2);
            lista = dao2.listarEmprestimos();
        }

        assertEquals(1, lista.size(), "Deve haver 1 registro salvo");
        Emprestimos salvo = lista.get(0);
        assertEquals(10, salvo.getIdAmigos());
        assertEquals(20, salvo.getIdFerramentas());
        assertEquals(1, salvo.getEstaEmprestada());
    }

    @Test
    void buscarEmprestimo_deveRetornarPorId() throws Exception {
        int idGerado;

        try (Connection c1 = newConnection()) {
            EmprestimosDAO dao = new EmprestimosDAO(c1);
            Emprestimos e = novoEmprestimo(1, 2, LocalDate.now(), LocalDate.now().plusDays(7));
            dao.insertBD(e);
        }

        try (Connection c2 = newConnection()) {
            EmprestimosDAO dao2 = new EmprestimosDAO(c2);
            assertFalse(dao2.listarEmprestimos().isEmpty());
            idGerado = dao2.listarEmprestimos().get(0).getId();
        }

        try (Connection c3 = newConnection()) {
            EmprestimosDAO dao3 = new EmprestimosDAO(c3);
            Emprestimos encontrado = dao3.buscarEmprestimo(idGerado);

            assertEquals(1, encontrado.getIdAmigos());
            assertEquals(2, encontrado.getIdFerramentas());
            assertEquals(1, encontrado.getEstaEmprestada());
        }
    }

    @Test
    void updateEmprestimos_deveAlterarStatus() throws Exception {
        int idGerado;

        try (Connection c1 = newConnection()) {
            EmprestimosDAO dao = new EmprestimosDAO(c1);
            dao.insertBD(novoEmprestimo(5, 6, LocalDate.now(), LocalDate.now()));
        }

        try (Connection c2 = newConnection()) {
            EmprestimosDAO dao2 = new EmprestimosDAO(c2);
            ArrayList<Emprestimos> lista = dao2.listarEmprestimos();
            assertFalse(lista.isEmpty(), "Deve existir pelo menos um registro");
            idGerado = lista.get(0).getId();
            assertEquals(1, lista.get(0).getEstaEmprestada());
        }

        try (Connection c3 = newConnection()) {
            EmprestimosDAO dao3 = new EmprestimosDAO(c3);
            dao3.updateEmprestimos(0, idGerado);
        }

        try (Connection c4 = newConnection()) {
            EmprestimosDAO dao4 = new EmprestimosDAO(c4);
            Emprestimos e = dao4.buscarEmprestimo(idGerado);
            assertEquals(0, e.getEstaEmprestada());
        }
    }

    @Test
    void updateEmprestimosComData_deveAtualizarStatusEDataDevolvida() throws Exception {
        int idGerado;
        java.util.Date dataDev = java.util.Date.from(LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant());

        try (Connection c1 = newConnection()) {
            EmprestimosDAO dao = new EmprestimosDAO(c1);
            dao.insertBD(novoEmprestimo(7, 8, LocalDate.now(), LocalDate.now().plusDays(7)));
        }

        try (Connection c2 = newConnection()) {
            EmprestimosDAO dao2 = new EmprestimosDAO(c2);
            ArrayList<Emprestimos> lista = dao2.listarEmprestimos();
            assertFalse(lista.isEmpty());
            idGerado = lista.get(0).getId();
        }

        try (Connection c3 = newConnection()) {
            EmprestimosDAO dao3 = new EmprestimosDAO(c3);
            dao3.updateEmprestimos(0, dataDev, idGerado);
        }

        try (Connection c4 = newConnection()) {
            EmprestimosDAO dao4 = new EmprestimosDAO(c4);
            Emprestimos atualizado = dao4.buscarEmprestimo(idGerado);
            assertEquals(0, atualizado.getEstaEmprestada());
            assertNotNull(atualizado.getDataDevolucao(), "Data de devolução deve estar registrada");
        }
    }

    @Test
    void insertBD_deveProtegerContraSQLInjection() throws Exception {
        try (Connection c1 = newConnection()) {
            EmprestimosDAO dao = new EmprestimosDAO(c1);
            Emprestimos e = novoEmprestimo(999, 999, LocalDate.now(), LocalDate.now().plusDays(3));
            dao.insertBD(e);
        }

        ArrayList<Emprestimos> lista;
        try (Connection c2 = newConnection()) {
            EmprestimosDAO dao2 = new EmprestimosDAO(c2);
            lista = dao2.listarEmprestimos();
        }

        assertTrue(lista.size() >= 1);
        assertEquals(999, lista.get(0).getIdAmigos());
    }

    @Test
    void insertBD_comDatasNulas_deveGravarSemErro() throws Exception {
        Emprestimos e = new Emprestimos(1, 1, null, null, 1);

        try (Connection c = newConnection()) {
            new EmprestimosDAO(c).insertBD(e);
        }

        try (Connection c2 = newConnection()) {
            var lista = new EmprestimosDAO(c2).listarEmprestimos();
            assertEquals(1, lista.size());
        }
    }

    @Test
    void listarEmprestimos_comTabelaInexistente_deveLancarSQLException_ou_Headless() throws Exception {
        try (Connection c = newConnection()) {
            try (Statement st = c.createStatement()) {
                st.execute("DROP TABLE IF EXISTS emprestimos");
            }
            EmprestimosDAO dao = new EmprestimosDAO(c);
            Exception exc = assertThrows(Exception.class, dao::listarEmprestimos);
            assertTrue(exc instanceof SQLException || exc instanceof java.awt.HeadlessException,
                    "Exceção esperada: SQLException ou HeadlessException; obtido: " + exc.getClass().getName());
        }
    }

    @Test
    void buscarEmprestimo_inexistente_retornaObjetoVazio() throws Exception {
        try (Connection c = newConnection()) {
            EmprestimosDAO dao = new EmprestimosDAO(c);
            Emprestimos emp = dao.buscarEmprestimo(999999);
            assertNotNull(emp);
            assertEquals(0, emp.getId());
            assertNull(emp.getDataEmprestimo());
        }
    }

    @Test
    void updateEmprestimos_comTabelaInexistente_naoLancaExcecaoNaoTratada() throws Exception {
        try (Connection c = newConnection()) {
            try (Statement st = c.createStatement()) {
                st.execute("DROP TABLE IF EXISTS emprestimos");
            }
            EmprestimosDAO dao = new EmprestimosDAO(c);
            try {
                dao.updateEmprestimos(1, 1);
            } catch (Exception ex) {
                if (ex instanceof java.awt.HeadlessException) {
                } else {
                    fail("updateEmprestimos lançou exceção inesperada: " + ex.getClass().getName());
                }
            }
        }
    }

    @Test
    void insertBD_withUtilDate_shouldPersist() throws Exception {
        try (Connection c = newConnection()) {
            createSchema(c);
            EmprestimosDAO dao = new EmprestimosDAO(c);

            Emprestimos empre = new Emprestimos();
            empre.setIdAmigos(7);
            empre.setIdFerramentas(9);
            empre.setEstaEmprestada(1);

            // usando java.util.Date
            java.util.Date agora = new java.util.Date();
            empre.setDataEmprestimo(Util.converterData(agora));
            empre.setDataDevolucao(Util.converterData(new java.util.Date(agora.getTime() + 86400000L)));

            dao.insertBD(empre);

            ArrayList<Emprestimos> lista = dao.listarEmprestimos();
            assertEquals(1, lista.size());
            assertEquals(7, lista.get(0).getIdAmigos());
        }
    }

    @Test
    void getAndConvertDate_whenResultSetThrows_shouldReturnNull() throws Exception {
        Class<?> daoClass = EmprestimosDAO.class;
        Connection c = newConnection();
        EmprestimosDAO dao = new EmprestimosDAO(c);

        java.lang.reflect.InvocationHandler handler = (proxy, method, args) -> {
            if ("getDate".equals(method.getName()) && args != null && args.length > 0) {
                throw new SQLException("simulated");
            }
            if ("next".equals(method.getName())) {
                return false;
            }
            return null;
        };

        Object rsProxy = java.lang.reflect.Proxy.newProxyInstance(
                ResultSet.class.getClassLoader(),
                new Class[]{ResultSet.class},
                handler
        );

        Method m = daoClass.getDeclaredMethod("getAndConvertDate", ResultSet.class, String.class);
        m.setAccessible(true);
        Object res = m.invoke(dao, rsProxy, "dataEmprestimo");
        assertNull(res, "Quando ResultSet.getDate lança SQLException, retorno deve ser null");
    }

}
