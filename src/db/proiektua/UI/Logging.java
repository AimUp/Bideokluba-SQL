package db.proiektua.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Logging extends JPanel{

	private static final long serialVersionUID = 1L;

	public Logging(){
		setLayout(new BorderLayout());
		
		JPanel erabiltzailePanela = new JPanel(new FlowLayout());
		JLabel izena = new JLabel("Erabiltzailea: ");
		JTextField erabSarr = new JTextField(15);
		
		erabiltzailePanela.add(izena, FlowLayout.LEFT);
		erabiltzailePanela.add(erabSarr, FlowLayout.CENTER);
		
		JPanel pasahitzPanela = new JPanel(new FlowLayout());
		JLabel pass = new JLabel("Pasahitza: ");
		JPasswordField pasSarr = new JPasswordField();
		pasSarr.setPreferredSize(new Dimension(190, 26));
		pasahitzPanela.add(pass, FlowLayout.LEFT);
		pasahitzPanela.add(pasSarr, FlowLayout.CENTER);
		
		JButton okBotoia = new JButton();
		okBotoia.setText("OK");
	
		
		add(erabiltzailePanela, BorderLayout.NORTH);
		add(pasahitzPanela, BorderLayout.CENTER);
		add(okBotoia, BorderLayout.SOUTH);	
	}
	
}
