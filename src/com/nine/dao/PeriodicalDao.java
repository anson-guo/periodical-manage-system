package com.nine.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PeriodicalDao {
	private final String TABLE_PP = "pressPeriodicaltb";
	private final String TABLE_P = "periodicaltb";
//	private final String TABLE_PRESS = "presstb";
	private final String KEY_PRESSID = "pressID";
//	private final String KEY_PRESSNAME = "PressName";
	private final String KEY_PNAME = "periodicalName";
	private final String TABLE_PRESS = "presstb";
	private final String KEY_PID = "periodicalID";
	private final String KEY_ISSUE = "issue";
	private final String KEY_PTYPE = "periodicalType";
	private final String KEY_PRESS = "press";
	private final String KEY_BDATE = "buyDate";
	private final String KEY_SNUM = "seqNum";
	private ComDao cd = new ComDao();
	//返回出版社总表
	public JSONObject returnPresstb() {
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONObject json_return = null;
		Map<String, String> pressMap = new HashMap<String,String>();
//		SELECT * FROM .presstb
		String sql = "select * from "+TABLE_PRESS;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				pressMap.put(rs.getString(2), rs.getString(1));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			cd.closeAll(con,pstmt,rs);
		}
		json_return = JSONObject.fromObject(pressMap);
		return json_return;
	}
	//返回出版期刊总表
	public JSONObject returnPressPeriodicaltb(String pressID) {
		
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONObject json_return = new JSONObject();
		JSONArray jsonArray_return =  new JSONArray();
//		SELECT * FROM jichen.pressPeriodicaltb where pressID='111';
		String sql = "select * from "+TABLE_PP+ " where "+KEY_PRESSID+"=?";
		try {
			pstmt = con.prepareStatement(sql); 
			pstmt.setString(1, pressID);
			rs = pstmt.executeQuery();
			while(rs.next()) {
//				Map<String, String> pressPeriodicalMap = new HashMap<String,String>();
//				pressPeriodicalMap.put(rs.getString(1), rs.getString(2));
				json_return.put(rs.getString(2), rs.getString(2));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			cd.closeAll(con,pstmt,rs);
		}
		return json_return;
	}
	//编辑期刊
	public boolean modifyPeriodical(String periodicalID,String issue,String periodicalName, String periodicalTyep,String press) {
//		update periodicalstb set issue='201711',periodicalName='中文信息1',periodicalType='周刊（Z）',press='170'
//				where periodicalID='110';
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		String sql = "update "+TABLE_P+" set "+KEY_ISSUE+"=?,"+KEY_PNAME+"=?,"+KEY_PTYPE+"=?,"+KEY_PRESS+"=?"+
						" where "+KEY_PID+"=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, issue);
			pstmt.setString(2, periodicalName);
			pstmt.setString(3, periodicalTyep);
			pstmt.setString(4, press);
			pstmt.setString(5, periodicalID);
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	//添加期刊
	public boolean addPeriodical(String issue,String periodicalName, String periodicalTyep,String press, String seqNum) {
//		insert into periodicalstb (issue,seqNum,periodicalName,periodicalType,press,buyDate)
//		values ('201811','01','中文信息','周刊（Z）','107','2018-02-01');
		Date nowtime = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into "+TABLE_P+" ("+KEY_ISSUE+","+KEY_SNUM+","+KEY_PNAME+","+KEY_PTYPE+","+KEY_PRESS+","+KEY_BDATE+")"+
		" values (?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, issue);
			pstmt.setString(2, seqNum);
			pstmt.setString(3, periodicalName);
			pstmt.setString(4, periodicalTyep);
			pstmt.setString(5, press);
			pstmt.setString(6, df.format(nowtime));
			pstmt.executeUpdate();
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	//
}
