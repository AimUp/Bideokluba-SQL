package db.proiektua;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		DatuBasea konexioa = new DatuBasea();
		
		//konexioa.setQuery("create table pelikula");
		//konexioa.setQuery("INSERT INTO PELIKULA VALUES ('10', 'izena', '10', 'A', '2000-1-1')");
		
		ResultSet erantzuna;
		erantzuna = konexioa.getQuery("select * from pelikula");
		
		String kodea;
		
		try {
			while(erantzuna.next()){
				kodea = erantzuna.getString("kodea");
				System.out.println("KODEA: " + kodea);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
