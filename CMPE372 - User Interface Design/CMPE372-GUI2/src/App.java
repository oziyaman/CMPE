import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class App extends JFrame {

	private JLabel lblFirstNumber;
	private JLabel lblSecondNumber;
	private JLabel lblThirdNumber;
	private JLabel lblFourthNumber;
	private JLabel lblFifthNumber;
	private JLabel lblSelect;
	private JLabel lblResultText;
	private JLabel lblResult;

	private JTextField txtFirstNumber;
	private JTextField txtSecondNumber;
	private JTextField txtThirdNumber;
	private JTextField txtFourthNumber;
	private JTextField txtFifthNumber;

	private JRadioButton max;
	private JRadioButton min;

	private JButton btnCalculate;
	private JButton btnReset;

	// Global array
	int[] arr;

	/**
	 * Main method starts the app
	 * @param args
	 */
	public static void main(String[] args) {
		new App();
	}

	/**
	 * Constructor for our app
	 */
	public App() {
		setLayout(new GridLayout(8, 2));

		lblFirstNumber = new JLabel("First number: ");
		lblSecondNumber = new JLabel("Second number: ");
		lblThirdNumber = new JLabel("Third number");
		lblFourthNumber = new JLabel("Fourth number: ");
		lblFifthNumber = new JLabel("Fifth number: ");
		lblSelect = new JLabel("Select");
		lblResultText = new JLabel("Result");
		lblResult = new JLabel("");

		txtFirstNumber = new JTextField();
		txtSecondNumber = new JTextField();
		txtThirdNumber = new JTextField();
		txtFourthNumber = new JTextField();
		txtFifthNumber = new JTextField();

		ButtonGroup btnGroup = new ButtonGroup();
		max = new JRadioButton("Max number", true);
		min = new JRadioButton("Min number");

		btnGroup.add(max);
		btnGroup.add(min);

		// Calculate button
		btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// checking for illegal inputs
				try {
					int first = Integer.parseInt(txtFirstNumber.getText().toString().trim());
					int second = Integer.parseInt(txtSecondNumber.getText().toString().trim());
					int third = Integer.parseInt(txtThirdNumber.getText().toString().trim());
					int fourth = Integer.parseInt(txtFourthNumber.getText().toString().trim());
					int fifth = Integer.parseInt(txtFifthNumber.getText().toString().trim());

					// creating array
					arr = new int[5];
					// assign numbers to the array
					arr[0] = first;
					arr[1] = second;
					arr[2] = third;
					arr[3] = fourth;
					arr[4] = fifth;

					// selecting function according to users choice
					if (max.isSelected()) {
						findMax();
					} else {
						findMin();
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Please enter legal integer format!");
				}
			}
		});

		// Reset button
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Resetting the used fields
				txtFirstNumber.setText("");
				txtSecondNumber.setText("");
				txtThirdNumber.setText("");
				txtFourthNumber.setText("");
				txtFifthNumber.setText("");

				lblResult.setText("");
			}
		});

		// Adding components to the frame
		add(lblFirstNumber);
		add(txtFirstNumber);

		add(lblSecondNumber);
		add(txtSecondNumber);

		add(lblThirdNumber);
		add(txtThirdNumber);

		add(lblFourthNumber);
		add(txtFourthNumber);

		add(lblFifthNumber);
		add(txtFifthNumber);

		add(lblSelect);
		JPanel p = new JPanel(new GridLayout(1, 2));
		p.add(max);
		p.add(min);

		add(p);

		add(lblResultText);
		add(lblResult);

		add(btnCalculate);
		add(btnReset);

		// initialize our window
		initializeWindow();
	}

	/**
	 * This method initializes the window by the given parameters
	 */
	public void initializeWindow() {
		this.setTitle("Assignment2 | 112200036");
		this.setSize(500, 400);
		//this.setLayout(new GridLayout(8, 2)); already its in constructor
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * This function finds the minimum number in the array.
	 * Example: 
	 * 		int[] arr = {1,2,3,4,5};
	 * 		returns 1 and sets lblResult's text
	 * 
	 * @input : local defined array
	 * @output : smallest number in the array
	 */
	private void findMin() {
		int min = arr[0];

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < min) {
				min = arr[i];
			}
		}
		lblResult.setText(min + "");
	}

	/**
	 * This function finds the maximum number in the array.
	 * Example: 
	 * 		int[] arr = {1,2,3,4,5};
	 * 		returns 5 and sets lblResult's text
	 * 
	 * @input : local defined array
	 * @output : biggest number in the array
	 */
	private void findMax() {
		int max = arr[0];

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		lblResult.setText(max + "");
	}
}
