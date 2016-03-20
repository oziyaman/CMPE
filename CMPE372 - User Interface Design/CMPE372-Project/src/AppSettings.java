import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AppSettings extends JFrame{
	
	private JTextField txt_username;
	private JButton btn_save, btn_exit;
	
	public static void main(String[] args) {
		new AppSettings();
	}
	
	public AppSettings(){
		setLayout(new GridLayout(3, 2));
		
		JLabel lbl_username = new JLabel("Username");
		txt_username = new JTextField();
		
		btn_save = new JButton("Save");
		btn_exit = new JButton("Exit");
		btn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		add(lbl_username);
		add(txt_username);
		
		add(btn_save);
		add(btn_exit);
		
		initializeSettingsWindow();
	}
	
	private void initializeSettingsWindow() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(300,300);
		this.setTitle("Game Settings");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(119, 158, 203));
		//setting the window to borderless
		this.setUndecorated(true);
		this.setVisible(true);
	}
}
