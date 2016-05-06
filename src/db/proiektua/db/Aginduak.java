package db.proiektua.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.proiektua.logic.Administratzailea;
import db.proiektua.logic.Bazkidea;
import db.proiektua.logic.Erabiltzailea;
import db.proiektua.logic.Pelikula;

public class Aginduak{

	public Aginduak(){}
	
	//CREATE
	public void createBazkidea(char[] pPasahitza, String pErabiltzaile, int pKreditua, int pEgoera, String pBazNoiztik){
		DatuBasea.getDatuBasea().konexioaHasi();
		String pass= new String(pPasahitza);
		System.out.println(pass);
		DatuBasea.getDatuBasea().setQuery("insert into Bazkidea (erabiltzailea,pasahitza,kreditua,egoera,administratzailea,bazkideaNoiztik) "
										+ "values ('"+pErabiltzaile+"','"+pass+"','"+pKreditua+"','"+pEgoera+"', '0' ,'"+pBazNoiztik+"')");
		DatuBasea.getDatuBasea().konexioaItxi();
	}
	
	public void pelikulaBerriBatSartu(String pIzenurua, int pPrezioa, String pEgoera, String pData){
		DatuBasea.getDatuBasea().konexioaHasi();
		DatuBasea.getDatuBasea().setQuery("insert into Pelikula (izenburua,prezioa,egoera,data) values ('" + pIzenurua + "', '" + pPrezioa + "', '" + pEgoera + "', '" + pData + "')");
		DatuBasea.getDatuBasea().konexioaItxi();
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
		DatuBasea.getDatuBasea().konexioaItxi();
		return erabiltzailea;
	}
	
	public Pelikula bilatuPelikula(String pIzenburua){
		DatuBasea.getDatuBasea().konexioaHasi();
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
		DatuBasea.getDatuBasea().konexioaItxi();
		return p;
	}
	
	public ArrayList<Pelikula> estreinuenZerrendaLortu(){
		DatuBasea.getDatuBasea().konexioaHasi();
		ArrayList<Pelikula> pl = new ArrayList<Pelikula>();
		ResultSet rs = DatuBasea.getDatuBasea().getQuery("select * from Pelikula where data > DATE_ADD(CURDATE(), INTERVAL -30 DAY)");
		try {
			if(rs!=null){
				while(rs.next()){
					if(rs!=null){
						Pelikula p = new Pelikula(rs.getString("izenburua"), rs.getInt("idPelikula"), rs.getInt("prezioa"), rs.getString("egoera"), rs.getString("data"));
						pl.add(p);
					}
				}
			}
		} catch (SQLException e) {
			pl=null;
		}
		DatuBasea.getDatuBasea().konexioaItxi();
		return pl;
	}
	
	public ArrayList<Pelikula> alokatuenZerrendaLortu(int idBazkide){
		DatuBasea.getDatuBasea().konexioaHasi();
		ArrayList<Pelikula>lista=new ArrayList<Pelikula>();
		Pelikula p=null;
		ResultSet r = DatuBasea.getDatuBasea().getQuery("select Pelikula_idPelikula from Bazkidea_has_Pelikula1 where Bazkidea_idBazkidea='"+idBazkide+"'");
		
		try {
			while(r.next()){
				if(r!=null){
					int idPeli = r.getInt("Pelikula_idPelikula");
					ResultSet rs = DatuBasea.getDatuBasea().getQuery("select * from Pelikula where idPelikula='" + String.valueOf(idPeli) + "'");
					try {
						while(rs.next()){
							p = new Pelikula(rs.getString("izenburua"), rs.getInt("idPelikula"), rs.getInt("prezioa"), rs.getString("egoera"), rs.getString("data"));
							lista.add(p);
						}
					}
					catch (SQLException e) {
						p=null;
					}
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		DatuBasea.getDatuBasea().konexioaItxi();
		return lista;
	}
	
	//Update
	public void bazkideBatenEgoeraAldatu(String erabiltzailea, boolean pEgoera){
		DatuBasea.getDatuBasea().konexioaHasi();
		int ego = 0;
		if(pEgoera) ego = 1;
		DatuBasea.getDatuBasea().setQuery("update Bazkidea set egoera='" + ego + "' where erabiltzailea='" + erabiltzailea + "'");
		DatuBasea.getDatuBasea().konexioaItxi();
	}
	
	public void pelikulaEgoeraAldatu(String pEgoera, int pIdPelikula){
		DatuBasea.getDatuBasea().konexioaHasi();
		DatuBasea.getDatuBasea().setQuery("update Pelikula set egoera='" + pEgoera + "' where idPelikula='" + pIdPelikula + "'");
		DatuBasea.getDatuBasea().konexioaItxi();
	}
	
	public void datuPertsonalakAldatu(String erabiltzailea,String[] aldatu){
		DatuBasea.getDatuBasea().konexioaHasi();
		DatuBasea.getDatuBasea().setQuery("update Bazkidea set izena='"+aldatu[1]+"', abizena='"+aldatu[2]+"', helbidea='"+aldatu[3]+"', pasahitza='"+aldatu[4]+"', erabiltzailea='"+aldatu[0]+"' where erabiltzailea='" + erabiltzailea + "'");
		DatuBasea.getDatuBasea().konexioaItxi();
	}
	
	public void kredituaEguneratu(String erabiltzaile, int pKredit){
		DatuBasea.getDatuBasea().konexioaHasi();
		DatuBasea.getDatuBasea().setQuery("update Bazkidea set kreditua='"+pKredit+"' where erabiltzailea='"+erabiltzaile+"'");
		DatuBasea.getDatuBasea().konexioaItxi();
	}

	public void pelikulaAlokatu(int idErabil, int pelikulaZenb){
		DatuBasea.getDatuBasea().konexioaHasi();
		DatuBasea.getDatuBasea().setQuery("insert Bazkidea_has_Pelikula1 VALUES ('"+ idErabil +"', '"+ pelikulaZenb +"')");
		DatuBasea.getDatuBasea().konexioaItxi();
	}
	
	public void pelikulaItzuli(int idErabil, int pelikulaZenb){
		DatuBasea.getDatuBasea().konexioaHasi();
		DatuBasea.getDatuBasea().setQuery("delete FROM Bazkidea_has_Pelikula1 WHERE Bazkidea_idBazkidea='"+idErabil+"' and Pelikula_idPelikula ='"+pelikulaZenb+"'");
		DatuBasea.getDatuBasea().konexioaItxi();
	}
	
	public void pelikulaEzabatu(int pelikulaZenb){
		DatuBasea.getDatuBasea().konexioaHasi();
		DatuBasea.getDatuBasea().setQuery("delete FROM Pelikula WHERE idPelikula ='"+pelikulaZenb+"'");
		DatuBasea.getDatuBasea().konexioaItxi();
	}
}
