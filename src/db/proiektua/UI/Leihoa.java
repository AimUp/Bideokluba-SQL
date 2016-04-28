package db.proiektua.UI;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
		
		log = new Logging();
		panelaAldatu(log);
	}
	
	public static Leihoa getLeihoa(){
		if(nLeihoa==null){
			nLeihoa = new Leihoa();
		}
		return nLeihoa;
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
				case BAZKIDEAKARGATU: 	panelaAldatu(new BazkidePanela());
					break;
				case AMINISTRATZAILEAKARGATU:  	panelaAldatu(new AdminPanela());
					break;
			}
		}
	}
	
	public void panelaAldatu(JPanel panela){
		getContentPane().removeAll();
		getContentPane().add(panela, BorderLayout.NORTH);
		repaint();
		setVisible(true);
	}
}
