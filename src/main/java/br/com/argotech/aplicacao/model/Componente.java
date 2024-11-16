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
import static br.com.argotech.aplicacao.telas.TelaComputadores.categoriaComponenteComboBox;
import static br.com.argotech.aplicacao.telas.TelaComputadores.idComponenteNumberLabel;
import static br.com.argotech.aplicacao.telas.TelaComputadores.nomeComponenteTextField;
import static br.com.argotech.aplicacao.telas.TelaComputadores.quantidadeComponenteSpinner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import static br.com.argotech.aplicacao.telas.TelaComputadores.componenteComputadorTable;
import static br.com.argotech.aplicacao.telas.TelaComputadores.idComputadorComputadorNumberLabel;

/**
 * @author DiogoNucci
 * @version 1.0
 */
public class Componente implements CRUD{
    
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    @Override
    public void criar() {
        String sql = "insert into tbcomps(idpc,nomecomp,catcomp,quantcomp) values(?,?,?,?)";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, idComputadorComputadorNumberLabel.getText());
            pst.setString(2, nomeComponenteTextField.getText());
            pst.setString(3, categoriaComponenteComboBox.getSelectedItem().toString());
            pst.setString(4, quantidadeComponenteSpinner.getValue().toString());
            
            if(nomeComponenteTextField.getText().isEmpty() || quantidadeComponenteSpinner.getValue().toString().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            }
            
            int adicionar = pst.executeUpdate();
            if(adicionar>0){
                JOptionPane.showMessageDialog(null, "Componente adicionado com sucesso");
                limparCampos();
                atualizar();
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void atualizar() {
        String sql = "select idcomp as ID, nomecomp as Nome, catcomp as Categoria, quantcomp as Quantidade from tbcomps where nomecomp like ? and idpc like ?";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, buscaComputadorTextField.getText() + "%");
            pst.setString(2, idComputadorComputadorNumberLabel.getText());
            rs = pst.executeQuery();
            
            componenteComputadorTable.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void alterar() {
        String sql = "update tbcomps set nomecomp=?,catcomp=?,quantcomp=? where idcomp=? and idpc=?";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, nomeComponenteTextField.getText());
            pst.setString(2, categoriaComponenteComboBox.getSelectedItem().toString());
            pst.setString(3, quantidadeComponenteSpinner.getValue().toString());
            
            pst.setString(4, idComponenteNumberLabel.getText());
            pst.setString(5, idComputadorComputadorNumberLabel.getText());
            
            if(nomeComponenteTextField.getText().isEmpty() || quantidadeComponenteSpinner.getValue().toString().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            }
            
            int alterar = pst.executeUpdate();
            if(alterar>0){
                JOptionPane.showMessageDialog(null, "Componente alterado com sucesso");
                limparCampos();
                atualizar();
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void deletar() {
        int confirma = JOptionPane.showConfirmDialog(null, "Certeza que deseja deletar este componente?", "Aviso", JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){           
            String sql = "delete from tbcomps where idcomp=? and idpc=?";
            try {
                con = ModuloConector.createConnection();
                pst = con.prepareStatement(sql);
                
                pst.setString(1, idComponenteNumberLabel.getText());
                pst.setString(2, idComputadorComputadorNumberLabel.getText());
                
                int deletar = pst.executeUpdate();

                if(deletar>0){
                    JOptionPane.showMessageDialog(null, "Componente deletado com sucesso");
                    atualizar();
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    @Override
    public void setarCampos() {
        int setar = componenteComputadorTable.getSelectedRow();
        
        idComponenteNumberLabel.setText(componenteComputadorTable.getModel().getValueAt(setar, 0).toString());
        nomeComponenteTextField.setText(componenteComputadorTable.getModel().getValueAt(setar, 1).toString());
        categoriaComponenteComboBox.setSelectedItem(componenteComputadorTable.getModel().getValueAt(setar, 2).toString());
        quantidadeComponenteSpinner.setValue(componenteComputadorTable.getModel().getValueAt(setar, 3));
    }

    @Override
    public void limparCampos() {
        componenteComputadorTable.clearSelection();
        
        idComponenteNumberLabel.setText("ID");
        nomeComponenteTextField.setText(null);
        categoriaComponenteComboBox.setSelectedIndex(0);
        quantidadeComponenteSpinner.setValue(0);
    }
    
}
