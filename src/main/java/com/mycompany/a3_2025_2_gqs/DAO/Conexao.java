package com.mycompany.a3_2025_2_gqs.DAO;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {

    private static final Logger LOGGER = Logger.getLogger(Conexao.class.getName());

    // Carrega variáveis do arquivo .env (se existir)
    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    // Configurações padrão (Docker Compose)
    private static final String DEFAULT_HOST = "localhost";
    private static final String DEFAULT_PORT = "3306";
    private static final String DEFAULT_DATABASE = "dbtooltracker";
    private static final String DEFAULT_USER = "tooltracker";
    private static final String DEFAULT_PASSWORD = "tooltracker123";

    // Lê do .env, depois variáveis de ambiente do sistema, depois usa padrão
    private static final String DB_HOST = getEnvValue("DB_HOST", DEFAULT_HOST);
    private static final String DB_PORT = getEnvValue("DB_PORT", DEFAULT_PORT);
    private static final String DB_NAME = getEnvValue("DB_NAME", DEFAULT_DATABASE);
    private static final String DB_USER = getEnvValue("DB_USER", DEFAULT_USER);
    private static final String DB_PASSWORD = getEnvValue("DB_PASSWORD", DEFAULT_PASSWORD);

    private static final String URL = String.format("jdbc:mysql://%s:%s/%s?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", 
                                                     DB_HOST, DB_PORT, DB_NAME);

    /**
     * Obtém valor do .env, depois do sistema, depois usa o padrão
     */
    private static String getEnvValue(String key, String defaultValue) {
        // Primeiro tenta ler do arquivo .env
        String value = dotenv.get(key);
        if (value != null && !value.isEmpty()) {
            return value;
        }
        // Depois tenta ler das variáveis de ambiente do sistema
        value = System.getenv(key);
        if (value != null && !value.isEmpty()) {
            return value;
        }
        // Por último usa o valor padrão
        return defaultValue;
    }

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
