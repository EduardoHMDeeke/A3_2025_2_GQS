package com.mycompany.a3_2025_2_gqs.Controller;


/**
* Controlador responsável pelas operações relacionadas aos empréstimos de ferramentas.
* Inclui a funcionalidade de devolução de ferramentas.
*/
import java.sql.*;
import javax.swing.JOptionPane;


import com.mycompany.a3_2025_2_gqs.DAO.Conexao;
import com.mycompany.a3_2025_2_gqs.DAO.EmprestimosDAO;
import com.mycompany.a3_2025_2_gqs.DAO.FerramentaDAO;
import com.mycompany.a3_2025_2_gqs.Model.Emprestimos;
import com.mycompany.a3_2025_2_gqs.Model.Ferramentas;
import com.mycompany.a3_2025_2_gqs.View.DevolverFerramenta;


/**
* Classe responsável por controlar as operações de empréstimos.
*/
public class EmprestimosController {


/**
* Referência para a tela de devolução de ferramenta.
*/
private DevolverFerramenta view;


/**
* Construtor da classe EmprestimosController.
*
* @param view Instância da tela de devolução de ferramenta.
*/
public EmprestimosController(DevolverFerramenta view) {
this.view = view;
}


/**
* Realiza o processo de devolução de uma ferramenta.
* Atualiza o status do empréstimo e da ferramenta correspondente.
*/
public void devolveFerramenta() {
try {
int id = Integer.parseInt(view.getTxtId().getText());
Connection conexao = new Conexao().getConnection();
EmprestimosDAO emprestimosdao = new EmprestimosDAO(conexao);


// Atualiza o status do empréstimo para devolvido
emprestimosdao.updateEmprestimos(0, id);


// Busca informações do empréstimo atualizado
Connection conexao1 = new Conexao().getConnection();
emprestimosdao = new EmprestimosDAO(conexao1);
Emprestimos emprestimos = emprestimosdao.buscarEmprestimo(id);


// Atualiza o status da ferramenta para disponível
Connection conexao2 = new Conexao().getConnection();
FerramentaDAO ferramentadao = new FerramentaDAO(conexao2);
ferramentadao.updateStatus(0, emprestimos.getIdFerramentas());
} catch (SQLException e) {
JOptionPane.showMessageDialog(null, "Erro devolver ferramenta");
}
}


}
