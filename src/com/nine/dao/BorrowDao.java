package com.nine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
	private final String KEY_PRESS = "press";
	private final String KEY_BDATE = "beginDate";
	private final String KEY_EDATE = "endDate";
	private final String KEY_REMARKS = "remarks";
	private ComDao cd = new ComDao();
	
//	public JSONObject getBorrowList(String readerID) {
	//获取借阅列表
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
//		System.out.println(sql);
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
		}finally {
			cd.cloesConnection(rs, pstmt, con);
		}
//		return jsonObject;
		return jsonArray;
	}
	//归还图书
	public boolean returnPeriodical( String readerID, String periodicalID) {
		Date nowtime = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Connection con=cd.getConnection();
		PreparedStatement pstmt = null;
//		update borrowtb set endDate='2018-01-05' where readerID='20150101' and periodicalID='115N000120170101';
		String sql = "update " + TABLE_B+" set " + KEY_EDATE +"=? where " + KEY_RID + "=? and " +
					KEY_PID + "=?";
//		System.out.println(sql);
//		System.out.println(df.format(nowtime));
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, df.format(nowtime).toString());
			pstmt.setString(2, readerID);
			pstmt.setString(3, periodicalID);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			cd.cloesConnection(null, pstmt, con);
		}
			return true;
	}
	//显示历史借阅
	public JSONArray historylist(String readerID) {
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		select borrowtb.periodicalID, periodicalName, beginDate, endDate, remarks
//		from borrowtb, periodicalstb
//		where readerID='20150101' and borrowtb.periodicalID = periodicalstb.periodicalID and endDate not like '0000-00-00';
		String sql = "select "+ TABLE_B + "." + KEY_PID + ", " + KEY_PNAME + ", " + KEY_BDATE + ", "+ KEY_EDATE + ", "+ KEY_REMARKS +
					" from " + TABLE_B + ", "+ TABLE_P +
					" where " + TABLE_B +"." + KEY_PID + "=" + TABLE_P + "." + KEY_PID +" and " + KEY_RID +"=? and " + KEY_EDATE + " != '0000-00-00'";
		Map<String, String> historyMap = new HashMap<String,String>();
		JSONObject jsonObject = null;
		JSONArray jsonArray = new JSONArray();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, readerID); 
			rs = pstmt.executeQuery();
			while(rs.next()) {
				historyMap.put(KEY_PID, rs.getString(1));
				historyMap.put(KEY_PNAME, rs.getString(2));
				historyMap.put(KEY_BDATE, rs.getString(3));
				historyMap.put(KEY_EDATE, rs.getString(4));
				historyMap.put(KEY_REMARKS, rs.getString(5));
				jsonObject = JSONObject.fromObject(historyMap);
//				System.out.println(jsonObject.toString());
				jsonArray.add(jsonObject);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			cd.cloesConnection(rs, pstmt, con);
		}
		return jsonArray;
	}
	//搜索框
	public JSONObject searchlist(String key, String search_item, String endcount, String readerID) {
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		JSONObject return_json = new JSONObject();
		int listcount = 0;
		String  sql = "select " + KEY_PNAME +"," + KEY_PRESS+"," + "count(2) as count" +
				" from "+ TABLE_P +
				" where "+search_item+" like ? group by " + KEY_PNAME +", "+ KEY_PRESS;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+key+"%");
			rs = pstmt.executeQuery();
			//计数
			while(rs.next()) {
				listcount ++;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			cd.cloesConnection(rs, pstmt, con);
		}
		return_json.put("listcount", listcount);
		
//		select periodicalID, periodicalName, press, count(2) as count from periodicalstb
//		where periodicalName like '%世界%'
//		group by periodicalName;
		sql = "select " + KEY_PNAME +"," + KEY_PRESS+"," + "count(2) as count" +
					" from "+ TABLE_P +
					" where "+search_item+" like ? group by " + KEY_PNAME +", "+ KEY_PRESS +
					" limit "+ (Integer.parseInt(endcount)-5 < 0? 0 : Integer.parseInt(endcount)-5) + "," + (Integer.parseInt(endcount) > listcount ? listcount: Integer.parseInt(endcount));
		System.out.println("list : "+sql);
		JSONObject jsonObject = null;
		JSONArray jsonArray = new JSONArray();
		Map<String,String> searchMap = new HashMap<String,String>();
		
		try {
			con = cd.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+key+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				searchMap.put(KEY_PNAME,rs.getString(1));
				searchMap.put(KEY_PRESS,rs.getString(2));
				searchMap.put("number",rs.getString(3));
//				select count(1) from borrowtb where periodicalID='115N000120170101' and readerID='20150101' and endDate = '0000-00-00';
				String sql2 = "select count(1) from "+TABLE_B+" where "+KEY_PID+"=? and "+KEY_RID+"=? and "+ KEY_EDATE+"='0000-00-00'";
				Connection con2 = cd.getConnection();
				PreparedStatement pstmt2 = con.prepareStatement(sql2);
				pstmt2.setString(1, searchMap.get(KEY_PID));
				pstmt2.setString(2, readerID);
//				System.out.println(sql);
				ResultSet rs2 = pstmt2.executeQuery();
				if(rs2.next()) {
					System.out.println("KEY_PID is " + searchMap.get(KEY_PID)+" "+ readerID);
					System.out.println("rs.getString is "+rs.getString(1));
					if(rs2.getString(1).equals("0")) {
//						System.out.println("true");
						searchMap.put("borrowed", "true");
					}else {
//						System.out.println("false");
						searchMap.put("borrowed", "false");
					}
				}
				cd.cloesConnection(rs2, pstmt2, con2);
				jsonObject = JSONObject.fromObject(searchMap);
				jsonArray.add(jsonObject);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			cd.cloesConnection(rs,pstmt, con);
		}
		
		return_json.put("search_result", jsonArray);
		return return_json;
	}
	//借阅图书
	public boolean borrowPeriodical(String readerID, String periodicalID) {
		Connection con = cd.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Date nowtime = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		SELECT count(1) from borrowtb where readerID='20150101' and periodicalID='115N000120170101';
		String sql = "select count(1) from " + TABLE_B + " where " + KEY_PID + "=? and " + KEY_RID + "=?";
//		System.out.println("borrowPeriodical---------------------------");
//		System.out.println(sql);
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(2, readerID);
			pstmt.setString(1, periodicalID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if(rs.getInt(1) == 0) {
//					insert into borrowtb(readerID, periodicalID, beginDate,endDate) values('20150101','115N000120170101','2017-05-12','0');
					sql = "insert into "+TABLE_B +"("+KEY_RID+", "+KEY_PID+", "+KEY_BDATE+", "+KEY_EDATE+ ") values(?,?,?,'0000-00-00')";
//					System.out.println(sql);
					cd.cloesConnection(rs, pstmt, con);
					con = cd.getConnection();
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, readerID);
					pstmt.setString(2, periodicalID);
					pstmt.setString(3, df.format(nowtime));
					
				}else {
//					update borrowtb set beginDate='2018-05-12', endDate='0000-00-00' where readerID='20150101' and periodicalID='115N000320170101';
					sql = "update "+ TABLE_B + " set " + KEY_BDATE +"=?, "+ KEY_EDATE +"='0000-00-00' where " + KEY_RID + "=? and " + KEY_PID + "=?";
//					System.out.println(sql);
					cd.cloesConnection(rs, pstmt, con);
					con = cd.getConnection();
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, df.format(nowtime));
					pstmt.setString(2, readerID);
					pstmt.setString(3, periodicalID);
				}				
				pstmt.executeUpdate();
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			cd.cloesConnection(rs,pstmt, con);
		}
		return true;
	}
}
