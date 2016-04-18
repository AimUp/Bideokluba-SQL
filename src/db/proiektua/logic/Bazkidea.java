package db.proiektua.logic;

public class Bazkidea {

	private int idBazkidea;
	private String pasahitza;
	private String izena;
	private String abizena;
	private String helbidea;
	private int kreditua;
	private String egoera;
	private int noiztikBezero;
	
	public Bazkidea(int pIdBazkidea, String pPasahitza, String pIzena, String pAbizena, String pHelbidea, int pKreditua, String pEgoera, int pNoiztikBezero){
		
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

	public void setIdBazkidea(int idBazkidea) {
		this.idBazkidea = idBazkidea;
	}

	public String getPasahitza() {
		return pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getAbizena() {
		return abizena;
	}

	public void setAbizena(String abizena) {
		this.abizena = abizena;
	}

	public String getHelbidea() {
		return helbidea;
	}

	public void setHelbidea(String helbidea) {
		this.helbidea = helbidea;
	}

	public int getKreditua() {
		return kreditua;
	}

	public void setKreditua(int pKreditua) {
		this.kreditua = pKreditua + kreditua;
	}

	public String getEgoera() {
		return egoera;
	}

	/*public void setEgoera(String egoera) {
		this.egoera = egoera;
	}*/

	public int getNoiztikBezero() {
		return noiztikBezero;
	}

	/*public void setNoiztikBezero(int noiztikBezero) {
		this.noiztikBezero = noiztikBezero;
	}*/
	
}
