package db.proiektua.UI;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class Leihoa extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private static Leihoa nLeihoa = null;
	
	private Leihoa(){
		setTitle("BIDEOKLUBA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350, 500);
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
	}
	
	public static Leihoa getLeihoa(){
		if(nLeihoa==null){
			nLeihoa = new Leihoa();
		}
		return nLeihoa;
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}
}
