package db.proiektua.UI;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import db.proiektua.logic.Bideokluba;
import db.proiektua.logic.EnumAginduak;

public class Leihoa extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private static Leihoa nLeihoa = null;
	private Logging log;
	
	private Leihoa(){
		Bideokluba.getBideokluba().addObserver(this);
		
		setTitle("BIDEOKLUBA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350, 500);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setResizable(true);
	}
	
	public static Leihoa getLeihoa(){
		if(nLeihoa==null){
			nLeihoa = new Leihoa();
		}
		return nLeihoa;
	}
	
	public void logginaKargatu(){
		log = new Logging();
		getContentPane().add(log, BorderLayout.NORTH);
		setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof EnumAginduak){
			EnumAginduak agindua =  (EnumAginduak) arg;
			switch(agindua){
				case ERABILTZAILEOKERRA: log.erabiltzaileOkerra();
					break;
				case PASAHITZOKERRA: log.pasahitzOkerra();
					break;
				case BEZEROAKARGATU: 	getContentPane().removeAll();
										getContentPane().add(new BezeroPanela());
										setVisible(true);
					break;
				case AMINISTRATZAILEAKARGATU:  	getContentPane().removeAll();
												getContentPane().add(new AdminPanela());
												setVisible(true);
					break;
			}
		}
	}
}
