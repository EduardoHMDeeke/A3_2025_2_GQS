package com.mycompany.a3_2025_2_gqs.Controller;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.mycompany.a3_2025_2_gqs.DAO.AmigosDAO;
import com.mycompany.a3_2025_2_gqs.DAO.Conexao;
import com.mycompany.a3_2025_2_gqs.DAO.EmprestimosDAO;
import com.mycompany.a3_2025_2_gqs.DAO.FerramentaDAO;
import com.mycompany.a3_2025_2_gqs.DTO.EmprestimosDTO;
import com.mycompany.a3_2025_2_gqs.Model.Amigos;
import com.mycompany.a3_2025_2_gqs.Model.Emprestimos;
import com.mycompany.a3_2025_2_gqs.Model.Ferramentas;
import com.mycompany.a3_2025_2_gqs.View.TelaPrincipal;
import com.mycompany.a3_2025_2_gqs.View.ViewEmprestimos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class ListaAmigosFerramentasController {

    private TelaPrincipal view;

    public ListaAmigosFerramentasController(TelaPrincipal view) {
        this.view = view;
    }

    public void listarAmigos() {
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

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro em listar Amigos" + erro);

        }

    }

    public void listarFerramentas() {
        try {
            Connection conexao = new Conexao().getConnection();
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

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro em listar Ferramentas" + erro);

        }

    }

    public void listarEmprestimos() {
        try {
            Connection conexao1 = new Conexao().getConnection();
            FerramentaDAO ferramentaDAO = new FerramentaDAO(conexao1);

            ArrayList<Ferramentas> listaFerramentas = ferramentaDAO.listarFerramentas();

            Connection conexao2 = new Conexao().getConnection();

            AmigosDAO amigosDAO = new AmigosDAO(conexao2);
            ArrayList<Amigos> listaAmigos = amigosDAO.listarAmigos();

            Connection conexao = new Conexao().getConnection();
            EmprestimosDAO emprestimodao = new EmprestimosDAO(conexao);

            DefaultTableModel model = (DefaultTableModel) view.getTabelaEmprestimo().getModel();
            model.setNumRows(0);

            ArrayList<EmprestimosDTO> lista = new ArrayList<>();
            ArrayList<Emprestimos> listaBD = emprestimodao.listarEmprestimos();

            for (Emprestimos emprestimos : listaBD) {

                Ferramentas ferramentas = buscarFerramentas(emprestimos.getIdFerramentas(), listaFerramentas);
                Amigos amigos = busqueAmigo(emprestimos.getIdAmigos(), listaAmigos);
                System.out.println(amigos);
                if (amigos != null && ferramentas != null && emprestimos.getEstaEmprestada() == 1) {
                    EmprestimosDTO emprestimosDTO = new EmprestimosDTO(emprestimos.getId(), amigos.getNome(), ferramentas.getNome(), com.mycompany.a3_2025_2_gqs.Util.Util.converterData(emprestimos.getDataDevolucao()), com.mycompany.a3_2025_2_gqs.Util.Util.converterData(emprestimos.getDataEmprestimo()));
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
