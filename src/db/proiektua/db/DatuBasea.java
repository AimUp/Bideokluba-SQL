package db.proiektua.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatuBasea {

	private String erabiltzailea = "root";
	private String pasahitza = "";
	private static String db = "Bideokluba";
	private static String url = "jdbc:mysql://localhost/"+db;
	private Connection conn = null;

	public DatuBasea() {
		try{
			Class.forName("com.mysql.jdbc.Connection");
			conn = (Connection) DriverManager.getConnection(url,erabiltzailea, pasahitza);
			if(conn != null){
				System.out.println(url + " datu basera konexioa");
			}
		}
		catch(SQLException e){
			System.out.println("Errore bat konexioan");
		}
		catch(ClassNotFoundException e){
			System.out.println(e);
		}
	}
	
	public ResultSet getQuery(String pQuery){
		Statement state = null;
		ResultSet emaitza = null;
		try{
			state = (Statement) conn.createStatement();
			emaitza = state.executeQuery(pQuery);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return emaitza;
	}
	
	public void setQuery(String pQuery){
		Statement state = null;
		try{
			state = (Statement) conn.createStatement();
			state.executeUpdate(pQuery);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
}
