package db.proiektua.logic;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.proiektua.db.DatuBasea;

public class Main {

	public static void main(String[] args) {
		DatuBasea konexioa = new DatuBasea();
		
		konexioa.setQuery("INSERT INTO Pelikula VALUES ('4', 'DD', '2', 'E', '2000-1-1')");
		
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
