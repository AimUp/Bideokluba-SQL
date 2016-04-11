package db.proiektua.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatuBasea {
	
	private static DatuBasea nDatuBasea = null;
	private String erabiltzailea;
	private String pasahitza;
	private static String db;
	private static String url;
	private Connection conn = null;

	private DatuBasea() {
		erabiltzailea = "root";
		pasahitza = "";
		db = "Bideokluba";
		url = "jdbc:mysql://localhost/" + db + "?autoReconnect=true&useSSL=false";
	}
	
	public static DatuBasea getDatuBasea(){
		if(nDatuBasea==null){
			nDatuBasea = new DatuBasea();
		}
		return nDatuBasea;
	}
	
	protected void konexioaHasi(){
		try{
			Class.forName("com.mysql.jdbc.Connection");
			conn = (Connection) DriverManager.getConnection(url,erabiltzailea, pasahitza);
			if(conn != null){
				System.out.println(url + " datu basera konexioa");
			}
		}
		catch(SQLException e){
			System.out.println("Errore bat konexioa sortzerakoan");
		}
		catch(ClassNotFoundException e){
			System.out.println(e);
		}
	}
	
	protected void konexioaItxi(){
		try {
			conn.close();
			if(conn.isClosed()){
				System.out.println(url + " datu baseko konexioa itxita");
			}
		} catch (SQLException e) {
			System.out.println("Errore bat konexioa ixterakoan");
			e.printStackTrace();
		}
	}
	
	protected ResultSet getQuery(String pQuery){
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
	
	protected void setQuery(String pQuery){
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
