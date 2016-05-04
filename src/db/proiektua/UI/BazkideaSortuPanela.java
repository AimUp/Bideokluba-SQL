package db.proiektua.UI;

import java.awt.BorderLayout;
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

import db.proiektua.logic.Administratzailea;
import db.proiektua.logic.Bideokluba;

public class BazkideaSortuPanela extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField[] textFieldak;
	private JPasswordField pass;
	private JPasswordField passErrepikatu;

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

	@Override
	public void actionPerformed(ActionEvent e) {
		Administratzailea a = (Administratzailea) Bideokluba.getBideokluba().getUnekoErabiltzailea();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		a.bazkideBerriaSortu(pass.getPassword(), textFieldak[0].getText(), textFieldak[1].getText(), textFieldak[2].getText(), textFieldak[3].getText(), 0, true, sdf.format(new Date()));
	}
}
