package common;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connection {
	
	public static void main(String[] args) {
		DB_Connection db_obj_con = new DB_Connection();
		System.out.println(db_obj_con.get_connection());
	}
	
	public Connection get_connection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmis2", "root", "We_Lcome@31*");
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

}