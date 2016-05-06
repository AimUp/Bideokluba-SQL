package db.proiektua.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import db.proiektua.logic.Bazkidea;
import db.proiektua.logic.Bideokluba;
import db.proiektua.logic.Pelikula;

public class PelikulaItzuliPanela extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Pelikula peli;

	public PelikulaItzuliPanela(){
		setLayout(new BorderLayout());
		Bazkidea b = (Bazkidea) Bideokluba.getBideokluba().getUnekoErabiltzailea();
		ArrayList<Pelikula> pl = b.alokatuakLortu();
		
		JPanel pelikulak = new JPanel(new GridLayout(1, pl.size()));
		for(int x=0; x<pl.size(); x++){
			peli = pl.get(x);
			JPanel p = new JPanel();
			p.setLayout(new BorderLayout(0,20));
			
			JPanel inf = new JPanel(new GridLayout(5, 2));
			if(x % 2 == 0){
				p.setBackground(new Color(238, 238, 238));	
				inf.setBackground(new Color(238, 238, 238));	
			}
			else{
				p.setBackground(new Color(233, 233, 233));	
				inf.setBackground(new Color(233, 233, 233));	
			}
			Border padding = BorderFactory.createEmptyBorder(30, 10, 10, 10);
			p.setBorder(padding);
			String[] labelak = {"ID-a:","Izenburua:","Prezioa:","Egoera:","Data:"};
			String[] info = peli.getInfo();
			for(int y=0; y<labelak.length; y++){
				JLabel l = new JLabel(labelak[y]);
				JLabel i = new JLabel(info[y]);
				inf.add(l);
				inf.add(i);
			}
			p.add(inf, BorderLayout.NORTH);
			JButton itzuliBot = new JButton("ITZULI");
			itzuliBot.addActionListener(this);
			p.add(itzuliBot, BorderLayout.CENTER);
			pelikulak.add(p);
		}
		
		JScrollPane scrollPane = new JScrollPane(pelikulak, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0,0,pelikulak.getWidth(),pelikulak.getHeight());
        
        add(scrollPane, BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Bazkidea b = (Bazkidea) Bideokluba.getBideokluba().getUnekoErabiltzailea();
		b.pelikulaItzuli(peli);
		removeAll();
		setLayout(new BorderLayout());
		JLabel mezua = new JLabel("Pelikula itzuli egin da", SwingConstants.CENTER);
		mezua.setOpaque(true);
		mezua.setForeground(new Color(0, 255, 0));
		add(mezua, BorderLayout.NORTH);
		Leihoa.getLeihoa().repaint();
		Leihoa.getLeihoa().setVisible(true);
	}
}
