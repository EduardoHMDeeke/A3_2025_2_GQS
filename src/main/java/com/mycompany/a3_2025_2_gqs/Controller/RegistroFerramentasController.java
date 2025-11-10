package com.mycompany.a3_2025_2_gqs.Controller;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import javax.swing.JOptionPane;

import com.mycompany.a3_2025_2_gqs.DAO.Conexao;
import com.mycompany.a3_2025_2_gqs.DAO.FerramentaDAO;
import com.mycompany.a3_2025_2_gqs.Model.Ferramentas;
import com.mycompany.a3_2025_2_gqs.View.RegistroFerramentas;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author NEX
 */
public class RegistroFerramentasController {

    private RegistroFerramentas view;

    public RegistroFerramentasController(RegistroFerramentas view) {
        this.view = view;
    }

    public void registrarFerramenta() {
        String nome = view.getTxtnomeFerramenta().getText();
        String marca = view.getTxtMarca().getText();
        String valor = view.getTxtPreco().getText();

        Ferramentas ferramentas = new Ferramentas(nome, marca, valor);

        if (view.getTxtnomeFerramenta().getText().equals("") || view.getTxtMarca().getText().equals("") || view.getTxtPreco().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe todos os dados para efetuar o cadastro!");
        } else {
            try {
                Connection conexao = new Conexao().getConnection();
                FerramentaDAO ferramentaDAO = new FerramentaDAO(conexao);
                ferramentaDAO.insertBD(ferramentas);
                JOptionPane.showMessageDialog(null, "FERRAMENTA CADASTRADA COM SUCESSO!");
                view.getTxtnomeFerramenta().setText("");
                view.getTxtMarca().setText("");
                view.getTxtPreco().setText("");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar o usuario" + ex);
            }
        }

    }

    public void updateFerramenta() {

        if (view.getTxtnomeFerramenta().getText().equals("") || view.getTxtMarca().getText().equals("") || view.getTxtPreco().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe todos os dados para alterar uma Ferramenta!");
        } else {
            int id = Integer.parseInt(view.getTxtId().getText());
            String nome = view.getTxtnomeFerramenta().getText();
            String marca = view.getTxtMarca().getText();
            String preco = view.getTxtPreco().getText();

            Ferramentas ferramentas = new Ferramentas(nome, marca, preco);
            Connection conexao = new Conexao().getConnection();
            FerramentaDAO ferramentaDAO = new FerramentaDAO(conexao);
            ferramentaDAO.updateFerramenta(ferramentas, id);
            JOptionPane.showMessageDialog(null, "FERRAMENTA ALTERADA COM SUCESSO!");
            view.getTxtnomeFerramenta().setText("");
            view.getTxtMarca().setText("");
            view.getTxtPreco().setText("");
        }
    }
    
      public void deleteFerramenta() {
        int id = Integer.parseInt(view.getTxtId().getText());
        Ferramentas ferramentas = new Ferramentas();
        Connection conexao = new Conexao().getConnection();
        FerramentaDAO ferramentaDAO = new FerramentaDAO(conexao);
        ferramentaDAO.deleteFerramentas(id);
    }
}
