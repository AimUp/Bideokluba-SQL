package db.proiektua.logic;

import java.util.Observable;

import db.proiektua.db.Aginduak;

public class Bideokluba extends Observable{

	private static Bideokluba nBideokluba = null;
	private Erabiltzailea unekoErabiltzailea;
	private Aginduak aginduak;
	
	private Bideokluba(){
		aginduak = new Aginduak();
	}
	
	public static Bideokluba getBideokluba(){
		if(nBideokluba==null){
			nBideokluba = new Bideokluba();
		}
		return nBideokluba;
	}
	
	public void erabiltzaileaKonprobatu(String pErabiltzailea, char[] pPasahitza){
		boolean erabiltzaileEgokia = false;
		boolean pasahitzEgokia = false;
		Erabiltzailea b = aginduak.erabiltzaileaLortu(pErabiltzailea);
		if(b != null){
			erabiltzaileEgokia = true;
			if(b.pasahitzaKonprobatu(pPasahitza)){
				pasahitzEgokia =  true;
				unekoErabiltzailea = b;
				if(b instanceof Administratzailea){
					setChanged();
					notifyObservers(EnumAginduak.AMINISTRATZAILEAKARGATU);
				}
				else{
					setChanged();
					notifyObservers(EnumAginduak.BEZEROAKARGATU);
				}
			}
		}
		if(!erabiltzaileEgokia){
			setChanged();
			notifyObservers(EnumAginduak.ERABILTZAILEOKERRA);
		}
		else if(!pasahitzEgokia){
			setChanged();
			notifyObservers(EnumAginduak.PASAHITZOKERRA);
		}
	}
}
