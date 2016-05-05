package db.proiektua.logic;

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
		agindua.kredituaGehitu(pKreditua);
	}
	
	public void pelikulaAlokatu(String pPelikula){
		Pelikula pelikula = agindua.bilatuPelikula(pPelikula);
		if(pelikula == null){
			//mezua
		}else{
			if(pelikula.alokatutaDago()){
				//mezua
			}else{
				pelikula.egoeraAldatu(Egoera.ALOKATUA.toString());
			}
		}
	}
	
	public void pelikulaItzuli(String pPelikula){
		Pelikula pelikula = agindua.bilatuPelikula(pPelikula);
		pelikula.egoeraAldatu(Egoera.LIBRE.toString());
	}
}

