package db.proiektua.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

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
	//konexioa.setQuery("INSERT INTO Pelikula VALUES ('5', 'DD', '2', 'E', '2000-1-1')");

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
