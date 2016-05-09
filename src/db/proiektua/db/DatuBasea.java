package db.proiektua.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

public class DatuBasea {
	
	private static DatuBasea nDatuBasea = null;
	private String erabiltzailea;
	private String pasahitza;
	private static String db;
	private static String url;
	private static String url2;
	
	private Connection conn = null;
	//private Statement st = null;
	private PreparedStatement ps = null;
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
				//System.out.println(url + " datu basera konexioa");
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
			//st.close();
			conn.close();
			if(conn.isClosed()){
				//System.out.println(url + " datu baseko konexioa itxita");
			}
		} catch (SQLException e) {
			System.out.println("Errore bat konexioa ixterakoan");
			e.printStackTrace();
		}
	}
	
	//ESKAERA BAT EGIN (INFORMAZIOA ATERA)
	protected ResultSet getQuery(String pQuery, ArrayList<Object> param){
		try{
			konexioaHasi();
			if(conn != null){
				ps = (PreparedStatement) conn.prepareStatement(pQuery);
				int kont = 1;
				for(Object o: param){
					if(o instanceof String){
						ps.setString(kont, (String) o);
					}
					else if(o instanceof Integer){
						ps.setInt(kont, (Integer) o);
					}
					else{
						ps.setBoolean(kont, (boolean) o);
					}
					kont++;
				}
				rs = ps.executeQuery();
			}
		}

		/*try{
			konexioaHasi();
			if(conn != null){
				st = (Statement) conn.createStatement();
				rs = st.executeQuery(pQuery);
			}
		}*/
		catch(SQLException e){
			e.printStackTrace();
			System.out.println("Konexiorik ez");
		}
		return rs;
	}
	
	//ESKAERA BAT BIDALI (INFORMAZIOA IGO)
	protected void setQuery(String pQuery, ArrayList<Object> param){
		try{
			konexioaHasi();
			if(conn != null){
				ps = (PreparedStatement) conn.prepareStatement(pQuery);
				int kont = 1;
				for(Object o: param){
					if(o instanceof String){
						ps.setString(kont, (String) o);
					}
					else if(o instanceof Integer){
						ps.setInt(kont, (Integer) o);
					}
					else{
						ps.setBoolean(kont, (boolean) o);
					}
					kont++;
				}
				ps.execute();
			}
		}
		/*try{
			konexioaHasi();
			if(conn != null){
				st = (Statement) conn.createStatement();
				st.executeUpdate(pQuery);
			}
		}*/
		catch(SQLException e){
			e.printStackTrace();
			System.out.println("Konexiorik ez");
		}
	}
}
