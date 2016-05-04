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
		DatuBasea.getDatuBasea().setQuery("insert into Pelikula values ('" + pIzenurua + "', '" + pPrezioa + "', " + pEgoera + "', " + pData + "')");
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
		ResultSet rs = DatuBasea.getDatuBasea().getQuery("select * from Pelikula where izenburua='" + pIzenburua + "'");
		try{
			if(rs!=null){
				rs.next();
				p = new Pelikula(rs.getString("izenburua"), rs.getInt("idPelikula"), rs.getInt("prezioa"), rs.getString("egoera"), rs.getString("data"));
			}
		} catch(SQLException e){
			p = null;
		}
		return p;
	}
	
	//Update
	public void bazkideBatenEgoeraAldatu(String pIzena, String pAbizena, boolean pEgoera){
		DatuBasea.getDatuBasea().setQuery("update Bazkidea set egoera='" + pEgoera + "' where izena='" + pIzena + "', abizena='" + pAbizena + "'");
	}
	
	public void pelikulaEgoeraAldatu(String pEgoera, int pIdPelikula){
		DatuBasea.getDatuBasea().setQuery("update Pelikula set egoera='" + pEgoera + "' where idPelikula='" + pIdPelikula + "'");
	}
	
	public void datuPertsonalakAldatu(){
		//Zer aldatu nahi duen behar da
		//DatuBasea.getDatuBasea().setQuery("update Bazkidea set egoera='" + pEgoera + "' where izena='" + pIzena + "', abizena='" + pAbizena + "'");
	}
	
	public void kredituaGehitu(int pKredit){
		//Bazkidearen daturen bat behar da
		//DatuBasea.getDatuBasea().setQuery("update Bazkidea set kreditua='kreditua + " + pKredit + "'");
	}
}
