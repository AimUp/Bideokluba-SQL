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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import db.proiektua.db.Aginduak;
import db.proiektua.logic.Pelikula;

public class KontuLibreaPanela extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextField izenburu;
	private JLabel mezua = new JLabel();
	private JPanel peliInfo = new JPanel();

	public KontuLibreaPanela(){
		setLayout(new BorderLayout());
		add(new EstreinaldienPanela(), BorderLayout.NORTH);
		
		JPanel bilatuPanela = new JPanel(new GridLayout(3,1));
		JLabel labela = new JLabel("PELIKULAREN IZENBURUA SARTU", SwingConstants.CENTER);
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
		
		JButton bilatuBotoia = new JButton("Bilatu");
		bilatuBotoia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!izenburu.getText().isEmpty()){
					bilatuAkzioa();
				}
			}
		});
		bilatuPanela.add(bilatuBotoia);
		add(bilatuPanela, BorderLayout.CENTER);
	}
	
	private void bilatuAkzioa(){
		Aginduak a = new Aginduak();
		Pelikula p = a.bilatuPelikula(izenburu.getText());
		if(p==null){
			remove(mezua);		
			remove(peliInfo);
			mezua.setText("Pelikula hau ez da topatu");
			mezua.setBackground(getBackground());
			mezua.setForeground(new Color(255, 0, 0));
			add(mezua, BorderLayout.SOUTH);
			Leihoa.getLeihoa().setVisible(true);
		}
		else{
			pelikulaInprimatu(p);
		}
	}
	
	private void pelikulaInprimatu(Pelikula p){
		remove(mezua);
		
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
		
		add(peliInfo, BorderLayout.SOUTH);
		Leihoa.getLeihoa().setVisible(true);
	}
}
