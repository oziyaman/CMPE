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
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class App extends JFrame {

	private JLabel lblUniversity;
	private JLabel lblDepartment;
	private JLabel lblName;
	private JLabel lblLastName;
	private JLabel lblStudentID;
	private JLabel lblSelect;

	private JTextField txtUniversity;
	private JTextField txtDepartment;
	private JTextField txtName;
	private JTextField txtLastName;
	private JTextField txtStudentID;

	private JRadioButton uppercase;
	private JRadioButton lowercase;

	private JButton btnPreview;
	private JButton btnReset;
	private JButton btnSave;
	private JButton btnExit;
	
	// Default placeholder for strings
	private String typeS = "Type: String";
	//Default placeholder for integers
	private String typeI = "Type: Integer";
	

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
		// setting the main layout
		setLayout(new GridLayout(8, 2));

		lblUniversity = new JLabel("University:");
		lblDepartment = new JLabel("Department:");
		lblName = new JLabel("Name:");
		lblLastName = new JLabel("Last Name:");
		lblStudentID = new JLabel("Student ID:");
		lblSelect = new JLabel("Select");

		txtUniversity = new JTextField();
		txtDepartment = new JTextField();
		txtName = new JTextField();
		txtLastName = new JTextField();
		txtStudentID = new JTextField();

		ButtonGroup btnGroup = new ButtonGroup();
		uppercase = new JRadioButton("UPPERCASE", true);
		lowercase = new JRadioButton("lowercase");

		btnGroup.add(uppercase);
		btnGroup.add(lowercase);

		// initializing focus listeners
		initializeFocusListeners();
		
		// initializing buttons
		initializeButtons();

		// initializing buttons keypress listeners
		initializeKeyPressListeners();

		// Adding components to the frame
		add(lblUniversity);
		add(txtUniversity);

		add(lblDepartment);
		add(txtDepartment);

		add(lblName);
		add(txtName);

		add(lblLastName);
		add(txtLastName);

		add(lblStudentID);
		add(txtStudentID);

		add(lblSelect);
		JPanel p = new JPanel(new GridLayout(1, 2));
		p.add(uppercase);
		p.add(lowercase);

		add(p);

		add(btnPreview);
		add(btnReset);
		add(btnSave);
		add(btnExit);

		// initializing main window
		initializeWindow();
	}

	/**
	 * This method initializes the key press listeners for buttons
	 */
	public void initializeKeyPressListeners() {
		// For preview button F5 is the shortcut key. 
		btnPreview.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				
			}
			
			public void keyReleased(KeyEvent e) {
				
			}
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_F5){
					preview();
				}
			}
		});
		
		// For save button ENTER is the shortcut key.
		btnSave.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				
			}
			
			public void keyReleased(KeyEvent e) {
				
			}
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					save();
				}
			}
		});
		
		// For reset button DEL is the shortcut key.
		btnReset.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				
			}
			
			public void keyReleased(KeyEvent e) {
				
			}
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_DELETE){
					reset();
				}
			}
		});
		
		// For exit button ESC is the shortcut key.
		btnExit.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				
			}
			
			public void keyReleased(KeyEvent e) {
				
			}
			
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					exit();
				}
			}
		});
	}

	/**
	 * This method initializes the buttons behaviors.
	 */
	private void initializeButtons() {
		// PREVIEW BUTTON
		btnPreview = new JButton("Preview");
		btnPreview.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				preview();
			}
		});

		// RESET BUTTON
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
			}

			
		});

		// SAVE BUTTON
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				save();
			}
		});

		// EXIT BUTTON
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});		
	}

	/** 
	 * This method initializes the place holders for text fields.
	 */
	public void initializeFocusListeners() {
				// TXT UNIVERSITY
				txtUniversity.setText(typeS);
				txtUniversity.addFocusListener(new FocusListener() {
					
					public void focusLost(FocusEvent e) {
						if(getUni().equals("")){
							txtUniversity.setText(typeS);
						}
						
					}
					
					public void focusGained(FocusEvent e) {
						if(getUni().equals(typeS)){
							txtUniversity.setText("");
						}
						
					}
				});
				
				// TXT DEPARTMENT
				txtDepartment.setText(typeS);
				txtDepartment.addFocusListener(new FocusListener() {
					
					public void focusLost(FocusEvent e) {
						if(getDept().equals("")){
							txtDepartment.setText(typeS);
						}
						
					}
					
					public void focusGained(FocusEvent e) {
						if(getDept().equals(typeS)){
							txtDepartment.setText("");
						}
						
					}
				});
				 
				// TXT NAME
				txtName.setText(typeS);
				txtName.addFocusListener(new FocusListener() {
					
					public void focusLost(FocusEvent e) {
						if(getName().equals("")){
							txtName.setText(typeS);
						}
					}
					
					public void focusGained(FocusEvent e) {
						if(getName().equals(typeS)){
							txtName.setText("");				
						}
					}
				});
				
				// TXT LAST NAME
				txtLastName.setText(typeS);
				txtLastName.addFocusListener(new FocusListener() {
					
					public void focusLost(FocusEvent e) {
						if(getLastName().equals("")){
							txtLastName.setText(typeS);
						}
					}
					
					public void focusGained(FocusEvent e) {
						if(getLastName().equals(typeS)){
							txtLastName.setText("");
						}
					}
				});
				
				// TXT STUDENT ID
				txtStudentID.setText(typeI);
				txtStudentID.addFocusListener(new FocusListener() {
					
					public void focusLost(FocusEvent e) {
						if(txtStudentID.getText().trim().equals("")){
							txtStudentID.setText(typeI);
						}
					}
					
					public void focusGained(FocusEvent e) {
						if(txtStudentID.getText().trim().equals(typeI)){
							txtStudentID.setText("");
						}
					}
				});	
	}

	/**
	 * This method initializes the window by the given parameters
	 */
	public void initializeWindow() {
		this.setTitle("Assignment3 | 112200036");
		this.setSize(500, 400);
		// this.setLayout(new GridLayout(8, 2)); already its in constructor
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * This method controls the given String fields.
	 * @return true if all the String areas are properly filled, false otherwise.
	 */
	public boolean controlStringFields() {
		String uni = getUni();
		String dep = getDept();
		String name = getName();
		String lastName = getLastName();

		if (uni.equals("") || uni.equals(typeS)) {
			JOptionPane.showMessageDialog(null, "Please enter a university name!", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (dep.equals("") || dep.equals(typeS)) {
			JOptionPane.showMessageDialog(null, "Please enter a department name!", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (name.equals("") || name.equals(typeS)) {
			JOptionPane.showMessageDialog(null, "Please enter a name!", "Error", JOptionPane.ERROR_MESSAGE);
		} else if (lastName.equals("") || lastName.equals(typeS)) {
			JOptionPane.showMessageDialog(null, "Please enter a surname!", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			return true;
		}
		return false;
	}

	/**
	 * This method for preview the info
	 * 
	 * @input: University, Department, Name, Last Name, Student ID
	 * @output: Modal window with given information
	 */
	public void preview() {
		
		try {
			if (controlStringFields()) {
				if (uppercase.isSelected()) {
					
					JOptionPane.showMessageDialog(null,
									"University: " + getUni().toUpperCase() + "\n" + 
									"Department: " + getDept().toUpperCase() + "\n"
									+ "Name: " + getName().toUpperCase() + "\n" 
									+ "Last Name: " + getLastName().toUpperCase()
									+ "\n" + "ID: " + getStudentID(),
									"Preview", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
									"University: " + getUni().toLowerCase() + "\n" 
									+ "Department: " + getDept().toLowerCase() + "\n"
									+ "Name: " + getName().toLowerCase() 
									+ "\n" + "Last Name: " + getLastName().toLowerCase()
									+ "\n" + "ID: " + getStudentID(),
									"Preview", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} catch (NumberFormatException e2) {
			JOptionPane.showMessageDialog(null, "Please enter legal student number!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * This method saves the information into the log.txt file
	 * @output log.txt
	 */
	public void save() {
		File file = new File("log.txt");
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))){
			if(controlStringFields()){
				String str = "";
				if(uppercase.isSelected()){
					str = "Name:\t\t" + getName().toUpperCase() + "\n" +
							"Last name:\t" + getLastName().toUpperCase() + "\n" +
							"Student ID:\t" + getStudentID() + "\n" +
							"University:\t" + getUni().toUpperCase() + "\n" +
							"Department:\t" + getDept().toUpperCase();
				}else{
					str = "Name:\t\t" + getName().toLowerCase() + "\n" +
							"Last name:\t" + getLastName().toLowerCase() + "\n" +
							"Student ID:\t" + getStudentID() + "\n" +
							"University:\t" + getUni().toLowerCase() + "\n" +
							"Department:\t" + getDept().toLowerCase();
				}
				bw.newLine();
				bw.write(str);
				bw.newLine();
				bw.write("========================================");
				bw.newLine();
				
				bw.flush();
				bw.close();
				JOptionPane.showMessageDialog(null, "Saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (IOException e2) {
			JOptionPane.showMessageDialog(null, "Something went wrong while saving!", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e2) {
			JOptionPane.showMessageDialog(null, "Please enter legal student number!", "Error", JOptionPane.ERROR_MESSAGE);
		}				
	}

	/**
	 * This method resets the input fields.
	 */
	public void reset() {
		txtUniversity.setText(typeS);
		txtDepartment.setText(typeS);
		txtName.setText(typeS);
		txtLastName.setText(typeS);
		txtStudentID.setText(typeI);				
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
			// -1 error
			System.exit(0);
		}
	}
	
	/**
	 * This method gets the users university.
	 * @return String txtUniversity
	 */
	public String getUni(){
		return txtUniversity.getText().toString().trim();
	}
	
	/**
	 * This method gets the users department.
	 * @return String txtDepartment
	 */
	public String getDept(){
		return txtDepartment.getText().toString().trim();
	}
	
	/**
	 * This method gets the users name.
	 * @return String txtName
	 */
	public String getName(){
		return txtName.getText().toString().trim();
	}
	
	/**
	 * This method gets the users last name.
	 * @return String txtLastName
	 */
	public String getLastName(){
		return txtLastName.getText().toString().trim();
	}
	
	/**
	 * This method gets the users student id.
	 * @return int txtStudentID
	 */
	public int getStudentID(){
		return Integer.parseInt(txtStudentID.getText().toString().trim());
	}
	
}
