package db.proiektua.logic;

import java.util.Observable;

import db.proiektua.UI.Leihoa;

public class Bideokluba extends Observable{

	private static Bideokluba nBideokluba = null;
	
	private Bideokluba(){
		Leihoa.getLeihoa().logginaKargatu();
	}
	
	public static Bideokluba getBideokluba(){
		if(nBideokluba==null){
			nBideokluba = new Bideokluba();
		}
		return nBideokluba;
	}
	
	
	
}
