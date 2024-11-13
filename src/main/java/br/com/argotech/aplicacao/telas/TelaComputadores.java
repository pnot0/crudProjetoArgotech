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
public class TelaComputadores extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaComputadores
     */
    
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaComputadores() {
        initComponents();
        atualizarComputador();
    }
    
    private void criarComputador(){
        String sql = "insert into tbpcs(nomepc,descpc,quantpcs) values(?,?,?)";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, nomeComputadorTextField.getText());
            pst.setString(2, descComputadorTextField.getText());
            pst.setString(3, quantidadeComputadorSpinner.getValue().toString());
            
            if(nomeComputadorTextField.getText().isEmpty() || quantidadeComputadorSpinner.getValue().toString().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            }
            
            int adicionar = pst.executeUpdate();
            if(adicionar>0){
                JOptionPane.showMessageDialog(null, "Computador adicionado com sucesso");
                limparCamposComputador();
                atualizarComputador();
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void atualizarComputador(){
        String sql = "select idpc as IDPC, nomepc as Nome, descpc as Descrição, quantpcs as Quantidade from tbpcs where nomepc like ?";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, buscaComputadorTextField.getText() + "%");
            rs = pst.executeQuery();
            
            computadorTable.setModel(DbUtils.resultSetToTableModel(rs));
            atualizarComponente();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void alterarComputador(){
        String sql = "update tbpcs set nomepc=?,descpc=?,quantpcs=? where idpc=?";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, nomeComputadorTextField.getText());
            pst.setString(2, descComputadorTextField.getText());
            pst.setString(3, quantidadeComputadorSpinner.getValue().toString());
            
            pst.setString(4, idComputadorNumberLabel.getText());
            
            if(nomeComputadorTextField.getText().isEmpty() || quantidadeComputadorSpinner.getValue().toString().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            }
            
            int alterar = pst.executeUpdate();

            if(alterar>0){
                JOptionPane.showMessageDialog(null, "Computador alterado com sucesso");
                limparCamposComputador();
                atualizarComputador();
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void deletarComputador(){
        int confirma = JOptionPane.showConfirmDialog(null, "Certeza que deseja deletar este computador? \nVocê tambem deletará seus componentes", "Aviso", JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){           
            String sql = "delete from tbpcs where idpc=?";
            try {
                con = ModuloConector.createConnection();
                pst = con.prepareStatement(sql);
                
                pst.setString(1, idComputadorNumberLabel.getText());
                
                int deletar = pst.executeUpdate();

                if(deletar>0){
                    JOptionPane.showMessageDialog(null, "Computador deletado com sucesso");
                    atualizarComputador();
                }
                
            } catch (Exception e) {

            }
        }
    }
    
    private void criarComponente(){
        String sql = "insert into tbcomps(idpc,nomecomp,catcomp,quantcomp) values(?,?,?,?)";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, idComputadorNumberLabel.getText());
            pst.setString(2, nomeComponenteTextField.getText());
            pst.setString(3, categoriaComponenteComboBox.getSelectedItem().toString());
            pst.setString(4, quantidadeComponenteSpinner.getValue().toString());
            
            if(nomeComponenteTextField.getText().isEmpty() || quantidadeComponenteSpinner.getValue().toString().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            }
            
            int adicionar = pst.executeUpdate();
            if(adicionar>0){
                JOptionPane.showMessageDialog(null, "Componente adicionado com sucesso");
                limparCamposComponente();
                atualizarComponente();
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void atualizarComponente(){
        String sql = "select idcomp as ID, nomecomp as Nome, catcomp as Categoria, quantcomp as Quantidade from tbcomps where nomecomp like ? and idpc like ?";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, buscaComputadorTextField.getText() + "%");
            pst.setString(2, idComputadorNumberLabel.getText());
            rs = pst.executeQuery();
            
            componenteTable.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void alterarComponente(){
        String sql = "update tbcomps set nomecomp=?,catcomp=?,quantcomp=? where idcomp=? and idpc=?";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, nomeComponenteTextField.getText());
            pst.setString(2, categoriaComponenteComboBox.getSelectedItem().toString());
            pst.setString(3, quantidadeComponenteSpinner.getValue().toString());
            
            pst.setString(4, idComponenteNumberLabel.getText());
            pst.setString(5, idComputadorNumberLabel.getText());
            
            if(nomeComponenteTextField.getText().isEmpty() || quantidadeComponenteSpinner.getValue().toString().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            }
            
            int alterar = pst.executeUpdate();
            if(alterar>0){
                JOptionPane.showMessageDialog(null, "Componente alterado com sucesso");
                limparCamposComponente();
                atualizarComponente();
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void deletarComponente(){
        int confirma = JOptionPane.showConfirmDialog(null, "Certeza que deseja deletar este componente?", "Aviso", JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){           
            String sql = "delete from tbcomps where idcomp=? and idpc=?";
            try {
                con = ModuloConector.createConnection();
                pst = con.prepareStatement(sql);
                
                pst.setString(1, idComponenteNumberLabel.getText());
                pst.setString(2, idComputadorNumberLabel.getText());
                
                int deletar = pst.executeUpdate();

                if(deletar>0){
                    JOptionPane.showMessageDialog(null, "Componente deletado com sucesso");
                    atualizarComponente();
                }
                
            } catch (Exception e) {

            }
        }
    }
    
    private void setarCamposComputador(){
        int setar = computadorTable.getSelectedRow();
        
        idComputadorNumberLabel.setText(computadorTable.getModel().getValueAt(setar, 0).toString());
        nomeComputadorTextField.setText(computadorTable.getModel().getValueAt(setar, 1).toString());
        descComputadorTextField.setText(computadorTable.getModel().getValueAt(setar, 2).toString());
        quantidadeComputadorSpinner.setValue(computadorTable.getModel().getValueAt(setar, 3));
        
        atualizarComponente();
        
        adicionarComponenteButton.setEnabled(true);
        alterarComponenteButton.setEnabled(true);
        deletarComponenteButton.setEnabled(true);
    }
    
    private void setarCamposComponente(){
        int setar = componenteTable.getSelectedRow();
        
        idComponenteNumberLabel.setText(componenteTable.getModel().getValueAt(setar, 0).toString());
        nomeComponenteTextField.setText(componenteTable.getModel().getValueAt(setar, 1).toString());
        categoriaComponenteComboBox.setSelectedItem(componenteTable.getModel().getValueAt(setar, 2).toString());
        quantidadeComponenteSpinner.setValue(componenteTable.getModel().getValueAt(setar, 3));
    }
    
    private void limparCamposComputador(){
        computadorTable.clearSelection();
        
        idComputadorNumberLabel.setText("ID");
        nomeComputadorTextField.setText(null);
        descComputadorTextField.setText(null);
        quantidadeComputadorSpinner.setValue(0);
        
        adicionarComponenteButton.setEnabled(false);
        alterarComponenteButton.setEnabled(false);
        deletarComponenteButton.setEnabled(false);
        
        limparCamposComponente();
    }
    
    private void limparCamposComponente(){
        componenteTable.clearSelection();
        
        idComponenteNumberLabel.setText("ID");
        nomeComponenteTextField.setText(null);
        categoriaComponenteComboBox.setSelectedIndex(0);
        quantidadeComponenteSpinner.setValue(0);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        computadorPanel = new javax.swing.JPanel();
        buscarComputadorButton = new javax.swing.JButton();
        computadorScrollPane = new javax.swing.JScrollPane();
        computadorTable = new javax.swing.JTable();
        idComputadorLabel = new javax.swing.JLabel();
        idComputadorNumberLabel = new javax.swing.JLabel();
        nomeComputadorLabel = new javax.swing.JLabel();
        nomeComputadorTextField = new javax.swing.JTextField();
        descComputadorLabel = new javax.swing.JLabel();
        descComputadorTextField = new javax.swing.JTextField();
        quantidadeComputadorLabel = new javax.swing.JLabel();
        quantidadeComputadorSpinner = new javax.swing.JSpinner();
        adicionarComputadorButton = new javax.swing.JButton();
        alterarComputadorButton = new javax.swing.JButton();
        deletarComputadorButton = new javax.swing.JButton();
        limparComputadorButton = new javax.swing.JButton();
        buscaComputadorTextField = new javax.swing.JTextField();
        componentePanel = new javax.swing.JPanel();
        nomeComponenteTextField = new javax.swing.JTextField();
        idComponenteLabel = new javax.swing.JLabel();
        nomeComponenteLabel = new javax.swing.JLabel();
        quantidadeComponenteLabel = new javax.swing.JLabel();
        quantidadeComponenteSpinner = new javax.swing.JSpinner();
        categoriaComponenteLabel = new javax.swing.JLabel();
        categoriaComponenteComboBox = new javax.swing.JComboBox<>();
        adicionarComponenteButton = new javax.swing.JButton();
        alterarComponenteButton = new javax.swing.JButton();
        deletarComponenteButton = new javax.swing.JButton();
        idComponenteNumberLabel = new javax.swing.JLabel();
        componenteScrollPane = new javax.swing.JScrollPane();
        componenteTable = new javax.swing.JTable();
        limparComponenteButton = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Computadores");

        computadorPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Computadores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Inter Medium", 0, 14))); // NOI18N

        buscarComputadorButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        buscarComputadorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarComputadorButtonActionPerformed(evt);
            }
        });

        componenteTable = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int colIndex){
                return false;
            }
        };
        computadorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "IDPC", "Nome", "Descrição", "Quantidade"
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
        computadorTable.setFocusable(false);
        computadorTable.getTableHeader().setReorderingAllowed(false);
        computadorTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                computadorTableMouseClicked(evt);
            }
        });
        computadorScrollPane.setViewportView(computadorTable);

        idComputadorLabel.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        idComputadorLabel.setText("ID Computador:");

        idComputadorNumberLabel.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        idComputadorNumberLabel.setText("ID");

        nomeComputadorLabel.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        nomeComputadorLabel.setText("Nome");

        nomeComputadorTextField.setFont(new java.awt.Font("Inter Medium", 0, 12)); // NOI18N

        descComputadorLabel.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        descComputadorLabel.setText("Descrição");

        descComputadorTextField.setFont(new java.awt.Font("Inter Medium", 0, 12)); // NOI18N

        quantidadeComputadorLabel.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        quantidadeComputadorLabel.setText("Quantidade");

        quantidadeComputadorSpinner.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N

        adicionarComputadorButton.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        adicionarComputadorButton.setText("Adicionar");
        adicionarComputadorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarComputadorButtonActionPerformed(evt);
            }
        });

        alterarComputadorButton.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        alterarComputadorButton.setText("Alterar");
        alterarComputadorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarComputadorButtonActionPerformed(evt);
            }
        });

        deletarComputadorButton.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        deletarComputadorButton.setText("Deletar");
        deletarComputadorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletarComputadorButtonActionPerformed(evt);
            }
        });

        limparComputadorButton.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        limparComputadorButton.setText("Limpar");
        limparComputadorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparComputadorButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout computadorPanelLayout = new javax.swing.GroupLayout(computadorPanel);
        computadorPanel.setLayout(computadorPanelLayout);
        computadorPanelLayout.setHorizontalGroup(
            computadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(computadorPanelLayout.createSequentialGroup()
                .addGroup(computadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(computadorPanelLayout.createSequentialGroup()
                        .addComponent(buscaComputadorTextField)
                        .addGap(18, 18, 18)
                        .addComponent(buscarComputadorButton))
                    .addGroup(computadorPanelLayout.createSequentialGroup()
                        .addComponent(computadorScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(computadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, computadorPanelLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(adicionarComputadorButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(alterarComputadorButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deletarComputadorButton))
                    .addGroup(computadorPanelLayout.createSequentialGroup()
                        .addGroup(computadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(computadorPanelLayout.createSequentialGroup()
                                .addComponent(nomeComputadorLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nomeComputadorTextField))
                            .addGroup(computadorPanelLayout.createSequentialGroup()
                                .addComponent(descComputadorLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(descComputadorTextField))
                            .addGroup(computadorPanelLayout.createSequentialGroup()
                                .addComponent(quantidadeComputadorLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(quantidadeComputadorSpinner))
                            .addGroup(computadorPanelLayout.createSequentialGroup()
                                .addComponent(idComputadorLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(idComputadorNumberLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(limparComputadorButton)))
                        .addContainerGap())))
        );
        computadorPanelLayout.setVerticalGroup(
            computadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(computadorPanelLayout.createSequentialGroup()
                .addGroup(computadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(computadorPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(buscaComputadorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(computadorScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(computadorPanelLayout.createSequentialGroup()
                        .addGroup(computadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(computadorPanelLayout.createSequentialGroup()
                                .addGroup(computadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(computadorPanelLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(computadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(idComputadorLabel)
                                            .addComponent(idComputadorNumberLabel)))
                                    .addComponent(limparComputadorButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(computadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(nomeComputadorLabel)
                                    .addComponent(nomeComputadorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(computadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(descComputadorLabel)
                                    .addComponent(descComputadorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(computadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(quantidadeComputadorLabel)
                                    .addComponent(quantidadeComputadorSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50)
                                .addGroup(computadorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(adicionarComputadorButton)
                                    .addComponent(alterarComputadorButton)
                                    .addComponent(deletarComputadorButton)))
                            .addGroup(computadorPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(buscarComputadorButton)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        componentePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Componentes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Inter Medium", 0, 14))); // NOI18N

        nomeComponenteTextField.setFont(new java.awt.Font("Inter Medium", 0, 12)); // NOI18N

        idComponenteLabel.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        idComponenteLabel.setText("ID Componente:");

        nomeComponenteLabel.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        nomeComponenteLabel.setText("Nome");

        quantidadeComponenteLabel.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        quantidadeComponenteLabel.setText("Quantidade");

        quantidadeComponenteSpinner.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N

        categoriaComponenteLabel.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        categoriaComponenteLabel.setText("Categoria");

        categoriaComponenteComboBox.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        categoriaComponenteComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CPU/Processador", "Cooler CPU", "Placa mãe", "Memoria RAM", "Placa de vídeo", "Armazenamento", "Fonte", "Cabo", "Cooler Gabinete", "Gabinete", "Outro" }));

        adicionarComponenteButton.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        adicionarComponenteButton.setText("Adicionar");
        adicionarComponenteButton.setEnabled(false);
        adicionarComponenteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarComponenteButtonActionPerformed(evt);
            }
        });

        alterarComponenteButton.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        alterarComponenteButton.setText("Alterar");
        alterarComponenteButton.setEnabled(false);
        alterarComponenteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarComponenteButtonActionPerformed(evt);
            }
        });

        deletarComponenteButton.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        deletarComponenteButton.setText("Deletar");
        deletarComponenteButton.setEnabled(false);
        deletarComponenteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletarComponenteButtonActionPerformed(evt);
            }
        });

        idComponenteNumberLabel.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        idComponenteNumberLabel.setText("ID");

        componenteTable = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int colIndex){
                return false;
            }
        };
        componenteTable.setModel(new javax.swing.table.DefaultTableModel(
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
        componenteTable.setFocusable(false);
        componenteTable.getTableHeader().setReorderingAllowed(false);
        componenteTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                componenteTableMouseClicked(evt);
            }
        });
        componenteScrollPane.setViewportView(componenteTable);

        limparComponenteButton.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        limparComponenteButton.setText("Limpar");
        limparComponenteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparComponenteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout componentePanelLayout = new javax.swing.GroupLayout(componentePanel);
        componentePanel.setLayout(componentePanelLayout);
        componentePanelLayout.setHorizontalGroup(
            componentePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(componentePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(componenteScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(componentePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(componentePanelLayout.createSequentialGroup()
                        .addComponent(categoriaComponenteLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(categoriaComponenteComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(componentePanelLayout.createSequentialGroup()
                        .addComponent(quantidadeComponenteLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quantidadeComponenteSpinner))
                    .addGroup(componentePanelLayout.createSequentialGroup()
                        .addComponent(nomeComponenteLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nomeComponenteTextField))
                    .addGroup(componentePanelLayout.createSequentialGroup()
                        .addComponent(idComponenteLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idComponenteNumberLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(limparComponenteButton))
                    .addGroup(componentePanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(adicionarComponenteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(alterarComponenteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deletarComponenteButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        componentePanelLayout.setVerticalGroup(
            componentePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(componentePanelLayout.createSequentialGroup()
                .addGroup(componentePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(componentePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(componentePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idComponenteLabel)
                            .addComponent(idComponenteNumberLabel)))
                    .addComponent(limparComponenteButton))
                .addGap(7, 7, 7)
                .addGroup(componentePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeComponenteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomeComponenteLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(componentePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoriaComponenteLabel)
                    .addComponent(categoriaComponenteComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(componentePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantidadeComponenteLabel)
                    .addComponent(quantidadeComponenteSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(componentePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adicionarComponenteButton)
                    .addComponent(alterarComponenteButton)
                    .addComponent(deletarComponenteButton))
                .addContainerGap(13, Short.MAX_VALUE))
            .addComponent(componenteScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(componentePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(computadorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(computadorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(componentePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void computadorTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_computadorTableMouseClicked
        setarCamposComputador();
    }//GEN-LAST:event_computadorTableMouseClicked

    private void componenteTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_componenteTableMouseClicked
        setarCamposComponente();
    }//GEN-LAST:event_componenteTableMouseClicked

    private void buscarComputadorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarComputadorButtonActionPerformed
        atualizarComputador();
    }//GEN-LAST:event_buscarComputadorButtonActionPerformed

    private void limparComputadorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparComputadorButtonActionPerformed
        limparCamposComputador();
    }//GEN-LAST:event_limparComputadorButtonActionPerformed

    private void adicionarComputadorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarComputadorButtonActionPerformed
        criarComputador();
    }//GEN-LAST:event_adicionarComputadorButtonActionPerformed

    private void alterarComputadorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarComputadorButtonActionPerformed
        alterarComputador();
    }//GEN-LAST:event_alterarComputadorButtonActionPerformed

    private void deletarComputadorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletarComputadorButtonActionPerformed
        deletarComputador();
    }//GEN-LAST:event_deletarComputadorButtonActionPerformed

    private void adicionarComponenteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarComponenteButtonActionPerformed
        criarComponente();
    }//GEN-LAST:event_adicionarComponenteButtonActionPerformed

    private void alterarComponenteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarComponenteButtonActionPerformed
        alterarComponente();
    }//GEN-LAST:event_alterarComponenteButtonActionPerformed

    private void deletarComponenteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletarComponenteButtonActionPerformed
        deletarComponente();
    }//GEN-LAST:event_deletarComponenteButtonActionPerformed

    private void limparComponenteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparComponenteButtonActionPerformed
        limparCamposComponente();
    }//GEN-LAST:event_limparComponenteButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionarComponenteButton;
    private javax.swing.JButton adicionarComputadorButton;
    private javax.swing.JButton alterarComponenteButton;
    private javax.swing.JButton alterarComputadorButton;
    private javax.swing.JTextField buscaComputadorTextField;
    private javax.swing.JButton buscarComputadorButton;
    private javax.swing.JComboBox<String> categoriaComponenteComboBox;
    private javax.swing.JLabel categoriaComponenteLabel;
    private javax.swing.JPanel componentePanel;
    private javax.swing.JScrollPane componenteScrollPane;
    private javax.swing.JTable componenteTable;
    private javax.swing.JPanel computadorPanel;
    private javax.swing.JScrollPane computadorScrollPane;
    private javax.swing.JTable computadorTable;
    private javax.swing.JButton deletarComponenteButton;
    private javax.swing.JButton deletarComputadorButton;
    private javax.swing.JLabel descComputadorLabel;
    private javax.swing.JTextField descComputadorTextField;
    private javax.swing.JLabel idComponenteLabel;
    private javax.swing.JLabel idComponenteNumberLabel;
    private javax.swing.JLabel idComputadorLabel;
    private javax.swing.JLabel idComputadorNumberLabel;
    private javax.swing.JButton limparComponenteButton;
    private javax.swing.JButton limparComputadorButton;
    private javax.swing.JLabel nomeComponenteLabel;
    private javax.swing.JTextField nomeComponenteTextField;
    private javax.swing.JLabel nomeComputadorLabel;
    private javax.swing.JTextField nomeComputadorTextField;
    private javax.swing.JLabel quantidadeComponenteLabel;
    private javax.swing.JSpinner quantidadeComponenteSpinner;
    private javax.swing.JLabel quantidadeComputadorLabel;
    private javax.swing.JSpinner quantidadeComputadorSpinner;
    // End of variables declaration//GEN-END:variables
}
