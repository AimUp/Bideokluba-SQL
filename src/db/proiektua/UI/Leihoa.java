package db.proiektua.UI;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import db.proiektua.logic.Bideokluba;

public class Leihoa extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private static Leihoa nLeihoa = null;
	
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
		getContentPane().add(new Logging(), BorderLayout.NORTH);
		setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}
