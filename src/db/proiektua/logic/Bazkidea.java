package db.proiektua.logic;

import java.util.ArrayList;

import db.proiektua.db.Aginduak;

public class Bazkidea extends Erabiltzailea{

	private Aginduak agindua = new Aginduak();
	
	public Bazkidea(int pIdBazkidea, String pErabiltzailea, String pPasahitza, String pIzena, String pAbizena, String pHelbidea, int pKreditua, String pBazkideaNoiztik, boolean pEgoera){
		super(pIdBazkidea, pErabiltzailea, pPasahitza, pIzena, pAbizena, pHelbidea, pKreditua, pBazkideaNoiztik, pEgoera);
	}
	
	public void datuPertsonalakAldatu(String[] aldatu){
		datuakAldatu(aldatu);
	}
	
	public void kredituaGehitu(int pKreditua){
		kredituaAldatu(pKreditua);
	}
	
	public boolean pelikulaAlokatu(Pelikula pPelikula){
		boolean alokatuAhalDa = true;
		if(pPelikula.alokatutaDago()){
				alokatuAhalDa = false;
		}else{
			agindua.pelikulaAlokatu(getId(), Integer.valueOf(pPelikula.getInfo()[0]));
			pPelikula.egoeraAldatu(Egoera.ALOKATUA.toString());
		}
		return alokatuAhalDa;
	}
	
	public void pelikulaItzuli(Pelikula pPelikula){
		agindua.pelikulaItzuli(getErabiltzailea(), Integer.valueOf(pPelikula.getInfo()[0]));
		pPelikula.egoeraAldatu(Egoera.LIBRE.toString());
	}
	
	public ArrayList<Pelikula> alokatuakLortu(){
		//TODO
	}
}

