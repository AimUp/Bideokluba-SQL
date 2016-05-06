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
	
	public int pelikulaAlokatu(Pelikula pPelikula){
		int e = 0; //0: Ondo, -1: Krediturik ez, -2: Alokatuta dagoeneko
		if(!pPelikula.alokatutaDago()){
			if(pPelikula.getPrezioa()<getKreditua()){
				agindua.pelikulaAlokatu(getId(), Integer.valueOf(pPelikula.getInfo()[0]));
				kredituaKendu(pPelikula.getPrezioa());
				pPelikula.egoeraAldatu(Egoera.ALOKATUA.toString());
			}
			else e = -1;
		}
		else e = -2;
		return e;
	}
	
	public void pelikulaItzuli(Pelikula pPelikula){
		agindua.pelikulaItzuli(getId(), Integer.valueOf(pPelikula.getInfo()[0]));
		pPelikula.egoeraAldatu(Egoera.LIBRE.toString());
	}
	
	public ArrayList<Pelikula> alokatuakLortu(){
		return agindua.alokatuenZerrendaLortu(getId());
	}
}

