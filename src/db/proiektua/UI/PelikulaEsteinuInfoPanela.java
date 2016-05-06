package db.proiektua.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
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
import db.proiektua.logic.Pelikula;

public class PelikulaEsteinuInfoPanela extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Pelikula peli;

	public PelikulaEsteinuInfoPanela(Pelikula p, int x){
		peli = p;
		setLayout(new BorderLayout(0,20));
		
		JPanel inf = new JPanel(new GridLayout(5, 2));
		if(x % 2 == 0){
			setBackground(new Color(238, 238, 238));	
			inf.setBackground(new Color(238, 238, 238));	
		}
		else{
			setBackground(new Color(233, 233, 233));	
			inf.setBackground(new Color(233, 233, 233));	
		}
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		setBorder(padding);
		String[] labelak = {"ID-a:","Izenburua:","Prezioa:","Egoera:","Data:"};
		String[] info = peli.getInfo();
		for(int y=0; y<labelak.length; y++){
			JLabel l = new JLabel(labelak[y]);
			JLabel i = new JLabel(info[y]);
			inf.add(l);
			inf.add(i);
		}
		add(inf, BorderLayout.NORTH);
		JButton itzuliBot = new JButton("ALOKATU");
		itzuliBot.addActionListener(this);
		add(itzuliBot, BorderLayout.CENTER);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(Bideokluba.getBideokluba().getUnekoErabiltzailea() != null){
			Bazkidea b = (Bazkidea) Bideokluba.getBideokluba().getUnekoErabiltzailea();
			int erantzuna = b.pelikulaAlokatu(peli);
			if(erantzuna == 0){
				removeAll();
				setLayout(new BorderLayout());
				JLabel mezua = new JLabel("Pelikula alokatu egin da", SwingConstants.CENTER);
				mezua.setOpaque(true);
				mezua.setForeground(new Color(0, 255, 0));
				mezua.setBackground(getBackground());
				add(mezua, BorderLayout.NORTH);
				Leihoa.getLeihoa().repaint();
				Leihoa.getLeihoa().setVisible(true);
			}
			else if(erantzuna == -1){
				removeAll();
				setLayout(new BorderLayout());
				JLabel mezua = new JLabel("Ez duzu kreditu nahikorik", SwingConstants.CENTER);
				mezua.setOpaque(true);
				mezua.setForeground(new Color(255, 0, 0));
				mezua.setBackground(getBackground());
				add(mezua, BorderLayout.NORTH);
				Leihoa.getLeihoa().repaint();
				Leihoa.getLeihoa().setVisible(true);
			}
			else if(erantzuna == -2){
				removeAll();
				setLayout(new BorderLayout());
				JLabel mezua = new JLabel("Pelikula alokatuta dago dagoeneko", SwingConstants.CENTER);
				mezua.setOpaque(true);
				mezua.setForeground(new Color(255, 0, 0));
				mezua.setBackground(getBackground());
				add(mezua, BorderLayout.NORTH);
				Leihoa.getLeihoa().repaint();
				Leihoa.getLeihoa().setVisible(true);
			}
		}
		else{
			removeAll();
			setLayout(new BorderLayout());
			JLabel mezua = new JLabel("Pelikula alokatzeko kontu bat behar duzu", SwingConstants.CENTER);
			mezua.setOpaque(true);
			mezua.setForeground(new Color(255, 0, 0));
			mezua.setBackground(getBackground());
			add(mezua, BorderLayout.NORTH);
			Leihoa.getLeihoa().repaint();
			Leihoa.getLeihoa().setVisible(true);
		}
	}
	
}
