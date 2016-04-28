package db.proiektua.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.proiektua.logic.Administratzailea;
import db.proiektua.logic.Bazkidea;
import db.proiektua.logic.Erabiltzailea;
import db.proiektua.logic.Pelikula;

public class Aginduak{

	private ResultSet erantzuna;
	
	public Aginduak(){
		erantzuna = null;
	}
	
	public ArrayList<String> pelikulaKodeaLortu(){
		ArrayList<String> pelikulak = new ArrayList<String>();
		try {
			DatuBasea.getDatuBasea().konexioaHasi();
			erantzuna = DatuBasea.getDatuBasea().getQuery("select * from Bazkidea");
			while(erantzuna.next()){
				pelikulak.add(erantzuna.getString("idBazkidea"));
			}
			DatuBasea.getDatuBasea().konexioaItxi();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pelikulak;
	}
	
	
	//CREATE
	public void createBazkidea(Bazkidea b){
		DatuBasea.getDatuBasea().konexioaHasi();
		//DatuBasea.getDatuBasea().setQuery("insert into bazkidea values ('"+b.getIdBazkidea()+"','"+b.getPasahitza()+"', '"+b.getIzena()+"', '"+b.getAbizena()+"', '"+b.getHelbidea()+"'");
		DatuBasea.getDatuBasea().konexioaItxi();
	}
	
	public void createPelikula(Pelikula p){
		DatuBasea.getDatuBasea().konexioaHasi();
		DatuBasea.getDatuBasea().setQuery("insert into pelikula values ('"+p.getIzena()+"', '"+p.getIdPelikula()+"', '"+p.getPrezioa()+"', '"+p.getEgoera()+"', '"+p.getData()+"')");
		DatuBasea.getDatuBasea().konexioaHasi();
	}
	
	//GET
	/*public ArrayList<Pelikula> getPelikulak(){
		ArrayList<Pelikula> pelikulak = new ArrayList<Pelikula>();
		DatuBasea.getDatuBasea().konexioaHasi();
		ResultSet rs = DatuBasea.getDatuBasea().getQuery("select * from Pelikula");
		try {
			while(rs.next()){
				Pelikula p = new Pelikula(rs.getString("izenburua"), rs.getInt("idPelikula"), rs.getInt("prezioa"), rs.getString("egoera"), rs.getString("data"));
				pelikulak.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DatuBasea.getDatuBasea().konexioaItxi();
		return pelikulak;	
	}*/
	
	public Erabiltzailea erabiltzaileaLortu(String pErabiltzailea){
		DatuBasea.getDatuBasea().konexioaHasi();
		ResultSet rs = DatuBasea.getDatuBasea().getQuery("select * from Bazkidea where erabiltzailea='"+ pErabiltzailea +"'");
		Erabiltzailea erabiltzailea = null;
		try {
			if(rs!=null){
				rs.next();
				if(rs.getBoolean("administratzailea")){
					erabiltzailea = new Administratzailea(rs.getInt("idBazkidea"), rs.getString("erabiltzailea"), rs.getString("pasahitza"), rs.getString("izena"), rs.getString("abizena"), rs.getString("helbidea"));
	
				}
				else{
					erabiltzailea = new Bazkidea(rs.getInt("idBazkidea"), rs.getString("erabiltzailea"), rs.getString("pasahitza"), rs.getString("izena"), rs.getString("abizena"), rs.getString("helbidea"));
				}
			}
		} catch (SQLException e) {
			erabiltzailea = null;
		}
		return erabiltzailea;
	}
}
