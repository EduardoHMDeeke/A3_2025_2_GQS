package com.mycompany.a3_2025_2_gqs.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {

    private static final Logger LOGGER = Logger.getLogger(Conexao.class.getName());

    // Configurações padrão (Docker Compose)
    private static final String DEFAULT_HOST = "localhost";
    private static final String DEFAULT_PORT = "3306";
    private static final String DEFAULT_DATABASE = "dbtooltracker";
    private static final String DEFAULT_USER = "tooltracker";
    private static final String DEFAULT_PASSWORD = "tooltracker123";

    // Permite sobrescrever via variáveis de ambiente
    private static final String DB_HOST = System.getenv("DB_HOST") != null ? System.getenv("DB_HOST") : DEFAULT_HOST;
    private static final String DB_PORT = System.getenv("DB_PORT") != null ? System.getenv("DB_PORT") : DEFAULT_PORT;
    private static final String DB_NAME = System.getenv("DB_NAME") != null ? System.getenv("DB_NAME") : DEFAULT_DATABASE;
    private static final String DB_USER = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : DEFAULT_USER;
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : DEFAULT_PASSWORD;

    private static final String URL = String.format("jdbc:mysql://%s:%s/%s?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", 
                                                     DB_HOST, DB_PORT, DB_NAME);

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
            LOGGER.info("Conexão com banco de dados estabelecida com sucesso");
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Erro ao conectar ao banco de dados: " + URL, ex);
            LOGGER.log(Level.SEVERE, "Usuário: " + DB_USER, ex);
        }
        return conn;
    }
}
