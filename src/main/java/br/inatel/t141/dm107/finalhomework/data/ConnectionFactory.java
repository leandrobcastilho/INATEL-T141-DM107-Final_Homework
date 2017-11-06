package br.inatel.t141.dm107.finalhomework.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	private DbConfig dbConfig = null;

	public Connection getConnection() {

		DbConfig dbConfig = getDbConfig();
		try {
			Connection conn = DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUser(),
					dbConfig.getPassword());
			if (conn != null) {
				System.out.println("Connected");
			}
			return conn;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	private DbConfig getDbConfig() {

		if (dbConfig == null) {
			dbConfig = new DbConfig("jdbc:mysql://localhost/INATEL_T141_DM107_Final_Homework", "root",
					"PwUserMysql1234");

			Properties prop = new Properties();
			try (InputStream input = new FileInputStream(getFilePath("config.properties"))) {

				// load a properties file
				prop.load(input);

				String url = prop.getProperty("url");
				if (url != null)
					dbConfig.setUrl(url);

				String user = prop.getProperty("user");
				if (user != null)
					dbConfig.setUser(user);

				String password = prop.getProperty("password");
				if (password != null)
					dbConfig.setPassword(password);

			} catch (IOException io) {
				io.printStackTrace();
			}
		}

		return dbConfig;
	}

	private String getFilePath(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		String pathFile = fileName;
		try {
			pathFile = URLDecoder.decode(classLoader.getResource(fileName).getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return pathFile;
	}

}