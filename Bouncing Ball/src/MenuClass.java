import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MenuClass extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public MenuClass() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar (menuBar);
		
		JMenu file = new JMenu("Menu");
		JMenu edit = new JMenu("Edit");
		JMenu help = new JMenu("Help");
		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(help);
		
		JMenuItem exit = new JMenuItem("Exit");
		file.add(exit);
		JMenuItem color = new JMenuItem("Color");
		edit.add(color);
		JMenuItem help2 = new JMenuItem("Help");
		JMenuItem about = new JMenuItem("About");
		help.add(help2);
		help.add(about);

		//Exit
		class ExitListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				System.exit(EXIT_ON_CLOSE); //Closes the program
			}
		}
		ActionListener exitAction = new ExitListener();
		exit.addActionListener(exitAction);
		
		//About
		class AboutListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(about, "Name: Ryan Doughty\nBouncing Ball Project (Assignment 3)\nDue Date: 5/7/2019\nMr. Ritter", "About", EXIT_ON_CLOSE); //I used this version of JOption Pane so that I could have a title 
			}
		}
		ActionListener aboutListener = new AboutListener();
		about.addActionListener(aboutListener);
		
		
	}
}
