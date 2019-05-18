package com.wksjava.tut.Database.examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleExample {
	public static void main(String[] args) {
		String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521/WONKDB1";
        String DB_USER = "pdbadmin";
        String DB_PASSWORD = "wonk123";
        String DRIVER = "oracle.jdbc.driver.OracleDriver";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        String query = "SELECT 1 as cnt FROM dual";
        try {
            // 드라이버를 로딩한다.
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e ) {
            e.printStackTrace();
        }

        try {
            // 데이터베이스의 연결을 설정한다.
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Statement를 가져온다.
            stmt = conn.createStatement();

            // SQL문을 실행한다.
            rs = stmt.executeQuery(query);

            while (rs.next()) { 
                int cnt = rs.getInt("cnt");
                // 결과를 출력한다.
                System.out.println(String.valueOf(cnt));
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            try {
                // ResultSet를 닫는다.
                rs.close();
                // Statement를 닫는다.
                stmt.close();
                // Connection를 닫는다.
                conn.close();
            } catch ( SQLException e ) {}
        }
	}
}
