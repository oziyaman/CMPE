import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.prefs.Preferences;

import javax.swing.JPanel;

public class Basket extends JPanel implements Runnable {

	private int width;
	private int height;
	private Color color = new Color(231, 76, 60);

	private int xPos;
	private int yPos;
	
	// user preferences
	Preferences userPref = Preferences.userRoot().node("~/BallCatchingGame/root");

	/**
	 * Constructor of Basket class
	 */
	public Basket(int xPos, int yPos, int width, int height) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;

		setBackground(color);
		setSize(150, 50);

	}

	public int getXPos() {
		Rectangle bound = getBounds();
		return bound.x;
	}

	public int getYPos() {
		Rectangle bound = getBounds();
		return bound.y;
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
	
	public int getWidth(){
		return width;
	}

	@Override
	protected void paintComponent(Graphics g) {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(color);
		g.fillRect(xPos, yPos, width, height);
	}

	@Override
	public void run() {
		// Movement direction
		String direction = "right";
		int basketPosition = getXPos();

		while (true) {
			if (direction.equals("right")) {
				basketPosition += 1;
				setXPos(basketPosition);

				if (basketPosition >= userPref.getInt("WINDOW_WIDTH", 1280) - getWidth()) {
					direction = "left";
				}
			}

			if (direction.equals("left")) {
				basketPosition -= 1;
				setXPos(basketPosition);

				if (basketPosition <= 0) {
					direction = "right";
				}
			}

			try {
				int speedOfBasket = Math.abs(userPref.getInt("SPEED_OF_BASKET", 2) - 7);
				// 7 because I want speed difference of basket and balls
				// in order to catch them
				Thread.sleep(speedOfBasket);
			} catch (Exception e) {
			}
		}

	}
}
