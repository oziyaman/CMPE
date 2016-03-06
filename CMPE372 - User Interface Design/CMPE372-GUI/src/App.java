import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class App extends JFrame {

	private JLabel lblFirstNubmer;
	private JTextField txtFirstNumber;
	private JLabel lblSecondNubmer;
	private JTextField txtSecondNumber;
	private JLabel lblResult;
	private JTextField txtResult;

	private JButton btnCalculate;
	private JButton btnReset;
	private JButton btnExit;

	public static void main(String[] args) {
		// creating our app
		new App();
	}

	/**
	 * Constructor of our app
	 */
	public App() {
		// First row
		JPanel row1 = new JPanel();
		row1.setLayout(new FlowLayout());

		lblFirstNubmer = new JLabel("First number: ");
		row1.add(lblFirstNubmer);

		txtFirstNumber = new JTextField(10);
		row1.add(txtFirstNumber);

		// Second row
		JPanel row2 = new JPanel();
		row2.setLayout(new FlowLayout());

		lblSecondNubmer = new JLabel("Second Number: ");
		row2.add(lblSecondNubmer);

		txtSecondNumber = new JTextField(10);
		row2.add(txtSecondNumber);

		// third row
		JPanel row3 = new JPanel();
		row2.setLayout(new FlowLayout());

		lblResult = new JLabel("Result: ");
		row3.add(lblResult);

		txtResult = new JTextField(10);
		row3.add(txtResult);

		// buttons row
		JPanel row4 = new JPanel();
		row4.setLayout(new FlowLayout());

		btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int firstNumber = Integer.parseInt(txtFirstNumber.getText().toString().trim());
					int secondNumber = Integer.parseInt(txtSecondNumber.getText().toString().trim());

					txtResult.setText(firstNumber + secondNumber + "");
				} catch (NumberFormatException e2) {
					txtResult.setText("Error: " + e2);
				}

			}
		});
		row4.add(btnCalculate);

		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtFirstNumber.setText("");
				txtSecondNumber.setText("");
				txtResult.setText("");

			}
		});
		row4.add(btnReset);

		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ask user to exit
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Close?",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					// 0 = close the app
					// -1 error
					System.exit(0);
				}
			}
		});
		row4.add(btnExit);

		this.add(row1);
		this.add(row2);
		this.add(row3);
		this.add(row4);

		// initializing the window
		initializeWindow();
	}

	/**
	 * This method initializes the window by the given parameters
	 */
	public void initializeWindow() {
		this.setTitle("Assignment1 | 112200036");
		this.setSize(300, 150);
		this.setLayout(new GridLayout(4, 2));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
