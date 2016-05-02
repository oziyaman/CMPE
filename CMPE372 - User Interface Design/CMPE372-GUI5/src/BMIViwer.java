import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BMIViwer extends JFrame implements KeyListener {

	private String bmi;
	private int xPos = 135;
	private int yPos = 10; // fixed
	private int velocity = 1;
	private JPanel imagePanel;

	private BufferedImage img = null;

	// source images must be on the desktop folder

	public static void main(String[] args) {

	}

	public BMIViwer(String bmi) {
		setLayout(new BorderLayout());

		this.bmi = bmi;
		double result = Double.parseDouble(bmi);

		if (result <= 18.5) {
			loadImage("underweight.png");

			imagePanel = new JPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawImage(img, xPos, yPos, null);
				}
			};
		} else if (result > 18.5 && result <= 24.9) {
			loadImage("normal.png");

			imagePanel = new JPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawImage(img, xPos, yPos, null);
				}
			};
		} else if (result > 24.9 && result <= 29.9) {
			loadImage("overweight.png");

			imagePanel = new JPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawImage(img, xPos, yPos, null);
				}
			};
		} else if (result > 30) {
			loadImage("obesity.png");

			imagePanel = new JPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawImage(img, xPos, yPos, null);
				}
			};
		}

		JButton close = new JButton("Close");
		close.setFocusable(false);
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		add(imagePanel, BorderLayout.CENTER);
		add(close, BorderLayout.SOUTH);

		// adding keylisteners
		addKeyListener(this);

		// initialize window
		initializeVisualizerWindow();
	}

	private void initializeVisualizerWindow() {
		this.setTitle("User Profiles | BMI Viewer | 112200036");
		this.setSize(400, 250);
		this.setResizable(false);
		// this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void loadImage(String type) {
		try {
			img = ImageIO.read(new File(type));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Source image does not exist!", "Source Image Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (xPos > 0) {
				xPos -= velocity;
			}

		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (xPos < this.getBounds().width - img.getWidth()) {
				xPos += velocity;
			}

		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
