package ch.makery.sortfilter;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sql {
    Connection conn = null;
    Statement stmt = null;

    public void checkTable() {
  	      String sql = "CREATE TABLE IF NOT EXISTS tabtest " +
                    "(timeYear      CHAR(50)    NOT NULL, " +
                    " timeMonth     CHAR(50)     NOT NULL, " +
                    " level         CHAR(50) NOT NULL, " +
                    " prize         CHAR(50) NOT NULL,"+
                    " major         CHAR(50) NOT NULL,"+
                    " grade         CHAR(50) NOT NULL,"+
                    " workName      CHAR(50) NOT NULL,"+
                    " name          CHAR(50) NOT NULL,"+
                    " path          CHAR(50) NOT NULL)";
  	      try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

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
    	try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	      try {
			conn = DriverManager.getConnection("jdbc:sqlite:yxytest.db");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

  	      try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
        sql = "insert into tabtest (timeYear, timeMonth, level, prize, major, grade, workName, name, path) values (\"" + arr[0] + "\", \"" + arr[1] + "\", \"" + arr[2] + "\", \"" + arr[3] + "\", \"" + arr[4] + "\", \"" + arr[5] + "\", \"" + arr[6] + "\", \"" + arr[7] + "\", \"" + arr[8] + "\")";
        try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void alter(String[] arr) {
    	String sql;
    	sql = "update tabtest set timeYear = \""+arr[0]+"\", timeMonth = \""+arr[1]+"\", level = \""+arr[2]+"\", prize = \""+arr[3]+"\", major = \""+arr[4]+"\", grade = \""+arr[5]+"\", workName = \""+arr[6]+"\", name = \""+arr[7]+"\", path = \""+arr[8]+"\"";
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
