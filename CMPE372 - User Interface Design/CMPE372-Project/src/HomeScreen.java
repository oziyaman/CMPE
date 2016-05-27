import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HomeScreen extends JFrame {

	private BufferedImage logo = null;

	private JButton btn_newGame, btn_settings, btn_exit;

	// user preferences
	Preferences userPref = Preferences.userRoot().node("~/BallCatchingGame/root");

	public static void main(String[] args) {
		new HomeScreen();
	}

	public HomeScreen() {
		setLayout(null);
		
		// default game settings
		userPref.put("USER_NAME", "guest");
		userPref.putInt("PERIOD_OF_GAME", 30);
		userPref.putInt("NUMBER_OF_BALLS", 5);
		userPref.putInt("SPEED_OF_BALLS", 2);
		userPref.putInt("SPEED_OF_BASKET", 2);
		userPref.putInt("SIZE_OF_BALLS", 50);

		// load the logo
		loadLogo();

		JPanel panel_logo = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(logo, 0, 0, null);
			}
		};

		panel_logo.setSize(400, 150);
		add(panel_logo);

		btn_newGame = new JButton("New Game");
		btn_newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new App();
				dispose();
			}
		});

		btn_settings = new JButton("Settings");
		btn_settings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AppSettings();

			}
		});

		btn_exit = new JButton("Exit");
		btn_exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exit();

			}
		});

		// configure buttons
		configureButtons(btn_newGame);
		configureButtons(btn_settings);
		configureButtons(btn_exit);

		btn_newGame.setBounds(75, 190, 250, 50);
		add(btn_newGame);

		btn_settings.setBounds(75, 270, 250, 50);
		add(btn_settings);

		btn_exit.setBounds(75, 350, 250, 50);
		add(btn_exit);

		// initialize the window
		initializeHomeScreen();
	}

	/**
	 * Screen initializer method
	 */
	private void initializeHomeScreen() {
		// this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400, 500);
		this.setTitle("Ball Catching Game");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(22, 25, 25));
		this.setVisible(true);
	}

	/**
	 * This method creates a customized button
	 * 
	 * @param btn
	 * @return
	 */
	public void configureButtons(JButton btn) {
		Font font = new Font("Sans serif", Font.BOLD, 24);
		btn.setFont(font);
		btn.setBackground(new Color(39, 174, 96));
		btn.setBorderPainted(false);

		btn.setFocusable(false);
	}

	/**
	 * This method asks user to exit
	 */
	private void exit() {
		// ask user to exit
		int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit",
				JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			// 0 = close the app
			// -1 = close with error
			System.exit(0);
		}
	}

	/**
	 * This method loads the logo.png
	 */
	private void loadLogo() {
		try {
			logo = ImageIO.read(new File("assets/logo.png"));
		} catch (IOException e) {
			System.out.println("Error while loading the logo " + e);
		}
	}

}
