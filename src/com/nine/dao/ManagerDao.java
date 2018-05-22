package com.nine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.nine.util.Employee;
//import com.nine.util.Reader;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ManagerDao {
	private final String TABLE_E = "employeetb";
	private final String KEY_ID = "employeeID";
	private final String KEY_PD = "employeePD";
	private final String KEY_NAME = "employeeName";
	private final String KEY_POST = "post";
	private final String KEY_SEX = "sex";
	private final String TABLE_W="workloadtb";
	private final String KEY_TR="toRegister";
	private final String KEY_LT="loginTime";
	private final String KEY_TTWORK="totalWorkload";
	private final String KEY_WDATE="workdate";
	private ComDao cd = new ComDao();
	//查找用户得到密码
	public boolean findManager(String employeeID,String employeePD) {
		//数据库
		boolean istrue = false;
		String sql = "select * from " + TABLE_E +  
				" where " + KEY_ID + "=? and "+KEY_PD + "=MD5(?)";
//		String sql = "select " + KEY_ID + "," + KEY_PD + " from " + TABLE_R +  " where " + KEY_NAME +"='郭逢枭' and "+KEY_ID + "=?;";
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		System.out.println(readerID);
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, employeeID);
			pstmt.setString(2, employeePD);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				istrue = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			cd.closeAll(con,pstmt,rs);
		}
		return istrue;
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
			cd.closeAll(con,pstmt,rs);
		}
		return jsonObject;
	}
	//获取管理员工作量
	public JSONArray returnWorkload(String employeeID) {
		JSONArray jsonArray = new JSONArray();
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		SELECT * FROM jichen.workloadtb where employeeID='20150101';
		String sql ="select "+KEY_TR+","+KEY_LT+","+KEY_TTWORK+","+KEY_WDATE+
				" from "+TABLE_W+" where "+KEY_ID +"=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, employeeID);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Map<String,String> workloadMap = new HashMap<String,String>();
				workloadMap.put(KEY_TR, rs.getString(1));
				workloadMap.put(KEY_LT, rs.getString(2));
				workloadMap.put(KEY_TTWORK, rs.getString(3));
				workloadMap.put(KEY_WDATE, rs.getString(4));
				jsonArray.add(workloadMap);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			cd.closeAll(con,pstmt,rs);
		}
		return jsonArray;
	}
	
}
