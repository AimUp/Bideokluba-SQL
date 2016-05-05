package db.proiektua.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import Salbuespenak.StringLuzea;
import db.proiektua.logic.Administratzailea;
import db.proiektua.logic.Bideokluba;

public class PelikulaSortuPanela extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JTextField[] textFieldak;
	private JLabel mezua = new JLabel();
	
	public PelikulaSortuPanela(){
		setLayout(new BorderLayout());
		JPanel panela = new JPanel(new SpringLayout());
		
		String[] labelak = {"Izenburua:", "Prezioa:", "Data:"};
		textFieldak = new JTextField[labelak.length];
		
		for(int x=0; x<labelak.length; x++){
			JLabel l = new JLabel(labelak[x]);
			panela.add(l);
			textFieldak[x] = new JTextField();
			panela.add(textFieldak[x]);
		}
		
		SpringUtilities.makeCompactGrid(panela,labelak.length, 2, 6, 6, 6, 6);
		
		JButton okBotoia = new JButton("OK");
		okBotoia.addActionListener(this);
		
		add(panela, BorderLayout.NORTH);
		add(okBotoia, BorderLayout.CENTER);
	}
	
	private void errorea(){
		remove(mezua);
		mezua = new JLabel("Errore bat pelikula sortzean", SwingConstants.CENTER);
		mezua.setOpaque(true);
		mezua.setForeground(new Color(255, 0, 0));
		add(mezua, BorderLayout.SOUTH);
		Leihoa.getLeihoa().setVisible(true);
	}
	
	private void ondo(){
		remove(mezua);
		mezua = new JLabel("Pelikula ondo sortu da", SwingConstants.CENTER);
		mezua.setOpaque(true);
		mezua.setForeground(new Color(0, 255, 0));
		add(mezua, BorderLayout.SOUTH);
		Leihoa.getLeihoa().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Administratzailea a = (Administratzailea) Bideokluba.getBideokluba().getUnekoErabiltzailea();
		try {
			int prezioa = Integer.parseInt(textFieldak[1].getText());
		    try{ 
		    	if(textFieldak[0].getText().length()>50 || textFieldak[1].getText().length()>11 || textFieldak[2].getText().length()>10){
		    		{throw new StringLuzea();}
		    	}
		    	ondo();
		    	a.pelikulaBerriBatSartu(textFieldak[0].getText(), prezioa, textFieldak[2].getText());
		    }catch(StringLuzea exc){
		    	for(int x=0; x<textFieldak.length; x++){
		    		textFieldak[x].setText("");
		    		errorea();
		    	}
		    }
		}catch (NumberFormatException ex) {
			textFieldak[1].setText("");
			errorea();
		}
	}
}
