package db.proiektua.UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import db.proiektua.logic.Administratzailea;
import db.proiektua.logic.Bideokluba;
import db.proiektua.logic.Erabiltzailea;
import sun.tools.jar.resources.jar;

public class BazkideEgoeraPanela extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextField izena;

	public BazkideEgoeraPanela(){
		setLayout(new BorderLayout());
		
		JPanel bilatuPanela = new JPanel(new SpringLayout());
		JLabel labela = new JLabel("BAJA EMAN NAHI DEN ERABILTZAILEA BILATU", SwingConstants.CENTER);
		izena = new  JTextField();
		izena.addActionListener(new AbstractAction(){
			private static final long serialVersionUID = 1L;
			@Override
		    public void actionPerformed(ActionEvent e){
				bilatuAkzioa();
		    }
		});
		bilatuPanela.add(labela, BorderLayout.NORTH);
		bilatuPanela.add(izena, BorderLayout.CENTER);
		add(bilatuPanela, BorderLayout.NORTH);
		
		JButton bilatuBotoia = new JButton("Bilatu");
		bilatuBotoia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bilatuAkzioa();
			}
		});
		add(bilatuBotoia, BorderLayout.CENTER);
	}
	
	private void bilatuAkzioa(){
		Administratzailea a = (Administratzailea) Bideokluba.getBideokluba().getUnekoErabiltzailea();
		Erabiltzailea e = a.erabiltzaileaBilatu(izena.getText());
		erabiltzaileaInprimatu(e);
	}
	
	private void erabiltzaileaInprimatu(Erabiltzailea e){
		JPanel erabilInfo = new JPanel(new GridLayout(5, 2));
		//String[] labelak = {"ID-a:","Izenburua:","Prezioa:","Egoera:","Data:"};
		String[] labelak = {}; 
		String[] info = e.getInfo();
		for(int x=0; x<labelak.length; x++){
			JLabel l = new JLabel(labelak[x]);
			JLabel i = new JLabel(info[x]);
			add(l);
			add(i);
		}
		add(erabilInfo);
		Leihoa.getLeihoa().setVisible(true);
	}
}
