import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.prefs.Preferences;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

public class App extends JFrame {

	private JTextField txt_weight, txt_height;
	private JLabel lbl_bmi;
	private JButton btn_calculate, btn_reset, btn_save, btn_exit;

	private JMenuBar menuBar;

	private JRadioButtonMenuItem mItem_guestUser, mItem_userProfiles;

	// user preferences
	Preferences userPref = Preferences.userRoot().node("~/custom/root");

	public static void main(String[] args) {
		new App();
	}

	public App() {
		// reset user preferences
		userPref.remove("USER");

		setLayout(new GridLayout(5, 2));

		JLabel lbl_weight = new JLabel("Weight");
		JLabel lbl_height = new JLabel("Height");
		JLabel lbl_empty = new JLabel("");
		lbl_bmi = new JLabel("");

		txt_weight = new JTextField();
		txt_weight.setToolTipText("Your weight in kilogram (Ex: 70)");
		txt_height = new JTextField();
		txt_height.setToolTipText("Your height in centimeter (Ex: 180)");

		btn_calculate = new JButton("Calculate");
		btn_calculate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				calculate();
			}
		});

		btn_save = new JButton("Save");
		btn_save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});

		btn_reset = new JButton("Reset");
		btn_reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});

		btn_exit = new JButton("Exit");
		btn_exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});

		// menu bar configurations
		menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");

		// menu items
		JMenuItem mItem_calculate = new JMenuItem("Calculate", KeyEvent.VK_C);
		mItem_calculate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				calculate();
			}
		});
		menu.add(mItem_calculate);

		JMenuItem mItem_reset = new JMenuItem("Reset", KeyEvent.VK_R);
		mItem_reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		menu.add(mItem_reset);

		menu.addSeparator();

		JMenuItem mItem_info = new JMenuItem("About BMI", KeyEvent.VK_I);
		mItem_info.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				about();
			}
		});
		menu.add(mItem_info);

		menu.addSeparator();

		JMenuItem mItem_exit = new JMenuItem("Exit", KeyEvent.VK_E);
		mItem_exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exit();

			}
		});
		menu.add(mItem_exit);

		menu.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_C:
					calculate();
					break;
				case KeyEvent.VK_R:
					clear();
					break;
				case KeyEvent.VK_I:
					about();
					break;
				case KeyEvent.VK_E:
					exit();
					break;
				default:
					break;
				}
			}
		});

		JMenu mProfile = new JMenu("Profile");

		mItem_guestUser = new JRadioButtonMenuItem("Guest user", true);
		mItem_guestUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				userPref.remove("USER");

			}
		});

		mItem_userProfiles = new JRadioButtonMenuItem("User profiles");
		mItem_userProfiles.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new UserProfiles();
			}
		});

		ButtonGroup mItem_group = new ButtonGroup();
		mItem_group.add(mItem_guestUser);
		mItem_group.add(mItem_userProfiles);

		mProfile.add(mItem_userProfiles);
		mProfile.add(mItem_guestUser);

		menuBar.add(menu);
		menuBar.add(mProfile);

		add(lbl_weight);
		add(txt_weight);

		add(lbl_height);
		add(txt_height);

		add(lbl_empty);
		add(lbl_bmi);

		add(btn_calculate);
		add(btn_reset);
		add(btn_save);
		add(btn_exit);

		// setting the menu bar of the app
		setJMenuBar(menuBar);

		// configuring the placeholders
		configurePlaceHolders();

		// initializing our app
		initializeApp();
	}

	/**
	 * This method initializes the window with the given parameters
	 */
	public void initializeApp() {
		this.setTitle("BMI Calculator | 112200036");
		this.setSize(450, 180);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * This method configures the placeholders for text areas
	 */
	public void configurePlaceHolders() {
		txt_weight.setText("Your weight in kilogram (Ex: 70)");
		txt_weight.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (txt_weight.getText().toString().trim().equals("")) {
					txt_weight.setText("Your weight in kilogram (Ex: 70)");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (txt_weight.getText().toString().trim().equals("Your weight in kilogram (Ex: 70)")) {
					txt_weight.setText("");
				}
			}
		});

		txt_height.setText("Your height in centimeter (Ex: 180)");
		txt_height.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (txt_height.getText().toString().trim().equals("")) {
					txt_height.setText("Your height in centimeter (Ex: 180)");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (txt_height.getText().toString().trim().equals("Your height in centimeter (Ex: 180)")) {
					txt_height.setText("");
				}
			}
		});
	}

	/**
	 * Body Mass Index Formula Body weight in kilograms divided by height in
	 * meters squared or,
	 * 
	 * BMI = x KG / (y M * y M) where:
	 * 
	 * x=bodyweight in KG y=height in m
	 * 
	 * 
	 * @param weight
	 * @param height
	 * @return bmi
	 */
	public String calculateBMI(int weight, int height) {
		try {
			DecimalFormat df = new DecimalFormat("#.##");
			df.setRoundingMode(RoundingMode.CEILING);
			// dividing 100.0 because we want a double division not an integer
			// division
			double bmi = weight / ((height / 100.0) * (height / 100.0));
			return df.format(bmi);
		} catch (ArithmeticException e) {
			JOptionPane.showMessageDialog(null, "Please enter non-zero and positive numbers!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	/**
	 * This method calculates the BMI by using calculateBMI(weight, height)
	 * method.
	 */
	private void calculate() {
		try {
			int weight = Integer.parseInt(txt_weight.getText().trim());
			int height = Integer.parseInt(txt_height.getText().trim());

			String bmi = calculateBMI(weight, height);

			// if bmi was calculated successfully change the lbl_bmi
			if (bmi != null) {
				lbl_bmi.setText(bmi + "");

				File imageCheck = new File("normal.png");
				if (imageCheck.exists() && !imageCheck.isDirectory()) {
					new BMIViwer(bmi);
				} else {
					JOptionPane.showMessageDialog(null,
							"Something wrong with the source images. BMI viewer will not start!!",
							"Source Image Absent", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		} catch (NumberFormatException e2) {
			JOptionPane.showMessageDialog(this, "Enter legal numbers!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * This method saves the results in a text file for user
	 */
	private void save() {
		// getting the username
		String defaultUser = "guest";
		String user = userPref.get("USER", defaultUser);

		// Creating the users directory
		File usersDir = new File("users");
		if (!usersDir.exists()) {
			usersDir.mkdir();
		}

		String calculatedBMI = lbl_bmi.getText().toString();

		if (!calculatedBMI.matches("") && !calculatedBMI.matches("saved")) {
			File file = new File("users/" + user);
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
				String str = txt_weight.getText().toString() + "\t\t" + txt_height.getText().toString() + "\t\t"
						+ lbl_bmi.getText().toString();

				bw.write(str);
				bw.newLine();

				bw.flush();
				bw.close();
			} catch (Exception e) {
				System.out.println("Error while writing the file: " + e);
			}

			clear();
			lbl_bmi.setText("saved");
		} else {
			JOptionPane.showMessageDialog(this, "Please calculate bmi first!", "Error",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

	/**
	 * This method clears the text fields
	 */
	private void clear() {
		txt_weight.setText("Your weight in kilogram (Ex: 70)");
		txt_height.setText("Your height in centimeter (Ex: 180)");
		lbl_bmi.setText("");
	}

	/**
	 * This method asks user to close the app.
	 */
	private void exit() {
		// ask user to exit
		int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Close",
				JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			// 0 = close the app
			// -1 = close with error
			System.exit(0);
		}
	}

	/**
	 * This method shows a dialog about BMI Categories. User can look at his/her
	 * result information
	 */
	private void about() {
		JOptionPane.showMessageDialog(null,
				"BMI Categories:\nUnderweight = <18.5\nNormal weight = 18.5–24.9\nOverweight = 25–29.9\nObesity = BMI of 30 or greater",
				"About BMI Results", JOptionPane.INFORMATION_MESSAGE);
	}
}
