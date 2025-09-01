/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.Conexao;
import DAO.EmprestimosDAO;
import DAO.FerramentaDAO;
import Model.Emprestimos;
import Model.Ferramentas;
import View.DevolverFerramenta;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author GUI
 */
public class EmprestimosController {
    
    private DevolverFerramenta view;
    
    public EmprestimosController(DevolverFerramenta view) {
        this.view = view;
    }
    
    public void devolveFerramenta() {
        try {
            int id = Integer.parseInt(view.getTxtId().getText());
            Connection conexao = new Conexao().getConnection();
            EmprestimosDAO emprestimosdao = new EmprestimosDAO(conexao);
            emprestimosdao.updateEmprestimos(0, id);
            System.out.println("Update emprestimo realizado com sucesso");
            Connection conexao1 = new Conexao().getConnection();
            emprestimosdao = new EmprestimosDAO(conexao1);
            Emprestimos emprestimos = emprestimosdao.buscarEmprestimo(id);
            System.out.println(emprestimos);
            Connection conexao2 = new Conexao().getConnection();
            FerramentaDAO ferramentadao = new FerramentaDAO(conexao2);
            ferramentadao.updateStatus(0, emprestimos.getIdFerramentas());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro devolver ferramenta");
        }
    }
    
}
