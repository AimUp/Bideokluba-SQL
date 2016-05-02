package db.proiektua.logic;

import db.proiektua.db.Aginduak;

public class Administratzailea extends Erabiltzailea{
	
	private Aginduak agindua = new Aginduak();
	
	public Administratzailea(int pIdBazkidea, String pErabiltzailea, String pPasahitza, String pIzena, String pAbizena, String pHelbidea){
		super(pIdBazkidea, pErabiltzailea, pPasahitza, pIzena, pAbizena, pHelbidea);
	}
	
	public void bazkideBerriaSortu(String pPasahitza, String pErabiltzaile, String pIzena, String pAbizena, String pHelbidea, int pKreditua, boolean pEgoera, String pBazNoiztik){
		agindua.createBazkidea(pPasahitza, pErabiltzaile, pIzena, pAbizena, pHelbidea, pKreditua, pEgoera, pBazNoiztik);
	}
	
	public void bazkideBatenEgoeraAldatu(String pErabiltzaile, boolean pEgoera){
		agindua.bazkideBatenEgoeraAldatu(pErabiltzaile, pEgoera);
	}
	
	public void pelikulaBerriBatSartu(String pIzenurua, int pPrezioa, String pEgoera, String pData){
		agindua.pelikulaBerriBatSartu(pIzenurua, pPrezioa, pEgoera, pData);
	}
	
	public void pelikulariBajaEman(String pIzenburua){
		Pelikula pelikula = agindua.bilatuPelikula(pIzenburua);
		pelikula.deskatalogatu();
	}
	
	public Erabiltzailea erabiltzaileaBilatu(String pErab){
		Erabiltzailea e = null;
		//TODO
		return e;
	}

	public Pelikula pelikulaBilatu(String pIzenb){
		Pelikula p = null;
		//TODO
		return p;
	}
}

