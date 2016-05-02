package db.proiektua.UI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AdminPanela extends JPanel{

	private static final long serialVersionUID = 1L;

	public AdminPanela(){
		setLayout(new GridLayout(4, 1));
		
		JButton bazkideaSortu = new JButton("BAZKIDEA SORTU");
		bazkideaSortu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Leihoa.getLeihoa().panelaAldatu(new BazkideaSortuPanela());
			}
		});
		add(bazkideaSortu);
		
		JButton bazkidearenEgoera = new JButton("BAZKIDEAREN EGOERA ALDATU");
		bazkidearenEgoera.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Leihoa.getLeihoa().panelaAldatu(new BazkideEgoeraPanela());
			}
		});
		add(bazkidearenEgoera);
		
		JButton pelikulaSortu = new JButton("PELIKULA SORTU");
		pelikulaSortu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		add(pelikulaSortu);
		
		JButton pelikularenEgoera = new JButton("PELIKULAREN EGOERA ALDATU");
		pelikularenEgoera.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		add(pelikularenEgoera);		
	}
}
