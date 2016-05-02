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
	public void createBazkidea(String pPasahitza, String pErabiltzaile, String pIzena, String pAbizena, String pHelbidea, int pKreditua, boolean pEgoera, String pBazNoiztik){
		DatuBasea.getDatuBasea().getQuery("insert into Bazkidea values ('"+pPasahitza+"', '"+pIzena+"', '"+pAbizena+"', '"+pHelbidea+"', '"+pKreditua+"', '"+pEgoera+"', '"+pBazNoiztik+"')");
	}
	
	public void pelikulaBerriBatSartu(String pIzenurua, int pPrezioa, String pEgoera, String pData){
		//TODO
	}
	
	//GET
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
	
	public Pelikula bilatuPelikula(String pIzenburua){
		Pelikula p=null;
		return p;
	}
	
	//ALTER
	public void bazkideBatenEgoeraAldatu(String pErabiltzaile, boolean pEgoera){
		//TODO
	}
	
	public void pelikulaEgoeraAldatu(String pEgoera){
		//TODO
	}
	
	public void datuPertsonalakAldatu(){
		//TODO
	}
	
	public void kredituaGehitu(int pKredit){
		//TODO
	}
}
