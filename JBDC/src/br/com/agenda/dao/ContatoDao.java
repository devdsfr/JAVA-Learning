package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//import java.sql.PreparedStatement;
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
			
			System.out.println("Contato salvo com sucesso");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			//Fechar as conexões
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

	public List<Contato> getContatos(){
		
		String sql = "SELECT * FROM contatos";
		
		List<Contato> contatos = new ArrayList<Contato>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco *SELECT*
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySql();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				Contato contato = new Contato();
				
				//Recuperar o id
				contato.setId(rset.getInt("id"));
				//Recuperar o nome
				contato.setNome(rset.getString("nome"));
				//Recuperar a idade
				contato.setIdade(rset.getInt("idade"));
				//Recuperar a data de cadastro
				contato.setDataCadastro(rset.getDate("datacadastro"));
				
				contatos.add(contato);		
			}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rset !=null) {
						rset.close();
					}
				
					if(pstm != null) {
						pstm.close();
					}
				
					if(conn != null) {
						conn.close();
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			return contatos;	
	}
}
