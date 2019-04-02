package oldTest;

import java.sql.Connection;
//import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NewJDBC {
	
	@SuppressWarnings("unused")
	public void ConnectDataBase(){
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("oracele.jdbc.driver.OracleDriver");
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			//DriverManager.registerDriver(new DB2Driver());
			//Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//Class.forName("net.sourceforge.jtds.jdbc.Drive");
			//Class.forName("com.sybase.jdbc.SybDriver");
			//Class.forName("com.sybase.jdbc2.jdbc.SybDriver");
			//Class.forName("org.postgresql.Driver");
			//Class.forName("com.ibm.db2.jdbc.DB2.Driver");
			//Class.forName("com.informix.jdbc.IfxDriver");
			String url="";
			String name="root";
			String password="yanru545";
			System.out.print("Connecting to database...");
			//CMSZDriverManager.getConnection(url, name, password);
			Connection conn = DriverManager.getConnection(url, name, password);
			System.out.println("Creating statement...");
			Statement stmt = conn.createStatement();
			String sql;
			sql = "SELECT id, first, last, age FROM Employees";
			ResultSet rs = stmt.executeQuery(sql);
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		NewJDBC nj = new NewJDBC();
		nj.ConnectDataBase();
	}
}
