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
import static br.com.argotech.aplicacao.telas.TelaComputadores.buscaComputadorTextField;
import static br.com.argotech.aplicacao.telas.TelaComputadores.descComputadorTextField;
import static br.com.argotech.aplicacao.telas.TelaComputadores.nomeComputadorTextField;
import static br.com.argotech.aplicacao.telas.TelaComputadores.quantidadeComputadorSpinner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import static br.com.argotech.aplicacao.telas.TelaComputadores.computadorComputadorTable;
import static br.com.argotech.aplicacao.telas.TelaComputadores.idComputadorComputadorNumberLabel;

/**
 * @author DiogoNucci
 * @version 1.0
 */
public class Computador implements CRUD{

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    Componente componente = new Componente();
    
    @Override
    public void criar() {
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
                limparCampos();
                atualizar();
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void atualizar() {
        String sql = "select idpc as IDPC, nomepc as Nome, descpc as Descrição, quantpcs as Quantidade from tbpcs where nomepc like ?";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, buscaComputadorTextField.getText() + "%");
            rs = pst.executeQuery();
            
            computadorComputadorTable.setModel(DbUtils.resultSetToTableModel(rs));
            componente.atualizar();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void alterar() {
        String sql = "update tbpcs set nomepc=?,descpc=?,quantpcs=? where idpc=?";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, nomeComputadorTextField.getText());
            pst.setString(2, descComputadorTextField.getText());
            pst.setString(3, quantidadeComputadorSpinner.getValue().toString());
            
            pst.setString(4, idComputadorComputadorNumberLabel.getText());
            
            if(nomeComputadorTextField.getText().isEmpty() || quantidadeComputadorSpinner.getValue().toString().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            }
            
            int alterar = pst.executeUpdate();

            if(alterar>0){
                JOptionPane.showMessageDialog(null, "Computador alterado com sucesso");
                limparCampos();
                atualizar();
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void deletar() {
        int confirma = JOptionPane.showConfirmDialog(null, "Certeza que deseja deletar este computador? \nVocê tambem deletará seus componentes e as suas vendas", "Aviso", JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){           
            String sql = "delete from tbpcs where idpc=?";
            try {
                con = ModuloConector.createConnection();
                pst = con.prepareStatement(sql);
                
                pst.setString(1, idComputadorComputadorNumberLabel.getText());
                
                int deletar = pst.executeUpdate();

                if(deletar>0){
                    JOptionPane.showMessageDialog(null, "Computador deletado com sucesso");
                    atualizar();
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    @Override
    public void setarCampos() {
        int setar = computadorComputadorTable.getSelectedRow();
        
        idComputadorComputadorNumberLabel.setText(computadorComputadorTable.getModel().getValueAt(setar, 0).toString());
        nomeComputadorTextField.setText(computadorComputadorTable.getModel().getValueAt(setar, 1).toString());
        descComputadorTextField.setText(computadorComputadorTable.getModel().getValueAt(setar, 2).toString());
        quantidadeComputadorSpinner.setValue(computadorComputadorTable.getModel().getValueAt(setar, 3));
        
        componente.atualizar();
    }

    @Override
    public void limparCampos() {
        computadorComputadorTable.clearSelection();
        
        idComputadorComputadorNumberLabel.setText("ID");
        nomeComputadorTextField.setText(null);
        descComputadorTextField.setText(null);
        quantidadeComputadorSpinner.setValue(0);
        
        componente.limparCampos();
    }
    
}
