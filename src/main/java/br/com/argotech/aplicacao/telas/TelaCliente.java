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
public class TelaCliente extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaCliente
     */
    
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaCliente() {
        initComponents();
        atualizarCliente();
    }
    
    private void criarCliente(){
        String sql = "insert into tbclientes(nomecli,endcli,telcli,emailcli) values(?,?,?,?)";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            //Pega todos os parametros inseridos pelo usuario
            pst.setString(1, nomeTextField.getText());
            pst.setString(2, enderecoTextField.getText());
            pst.setString(3, foneTextField.getText());
            pst.setString(4, emailTextField.getText());
            
            //Checa se campos obritarios foram preenchidos
            if(nomeTextField.getText().isEmpty() || foneTextField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            }
            
            int adicionar = pst.executeUpdate();
            //Checa se é póssivel adicionar o usuario e retorna a confirmação para o usuario
            if(adicionar>0){
                JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso");
                limparCampos();
                atualizarCliente();
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
        
    private void atualizarCliente(){
        String sql = "select idcli as ID, nomecli as Nome, endcli as Endereço, telcli as Fone, emailcli as Email from tbclientes where nomecli like ?";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, buscaTextField.getText() + "%");
            rs = pst.executeQuery();
            
            clienteTable.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void setarCampos(){
        int setar = clienteTable.getSelectedRow();
        
        idNumberLabel.setText(clienteTable.getModel().getValueAt(setar, 0).toString());
        nomeTextField.setText(clienteTable.getModel().getValueAt(setar, 1).toString());
        
        if(clienteTable.getModel().getValueAt(setar, 2).toString() != null){
            enderecoTextField.setText(clienteTable.getModel().getValueAt(setar, 2).toString());
        }else{
            enderecoTextField.setText("");
        }
        
        foneTextField.setText(clienteTable.getModel().getValueAt(setar, 3).toString());
        
        if(clienteTable.getModel().getValueAt(setar, 4).toString() != null){
            emailTextField.setText(clienteTable.getModel().getValueAt(setar, 4).toString());
        }else{
            emailTextField.setText("");
        } 
    }

    private void alterarCliente(){
        String sql = "update tbclientes set nomecli=?,endcli=?,telcli=?,emailcli=? where idcli=?";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, nomeTextField.getText());
            pst.setString(2, enderecoTextField.getText());
            pst.setString(3, foneTextField.getText());
            pst.setString(4, emailTextField.getText());
            
            pst.setString(5, idNumberLabel.getText());
            
            if(nomeTextField.getText().isEmpty() || foneTextField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            }
            
            int alterar = pst.executeUpdate();

            if(alterar>0){
                JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso");
                limparCampos();
                atualizarCliente();
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void deletarCliente(){
        
        int confirma = JOptionPane.showConfirmDialog(null, "Certeza que deseja deletar este cliente?", "Aviso", JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){           
            String sql = "delete from tbclientes where idcli=?";
            try {
                con = ModuloConector.createConnection();
                pst = con.prepareStatement(sql);
                
                pst.setString(1, idNumberLabel.getText());
                
                int deletar = pst.executeUpdate();

                if(deletar>0){
                    JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso");
                    atualizarCliente();
                }
                
            } catch (Exception e) {

            }
        }
    }
    
    private void limparCampos(){
        clienteTable.clearSelection();
        
        idNumberLabel.setText("ID");
        nomeTextField.setText(null);
        enderecoTextField.setText(null);
        foneTextField.setText(null);
        emailTextField.setText(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buscaTextField = new javax.swing.JTextField();
        JScrollPane = new javax.swing.JScrollPane();
        clienteTable = new javax.swing.JTable();
        buscarButton = new javax.swing.JButton();
        obrigatorioLabel = new javax.swing.JLabel();
        nomeTextField = new javax.swing.JTextField();
        enderecoTextField = new javax.swing.JTextField();
        foneTextField = new javax.swing.JTextField();
        emailTextField = new javax.swing.JTextField();
        nomeLabel = new javax.swing.JLabel();
        enderecoLabel = new javax.swing.JLabel();
        foneLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        idLabel = new javax.swing.JLabel();
        idNumberLabel = new javax.swing.JLabel();
        criarClienteButton = new javax.swing.JButton();
        alterarClienteButton = new javax.swing.JButton();
        deletarClienteButton = new javax.swing.JButton();
        limparButton = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Clientes");
        setPreferredSize(new java.awt.Dimension(620, 470));

        buscaTextField.setToolTipText("");
        buscaTextField.setName(""); // NOI18N
        buscaTextField.setVerifyInputWhenFocusTarget(false);

        clienteTable = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int colIndex){
                return false;
            }
        };
        clienteTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Endereço", "Fone", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        clienteTable.setFocusable(false);
        clienteTable.getTableHeader().setReorderingAllowed(false);
        clienteTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clienteTableMouseClicked(evt);
            }
        });
        JScrollPane.setViewportView(clienteTable);

        buscarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        buscarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarButtonActionPerformed(evt);
            }
        });

        obrigatorioLabel.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        obrigatorioLabel.setText("Campos obrigatórios: *");

        nomeTextField.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N

        enderecoTextField.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N

        foneTextField.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N

        emailTextField.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N

        nomeLabel.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        nomeLabel.setText("Nome*");

        enderecoLabel.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        enderecoLabel.setText("Endereço");

        foneLabel.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        foneLabel.setText("Fone*");

        emailLabel.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        emailLabel.setText("Email");

        idLabel.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        idLabel.setText("ID:");

        idNumberLabel.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        idNumberLabel.setText("ID");

        criarClienteButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        criarClienteButton.setText("Adicionar Cliente");
        criarClienteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criarClienteButtonActionPerformed(evt);
            }
        });

        alterarClienteButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        alterarClienteButton.setText("Alterar Cliente");
        alterarClienteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarClienteButtonActionPerformed(evt);
            }
        });

        deletarClienteButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        deletarClienteButton.setText("Deletar Cliente");
        deletarClienteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletarClienteButtonActionPerformed(evt);
            }
        });

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
                    .addComponent(JScrollPane)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(foneLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(foneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nomeLabel)
                                .addGap(18, 18, 18)
                                .addComponent(nomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(enderecoLabel)
                            .addComponent(emailLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(enderecoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(emailTextField)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buscaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buscarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(obrigatorioLabel)
                        .addGap(0, 76, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(idLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(idNumberLabel))
                            .addComponent(limparButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(criarClienteButton)
                        .addGap(12, 12, 12)
                        .addComponent(alterarClienteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deletarClienteButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(obrigatorioLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buscarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buscaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeLabel)
                    .addComponent(nomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enderecoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enderecoLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(foneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(foneLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idLabel)
                    .addComponent(idNumberLabel))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alterarClienteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(criarClienteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deletarClienteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(limparButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clienteTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clienteTableMouseClicked
        setarCampos();
    }//GEN-LAST:event_clienteTableMouseClicked

    private void buscarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarButtonActionPerformed
        atualizarCliente();
    }//GEN-LAST:event_buscarButtonActionPerformed

    private void criarClienteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criarClienteButtonActionPerformed
        criarCliente();
    }//GEN-LAST:event_criarClienteButtonActionPerformed

    private void alterarClienteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarClienteButtonActionPerformed
        alterarCliente();
    }//GEN-LAST:event_alterarClienteButtonActionPerformed

    private void deletarClienteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletarClienteButtonActionPerformed
        deletarCliente();
    }//GEN-LAST:event_deletarClienteButtonActionPerformed

    private void limparButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparButtonActionPerformed
        limparCampos();
    }//GEN-LAST:event_limparButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScrollPane;
    private javax.swing.JButton alterarClienteButton;
    private javax.swing.JTextField buscaTextField;
    private javax.swing.JButton buscarButton;
    private javax.swing.JTable clienteTable;
    private javax.swing.JButton criarClienteButton;
    private javax.swing.JButton deletarClienteButton;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel enderecoLabel;
    private javax.swing.JTextField enderecoTextField;
    private javax.swing.JLabel foneLabel;
    private javax.swing.JTextField foneTextField;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel idNumberLabel;
    private javax.swing.JButton limparButton;
    private javax.swing.JLabel nomeLabel;
    private javax.swing.JTextField nomeTextField;
    private javax.swing.JLabel obrigatorioLabel;
    // End of variables declaration//GEN-END:variables
}
