import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

public class Ball extends JComponent{
	
	int x, y;
	int dx, dy; //I made these a parameter of the ball so that I could easily use it wherever
	int size;
	Color fill;
	Ellipse2D.Double ball;
	
	public Ball(int x, int y, int size, Color fill, int dx, int dy) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.fill = fill;
		this.dx = dx;
		this.dy = dy;
		
		ball = new Ellipse2D.Double(x, y, size, size);
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.black);
		g2.draw(ball);
		g2.setColor(fill);
		g2.fill(ball);
		g2.dispose();
	
	}
	//Getter methods
	public int getSizeBall() {
		return size;	
	}
	public int getDX() {
		return dx;
	}
	public int getDY() {
		return dy;
	}

	//Setter Methods
	public void setBall(int x, int y, int size) { //I used this as a setter for the x y and size of the ball
		this.size = size;
		this.x = x;
		this.y = y;
		ball.setFrame(x, y, size, size);
		setSize(size, size);
		setLocation(x, y);
		System.out.println("NEW LOCATION: (" + x + " , " + y + ")");
		System.out.println("NEW SIZE: " + size);
		revalidate(); //This is a built in method that checks the variables or something, I do not quite understand what it does, but it was supposed to help
		repaint();
	}

	public void setFill(Color fill) {
		this.fill = fill;
		repaint();
	}
	
	public void setDX(int dx) {
		this.dx = dx;
	}
	public void setDY(int dy) {
		this.dy = dy;
	}
}
