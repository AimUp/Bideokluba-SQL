package db.proiektua.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import db.proiektua.logic.Bazkidea;
import db.proiektua.logic.Pelikula;

public class Aginduak implements Observer{

	private ResultSet erantzuna;
	
	public Aginduak(){
		erantzuna = null;
	}
	
	public ArrayList<String> pelikulaKodeaLortu(){
		ArrayList<String> pelikulak = new ArrayList<String>();
		try {
			DatuBasea.getDatuBasea().konexioaHasi();
			erantzuna = DatuBasea.getDatuBasea().getQuery("select * from Bazkidea");
			while(erantzuna.next()){
				pelikulak.add(erantzuna.getString("idBazkidea"));
			}
			DatuBasea.getDatuBasea().konexioaItxi();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pelikulak;
	}
	
	
	//CREATE
	public void createBazkidea(Bazkidea b){
		DatuBasea.getDatuBasea().konexioaHasi();
		DatuBasea.getDatuBasea().setQuery("insert into bazkidea values ('"+b.getIdBazkidea()+"','"+b.getPasahitza()+"', '"+b.getIzena()+"', '"+b.getAbizena()+"', '"+b.getHelbidea()+"', '"+b.getKreditua()+"', '"+b.getEgoera()+"', '"+b.getNoiztikBezero()+"')");
		DatuBasea.getDatuBasea().konexioaItxi();
	}
	
	public void createPelikula(Pelikula p){
		DatuBasea.getDatuBasea().konexioaHasi();
		DatuBasea.getDatuBasea().setQuery("insert into pelikula values ('"+p.getIzena()+"', '"+p.getIdPelikula()+"', '"+p.getPrezioa()+"', '"+p.getEgoera()+"', '"+p.getData()+"')");
		DatuBasea.getDatuBasea().konexioaHasi();
	}
	
	//GET
	public ArrayList<Bazkidea> getBazkideak(){
		ArrayList<Bazkidea> bazkideak = new ArrayList<Bazkidea>();
		DatuBasea.getDatuBasea().konexioaHasi();
			ResultSet rs = DatuBasea.getDatuBasea().getQuery("select * from Bazkidea");
			try {
				while(rs.next()){
					Bazkidea b = new Bazkidea(rs.getInt("idBazkidea"), rs.getString("pasahitza"), rs.getString("izena"), rs.getString("abizena"), rs.getString("helbidea"), rs.getInt("kreditua"), rs.getBoolean("egoera"), rs.getString("noiztikBezero"));
					bazkideak.add(b);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		DatuBasea.getDatuBasea().konexioaItxi();
		return bazkideak;
	}
	
	public ArrayList<Pelikula> getPelikulak(){
		ArrayList<Pelikula> pelikulak = new ArrayList<Pelikula>();
		DatuBasea.getDatuBasea().konexioaHasi();
		ResultSet rs = DatuBasea.getDatuBasea().getQuery("select * from Pelikula");
		try {
			while(rs.next()){
				Pelikula p = new Pelikula(rs.getString("izenburua"), rs.getInt("idPelikula"), rs.getInt("prezioa"), rs.getString("egoera"), rs.getString("data"));
				pelikulak.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DatuBasea.getDatuBasea().konexioaItxi();
		return pelikulak;	
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}
