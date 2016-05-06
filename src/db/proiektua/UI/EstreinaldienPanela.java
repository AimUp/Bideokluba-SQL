package db.proiektua.UI;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import db.proiektua.logic.Bideokluba;
import db.proiektua.logic.Pelikula;

public class EstreinaldienPanela extends JPanel{

	private static final long serialVersionUID = 1L;

	public EstreinaldienPanela(){
		ArrayList<Pelikula> pl = Bideokluba.getBideokluba().estreinaldiakLortu();
		setLayout(new BorderLayout());
		
		JLabel estreinaldiak = new JLabel("Estreinaldiak", SwingConstants.CENTER);
		add(estreinaldiak, BorderLayout.NORTH);
		if(pl.size()>0){
			JPanel pelikulak = new JPanel();
			for(int x=0; x<pl.size(); x++){

				JPanel p = new PelikulaEsteinuInfoPanela(pl.get(x), x);
				pelikulak.add(p);
			}
			
			JScrollPane scrollPane = new JScrollPane(pelikulak, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        scrollPane.setBounds(0,0,pelikulak.getWidth(),pelikulak.getHeight());
	        
	        add(scrollPane, BorderLayout.CENTER);
		}
		else{
			JLabel estreEz = new JLabel("ESTREINALDIRIK EZ", SwingConstants.CENTER);
			add(estreEz, BorderLayout.CENTER);
		}
	}
}
