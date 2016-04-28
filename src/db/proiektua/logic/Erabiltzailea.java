package db.proiektua.logic;

import java.util.Observable;

public abstract class Erabiltzailea extends Observable{

	private int idBazkidea;
	private String erabiltzailea;
	private String pasahitza;
	private String izena;
	private String abizena;
	private String helbidea;
	private int kreditua;
	private boolean egoera;
	
	public Erabiltzailea(int pIdBazkidea, String pErabiltzailea, String pPasahitza, String pIzena, String pAbizena, String pHelbidea){
		idBazkidea = pIdBazkidea;
		erabiltzailea = pErabiltzailea;
		pasahitza = pPasahitza;
		izena = pIzena;
		abizena = pAbizena;
		helbidea = pHelbidea;
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

}
