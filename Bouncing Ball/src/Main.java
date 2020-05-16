import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Main {

	public static void main(String[] args) {
		final int WIDTH = 800;
		final int HEIGHT = 600;
		

		JFrame frame = new JFrame();
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.GRAY);

		frame.setTitle("Bouncing Ball");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
		Ball ball = new Ball(0, 0, 40, Color.black, 5, 5);
				
		//Moving Ball
		class moveTimer implements ActionListener{

			public void actionPerformed(ActionEvent e) {
		
				if (ball.getX() >= frame.getWidth() || ball.getX() <= 0) {
					ball.setDX(ball.getDX()*-1);
				}
				if (ball.getY() >= frame.getHeight() || ball.getY() <= 0) {
					ball.setDY(ball.getDY()*-1);
				}
				ball.setBall((ball.getX() + ball.getDX()), (ball.getY() + ball.getDY()), ball.getSizeBall());
			}
		}
		ActionListener moveListener = new moveTimer();
		Timer moveTimer = new Timer(50, moveListener);
		
		//Ball size clicker
		class BallSizeClicker implements MouseListener{
			int x1, y1;
			int x2, y2;
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				x1 = e.getX();
				y1 = e.getY();
				System.out.println("Pressed " + x1 + " " + y1);
			}
			public void mouseReleased(MouseEvent e) {
				x2 = e.getX();
				y2 = e.getY();
				int radius = (int) Math.sqrt(Math.pow((y2 - y1), 2)+Math.pow((x2-x1), 2));
				int locationX = x1 - radius - 10;
				int locationY = y1 - radius - 70;
				
				if (locationX < 0 || locationX > frame.getWidth() || locationY < 0 || locationY > frame.getHeight()) {
					JOptionPane.showMessageDialog(null, "You got a little too excited, and therefore \nthe ball is too large for the frame, try again", "BALL TOO BIG", 0);
				}else {
					ball.setBall(locationX, locationY, radius*2);
				}
				System.out.println("Released " + x2 + " " + y2);
				
				//moveTimer.start();
			}
		}	
			MouseListener ballsizeclicker = new BallSizeClicker();		
			
			
		//Random BUTTON with Timer
		class colorTimer implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				int red = (int) (Math.random()*255) + 1; //Random color generator that takes 3 random integers and makes a color out of them, then sets the color
				int green = (int) (Math.random()*255) + 1;
				int blue = (int) (Math.random()*255) + 1;					
				Color randomColor = new Color(red, green, blue);
				ball.setFill(randomColor);
			}
		}
		ActionListener colorTimer = new colorTimer();
		Timer t = new Timer(300, colorTimer);

		class colorListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				t.start();
			}
		}
		ActionListener color = new colorListener();
		JButton random = new JButton();
		random.setText("RANDOM");
		random.addActionListener(color);
			
			
			
		//RED BUTTON
		class RedListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				ball.setFill(Color.red);
				t.stop();
			}
		}
		ActionListener redListener = new RedListener();
		JButton red = new JButton();
		red.setText("RED");
		red.addActionListener(redListener);
			
			
		//BLUE BUTTON
		class BlueListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				t.stop();
				ball.setFill(Color.BLUE);
			}
		}
		ActionListener blueListener = new BlueListener();
		JButton blue = new JButton();
		blue.setText("BLUE");
		blue.addActionListener(blueListener);
		
			
			
		//GREEN BUTTON
		class GreenListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				t.stop();
				ball.setFill(Color.green);
			}
		}
		
		ActionListener greenListener = new GreenListener();
		JButton green = new JButton();
		green.setText("GREEN");
		green.addActionListener(greenListener);

		//Music Button
		class MusicListener implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {
				String song = "RickRoll.wav";				
				playSound(song);
			}			
		}
		JButton musicButton = new JButton();
		musicButton.setText("PLAY A SONG!");
		ActionListener music = new MusicListener();
		musicButton.addActionListener(music);
		
		
		
		
		//MENU SECTION OF THE PROGRAM
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar (menuBar);
		
		JMenu file = new JMenu("Menu"); //File
		JMenu edit = new JMenu("Edit"); //Edit
		JMenu help = new JMenu("Help"); //Help
		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(help);
		
		JMenuItem exit = new JMenuItem("Exit");
		file.add(exit);
		JMenuItem help2 = new JMenuItem("Help");
		JMenuItem about = new JMenuItem("About");
		help.add(help2);
		help.add(about);
		
		//Speed Menu
		JMenu speedMenu = new JMenu("Speed");
		edit.add(speedMenu);
		
		JMenuItem speedUp = new JMenuItem("Speed Up");
		JMenuItem speedDown = new JMenuItem("Slow Down");
		
		class SpeedUpListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				ball.setDX(ball.getDX()*2);
				ball.setDY(ball.getDY()*2);
			}
		}
		ActionListener speedUpAction = new SpeedUpListener();
		speedUp.addActionListener(speedUpAction);
		speedMenu.add(speedUp);
		
		class SpeedDownListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				if (ball.getDX() >= 1) {
				ball.setDX(ball.getDX()/2);
				ball.setDY(ball.getDY()/2);
				}else {
					JOptionPane.showMessageDialog(null, "The ball cannot go any slower", "ERROR", 0);
					
				}
			}
		}
		ActionListener speedDownAction = new SpeedDownListener();
		speedDown.addActionListener(speedDownAction);
		speedMenu.add(speedDown);
		
		//Color Menu
		JMenu colorMenu = new JMenu("Color");
		edit.add(colorMenu);
			
		JMenuItem redMenu = new JMenuItem("Red");
		redMenu.addActionListener(redListener);
		colorMenu.add(redMenu);
		
		JMenuItem greenMenu = new JMenuItem("Green");
		greenMenu.addActionListener(greenListener);
		colorMenu.add(greenMenu);
		
		JMenuItem blueMenu = new JMenuItem("Blue");
		blueMenu.addActionListener(blueListener);
		colorMenu.add(blueMenu);
		
		JMenuItem randomMenu = new JMenuItem("Random");
		randomMenu.addActionListener(color);
		colorMenu.add(randomMenu);

		//Exit Menu Action
		class ExitListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				System.exit(frame.EXIT_ON_CLOSE); //Closes the program
			}
		}
		ActionListener exitAction = new ExitListener();
		exit.addActionListener(exitAction);
		
		//About Menu Action
		class AboutListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(about, "Name: Ryan Doughty\nBouncing Ball Project (Assignment 3)\nDue Date: 5/7/2019\nMr. Ritter", "About", frame.EXIT_ON_CLOSE); //I used this version of JOption Pane so that I could have a title 
			}
		}
		ActionListener aboutListener = new AboutListener();
		about.addActionListener(aboutListener);
		
		//Help Menu Action
		class HelpListener implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(help, "This program... _________", "Help", frame.EXIT_ON_CLOSE);
			}
		}
		ActionListener helpListener = new HelpListener();
		help2.addActionListener(helpListener);
		
		
		buttonPanel.add(red);
		buttonPanel.add(blue);
		buttonPanel.add(green);
		buttonPanel.add(random);
		buttonPanel.add(musicButton);
			
		frame.addMouseListener(ballsizeclicker);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.add(ball);
			
		frame.setVisible(true);
	}
	
	
	//Mr Ritter: This is completely unnecessary, but was totally worth it
	public static void playSound(String filepath) {
		try {
			File musicPath = new File(filepath); //Get the music file
			if (musicPath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath); //built in java class to convert the file into playable audio
				Clip clip = AudioSystem.getClip();//allows audio input to be playable
				clip.open(audioInput);
				clip.start();
		}else {
			System.out.println("Can't find file");
		}	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	
}

