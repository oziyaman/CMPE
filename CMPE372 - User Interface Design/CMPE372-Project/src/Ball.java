import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.prefs.Preferences;

import javax.swing.JPanel;

public class Ball extends JPanel implements MouseListener, Runnable {

	private int xPos;
	private int yPos;
	private int radius;
	public Thread t;
	
	private Color color;
	
	// user preferences
	Preferences userPref = Preferences.userRoot().node("~/BallCatchingGame/root");

	/**
	 * Constructor of the Ball class
	 * 
	 * @param xPos
	 * @param yPos
	 * @param radius
	 * @param color
	 */
	public Ball(int xPos, int yPos, int radius, Color color) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.radius = radius;
		this.color = color;
		
		setBackground(color);
		setSize(100,100);
		
		addMouseListener(this);
	}
	
	public int getRadius(){
		return radius;
	}
	
	public int getXPos(){
		return xPos;
	}

	public int getYPos(){
		return yPos;
	}
	
	public void setXPos(int xPos) {
		Rectangle bound = getBounds();
		bound.x = xPos;
		setBounds(bound);
	}

	public void setYPos(int yPos) {
		Rectangle bound = getBounds();
		bound.y = yPos;
		setBounds(bound);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(color);
		g.fillOval(xPos, yPos, radius, radius);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//setVisible(false);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		Random rnd = new Random();

		String direction = "right";
		int ballPosition = getX();

		while (true) {
			if (direction.equals("right")) {
				ballPosition += 1;
				setXPos(ballPosition);

				if (ballPosition >= (userPref.getInt("WINDOW_WIDTH", 1280) - getRadius())) {
					direction = "left";
				}
			}

			if (direction.equals("left")) {
				ballPosition -= 1;
				setXPos(ballPosition);

				if (ballPosition <= 0) {
					direction = "right";
				}
			}

			try {
				int speedOfBalls = Math.abs(userPref.getInt("SPEED_OF_BALLS", 2) - 6);
				Thread.sleep(speedOfBalls);
			} catch (InterruptedException e) {}
		}
		
	}
	
	

}
