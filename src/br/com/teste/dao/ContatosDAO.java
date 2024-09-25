package br.com.teste.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.teste.factory.ConnectionFactory;
import br.com.teste.model.Contatos;

public class ContatosDAO {
	
	Connection con = null;
	PreparedStatement pstm = null;
	
	public void save(Contatos contatos) {
		String sql = "INSERT INTO contatos(nome, idade, dataCadastro) VALUES (?, ?, ?)";
			
		try {
			con = ConnectionFactory.createConnectionSQL();
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, contatos.getNome());
			pstm.setInt(2, contatos.getIdade());
			pstm.setDate(3, new Date(contatos.getDataCadastro().getTime()));
			
			pstm.execute();
			
			System.out.println("Dados enviados com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				if(pstm!=null) {
					pstm.close();
				}if(con!=null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(Contatos contatos) {
		
		String sql = "UPDATE contatos SET nome = ?, idade = ?, dataCadastro = ? "+"WHERE id = ?";
		
		try {
			con = ConnectionFactory.createConnectionSQL();
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, contatos.getNome());
			pstm.setInt(2, contatos.getIdade());
			pstm.setDate(3, new Date(contatos.getDataCadastro().getTime()));
			
			pstm.setInt(4, contatos.getId());
			
			pstm.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				try {
					if(pstm!=null) {
						pstm.close();
					}if(con!=null) {
						con.close();
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void deleteByID(int id) {
		
		String sql = "DELETE FROM contatos WHERE id = ?";
		
		try {
			con = ConnectionFactory.createConnectionSQL();
			
			pstm = con.prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			pstm.execute();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}if(con != null) {
					con.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Contatos> getContatos(){
		
		String sql = "SELECT * FROM contatos";
		
		List<Contatos> contatosLista = new ArrayList<Contatos>();
		
		ResultSet rset = null;
		
		try {
			con = ConnectionFactory.createConnectionSQL();
			pstm = con.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				Contatos contatos = new Contatos();
				
				contatos.setId(rset.getInt("id"));
				contatos.setNome(rset.getString("nome"));
				contatos.setIdade(rset.getInt("idade"));
				contatos.setDataCadastro(rset.getDate("dataCadastro"));
				
				contatosLista.add(contatos);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rset!=null) {
					rset.close();
				}
				if(pstm!=null) {
					pstm.close();
				}if(con!=null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return contatosLista;
	}
}

	
