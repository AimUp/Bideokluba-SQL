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
		switch(pEgo){
			case "KATALOGATU":
				if(alokatutaDago()){
					egoera = Egoera.ALOKATUA.toString();
				}
				break;
			case "EZABATU": 
				if(alokatutaDago()){
					egoera = Egoera.DESKATALOGATU.toString();
				}
				else{
					egoera = "EZABATUA";
					agindua.pelikulaEzabatu(idPelikula);
				}
				break;
			case "ALOKATUA":
				egoera = "ALOKATUA";
				break;
			case "LIBRE":
				egoera = "LIBRE";
				break;
		}
		agindua.pelikulaEgoeraAldatu(egoera, idPelikula);
	}
	
	public boolean alokatutaDago(){
		boolean erantzuna = false;
		if(!egoera.equals(Egoera.LIBRE.toString())){
			erantzuna = true;
		}
		return erantzuna;
	}
	
	public int getPrezioa(){
		return prezioa;
	}
	
	public String[] getInfo(){
		String[] s = {String.valueOf(idPelikula), izena, String.valueOf(prezioa), egoera, data};
		return s;
	}
}