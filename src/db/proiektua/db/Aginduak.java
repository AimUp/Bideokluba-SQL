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
	public void createBazkidea(char[] pPasahitza, String pErabiltzaile, String pIzena, String pAbizena, String pHelbidea, int pKreditua, int pEgoera, String pBazNoiztik){
		String pass= new String(pPasahitza);
		System.out.println(pass);
		DatuBasea.getDatuBasea().setQuery("insert into Bazkidea (erabiltzailea,pasahitza,izena,abizena,helbidea,kreditua,egoera,administratzailea,bazkideaNoiztik) "
										+ "values ('"+pErabiltzaile+"','"+pass+"','"+pIzena+"','"+pAbizena+"','"+pHelbidea+"','"+pKreditua+"','"+pEgoera+"', '0' ,'"+pBazNoiztik+"')");
	}
	
	public void pelikulaBerriBatSartu(String pIzenurua, int pPrezioa, String pEgoera, String pData){
		DatuBasea.getDatuBasea().setQuery("insert into Pelikula (izenburua,prezioa,egoera,data) values ('" + pIzenurua + "', '" + pPrezioa + "', '" + pEgoera + "', '" + pData + "')");
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
					erabiltzailea = new Administratzailea(rs.getInt("idBazkidea"), rs.getString("erabiltzailea"), rs.getString("pasahitza"), rs.getString("izena"), rs.getString("abizena"), rs.getString("helbidea"), rs.getInt("Kreditua"), rs.getString("BazkideaNoiztik"), rs.getBoolean("egoera"));
				}
				else{
					erabiltzailea = new Bazkidea(rs.getInt("idBazkidea"), rs.getString("erabiltzailea"), rs.getString("pasahitza"), rs.getString("izena"), rs.getString("abizena"), rs.getString("helbidea"), rs.getInt("Kreditua"), rs.getString("BazkideaNoiztik"),rs.getBoolean("egoera"));
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
	public void bazkideBatenEgoeraAldatu(String erabiltzailea, boolean pEgoera){
		int ego = 0;
		if(pEgoera) ego = 1;
		DatuBasea.getDatuBasea().setQuery("update Bazkidea set egoera='" + ego + "' where erabiltzailea='" + erabiltzailea + "'");
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
