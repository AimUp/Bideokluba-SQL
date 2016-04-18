package db.proiektua.UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

public class Logging extends JPanel{

	private static final long serialVersionUID = 1L;

	public Logging(){
		setLayout(new SpringLayout());
		JLabel izena = new JLabel("Erabiltzailea: ", SwingConstants.TRAILING);
		JTextField erabSarr = new JTextField(15);
		erabSarr.setText("Erabiltzailea sartu");
		
		JLabel pass = new JLabel("Pasahitza: ", SwingConstants.TRAILING);
		JPasswordField pasSarr = new JPasswordField();
		pass.setLabelFor(pasSarr);
		
		JButton okBotoia = new JButton();
		okBotoia.setText("OK");
	
		
		add(izena);
		add(erabSarr);
		add(pass);
		add(pasSarr);
		add(okBotoia, BorderLayout.SOUTH);
		
		SpringUtilities.makeCompactGrid(this, 2, 2, 6, 6, 6, 6);
	}
	
}
