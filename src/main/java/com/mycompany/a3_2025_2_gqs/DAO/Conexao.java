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

    // Configurações padrão apenas para host e porta (não sensíveis)
    private static final String DEFAULT_HOST = "localhost";
    private static final String DEFAULT_PORT = "3306";

    // Lê do .env, depois variáveis de ambiente do sistema
    // Para valores sensíveis (usuário e senha), NÃO usa valores padrão
    private static final String DB_HOST = getEnvValue("DB_HOST", DEFAULT_HOST);
    private static final String DB_PORT = getEnvValue("DB_PORT", DEFAULT_PORT);
    private static final String DB_NAME = getRequiredEnvValue("DB_NAME");
    private static final String DB_USER = getRequiredEnvValue("DB_USER");
    private static final String DB_PASSWORD = getRequiredEnvValue("DB_PASSWORD");

    private static final String URL = String.format("jdbc:mysql://%s:%s/%s?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", 
                                                     DB_HOST, DB_PORT, DB_NAME);

    /**
     * Obtém valor do .env, depois do sistema, depois usa o padrão (apenas para valores não sensíveis)
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
        // Por último usa o valor padrão (apenas para host e porta)
        return defaultValue;
    }

    /**
     * Obtém valor obrigatório do .env ou variáveis de ambiente (para valores sensíveis)
     * Lança exceção se não encontrar
     */
    private static String getRequiredEnvValue(String key) {
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
        // Se não encontrar, lança exceção informando que é obrigatório
        throw new IllegalStateException(
            String.format("Variável de ambiente obrigatória '%s' não encontrada. " +
                         "Configure no arquivo .env ou nas variáveis de ambiente do sistema.", key)
        );
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
