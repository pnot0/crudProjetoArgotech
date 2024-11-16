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
import br.com.argotech.aplicacao.telas.TelaVenda;
import static br.com.argotech.aplicacao.telas.TelaVenda.buscaClienteVendaTextField;
import static br.com.argotech.aplicacao.telas.TelaVenda.buscaComputadorVendaTextField;
import static br.com.argotech.aplicacao.telas.TelaVenda.buscaVendaVendaTextField;
import static br.com.argotech.aplicacao.telas.TelaVenda.clienteVendaTable;
import static br.com.argotech.aplicacao.telas.TelaVenda.computadorVendaTable;
import static br.com.argotech.aplicacao.telas.TelaVenda.idClienteVendaNumberTextField;
import static br.com.argotech.aplicacao.telas.TelaVenda.idComputadorVendaNumberTextField;
import static br.com.argotech.aplicacao.telas.TelaVenda.idVendaNumberLabel;
import static br.com.argotech.aplicacao.telas.TelaVenda.limparIDClienteVendaButton;
import static br.com.argotech.aplicacao.telas.TelaVenda.limparIDComputadorVendaButton;
import static br.com.argotech.aplicacao.telas.TelaVenda.precoVendaFormattedTextField;
import static br.com.argotech.aplicacao.telas.TelaVenda.quantidadeVendaSpinner;
import static br.com.argotech.aplicacao.telas.TelaVenda.vendaVendaTable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 * @author DiogoNucci
 * @version 1.0
 */
public class Venda implements CRUD{

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    @Override
    public void criar() {
        String sql = "insert into tbvendas(idpc,idcli,quantvenda,precovenda) values (?,?,?,?)";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, idComputadorVendaNumberTextField.getText());
            pst.setString(2, idClienteVendaNumberTextField.getText());
            pst.setString(3, quantidadeVendaSpinner.getValue().toString());
            pst.setString(4, precoVendaFormattedTextField.getText());
            
            if(idComputadorVendaNumberTextField.getText().isEmpty() ||
                    idClienteVendaNumberTextField.getText().isEmpty() ||
                    quantidadeVendaSpinner.getValue().toString().isEmpty() ||
                    precoVendaFormattedTextField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            }
            
            int adicionar = pst.executeUpdate();
            if(adicionar>0){
                JOptionPane.showMessageDialog(null, "Venda adicionada com sucesso");
                limparCampos();
                atualizar();
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void atualizar() {
        String sql = "select idvenda as IDVenda, idcli as IDCliente, idpc as IDPC, quantvenda as Quantidade, precovenda as Preço, totalvenda as Total, datavenda as Data from tbvendas where idvenda like ?";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, buscaVendaVendaTextField.getText() + "%");
            rs = pst.executeQuery();
            
            vendaVendaTable.setModel(DbUtils.resultSetToTableModel(rs));
            atualizarComputador();
            atualizarCliente();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void atualizarComputador(){
        String sql = "select idpc as ID, nomepc as Nome, descpc as Descrição from tbpcs where nomepc like ?";
        try {
            con = ModuloConector.createConnection();

            if(!idComputadorVendaNumberTextField.getText().isEmpty()){
                pst = con.prepareStatement(sql + " and idpc like ?");
                pst.setString(1, buscaComputadorVendaTextField.getText() + "%");
                pst.setString(2, idComputadorVendaNumberTextField.getText());
            }else{
                pst = con.prepareStatement(sql);
                pst.setString(1, buscaComputadorVendaTextField.getText() + "%");
            }

            rs = pst.executeQuery();
            
            computadorVendaTable.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void atualizarCliente(){
        String sql = "select idcli as ID, nomecli as Nome, telcli as Fone from tbclientes where nomecli like ?";
        try {
            con = ModuloConector.createConnection();

            if(!idComputadorVendaNumberTextField.getText().isEmpty()){
                pst = con.prepareStatement(sql + " and idcli like ?");
                pst.setString(1, buscaClienteVendaTextField.getText() + "%");
                pst.setString(2, idClienteVendaNumberTextField.getText());
            }else{
                pst = con.prepareStatement(sql);
                pst.setString(1, buscaClienteVendaTextField.getText() + "%");
            }

            rs = pst.executeQuery();
            
            clienteVendaTable.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @Override
    public void alterar() {
        String sql = "update tbvendas set idpc=?,idcli=?,quantvenda=?,precovenda=? where idvenda=?";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, idComputadorVendaNumberTextField.getText());
            pst.setString(2, idClienteVendaNumberTextField.getText());
            pst.setString(3, quantidadeVendaSpinner.getValue().toString());
            pst.setString(4, precoVendaFormattedTextField.getText());
            
            pst.setString(5, idVendaNumberLabel.getText());
            
            if(idComputadorVendaNumberTextField.getText().isEmpty() ||
                    idClienteVendaNumberTextField.getText().isEmpty() ||
                    quantidadeVendaSpinner.getValue().toString().isEmpty() ||
                    precoVendaFormattedTextField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            }
            
            int adicionar = pst.executeUpdate();
            if(adicionar>0){
                JOptionPane.showMessageDialog(null, "Venda alterada com sucesso");
                limparCampos();
                atualizar();
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void deletar() {
        int confirma = JOptionPane.showConfirmDialog(null, "Certeza que deseja deletar esta venda?", "Aviso", JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){
            String sql = "delete from tbvendas where idvenda=?";
            try {
                con = ModuloConector.createConnection();
                pst = con.prepareStatement(sql);
                
                pst.setString(1, idVendaNumberLabel.getText());
                
                int deletar = pst.executeUpdate();
                
                if(deletar>0){
                    JOptionPane.showMessageDialog(null, "Venda deletada com sucesso");
                    atualizar();
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    @Override
    public void setarCampos() {
        int setar = vendaVendaTable.getSelectedRow();
        
        idVendaNumberLabel.setText(vendaVendaTable.getModel().getValueAt(setar, 0).toString());
        idClienteVendaNumberTextField.setText(vendaVendaTable.getModel().getValueAt(setar, 1).toString());
        idComputadorVendaNumberTextField.setText(vendaVendaTable.getModel().getValueAt(setar, 2).toString());
        quantidadeVendaSpinner.setValue(vendaVendaTable.getModel().getValueAt(setar, 3));
        precoVendaFormattedTextField.setText((vendaVendaTable.getModel().getValueAt(setar, 4)).toString());
    }
    
    public void setarIDComputador() {
        int setar = computadorVendaTable.getSelectedRow();
        
        idComputadorVendaNumberTextField.setText(computadorVendaTable.getModel().getValueAt(setar, 0).toString());
    }
    
    public void limparIDComputador(){
        
        idComputadorVendaNumberTextField.setText(null);
        limparIDComputadorVendaButton.setEnabled(false);
        
        computadorVendaTable.clearSelection();
    }
        
    public void setarIDCliente() {
        int setar = clienteVendaTable.getSelectedRow();
        
        idClienteVendaNumberTextField.setText(clienteVendaTable.getModel().getValueAt(setar, 0).toString());
    }
    
    public void limparIDCliente(){
        
        idClienteVendaNumberTextField.setText(null);
        limparIDClienteVendaButton.setEnabled(false);
        
        clienteVendaTable.clearSelection();
    }

    @Override
    public void limparCampos() {
        limparIDComputador();
        limparIDCliente();
        
        idVendaNumberLabel.setText("ID");
        quantidadeVendaSpinner.setValue(0);
        precoVendaFormattedTextField.setText(null);
        
        vendaVendaTable.clearSelection();
    }
    
}
