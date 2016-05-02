package db.proiektua.logic;

import db.proiektua.db.Aginduak;

public class Bazkidea extends Erabiltzailea{

	private String bezeroaNoiztik;
	private Aginduak agindua = new Aginduak();
	
	public Bazkidea(int pIdBazkidea, String pErabiltzailea, String pPasahitza, String pIzena, String pAbizena, String pHelbidea){
		super(pIdBazkidea, pErabiltzailea, pPasahitza, pIzena, pAbizena, pHelbidea);
	}
	
	public void datuPertsonalakAldatu(){
		agindua.datuPertsonalakAldatu();
	}
	
	public void kredituaGehitu(int pKreditua){
		agindua.kredituaGehitu(pKreditua);
	}
	
	public void pelikulaAlokatu(String pPelikula){
		Pelikula pelikula = agindua.getPelikula(pPelikula);
		if(pelikula == null){
			//mezua
		}else{
			if(pelikula.alokatutaDago()){
				//mezua
			}else{
			pelikula.bajaEman();
			}
		}
	}
	
	public void pelikulaItzuli(String pPelikula){
		agindua.pelikulaItzuli(pPelikula);
		Pelikula pelikula = agindua.getPelikula(pPelikula);
		pelikula.altaEman();
	}
	
}

