package com.github.veeshostak.app;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

//import com.github.veeshostak.constants.Constants;

//Every cell can be empty or have a bomb
//Mine Layers place bombs 
//Mine Sweepers sweep the mines
//
// each cell will be assigned a state
//EMPTY, MINE, MINESWEEPER, MINELAYER;


public class App {

	public static void main(String[] args) {
		
		try {
			// each OS has it's own design, set the look and feel for each system
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// run app in a different thread
				new MainFrame();
			}
		});
	}
}
