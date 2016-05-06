package db.proiektua.logic;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import db.proiektua.UI.Leihoa;

public class Main {

	public static void main(String[] args){
		try {											//PROGRAMAREN ESTILO GRAFIKOA ALDATU
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
		Leihoa.getLeihoa();
	}
}
