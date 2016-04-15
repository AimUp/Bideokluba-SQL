package db.proiektua.logic;

import java.util.ArrayList;

import db.proiektua.db.Aginduak;
import db.proiektua.db.DatuBasea;

public class Main {

	public static void main(String[] args) {

		Aginduak agindu = new Aginduak();
		
		ArrayList<String> erantzuna = null;
		erantzuna = agindu.pelikulaKodeaLortu();

		for(int x=0; x<erantzuna.size(); x++){
			System.out.println("KODEA: " + erantzuna.get(x));
		}
	}

}
