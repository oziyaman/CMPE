import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class App extends JFrame{
	
	private JMenuBar menuBar;
	private JLabel lbl_win, lbl_fail;
	private JLabel lbl_time, lbl_date;
	
	public static void main(String[] args) {
		new App();
	}
	
	public App(){
		setLayout(new FlowLayout());
		
		// Win - Fail labels
		JLabel lbl_w = new JLabel("Win: ");
		JLabel lbl_f = new JLabel("Fail: ");
		
		lbl_win = new JLabel("0");
		lbl_fail = new JLabel("0");
		lbl_time = new JLabel("-");
		lbl_date = new JLabel("");
		
		Date date = new Date();
		SimpleDateFormat sdf_date = new SimpleDateFormat("dd.MM.yyyy");
		
		lbl_date.setText(sdf_date.format(date));
		
		SimpleDateFormat sdf_hour = new SimpleDateFormat("HH:mm:ss");
		
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			
			@Override
			public void run() {
				lbl_time.setText(sdf_hour.format(date));
			}
		};
		
		timer.schedule(timerTask, 1000);
		
		
		
		
		add(lbl_w);
		add(lbl_win);
		add(lbl_f);
		add(lbl_fail);
		add(lbl_time);
		add(lbl_date);
		
		
		
		
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
		menu.add(mItem_exit);
		
		menuBar.add(menu);
		menuBar.add(about);
		
		this.setJMenuBar(menuBar);
	}

	private void initializeGameWindow() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500,500);
		this.setTitle("Awesome Game");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(174, 198, 207));
		this.setVisible(true);
	}
	
	
	
	
}
