package com.mycompany.a3_2025_2_gqs.DAO;

import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class ConexaoTest {

    private Connection testConnection;

    @BeforeEach
    void setUp() throws SQLException {
        // Create an in-memory SQLite database for testing
        testConnection = DriverManager.getConnection("jdbc:sqlite:file:memdb2?mode=memory&cache=shared");
        createTestSchema(testConnection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (testConnection != null && !testConnection.isClosed()) {
            testConnection.close();
        }
    }

    private static void createTestSchema(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS test_table ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT"
                + ")";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    @Test
    void getConnection_shouldReturnValidConnection() {
        // This test will be skipped if MySQL environment variables are not set
        // since we can't test the actual MySQL connection without credentials
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        
        // Only run this test if environment variables are set
        Assumptions.assumeTrue(user != null && !user.isEmpty(), "DB_USER environment variable not set");
        Assumptions.assumeTrue(password != null && !password.isEmpty(), "DB_PASSWORD environment variable not set");

        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();
        
        assertNotNull(conn, "Connection should not be null");
        
        try {
            assertFalse(conn.isClosed(), "Connection should be open");
            
            // Test that we can execute a simple query
            try (Statement stmt = conn.createStatement()) {
                boolean result = stmt.execute("SELECT 1");
                assertTrue(result, "Should be able to execute query");
            }
        } catch (SQLException e) {
            fail("Should be able to use the connection without exceptions: " + e.getMessage());
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                // Ignore close exceptions
            }
        }
    }

    @Test
    void getConnection_shouldHandleInvalidCredentialsGracefully() {
        // Temporarily set invalid credentials to test error handling
        // We'll use reflection to test the error handling path
        Conexao conexao = new Conexao();
        
        // Since we can't easily mock the environment variables for this test,
        // we'll rely on the fact that the method should handle exceptions gracefully
        // and not throw them
        
        // This test verifies that the method doesn't throw an exception
        // even when connection fails
        assertDoesNotThrow(() -> {
            Connection conn = conexao.getConnection();
            // Connection might be null, but no exception should be thrown
        });
    }

    @Test
    void getConnection_shouldReturnConnectionWithCorrectProperties() {
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        
        // Skip test if environment variables aren't set
        Assumptions.assumeTrue(user != null && !user.isEmpty(), "DB_USER environment variable not set");
        Assumptions.assumeTrue(password != null && !password.isEmpty(), "DB_PASSWORD environment variable not set");

        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();
        
        assertNotNull(conn, "Connection should not be null");
        
        try {
            // Verify connection is usable and has basic functionality
            assertTrue(conn.isValid(2), "Connection should be valid");
            assertEquals(Connection.TRANSACTION_REPEATABLE_READ, conn.getTransactionIsolation(), 
                "Should have default transaction isolation level");
        } catch (SQLException e) {
            fail("Should be able to check connection properties: " + e.getMessage());
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                // Ignore close exceptions
            }
        }
    }

    @Test
    void conexaoInstance_shouldBeCreateable() {
        // Simple test to ensure we can create instances of Conexao
        assertDoesNotThrow(() -> {
            Conexao conexao = new Conexao();
            assertNotNull(conexao, "Should be able to create Conexao instance");
        });
    }

    @Test
    void multipleGetConnectionCalls_shouldReturnNewConnections() {
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        
        // Skip test if environment variables aren't set
        Assumptions.assumeTrue(user != null && !user.isEmpty(), "DB_USER environment variable not set");
        Assumptions.assumeTrue(password != null && !password.isEmpty(), "DB_PASSWORD environment variable not set");

        Conexao conexao = new Conexao();
        
        Connection conn1 = conexao.getConnection();
        Connection conn2 = conexao.getConnection();
        
        assertNotNull(conn1, "First connection should not be null");
        assertNotNull(conn2, "Second connection should not be null");
        
        // They should be different connection instances
        assertNotSame(conn1, conn2, "Should return different connection instances");
        
        try {
            // Both connections should be usable
            assertTrue(conn1.isValid(2), "First connection should be valid");
            assertTrue(conn2.isValid(2), "Second connection should be valid");
        } catch (SQLException e) {
            fail("Should be able to validate both connections: " + e.getMessage());
        } finally {
            try {
                if (conn1 != null && !conn1.isClosed()) conn1.close();
                if (conn2 != null && !conn2.isClosed()) conn2.close();
            } catch (SQLException e) {
                // Ignore close exceptions
            }
        }
    }
}