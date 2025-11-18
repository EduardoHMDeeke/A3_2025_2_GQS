package com.mycompany.a3_2025_2_gqs.View;

import java.awt.CardLayout;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.mycompany.a3_2025_2_gqs.Controller.ListaAmigosFerramentasController;

/*


author @Caio / @Guilherme
 */
public class TelaPrincipal extends javax.swing.JFrame {

    private boolean b_editarOuDeletar;
    private static final String FONT_NAME = "Segoe UI Black"; 
    private static final String AMIGO = "Cadastrar Amigo"; 
    private static final String LOGO = "/Icons/ToolTracker Logo Oficial.png"; 
    private static final String REFRESH = "Refresh"; 
    private static final String AMIGO_ESTOU_AQUI = "Lista Amigos"; 
    private static final String FERRAMENTA = "Lista Ferramentas"; 
    private static final String RELATORIO = "Relatorio"; 



    
    private transient final ListaAmigosFerramentasController controller;

    private boolean cardHome = false;
    private boolean cardAmigos = false;
    private boolean cardFerramentas = false;
    private boolean cardRelatorio = false;

    public TelaPrincipal() {
        initComponents();
        //Colocando o icone do Sistema/Janela
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Icons/ToolTracker_logo-removebg-preview.png")).getImage());

        controller = new ListaAmigosFerramentasController(this);

        // Codigo para iniciar em tela cheia
        this.setExtendedState(MAXIMIZED_BOTH);

        controller.listarAmigos();
        controller.listarFerramentas();
        controller.listarEmprestimos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPop_botoes = new javax.swing.JPopupMenu();
        popupHome = new javax.swing.JMenuItem();
        popupAmigos = new javax.swing.JMenuItem();
        popupFerramentas = new javax.swing.JMenuItem();
        popupRelatorio = new javax.swing.JMenuItem();
        popupOpcoes = new javax.swing.JMenuItem();
        popupSair = new javax.swing.JMenuItem();
        JPop_Amigos = new javax.swing.JPopupMenu();
        popupCadastrar = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        JPop_Home = new javax.swing.JPopupMenu();
        popup001 = new javax.swing.JMenuItem();
        JP_Principal = new javax.swing.JPanel();
        JP_Home = new javax.swing.JPanel();
        realizarEmprestimo = new javax.swing.JButton();
        CadastrarAmigoHome = new javax.swing.JButton();
        CadastrarFerramentaHome = new javax.swing.JButton();
        editarEmprestimo = new javax.swing.JButton();
        devolverEmprestimo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelaEmprestimo = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        b_refreshEmprestimos = new javax.swing.JButton();
        JP_ListaAmigos = new javax.swing.JPanel();
        JL_ListaAmigos = new javax.swing.JLabel();
        b_cadastrarAmigos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_amigos = new javax.swing.JTable();
        b_editarAmigo = new javax.swing.JButton();
        deleteAmigo = new javax.swing.JButton();
        atualizarTabela = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        JP_ListaFerramentas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_ferramentas = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        b_cadastrarFerramenta = new javax.swing.JButton();
        b_editarFerramenta = new javax.swing.JButton();
        b_apagarFerramenta = new javax.swing.JButton();
        AtualizarFerramentas = new javax.swing.JButton();
        JP_Relatorio = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        JP_Lista = new javax.swing.JPanel();
        b_Home = new javax.swing.JButton();
        b_ListaAmigos = new javax.swing.JButton();
        b_ListaFerramentas = new javax.swing.JButton();
        b_relatorio = new javax.swing.JButton();
        jMudarTema = new javax.swing.JButton();
        b_opcoes = new javax.swing.JButton();

        popupHome.setText("Home");
        popupHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popupHomeActionPerformed(evt);
            }
        });
        JPop_botoes.add(popupHome);

        popupAmigos.setText("Amigos");
        popupAmigos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popupAmigosActionPerformed(evt);
            }
        });
        JPop_botoes.add(popupAmigos);

        popupFerramentas.setText("Ferramentas");
        popupFerramentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popupFerramentasActionPerformed(evt);
            }
        });
        JPop_botoes.add(popupFerramentas);

        popupRelatorio.setText("Relatório");
        popupRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popupRelatorioActionPerformed(evt);
            }
        });
        JPop_botoes.add(popupRelatorio);

        popupOpcoes.setText("Opções");
        popupOpcoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popupOpcoesActionPerformed(evt);
            }
        });
        JPop_botoes.add(popupOpcoes);

        popupSair.setText("Sair");
        popupSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popupSairActionPerformed(evt);
            }
        });
        JPop_botoes.add(popupSair);

        popupCadastrar.setText(AMIGO);
        popupCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popupCadastrarActionPerformed(evt);
            }
        });
        JPop_Amigos.add(popupCadastrar);

        jMenuItem2.setText("...");
        JPop_Amigos.add(jMenuItem2);

        popup001.setText("...");
        JPop_Home.add(popup001);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela Principal");
        setIconImages(null);

        JP_Principal.setPreferredSize(new java.awt.Dimension(1575, 300));
        JP_Principal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                JP_PrincipalMouseReleased(evt);
            }
        });
        JP_Principal.setLayout(new java.awt.CardLayout());

        realizarEmprestimo.setText("Realizar Emprestimo");
        realizarEmprestimo.setFocusPainted(false);
        realizarEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                realizarEmprestimoActionPerformed(evt);
            }
        });

        CadastrarAmigoHome.setText(AMIGO);
        CadastrarAmigoHome.setFocusPainted(false);
        CadastrarAmigoHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadastrarAmigoHomeActionPerformed(evt);
            }
        });

        CadastrarFerramentaHome.setText("Cadastrar Ferramenta");
        CadastrarFerramentaHome.setFocusPainted(false);
        CadastrarFerramentaHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CadastrarFerramentaHomeActionPerformed(evt);
            }
        });

        editarEmprestimo.setText("Editar Emprestimo");
        editarEmprestimo.setFocusPainted(false);
        editarEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarEmprestimoActionPerformed(evt);
            }
        });

        devolverEmprestimo.setText("Devolver Emprestimo");
        devolverEmprestimo.setFocusPainted(false);
        devolverEmprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                devolverEmprestimoActionPerformed(evt);
            }
        });

        tabelaEmprestimo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Amigo", "Ferramenta", "Data de emprestimo", "Data de devolução"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tabelaEmprestimo);
        if (tabelaEmprestimo.getColumnModel().getColumnCount() > 0) {
            tabelaEmprestimo.getColumnModel().getColumn(0).setResizable(false);
            tabelaEmprestimo.getColumnModel().getColumn(1).setResizable(false);
            tabelaEmprestimo.getColumnModel().getColumn(2).setResizable(false);
            tabelaEmprestimo.getColumnModel().getColumn(3).setResizable(false);
            tabelaEmprestimo.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
        );

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("", 1, 94)); // NOI18N
        jLabel3.setText("TOOL TRACKER");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 46, -1, -1));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource(LOGO))); // NOI18N
        jLabel7.setToolTipText("");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(799, 0, -1, -1));

        b_refreshEmprestimos.setText(REFRESH);
        b_refreshEmprestimos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_refreshEmprestimosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_HomeLayout = new javax.swing.GroupLayout(JP_Home);
        JP_Home.setLayout(JP_HomeLayout);
        JP_HomeLayout.setHorizontalGroup(
            JP_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_HomeLayout.createSequentialGroup()
                .addGroup(JP_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_HomeLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(JP_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(JP_HomeLayout.createSequentialGroup()
                                    .addComponent(devolverEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_HomeLayout.createSequentialGroup()
                                    .addGroup(JP_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(realizarEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(editarEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(69, 69, 69)))
                            .addGroup(JP_HomeLayout.createSequentialGroup()
                                .addComponent(CadastrarFerramentaHome)
                                .addGap(47, 47, 47)
                                .addComponent(CadastrarAmigoHome, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_HomeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(b_refreshEmprestimos)
                        .addGap(113, 113, 113)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        JP_HomeLayout.setVerticalGroup(
            JP_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_HomeLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JP_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JP_HomeLayout.createSequentialGroup()
                        .addComponent(b_refreshEmprestimos)
                        .addGap(74, 74, 74)
                        .addComponent(realizarEmprestimo)
                        .addGap(27, 27, 27)
                        .addComponent(editarEmprestimo)
                        .addGap(32, 32, 32)
                        .addComponent(devolverEmprestimo)
                        .addGap(76, 76, 76)
                        .addGroup(JP_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CadastrarFerramentaHome)
                            .addComponent(CadastrarAmigoHome))
                        .addGap(125, 125, 125))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 15, Short.MAX_VALUE))
        );

        JP_Principal.add(JP_Home, "Home");

        JL_ListaAmigos.setFont(new java.awt.Font(FONT_NAME, 1, 48)); // NOI18N
        JL_ListaAmigos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JL_ListaAmigos.setText("AMIGOS");

        b_cadastrarAmigos.setText(AMIGO);
        b_cadastrarAmigos.setFocusPainted(false);
        b_cadastrarAmigos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cadastrarAmigosActionPerformed(evt);
            }
        });

        table_amigos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id", "Nome", "Email", "Telefone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table_amigos);
        if (table_amigos.getColumnModel().getColumnCount() > 0) {
            table_amigos.getColumnModel().getColumn(0).setResizable(false);
        }

        b_editarAmigo.setText("Editar Amigo");
        b_editarAmigo.setFocusPainted(false);
        b_editarAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_editarAmigoActionPerformed(evt);
            }
        });

        deleteAmigo.setText("Apagar Amigo");
        deleteAmigo.setFocusPainted(false);
        deleteAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAmigoActionPerformed(evt);
            }
        });

        atualizarTabela.setText(REFRESH);
        atualizarTabela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarTabelaActionPerformed(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource(LOGO))); // NOI18N
        jLabel6.setToolTipText("");

        javax.swing.GroupLayout JP_ListaAmigosLayout = new javax.swing.GroupLayout(JP_ListaAmigos);
        JP_ListaAmigos.setLayout(JP_ListaAmigosLayout);
        JP_ListaAmigosLayout.setHorizontalGroup(
            JP_ListaAmigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_ListaAmigosLayout.createSequentialGroup()
                .addGroup(JP_ListaAmigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_ListaAmigosLayout.createSequentialGroup()
                        .addGroup(JP_ListaAmigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JP_ListaAmigosLayout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(JP_ListaAmigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(b_editarAmigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(deleteAmigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(b_cadastrarAmigos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)))
                            .addGroup(JP_ListaAmigosLayout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(JL_ListaAmigos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_ListaAmigosLayout.createSequentialGroup()
                        .addComponent(atualizarTabela)
                        .addGap(75, 75, 75)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        JP_ListaAmigosLayout.setVerticalGroup(
            JP_ListaAmigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_ListaAmigosLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(JP_ListaAmigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(JP_ListaAmigosLayout.createSequentialGroup()
                        .addComponent(JL_ListaAmigos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(atualizarTabela)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(b_cadastrarAmigos)
                        .addGap(48, 48, 48)
                        .addComponent(b_editarAmigo)
                        .addGap(49, 49, 49)
                        .addComponent(deleteAmigo)
                        .addGap(0, 206, Short.MAX_VALUE)))
                .addContainerGap())
        );

        JP_Principal.add(JP_ListaAmigos, AMIGO_ESTOU_AQUI);

        jLabel1.setFont(new java.awt.Font(FONT_NAME, 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ferramentas");

        table_ferramentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "Marca", "Preço"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table_ferramentas);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource(LOGO))); // NOI18N
        jLabel8.setToolTipText("");

        b_cadastrarFerramenta.setText("Cadastrar Ferramenta");
        b_cadastrarFerramenta.setFocusPainted(false);
        b_cadastrarFerramenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_cadastrarFerramentaActionPerformed(evt);
            }
        });

        b_editarFerramenta.setText("Editar Ferramenta");
        b_editarFerramenta.setFocusPainted(false);
        b_editarFerramenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_editarFerramentaActionPerformed(evt);
            }
        });

        b_apagarFerramenta.setText("Apagar Ferramenta");
        b_apagarFerramenta.setFocusPainted(false);
        b_apagarFerramenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_apagarFerramentaActionPerformed(evt);
            }
        });

        AtualizarFerramentas.setText(REFRESH);
        AtualizarFerramentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtualizarFerramentasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_ListaFerramentasLayout = new javax.swing.GroupLayout(JP_ListaFerramentas);
        JP_ListaFerramentas.setLayout(JP_ListaFerramentasLayout);
        JP_ListaFerramentasLayout.setHorizontalGroup(
            JP_ListaFerramentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_ListaFerramentasLayout.createSequentialGroup()
                .addGroup(JP_ListaFerramentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_ListaFerramentasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JP_ListaFerramentasLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel8))
                    .addGroup(JP_ListaFerramentasLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(b_cadastrarFerramenta, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JP_ListaFerramentasLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(b_editarFerramenta, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JP_ListaFerramentasLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(b_apagarFerramenta, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_ListaFerramentasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(AtualizarFerramentas)
                        .addGap(106, 106, 106)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)
                .addContainerGap())
        );
        JP_ListaFerramentasLayout.setVerticalGroup(
            JP_ListaFerramentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_ListaFerramentasLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(JP_ListaFerramentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(JP_ListaFerramentasLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(AtualizarFerramentas)
                        .addGap(42, 42, 42)
                        .addComponent(b_cadastrarFerramenta)
                        .addGap(61, 61, 61)
                        .addComponent(b_editarFerramenta)
                        .addGap(65, 65, 65)
                        .addComponent(b_apagarFerramenta)
                        .addGap(0, 88, Short.MAX_VALUE)))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        JP_Principal.add(JP_ListaFerramentas, FERRAMENTA);

        jLabel4.setFont(new java.awt.Font(FONT_NAME, 1, 24)); // NOI18N
        jLabel4.setText(RELATORIO);

        javax.swing.GroupLayout JP_RelatorioLayout = new javax.swing.GroupLayout(JP_Relatorio);
        JP_Relatorio.setLayout(JP_RelatorioLayout);
        JP_RelatorioLayout.setHorizontalGroup(
            JP_RelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_RelatorioLayout.createSequentialGroup()
                .addContainerGap(485, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(462, 462, 462))
        );
        JP_RelatorioLayout.setVerticalGroup(
            JP_RelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_RelatorioLayout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel4)
                .addContainerGap(646, Short.MAX_VALUE))
        );

        JP_Principal.add(JP_Relatorio, RELATORIO);

        JP_Lista.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        JP_Lista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                JP_ListaMouseReleased(evt);
            }
        });

        b_Home.setFont(new java.awt.Font(FONT_NAME, 1, 14)); // NOI18N
        b_Home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/casa_1.png"))); // NOI18N
        b_Home.setText("   Home");
        b_Home.setFocusPainted(false);
        b_Home.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        b_Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_HomeActionPerformed(evt);
            }
        });

        b_ListaAmigos.setFont(new java.awt.Font(FONT_NAME, 1, 14)); // NOI18N
        b_ListaAmigos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/amigos_lp.png"))); // NOI18N
        b_ListaAmigos.setText("   Amigos");
        b_ListaAmigos.setFocusPainted(false);
        b_ListaAmigos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        b_ListaAmigos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        b_ListaAmigos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_ListaAmigosActionPerformed(evt);
            }
        });

        b_ListaFerramentas.setFont(new java.awt.Font(FONT_NAME, 1, 12)); // NOI18N
        b_ListaFerramentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/ferramentas_lp.png"))); // NOI18N
        b_ListaFerramentas.setText("    Ferramentas");
        b_ListaFerramentas.setFocusable(false);
        b_ListaFerramentas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        b_ListaFerramentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_ListaFerramentasActionPerformed(evt);
            }
        });

        b_relatorio.setFont(new java.awt.Font(FONT_NAME, 1, 14)); // NOI18N
        b_relatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/emprestimos_lp.png"))); // NOI18N
        b_relatorio.setText("    Relatório");
        b_relatorio.setFocusable(false);
        b_relatorio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        b_relatorio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        b_relatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_relatorioActionPerformed(evt);
            }
        });

        jMudarTema.setText("Mudar Tema");
        jMudarTema.setFocusPainted(false);
        jMudarTema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMudarTemaActionPerformed(evt);
            }
        });

        b_opcoes.setFont(new java.awt.Font(FONT_NAME, 1, 14)); // NOI18N
        b_opcoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/opcoes_lp.png"))); // NOI18N
        b_opcoes.setText("    Opções");
        b_opcoes.setFocusPainted(false);
        b_opcoes.setFocusable(false);
        b_opcoes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        b_opcoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_opcoesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_ListaLayout = new javax.swing.GroupLayout(JP_Lista);
        JP_Lista.setLayout(JP_ListaLayout);
        JP_ListaLayout.setHorizontalGroup(
            JP_ListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(b_Home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b_ListaAmigos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b_ListaFerramentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b_relatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(b_opcoes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_ListaLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jMudarTema, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        JP_ListaLayout.setVerticalGroup(
            JP_ListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_ListaLayout.createSequentialGroup()
                .addComponent(b_Home, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_ListaAmigos, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_ListaFerramentas, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_relatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 288, Short.MAX_VALUE)
                .addComponent(jMudarTema)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b_opcoes, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JP_Lista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JP_Principal, javax.swing.GroupLayout.DEFAULT_SIZE, 1062, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JP_Lista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JP_Principal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //Botão de opções
    private void b_opcoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_opcoesActionPerformed
        Opcoes opcoes = new Opcoes();
        opcoes.setVisible(true);
    }//GEN-LAST:event_b_opcoesActionPerformed

    //Botão Home
    private void b_HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_HomeActionPerformed
        CardLayout CL = (CardLayout) JP_Principal.getLayout();
        CL.show(JP_Principal, "Home");

        //Para verificação do PopUp Menu
        cardAmigos = false;
        cardHome = true;
        cardFerramentas = false;
        cardRelatorio = false;
    }//GEN-LAST:event_b_HomeActionPerformed

    //Botão Amigos
    private void b_ListaAmigosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_ListaAmigosActionPerformed
        CardLayout CL = (CardLayout) JP_Principal.getLayout();
        CL.show(JP_Principal, AMIGO_ESTOU_AQUI);

        //Para verificação do PopUp Menu
        cardAmigos = true;
        cardHome = false;
        cardFerramentas = false;
        cardRelatorio = false;
    }//GEN-LAST:event_b_ListaAmigosActionPerformed

    //Botão Ferramentas
    private void b_ListaFerramentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_ListaFerramentasActionPerformed
        CardLayout CL = (CardLayout) JP_Principal.getLayout();
        CL.show(JP_Principal, FERRAMENTA);

        //Para verificação do PopUp Menu
        cardAmigos = false;
        cardHome = false;
        cardFerramentas = true;
        cardRelatorio = false;
    }//GEN-LAST:event_b_ListaFerramentasActionPerformed

    //Botão Rank
    private void b_relatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_relatorioActionPerformed
        CardLayout CL = (CardLayout) JP_Principal.getLayout();
        CL.show(JP_Principal, RELATORIO);

        // Para verificação do PopUp Menu
        cardAmigos = false;
        cardHome = false;
        cardFerramentas = false;
        cardRelatorio = true;
    }//GEN-LAST:event_b_relatorioActionPerformed

    //Código para aparecer o Pop-Up menu no Panel das opções (Home, amigos, ferramentas, rank)
    private void JP_ListaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JP_ListaMouseReleased
        if (evt.isPopupTrigger()) {
            JPop_botoes.show(this, evt.getX() + 10, evt.getY() + 30);
        }
    }//GEN-LAST:event_JP_ListaMouseReleased

    //Ação de PopUp Sair (JPanel Botões)
    private void popupSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popupSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_popupSairActionPerformed

    //Indicador de PopUps
    private void JP_PrincipalMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JP_PrincipalMouseReleased

        if ((evt.isPopupTrigger()) && (cardHome == true)) {
            JPop_Home.show(this, evt.getX() + 250, evt.getY() + 35);
        }

        if ((evt.isPopupTrigger()) && (cardAmigos == true)) {
            JPop_Amigos.show(this, evt.getX() + 250, evt.getY() + 35);
        }

        if ((evt.isPopupTrigger()) && (cardFerramentas == true)) {

        }

        if ((evt.isPopupTrigger()) && (cardRelatorio == true)) {

        }
    }//GEN-LAST:event_JP_PrincipalMouseReleased

    private void b_cadastrarAmigosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cadastrarAmigosActionPerformed
        RegistrosAmigos registroAmigos = new RegistrosAmigos();

        registroAmigos.setVisible(true);
        registroAmigos.getjBotaoCadastro().setVisible(true);
    }//GEN-LAST:event_b_cadastrarAmigosActionPerformed

    private void jMudarTemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMudarTemaActionPerformed

        try {

            // Obter o Look and Feel atual
            String currentLF = UIManager.getLookAndFeel().getClass().getName();
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.FINE, "Look and Feel atual: {0}", currentLF);

            // Verificar se o tema atual é escuro ou claro
            if (currentLF.equals("javax.swing.plaf.nimbus.NimbusLookAndFeel")) {
                // Definir algumas propriedades para o tema claro
                UIManager.put("control", new Color(214, 217, 223));
                UIManager.put("text", new Color(0, 0, 0));
                UIManager.put("nimbusBase", new Color(214, 217, 223));
                UIManager.put("nimbusBlueGrey", new Color(193, 200, 206));
                UIManager.put("nimbusFocus", new Color(115, 164, 209));
                UIManager.put("nimbusLightBackground", new Color(214, 217, 223));
                UIManager.put("nimbusOrange", new Color(191, 98, 4));
                UIManager.put("nimbusSelectedText", new Color(0, 0, 0));
                UIManager.put("nimbusSelectionBackground", new Color(104, 93, 156));
                // Se for escuro, mudar para claro
                // UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            } else {
                // Definir algumas propriedades para o tema escuro
                UIManager.put("control", new Color(68, 68, 68));
                UIManager.put("text", new Color(214, 217, 223));
                UIManager.put("nimbusBase", new Color(18, 30, 49));
                UIManager.put("nimbusBlueGrey", new Color(48, 57, 67));
                UIManager.put("nimbusFocus", new Color(115, 164, 209));
                UIManager.put("nimbusLightBackground", new Color(18, 30, 49));
                UIManager.put("nimbusOrange", new Color(191, 98, 4));
                UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
                UIManager.put("nimbusSelectionBackground", new Color(104, 93, 156));
                // Se for claro, mudar para escuro
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            }
            // Verificar se o tema atual é o Nimbus claro
            /*  if (currentLF.equals("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel")) {

            } else {

            }
        }*/

            // Atualizar a aparência da interface do usuário
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, "Erro ao mudar tema", ex);
        }

    }//GEN-LAST:event_jMudarTemaActionPerformed

    private void b_editarAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_editarAmigoActionPerformed

        int linhaSelecionada = table_amigos.getSelectedRow();

        if (linhaSelecionada != -1) {

            CarregarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma linha!");

        }


    }//GEN-LAST:event_b_editarAmigoActionPerformed

    private void atualizarTabelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizarTabelaActionPerformed
        controller.listarAmigos();
    }//GEN-LAST:event_atualizarTabelaActionPerformed

    private void deleteAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAmigoActionPerformed

        int linhaSelecionada = table_amigos.getSelectedRow();

        if (linhaSelecionada != -1) {

            camposNaoEditaveis();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um amigo que deseja excluir!");

        }


    }//GEN-LAST:event_deleteAmigoActionPerformed

    //Eventos dos PopUps Menu (JPanel Botões)
    private void popupHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popupHomeActionPerformed
        CardLayout CL = (CardLayout) JP_Principal.getLayout();
        CL.show(JP_Principal, "Home");

        //Para verificação do PopUp Menu
        cardAmigos = false;
        cardHome = true;
        cardFerramentas = false;
        cardRelatorio = false;
    }//GEN-LAST:event_popupHomeActionPerformed

    private void popupAmigosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popupAmigosActionPerformed
        CardLayout CL = (CardLayout) JP_Principal.getLayout();
        CL.show(JP_Principal, AMIGO_ESTOU_AQUI);

        //Para verificação do PopUp Menu
        cardAmigos = true;
        cardHome = false;
        cardFerramentas = false;
        cardRelatorio = false;
    }//GEN-LAST:event_popupAmigosActionPerformed

    private void popupFerramentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popupFerramentasActionPerformed
        CardLayout CL = (CardLayout) JP_Principal.getLayout();
        CL.show(JP_Principal, FERRAMENTA);

        //Para verificação do PopUp Menu
        cardAmigos = false;
        cardHome = false;
        cardFerramentas = true;
        cardRelatorio = false;
    }//GEN-LAST:event_popupFerramentasActionPerformed

    private void popupRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popupRelatorioActionPerformed
        CardLayout CL = (CardLayout) JP_Principal.getLayout();
        CL.show(JP_Principal, RELATORIO);

        // Para verificação do PopUp Menu
        cardAmigos = false;
        cardHome = false;
        cardFerramentas = false;
        cardRelatorio = true;
    }//GEN-LAST:event_popupRelatorioActionPerformed

    private void popupOpcoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popupOpcoesActionPerformed
        Opcoes opcoes = new Opcoes();
        opcoes.setVisible(true);
    }//GEN-LAST:event_popupOpcoesActionPerformed

    private void realizarEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_realizarEmprestimoActionPerformed
        ViewEmprestimos emp = null;
        try {
            emp = new ViewEmprestimos();
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        emp.setVisible(true);
    }//GEN-LAST:event_realizarEmprestimoActionPerformed

    private void b_cadastrarFerramentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_cadastrarFerramentaActionPerformed
        RegistroFerramentas registroFerramentas = new RegistroFerramentas();

        registroFerramentas.setVisible(true);
        registroFerramentas.getjBotaoCadastroFerramenta().setVisible(true);
        registroFerramentas.getjDeleteFerramenta().setVisible(false);
    }//GEN-LAST:event_b_cadastrarFerramentaActionPerformed

    private void b_editarFerramentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_editarFerramentaActionPerformed

        b_editarOuDeletar = true;

        int linhaSelecionada = table_ferramentas.getSelectedRow();

        if (linhaSelecionada != -1) {
            CarregarCamposFerramentas();

        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma linha!");

        }
    }//GEN-LAST:event_b_editarFerramentaActionPerformed

    private void AtualizarFerramentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AtualizarFerramentasActionPerformed
        controller.listarFerramentas();
    }//GEN-LAST:event_AtualizarFerramentasActionPerformed

    //Botão abrir tela para apagar Ferramenta selecionada
    private void b_apagarFerramentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_apagarFerramentaActionPerformed

        b_editarOuDeletar = false;

        int linhaSelecionada = table_ferramentas.getSelectedRow();

        if (linhaSelecionada != -1) {
            camposNaoEditaveisFerramentas();

        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma ferramenta que deseja excluir!");

        }
    }//GEN-LAST:event_b_apagarFerramentaActionPerformed

    private void CadastrarAmigoHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadastrarAmigoHomeActionPerformed
        RegistrosAmigos registroAmigos = new RegistrosAmigos();

        registroAmigos.setVisible(true);
        registroAmigos.getjBotaoCadastro().setVisible(true);
    }//GEN-LAST:event_CadastrarAmigoHomeActionPerformed

    private void CadastrarFerramentaHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CadastrarFerramentaHomeActionPerformed
        RegistroFerramentas registroFerramentas = new RegistroFerramentas();

        registroFerramentas.setVisible(true);
        registroFerramentas.getjBotaoCadastroFerramenta().setVisible(true);
    }//GEN-LAST:event_CadastrarFerramentaHomeActionPerformed

    private void popupCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popupCadastrarActionPerformed
        RegistrosAmigos registroAmigos = new RegistrosAmigos();

        registroAmigos.setVisible(true);
        registroAmigos.getjBotaoCadastro().setVisible(true);
    }//GEN-LAST:event_popupCadastrarActionPerformed

    private void b_refreshEmprestimosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_refreshEmprestimosActionPerformed
        controller.listarEmprestimos();
    }//GEN-LAST:event_b_refreshEmprestimosActionPerformed

    private void editarEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarEmprestimoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editarEmprestimoActionPerformed

    private void devolverEmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_devolverEmprestimoActionPerformed
        int linhaSelecionada = tabelaEmprestimo.getSelectedRow();

        if (linhaSelecionada != -1) {
            camposNaoEditaveisEmprestimo();

        } else {
            JOptionPane.showMessageDialog(null, "Selecione um emprestimo que deseja devolver!");

        }    }//GEN-LAST:event_devolverEmprestimoActionPerformed

//Fim PopUps (JPanel Botões)
    //PopUp de sair está localizado anteriormente!
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AtualizarFerramentas;
    private javax.swing.JButton CadastrarAmigoHome;
    private javax.swing.JButton CadastrarFerramentaHome;
    private javax.swing.JLabel JL_ListaAmigos;
    private javax.swing.JPanel JP_Home;
    private javax.swing.JPanel JP_Lista;
    private javax.swing.JPanel JP_ListaAmigos;
    private javax.swing.JPanel JP_ListaFerramentas;
    private javax.swing.JPanel JP_Principal;
    private javax.swing.JPanel JP_Relatorio;
    private javax.swing.JPopupMenu JPop_Amigos;
    private javax.swing.JPopupMenu JPop_Home;
    private javax.swing.JPopupMenu JPop_botoes;
    private javax.swing.JButton atualizarTabela;
    private javax.swing.JButton b_Home;
    private javax.swing.JButton b_ListaAmigos;
    private javax.swing.JButton b_ListaFerramentas;
    private javax.swing.JButton b_apagarFerramenta;
    private javax.swing.JButton b_cadastrarAmigos;
    private javax.swing.JButton b_cadastrarFerramenta;
    private javax.swing.JButton b_editarAmigo;
    private javax.swing.JButton b_editarFerramenta;
    private javax.swing.JButton b_opcoes;
    private javax.swing.JButton b_refreshEmprestimos;
    private javax.swing.JButton b_relatorio;
    private javax.swing.JButton deleteAmigo;
    private javax.swing.JButton devolverEmprestimo;
    private javax.swing.JButton editarEmprestimo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JButton jMudarTema;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JMenuItem popup001;
    private javax.swing.JMenuItem popupAmigos;
    private javax.swing.JMenuItem popupCadastrar;
    private javax.swing.JMenuItem popupFerramentas;
    private javax.swing.JMenuItem popupHome;
    private javax.swing.JMenuItem popupOpcoes;
    private javax.swing.JMenuItem popupRelatorio;
    private javax.swing.JMenuItem popupSair;
    private javax.swing.JButton realizarEmprestimo;
    private javax.swing.JTable tabelaEmprestimo;
    private javax.swing.JTable table_amigos;
    private javax.swing.JTable table_ferramentas;
    // End of variables declaration//GEN-END:variables

    public JTable getTable_amigos() {
        return table_amigos;
    }

    public void setTable_amigos(JTable table_amigos) {
        this.table_amigos = table_amigos;
    }

    public void CarregarCampos() {
        RegistrosAmigos registroAmigos = new RegistrosAmigos();
        registroAmigos.setVisible(true);
        int setar = table_amigos.getSelectedRow();
        registroAmigos.getUpdate().setVisible(true);
        registroAmigos.getTxtnome().setText(table_amigos.getModel().getValueAt(setar, 1).toString());
        registroAmigos.getTxtemail().setText(table_amigos.getModel().getValueAt(setar, 2).toString());
        registroAmigos.getTxttelefone().setText(table_amigos.getModel().getValueAt(setar, 3).toString());
        registroAmigos.getTxtId().setText(table_amigos.getModel().getValueAt(setar, 0).toString());
    }

    public void camposNaoEditaveis() {
        RegistrosAmigos registroAmigos = new RegistrosAmigos();
        registroAmigos.setVisible(true);
        registroAmigos.getjDeleteAmigo().setVisible(true);
        registroAmigos.getjBotaoCadastro().setVisible(false);

        int setar = table_amigos.getSelectedRow();
        registroAmigos.getUpdate().setVisible(false);
        registroAmigos.getTxtnome().setText(table_amigos.getModel().getValueAt(setar, 1).toString());
        registroAmigos.getTxtemail().setText(table_amigos.getModel().getValueAt(setar, 2).toString());
        registroAmigos.getTxttelefone().setText(table_amigos.getModel().getValueAt(setar, 3).toString());
        registroAmigos.getTxtId().setText(table_amigos.getModel().getValueAt(setar, 0).toString());
        registroAmigos.getTxtnome().setEnabled(false);
        registroAmigos.getTxtemail().setEnabled(false);
        registroAmigos.getTxttelefone().setEnabled(false);
    }

    public void CarregarCamposFerramentas() {
        RegistroFerramentas RF = new RegistroFerramentas();
        RF.setVisible(true);

        if (b_editarOuDeletar == true) {
            RF.getjDeleteFerramenta().setVisible(false);
            RF.getUpdateFerramenta().setVisible(true);

        } else if (b_editarOuDeletar == false) {
            RF.getUpdateFerramenta().setVisible(false);
            RF.getjDeleteFerramenta().setVisible(true);
        }

        int setar = table_ferramentas.getSelectedRow();

        RF.getTxtnomeFerramenta().setText(table_ferramentas.getModel().getValueAt(setar, 1).toString());
        RF.getTxtMarca().setText(table_ferramentas.getModel().getValueAt(setar, 2).toString());
        RF.getTxtPreco().setText(table_ferramentas.getModel().getValueAt(setar, 3).toString());
        RF.getTxtId().setText(table_ferramentas.getModel().getValueAt(setar, 0).toString());
    }

    public void camposNaoEditaveisFerramentas() {
        RegistroFerramentas RF = new RegistroFerramentas();
        RF.setVisible(true);

        RF.getjDeleteFerramenta().setVisible(true);
        RF.getjBotaoCadastroFerramenta().setVisible(false);

        int setar = table_ferramentas.getSelectedRow();

        RF.getUpdateFerramenta().setVisible(false);
        RF.getTxtnomeFerramenta().setText(table_ferramentas.getModel().getValueAt(setar, 1).toString());
        RF.getTxtMarca().setText(table_ferramentas.getModel().getValueAt(setar, 2).toString());
        RF.getTxtPreco().setText(table_ferramentas.getModel().getValueAt(setar, 3).toString());
        RF.getTxtId().setText(table_ferramentas.getModel().getValueAt(setar, 0).toString());
        RF.getTxtnomeFerramenta().setEnabled(false);
        RF.getTxtMarca().setEnabled(false);
        RF.getTxtPreco().setEnabled(false);
    }

    public void camposNaoEditaveisEmprestimo() {
        DevolverFerramenta devolverFerramenta = new DevolverFerramenta();
        devolverFerramenta.setVisible(true);
        devolverFerramenta.getjDevolver().setVisible(true);

        int setar = tabelaEmprestimo.getSelectedRow();
        devolverFerramenta.getTxtId().setText(tabelaEmprestimo.getModel().getValueAt(setar, 0).toString());
        devolverFerramenta.getTxtNomeAmigos().setText(tabelaEmprestimo.getModel().getValueAt(setar, 1).toString());
        devolverFerramenta.getTxtNomeFerramenta().setText(tabelaEmprestimo.getModel().getValueAt(setar, 2).toString());
        devolverFerramenta.getTxtDataEmprestimo().setText(tabelaEmprestimo.getModel().getValueAt(setar, 3).toString());
        devolverFerramenta.getTxtDataDevolcao().setText(tabelaEmprestimo.getModel().getValueAt(setar, 4).toString());
        devolverFerramenta.getTxtNomeAmigos().setEnabled(false);
        devolverFerramenta.getTxtNomeFerramenta().setEnabled(false);
        devolverFerramenta.getTxtDataEmprestimo().setEnabled(false);
        devolverFerramenta.getTxtDataDevolcao().setEnabled(false);
    }

    public JTable getTable_ferramentas() {
        return table_ferramentas;
    }

    public void setTable_ferramentas(JTable table_ferramentas) {
        this.table_ferramentas = table_ferramentas;
    }

    public JTable getTabelaEmprestimo() {
        return tabelaEmprestimo;
    }

    public void setTabelaEmprestimo(JTable tabelaEmprestimo) {
        this.tabelaEmprestimo = tabelaEmprestimo;
    }

}
