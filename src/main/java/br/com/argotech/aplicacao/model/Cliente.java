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
import static br.com.argotech.aplicacao.telas.TelaUsuario.idUsuarioNumberLabel;
import static br.com.argotech.aplicacao.telas.TelaCliente.buscaClienteTextField;
import static br.com.argotech.aplicacao.telas.TelaCliente.clienteClienteTable;
import static br.com.argotech.aplicacao.telas.TelaCliente.emailClienteTextField;
import static br.com.argotech.aplicacao.telas.TelaCliente.enderecoClienteTextField;
import static br.com.argotech.aplicacao.telas.TelaCliente.foneClienteTextField;
import static br.com.argotech.aplicacao.telas.TelaCliente.nomeClienteTextField;
import static br.com.argotech.aplicacao.telas.TelaCliente.idClienteClienteNumberLabel;

/**
 * @author DiogoNucci
 * @version 1.0
 */
public class Cliente implements CRUD{

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    @Override
    public void criar() {
        String sql = "insert into tbclientes(nomecli,endcli,telcli,emailcli) values(?,?,?,?)";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            //Pega todos os parametros inseridos pelo usuario
            pst.setString(1, nomeClienteTextField.getText());
            pst.setString(2, enderecoClienteTextField.getText());
            pst.setString(3, foneClienteTextField.getText());
            pst.setString(4, emailClienteTextField.getText());
            
            //Checa se campos obritarios foram preenchidos
            if(nomeClienteTextField.getText().isEmpty() || foneClienteTextField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            }
            
            int adicionar = pst.executeUpdate();
            //Checa se é póssivel adicionar o usuario e retorna a confirmação para o usuario
            if(adicionar>0){
                JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso");
                limparCampos();
                atualizar();
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void atualizar() {
        String sql = "select idcli as ID, nomecli as Nome, endcli as Endereço, telcli as Fone, emailcli as Email from tbclientes where nomecli like ?";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, buscaClienteTextField.getText() + "%");
            rs = pst.executeQuery();
            
            clienteClienteTable.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void alterar() {
        String sql = "update tbclientes set nomecli=?,endcli=?,telcli=?,emailcli=? where idcli=?";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, nomeClienteTextField.getText());
            pst.setString(2, enderecoClienteTextField.getText());
            pst.setString(3, foneClienteTextField.getText());
            pst.setString(4, emailClienteTextField.getText());
            
            pst.setString(5, idClienteClienteNumberLabel.getText());
            
            if(nomeClienteTextField.getText().isEmpty() || foneClienteTextField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            }
            
            int alterar = pst.executeUpdate();

            if(alterar>0){
                JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso");
                limparCampos();
                atualizar();
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void deletar() {
        int confirma = JOptionPane.showConfirmDialog(null, "Certeza que deseja deletar este cliente? \nVocê tambem deletará suas vendas", "Aviso", JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){           
            String sql = "delete from tbclientes where idcli=?";
            try {
                con = ModuloConector.createConnection();
                pst = con.prepareStatement(sql);
                
                pst.setString(1, idClienteClienteNumberLabel.getText());
                
                int deletar = pst.executeUpdate();

                if(deletar>0){
                    JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso");
                    atualizar();
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    @Override
    public void setarCampos() {
        int setar = clienteClienteTable.getSelectedRow();
        
        idClienteClienteNumberLabel.setText(clienteClienteTable.getModel().getValueAt(setar, 0).toString());
        nomeClienteTextField.setText(clienteClienteTable.getModel().getValueAt(setar, 1).toString());
        
        if(clienteClienteTable.getModel().getValueAt(setar, 2).toString() != null){
            enderecoClienteTextField.setText(clienteClienteTable.getModel().getValueAt(setar, 2).toString());
        }else{
            enderecoClienteTextField.setText("");
        }
        
        foneClienteTextField.setText(clienteClienteTable.getModel().getValueAt(setar, 3).toString());
        
        if(clienteClienteTable.getModel().getValueAt(setar, 4).toString() != null){
            emailClienteTextField.setText(clienteClienteTable.getModel().getValueAt(setar, 4).toString());
        }else{
            emailClienteTextField.setText("");
        } 
    }

    @Override
    public void limparCampos() {
        clienteClienteTable.clearSelection();
        
        idClienteClienteNumberLabel.setText("ID");
        nomeClienteTextField.setText(null);
        enderecoClienteTextField.setText(null);
        foneClienteTextField.setText(null);
        emailClienteTextField.setText(null);
    }
    
}
