/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.argotech.aplicacao.telas;

import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author DiogoNucci
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane = new javax.swing.JDesktopPane();
        argotechLogoLabel = new javax.swing.JLabel();
        usuarioLabel = new javax.swing.JLabel();
        dataLabel = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        registroMenu = new javax.swing.JMenu();
        usuarioMenuItem = new javax.swing.JMenuItem();
        clienteMenuItem = new javax.swing.JMenuItem();
        estoqueMenuItem = new javax.swing.JMenuItem();
        pcMenuItem = new javax.swing.JMenuItem();
        relatorioMenu = new javax.swing.JMenu();
        vendasMenuItem = new javax.swing.JMenuItem();
        ajudaMenu = new javax.swing.JMenu();
        sobreMenuItem = new javax.swing.JMenuItem();
        opcoesMenu = new javax.swing.JMenu();
        sairMenuItem = new javax.swing.JMenuItem();
        fecharMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Argotech Sistema CRUD");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jDesktopPane.setPreferredSize(new java.awt.Dimension(620, 455));

        javax.swing.GroupLayout jDesktopPaneLayout = new javax.swing.GroupLayout(jDesktopPane);
        jDesktopPane.setLayout(jDesktopPaneLayout);
        jDesktopPaneLayout.setHorizontalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 840, Short.MAX_VALUE)
        );
        jDesktopPaneLayout.setVerticalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        argotechLogoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/argotech_img.png"))); // NOI18N

        usuarioLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        usuarioLabel.setText("Usuário");

        dataLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        dataLabel.setText("Data");

        registroMenu.setText("Registro");

        usuarioMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_DOWN_MASK));
        usuarioMenuItem.setText("Usuarios");
        usuarioMenuItem.setEnabled(false);
        usuarioMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioMenuItemActionPerformed(evt);
            }
        });
        registroMenu.add(usuarioMenuItem);

        clienteMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_DOWN_MASK));
        clienteMenuItem.setText("Clientes");
        clienteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienteMenuItemActionPerformed(evt);
            }
        });
        registroMenu.add(clienteMenuItem);

        estoqueMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.ALT_DOWN_MASK));
        estoqueMenuItem.setText("Estoque");
        estoqueMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estoqueMenuItemActionPerformed(evt);
            }
        });
        registroMenu.add(estoqueMenuItem);

        pcMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_DOWN_MASK));
        pcMenuItem.setText("Computadores");
        pcMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pcMenuItemActionPerformed(evt);
            }
        });
        registroMenu.add(pcMenuItem);

        menuBar.add(registroMenu);

        relatorioMenu.setText("Relatório");
        relatorioMenu.setEnabled(false);

        vendasMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_DOWN_MASK));
        vendasMenuItem.setText("Vendas");
        relatorioMenu.add(vendasMenuItem);

        menuBar.add(relatorioMenu);

        ajudaMenu.setText("Ajuda");

        sobreMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.ALT_DOWN_MASK));
        sobreMenuItem.setText("Sobre");
        sobreMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sobreMenuItemActionPerformed(evt);
            }
        });
        ajudaMenu.add(sobreMenuItem);

        menuBar.add(ajudaMenu);

        opcoesMenu.setText("Opções");

        sairMenuItem.setText("Sair");
        sairMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairMenuItemActionPerformed(evt);
            }
        });
        opcoesMenu.add(sairMenuItem);

        fecharMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        fecharMenuItem.setText("Fechar");
        fecharMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecharMenuItemActionPerformed(evt);
            }
        });
        opcoesMenu.add(fecharMenuItem);

        menuBar.add(opcoesMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usuarioLabel)
                    .addComponent(dataLabel)
                    .addComponent(argotechLogoLabel))
                .addContainerGap(222, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(dataLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(usuarioLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 406, Short.MAX_VALUE)
                        .addComponent(argotechLogoLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jDesktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1280, 757));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void usuarioMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioMenuItemActionPerformed
        TelaUsuario telaUsuario = new TelaUsuario();
        telaUsuario.setVisible(true);
        jDesktopPane.add(telaUsuario);
    }//GEN-LAST:event_usuarioMenuItemActionPerformed

    private void sobreMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sobreMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sobreMenuItemActionPerformed

    //Pega a data do sistema e atribui para a tela principal a data formatada
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        Date date = new Date();
        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
        dataLabel.setText(format.format(date) + " - Data acesso");
    }//GEN-LAST:event_formWindowActivated

    //Abre uma caixa questionando se o usuario deseja sair, se sim fechara o sistema, se não fechara somente a caixa
    private void fecharMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecharMenuItemActionPerformed
        int exit = JOptionPane.showConfirmDialog(null, "Você deseja fechar?","Aviso",JOptionPane.YES_NO_OPTION);
        if(exit == 0){
            System.exit(0);
        }
    }//GEN-LAST:event_fecharMenuItemActionPerformed

    private void clienteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clienteMenuItemActionPerformed
        TelaCliente telaCliente = new TelaCliente();
        telaCliente.setVisible(true);
        jDesktopPane.add(telaCliente);
    }//GEN-LAST:event_clienteMenuItemActionPerformed

    private void sairMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairMenuItemActionPerformed
        int logout = JOptionPane.showConfirmDialog(null, "Você deseja sair?","Aviso",JOptionPane.YES_NO_OPTION);
        if(logout == 0){
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_sairMenuItemActionPerformed

    private void estoqueMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estoqueMenuItemActionPerformed
        TelaEstoque telaEstoque = new TelaEstoque();
        telaEstoque.setVisible(true);
        jDesktopPane.add(telaEstoque);
    }//GEN-LAST:event_estoqueMenuItemActionPerformed

    private void pcMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pcMenuItemActionPerformed
        TelaComputadores telaComputadores = new TelaComputadores();
        telaComputadores.setVisible(true);
        jDesktopPane.add(telaComputadores);
    }//GEN-LAST:event_pcMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu ajudaMenu;
    private javax.swing.JLabel argotechLogoLabel;
    private javax.swing.JMenuItem clienteMenuItem;
    private javax.swing.JLabel dataLabel;
    private javax.swing.JMenuItem estoqueMenuItem;
    private javax.swing.JMenuItem fecharMenuItem;
    private javax.swing.JDesktopPane jDesktopPane;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu opcoesMenu;
    private javax.swing.JMenuItem pcMenuItem;
    private javax.swing.JMenu registroMenu;
    public static javax.swing.JMenu relatorioMenu;
    private javax.swing.JMenuItem sairMenuItem;
    private javax.swing.JMenuItem sobreMenuItem;
    public static javax.swing.JLabel usuarioLabel;
    public static javax.swing.JMenuItem usuarioMenuItem;
    private javax.swing.JMenuItem vendasMenuItem;
    // End of variables declaration//GEN-END:variables
}