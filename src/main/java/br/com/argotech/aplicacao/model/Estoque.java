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
import static br.com.argotech.aplicacao.telas.TelaEstoque.buscaEstoqueTextField;
import static br.com.argotech.aplicacao.telas.TelaEstoque.categoriaEstoqueComboBox;
import static br.com.argotech.aplicacao.telas.TelaEstoque.estoqueEstoqueTable;
import static br.com.argotech.aplicacao.telas.TelaEstoque.idEstoqueNumberLabel;
import static br.com.argotech.aplicacao.telas.TelaEstoque.nomeEstoqueTextField;
import static br.com.argotech.aplicacao.telas.TelaEstoque.quantidadeEstoqueSpinner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 * @author DiogoNucci
 * @version 1.0
 */
public class Estoque implements CRUD{

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    @Override
    public void criar() {
        String sql = "insert into tbestoque(nomeest,catest,quantest) values(?,?,?)";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            //Pega todos os parametros inseridos pelo usuario
            pst.setString(1, nomeEstoqueTextField.getText());
            pst.setString(2, categoriaEstoqueComboBox.getSelectedItem().toString());
            pst.setString(3, quantidadeEstoqueSpinner.getValue().toString());
            
            //Checa se campos obritarios foram preenchidos
            if(nomeEstoqueTextField.getText().isEmpty() || quantidadeEstoqueSpinner.getValue().toString().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            }
            
            int adicionar = pst.executeUpdate();
            //Checa se é póssivel adicionar o usuario e retorna a confirmação para o usuario
            if(adicionar>0){
                JOptionPane.showMessageDialog(null, "Estoque adicionado com sucesso");
                limparCampos();
                atualizar();
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void atualizar() {
        String sql = "select idest as ID, nomeest as Nome, catest as Categoria, quantest as Quantidade from tbestoque where nomeest like ?";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, buscaEstoqueTextField.getText() + "%");
            rs = pst.executeQuery();
            
            estoqueEstoqueTable.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void alterar() {
        String sql = "update tbestoque set nomeest=?,catest=?,quantest=? where idest=?";
        try {
            con = ModuloConector.createConnection();
            pst = con.prepareStatement(sql);
            
            pst.setString(1, nomeEstoqueTextField.getText());
            pst.setString(2, categoriaEstoqueComboBox.getSelectedItem().toString());
            pst.setString(3, quantidadeEstoqueSpinner.getValue().toString());
            
            pst.setString(4, idEstoqueNumberLabel.getText());
            
            if(nomeEstoqueTextField.getText().isEmpty() || quantidadeEstoqueSpinner.getValue().toString().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios");
            }
            
            int alterar = pst.executeUpdate();

            if(alterar>0){
                JOptionPane.showMessageDialog(null, "Estoque alterado com sucesso");
                limparCampos();
                atualizar();
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void deletar() {
        int confirma = JOptionPane.showConfirmDialog(null, "Certeza que deseja deletar este estoque?", "Aviso", JOptionPane.YES_NO_OPTION);
        if(confirma==JOptionPane.YES_OPTION){           
            String sql = "delete from tbestoque where idest=?";
            try {
                con = ModuloConector.createConnection();
                pst = con.prepareStatement(sql);
                
                pst.setString(1, idEstoqueNumberLabel.getText());
                
                int deletar = pst.executeUpdate();

                if(deletar>0){
                    JOptionPane.showMessageDialog(null, "Estoque deletado com sucesso");
                    atualizar();
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    @Override
    public void setarCampos() {
        int setar = estoqueEstoqueTable.getSelectedRow();
        
        idEstoqueNumberLabel.setText(estoqueEstoqueTable.getModel().getValueAt(setar, 0).toString());
        nomeEstoqueTextField.setText(estoqueEstoqueTable.getModel().getValueAt(setar, 1).toString());
        categoriaEstoqueComboBox.setSelectedItem(estoqueEstoqueTable.getModel().getValueAt(setar, 2).toString());
        quantidadeEstoqueSpinner.setValue(estoqueEstoqueTable.getModel().getValueAt(setar, 3));
    }

    @Override
    public void limparCampos() {
        estoqueEstoqueTable.clearSelection();
        
        idEstoqueNumberLabel.setText("ID");
        nomeEstoqueTextField.setText(null);
        quantidadeEstoqueSpinner.setValue(0);
        categoriaEstoqueComboBox.setSelectedIndex(0);
    }
    
}
