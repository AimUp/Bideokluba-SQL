package db.proiektua.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import db.proiektua.Salbuespenak.BazkideaBadago;
import db.proiektua.Salbuespenak.PasahitzDesberdina;
import db.proiektua.Salbuespenak.StringLuzea;
import db.proiektua.db.Aginduak;
import db.proiektua.logic.Bazkidea;
import db.proiektua.logic.Bideokluba;

public class DatuPertsonalakPanela extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField[] textFieldak;
	private JPasswordField pass;
	private JPasswordField passErrepikatu;
	private JLabel mezua = new JLabel();
	private Bazkidea bazkide;
	
	public DatuPertsonalakPanela(){
		bazkide = (Bazkidea) Bideokluba.getBideokluba().getUnekoErabiltzailea();
		String[] inf = bazkide.getInfo();
		
		setLayout(new BorderLayout());
		JPanel panela = new JPanel(new SpringLayout());
		
		String[] labelak = {"Erabiltzailea:", "Izena:", "Abizena:", "Helbidea:"};
		textFieldak = new JTextField[labelak.length];
		
		for(int x=0; x<labelak.length; x++){
			JLabel l = new JLabel(labelak[x]);
			panela.add(l);
			textFieldak[x] = new JTextField(inf[x+1]);
			panela.add(textFieldak[x]);
		}
		
		JLabel kred = new JLabel("Kredituak");
		JLabel kredZen = new JLabel(inf[5], SwingConstants.CENTER);
		panela.add(kred);
		panela.add(kredZen);
		
		JLabel label1 = new JLabel("Pasahitza:");
		panela.add(label1);
		pass = new JPasswordField();
		panela.add(pass);
		
		JLabel label2 = new JLabel("Pasahitza errepikatu:");
		panela.add(label2);
		passErrepikatu = new JPasswordField();
		panela.add(passErrepikatu);
		
		SpringUtilities.makeCompactGrid(panela,labelak.length+3, 2, 6, 6, 6, 6);
		
		JButton okBotoia = new JButton("OK");
		okBotoia.addActionListener(this);
		
		add(panela, BorderLayout.NORTH);
		add(okBotoia, BorderLayout.CENTER);
	}
	
	private void errorea(String err){
		remove(mezua);
		mezua = new JLabel(err, SwingConstants.CENTER);
		mezua.setOpaque(true);
		mezua.setForeground(new Color(255, 0, 0));
		add(mezua, BorderLayout.SOUTH);
		Leihoa.getLeihoa().setVisible(true);
	}
	
	private void ondo(){
		remove(mezua);
		mezua = new JLabel("Datuak ondo aldatu egin dira", SwingConstants.CENTER);
		mezua.setOpaque(true);
		mezua.setForeground(new Color(0, 255, 0));
		add(mezua, BorderLayout.SOUTH);
		Leihoa.getLeihoa().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Bazkidea baz = (Bazkidea) Bideokluba.getBideokluba().getUnekoErabiltzailea();
		try{
			if(!textFieldak[0].getText().equals(baz.getInfo()[1])){
				Aginduak a = new Aginduak();
				if(a.erabiltzaileaLortu(textFieldak[0].getText())!=null){
					{throw new BazkideaBadago();}
				}
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
				try{
					if(!Arrays.equals(pass.getPassword(), passErrepikatu.getPassword())){
						{throw new PasahitzDesberdina();}
					}
					String p = new String(pass.getPassword());
					String[] aldatu = {textFieldak[0].getText(), textFieldak[1].getText(), textFieldak[2].getText(), textFieldak[3].getText(), p};
					baz.datuPertsonalakAldatu(aldatu);
					ondo();	
				}
				catch(PasahitzDesberdina ex){
					errorea("Bi pasahitzek ez dute koinziditzen");
				}
			}catch(StringLuzea exc){
				errorea("Erroreren bat datuak sartzean");
			}
		}catch(BazkideaBadago ex){
			errorea("Erabiltzaile hau dagoeneko haukeratuta dago");
		}
	}	
}
