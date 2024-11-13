/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.argotech.aplicacao.telas;

import br.com.argotech.aplicacao.dal.ModuloConector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author pnot0
 */
public class TelaUsuario extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaUsuario
     */
    
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaUsuario() {
        initComponents();
        atualizarUsuario();
    }
    
    private void criarUsuario(){
        String sql = "insert into tbusuarios(usuario,fone,login,senha,acesso) values(?,?,?,?,?)";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            //Pega todos os parametros inseridos pelo usuario
            pst.setString(1, usuarioTextField.getText());
            pst.setString(2, foneTextField.getText());
            pst.setString(3, loginTextField.getText());
            pst.setString(4, senhaTextField.getText());
            pst.setString(5, perfilComboBox.getSelectedItem().toString());
            
            //Checa se campos obritarios foram preenchidos
            if(usuarioTextField.getText().isEmpty() || loginTextField.getText().isEmpty() || senhaTextField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            }
            
            int adicionar = pst.executeUpdate();
            //Checa se é póssivel adicionar o usuario e retorna a confirmação para o usuario
            if(adicionar>0){
                JOptionPane.showMessageDialog(null, "Usuario adicionado com sucesso");
                limparCampos();
                atualizarUsuario();
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
        
    private void atualizarUsuario(){
        String sql = "select iduser as ID, usuario as Usuario, fone as Fone, login as Login, senha as Senha, acesso as Perfil from tbusuarios where usuario like ?";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, buscaTextField.getText() + "%");
            rs = pst.executeQuery();
            
            usuarioTable.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void setarCampos(){
        int setar = usuarioTable.getSelectedRow();
        
        idNumberLabel.setText(usuarioTable.getModel().getValueAt(setar, 0).toString());
        usuarioTextField.setText(usuarioTable.getModel().getValueAt(setar, 1).toString());
        
        if(usuarioTable.getModel().getValueAt(setar, 2).toString() == null){
            foneTextField.setText(usuarioTable.getModel().getValueAt(setar, 2).toString());
        }else{
            foneTextField.setText("");
        }
        
        loginTextField.setText(usuarioTable.getModel().getValueAt(setar, 3).toString());
        senhaTextField.setText(usuarioTable.getModel().getValueAt(setar, 4).toString());
        perfilComboBox.setSelectedItem(usuarioTable.getModel().getValueAt(setar, 5).toString());
    }

    private void alterarUsuario(){
        String sql = "update tbusuarios set usuario=?,fone=?,login=?,senha=?,acesso=? where iduser=?";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, usuarioTextField.getText());
            pst.setString(2, foneTextField.getText());
            pst.setString(3, loginTextField.getText());
            pst.setString(4, senhaTextField.getText());
            pst.setString(5, perfilComboBox.getSelectedItem().toString());
            
            pst.setString(6, idNumberLabel.getText());
            
            if(usuarioTextField.getText().isEmpty() || loginTextField.getText().isEmpty() || senhaTextField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            }
            
            int alterar = pst.executeUpdate();

            if(alterar>0){
                JOptionPane.showMessageDialog(null, "Usuario alterado com sucesso");
                limparCampos();
                atualizarUsuario();
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void deletarUsuario(){
        
        int confirma = JOptionPane.showConfirmDialog(null, "Certeza que deseja deletar este usuario?", "Aviso", JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){           
            String sql = "delete from tbusuarios where iduser=?";
            try {
                con = ModuloConector.createConnection();
                pst = con.prepareStatement(sql);
                
                pst.setString(1, idNumberLabel.getText());
                
                int deletar = pst.executeUpdate();

                if(deletar>0){
                    JOptionPane.showMessageDialog(null, "Usuario deletado com sucesso");
                    atualizarUsuario();
                }
                
            } catch (Exception e) {

            }
        }
    }
    
    private void limparCampos(){
        usuarioTable.clearSelection();
        
        idNumberLabel.setText("ID");
        usuarioTextField.setText(null);
        foneTextField.setText(null);
        loginTextField.setText(null);
        senhaTextField.setText(null);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usuarioTextField = new javax.swing.JTextField();
        foneTextField = new javax.swing.JTextField();
        loginTextField = new javax.swing.JTextField();
        senhaTextField = new javax.swing.JTextField();
        usuarioLabel = new javax.swing.JLabel();
        foneLabel = new javax.swing.JLabel();
        loginLabel = new javax.swing.JLabel();
        senhaLabel = new javax.swing.JLabel();
        perfilLabel = new javax.swing.JLabel();
        perfilComboBox = new javax.swing.JComboBox<>();
        criarUsuarioButton = new javax.swing.JButton();
        obrigatorioLabel = new javax.swing.JLabel();
        JScrollPane = new javax.swing.JScrollPane();
        usuarioTable = new javax.swing.JTable();
        buscaTextField = new javax.swing.JTextField();
        buscarButton = new javax.swing.JButton();
        alterarUsuarioButton = new javax.swing.JButton();
        deletarUsuarioButton = new javax.swing.JButton();
        idLabel = new javax.swing.JLabel();
        idNumberLabel = new javax.swing.JLabel();
        limparButton = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuario");
        setPreferredSize(new java.awt.Dimension(620, 470));

        usuarioTextField.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N

        foneTextField.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N

        loginTextField.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N

        senhaTextField.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N

        usuarioLabel.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        usuarioLabel.setText("Usuario*");

        foneLabel.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        foneLabel.setText("Fone");

        loginLabel.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        loginLabel.setText("Login*");

        senhaLabel.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        senhaLabel.setText("Senha*");

        perfilLabel.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        perfilLabel.setText("Perfil");

        perfilComboBox.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        perfilComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "usuario" }));

        criarUsuarioButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        criarUsuarioButton.setText("Criar Usuario");
        criarUsuarioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criarUsuarioButtonActionPerformed(evt);
            }
        });

        obrigatorioLabel.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        obrigatorioLabel.setText("Campos obrigatórios: *");

        usuarioTable = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int colIndex){
                return false;
            }
        };
        usuarioTable.setModel(new javax.swing.table.DefaultTableModel(
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
        usuarioTable.getTableHeader().setReorderingAllowed(false);
        usuarioTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usuarioTableMouseClicked(evt);
            }
        });
        JScrollPane.setViewportView(usuarioTable);

        buscaTextField.setToolTipText("");
        buscaTextField.setName(""); // NOI18N
        buscaTextField.setVerifyInputWhenFocusTarget(false);

        buscarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        buscarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarButtonActionPerformed(evt);
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

        idLabel.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        idLabel.setText("ID:");

        idNumberLabel.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        idNumberLabel.setText("ID");

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
                        .addComponent(buscaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buscarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(obrigatorioLabel)
                        .addGap(0, 76, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(perfilLabel)
                                .addGap(58, 58, 58)
                                .addComponent(perfilComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(foneLabel)
                                        .addGap(58, 58, 58)
                                        .addComponent(foneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(usuarioLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(usuarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(loginLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(senhaLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(idLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(idNumberLabel)))
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(loginTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                    .addComponent(senhaTextField)))
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
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(buscaTextField)
                    .addComponent(obrigatorioLabel)
                    .addComponent(buscarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(JScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginLabel)
                    .addComponent(loginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usuarioLabel)
                    .addComponent(usuarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(senhaLabel)
                    .addComponent(senhaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(foneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(foneLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(idLabel)
                        .addComponent(idNumberLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(perfilComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(perfilLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alterarUsuarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(criarUsuarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deletarUsuarioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(limparButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void criarUsuarioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criarUsuarioButtonActionPerformed
        criarUsuario();
    }//GEN-LAST:event_criarUsuarioButtonActionPerformed

    private void buscarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarButtonActionPerformed
        atualizarUsuario();
    }//GEN-LAST:event_buscarButtonActionPerformed

    private void usuarioTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuarioTableMouseClicked
        setarCampos();
    }//GEN-LAST:event_usuarioTableMouseClicked

    private void alterarUsuarioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarUsuarioButtonActionPerformed
        alterarUsuario();
    }//GEN-LAST:event_alterarUsuarioButtonActionPerformed

    private void deletarUsuarioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletarUsuarioButtonActionPerformed
        deletarUsuario();
    }//GEN-LAST:event_deletarUsuarioButtonActionPerformed

    private void limparButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparButtonActionPerformed
        limparCampos();
    }//GEN-LAST:event_limparButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScrollPane;
    private javax.swing.JButton alterarUsuarioButton;
    private javax.swing.JTextField buscaTextField;
    private javax.swing.JButton buscarButton;
    private javax.swing.JButton criarUsuarioButton;
    private javax.swing.JButton deletarUsuarioButton;
    private javax.swing.JLabel foneLabel;
    private javax.swing.JTextField foneTextField;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel idNumberLabel;
    private javax.swing.JButton limparButton;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JTextField loginTextField;
    private javax.swing.JLabel obrigatorioLabel;
    private javax.swing.JComboBox<String> perfilComboBox;
    private javax.swing.JLabel perfilLabel;
    private javax.swing.JLabel senhaLabel;
    private javax.swing.JTextField senhaTextField;
    private javax.swing.JLabel usuarioLabel;
    private javax.swing.JTable usuarioTable;
    private javax.swing.JTextField usuarioTextField;
    // End of variables declaration//GEN-END:variables
}
