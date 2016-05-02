import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test extends JFrame{

	BufferedImage img;
	
	public static void main(String[] args) {
		new Test();
	}
	
	public Test(){
		
		
		
		try {
			img = ImageIO.read(new File("/home/furkan/Desktop/adam.png"));
			int width = img.getWidth();
			int height = img.getHeight();
			BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics g = bi.getGraphics();
			
		} catch (IOException e) {
			System.out.println("image error" + e);
		}
		
		JPanel panel = new JPanel(){
			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 100, 90, null);
            }
		
		};
		
		add(panel);
		
		initializeWindow();
	}

	private void initializeWindow() {
		this.setTitle("Test window | BMI Viewer | 112200036");
		this.setSize(800, 800);
		//this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
