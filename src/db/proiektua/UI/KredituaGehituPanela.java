package db.proiektua.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import db.proiektua.logic.Bazkidea;
import db.proiektua.logic.Bideokluba;

public class KredituaGehituPanela extends JPanel{

	private static final long serialVersionUID = 1L;
	private int diruaGehitu = 0;
	private Bazkidea bazkidea = (Bazkidea) Bideokluba.getBideokluba().getUnekoErabiltzailea();
	private JLabel zureKred;
	private JLabel dirua;

	public KredituaGehituPanela(){
		Border padding = BorderFactory.createEmptyBorder(30, 0, 0, 0);
		setBorder(padding);
		
		setLayout(new BorderLayout(0,30));
		
		JPanel inf = new JPanel(new BorderLayout(0,10));
		JLabel textua = new JLabel("GEHITU NAHI DITUZUN KREDITUAK SARTU", SwingConstants.CENTER);
		zureKred = new JLabel("Zure kreditua: " + bazkidea.getKreditua(), SwingConstants.CENTER);
		inf.add(textua, BorderLayout.NORTH);
		inf.add(zureKred, BorderLayout.CENTER);
		add(inf, BorderLayout.NORTH);
		
		JPanel sartu = new JPanel(new FlowLayout());
		dirua = new JLabel("0");
		JPanel kenduPan = new JPanel();
		JButton kendu = new JButton("-");
		kendu.setPreferredSize(new Dimension(40, 35));
		kendu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(diruaGehitu>0){
					diruaGehitu--;
					dirua.setText(String.valueOf(diruaGehitu));
				}
			}
		});
		JButton kendu10 = new JButton("-10");
		kendu10.setPreferredSize(new Dimension(40, 35));
		kendu10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(diruaGehitu>=10){
					diruaGehitu = diruaGehitu-10;
					dirua.setText(String.valueOf(diruaGehitu));
				}
			}
		});
		kenduPan.add(kendu10);
		kenduPan.add(kendu);
		
		JPanel gehituPan = new JPanel();
		JButton gehitu = new JButton("+");
		gehitu.setPreferredSize(new Dimension(40, 35));
		gehitu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(diruaGehitu<100000){
					diruaGehitu++;
					dirua.setText(String.valueOf(diruaGehitu));
				}
			}
		});
		JButton gehitu10 = new JButton("+10");
		gehitu10.setPreferredSize(new Dimension(40, 35));
		gehitu10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(diruaGehitu<=99990){
					diruaGehitu = diruaGehitu+10;
					dirua.setText(String.valueOf(diruaGehitu));
				}
			}
		});
		gehituPan.add(gehitu);
		gehituPan.add(gehitu10);
		
		JButton ok = new JButton("SARTU");
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bazkidea.kredituaGehitu(diruaGehitu);
				zureKred.setText("Zure kreditua: " + bazkidea.getKreditua());
			}
		});
		
		sartu.add(kenduPan,FlowLayout.LEFT);
		sartu.add(dirua, FlowLayout.CENTER);
		sartu.add(gehituPan, FlowLayout.RIGHT);
		add(sartu, BorderLayout.CENTER);
		add(ok, BorderLayout.SOUTH);
	}
}
