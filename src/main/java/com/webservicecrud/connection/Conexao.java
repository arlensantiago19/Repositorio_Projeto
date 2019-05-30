package com.webservicecrud.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	String serverName = "localhost:3306";

	String mydatabase = "world?useTimezone=true&serverTimezone=UTC&useSSL=false";

	String url = "jdbc:mysql://" + serverName + "/" + mydatabase;

	String username = "root";

	String password = "mysql";

	public Connection conexao;

	public Conexao() throws SQLException {
		conexao = DriverManager.getConnection(url, username, password);
	}
}
