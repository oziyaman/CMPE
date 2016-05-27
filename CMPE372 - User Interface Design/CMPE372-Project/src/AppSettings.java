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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class AppSettings extends JFrame {

	private BufferedImage logo = null;

	private JTextField txt_username;
	private JButton btn_save, btn_exit;
	private JSlider sldr_numberOfBalls, sldr_periodOfGame, sldr_speedOfBalls, sldr_speedOfBasket, sldr_sizeOfBalls;

	// user preferences
	Preferences userPref = Preferences.userRoot().node("~/BallCatchingGame/root");

	private Font font = new Font("Sans serif", Font.PLAIN, 18);

	public static void main(String[] args) {
		//new AppSettings();
	}

	public AppSettings() {
		setLayout(null);

		loadLogo();
		JPanel panel_logo = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(logo, 0, 0, null);
			}
		};

		panel_logo.setSize(400, 150);
		add(panel_logo);

		JPanel row1 = new JPanel();
		JPanel row2 = new JPanel();
		JPanel row3 = new JPanel();
		JPanel row4 = new JPanel();
		JPanel row5 = new JPanel();
		JPanel row6 = new JPanel();

		txt_username = new JTextField(10);
		txt_username.setFont(font);
		txt_username.setHorizontalAlignment(JTextField.CENTER);

		JLabel lbl_username = new JLabel("Username");
		JLabel lbl_numberOfBalls = new JLabel("Number of balls");
		JLabel lbl_periodOfGame = new JLabel("Period of game");
		JLabel lbl_speedOfBalls = new JLabel("Speed of balls");
		JLabel lbl_speedOfBasket = new JLabel("Speed of basket");
		JLabel lbl_sizeOfBalls = new JLabel("Size of balls");

		lbl_username.setFont(font);
		lbl_numberOfBalls.setFont(font);
		lbl_periodOfGame.setFont(font);
		lbl_speedOfBalls.setFont(font);
		lbl_speedOfBasket.setFont(font);
		lbl_sizeOfBalls.setFont(font);

		configureLabelForegroundColor(lbl_username);
		configureLabelForegroundColor(lbl_periodOfGame);
		configureLabelForegroundColor(lbl_numberOfBalls);
		configureLabelForegroundColor(lbl_speedOfBalls);
		configureLabelForegroundColor(lbl_speedOfBasket);
		configureLabelForegroundColor(lbl_sizeOfBalls);

		sldr_numberOfBalls = new JSlider(JSlider.HORIZONTAL, 3, 10, 5); // min
																		// max
																		// initial
		sldr_periodOfGame = new JSlider(JSlider.HORIZONTAL, 30, 60, 30);
		sldr_speedOfBalls = new JSlider(JSlider.HORIZONTAL, 1, 5, 2);
		sldr_speedOfBasket = new JSlider(JSlider.HORIZONTAL, 1, 5, 2);
		sldr_sizeOfBalls = new JSlider(JSlider.HORIZONTAL, 25, 100, 50);

		configureBackgroundColorOfJSlider(sldr_numberOfBalls);
		configureBackgroundColorOfJSlider(sldr_periodOfGame);
		configureBackgroundColorOfJSlider(sldr_speedOfBalls);
		configureBackgroundColorOfJSlider(sldr_speedOfBasket);
		configureBackgroundColorOfJSlider(sldr_sizeOfBalls);

		btn_save = new JButton("Save");
		btn_exit = new JButton("Exit");

		row1.add(lbl_username);
		row1.add(txt_username);
		row1.setLayout(null);
		row1.setBounds(25, 180, 350, 30);
		lbl_username.setBounds(0, 0, 150, 30);
		txt_username.setBounds(150, 0, 200, 30);

		row2.add(lbl_periodOfGame);
		row2.add(sldr_periodOfGame);
		row2.setLayout(null);
		row2.setBounds(25, 220, 350, 30);
		lbl_periodOfGame.setBounds(0, 0, 150, 30);
		sldr_periodOfGame.setBounds(150, 0, 200, 30);

		row3.add(lbl_numberOfBalls);
		row3.add(sldr_numberOfBalls);
		row3.setLayout(null);
		row3.setBounds(25, 260, 350, 30);
		lbl_numberOfBalls.setBounds(0, 0, 150, 30);
		sldr_numberOfBalls.setBounds(150, 0, 200, 30);

		row4.add(lbl_speedOfBalls);
		row4.add(sldr_speedOfBalls);
		row4.setLayout(null);
		row4.setBounds(25, 300, 350, 30);
		lbl_speedOfBalls.setBounds(0, 0, 150, 30);
		sldr_speedOfBalls.setBounds(150, 0, 200, 30);

		row5.add(lbl_speedOfBasket);
		row5.add(sldr_speedOfBasket);
		row5.setLayout(null);
		row5.setBounds(25, 340, 350, 30);
		lbl_speedOfBasket.setBounds(0, 0, 150, 30);
		sldr_speedOfBasket.setBounds(150, 0, 200, 30);
		
		row6.add(lbl_sizeOfBalls);
		row6.add(sldr_sizeOfBalls);
		row6.setLayout(null);
		row6.setBounds(25, 380, 350, 30);
		lbl_sizeOfBalls.setBounds(0, 0, 150, 30);
		sldr_sizeOfBalls.setBounds(150, 0, 200, 30);

		configureBackgroundColorOfJPanel(row1);
		configureBackgroundColorOfJPanel(row2);
		configureBackgroundColorOfJPanel(row3);
		configureBackgroundColorOfJPanel(row4);
		configureBackgroundColorOfJPanel(row5);
		configureBackgroundColorOfJPanel(row6);

		add(row1);
		add(row2);
		add(row3);
		add(row4);
		add(row5);
		add(row6);

		btn_save = new JButton("Save");
		btn_exit = new JButton("Exit");

		// button onclick events handling
		buttonClicks();

		configureButtons(btn_save);
		configureButtons(btn_exit);

		btn_save.setBounds(50, 430, 150, 50);
		btn_save.setBackground(new Color(46, 204, 113));
		add(btn_save);

		btn_exit.setBounds(200, 430, 150, 50);
		btn_exit.setBackground(new Color(231, 76, 60));
		add(btn_exit);

		// loading default settings
		txt_username.setText("" + userPref.get("USER_NAME", "guest").toString()); // variable,
																					// if
																					// variable
																					// empty
		sldr_periodOfGame.setValue(userPref.getInt("PERIOD_OF_GAME", 3));
		sldr_numberOfBalls.setValue(userPref.getInt("NUMBER_OF_BALLS", 5));
		sldr_speedOfBalls.setValue(userPref.getInt("SPEED_OF_BALLS", 2));
		sldr_speedOfBasket.setValue(userPref.getInt("SPEED_OF_BASKET", 2));
		sldr_sizeOfBalls.setValue(userPref.getInt("SIZE_OF_BALLS", 50));

		// initialize window
		initializeSettingsScreen();
	}

	/**
	 * Screen initializer method
	 */
	private void initializeSettingsScreen() {
		this.setSize(400, 520);
		this.setTitle("Game Settings");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(22, 25, 25));
		this.setVisible(true);
	}

	private void buttonClicks() {
		btn_save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// ask user to save
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to save?", "Save",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					userPref.put("USER_NAME", txt_username.getText().toString());
					userPref.putInt("PERIOD_OF_GAME", sldr_periodOfGame.getValue());
					userPref.putInt("NUMBER_OF_BALLS", sldr_numberOfBalls.getValue());
					userPref.putInt("SPEED_OF_BALLS", sldr_speedOfBalls.getValue());
					userPref.putInt("SPEED_OF_BASKET", sldr_speedOfBasket.getValue());
					userPref.putInt("SIZE_OF_BALLS", sldr_sizeOfBalls.getValue());

					dispose();
				}
			}
		});

		btn_exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
	}

	private void configureBackgroundColorOfJPanel(JPanel panel) {
		panel.setBackground(new Color(22, 25, 25));
	}

	private void configureBackgroundColorOfJSlider(JSlider slider) {
		slider.setBackground(new Color(22, 25, 25));
	}

	private void configureLabelForegroundColor(JLabel label) {
		label.setForeground(new Color(189, 195, 199));
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
		// btn.setBackground(new Color(39, 174, 96));
		btn.setBorderPainted(false);

		btn.setFocusable(false);
	}

	/**
	 * This method loads the logo.png
	 */
	private void loadLogo() {
		try {
			logo = ImageIO.read(new File("assets/settings.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "logo.png cannot loaded!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
