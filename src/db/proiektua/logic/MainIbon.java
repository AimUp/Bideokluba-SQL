package db.proiektua.logic;

import java.util.ArrayList;

import db.proiektua.db.DBAccess;


public class MainIbon {

	public static void main(String[] args) {
		DBAccess db = new DBAccess();
		
		//BAZKIDEA -->
		
		//BAZKIDEAK SORTU
		
		Bazkidea bazkide1 = new Bazkidea( 0, "ibon", "lopez", "db", "sanmames", 90, "alta", 2016);
		db.createBazkidea(bazkide1);
		Bazkidea bazkide2 = new Bazkidea( 0, "jon", "irazabal", "db", "sanmames", 90, "alta", 2016);
		db.createBazkidea(bazkide2);
		Bazkidea bazkide3 = new Bazkidea( 0, "aimar", "ugarte", "db", "sanmames", 90, "alta", 2016);
		db.createBazkidea(bazkide3);
		Bazkidea bazkide4 = new Bazkidea( 0, "hector", "vadillo", "db", "sanmames", 90, "alta", 2016);
		db.createBazkidea(bazkide4);
		Bazkidea bazkide5 = new Bazkidea( 0, "adrian", "santana", "db", "sanmames", 90, "alta", 2016);
		db.createBazkidea(bazkide5);
		
		//BAZKIDEAK LORTU
		
		ArrayList<Bazkidea> bazkideak = db.getBazkideak();
		
		//BAZKIDEEN ID-A INPRIMATU
		
		for(Bazkidea b : bazkideak){
			System.out.println(b.getIdBazkidea());
		}
		
		//PELIKULA -->
		
		//PELIKULAK SORTU
		
		Pelikula pelikula1 = new Pelikula("superman", 0, 10, "libre", 2016);
		db.createPelikula(pelikula1);
		
		//PELIKULAK LORTU
		
		ArrayList<Pelikula> pelikulak = db.getPelikulak();
		
		//PELIKULEN ID-A INPRIMATU
		
		for(Pelikula p : pelikulak){
			System.out.println(p.getIdPelikula());
		}
	}

}
