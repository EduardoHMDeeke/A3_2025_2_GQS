package com.mycompany.a3_2025_2_gqs.backend.controller;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import com.mycompany.a3_2025_2_gqs.backend.model.Amigos;
import com.mycompany.a3_2025_2_gqs.backend.repository.AmigosDAO;
import com.mycompany.a3_2025_2_gqs.backend.utils.database.mysql.MySqlConnectionFactory;
import com.mycompany.a3_2025_2_gqs.frontend.view.RegistrosAmigos;

/**
 *
 * @author Guilherme
 */
public class RegistrosAmigosController {

    private RegistrosAmigos view;

    public RegistrosAmigosController(RegistrosAmigos view) {
        this.view = view;
    }

    public void  registrarAmigo() {
        String nome = view.getTxtnome().getText();
        String email = view.getTxtemail().getText();
        String telefone = view.getTxttelefone().getText();

        Amigos amigos = new Amigos(nome, email, telefone);

        if (view.getTxtnome().getText().equals("") || view.getTxtemail().getText().equals("") || view.getTxttelefone().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe todos os dados para efetuar o cadastro!");
        } else {
            try (java.sql.Connection conexao = MySqlConnectionFactory.getConnection()) {
                AmigosDAO amigosDAO = new AmigosDAO(conexao);
                amigosDAO.insertBD(amigos);
                JOptionPane.showMessageDialog(null, "AMIGO CADASTRADO COM SUCESSO!");
                view.getTxtnome().setText("");
                view.getTxtemail().setText("");
                view.getTxttelefone().setText("");

            } catch (SQLException ex) {
                Logger.getLogger(RegistrosAmigos.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar o usuario" + ex);
            }
        }

    }

    public void updateAmigo() {
        if (view.getTxtnome().getText().equals("") || view.getTxtemail().getText().equals("") || view.getTxttelefone().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe todos os dados para alterar uma Ferramenta!");
        } else {
            int id = Integer.parseInt(view.getTxtId().getText());
            String nome = view.getTxtnome().getText();
            String email = view.getTxtemail().getText();
            String telefone = view.getTxttelefone().getText();

            Amigos amigos = new Amigos(nome, email, telefone);
            try (java.sql.Connection conexao = MySqlConnectionFactory.getConnection()) {
                AmigosDAO amigosDAO = new AmigosDAO(conexao);
                amigosDAO.UpdateAmigos(amigos, id);
                JOptionPane.showMessageDialog(null, "AMIGO ALTERADO COM SUCESSO!");
                view.getTxtnome().setText("");
                view.getTxtemail().setText("");
                view.getTxttelefone().setText("");
            } catch (SQLException ex) {
                Logger.getLogger(RegistrosAmigos.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Erro ao atualizar o usuario" + ex);
            }
        }
    }

    public void deleteAmigo() {
        int id = Integer.parseInt(view.getTxtId().getText());
        try (java.sql.Connection conexao = MySqlConnectionFactory.getConnection()) {
            AmigosDAO amigosDAO = new AmigosDAO(conexao);
            amigosDAO.deleteAmigos(id);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrosAmigos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao deletar o usuario" + ex);
        }
    }
}
