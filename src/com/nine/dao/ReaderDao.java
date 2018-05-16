package com.nine.dao;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.nine.util.Reader;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONString;

public class ReaderDao {
	private final String TABLE_R = "readertb";
	private final String KEY_ID = "readerID";
	private final String KEY_PD = "readerPD";
	private final String KEY_NAME = "readerName";
	private final String KEY_DP = "department";
	private final String KEY_RT = "reputation";
	private final String KEY_SEX = "sex";
	private ComDao cd = new ComDao();
	//查找用户得到密码
	public String findUser(String readerID) {
		//数据库
		String psw = null;
		String sql = "select " + KEY_ID + "," + KEY_PD + " from " + TABLE_R +  " where " + KEY_ID + "=?;";
//		String sql = "select " + KEY_ID + "," + KEY_PD + " from " + TABLE_R +  " where " + KEY_NAME +"='郭逢枭' and "+KEY_ID + "=?;";
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		System.out.println(readerID);
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, readerID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
//				System.out.println(rs.getString("readerPD"));
				psw = rs.getString(2);
//				System.out.println(psw);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			cd.cloesConnection(rs, pstmt, con);
		}
		return psw;
	}
	//获取用户信息
	public JSONObject getReader(String readerID) {
		Reader temp_reader = new Reader();
		JSONObject jsonObject = null;
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select "+ KEY_NAME +","+ KEY_DP + "," + KEY_RT+ "," + KEY_SEX +
				" from " + TABLE_R + 
				" where " + KEY_ID + "=?";
//		String sql = "select "+ KEY_NAME +","+ KEY_DP + "," + KEY_RT+ "," + KEY_SEX +
//		" from " + TABLE_R + " where " + KEY_ID + "=? and readerName='郭逢枭'";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, readerID);
			rs = pstmt.executeQuery();
			System.out.println("readerinfo : "+sql);
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
			cd.cloesConnection(rs, pstmt, con);
		}
		return jsonObject;
	}
	//修改密码
	//验证旧密码
	public boolean validatePassword(String readerID, String old_password) {
		String psw = null;
//		SELECT readerID,readerPD FROM jichen.readertb where readerID='2015110305';
		String sql = "select " + KEY_ID + "," + KEY_PD + " from " + TABLE_R +  " where " + KEY_ID + "=?;";
		Connection con = cd.getConnection();
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
			cd.cloesConnection(rs, pstmt, con);
		}
		if(psw.equals(old_password)) {
			return true;
		}else {
			return false;
		}
	}
	//修改密码
	public boolean modifyPassword(String readerID, String new_password) {
//		update readertb set readerPD='admin1' where readerID='2015110305';
		String sql = "update "+TABLE_R+" set "+KEY_PD+"=? where "+KEY_ID+"=?";
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		System.out.println(readerID);
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, new_password);
			pstmt.setString(2, readerID);
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			cd.cloesConnection(rs, pstmt, con);
		}
		return true;
	}
	//修改读者信息
	public boolean modifyReader(String readerID, String readerName, String department, String reputation, String sex) {
//		update readertb set readerName='邓强1',department='志工部',reputation='10',sex='男'
//		where readerID='2015110305';con
		String sql = "update "+TABLE_R+
				" set "+ KEY_NAME+"=?," + KEY_DP +"=?," + KEY_RT + "=?,"+KEY_SEX+"=? "
				+ "where "+KEY_ID+"=?";
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, readerName);
			pstmt.setString(2, department);
			pstmt.setString(3, reputation);
			pstmt.setString(4, sex);
			pstmt.setString(5, readerID);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	//添加读者
	public boolean addReader(String readerID, String readerName, String department, String sex) {
//		insert into readertb values ('2015110316','黄宇','志工部','admin','100','女')
		String sql = "insert into "+TABLE_R+" values (?,?,?,'admin','99',?) ";
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, readerID);
			pstmt.setString(2, readerName);
			pstmt.setString(3, department);
			pstmt.setString(4, sex);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	//搜索读者
	public JSONArray searchReader(String key, String search_item) {
		JSONArray jsonArray = new JSONArray();
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		select readerID,readerName,department,reputation,sex
//		from readertb
//		where readerName like '%邓%'

		String sql = "select "+KEY_ID+","+KEY_NAME+","+KEY_DP+","+KEY_RT+","+KEY_SEX+
					" from "+TABLE_R+
					" where "+search_item+" like ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+key+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Map<String,String> searchReaderMap = new HashMap<String,String>();
				searchReaderMap.put(KEY_ID, rs.getString(1));
				searchReaderMap.put(KEY_NAME, rs.getString(2));
				searchReaderMap.put(KEY_DP, rs.getString(3));
				searchReaderMap.put(KEY_RT, rs.getString(4));
				searchReaderMap.put(KEY_SEX, rs.getString(5));
				jsonArray.add(searchReaderMap);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			cd.cloesConnection(rs, pstmt, con);
		}
		return jsonArray;
	}
	public boolean delReader(String readerID) {
//		delete from readertb where readerID='2015110301';
		String sql = "delete from "+TABLE_R+" where "+KEY_ID+"=?";
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, readerID);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
