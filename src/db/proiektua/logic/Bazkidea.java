package db.proiektua.logic;

public class Bazkidea {

	private int idBazkidea;
	private String pasahitza;
	private String izena;
	private String abizena;
	private String helbidea;
	private int kreditua;
	private boolean egoera;
	private String noiztikBezero;
	
	public Bazkidea(int pIdBazkidea, String pPasahitza, String pIzena, String pAbizena, String pHelbidea, int pKreditua, boolean pEgoera, String pNoiztikBezero){
		this.idBazkidea = pIdBazkidea;
		this.pasahitza = pPasahitza;
		this.izena = pIzena;
		this.abizena = pAbizena;
		this.helbidea = pHelbidea;
		this.kreditua = pKreditua;
		this.egoera = pEgoera;
		this.noiztikBezero = pNoiztikBezero;
	}
	
	public Bazkidea(int pIdBazkidea){
		this.idBazkidea = pIdBazkidea;
	}

	public int getIdBazkidea() {
		return idBazkidea;
	}

	public String getPasahitza() {
		return pasahitza;
	}

	public String getIzena() {
		return izena;
	}

	public String getAbizena() {
		return abizena;
	}

	public String getHelbidea() {
		return helbidea;
	}
	
	public int getKreditua() {
		return kreditua;
	}

	public boolean getEgoera() {
		return egoera;
	}

	public String getNoiztikBezero() {
		return noiztikBezero;
	}
}
