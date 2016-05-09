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
		String pass= new String(pPasahitza);
		ArrayList<Object> param = new ArrayList<>();
		param.add(pErabiltzaile);
		param.add(pass);		
		param.add(pKreditua);
		param.add(pEgoera);
		param.add(pBazNoiztik);
		
		
		DatuBasea.getDatuBasea().setQuery("insert into Bazkidea (erabiltzailea,pasahitza,kreditua,egoera,bazkideaNoiztik,administratzailea) "
										+ "values (?,?,?,?,?,0)", param);
		DatuBasea.getDatuBasea().konexioaItxi();
	}
	
	public void pelikulaBerriBatSartu(String pIzenburua, int pPrezioa, String pEgoera, String pData){
		ArrayList<Object> param =new ArrayList<>();
		param.add(pIzenburua);
		param.add(pPrezioa);
		param.add(pEgoera);
		param.add(pData);
		DatuBasea.getDatuBasea().setQuery("insert into Pelikula (izenburua,prezioa,egoera,data) values (?,?,?,?)", param);
		DatuBasea.getDatuBasea().konexioaItxi();
	}
	
	//GET
	public Erabiltzailea erabiltzaileaLortu(String pErabiltzailea){
		ArrayList<Object> param = new ArrayList<>();
		param.add(pErabiltzailea);
		ResultSet rs = DatuBasea.getDatuBasea().getQuery("select * from Bazkidea where erabiltzailea = ?",param);
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

		/*ResultSet rs = DatuBasea.getDatuBasea().getQuery("select * from Bazkidea where erabiltzailea='"+ pErabiltzailea +"'");
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
			}*/
		} catch (SQLException e) {
			erabiltzailea = null;
		}
		DatuBasea.getDatuBasea().konexioaItxi();
		return erabiltzailea;
	}
	
	public Pelikula bilatuPelikula(String pIzenburua){
		Pelikula p=null;
		ArrayList<Object> param = new ArrayList<>();
		param.add(pIzenburua);
		ResultSet rs = DatuBasea.getDatuBasea().getQuery("select * from Pelikula where izenburua=?", param);
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
		ArrayList<Object> param = new ArrayList<>();
		//param.add("data");
		ArrayList<Pelikula> pl = new ArrayList<Pelikula>();
		ResultSet rs = DatuBasea.getDatuBasea().getQuery("select * from Pelikula where data > DATE_ADD(CURDATE(), INTERVAL -30 DAY)", param);
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
			e.printStackTrace();
			pl=null;
		}
		DatuBasea.getDatuBasea().konexioaItxi();
		return pl;
	}
	
	public ArrayList<Pelikula> alokatuenZerrendaLortu(int idBazkide){
		ArrayList<Object> param = new ArrayList<>();
		param.add(idBazkide);
		ArrayList<Pelikula>lista=new ArrayList<Pelikula>();
		Pelikula p=null;
		ResultSet r = DatuBasea.getDatuBasea().getQuery("select Pelikula_idPelikula from Bazkidea_has_Pelikula1 where Bazkidea_idBazkidea=?", param);
		
		try {
			while(r.next()){
				if(r!=null){
					int idPeli = r.getInt("Pelikula_idPelikula");
					param.clear();
					param.add(idPeli);
					ResultSet rs = DatuBasea.getDatuBasea().getQuery("select * from Pelikula where idPelikula=?", param);
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
		int ego = 0;
		if(pEgoera) ego = 1;
		
		ArrayList<Object> param = new ArrayList<>();
		param.add(ego);
		param.add(pEgoera);
		
		DatuBasea.getDatuBasea().setQuery("update Bazkidea set egoera=? where erabiltzailea=?", param);
		DatuBasea.getDatuBasea().konexioaItxi();
	}
	
	public void pelikulaEgoeraAldatu(String pEgoera, int pIdPelikula){
		ArrayList<Object> param = new ArrayList<>();
		param.add(pEgoera);
		param.add(pIdPelikula);
		DatuBasea.getDatuBasea().setQuery("update Pelikula set egoera=? where idPelikula=?", param);
		DatuBasea.getDatuBasea().konexioaItxi();
	}
	
	public void datuPertsonalakAldatu(String erabiltzailea,String[] aldatu){
		ArrayList<Object> param = new ArrayList<>();
		param.add(aldatu[1]);
		param.add(aldatu[2]);
		param.add(aldatu[3]);
		param.add(aldatu[4]);
		param.add(aldatu[0]);
		param.add(erabiltzailea);
		DatuBasea.getDatuBasea().setQuery("update Bazkidea set izena=?, abizena=?, helbidea=?, pasahitza=?, erabiltzailea=? where erabiltzailea=?", param);
		DatuBasea.getDatuBasea().konexioaItxi();
	}
	
	public void kredituaEguneratu(String erabiltzaile, int pKredit){
		ArrayList<Object> param = new ArrayList<>();
		param.add(pKredit);
		param.add(erabiltzaile);
		DatuBasea.getDatuBasea().setQuery("update Bazkidea set kreditua=? where erabiltzailea=?", param);
		DatuBasea.getDatuBasea().konexioaItxi();
	}

	public void pelikulaAlokatu(int idErabil, int pelikulaZenb){
		ArrayList<Object> param = new ArrayList<>();
		param.add(idErabil);
		param.add(pelikulaZenb);
		DatuBasea.getDatuBasea().setQuery("insert Bazkidea_has_Pelikula1 VALUES (?, ?)", param);
		DatuBasea.getDatuBasea().konexioaItxi();
	}
	
	public void pelikulaItzuli(int idErabil, int pelikulaZenb){
		ArrayList<Object> param = new ArrayList<>();
		param.add(idErabil);
		param.add(pelikulaZenb);
		DatuBasea.getDatuBasea().setQuery("delete FROM Bazkidea_has_Pelikula1 WHERE Bazkidea_idBazkidea=? and Pelikula_idPelikula =?", param);
		DatuBasea.getDatuBasea().konexioaItxi();
	}
	
	public void pelikulaEzabatu(int pelikulaZenb){
		ArrayList<Object> param = new ArrayList<>();
		param.add(pelikulaZenb);
		DatuBasea.getDatuBasea().setQuery("delete FROM Pelikula WHERE idPelikula =?", param);
		DatuBasea.getDatuBasea().konexioaItxi();
	}
}
