package com.nine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import com.nine.util.BorrowElement;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BorrowDao {
	private final String TABLE_B = "borrowtb";
	private final String TABLE_P = "periodicalstb";
	private final String KEY_RID = "readerID";
	private final String KEY_PID = "periodicalID";
	private final String KEY_PNAME = "periodicalName";
	private final String KEY_BDATE = "beginDate";
	private final String KEY_EDATE = "endDate";
	private final String KEY_REMARKS = "remarks";
	private ComDao cd = new ComDao();
	
//	public JSONObject getBorrowList(String readerID) {
	public JSONArray getBorrowList(String readerID) {
		JSONObject jsonObject = null;
		Map<String, Object> borrowMap = new HashMap<String, Object>();
		JSONArray jsonArray = new JSONArray();
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select " + TABLE_B + "." + KEY_PID + "," + KEY_PNAME + "," + KEY_BDATE + "," + KEY_EDATE + "," + KEY_REMARKS +
				" from " + TABLE_B + "," + TABLE_P +
				" where " + TABLE_B + "." + KEY_PID + "=" + TABLE_P + "." + KEY_PID + " and " + KEY_EDATE +"='0'" + " and " + KEY_RID + "='" + readerID +"'";
		System.out.println(sql);
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BorrowElement bElement = new BorrowElement();
//				System.out.println(rs.getString(1));
				borrowMap.put("periodicalID",rs.getString(1));
//				System.out.println(rs.getString(2));
				borrowMap.put("periodicalName",rs.getString(2));
//				System.out.println(rs.getString(3).toString());
				borrowMap.put("beginDate",rs.getDate(3).toString());
				jsonObject = JSONObject.fromObject(borrowMap);
//				System.out.println(jsonObject.toString());
				jsonArray.add(jsonObject);
//				System.out.println(borrowList);
//				jsonArray = JSONArray.fromObject(borrowList);
//				System.out.println(jsonArray.toString());
//				jsonObject = JSONObject.fromObject(jsonArray);
			}
//			System.out.println(jsonArray.toString());
		}catch(SQLException e) {
			e.printStackTrace();
		}
//		return jsonObject;
		return jsonArray;
	}
}
