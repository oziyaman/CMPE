import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class UserProfiles extends JFrame {

	private String user = "guest";
	private ArrayList<String> users;

	private JPanel jp_bottom, jp_top;
	private JButton btn_addUser, btn_deleteUser, btn_select;
	private JTextField txt_username;

	private DefaultTableModel tableModel;

	private JTable table;

	// user preferences
	Preferences userPref = Preferences.userRoot().node("~/custom/root");

	public static void main(String[] args) {
		// new UserProfiles();
	}

	public UserProfiles() {
		setLayout(new BorderLayout());

		// creating top panel components
		createTopPanel();

		// creating bottom panel components
		createBottomPanel();

		add(jp_top, BorderLayout.CENTER);
		add(jp_bottom, BorderLayout.SOUTH);

		// load users tp the jtable
		loadCurrentUserData();

		// initialize our window
		initializeWindow();
	}

	/**
	 * This method initializes the window with the given parameters
	 */
	public void initializeWindow() {
		this.setTitle("User Profiles | BMI Calculator | 112200036");
		this.setSize(400, 250);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * This method creates components for top panel
	 */
	public void createTopPanel() {
		jp_top = new JPanel();

		table = new JTable();

		String[] columnNames = { "Username" };
		tableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableModel.setColumnIdentifiers(columnNames);
		table.setModel(tableModel);
		table.setFont(new Font("Sans-Serif", Font.PLAIN, 14));

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

			}
		});

		JScrollPane jsp = new JScrollPane(table);
		table.setPreferredScrollableViewportSize(new Dimension(385, 120));

		jp_top.add(jsp);
	}

	/**
	 * This method creates components for bottom panel
	 */
	public void createBottomPanel() {
		jp_bottom = new JPanel();
		jp_bottom.setLayout(new GridLayout(3, 2));

		txt_username = new JTextField();
		JLabel lbl_username = new JLabel("Username");

		btn_addUser = new JButton("Add User");
		btn_addUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Creating the users directory if does not exist
				File usersDir = new File("users");
				if (!usersDir.exists()) {
					usersDir.mkdir();
				}

				String username = txt_username.getText().toString().trim();

				if (users.contains(username)) {
					JOptionPane.showMessageDialog(null, "This user already exists!", "User already exist",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (!username.equals("")) {

						File file = new File("users/" + username);
						try {
							FileWriter fw = new FileWriter(file);

						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "Error while creating the file for: " + username,
									"Error", JOptionPane.ERROR_MESSAGE);
						}

						Vector<String> row = new Vector<String>();
						row.add(username);

						tableModel.addRow(row);

						if (!users.contains(username)) {
							users.add(username);
						}

					}
					txt_username.setText("");
				}

			}
		});

		btn_deleteUser = new JButton("Delete User");
		btn_deleteUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int row = table.getSelectedRow();
					int column = table.getSelectedColumn();

					String selectedFile = (String) table.getModel().getValueAt(row, column);

					File file = new File("users/" + selectedFile);

					file.delete();

					tableModel.removeRow(row);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Please select a row to delete.", "Delete row",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		btn_select = new JButton("Select");
		btn_select.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int row = table.getSelectedRow();
					int column = table.getSelectedColumn();

					String selectedUser = (String) table.getModel().getValueAt(row, column);

					userPref.put("USER", selectedUser);

					dispose();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Please select a user", "User select",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		jp_bottom.add(lbl_username);
		jp_bottom.add(txt_username);
		jp_bottom.add(btn_addUser);
		jp_bottom.add(btn_deleteUser);
		jp_bottom.add(btn_select);
	}

	/**
	 * This method loads the users that are in the users folder to the JTable
	 */
	private void loadCurrentUserData() {
		// filling local users list
		users = new ArrayList<String>();

		File usersFolder = new File("users");
		if (usersFolder.exists()) {
			for (File users : usersFolder.listFiles()) {
				Vector<String> row = new Vector<String>();
				row.add(users.getName());

				tableModel.addRow(row);

				this.users.add(users.getName());
			}
		}

	}

}
