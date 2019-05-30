package com.webservicecrud.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class TimestampRegister {

	public static void dataCadastro() throws SQLException {

		String url = "jdbc:mysql://localhost:3306/world?useTimezone=true&serverTimezone=UTC&useSSL=false";
		Connection con = DriverManager.getConnection(url, "root", "mysql");

		Calendar calendar = Calendar.getInstance();
		java.sql.Timestamp timeStamp = new java.sql.Timestamp(calendar.getTime().getTime());

		String sqlInsert = "INSERT INTO usuarios (dt_cadastro) VALUES ('?')";
		PreparedStatement ps = con.prepareStatement(sqlInsert);
		ps.setTimestamp(1, timeStamp);

		ps.executeUpdate();
		ps.close();
	}
}
