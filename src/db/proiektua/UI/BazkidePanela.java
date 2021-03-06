package db.proiektua.UI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BazkidePanela extends JPanel{

	private static final long serialVersionUID = 1L;

	public BazkidePanela(){
		setLayout(new GridLayout(4, 1));
		
		JButton bazkideaSortu = new JButton("DATU PERTSONALAK");
		bazkideaSortu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Leihoa.getLeihoa().panelaAldatu(new DatuPertsonalakPanela());
			}
		});
		add(bazkideaSortu);
		
		JButton bazkidearenEgoera = new JButton("KREDITUA SARTU");
		bazkidearenEgoera.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Leihoa.getLeihoa().panelaAldatu(new KredituaGehituPanela());
			}
		});
		add(bazkidearenEgoera);
		
		JButton pelikulaSortu = new JButton("PELIKULA BAT ALOKATU");
		pelikulaSortu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Leihoa.getLeihoa().panelaAldatu(new PelikulaAlokatuPanela());
			}
		});
		add(pelikulaSortu);
		
		JButton pelikularenEgoera = new JButton("PELIKULA BAT ITZULI");
		pelikularenEgoera.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Leihoa.getLeihoa().panelaAldatu(new PelikulaItzuliPanela());
			}
		});
		add(pelikularenEgoera);		
	}
}
