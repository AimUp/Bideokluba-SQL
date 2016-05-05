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
		
		JButton bazkideaSortu = new JButton("");
		bazkideaSortu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(bazkideaSortu);
		
		JButton bazkidearenEgoera = new JButton("");
		bazkidearenEgoera.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(bazkidearenEgoera);
		
		JButton pelikulaSortu = new JButton("");
		pelikulaSortu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(pelikulaSortu);
		
		JButton pelikularenEgoera = new JButton("");
		pelikularenEgoera.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(pelikularenEgoera);		
	}
}
