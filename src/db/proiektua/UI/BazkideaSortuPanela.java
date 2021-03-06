package db.proiektua.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

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
import db.proiektua.logic.Administratzailea;
import db.proiektua.logic.Bideokluba;

public class BazkideaSortuPanela extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField erabTx;
	private JPasswordField pass;
	private JPasswordField passErrepikatu;
	private JLabel mezua = new JLabel();

	public BazkideaSortuPanela(){
		setLayout(new BorderLayout());
		JPanel panela = new JPanel(new SpringLayout());
		
		JLabel l = new JLabel("Erabiltzailea:");
		panela.add(l);
		erabTx = new JTextField();
		panela.add(erabTx);
		
		JLabel label1 = new JLabel("Pasahitza:");
		panela.add(label1);
		pass = new JPasswordField();
		panela.add(pass);
		
		JLabel label2 = new JLabel("Pasahitza errepikatu:");
		panela.add(label2);
		passErrepikatu = new JPasswordField();
		panela.add(passErrepikatu);
		
		SpringUtilities.makeCompactGrid(panela,3, 2, 6, 6, 6, 6);
		
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
			if(a.erabiltzaileaLortu(erabTx.getText())!=null){
				{throw new BazkideaBadago();}
			}
			try{
				if(erabTx.getText().length()>30){
					{throw new StringLuzea();}
				}
				try{
					if(!Arrays.equals(pass.getPassword(), passErrepikatu.getPassword())){
						{throw new PasahitzDesberdina();}
					}
					Administratzailea admi = (Administratzailea) Bideokluba.getBideokluba().getUnekoErabiltzailea();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					admi.bazkideBerriaSortu(pass.getPassword(), erabTx.getText(), 0, 1, sdf.format(new Date()));
					ondo();
				}catch(PasahitzDesberdina ex){
					errorea("Bi pasahitzek ez dute koinziditzen");
				}
			}catch(StringLuzea ex){
				errorea("Izen edo pasahitz luzeegia");
			}
		}catch(BazkideaBadago ex){
			errorea("Erabiltzaile hau dagoeneko haukeratuta dago");
		}
	}
}
