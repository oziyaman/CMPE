import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class AppSettingsOld extends JFrame {

	private JTextField txt_username;
	private JButton btn_save, btn_exit;
	private JSlider sldr_numberOfBalls, sldr_periodOfGame, sldr_speedOfBalls, sldr_speedOfBasket;

	public static void main(String[] args) {
		new AppSettingsOld();
	}

	public AppSettingsOld() {
		setLayout(new GridLayout(6, 2));

		JLabel lbl_username = new JLabel("Username");
		JLabel lbl_numberOfBalls = new JLabel("Number of balls");
		JLabel lbl_periodOfGame = new JLabel("Period of game");
		JLabel lbl_speedOfBalls = new JLabel("Speed of balls");
		JLabel lbl_speedOfBasket = new JLabel("Speed of basket");
		
		Font settingsFont = new Font("SansSerif", Font.PLAIN, 20);
		
		lbl_username.setFont(settingsFont);
		lbl_numberOfBalls.setFont(settingsFont);
		lbl_periodOfGame.setFont(settingsFont);
		lbl_speedOfBalls.setFont(settingsFont);
		lbl_speedOfBasket.setFont(settingsFont);
		
		
		sldr_numberOfBalls = new JSlider(JSlider.HORIZONTAL, 3, 10, 5); // min max initial
		sldr_periodOfGame = new JSlider(JSlider.HORIZONTAL, 1, 5, 3);
		sldr_speedOfBalls = new JSlider(JSlider.HORIZONTAL, 10, 100, 30);
		sldr_speedOfBasket = new JSlider(JSlider.HORIZONTAL, 10, 100, 30);
		
		
		
		
		txt_username = new JTextField();
		txt_username.setFont(settingsFont);

		btn_save = new JButton("Save");
		btn_exit = new JButton("Exit");
		
		btn_save.setFont(settingsFont);
		btn_exit.setFont(settingsFont);

		
		
		add(lbl_username);
		add(txt_username);
		add(lbl_numberOfBalls);
		add(sldr_numberOfBalls);
		add(lbl_periodOfGame);
		add(sldr_periodOfGame);
		add(lbl_speedOfBalls);
		add(sldr_speedOfBalls);
		add(lbl_speedOfBasket);
		add(sldr_speedOfBasket);

		add(btn_save);
		add(btn_exit);
		
		// setting the background colors
		setBackgroundColorsOfComponents();

		// initialize buttons action listeners
		initializeButtonActionListeners();
		// initializing settings window
		initializeSettingsScreen();
	}

	public void initializeButtonActionListeners() {
		btn_exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btn_save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void setBackgroundColorsOfComponents(){
		Color pastelBlue = new Color(119, 158, 203);
		
		sldr_numberOfBalls.setBackground(pastelBlue);
		sldr_periodOfGame.setBackground(pastelBlue);
		sldr_speedOfBalls.setBackground(pastelBlue);
		sldr_speedOfBasket.setBackground(pastelBlue);
		
		btn_save.setBackground(new Color(119, 190, 119)); // green
		btn_exit.setBackground(new Color(255, 105, 97)); // red
	}
	
	/**
	 * Screen initializer method
	 */
	private void initializeSettingsScreen() {
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400, 500);
		this.setTitle("Game Settings");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(22, 25, 25));
		this.setUndecorated(true);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}
}
