package db.proiektua.logic;

import db.proiektua.db.Aginduak;

public class Administratzailea extends Erabiltzailea{
	
	private Aginduak agindua = new Aginduak();
	
	public Administratzailea(int pIdBazkidea, String pErabiltzailea, String pPasahitza, String pIzena, String pAbizena, String pHelbidea, int pKreditua, String pBazkideaNoiztik, boolean pEgoera){
		super(pIdBazkidea, pErabiltzailea, pPasahitza, pIzena, pAbizena, pHelbidea, pKreditua, pBazkideaNoiztik, pEgoera);
	}
	
	public void bazkideBerriaSortu(char[] pPasahitza, String pErabiltzaile, String pIzena, String pAbizena, String pHelbidea, int pKreditua, boolean pEgoera, String pBazNoiztik){
		agindua.createBazkidea(pPasahitza, pErabiltzaile, pIzena, pAbizena, pHelbidea, pKreditua, pEgoera, pBazNoiztik);
	}
	
	public void pelikulaBerriBatSartu(String pIzenurua, int pPrezioa, String pData){
		agindua.pelikulaBerriBatSartu(pIzenurua, pPrezioa, Egoera.LIBRE.toString(), pData);
	}
	
	public Erabiltzailea erabiltzaileaBilatu(String pErabiltzailea){
		Erabiltzailea e = agindua.erabiltzaileaLortu(pErabiltzailea);
		return e;
	}

	public Pelikula pelikulaBilatu(String pIzenb){
		Pelikula p = agindua.bilatuPelikula(pIzenb);
		return p;
	}
}

