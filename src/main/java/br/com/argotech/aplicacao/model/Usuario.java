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
package br.com.argotech.aplicacao.model;

import br.com.argotech.aplicacao.dal.ModuloConector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import static br.com.argotech.aplicacao.telas.TelaUsuario.buscaUsuarioTextField;
import static br.com.argotech.aplicacao.telas.TelaUsuario.usuarioUsuarioTable;
import static br.com.argotech.aplicacao.telas.TelaUsuario.usuarioUsuarioTextField;
import static br.com.argotech.aplicacao.telas.TelaUsuario.foneUsuarioTextField;
import static br.com.argotech.aplicacao.telas.TelaUsuario.perfilUsuarioComboBox;
import static br.com.argotech.aplicacao.telas.TelaUsuario.loginUsuarioTextField;
import static br.com.argotech.aplicacao.telas.TelaUsuario.senhaUsuarioTextField;
import static br.com.argotech.aplicacao.telas.TelaUsuario.idUsuarioNumberLabel;

/**
 * @author DiogoNucci
 * @version 1.0
 */
public class Usuario implements CRUD{

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    @Override
    public void criar() {
        String sql = "insert into tbusuarios(usuario,fone,login,senha,acesso) values(?,?,?,?,?)";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            //Pega todos os parametros inseridos pelo usuario
            pst.setString(1, usuarioUsuarioTextField.getText());
            pst.setString(2, foneUsuarioTextField.getText());
            pst.setString(3, loginUsuarioTextField.getText());
            pst.setString(4, senhaUsuarioTextField.getText());
            pst.setString(5, perfilUsuarioComboBox.getSelectedItem().toString());
            
            //Checa se campos obritarios foram preenchidos
            if(usuarioUsuarioTextField.getText().isEmpty() || loginUsuarioTextField.getText().isEmpty() || senhaUsuarioTextField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            }
            
            int adicionar = pst.executeUpdate();
            //Checa se é póssivel adicionar o usuario e retorna a confirmação para o usuario
            if(adicionar>0){
                JOptionPane.showMessageDialog(null, "Usuario adicionado com sucesso");
                limparCampos();
                atualizar();
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void atualizar() {
        String sql = "select iduser as ID, usuario as Usuario, fone as Fone, login as Login, senha as Senha, acesso as Perfil from tbusuarios where usuario like ?";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, buscaUsuarioTextField.getText() + "%");
            rs = pst.executeQuery();
            
            usuarioUsuarioTable.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void alterar() {
        String sql = "update tbusuarios set usuario=?,fone=?,login=?,senha=?,acesso=? where iduser=?";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, usuarioUsuarioTextField.getText());
            pst.setString(2, foneUsuarioTextField.getText());
            pst.setString(3, loginUsuarioTextField.getText());
            pst.setString(4, senhaUsuarioTextField.getText());
            pst.setString(5, perfilUsuarioComboBox.getSelectedItem().toString());
            
            pst.setString(6, idUsuarioNumberLabel.getText());
            
            if(usuarioUsuarioTextField.getText().isEmpty() || loginUsuarioTextField.getText().isEmpty() || senhaUsuarioTextField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            }
            
            int alterar = pst.executeUpdate();

            if(alterar>0){
                JOptionPane.showMessageDialog(null, "Usuario alterado com sucesso");
                limparCampos();
                atualizar();
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void deletar() {
        int confirma = JOptionPane.showConfirmDialog(null, "Certeza que deseja deletar este usuario?", "Aviso", JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){           
            String sql = "delete from tbusuarios where iduser=?";
            try {
                con = ModuloConector.createConnection();
                pst = con.prepareStatement(sql);
                
                pst.setString(1, idUsuarioNumberLabel.getText());
                
                int deletar = pst.executeUpdate();

                if(deletar>0){
                    JOptionPane.showMessageDialog(null, "Usuario deletado com sucesso");
                    atualizar();
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    @Override
    public void setarCampos() {
        int setar = usuarioUsuarioTable.getSelectedRow();
        
        idUsuarioNumberLabel.setText(usuarioUsuarioTable.getModel().getValueAt(setar, 0).toString());
        usuarioUsuarioTextField.setText(usuarioUsuarioTable.getModel().getValueAt(setar, 1).toString());
        
        foneUsuarioTextField.setText(usuarioUsuarioTable.getModel().getValueAt(setar, 2).toString());
        
        loginUsuarioTextField.setText(usuarioUsuarioTable.getModel().getValueAt(setar, 3).toString());
        senhaUsuarioTextField.setText(usuarioUsuarioTable.getModel().getValueAt(setar, 4).toString());
        perfilUsuarioComboBox.setSelectedItem(usuarioUsuarioTable.getModel().getValueAt(setar, 5).toString());
    }

    @Override
    public void limparCampos() {
        usuarioUsuarioTable.clearSelection();
        
        idUsuarioNumberLabel.setText("ID");
        usuarioUsuarioTextField.setText(null);
        foneUsuarioTextField.setText(null);
        loginUsuarioTextField.setText(null);
        senhaUsuarioTextField.setText(null);
    }
    
}
