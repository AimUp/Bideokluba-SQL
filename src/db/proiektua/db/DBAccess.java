package db.proiektua.db;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.sql.Connection;

import db.proiektua.logic.Bazkidea;
import db.proiektua.logic.Pelikula;;

public class DBAccess {
	//konexioa
	Connection conn = null;
	//statement
	Statement st = null;
	//result set
	ResultSet rs = null;
	
	//BAZKIDEA -->
	
	//BAZKIDEAK DB-TIK LORTU
	
	public ArrayList<Bazkidea> getBazkideak(){
		
		ArrayList<Bazkidea> bazkideak = new ArrayList<Bazkidea>();
		try {
			konexioaHasi();
			ResultSet rs = st.executeQuery("select * from Bazkidea");
			while(rs.next()){
				Bazkidea b = new Bazkidea(rs.getInt("idBazkidea"), rs.getString("pasahitza"), rs.getString("izena"), rs.getString("abizena"), rs.getString("helbidea"), rs.getInt("kreditua"), rs.getString("egoera"), rs.getInt("noiztikBezero"));
				bazkideak.add(b);
			}
			
			konexioaAmaitu();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bazkideak;
		
	}
	
	//BAZKIDEAK SORTU
	
	public void createBazkidea(Bazkidea b){
		
		try {
			konexioaHasi();
			st.execute("insert into bazkidea values ('"+b.getIdBazkidea()+"','"+b.getPasahitza()+"', '"+b.getIzena()+"', '"+b.getAbizena()+"', '"+b.getHelbidea()+"', '"+b.getKreditua()+"', '"+b.getEgoera()+"', '"+b.getNoiztikBezero()+"')");
			konexioaAmaitu();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//PELIKULA -->
	
	//PELIKULAK DB-TIK LORTU
	
	public ArrayList<Pelikula> getPelikulak(){
		
		ArrayList<Pelikula> pelikulak = new ArrayList<Pelikula>();
		try {
			konexioaHasi();
			ResultSet rs = st.executeQuery("select * from Pelikula");
			while(rs.next()){
				Pelikula p = new Pelikula(rs.getString("izena"), rs.getInt("idPelikula"), rs.getInt("prezioa"), rs.getString("egoera"), rs.getInt("data"));
				pelikulak.add(p);
			}
			
			konexioaAmaitu();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pelikulak;
		
	}
	
	//PELIKULAK SORTU
	
	public void createPelikula(Pelikula p){
		
		try {
			konexioaHasi();
			st.execute("insert into pelikula values ('"+p.getIzena()+"', '"+p.getIdPelikula()+"', '"+p.getPrezioa()+"', '"+p.getEgoera()+"', '"+p.getData()+"')");
			konexioaAmaitu();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//KONEXIOA
	
	private void konexioaHasi() throws ClassNotFoundException, SQLException{
		
		//driver-a kargatu
		Class.forName("com.mysql.jdbc.Driver");
		
		//konexioa
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bideokluba?autoReconnect=true&useSSL=false", "root", "anemateos");
		
		//statement
		st = conn.createStatement();
	}
	
	private void konexioaAmaitu() throws SQLException {
		
		if(rs!=null){
			rs.close();
		}
		
		if(st!=null){
			st.close();
		}
		
		if(conn!=null){
			conn.close();
		}
		
	}

}
