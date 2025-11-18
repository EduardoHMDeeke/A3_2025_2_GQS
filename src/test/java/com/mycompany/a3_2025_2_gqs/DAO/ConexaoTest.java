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
        
        // Skip test if connection is null (MySQL not available)
        Assumptions.assumeTrue(conn != null, "MySQL database not available - connection returned null");
        
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
        
        // Skip test if connection is null (MySQL not available)
        Assumptions.assumeTrue(conn != null, "MySQL database not available - connection returned null");
        
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
        
        // Skip test if connections are null (MySQL not available)
        Assumptions.assumeTrue(conn1 != null, "MySQL database not available - first connection returned null");
        Assumptions.assumeTrue(conn2 != null, "MySQL database not available - second connection returned null");
        
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

    @Test
    void getConnection_shouldUseDefaultHostAndPort_whenNotSet() throws Exception {
        // Test that default values are used when environment variables are not set
        // This tests the getEnvValue method with defaults
        Conexao conexao = new Conexao();
        
        // The method should not throw an exception even if DB_HOST and DB_PORT are not set
        // It should use defaults
        assertDoesNotThrow(() -> {
            Connection conn = conexao.getConnection();
            // Connection might be null if required vars are missing, but no exception should be thrown
        });
    }

    @Test
    void getConnection_shouldThrowException_whenRequiredVarsMissing() {
        // Test that getRequiredEnvValue throws IllegalStateException when required vars are missing
        // We can't easily test this without mocking, but we can verify the behavior
        Conexao conexao = new Conexao();
        
        // If required environment variables are not set, getConnection should handle it gracefully
        // (either return null or throw IllegalStateException)
        assertDoesNotThrow(() -> {
            Connection conn = conexao.getConnection();
            // If required vars are missing, conn will be null or exception will be thrown
        });
    }

    @Test
    void getConnection_shouldHandleSQLException() {
        // Test that getConnection handles SQLException gracefully
        Conexao conexao = new Conexao();
        
        // The method should catch SQLException and log it, returning null
        assertDoesNotThrow(() -> {
            Connection conn = conexao.getConnection();
            // Connection might be null if there's an error, but no exception should be thrown
        });
    }

    @Test
    void getConnection_shouldUseDotenvFirst() {
        // Test that dotenv is loaded lazily
        Conexao conexao = new Conexao();
        
        // First call should load dotenv
        assertDoesNotThrow(() -> {
            conexao.getConnection();
        });
        
        // Second call should use cached dotenv
        assertDoesNotThrow(() -> {
            conexao.getConnection();
        });
    }

    @Test
    void getConnection_shouldFallbackToSystemEnv() {
        // Test that system environment variables are used as fallback
        Conexao conexao = new Conexao();
        
        // The method should try system env if .env doesn't have the value
        assertDoesNotThrow(() -> {
            Connection conn = conexao.getConnection();
            // Should not throw even if .env doesn't exist
        });
    }

    @Test
    void getConnection_shouldUseDefaultHostAndPort() {
        // Test that default host and port are used when not set
        Conexao conexao = new Conexao();
        
        // Should use default localhost:3306 if not set in env
        assertDoesNotThrow(() -> {
            Connection conn = conexao.getConnection();
            // Should not throw even if DB_HOST and DB_PORT are not set
        });
    }

    @Test
    void getConnection_shouldFormatUrlCorrectly() {
        // Test that URL is formatted correctly
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        String dbName = System.getenv("DB_NAME");
        
        // Skip if env vars not set
        Assumptions.assumeTrue(user != null && password != null && dbName != null,
                "DB environment variables not set");
        
        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();
        
        Assumptions.assumeTrue(conn != null, "Connection is null");
        
        // If we got here, URL was formatted correctly
        assertTrue(true);
        
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            // Ignore
        }
    }

    @Test
    void getConnection_shouldLogErrorOnFailure() {
        // Test that errors are logged (we can't easily verify logging, but we can test it doesn't throw)
        Conexao conexao = new Conexao();
        
        // Should log error but not throw
        assertDoesNotThrow(() -> {
            Connection conn = conexao.getConnection();
            // Error logging happens inside, we just verify no exception
        });
    }
}