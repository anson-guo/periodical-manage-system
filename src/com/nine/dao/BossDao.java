package com.nine.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.sql.PreparedStatement;

import net.sf.json.JSONArray;

public class BossDao {
	private final String TABLE_W="workloadtb";
	private final String KEY_EID="employeeID";
	private final String KEY_TR="toRegister";
	private final String KEY_LT="loginTime";
	private final String KEY_TTWORK="totalWorkload";
	private final String KEY_WDATE="workdate";
	private ComDao cd = new ComDao();
	public JSONArray returnWorkload(String employeeID) {
		JSONArray jsonArray = new JSONArray();
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		SELECT * FROM jichen.workloadtb where employeeID='20150101';
		String sql ="select "+KEY_TR+","+KEY_LT+","+KEY_TTWORK+","+KEY_WDATE+
				" from "+TABLE_W+" where "+KEY_EID +"=?";
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
		}
		return jsonArray;
	}
}
