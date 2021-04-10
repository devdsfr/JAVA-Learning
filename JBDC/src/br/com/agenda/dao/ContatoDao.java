package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;

import com.mysql.jdbc.PreparedStatement;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDao {
	
	/*
	 *CRUD
	 *c: create
	 *r: read
	 *u: update
	 *d: delete
	 */ 
	
	public void save(Contato contato) {
		String sql = "INSERT INTO contatos(nome, idade, datavadastro) VALUES (?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			// Criar conexao com o banco de dados
			conn = ConnectionFactory.createConnectionToMySql();
			
			// Criamos uma PreparedStatement para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//Adicionar os valores que são esperados pela query
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
		    
			//Executar a query
			pstm.execute();			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			//Fechar conexões
			try {
				if (pstm != null) {
					pstm.close();
				}
				
				if (conn != null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		} 
	}
	
}
