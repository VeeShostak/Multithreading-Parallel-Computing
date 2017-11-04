package com.github.veeshostak.app;

import com.github.veeshostak.gui.Board;
import com.github.veeshostak.gui.Controller;
import com.github.veeshostak.gui.TimePanel;
import com.github.veeshostak.gui.Toolbar;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.github.veeshostak.constants.Constants;
import com.github.veeshostak.callbacks.ButtonListener;


public class MainFrame extends JFrame implements ButtonListener{

	private static final long serialVersionUID = 1L;
	private Board board;
	private Toolbar toolbar;
	private TimePanel timePanel;
	private ExecutorService executor;
	
	public MainFrame(){
		super("Conway's Game of Life");
		
		setJMenuBar(createMenuBar());
	
		toolbar=new Toolbar();
		timePanel=new TimePanel();
		board=new Board(timePanel);
		
		toolbar.setButtonListener(this);
		
		add(board,BorderLayout.CENTER);
		add(toolbar,BorderLayout.NORTH);
		add(timePanel,BorderLayout.SOUTH);
		
		setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(this);	
	}
	
	public JMenuBar createMenuBar(){
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(exitMenuItem);
		
		JMenu aboutMenu = new JMenu("About");
		JMenu helpMenu = new JMenu("Help");
		
		menuBar.add(fileMenu);
		menuBar.add(aboutMenu);
		menuBar.add(helpMenu);
		
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int action = JOptionPane.showConfirmDialog(MainFrame.this, "Exit the program?","Warning",JOptionPane.YES_NO_OPTION);
				if( action == JOptionPane.OK_OPTION ){
					System.gc();
					System.exit(0);
				}
			}
		});
		
		return menuBar;
	}

	@Override
	public void startClicked() {
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				toolbar.setStartButton(false);
				toolbar.setRestartButton(true);
			}
		});
		
		Controller.startThread();
		executor = Executors.newSingleThreadExecutor();
		executor.execute(new Controller(board));
	}

	@Override
	public void restartClicked() {
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				toolbar.setStartButton(true);
				toolbar.setRestartButton(false);
			}
		});
		
		executor.shutdown();
	    Controller.stopThread();
	    board.resetBorder();
	    timePanel.refreshCounter();
	}
}
