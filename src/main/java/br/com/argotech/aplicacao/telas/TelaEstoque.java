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
public class TelaEstoque extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaEstoque
     */
    
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaEstoque() {
        initComponents();
        atualizarEstoque();
    }
    
    private void criarEstoque(){
        String sql = "insert into tbestoque(nomeest,catest,quantest) values(?,?,?)";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            //Pega todos os parametros inseridos pelo usuario
            pst.setString(1, nomeTextField.getText());
            pst.setString(2, categoriaComboBox.getSelectedItem().toString());
            pst.setString(3, quantidadeSpinner.getValue().toString());
            
            //Checa se campos obritarios foram preenchidos
            if(nomeTextField.getText().isEmpty() || quantidadeSpinner.getValue().toString().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            }
            
            int adicionar = pst.executeUpdate();
            //Checa se é póssivel adicionar o usuario e retorna a confirmação para o usuario
            if(adicionar>0){
                JOptionPane.showMessageDialog(null, "Estoque adicionado com sucesso");
                limparCampos();
                atualizarEstoque();
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
        
    private void atualizarEstoque(){
        String sql = "select idest as ID, nomeest as Nome, catest as Categoria, quantest as Quantidade from tbestoque where nomeest like ?";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, buscaTextField.getText() + "%");
            rs = pst.executeQuery();
            
            estoqueTable.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void setarCampos(){
        int setar = estoqueTable.getSelectedRow();
        
        idNumberLabel.setText(estoqueTable.getModel().getValueAt(setar, 0).toString());
        nomeTextField.setText(estoqueTable.getModel().getValueAt(setar, 1).toString());
        categoriaComboBox.setSelectedItem(estoqueTable.getModel().getValueAt(setar, 2).toString());
        quantidadeSpinner.setValue(estoqueTable.getModel().getValueAt(setar, 3));
        
    }

    private void alterarEstoque(){
        String sql = "update tbestoque set nomeest=?,catest=?,quantest=? where idest=?";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, nomeTextField.getText());
            pst.setString(2, categoriaComboBox.getSelectedItem().toString());
            pst.setString(3, quantidadeSpinner.getValue().toString());
            
            pst.setString(4, idNumberLabel.getText());
            
            if(nomeTextField.getText().isEmpty() || quantidadeSpinner.getValue().toString().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            }
            
            int alterar = pst.executeUpdate();

            if(alterar>0){
                JOptionPane.showMessageDialog(null, "Estoque alterado com sucesso");
                limparCampos();
                atualizarEstoque();
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void deletarEstoque(){
        
        int confirma = JOptionPane.showConfirmDialog(null, "Certeza que deseja deletar este estoque?", "Aviso", JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){           
            String sql = "delete from tbestoque where idest=?";
            try {
                con = ModuloConector.createConnection();
                pst = con.prepareStatement(sql);
                
                pst.setString(1, idNumberLabel.getText());
                
                int deletar = pst.executeUpdate();

                if(deletar>0){
                    JOptionPane.showMessageDialog(null, "Estoque deletado com sucesso");
                    atualizarEstoque();
                }
                
            } catch (Exception e) {

            }
        }
    }
    
    private void limparCampos(){
        estoqueTable.clearSelection();
        
        idNumberLabel.setText("ID");
        nomeTextField.setText(null);
        quantidadeSpinner.setValue(0);
        categoriaComboBox.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        idLabel = new javax.swing.JLabel();
        buscarButton = new javax.swing.JButton();
        idNumberLabel = new javax.swing.JLabel();
        obrigatorioLabel = new javax.swing.JLabel();
        criarEstoqueButton = new javax.swing.JButton();
        nomeTextField = new javax.swing.JTextField();
        alterarEstoqueButton = new javax.swing.JButton();
        deletarEstoqueButton = new javax.swing.JButton();
        limparButton = new javax.swing.JButton();
        nomeLabel = new javax.swing.JLabel();
        quantidadeLabel = new javax.swing.JLabel();
        buscaTextField = new javax.swing.JTextField();
        JScrollPane = new javax.swing.JScrollPane();
        estoqueTable = new javax.swing.JTable();
        categoriaComboBox = new javax.swing.JComboBox<>();
        quantidadeSpinner = new javax.swing.JSpinner();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Estoque");
        setPreferredSize(new java.awt.Dimension(620, 470));

        idLabel.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        idLabel.setText("ID:");

        buscarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        buscarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarButtonActionPerformed(evt);
            }
        });

        idNumberLabel.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        idNumberLabel.setText("ID");

        obrigatorioLabel.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        obrigatorioLabel.setText("Campos obrigatórios: *");

        criarEstoqueButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        criarEstoqueButton.setText("Adicionar Estoque");
        criarEstoqueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criarEstoqueButtonActionPerformed(evt);
            }
        });

        nomeTextField.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N

        alterarEstoqueButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        alterarEstoqueButton.setText("Alterar Estoque");
        alterarEstoqueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarEstoqueButtonActionPerformed(evt);
            }
        });

        deletarEstoqueButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        deletarEstoqueButton.setText("Deletar Estoque");
        deletarEstoqueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletarEstoqueButtonActionPerformed(evt);
            }
        });

        limparButton.setText("Limpar");
        limparButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparButtonActionPerformed(evt);
            }
        });

        nomeLabel.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        nomeLabel.setText("Nome estoque*");

        quantidadeLabel.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        quantidadeLabel.setText("Quantidade*");

        buscaTextField.setToolTipText("");
        buscaTextField.setName(""); // NOI18N
        buscaTextField.setVerifyInputWhenFocusTarget(false);

        estoqueTable = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int colIndex){
                return false;
            }
        };
        estoqueTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Categoria", "Quantidade"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
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
        estoqueTable.setFocusable(false);
        estoqueTable.getTableHeader().setReorderingAllowed(false);
        estoqueTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                estoqueTableMouseClicked(evt);
            }
        });
        JScrollPane.setViewportView(estoqueTable);

        categoriaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CPU/Processador", "Cooler CPU", "Placa mãe", "Memoria RAM", "Placa de vídeo", "Armazenamento", "Fonte", "Cabo", "Cooler Gabinete", "Gabinete", "Outro" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JScrollPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(limparButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addComponent(criarEstoqueButton)
                        .addGap(14, 14, 14)
                        .addComponent(alterarEstoqueButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deletarEstoqueButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buscaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buscarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(obrigatorioLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nomeLabel)
                                    .addComponent(quantidadeLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(quantidadeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(idLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(idNumberLabel))
                                    .addComponent(categoriaComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                    .addComponent(idLabel)
                    .addComponent(idNumberLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantidadeLabel)
                    .addComponent(categoriaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantidadeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alterarEstoqueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(criarEstoqueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deletarEstoqueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(limparButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarButtonActionPerformed
        atualizarEstoque();
    }//GEN-LAST:event_buscarButtonActionPerformed

    private void criarEstoqueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criarEstoqueButtonActionPerformed
        criarEstoque();
    }//GEN-LAST:event_criarEstoqueButtonActionPerformed

    private void alterarEstoqueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarEstoqueButtonActionPerformed
        alterarEstoque();
    }//GEN-LAST:event_alterarEstoqueButtonActionPerformed

    private void deletarEstoqueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletarEstoqueButtonActionPerformed
        deletarEstoque();
    }//GEN-LAST:event_deletarEstoqueButtonActionPerformed

    private void limparButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparButtonActionPerformed
        limparCampos();
    }//GEN-LAST:event_limparButtonActionPerformed

    private void estoqueTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_estoqueTableMouseClicked
        setarCampos();
    }//GEN-LAST:event_estoqueTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScrollPane;
    private javax.swing.JButton alterarEstoqueButton;
    private javax.swing.JTextField buscaTextField;
    private javax.swing.JButton buscarButton;
    private javax.swing.JComboBox<String> categoriaComboBox;
    private javax.swing.JButton criarEstoqueButton;
    private javax.swing.JButton deletarEstoqueButton;
    private javax.swing.JTable estoqueTable;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel idNumberLabel;
    private javax.swing.JButton limparButton;
    private javax.swing.JLabel nomeLabel;
    private javax.swing.JTextField nomeTextField;
    private javax.swing.JLabel obrigatorioLabel;
    private javax.swing.JLabel quantidadeLabel;
    private javax.swing.JSpinner quantidadeSpinner;
    // End of variables declaration//GEN-END:variables
}
