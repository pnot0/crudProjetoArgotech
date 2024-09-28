package br.com.teste.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	private static final String USERNAME = "db_operator";
	private static final String PASSWORD = "AVNS_xR3MhLBXeV_Bz0nrGir";
	private static final String DATABASE_URL = "jdbc:mysql://argotechteste-argotech.h.aivencloud.com:14053/argotechteste";
	
	public static Connection createConnectionSQL() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
	}
	
}
