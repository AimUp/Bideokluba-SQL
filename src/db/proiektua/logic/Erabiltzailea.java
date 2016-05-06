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
	
	protected void datuakAldatu(String[] aldatu){
		String erabiltzaileZaharra = erabiltzailea;
		if(aldatu[0].length()>0) erabiltzailea = aldatu[0];
		if(aldatu[1].length()>0) izena = aldatu[1];
		if(aldatu[2].length()>0) abizena = aldatu[2];
		if(aldatu[3].length()>0) helbidea = aldatu[3];
		if(aldatu[4].length()>0) pasahitza = aldatu[4];	
		agindua.datuPertsonalakAldatu(erabiltzaileZaharra,aldatu);
	}
	
	protected void kredituaAldatu(int pKred){
		kreditua = kreditua + pKred;
		agindua.kredituaEguneratu(erabiltzailea, kreditua);
	}
	
	public int getKreditua(){
		return kreditua;
	}
	
	public int getId(){
		return idBazkidea;
	}
	
	protected void kredituaKendu(int pKred){
		kreditua = kreditua - pKred;
		agindua.kredituaEguneratu(erabiltzailea, kreditua);
	}
	
	public Pelikula pelikulaBilatu(String pIzenb){
		Pelikula p = agindua.bilatuPelikula(pIzenb);
		return p;
	}
}
