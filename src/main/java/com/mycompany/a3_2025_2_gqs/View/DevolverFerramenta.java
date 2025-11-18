
package com.mycompany.a3_2025_2_gqs.View;

import javax.swing.JButton;
import javax.swing.JTextField;

import com.mycompany.a3_2025_2_gqs.Controller.EmprestimosController;

public class DevolverFerramenta extends javax.swing.JFrame {


    
    private transient final EmprestimosController controller;
    public DevolverFerramenta() {
        if (!java.awt.GraphicsEnvironment.isHeadless()) {
            initComponents();
        } else {
            // Inicializar componentes mínimos para testes em modo headless
            jDevolver = new javax.swing.JButton();
            txtNomeAmigos = new javax.swing.JTextField();
            txtNomeFerramenta = new javax.swing.JTextField();
            txtDataEmprestimo = new javax.swing.JTextField();
            txtDataDevolcao = new javax.swing.JTextField();
            txtId = new javax.swing.JTextField();
        }
        controller = new EmprestimosController(this);
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        jDevolver = new javax.swing.JButton();
        txtNomeAmigos = new javax.swing.JTextField();
        txtNomeFerramenta = new javax.swing.JTextField();
        txtDataEmprestimo = new javax.swing.JTextField();
        txtDataDevolcao = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jDevolver.setText("Devolver");
        jDevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDevolverActionPerformed(evt);
            }
        });

        txtId.setVisible(false);
        txtId.setText("NÃO MEXER NESTE BOTÃO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDevolver)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDataDevolcao)
                        .addComponent(txtDataEmprestimo)
                        .addComponent(txtNomeFerramenta)
                        .addComponent(txtNomeAmigos)))
                .addContainerGap(175, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomeAmigos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomeFerramenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDataEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDataDevolcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jDevolver)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        pack();
    }

    private void jDevolverActionPerformed(java.awt.event.ActionEvent evt) {
        controller.devolveFerramenta();
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DevolverFerramenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DevolverFerramenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DevolverFerramenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DevolverFerramenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DevolverFerramenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jDevolver;
    private javax.swing.JTextField txtDataDevolcao;
    private javax.swing.JTextField txtDataEmprestimo;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNomeAmigos;
    private javax.swing.JTextField txtNomeFerramenta;
    // End of variables declaration//GEN-END:variables

    public JButton getjDevolver() {
        return jDevolver;
    }

    public void setjDevolver(JButton jDevolver) {
        this.jDevolver = jDevolver;
    }

    public JTextField getTxtDataDevolcao() {
        return txtDataDevolcao;
    }

    public void setTxtDataDevolcao(JTextField txtDataDevolcao) {
        this.txtDataDevolcao = txtDataDevolcao;
    }

    public JTextField getTxtDataEmprestimo() {
        return txtDataEmprestimo;
    }

    public void setTxtDataEmprestimo(JTextField txtDataEmprestimo) {
        this.txtDataEmprestimo = txtDataEmprestimo;
    }


    public JTextField getTxtId() {
        return txtId;
    }

    public void setTxtId(JTextField txtId) {
        this.txtId = txtId;
    }

    public JTextField getTxtNomeAmigos() {
        return txtNomeAmigos;
    }

    public void setTxtNomeAmigos(JTextField txtNomeAmigos) {
        this.txtNomeAmigos = txtNomeAmigos;
    }

    public JTextField getTxtNomeFerramenta() {
        return txtNomeFerramenta;
    }

    public void setTxtNomeFerramenta(JTextField txtNomeFerramenta) {
        this.txtNomeFerramenta = txtNomeFerramenta;
    }



}
