package db.proiektua.logic;

public class Pelikula {

	private String izena;
	private int idPelikula;
	private int prezioa;
	private String egoera;
	private int data;
	
	public Pelikula(String pIzena, int pIdPelikula, int pPrezioa, String pEgoera, int pData) {
		this.izena = pIzena;
		this.idPelikula = pIdPelikula;
		this.prezioa = pPrezioa;
		this.egoera = pEgoera;
		this.data = pData;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public int getIdPelikula() {
		return idPelikula;
	}

	public void setIdPelikula(int idPelikula) {
		this.idPelikula = idPelikula;
	}

	public int getPrezioa() {
		return prezioa;
	}

	public void setPrezioa(int prezioa) {
		this.prezioa = prezioa;
	}

	public String getEgoera() {
		return egoera;
	}

	public void setEgoera(String egoera) {
		this.egoera = egoera;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}
	
}