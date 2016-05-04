package db.proiektua.UI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import db.proiektua.logic.Administratzailea;
import db.proiektua.logic.Bideokluba;

public class PelikulaSortuPanela extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JTextField[] textFieldak;
	
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Administratzailea a = (Administratzailea) Bideokluba.getBideokluba().getUnekoErabiltzailea();
		try {
		     int prezioa = Integer.parseInt(textFieldak[1].getText());
		     a.pelikulaBerriBatSartu(textFieldak[0].getText(), prezioa, textFieldak[2].getText());
		} catch (NumberFormatException ex) {
			textFieldak[1].setText("");
		}
	}

}
