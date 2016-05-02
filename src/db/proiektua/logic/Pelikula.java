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

	public void altaEman(){
		egoera = "alta";
		agindua.pelikulaEgoeraAldatu(egoera);
	}
	
	public void bajaEman(){
		egoera = "baja";
		agindua.pelikulaEgoeraAldatu(egoera);
	}
	
	public boolean alokatutaDago(){
		boolean erantzuna = true;
		if(egoera == "alta"){
			erantzuna = false;
		}
		return erantzuna;
	}
	
	public void deskatalogatu(){
		egoera = "deskatalogatuta";
		agindua.pelikulaEgoeraAldatu(egoera);
	}
}