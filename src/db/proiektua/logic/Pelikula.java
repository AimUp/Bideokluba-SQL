package db.proiektua.logic;

import db.proiektua.db.Aginduak;

public class Pelikula {

	private String izena;
	private int idPelikula;
	private int prezioa;
	private String egoera;
	private String data;
	private Aginduak agindua = new Aginduak();
	
	public Pelikula(String pIzena, int pIdPelikula, int pPrezioa, String pEgoera, String pData) {
		this.izena = pIzena;
		this.idPelikula = pIdPelikula;
		this.prezioa = pPrezioa;
		this.egoera = pEgoera;
		this.data = pData;
	}

	public void egoeraAldatu(String pEgo){
		egoera = pEgo;
		agindua.pelikulaEgoeraAldatu(egoera, idPelikula);
	}
	
	public boolean alokatutaDago(){
		boolean erantzuna = true;
		if(egoera == "alta"){
			erantzuna = false;
		}
		return erantzuna;
	}
	
	public String[] getInfo(){
		String[] s = {String.valueOf(idPelikula), izena, String.valueOf(prezioa), egoera, data};
		return s;
	}
}