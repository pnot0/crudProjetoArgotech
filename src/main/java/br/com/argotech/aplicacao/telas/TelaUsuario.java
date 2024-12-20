/*
 * The MIT License
 *
 * Copyright 2024 pnot0.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.com.argotech.aplicacao.telas;

import br.com.argotech.aplicacao.model.Usuario;

/**
 * @author DiogoNucci
 * @version 1.0
 */
public class TelaUsuario extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaUsuario
     */
    
    Usuario usuario = new Usuario();
    
    public TelaUsuario() {
        initComponents();
        usuario.atualizar();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usuarioUsuarioTextField = new javax.swing.JTextField();
        foneUsuarioTextField = new javax.swing.JTextField();
        loginUsuarioTextField = new javax.swing.JTextField();
        senhaUsuarioTextField = new javax.swing.JTextField();
        usuarioUsuarioLabel = new javax.swing.JLabel();
        foneUsuarioLabel = new javax.swing.JLabel();
        loginUsuarioLabel = new javax.swing.JLabel();
        senhaUsuarioLabel = new javax.swing.JLabel();
        perfilUsuarioLabel = new javax.swing.JLabel();
        perfilUsuarioComboBox = new javax.swing.JComboBox<>();
        criarUsuarioButton = new javax.swing.JButton();
        obrigatorioUsuarioLabel = new javax.swing.JLabel();
        JScrollPane = new javax.swing.JScrollPane();
        usuarioUsuarioTable = new javax.swing.JTable();
        buscaUsuarioTextField = new javax.swing.JTextField();
        buscarUsuarioButton = new javax.swing.JButton();
        alterarUsuarioButton = new javax.swing.JButton();
        deletarUsuarioButton = new javax.swing.JButton();
        idUsuarioLabel = new javax.swing.JLabel();
        idUsuarioNumberLabel = new javax.swing.JLabel();
        limparButton = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuario");
        setPreferredSize(new java.awt.Dimension(620, 470));

        usuarioUsuarioTextField.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N

        foneUsuarioTextField.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N

        loginUsuarioTextField.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N

        senhaUsuarioTextField.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N

        usuarioUsuarioLabel.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        usuarioUsuarioLabel.setText("Usuario*");

        foneUsuarioLabel.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        foneUsuarioLabel.setText("Fone");

        loginUsuarioLabel.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        loginUsuarioLabel.setText("Login*");

        senhaUsuarioLabel.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        senhaUsuarioLabel.setText("Senha*");

        perfilUsuarioLabel.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        perfilUsuarioLabel.setText("Perfil");

        perfilUsuarioComboBox.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        perfilUsuarioComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "usuario" }));

        criarUsuarioButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        criarUsuarioButton.setText("Criar Usuario");
        criarUsuarioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criarUsuarioButtonActionPerformed(evt);
            }
        });

        obrigatorioUsuarioLabel.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        obrigatorioUsuarioLabel.setText("Campos obrigatórios: *");

        usuarioUsuarioTable = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int colIndex){
                return false;
            }
        };
        usuarioUsuarioTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Usuario", "Fone", "Login", "Senha", "Perfil"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        usuarioUsuarioTable.getTableHeader().setReorderingAllowed(false);
        usuarioUsuarioTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usuarioUsuarioTableMouseClicked(evt);
            }
        });
        JScrollPane.setViewportView(usuarioUsuarioTable);

        buscaUsuarioTextField.setToolTipText("");
        buscaUsuarioTextField.setName(""); // NOI18N
        buscaUsuarioTextField.setVerifyInputWhenFocusTarget(false);

        buscarUsuarioButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        buscarUsuarioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarUsuarioButtonActionPerformed(evt);
            }
        });

        alterarUsuarioButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        alterarUsuarioButton.setText("Alterar Usuario");
        alterarUsuarioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarUsuarioButtonActionPerformed(evt);
            }
        });

        deletarUsuarioButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        deletarUsuarioButton.setText("Deletar Usuario");
        deletarUsuarioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletarUsuarioButtonActionPerformed(evt);
            }
        });

        idUsuarioLabel.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        idUsuarioLabel.setText("ID:");

        idUsuarioNumberLabel.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        idUsuarioNumberLabel.setText("ID");

        limparButton.setText("Limpar");
        limparButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buscaUsuarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buscarUsuarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(obrigatorioUsuarioLabel)
                        .addGap(0, 76, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(perfilUsuarioLabel)
                                .addGap(58, 58, 58)
                                .addComponent(perfilUsuarioComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(foneUsuarioLabel)
                                        .addGap(58, 58, 58)
                                        .addComponent(foneUsuarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(usuarioUsuarioLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(usuarioUsuarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(loginUsuarioLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(senhaUsuarioLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(idUsuarioLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(idUsuarioNumberLabel)))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(loginUsuarioTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                    .addComponent(senhaUsuarioTextField)))
                            .addComponent(JScrollPane)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(limparButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(criarUsuarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(alterarUsuarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(deletarUsuarioButton)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(buscaUsuarioTextField)
                    .addComponent(obrigatorioUsuarioLabel)
                    .addComponent(buscarUsuarioButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(JScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginUsuarioLabel)
                    .addComponent(loginUsuarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usuarioUsuarioLabel)
                    .addComponent(usuarioUsuarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(senhaUsuarioLabel)
                    .addComponent(senhaUsuarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(foneUsuarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(foneUsuarioLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(idUsuarioLabel)
                        .addComponent(idUsuarioNumberLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(perfilUsuarioComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(perfilUsuarioLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alterarUsuarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(criarUsuarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deletarUsuarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(limparButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void criarUsuarioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criarUsuarioButtonActionPerformed
        usuario.criar();
    }//GEN-LAST:event_criarUsuarioButtonActionPerformed

    private void buscarUsuarioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarUsuarioButtonActionPerformed
        usuario.atualizar();
    }//GEN-LAST:event_buscarUsuarioButtonActionPerformed

    private void usuarioUsuarioTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuarioUsuarioTableMouseClicked
        usuario.setarCampos();
    }//GEN-LAST:event_usuarioUsuarioTableMouseClicked

    private void alterarUsuarioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarUsuarioButtonActionPerformed
        usuario.alterar();
    }//GEN-LAST:event_alterarUsuarioButtonActionPerformed

    private void deletarUsuarioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletarUsuarioButtonActionPerformed
        usuario.deletar();
    }//GEN-LAST:event_deletarUsuarioButtonActionPerformed

    private void limparButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparButtonActionPerformed
        usuario.limparCampos();
    }//GEN-LAST:event_limparButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScrollPane;
    private javax.swing.JButton alterarUsuarioButton;
    public static javax.swing.JTextField buscaUsuarioTextField;
    private javax.swing.JButton buscarUsuarioButton;
    private javax.swing.JButton criarUsuarioButton;
    private javax.swing.JButton deletarUsuarioButton;
    private javax.swing.JLabel foneUsuarioLabel;
    public static javax.swing.JTextField foneUsuarioTextField;
    private javax.swing.JLabel idUsuarioLabel;
    public static javax.swing.JLabel idUsuarioNumberLabel;
    private javax.swing.JButton limparButton;
    private javax.swing.JLabel loginUsuarioLabel;
    public static javax.swing.JTextField loginUsuarioTextField;
    private javax.swing.JLabel obrigatorioUsuarioLabel;
    public static javax.swing.JComboBox<String> perfilUsuarioComboBox;
    private javax.swing.JLabel perfilUsuarioLabel;
    private javax.swing.JLabel senhaUsuarioLabel;
    public static javax.swing.JTextField senhaUsuarioTextField;
    private javax.swing.JLabel usuarioUsuarioLabel;
    public static javax.swing.JTable usuarioUsuarioTable;
    public static javax.swing.JTextField usuarioUsuarioTextField;
    // End of variables declaration//GEN-END:variables
}
