package db.proiektua.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import db.proiektua.logic.Administratzailea;
import db.proiektua.logic.Bideokluba;
import db.proiektua.logic.Erabiltzailea;

public class BazkideEgoeraPanela extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField izena;
	private JTextArea errorea = new JTextArea();
	private JPanel erabilInfo = new JPanel();
	private Erabiltzailea erabiltzailea;

	public BazkideEgoeraPanela(){
		setLayout(new BorderLayout());
		
		JPanel bilatuPanela = new JPanel(new GridLayout(2,1));
		JLabel labela = new JLabel("BAJA EMAN NAHI DEN ERABILTZAILEA BILATU", SwingConstants.CENTER);
		izena = new  JTextField();
		izena.addActionListener(new AbstractAction(){
			private static final long serialVersionUID = 1L;
			@Override
		    public void actionPerformed(ActionEvent e){
				if(!izena.getText().isEmpty()){
					bilatuAkzioa();
				}
		    }
		});
		bilatuPanela.add(labela);
		bilatuPanela.add(izena);
		add(bilatuPanela, BorderLayout.NORTH);
		
		JButton bilatuBotoia = new JButton("Bilatu");
		bilatuBotoia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!izena.getText().isEmpty()){
					bilatuAkzioa();
				}
			}
		});
		add(bilatuBotoia, BorderLayout.CENTER);
	}
	
	private void bilatuAkzioa(){
		Administratzailea a = (Administratzailea) Bideokluba.getBideokluba().getUnekoErabiltzailea();
		Erabiltzailea e = a.erabiltzaileaBilatu(izena.getText());
		if(e==null){
			remove(errorea);		
			remove(erabilInfo);
			errorea.setText("Erabiltzaile hau ez da topatu");
			errorea.setEditable(false);
			errorea.setBackground(getBackground());
			errorea.setForeground(new Color(255, 0, 0));
			add(errorea, BorderLayout.SOUTH);
			Leihoa.getLeihoa().setVisible(true);
		}
		else{
			erabiltzailea = e;
			erabiltzaileaInprimatu(e);
		}
	}
	
	private void erabiltzaileaInprimatu(Erabiltzailea e){
		remove(errorea);
		remove(erabilInfo);
		erabilInfo.setLayout(new BorderLayout(0,20));
		JPanel inf = new JPanel(new GridLayout(8, 2));
		Border padding = BorderFactory.createEmptyBorder(30, 10, 10, 10);
		erabilInfo.setBorder(padding);
		String[] labelak = {"ID:", "Erabiltzailea:", "Izena:", "Abizena:", "Helbidea:", "Kreditua:", "Bazkidea noiztik:", "Egoera:"}; 
		String[] info = e.getInfo();
		for(int x=0; x<labelak.length; x++){
			JLabel l = new JLabel(labelak[x]);
			JLabel i = new JLabel(info[x]);
			inf.add(l);
			inf.add(i);
		}
		erabilInfo.add(inf, BorderLayout.NORTH);
		
		JButton egoBotoia = new JButton("EGOERA ALDATU");
		egoBotoia.addActionListener(this);
		erabilInfo.add(egoBotoia, BorderLayout.CENTER);
		add(erabilInfo, BorderLayout.SOUTH);
		Leihoa.getLeihoa().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		erabiltzailea.egoeraAldatu();
	}
}
