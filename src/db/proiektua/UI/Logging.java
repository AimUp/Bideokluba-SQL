package db.proiektua.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import db.proiektua.logic.Bideokluba;

public class Logging extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextField erabSarr;
	private JPasswordField pasSarr;
	private JTextArea errorea = new JTextArea();

	public Logging(){
		setLayout(new BorderLayout());
		JPanel datuSarrera = new JPanel(new SpringLayout());
		
		//Erabiltzailea
		JLabel izena = new JLabel("Erabiltzailea: ");
		erabSarr = new JTextField(15);
		erabSarr.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (erabSarr.getText().length()>=30){
				     e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		
		erabSarr.addActionListener(new AbstractAction(){
			private static final long serialVersionUID = 1L;
			@Override
		    public void actionPerformed(ActionEvent e){
				okAkzioa();
		    }
		});
		datuSarrera.add(izena);
		datuSarrera.add(erabSarr);
		
		//Pasahitza
		JLabel pass = new JLabel("Pasahitza: ");
		pasSarr = new JPasswordField();
		pasSarr.setPreferredSize(new Dimension(190, 26));
		pasSarr.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (pasSarr.getPassword().length>=30){
				     e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		
		pasSarr.addActionListener(new AbstractAction(){
			private static final long serialVersionUID = 1L;
			@Override
		    public void actionPerformed(ActionEvent e){
				okAkzioa();
		    }
		});
		datuSarrera.add(pass);
		datuSarrera.add(pasSarr);
		
		SpringUtilities.makeCompactGrid(datuSarrera,2, 2, 6, 6, 6, 6);
		
		//OK botoia
		JButton okBotoia = new JButton();
		okBotoia.setText("OK");
		okBotoia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				okAkzioa();
			}
		});
		
		add(datuSarrera, BorderLayout.NORTH);
		add(okBotoia, BorderLayout.CENTER);	
	}
	
	public void erabiltzaileOkerra(){
		remove(errorea);
		erabSarr.setText("");
		pasSarr.setText("");
		errorea.setText("Erabiltzaile okerra sartu duzu");
		errorea.setEditable(false);
		errorea.setBackground(getBackground());
		errorea.setForeground(new Color(255, 0, 0));
		add(errorea, BorderLayout.SOUTH);
		Leihoa.getLeihoa().repaint();
		Leihoa.getLeihoa().setVisible(true);
	}
	
	public void pasahitzOkerra(){
		remove(errorea);
		pasSarr.setText("");
		errorea.setText("Pasahitz okerra sartu duzu");
		errorea.setEditable(false);
		errorea.setBackground(getBackground());
		errorea.setForeground(new Color(255, 0, 0));
		add(errorea, BorderLayout.SOUTH);
		Leihoa.getLeihoa().repaint();
		Leihoa.getLeihoa().setVisible(true);
	}
	
	private void okAkzioa(){
		Bideokluba.getBideokluba().erabiltzaileaKonprobatu(erabSarr.getText(), pasSarr.getPassword());
	}
}
