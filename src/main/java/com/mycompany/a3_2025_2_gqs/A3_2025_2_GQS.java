package com.mycompany.a3_2025_2_gqs;

import java.util.logging.Logger;

/**
 * Classe principal da aplicação A3_2025_2_GQS.
 * Responsável por inicializar o programa.
 * 
 * <p>Esta classe demonstra o uso de um logger em vez de {@code System.out}
 * conforme recomendado por ferramentas de qualidade como o Sonar.</p>
 * 
 */
public class A3_2025_2_GQS {

    /**
     * Logger da classe para registro de eventos e mensagens.
     */
    private static final Logger LOGGER = Logger.getLogger(A3_2025_2_GQS.class.getName());

    /**
     * Método principal da aplicação.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        LOGGER.info("Hello World!");
    }
}
