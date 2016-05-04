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
	private static String url2;
	
	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;

	private DatuBasea() {
		erabiltzailea = "root";
		pasahitza = "root";
		db = "Bideokluba";
		url = "jdbc:mysql://localhost:3306/" + db;
		url2 = "?autoReconnect=true&useSSL=false";
	}
	
	public static DatuBasea getDatuBasea(){
		if(nDatuBasea==null){
			nDatuBasea = new DatuBasea();
		}
		return nDatuBasea;
	}
	
	//KONEXIOA HASI
	protected void konexioaHasi(){
		try{
			//driver-a kargatu
			Class.forName("com.mysql.jdbc.Connection");
			//konexioa
			conn = (Connection) DriverManager.getConnection(url+url2,erabiltzailea, pasahitza);
			if(conn != null){
				System.out.println(url + " datu basera konexioa");
				//st = conn.createStatement();
			}
		}
		catch(SQLException e){
			System.out.println("Errore bat konexioa sortzerakoan");
		}
		catch(ClassNotFoundException e){
			System.out.println(e);
		}
	}
	
	//KONEXIOA ITXI
	protected void konexioaItxi(){
		try {
			rs.close();
			st.close();
			conn.close();
			if(conn.isClosed()){
				System.out.println(url + " datu baseko konexioa itxita");
			}
		} catch (SQLException e) {
			System.out.println("Errore bat konexioa ixterakoan");
			e.printStackTrace();
		}
	}
	
	//ESKAERA BAT EGIN (INFORMAZIOA ATERA)
	protected ResultSet getQuery(String pQuery){
		try{
			if(conn != null){
				st = (Statement) conn.createStatement();
				rs = st.executeQuery(pQuery);
			}
		}
		catch(SQLException e){
			System.out.println("Konexiorik ez");
		}
		return rs;
	}
	
	//ESKAERA BAT BIDALI (INFORMAZIOA IGO)
	protected void setQuery(String pQuery){
		try{
			if(conn != null){
				st = (Statement) conn.createStatement();
				st.executeUpdate(pQuery);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			System.out.println("Konexiorik ez");
		}
	}
}
