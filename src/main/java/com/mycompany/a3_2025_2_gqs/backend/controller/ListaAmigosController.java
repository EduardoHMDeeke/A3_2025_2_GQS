package com.mycompany.a3_2025_2_gqs.backend.controller;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.mycompany.a3_2025_2_gqs.backend.model.Amigos;
import com.mycompany.a3_2025_2_gqs.backend.repository.AmigosDAO;
import com.mycompany.a3_2025_2_gqs.backend.repository.Conexao;
import com.mycompany.a3_2025_2_gqs.frontend.view.TelaPrincipal;

import java.util.ArrayList;

/**
 *
 * @author Guilherme
 */
public class ListaAmigosController {

    private TelaPrincipal view;

    public ListaAmigosController(TelaPrincipal view) {
        this.view = view;
    }

    public void listarAmigos() {
        //Faz a coenxao com a classe e manda os dados para a tela dentro da tabela;
        try {
            Connection conexao = new Conexao().getConnection();
            AmigosDAO amigosdao = new AmigosDAO(conexao);

            DefaultTableModel model = (DefaultTableModel) view.getTable_amigos().getModel();
            model.setNumRows(0);

            ArrayList<Amigos> lista = amigosdao.listarAmigos();

            for (int i = 0; i < lista.size(); i++) {
                model.addRow(new Object[]{
                    lista.get(i).getId(),
                    lista.get(i).getNome(),
                    lista.get(i).getEmail(),
                    lista.get(i).getTelefone(),});

            }

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(null, "Erro em listar Amigos" + erro);

        }
            
       
    }
    
     
}
