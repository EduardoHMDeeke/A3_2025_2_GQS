package com.mycompany.a3_2025_2_gqs.backend.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import com.mycompany.a3_2025_2_gqs.backend.model.Ferramentas;
import com.mycompany.a3_2025_2_gqs.backend.repository.FerramentaDAO;
import com.mycompany.a3_2025_2_gqs.backend.utils.database.mysql.MySqlConnectionFactory;
import com.mycompany.a3_2025_2_gqs.frontend.view.RegistroFerramentas;

public class RegistroFerramentasController {

    private RegistroFerramentas view;

    public RegistroFerramentasController(RegistroFerramentas view) {
        this.view = view;
    }

    public void registrarFerramenta() {
        String nome = view.getTxtnomeFerramenta().getText();
        String marca = view.getTxtMarca().getText();
        String valor = view.getTxtPreco().getText();

        if (nome.isEmpty() || marca.isEmpty() || valor.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe todos os dados para efetuar o cadastro!");
            return;
        }
        
        Ferramentas ferramentas = new Ferramentas(nome, marca, valor);

        try (Connection conexao = MySqlConnectionFactory.getConnection()) {
            FerramentaDAO ferramentaDAO = new FerramentaDAO(conexao);
            ferramentaDAO.insertBD(ferramentas);
            
            JOptionPane.showMessageDialog(null, "FERRAMENTA CADASTRADA COM SUCESSO!");
            
            view.getTxtnomeFerramenta().setText("");
            view.getTxtMarca().setText("");
            view.getTxtPreco().setText("");

        } catch (SQLException ex) {
            Logger.getLogger(RegistroFerramentasController.class.getName()).log(Level.SEVERE, "Erro ao cadastrar a ferramenta", ex);
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar a ferramenta. Detalhes: " + ex.getMessage());
        }
    }

    public void updateFerramenta() {
        String nome = view.getTxtnomeFerramenta().getText();
        String marca = view.getTxtMarca().getText();
        String preco = view.getTxtPreco().getText();
        String idText = view.getTxtId().getText();

        if (nome.isEmpty() || marca.isEmpty() || preco.isEmpty() || idText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe todos os dados, incluindo o ID, para alterar a Ferramenta!");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "O ID deve ser um nÃºmero inteiro vÃ¡lido.");
            return;
        }

        Ferramentas ferramentas = new Ferramentas(nome, marca, preco);
        
        try (Connection conexao = MySqlConnectionFactory.getConnection()) {
            FerramentaDAO ferramentaDAO = new FerramentaDAO(conexao);
            ferramentaDAO.updateFerramenta(ferramentas, id);
            
            JOptionPane.showMessageDialog(null, "FERRAMENTA ALTERADA COM SUCESSO!");
            
            view.getTxtnomeFerramenta().setText("");
            view.getTxtMarca().setText("");
            view.getTxtPreco().setText("");
            view.getTxtId().setText("");

        } catch (SQLException ex) {
            Logger.getLogger(RegistroFerramentasController.class.getName()).log(Level.SEVERE, "Erro ao atualizar a ferramenta (ID: " + id + ")", ex);
            JOptionPane.showMessageDialog(null, "Erro ao alterar a ferramenta. Detalhes: " + ex.getMessage());
        }
    }
    
    public void deleteFerramenta() {
        String idText = view.getTxtId().getText();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o ID da ferramenta a ser deletada.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "O ID deve ser um nÃºmero inteiro vÃ¡lido.");
            return;
        }

        try (Connection conexao = MySqlConnectionFactory.getConnection()) {
            FerramentaDAO ferramentaDAO = new FerramentaDAO(conexao);
            ferramentaDAO.deleteFerramentas(id);
            JOptionPane.showMessageDialog(null, "FERRAMENTA DELETADA COM SUCESSO!");
            view.getTxtId().setText("");

        } catch (SQLException ex) {
            Logger.getLogger(RegistroFerramentasController.class.getName()).log(Level.SEVERE, "Erro ao deletar a ferramenta (ID: " + id + ")", ex);
            JOptionPane.showMessageDialog(null, "Erro ao deletar a ferramenta. Detalhes: " + ex.getMessage());
        }
    }
}
