package com.nine.Dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nine.util.Reader;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONString;

public class UserDao {
	public String findUser(String readerID) {
		//不调用数据库
//		String pwd = null;
//		if(username.equals("admin")){
//			return "admin";
//		}else {
//			return pwd;
//		}
		//数据库
		String psw = null;
		String sql = "select readerID,readerPD from readertb where readerID=?;";
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		System.out.println(readerID);
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, readerID);
			rs = pstmt.executeQuery();
//			System.out.println(sql);
			if(rs.next()) {
//				System.out.println(rs.getString("readerPD"));
				psw = rs.getString(2);
//				System.out.println(psw);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null)con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return psw;
	}
	public JSONObject getReader(String readerID) {
		Reader temp_reader = new Reader();
		JSONObject jsonObject = null;
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select readerName,department,reputation,sex from readertb where readerId=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, readerID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				temp_reader.setReaderID(readerID);
				temp_reader.setReaderName(rs.getString(1));
				temp_reader.setDepartment(rs.getString(2));
				temp_reader.setReputation(rs.getString(3));
				temp_reader.setSex(rs.getString(4));
				jsonObject = JSONObject.fromObject(temp_reader);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null)con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return jsonObject;
	}
	public Connection getConnection() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/periodicaldatabase?characterEncoding=utf-8";
		String user = "root";
		String password = "root";
		Connection connection = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
