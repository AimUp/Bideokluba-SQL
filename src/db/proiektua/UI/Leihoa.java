package db.proiektua.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import db.proiektua.logic.Administratzailea;
import db.proiektua.logic.Bazkidea;
import db.proiektua.logic.Bideokluba;
import db.proiektua.logic.EnumAginduak;
import db.proiektua.logic.Erabiltzailea;

public class Leihoa extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private static Leihoa nLeihoa = null;
	private Logging log;
	private JMenuBar menuBar;
	
	private Leihoa(){
		Bideokluba.getBideokluba().addObserver(this);
		
		setTitle("BIDEOKLUBA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350, 510);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setResizable(true);
		setBackground(new Color(238, 238, 238));
		
		menuBar = new JMenuBar();
		menuBar.setBorder(null);
		menuBar.setBackground(new Color(238, 238, 238));
		JMenu menua = new JMenu("MENUA");
		menua.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
				Erabiltzailea era = Bideokluba.getBideokluba().getUnekoErabiltzailea();
				if(era instanceof Administratzailea){
					panelaAldatu(new AdminPanela());
				}
				else if(era instanceof Bazkidea){
					panelaAldatu(new BazkidePanela());
				}
				else if(era == null){
					panelaAldatu(new Logging());
				}
			}
			@Override
			public void menuDeselected(MenuEvent e){}
			@Override
			public void menuCanceled(MenuEvent e){}
		});
		menuBar.add(menua);
		
		JMenu itxi = new JMenu("SAIOA ITXI");
		itxi.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
				panelaAldatu(new Logging());
			}
			@Override
			public void menuDeselected(MenuEvent e){}
			@Override
			public void menuCanceled(MenuEvent e){}
		});
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(itxi);
		
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
		setJMenuBar(null);
		if(!(panela instanceof Logging) && !(panela instanceof AdminPanela) && !(panela instanceof BazkidePanela)){
			setJMenuBar(menuBar);
		}
		getContentPane().add(panela, BorderLayout.NORTH);
		repaint();
		setVisible(true);
	}
}
