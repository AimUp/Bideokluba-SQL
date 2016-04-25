package db.proiektua.logic;

import java.util.Observable;

public class Bideokluba extends Observable{

	private static Bideokluba nBideokluba = null;
	
	private Bideokluba(){
	}
	
	public static Bideokluba getBideokluba(){
		if(nBideokluba==null){
			nBideokluba = new Bideokluba();
		}
		return nBideokluba;
	}
}
