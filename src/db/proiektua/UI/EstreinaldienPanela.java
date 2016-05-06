package db.proiektua.UI;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class EstreinaldienPanela extends JPanel{

	private static final long serialVersionUID = 1L;

	public EstreinaldienPanela(){
		setLayout(new BorderLayout());
		
		JLabel estreinaldiak = new JLabel("Estreinaldiak", SwingConstants.CENTER);
		add(estreinaldiak, BorderLayout.NORTH);
		
		JPanel pelikulak = new JPanel();
		add(pelikulak, BorderLayout.CENTER);
	}
}
