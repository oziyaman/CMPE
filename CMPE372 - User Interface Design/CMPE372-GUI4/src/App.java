import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class App extends JFrame {

	private JComboBox<File> cb_chooser;
	private JLabel lbl_image;

	private BufferedImage img = null;
	private String photo_path = "";

	/**
	 * Main method which starts the app
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new App();
	}

	/**
	 * Constructor for our app
	 */
	public App() {
		setLayout(new BorderLayout());

		try {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG files only", "jpg", "png");
			chooser.setFileFilter(filter);
			chooser.setMultiSelectionEnabled(true);

			int option = chooser.showOpenDialog(this);
			File files[] = chooser.getSelectedFiles();

			// if selected items are more than 3
			if (files.length >= 3) {
				
				if (option == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooser.getSelectedFile();
					photo_path = (selectedFile.getAbsolutePath());
				} else {
					System.exit(0);
				}
				
				img = ImageIO.read(new File(photo_path));
				cb_chooser = new JComboBox<>(files);
				cb_chooser.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent e) {
						lbl_image.setIcon(new ImageIcon(cb_chooser.getSelectedItem() + ""));
					}
				});
				ImageIcon icon = new ImageIcon(img);
				lbl_image = new JLabel(icon);

				add(cb_chooser, BorderLayout.PAGE_START);
				add(lbl_image, BorderLayout.CENTER);

				//initialize window
				initializeWindow();

			} else {
				JOptionPane.showMessageDialog(null, "Please select more than or equal to 3 images!", "Error",
						JOptionPane.ERROR_MESSAGE);
				new App();
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Please select valid file!", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * This method initializes the window with the given parameters
	 */
	public void initializeWindow() {
		this.setTitle("Assignment4 | 112200036");
		this.setSize(500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}