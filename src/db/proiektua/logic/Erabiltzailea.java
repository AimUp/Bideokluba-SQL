package db.proiektua.logic;

import java.util.Observable;

import db.proiektua.db.Aginduak;

public abstract class Erabiltzailea extends Observable{

	private int idBazkidea;
	private String erabiltzailea;
	private String pasahitza;
	private String izena;
	private String abizena;
	private String helbidea;
	private int kreditua;
	private String bazkideaNoiztik;
	private boolean egoera;
	private Aginduak agindua = new Aginduak();
	
	public Erabiltzailea(int pIdBazkidea, String pErabiltzailea, String pPasahitza, String pIzena, String pAbizena, String pHelbidea, int pKreditua, String pBazkideaNoiztik, boolean pEgoera){
		idBazkidea = pIdBazkidea;
		erabiltzailea = pErabiltzailea;
		pasahitza = pPasahitza;
		izena = pIzena;
		abizena = pAbizena;
		helbidea = pHelbidea;
		kreditua = pKreditua;
		bazkideaNoiztik = pBazkideaNoiztik;
		egoera = pEgoera;
	}
	
	public boolean pasahitzaKonprobatu(char[] pPasahitza){
		boolean berdina = true;
		if(pasahitza.length() == pPasahitza.length){
			for(int x=0; x<pasahitza.length(); x++){
				if(pasahitza.toCharArray()[x] != pPasahitza[x]){
					berdina = false;
				}
			}
		}
		else berdina = false;
		return berdina;
	}
	
	public String[] getInfo(){
		String ego;
		if(egoera) ego = "Alta";
		else ego = "Baja";
		String[] s = {String.valueOf(idBazkidea), erabiltzailea, izena, abizena, helbidea, String.valueOf(kreditua), bazkideaNoiztik, ego};
		return s;
	}
	
	public void egoeraAldatu(){
		egoera = !egoera;
		agindua.bazkideBatenEgoeraAldatu(erabiltzailea, egoera);
	}

}
