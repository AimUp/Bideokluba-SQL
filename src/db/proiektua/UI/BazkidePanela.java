package db.proiektua.UI;

import javax.swing.JPanel;

public class BazkidePanela extends JPanel{

	private static final long serialVersionUID = 1L;
	private JPanel panela;

	public BazkidePanela(){
		add(panela);
		Leihoa.getLeihoa().setVisible(true);
	}
}
