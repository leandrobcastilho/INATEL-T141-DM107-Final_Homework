package br.inatel.t141.dm107.finalhomework;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.inatel.t141.dm107.finalhomework.data.ConnectionFactory;

public class ConnectionTest {

	public static void main(String[] args) {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection conn = connectionFactory.getConnection();
		ResultSet rs;
		try {
			rs = conn.prepareStatement("show tables").executeQuery();
			while (rs.next()) {
				String s = rs.getString(1);
				System.out.println(s);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
