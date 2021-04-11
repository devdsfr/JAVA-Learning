package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
//import java.sql.DriverManager;


public class ConnectionFactory {
	
	//Nome do usuario do mysql
	private static final String USERNAME = "root";
	
	//Senha do banco
	private static final String PASSWORD = "";
	
	//Caminho do banco de dados, porta ep nome do banco de dados
	private static final String DATABASE_URL = "jbdc:mysql://127.0.0.1:3306/agenda";
	
	//conexao com o banco de dados 
	
	public static Connection createConnectionToMySql() throws Exception {
		// faz que a classe seja carregada com o JVM
		Class.forName("com.mysql.jdbc.Driver");
		

	   //Criar a conexao com o banco de dados
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
	}
	
	public static void main(String[] args) throws Exception {
	 //Recuperar uma conexão com o banco de dados
		Connection con = createConnectionToMySql();
		
	//Testar se a conexão é nula
		if(con!=null) {
			System.out.println("Conexão obtida com sucesso");
			con.close();
		}
	}
	

}
