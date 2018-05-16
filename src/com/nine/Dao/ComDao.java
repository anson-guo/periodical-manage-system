package com.nine.Dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class ComDao {
	public ComDao() {
		
	}
	public Connection getConnection() {
		Properties datainfo = new Properties();
		InputStream path = null;
		Connection connection = null;
		try {
			path = getClass().getResourceAsStream("/datainfo.properties");
			datainfo.load(path);
//			System.out.println(datainfo.getProperty("database") + datainfo.getProperty("user") + datainfo.getProperty("password"));
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/" + datainfo.getProperty("database") + "?characterEncoding=utf-8";
			String user = datainfo.getProperty("user");
			String password = datainfo.getProperty("password");
			try {
				Class.forName(driver);
				connection = DriverManager.getConnection(url, user, password);
			} catch(ClassNotFoundException e) {
				e.printStackTrace();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}catch(Exception e) {
			
		}
		
		return connection;
	}
	public void cloesConnection(PreparedStatement pstmt, Connection con) {
		try {
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
