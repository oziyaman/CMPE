import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class App extends JFrame implements Runnable {
	
	int xPos = 0;

	private JMenuBar menuBar;
	private JLabel lbl_win, lbl_fail;
	private Date date;

	private BufferedImage img_ball = null;
	private BufferedImage img_basket = null;

	private JPanel gamePanel;

	private Font fontHeader = new Font("Courier New", Font.BOLD, 20);

	public static void main(String[] args) {
		//new App();
		
		Thread ball1 = new Thread(new App());
		ball1.start();
	}

	public App() {
		setLayout(null);

		// win fail panel
		JPanel panel_winFail = new JPanel();

		// Win - Fail labels
		JLabel lbl_w = new JLabel("W: ");
		JLabel lbl_f = new JLabel("F: ");

		lbl_w.setFont(fontHeader);
		lbl_f.setFont(fontHeader);

		lbl_win = new JLabel("0");
		lbl_fail = new JLabel("0");

		lbl_win.setFont(fontHeader);
		lbl_fail.setFont(fontHeader);

		configureJLabelColor(lbl_w);
		configureJLabelColor(lbl_win);
		configureJLabelColor(lbl_f);
		configureJLabelColor(lbl_fail);

		panel_winFail.add(lbl_w);
		panel_winFail.add(lbl_win);
		panel_winFail.add(lbl_f);
		panel_winFail.add(lbl_fail);

		panel_winFail.setBounds(0, 0, 1280, 30);
		// configurePanelBackgroundColor(panel_winFail);
		add(panel_winFail);

		// configure game panel
		configureGamePanel();

		// initializing menu bar
		initializeMenuBar();

		// loading images
		loadImages();

		// initializing our game
		initializeGameWindow();
	}

	private void initializeMenuBar() {
		menuBar = new JMenuBar();

		// Menu tab
		JMenu menu = new JMenu("Menu");

		JMenuItem mItem_settings = new JMenuItem("Settings");
		mItem_settings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AppSettings();
			}
		});
		menu.add(mItem_settings);

		menu.addSeparator();

		JMenu mItem_game = new JMenu("Game");
		menu.add(mItem_game);

		JMenuItem sub_start = new JMenuItem("Start");
		mItem_game.add(sub_start);

		JMenuItem sub_pause = new JMenuItem("Pause");
		mItem_game.add(sub_pause);

		JMenuItem sub_stop = new JMenuItem("Stop");
		mItem_game.add(sub_stop);

		JMenuItem sub_reset = new JMenuItem("Reset");
		mItem_game.add(sub_reset);

		menu.addSeparator();

		// About tab
		JMenu about = new JMenu("About");

		JMenuItem mItem_help = new JMenuItem("Help");
		about.add(mItem_help);

		JMenuItem mItem_report = new JMenuItem("Report");
		about.add(mItem_report);

		JMenuItem mItem_exit = new JMenuItem("Exit");
		mItem_exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exit();

			}
		});
		menu.add(mItem_exit);

		menuBar.add(menu);
		menuBar.add(about);

		// Date & Time
		JLabel dateTime = new JLabel("");

		new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				date = new Date();

				SimpleDateFormat sdf_date = new SimpleDateFormat("dd.MM.yyyy");
				SimpleDateFormat sdf_hour = new SimpleDateFormat("HH:mm:ss");

				dateTime.setText(sdf_date.format(date) + " - " + sdf_hour.format(date) + "  ");
			}
		}).start();

		// right align
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(dateTime);

		this.setJMenuBar(menuBar);
	}

	private void configureGamePanel() {
		gamePanel = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img_ball, xPos, 0, null);
			}
		};
		
		gamePanel.setBounds(0,31,1280,50);
		//configurePanelBackgroundColor(gamePanel);
		add(gamePanel);

		
	}

	private void loadImages() {
		try {
			img_ball = ImageIO.read(new File("assets/ball.png"));
			img_basket = ImageIO.read(new File("assets/basket.gif"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "ball.png or basket.png cannot loaded!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void configureJLabelColor(JLabel label) {
		label.setForeground(new Color(149, 165, 166));
	}

	private void configurePanelBackgroundColor(JPanel panel) {
		panel.setBackground(new Color(22, 25, 25));
	}

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

	private void initializeGameWindow() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1280, 720);
		this.setTitle("Awesome Game");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(22, 25, 25));
		this.setVisible(true);
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			xPos += 10;
			repaint();
			System.out.println(i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
