package db.proiektua.logic;

public class Pelikula {

	private String izena;
	private int idPelikula;
	private int prezioa;
	private String egoera;
	private String data;
	
	public Pelikula(String pIzena, int pIdPelikula, int pPrezioa, String pEgoera, String pData) {
		this.izena = pIzena;
		this.idPelikula = pIdPelikula;
		this.prezioa = pPrezioa;
		this.egoera = pEgoera;
		this.data = pData;
	}

	public String getIzena() {
		return izena;
	}

	public int getIdPelikula() {
		return idPelikula;
	}

	public int getPrezioa() {
		return prezioa;
	}

	public String getEgoera() {
		return egoera;
	}

	public String getData() {
		return data;
	}
}