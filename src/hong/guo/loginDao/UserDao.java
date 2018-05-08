package hong.guo.loginDao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	public String findUser(String username) {
		//不调用数据库
//		String pwd = null;
//		if(username.equals("admin")){
//			return "admin";
//		}else {
//			return pwd;
//		}
		//数据库
		String psw = null;
		String sql = "select readerName,readerPD from readertb where readerName='郭逢枭';";
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println(username);
		try {
			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			System.out.println(sql);
			if(rs.next()) {
				System.out.println(rs.getString("readerPD"));
				psw = rs.getString("readerPD");
				System.out.println(psw);
			}else {
				psw = null;
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
	public void addUser(String username, String psw) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into user(username,password) values(?,?)";
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, psw);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(con!=null)con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
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
