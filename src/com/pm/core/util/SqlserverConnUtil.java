package com.pm.core.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlserverConnUtil {

	public static Connection getConnection() {

		StringBuffer connStr = new StringBuffer();
		connStr.append("jdbc:sqlserver://");
		connStr.append("120.27.141.173");
		connStr.append(":");
		connStr.append("1433");
		connStr.append(";databaseName=");
		connStr.append("oujian_pm");
		connStr.append(";user=");
		connStr.append("pm");
		connStr.append(";password=");
		connStr.append("system123*");
		System.out.println(connStr);
		
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connStr.toString());
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	
	
	public static void closeConnect(ResultSet rs, Statement st, Connection conn) {
		
		try {
			
			if (rs!=null) {
				
				rs.close();
			}
			if (st!=null) {
				
				st.close();
			}
			if (conn!=null) {
				
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
