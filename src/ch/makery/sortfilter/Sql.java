package ch.makery.sortfilter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sql {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/yxytest?characterEncoding=utf8&useSSL=true";

    // 数据库的用户名与密码，需要根据自己的设置
    private static final String USER = "root";
    private static final String PASS = "2254655";

    Connection conn = null;
    Statement stmt = null;

    public ResultSet inquire() {
    	try {
                String sql;
                sql = "SELECT * FROM tabtest";
                ResultSet rs = stmt.executeQuery(sql);
                return rs;
    	} catch (Exception e) {
    	       e.printStackTrace();
    	}
		return null;

    }

    public void connect() {
    	// 注册 JDBC 驱动
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
        	conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();

    	} catch(SQLException se){
    	      //Handle errors for JDBC
    	      se.printStackTrace();
    	} catch (Exception e) {
 	       e.printStackTrace();
    	}
    }

    public void delete(String name, String workName) {
    	String sql;
        sql = "delete FROM tabtest where name = \"" + name + "\" " + "and workname = \"" + workName + "\"";
        try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void add(String[] arr) {
    	String sql;
        sql = "insert into tabtest (major, classNum, name, studentID, compettition, time, level, prize, workname, path) values (\"" + arr[0] + "\", \"" + arr[1] + "\", \"" + arr[2] + "\", \"" + arr[3] + "\", \"" + arr[4] + "\", \"" + arr[5] + "\", \"" + arr[6] + "\", \"" + arr[7] + "\", \"" + arr[8] + "\", \"" + arr[9] + "\" )";
        try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void alter(String[] arr) {
    	String sql;
    	sql = "update tabtest set major = \""+arr[0]+"\", classNum = \""+arr[1]+"\", name = \""+arr[2]+"\", studentID = \""+arr[3]+"\", compettition = \""+arr[4]+"\", time = \""+arr[5]+"\", level = \""+arr[6]+"\", prize = \""+arr[7]+"\", workname = \""+arr[8]+"\", path = \""+arr[9]+"\"";
    	try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void close() {
    	try {
    		if(stmt != null) {
    			stmt.close();
    		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
        	if(conn != null) {
        		conn.close();
        	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
