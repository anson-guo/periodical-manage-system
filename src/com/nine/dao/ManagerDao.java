package com.nine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nine.util.Employee;
import com.nine.util.Reader;

import net.sf.json.JSONObject;

public class ManagerDao {
	private final String TABLE_E = "employeetb";
	private final String KEY_ID = "employeeID";
	private final String KEY_PD = "employeePD";
	private final String KEY_NAME = "employeeName";
	private final String KEY_POST = "post";
	private final String KEY_SEX = "sex";
	private ComDao cd = new ComDao();
	//查找用户得到密码
	public String findManager(String employeeID) {
		//数据库
		String psw = null;
		String sql = "select " + KEY_ID + "," + KEY_PD + " from " + TABLE_E +  " where " + KEY_ID + "=?;";
//		String sql = "select " + KEY_ID + "," + KEY_PD + " from " + TABLE_R +  " where " + KEY_NAME +"='郭逢枭' and "+KEY_ID + "=?;";
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		System.out.println(readerID);
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, employeeID);
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
	// 获取管理员信息
	public JSONObject getEmployee(String employeeID) {
		Employee temp_employee = new Employee();
		JSONObject jsonObject = null;
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//SELECT employeeID, employeeName,sex,post FROM jichen.employeetb;
		String sql = "select " + KEY_ID + "," + KEY_NAME + "," + KEY_SEX + "," + KEY_POST + 
				" from " + TABLE_E + 
				" where " + KEY_ID + "=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, employeeID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				temp_employee.setEmployeeID(employeeID);
				temp_employee.setEmployeeName(rs.getString(2));
				temp_employee.setSex(rs.getString(3));
				temp_employee.setPost(rs.getString(4));
				jsonObject = JSONObject.fromObject(temp_employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cd.cloesConnection(rs, pstmt, con);
		}
		return jsonObject;
	}
	
}
