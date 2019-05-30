package com.webservicecrud.services;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.webservicecrud.connection.Conexao;

public class TableGenerator {
	
	public static void criaTabela(String tabNome, HashMap<String, String> tabCampos) throws SQLException {
		Conexao con = new Conexao();
		Statement st = con.conexao.createStatement();
		StringBuilder sb = new StringBuilder("CREATE TABLE ");
		sb.append(tabNome);
		sb.append(" (");
		for (String campo : tabCampos.keySet()) {
			sb.append(campo);
			sb.append(" " + tabCampos.get(campo));
			sb.append(", ");
		}
		sb.replace(sb.lastIndexOf(", "), sb.length(), ")");

		System.out.println("DDL: " + sb.toString());
		st.executeUpdate(sb.toString());

	}
}
