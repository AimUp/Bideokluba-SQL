package db.proiektua.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import db.proiektua.logic.Bideokluba;

public class Logging extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextField erabSarr;
	private JPasswordField pasSarr;
	private JTextArea errorea = new JTextArea();

	public Logging(){
		setLayout(new BorderLayout());
		
		//Erabiltzailea
		JPanel erabiltzailePanela = new JPanel(new FlowLayout());
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
		erabiltzailePanela.add(izena, FlowLayout.LEFT);
		erabiltzailePanela.add(erabSarr, FlowLayout.CENTER);
		
		
		//Pasahitza
		JPanel pasahitzPanela = new JPanel(new FlowLayout());
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
		pasahitzPanela.add(pass, FlowLayout.LEFT);
		pasahitzPanela.add(pasSarr, FlowLayout.CENTER);
		
	
		//OK botoia
		JButton okBotoia = new JButton();
		okBotoia.setText("OK");
		okBotoia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				okAkzioa();
				
			}
		});
		
		JPanel datuSarrera = new JPanel();
		datuSarrera.setLayout(new BorderLayout());
		datuSarrera.add(erabiltzailePanela, BorderLayout.NORTH);
		datuSarrera.add(pasahitzPanela, BorderLayout.CENTER);
		
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
		Leihoa.getLeihoa().setVisible(true);
	}
	
	private void okAkzioa(){
		Bideokluba.getBideokluba().erabiltzaileaKonprobatu(erabSarr.getText(), pasSarr.getPassword());
	}
}
