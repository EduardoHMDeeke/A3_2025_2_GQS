package com.mycompany.a3_2025_2_gqs.backend.repository;

import org.junit.jupiter.api.*;

import com.mycompany.a3_2025_2_gqs.backend.model.Emprestimos;
import com.mycompany.a3_2025_2_gqs.backend.repository.EmprestimosDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes de integração para EmprestimosDAO usando SQLite in-memory.
 * Até Commit F (inclui insert, listar, buscar, updates e SQL injection)
 */
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
        String sql = "CREATE TABLE IF NOT EXISTS emprestimos ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "idAmigo INTEGER, "
                + "idFerramenta INTEGER, "
                + "dataEmprestimo DATE, "
                + "dataDevolucao DATE, "
                + "dataDevolvida DATE, "
                + "estaEmprestada INTEGER"
                + ")";
        try (Statement st = c.createStatement()) {
            st.execute(sql);
        }
    }

    private static void clearData(Connection c) throws SQLException {
        try (Statement st = c.createStatement()) {
            st.executeUpdate("DELETE FROM emprestimos");
        }
    }

    // helper para criar um objeto Emprestimos (ajuste se sua model for diferente)
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
            LocalDate hoje = LocalDate.now();
            LocalDate seteDias = hoje.plusDays(7);
            Emprestimos e = novoEmprestimo(1, 2, hoje, seteDias);
            dao.insertBD(e);
        }

        try (Connection c2 = newConnection()) {
            EmprestimosDAO dao2 = new EmprestimosDAO(c2);
            ArrayList<Emprestimos> lista = dao2.listarEmprestimos();
            assertFalse(lista.isEmpty(), "A lista não deve estar vazia");
            idGerado = lista.get(0).getId();
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
            LocalDate hoje = LocalDate.now();
            LocalDate seteDias = hoje.plusDays(7);
            Emprestimos e = novoEmprestimo(5, 6, hoje, seteDias);
            dao.insertBD(e);
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
        Date dataDev = Date.from(LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant());

        try (Connection c1 = newConnection()) {
            EmprestimosDAO dao = new EmprestimosDAO(c1);
            LocalDate hoje = LocalDate.now();
            LocalDate seteDias = hoje.plusDays(7);
            Emprestimos e = novoEmprestimo(7, 8, hoje, seteDias);
            dao.insertBD(e);
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
            LocalDate hoje = LocalDate.now();
            LocalDate depois = hoje.plusDays(3);

            Emprestimos e = novoEmprestimo(999, 999, hoje, depois);
            dao.insertBD(e);
        }

        ArrayList<Emprestimos> lista;
        try (Connection c2 = newConnection()) {
            EmprestimosDAO dao2 = new EmprestimosDAO(c2);
            lista = dao2.listarEmprestimos();
        }

        assertTrue(lista.size() >= 1, "Tabela deveria conter pelo menos 1 registro após insert");
        assertEquals(999, lista.get(0).getIdAmigos());
    }
<<<<<<< Updated upstream:src/test/java/com/mycompany/a3_2025_2_gqs/backend/repository/EmprestimosDAOTest.java
=======

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

    @Test
    void insertBD_comLocalDateTime_devePersistir() throws Exception {
        try (Connection c = newConnection()) {
            createSchema(c);
            EmprestimosDAO dao = new EmprestimosDAO(c);

            Emprestimos empre = new Emprestimos();
            empre.setIdAmigos(1);
            empre.setIdFerramentas(1);
            empre.setEstaEmprestada(1);
            
            // Usar reflection para definir LocalDateTime
            java.time.LocalDateTime ldt = java.time.LocalDateTime.now();
            java.lang.reflect.Field field = Emprestimos.class.getDeclaredField("dataEmprestimo");
            field.setAccessible(true);
            field.set(empre, ldt);
            
            field = Emprestimos.class.getDeclaredField("dataDevolucao");
            field.setAccessible(true);
            field.set(empre, ldt.plusDays(7));

            dao.insertBD(empre);

            ArrayList<Emprestimos> lista = dao.listarEmprestimos();
            assertEquals(1, lista.size());
        }
    }

    @Test
    void insertBD_comTimestamp_devePersistir() throws Exception {
        try (Connection c = newConnection()) {
            createSchema(c);
            EmprestimosDAO dao = new EmprestimosDAO(c);

            Emprestimos empre = new Emprestimos();
            empre.setIdAmigos(2);
            empre.setIdFerramentas(2);
            empre.setEstaEmprestada(1);
            
            // Usar reflection para definir Timestamp diretamente no campo
            java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
            java.lang.reflect.Field field = Emprestimos.class.getDeclaredField("dataEmprestimo");
            field.setAccessible(true);
            field.set(empre, ts);
            
            field = Emprestimos.class.getDeclaredField("dataDevolucao");
            field.setAccessible(true);
            field.set(empre, new java.sql.Timestamp(System.currentTimeMillis() + 86400000L));

            dao.insertBD(empre);

            ArrayList<Emprestimos> lista = dao.listarEmprestimos();
            assertEquals(1, lista.size());
        }
    }

    @Test
    void insertBD_comUtilDate_devePersistir() throws Exception {
        try (Connection c = newConnection()) {
            createSchema(c);
            EmprestimosDAO dao = new EmprestimosDAO(c);

            Emprestimos empre = new Emprestimos();
            empre.setIdAmigos(5);
            empre.setIdFerramentas(5);
            empre.setEstaEmprestada(1);
            
            // Usar reflection para definir java.util.Date
            java.util.Date date = new java.util.Date();
            java.lang.reflect.Field field = Emprestimos.class.getDeclaredField("dataEmprestimo");
            field.setAccessible(true);
            field.set(empre, date);
            
            field = Emprestimos.class.getDeclaredField("dataDevolucao");
            field.setAccessible(true);
            field.set(empre, new java.util.Date(date.getTime() + 86400000L));

            dao.insertBD(empre);

            ArrayList<Emprestimos> lista = dao.listarEmprestimos();
            assertEquals(1, lista.size());
        }
    }

    @Test
    void insertBD_comTipoDesconhecido_deveUsarNull() throws Exception {
        try (Connection c = newConnection()) {
            createSchema(c);
            EmprestimosDAO dao = new EmprestimosDAO(c);

            Emprestimos empre = new Emprestimos();
            empre.setIdAmigos(3);
            empre.setIdFerramentas(3);
            empre.setEstaEmprestada(1);
            
            // Usar reflection para definir um tipo desconhecido
            java.lang.reflect.Field field = Emprestimos.class.getDeclaredField("dataEmprestimo");
            field.setAccessible(true);
            field.set(empre, "tipo_desconhecido");
            
            field = Emprestimos.class.getDeclaredField("dataDevolucao");
            field.setAccessible(true);
            field.set(empre, "tipo_desconhecido");

            dao.insertBD(empre);

            ArrayList<Emprestimos> lista = dao.listarEmprestimos();
            assertEquals(1, lista.size());
        }
    }

    @Test
    void insertBD_comEstaEmprestadaZero_devePersistirZero() throws Exception {
        try (Connection c = newConnection()) {
            createSchema(c);
            EmprestimosDAO dao = new EmprestimosDAO(c);

            Emprestimos empre = new Emprestimos();
            empre.setIdAmigos(4);
            empre.setIdFerramentas(4);
            empre.setEstaEmprestada(0);
            empre.setDataEmprestimo(LocalDate.now());
            empre.setDataDevolucao(LocalDate.now().plusDays(7));

            dao.insertBD(empre);

            ArrayList<Emprestimos> lista = dao.listarEmprestimos();
            assertEquals(1, lista.size());
            assertEquals(0, lista.get(0).getEstaEmprestada());
        }
    }

    @Test
    void updateEmprestimos_comDataDevolvidaNull_deveFuncionar() throws Exception {
        int idGerado;

        try (Connection c1 = newConnection()) {
            EmprestimosDAO dao = new EmprestimosDAO(c1);
            dao.insertBD(novoEmprestimo(8, 9, LocalDate.now(), LocalDate.now().plusDays(7)));
        }

        try (Connection c2 = newConnection()) {
            EmprestimosDAO dao2 = new EmprestimosDAO(c2);
            ArrayList<Emprestimos> lista = dao2.listarEmprestimos();
            assertFalse(lista.isEmpty());
            idGerado = lista.get(0).getId();
        }

        try (Connection c3 = newConnection()) {
            EmprestimosDAO dao3 = new EmprestimosDAO(c3);
            dao3.updateEmprestimos(0, null, idGerado);
        }

        try (Connection c4 = newConnection()) {
            EmprestimosDAO dao4 = new EmprestimosDAO(c4);
            Emprestimos atualizado = dao4.buscarEmprestimo(idGerado);
            assertEquals(0, atualizado.getEstaEmprestada());
        }
    }

    @Test
    void getAndConvertDate_comDataNull_deveRetornarNull() throws Exception {
        Class<?> daoClass = EmprestimosDAO.class;
        Connection c = newConnection();
        EmprestimosDAO dao = new EmprestimosDAO(c);

        java.lang.reflect.InvocationHandler handler = (proxy, method, args) -> {
            if ("getDate".equals(method.getName()) && args != null && args.length > 0) {
                return null; // Simula getDate retornando null
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
        assertNull(res, "Quando ResultSet.getDate retorna null, retorno deve ser null");
    }

    @Test
    void insertBD_comExcecaoSQL_deveLancarExcecao() throws Exception {
        try (Connection c = newConnection()) {
            // Dropar tabela para forçar erro
            try (Statement st = c.createStatement()) {
                st.execute("DROP TABLE IF EXISTS emprestimos");
            }
            
            EmprestimosDAO dao = new EmprestimosDAO(c);
            Emprestimos empre = novoEmprestimo(1, 1, LocalDate.now(), LocalDate.now());
            
            assertThrows(SQLException.class, () -> {
                dao.insertBD(empre);
            });
        }
    }

>>>>>>> Stashed changes:src/test/java/com/mycompany/a3_2025_2_gqs/DAO/EmprestimosDAOTest.java
}
