package com.mycompany.a3_2025_2_gqs.backend.controller;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.mycompany.a3_2025_2_gqs.backend.dto.EmprestimosDTO;
import com.mycompany.a3_2025_2_gqs.backend.model.Amigos;
import com.mycompany.a3_2025_2_gqs.backend.model.Emprestimos;
import com.mycompany.a3_2025_2_gqs.backend.model.Ferramentas;
import com.mycompany.a3_2025_2_gqs.backend.repository.AmigosDAO;
import com.mycompany.a3_2025_2_gqs.backend.repository.EmprestimosDAO;
import com.mycompany.a3_2025_2_gqs.backend.repository.FerramentaDAO;
import com.mycompany.a3_2025_2_gqs.backend.utils.database.mysql.MySqlConnectionFactory;
import com.mycompany.a3_2025_2_gqs.frontend.view.TelaPrincipal;

import java.util.ArrayList;

public class ListaAmigosFerramentasController {

    private TelaPrincipal view;

    public ListaAmigosFerramentasController(TelaPrincipal view) {
        this.view = view;
    }

    public void listarAmigos() {
        try {
            try (Connection conexao = MySqlConnectionFactory.getConnection()) {
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
            }

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro em listar Amigos" + erro);

        }

    }

    public void listarFerramentas() {
        try {
            try (Connection conexao = MySqlConnectionFactory.getConnection()) {
                FerramentaDAO ferramentaDAO = new FerramentaDAO(conexao);

                DefaultTableModel model = (DefaultTableModel) view.getTable_ferramentas().getModel();
                model.setNumRows(0);

                ArrayList<Ferramentas> lista = ferramentaDAO.listarFerramentas();

                for (int i = 0; i < lista.size(); i++) {
                    model.addRow(new Object[]{
                        lista.get(i).getId(),
                        lista.get(i).getNome(),
                        lista.get(i).getMarca(),
                        lista.get(i).getPreco(),});

                }
            }

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro em listar Ferramentas" + erro);

        }

    }

    public void listarEmprestimos() {
        try {
            ArrayList<Ferramentas> listaFerramentas;
            try (Connection conexaoFerramentas = MySqlConnectionFactory.getConnection()) {
                FerramentaDAO ferramentaDAO = new FerramentaDAO(conexaoFerramentas);
                listaFerramentas = ferramentaDAO.listarFerramentas();
            }

            ArrayList<Amigos> listaAmigos;
            try (Connection conexaoAmigos = MySqlConnectionFactory.getConnection()) {
                AmigosDAO amigosDAO = new AmigosDAO(conexaoAmigos);
                listaAmigos = amigosDAO.listarAmigos();
            }

            ArrayList<Emprestimos> listaBD;
            try (Connection conexaoEmprestimos = MySqlConnectionFactory.getConnection()) {
                EmprestimosDAO emprestimodao = new EmprestimosDAO(conexaoEmprestimos);
                listaBD = emprestimodao.listarEmprestimos();
            }

            DefaultTableModel model = (DefaultTableModel) view.getTabelaEmprestimo().getModel();
            model.setNumRows(0);

            ArrayList<EmprestimosDTO> lista = new ArrayList<>();

            for (Emprestimos emprestimos : listaBD) {

                Ferramentas ferramentas = buscarFerramentas(emprestimos.getIdFerramentas(), listaFerramentas);
                Amigos amigos = busqueAmigo(emprestimos.getIdAmigos(), listaAmigos);
                System.out.println(amigos);
                if (amigos != null && ferramentas != null && emprestimos.getEstaEmprestada() == 1) {
                    EmprestimosDTO emprestimosDTO = new EmprestimosDTO(emprestimos.getId(), amigos.getNome(), ferramentas.getNome(), com.mycompany.a3_2025_2_gqs.backend.utils.Util.converterData(emprestimos.getDataDevolucao()), com.mycompany.a3_2025_2_gqs.backend.utils.Util.converterData(emprestimos.getDataEmprestimo()));
                    model.addRow(new Object[]{
                        emprestimosDTO.getId(),
                        emprestimosDTO.getAmigo(),
                        emprestimosDTO.getFerramenta(),
                        emprestimosDTO.getDataEmprestimo(),
                        emprestimosDTO.getDataDevolucao()

                    });
                }

            }

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro em listar Ferramentas" + erro);

        }

    }

    private Amigos busqueAmigo(int id, ArrayList<Amigos> listaAmigos) {
        Amigos amigos = null;
        for (Amigos amigo : listaAmigos) {
            if (amigo.getId() == id) {
                amigos = amigo;

            }

        }
        return amigos;

    }

    private Ferramentas buscarFerramentas(int id, ArrayList<Ferramentas> listaFerramentas) {
        Ferramentas ferramentas = null;
        for (Ferramentas ferramenta : listaFerramentas) {

            if (ferramenta.getId() == id) {
                ferramentas = ferramenta;

            }

        }
        return ferramentas;

    }
}
