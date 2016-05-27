import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.prefs.Preferences;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;

public class App extends JFrame implements MouseListener {

	private JMenuBar menuBar;
	private JMenuItem sub_pause;
	private JLabel lbl_win, lbl_fail, lbl_time;
	private Date date;
	private JPanel gamePanel;
	private Font fontHeader = new Font("Courier New", Font.BOLD, 20);
	private Font fontTimer = new Font("Courier New", Font.ITALIC, 12);

	private ArrayList<Ball> balls = new ArrayList<Ball>();
	private Basket basket;

	private Thread t_basket;
	private Thread t_ball;

	private Timer timer;
	private int time = 30;
	private long gameStart = 0;
	private long gameEnd = 0;

	// user preferences
	Preferences userPref = Preferences.userRoot().node("~/BallCatchingGame/root");

	private static final int WINDOW_WIDTH = 1280;
	private static final int WINDOW_HEIGHT = 720;

	private volatile boolean isRunning = false;

	// this flag is for incrementing label win for once
	private boolean flag = true;

	public static void main(String[] args) {
		new App();
	}

	public App() {
		setLayout(null);

		// listener
		addMouseListener(this);

		// set the window width and height
		userPref.putInt("WINDOW_WIDTH", WINDOW_WIDTH);
		userPref.putInt("WINDOW_HEIGHT", WINDOW_HEIGHT);

		// win fail panel
		JPanel panel_winFail = new JPanel();

		// Win - Fail labels
		JLabel lbl_w = new JLabel("W: ");
		JLabel lbl_f = new JLabel("F: ");

		lbl_w.setFont(fontHeader);
		lbl_f.setFont(fontHeader);

		lbl_win = new JLabel("0");
		lbl_fail = new JLabel("0");
		lbl_time = new JLabel("time");

		lbl_win.setFont(fontHeader);
		lbl_fail.setFont(fontHeader);
		lbl_time.setFont(fontTimer);

		configureJLabelColor(lbl_w);
		configureJLabelColor(lbl_win);
		configureJLabelColor(lbl_f);
		configureJLabelColor(lbl_fail);
		configureJLabelColor(lbl_time);

		panel_winFail.add(lbl_w);
		panel_winFail.add(lbl_win);
		panel_winFail.add(lbl_f);
		panel_winFail.add(lbl_fail);
		panel_winFail.add(lbl_time);

		panel_winFail.setBounds(0, 0, WINDOW_WIDTH, 30);
		configurePanelBackgroundColor(panel_winFail);
		add(panel_winFail);

		/*
		 * Border border = comp.getBorder(); Border margin = new
		 * EmptyBorder(10,10,10,10); comp.setBorder(new CompoundBorder(border,
		 * margin));
		 */

		// game timer
		// int time = userPref.getInt("PERIOD_OF_GAME", 30);
		lbl_time.setText(time + "");

		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				time = Integer.parseInt(lbl_time.getText());
				if (time > 0) {
					time--;
					lbl_time.setText(time + "");
				} else {
					stopGame();

					// fail counter
					int failCount = Integer.parseInt(lbl_fail.getText());
					lbl_fail.setText(++failCount + "");

					int restartGame = JOptionPane.showConfirmDialog(null, "Time is over!! Do you want to play more?",
							"Time up", JOptionPane.YES_NO_OPTION);
					if (restartGame == JOptionPane.YES_OPTION) {
						lbl_time.setText(userPref.getInt("PERIOD_OF_GAME", 30) + "");
						startGame();
					}
				}
			}
		});
		timer.start();

		// configure game panel
		configureGamePanel();

		// start game
		startGame();

		// initializing menu bar
		initializeMenuBar();

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

		JMenuItem sub_start = new JMenuItem("Start New Game");
		sub_start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startGame();
			}
		});
		mItem_game.add(sub_start);

		sub_pause = new JMenuItem("Pause");
		sub_pause.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pauseGame();
			}
		});
		mItem_game.add(sub_pause);

		JMenuItem sub_stop = new JMenuItem("Stop");
		sub_stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				stopGame();
			}
		});
		mItem_game.add(sub_stop);

		JMenuItem sub_reset = new JMenuItem("Reset");
		sub_reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				resetGame();
			}
		});
		mItem_game.add(sub_reset);

		menu.addSeparator();

		// About tab
		JMenu about = new JMenu("About");

		JMenuItem mItem_help = new JMenuItem("Help");
		mItem_help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Welcome to Ball Catching Game\nPlaying instructions: Click the balls when they align with the basket\nAim of the game: Destroy all the balls within the given time interval",
						"Help Center", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		about.add(mItem_help);

		JMenuItem mItem_report = new JMenuItem("Report");
		mItem_report.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showGameReport();
			}
		});
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
		gamePanel = new JPanel();
		gamePanel.setLayout(null);

		gamePanel.setBounds(0, 31, WINDOW_WIDTH, 660);
		configurePanelBackgroundColor(gamePanel);
		add(gamePanel);

	}

	/**
	 * This method initializes the balls and starts the game
	 */
	private void startGame() {
		// for the win counter
		flag = true;

		// reset the game
		resetGame();

		// start the countdown
		timer.start();

		// starting the millisecond timer
		gameStart = System.currentTimeMillis();

		lbl_time.setText(userPref.getInt("PERIOD_OF_GAME", 30) + "");

		// === Basket ===
		basket = new Basket(0, 0, 150, 30);
		gamePanel.add(basket);

		basket.setXPos((WINDOW_WIDTH / 2) - (basket.getWidth() / 2));
		basket.setYPos(WINDOW_HEIGHT - 120);

		// Thread for Basket
		t_basket = new Thread(basket);
		t_basket.start();

		// === Balls ===
		Random rnd = new Random();

		int ballCount = userPref.getInt("NUMBER_OF_BALLS", 5);

		for (int i = 0; i < ballCount; i++) {
			float r = rnd.nextFloat();
			float g = rnd.nextFloat();
			float b = rnd.nextFloat();

			int ballSize = userPref.getInt("SIZE_OF_BALLS", 50);

			balls.add(new Ball(0, 0, ballSize, new Color(r, g, b)));

			gamePanel.add(balls.get(i));

			balls.get(i).setXPos(rnd.nextInt(WINDOW_WIDTH));
			balls.get(i).setYPos(rnd.nextInt(400));
		}

		// Threads for Balls
		for (Ball ball : balls) {
			t_ball = new Thread(ball);
			ball.t = t_ball;
			t_ball.start();
		}
	}

	/**
	 * This method resets the game
	 */
	private void resetGame() {
		timer.stop();

		gamePanel.removeAll();
		gamePanel.repaint();

		// removing current Ball objects from the arraylist
		balls.removeAll(balls);

		// stopping all threads
		isRunning = false;
	}

	/**
	 * This method pauses the game
	 */
	private void pauseGame() {
		timer.stop();

		isRunning = !isRunning;
		
		for (Ball ball : balls) {
			ball.t.stop();
		}
/*
		if (isRunning) {
			sub_pause.setText("Pause");
			t_basket.resume();
		} else {
			t_basket.stop();
			// t_ball.interrupt();
			sub_pause.setText("Resume");
		}
		*/
		t_ball.stop();

	}

	/**
	 * This method stops the game
	 */
	public void stopGame() {
		timer.stop();

		for (Ball ball : balls) {
			ball.t.stop();
		}
		t_basket.stop();

		isRunning = false;
	}

	/**
	 * Helper method for configuring the JLabel colors
	 * 
	 * @param label
	 */
	private void configureJLabelColor(JLabel label) {
		label.setForeground(new Color(149, 165, 166));
	}

	/**
	 * Helper method for configuring the JPanel background colors
	 * 
	 * @param panel
	 */
	private void configurePanelBackgroundColor(JPanel panel) {
		panel.setBackground(new Color(22, 25, 25));
	}

	/**
	 * Prompts user to exit
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
	 * This method initializes the game window
	 */
	private void initializeGameWindow() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setTitle("Ball Catching Game | 112200036");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(22, 25, 25));
		this.setVisible(true);
	}

	/**
	 * This method triggered by the menu item "Report" It shows some information
	 * about the game
	 */
	private void showGameReport() {
		String username = userPref.get("USER_NAME", "guest");
		String period = userPref.getInt("PERIOD_OF_GAME", 30) + "";
		String ballCount = userPref.getInt("NUMBER_OF_BALLS", 5) + "";
		String ballSpeed = userPref.getInt("SPEED_OF_BALLS", 2) + "";
		String basketSpeed = userPref.getInt("SPEED_OF_BASKET", 2) + "";
		String ballSize = userPref.getInt("SIZE_OF_BALLS", 50) + "";
		String win = lbl_win.getText();
		String fail = lbl_fail.getText();
		String timeSpend = "";
		if (gameEnd != 0) {
			int seconds = (int) ((gameEnd - gameStart) / 1000) % 60;
			timeSpend += seconds;
		}

		Object[][] rows = { { username, period, ballCount, ballSpeed, basketSpeed, ballSize, win, fail, timeSpend } };
		Object[] cols = { "Username", "Period(sec)", "Ball Count", "Ball Speed", "Basket Speed", "Ball Size", "Win",
				"Fail", "Time Passed(sec)" };

		JTable table = new JTable(rows, cols);
		JScrollPane jsp = new JScrollPane(table);
		table.setFont(new Font("Sans serif", Font.PLAIN, 16));
		jsp.setPreferredSize(new Dimension(1100, 50));
		JOptionPane.showMessageDialog(null, jsp, "Game Report", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * This method triggered by the end of the game If the player is successful
	 * s/he sees this pop up
	 */
	private void showEndGameReport() {
		String username = userPref.get("USER_NAME", "guest");
		String period = userPref.getInt("PERIOD_OF_GAME", 30) + "";
		String ballCount = userPref.getInt("NUMBER_OF_BALLS", 5) + "";
		String ballSpeed = userPref.getInt("SPEED_OF_BALLS", 2) + "";
		String basketSpeed = userPref.getInt("SPEED_OF_BASKET", 2) + "";
		String ballSize = userPref.getInt("SIZE_OF_BALLS", 50) + "";
		String win = lbl_win.getText();
		String fail = lbl_fail.getText();
		String timeSpend = "";
		if (gameEnd != 0) {
			int seconds = (int) ((gameEnd - gameStart) / 1000) % 60;
			timeSpend += seconds;
		}

		Object[][] rows = { { username, period, ballCount, ballSpeed, basketSpeed, ballSize, win, fail, timeSpend } };
		Object[] cols = { "Username", "Period(sec)", "Ball Count", "Ball Speed", "Basket Speed", "Ball Size", "Win",
				"Fail", "Time Passed(sec)" };

		JTable table = new JTable(rows, cols);
		JScrollPane jsp = new JScrollPane(table);
		table.setFont(new Font("Sans serif", Font.PLAIN, 16));
		jsp.setPreferredSize(new Dimension(1100, 50));

		// ask player to play again
		int reply = JOptionPane.showConfirmDialog(null, jsp, "Do you want to play again?", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			startGame();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int numberOfBalls = userPref.getInt("NUMBER_OF_BALLS", 5);
		int basketXPos = basket.getX();

		for (Ball ball : balls) {
			int ballXPos = ball.getX();
			int ballYPos = ball.getY();
			// is the ball vertically aligns with the basket
			if (ballXPos > basketXPos && (ballXPos - ball.getRadius()) < basketXPos + basket.getWidth()) {
				// is the balls y point is matches with clicks y point
				if ((e.getPoint().y - 30) >= ballYPos && (e.getPoint().y - 30) <= ballYPos + (ball.getRadius())) {
					if (e.getPoint().x >= ballXPos && e.getPoint().x <= (ballXPos + ball.getRadius())) {
						ball.setVisible(false);
					}
				}
			}
		}

		// Unvisible ball count
		int count = 0;
		for (Ball ball : balls) {
			if (!ball.isVisible()) {
				count++;
			}
		}

		// if all the balls are destroyed finish the game
		if (count == numberOfBalls) {
			gameEnd = System.currentTimeMillis();
			stopGame();
			// report and play again option
			showEndGameReport();
		}

		// win counter
		if (flag) {
			int winCount = Integer.parseInt(lbl_win.getText());
			lbl_win.setText(++winCount + "");
			flag = false;

			// TODO win labeli 1 arttırma olayı
			// TODO pause stop olayları
		}
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
}
