package db.proiektua.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import db.proiektua.logic.Administratzailea;
import db.proiektua.logic.Bideokluba;
import db.proiektua.logic.Pelikula;

public class PelikulaEgoeraPanela extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JTextField izenburu;
	private JTextArea errorea = new JTextArea();
	private JPanel peliInfo;
	private Pelikula pelikula;
	private JComboBox<String> comBox = new JComboBox<String>();

	public PelikulaEgoeraPanela(){
		setLayout(new BorderLayout());
		
		JPanel bilatuPanela = new JPanel(new GridLayout(2,1));
		JLabel labela = new JLabel("EGOERA ALDATU NAHI DEN PELIKULA BILATU", SwingConstants.CENTER);
		izenburu = new  JTextField();
		izenburu.addActionListener(new AbstractAction(){
			private static final long serialVersionUID = 1L;
			@Override
		    public void actionPerformed(ActionEvent e){
				bilatuAkzioa();
		    }
		});
		bilatuPanela.add(labela);
		bilatuPanela.add(izenburu);
		add(bilatuPanela, BorderLayout.NORTH);
		
		JButton bilatuBotoia = new JButton("Bilatu");
		bilatuBotoia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!izenburu.getText().isEmpty()){
					bilatuAkzioa();
				}
			}
		});
		add(bilatuBotoia, BorderLayout.CENTER);
		
		peliInfo = new JPanel();
		
		comBox.addItem("KATALOGATU");
		comBox.addItem("EZABATU");
	}
	
	private void bilatuAkzioa(){
		Administratzailea a = (Administratzailea) Bideokluba.getBideokluba().getUnekoErabiltzailea();
		Pelikula p = a.pelikulaBilatu(izenburu.getText());
		if(p==null){
			remove(errorea);		
			remove(peliInfo);
			errorea.setText("Pelikula hau ez da topatu");
			errorea.setEditable(false);
			errorea.setBackground(getBackground());
			errorea.setForeground(new Color(255, 0, 0));
			add(errorea, BorderLayout.SOUTH);
			Leihoa.getLeihoa().setVisible(true);
		}
		else{
			pelikula = p;
			pelikulaInprimatu(p);
		}
	}
	
	private void pelikulaInprimatu(Pelikula p){
		remove(errorea);
		
		peliInfo = new JPanel();
		peliInfo.setLayout(new BorderLayout(0,20));
		JPanel inf = new JPanel(new GridLayout(5, 2));
		Border padding = BorderFactory.createEmptyBorder(30, 10, 10, 10);
		peliInfo.setBorder(padding);
		String[] labelak = {"ID-a:","Izenburua:","Prezioa:","Egoera:","Data:"};
		String[] info = p.getInfo();
		for(int x=0; x<labelak.length; x++){
			JLabel l = new JLabel(labelak[x]);
			JLabel i = new JLabel(info[x]);
			inf.add(l);
			inf.add(i);
		}
		peliInfo.add(inf, BorderLayout.NORTH);
		
		JButton botoia = new JButton("Aldatu");
		botoia.addActionListener(this);
		
		peliInfo.add(comBox, BorderLayout.CENTER);
		peliInfo.add(botoia,  BorderLayout.SOUTH);
		add(peliInfo, BorderLayout.SOUTH);
		Leihoa.getLeihoa().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(comBox.getSelectedIndex());
		if("EZABATU" == (String)comBox.getSelectedItem()){
			pelikula.egoeraAldatu("EZABATU");
		}
		else{
			pelikula.egoeraAldatu("KATALOGATU");
		}
		pelikulaInprimatu(pelikula);
	}
}
