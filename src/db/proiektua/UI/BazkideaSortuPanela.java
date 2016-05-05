package db.proiektua.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import Salbuespenak.BazkideaBadago;
import Salbuespenak.StringLuzea;
import db.proiektua.db.Aginduak;
import db.proiektua.logic.Administratzailea;
import db.proiektua.logic.Bideokluba;

public class BazkideaSortuPanela extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField[] textFieldak;
	private JPasswordField pass;
	private JPasswordField passErrepikatu;
	private JLabel mezua = new JLabel();

	public BazkideaSortuPanela(){
		setLayout(new BorderLayout());
		JPanel panela = new JPanel(new SpringLayout());
		
		String[] labelak = {"Erabiltzailea:", "Izena:", "Abizena:", "Helbidea:"};
		textFieldak = new JTextField[labelak.length];
		
		for(int x=0; x<labelak.length; x++){
			JLabel l = new JLabel(labelak[x]);
			panela.add(l);
			textFieldak[x] = new JTextField();
			panela.add(textFieldak[x]);
		}
		
		JLabel label1 = new JLabel("Pasahitza:");
		panela.add(label1);
		pass = new JPasswordField();
		panela.add(pass);
		
		JLabel label2 = new JLabel("Pasahitza errepikatu:");
		panela.add(label2);
		passErrepikatu = new JPasswordField();
		panela.add(passErrepikatu);
		
		SpringUtilities.makeCompactGrid(panela,labelak.length+2, 2, 6, 6, 6, 6);
		
		JButton okBotoia = new JButton("OK");
		okBotoia.addActionListener(this);
		
		add(panela, BorderLayout.NORTH);
		add(okBotoia, BorderLayout.CENTER);
	}
	
	private void erabiltzaileaBadago(){
		remove(mezua);
		mezua = new JLabel("Erabiltzaile hau dagoeneko haukeratuta dago", SwingConstants.CENTER);
		mezua.setOpaque(true);
		mezua.setForeground(new Color(255, 0, 0));
		add(mezua, BorderLayout.SOUTH);
		Leihoa.getLeihoa().setVisible(true);
	}
	
	private void errorea(){
		remove(mezua);
		mezua = new JLabel("Errore bat erabiltzailea sortzean", SwingConstants.CENTER);
		mezua.setOpaque(true);
		mezua.setForeground(new Color(255, 0, 0));
		add(mezua, BorderLayout.SOUTH);
		Leihoa.getLeihoa().setVisible(true);
	}
	
	private void ondo(){
		remove(mezua);
		mezua = new JLabel("Erabiltzailea ondo sortu da", SwingConstants.CENTER);
		mezua.setOpaque(true);
		mezua.setForeground(new Color(0, 255, 0));
		add(mezua, BorderLayout.SOUTH);
		Leihoa.getLeihoa().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			Aginduak a = new Aginduak();
			if(a.erabiltzaileaLortu(textFieldak[0].getText())!=null){
				{throw new BazkideaBadago();}
			}
			try{
				for(int x=0; x<textFieldak.length-1; x++){
					if(textFieldak[x].getText().length()>30){
						{throw new StringLuzea();}
					}
				}
				if(textFieldak[textFieldak.length-1].getText().length()>50){
					{throw new StringLuzea();}
				}
				Administratzailea admi = (Administratzailea) Bideokluba.getBideokluba().getUnekoErabiltzailea();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				admi.bazkideBerriaSortu(pass.getPassword(), textFieldak[0].getText(), textFieldak[1].getText(), textFieldak[2].getText(), textFieldak[3].getText(), 0, 1, sdf.format(new Date()));
				ondo();
			}catch(StringLuzea exc){
				erabiltzaileaBadago();
			}
		}catch(BazkideaBadago ex){
			errorea();
		}
	}
}
