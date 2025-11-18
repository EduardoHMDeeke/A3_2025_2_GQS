package com.mycompany.a3_2025_2_gqs.DAO;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {

    private static final Logger LOGGER = Logger.getLogger(Conexao.class.getName());

    // Carrega variáveis do arquivo .env (se existir) - lazy loading
    private static Dotenv dotenv = null;

    // Configurações padrão apenas para host e porta (não sensíveis)
    private static final String DEFAULT_HOST = "localhost";
    private static final String DEFAULT_PORT = "3306";

    // Lazy initialization - só carrega quando necessário
    private static Dotenv getDotenv() {
        if (dotenv == null) {
            dotenv = Dotenv.configure()
                    .ignoreIfMissing()
                    .load();
        }
        return dotenv;
    }

    /**
     * Obtém valor do .env, depois do sistema, depois usa o padrão (apenas para valores não sensíveis)
     */
    private static String getEnvValue(String key, String defaultValue) {
        Dotenv env = getDotenv();
        // Primeiro tenta ler do arquivo .env
        String value = env.get(key);
        if (value != null && !value.isEmpty()) {
            return value;
        }
        // Depois tenta ler das variáveis de ambiente do sistema
        value = System.getenv(key);
        if (value != null && !value.isEmpty()) {
            return value;
        }
        // Por último usa o valor padrão (apenas para host e porta)
        return defaultValue;
    }

    /**
     * Obtém valor obrigatório do .env ou variáveis de ambiente (para valores sensíveis)
     * Lança exceção se não encontrar
     */
    private static String getRequiredEnvValue(String key) {
        Dotenv env = getDotenv();
        // Primeiro tenta ler do arquivo .env
        String value = env.get(key);
        if (value != null && !value.isEmpty()) {
            return value;
        }
        // Depois tenta ler das variáveis de ambiente do sistema
        value = System.getenv(key);
        if (value != null && !value.isEmpty()) {
            return value;
        }
        // Se não encontrar, lança exceção informando que é obrigatório
        throw new IllegalStateException(
            String.format("Variável de ambiente obrigatória '%s' não encontrada. " +
                         "Configure no arquivo .env ou nas variáveis de ambiente do sistema.", key)
        );
    }

    public Connection getConnection() {
        // Lê as variáveis apenas quando necessário (lazy loading)
        String dbHost = getEnvValue("DB_HOST", DEFAULT_HOST);
        String dbPort = getEnvValue("DB_PORT", DEFAULT_PORT);
        String dbName = getRequiredEnvValue("DB_NAME");
        String dbUser = getRequiredEnvValue("DB_USER");
        String dbPassword = getRequiredEnvValue("DB_PASSWORD");
        
        String url = String.format("jdbc:mysql://%s:%s/%s?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", 
                                   dbHost, dbPort, dbName);
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, dbUser, dbPassword);
            LOGGER.info("Conexão com banco de dados estabelecida com sucesso");
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Erro ao conectar ao banco de dados: " + url, ex);
            LOGGER.log(Level.SEVERE, "Usuário: " + dbUser, ex);
        }
        return conn;
    }
}
