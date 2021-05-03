package br.uel.prova.model;

import java.sql.Connection;
import java.sql.DriverManager;
public class ConnectionFactory {
	
	public Connection getConnection(){
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(
					"jdbc:mysql://127.0.01:3306/"
					+ "contatos?useTimezone=true&serverTimezone=America/"
					+ "Sao_Paulo"
					+ "&allowPublicKeyRetrieval=true&useSSL=false", 
					"aluno", "aluno2021");
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

}