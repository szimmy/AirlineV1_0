package Airline.GUIClasses;

import javax.swing.*;

import Airline.Passenger.GoldMember;
import Airline.Passenger.MemberPassenger;
import Airline.Passenger.NotMember;
import Airline.Passenger.Passenger;
import Airline.Passenger.PlatinumMember;
import Airline.Passenger.SilverMember;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;

public class CreateUserGUI extends JFrame {

	//Fields
	private JLabel infoLabel;
	private JLabel firstNameLabel;
	private JLabel middleNameLabel;
	private JLabel lastNameLabel;
	private JLabel emailLabel;
	private JLabel passwordLabel;
	private JLabel passwordConfirmLabel;
	private JPasswordField passwordText;
	private JPasswordField passwordConfirmText;
	private JTextField emailText;
	private JTextField firstNameText;
	private JTextField middleNameText;
	private JTextField lastNameText;
	private JPanel northPanel;
	private JPanel centerPanel;
	private JPanel southPanel;
	private JPanel westPanel;
	private JPanel eastPanel;
	private JButton confirm;
	private JButton exit;
	private String[] membershipTypes = {"Platinum", "Gold", "Silver", "None"};
	private JComboBox<String> membershipBox;
	private JLabel membershipTypeLabel;
	private Passenger newMember;
	
	public CreateUserGUI() {
		
		northPanel = new JPanel();	//North panel contains info label
		centerPanel = new JPanel(); //Center panel contains all labels and text fields for user input
		southPanel = new JPanel();	//South panel contains buttons for submit or any additional alerts
		eastPanel = new JPanel();   // East panel is just used for spacing
		westPanel = new JPanel();   //West panel is just used for spacing
		
		infoLabel = new JLabel("<html>" + "Fields with denoted with an asterisk are optional."
				+ "<br>" + "\nPassword must be between 6-20 characters with at least one capital letter and one digit." + "</html>");
		
		firstNameText = new JTextField();
		firstNameLabel = new JLabel("First name: ");
		
		middleNameLabel = new JLabel("Middle name*: ");
		middleNameText = new JTextField();
		
		lastNameLabel = new JLabel("Last name: ");
		lastNameText = new JTextField();
		
		emailLabel = new JLabel("E-mail: ");
		emailText = new JTextField();
		emailText.addKeyListener(new TextInputListener());
		
		passwordLabel = new JLabel("Password: ");
		passwordConfirmLabel = new JLabel("Confirm password: ");
		passwordText = new JPasswordField();
		passwordText.addKeyListener(new TextInputListener());
		passwordConfirmText = new JPasswordField();
		passwordConfirmText.addKeyListener(new TextInputListener());
		
		membershipTypeLabel = new JLabel("Desired membership: ");
		membershipBox = new JComboBox<String>(membershipTypes);
		membershipBox.setBackground(Color.WHITE);
		
		confirm = new JButton("Confirm");
		confirm.addActionListener(new ConfirmListener());
		
		exit = new JButton("Exit");
		exit.addActionListener(new ExitListener());

		this.addWindowListener(new WindowExitListener());
		this.setLayout(new BorderLayout());
		centerPanel.setLayout(new GridLayout(0, 2));	//Two columns, so that a label and its text field are on the same line
		
		//Adds info label to the north
		northPanel.add(infoLabel);

		//First row is first name fields
		centerPanel.add(firstNameLabel);
		centerPanel.add(firstNameText);
		//Second row is middle name fields
		centerPanel.add(middleNameLabel);
		centerPanel.add(middleNameText);
		//Third row is last name fields
		centerPanel.add(lastNameLabel);
		centerPanel.add(lastNameText);
		//Fourth row is email fields
		centerPanel.add(emailLabel);
		centerPanel.add(emailText);
		//Fifth row is first place to enter password
		centerPanel.add(passwordLabel);
		centerPanel.add(passwordText);
		//Sixth row is to confirm password
		centerPanel.add(passwordConfirmLabel);
		centerPanel.add(passwordConfirmText);
		//Seventh row is membership information
		centerPanel.add(membershipTypeLabel);
		centerPanel.add(membershipBox);
		
		southPanel.add(confirm);
		southPanel.add(exit);
		
		this.add(northPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);
		this.add(eastPanel, BorderLayout.EAST);
		this.add(westPanel, BorderLayout.WEST);
		this.setTitle("User Information");
		this.pack();
		this.setMinimumSize(new Dimension(550,300));
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);		
		
	}
	
	/**
	 * Confirms that the user really wants to exit the program. 
	 * Used by both the exit button and the red x.
	 */
	private void confirmClose() {
		int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel registration?"
				+ "\nYou will lose any unsaved data.",
				"Warning" , JOptionPane.WARNING_MESSAGE);
		if (n == JOptionPane.YES_OPTION) {
			this.dispose();
		}
		else {
			System.out.println("User chose not to exit");
		}
	}
	
	/**
	 * For whenever we want to close without warning the user
	 */
	private void closeWithoutConfirm() {
		this.dispose();
	}
	
	/**
	 * Updates the colors of the password and email labels
	 * depending on whether or not current text input is valid.
	 */
	private void updateColors(KeyEvent e) {
		if (e.getSource() instanceof JPasswordField) {	
			//Sets color of password label if the source is a JPasswordField
			String password = new String(passwordText.getPassword());
			String confirmation = new String(passwordConfirmText.getPassword());
			
			if (Passenger.validatePassword(password)) {
				passwordLabel.setForeground(Color.GREEN);
			} else {
				passwordLabel.setForeground(Color.RED);
			}
			
			//Checks to see if two password fields match
			if(confirmation.equals(password) && password.length() > 0) {
				passwordConfirmLabel.setForeground(Color.GREEN);
			}
			else {
				passwordConfirmLabel.setForeground(Color.RED);
			}
			
		}
		
		else if(e.getSource() instanceof JTextField) {
			//Sets the color of email label if the source is a JTextField
			if(Passenger.validateEmail(emailText.getText())) {
				emailLabel.setForeground(Color.GREEN);
			}
			else {
				emailLabel.setForeground(Color.RED);
			}	
		}
		
	}
	
	/**
	 * Creates a new passenger account.
	 * @param member The MemberPassenger object that determines their membership
	 * @param passwordString The password of the user
	 */
	private void createNewMember(MemberPassenger member, String passwordString) {
		newMember = new Passenger(Passenger.getHighestId(), member, firstNameText.getText(), 
				middleNameText.getText(), lastNameText.getText(), emailText.getText(),
				passwordString);
		newMember.serialize();
		Passenger.increaseId();
		newMember.writeToCSV();
	
		System.out.println(newMember); //Remove this before final version, just for testing
	}
	
	
	/**
	 * Listener for the confirm button. Checks to make sure all fields are valid.
	 * @author Matthew Gimbut
	 *
	 */
	private class ConfirmListener implements ActionListener {
		
		private String errorString = "";
		
		@Override
		/**
		 * Runs through all necessary checks to see if the user's information is sufficient to create an account.
		 * Creates appropriate passenger object
		 */
		public void actionPerformed(ActionEvent e) {
			String passwordString = new String(passwordText.getPassword());

			if(!matchingPasswords()) {
				errorString += "- Passwords don't match!\n";
			}
			if(!Passenger.validatePassword(passwordString)) {
				errorString += "- Invalid password!\n";
			}
			if(!Passenger.validateEmail(emailText.getText())) {
				errorString += "- Invalid email!\n";
			}
			if(firstNameText.getText().equals("")) {
				errorString += "- No first name!";
			}
			if(lastNameText.getText().equals("")) {
				errorString += "- No last name!";
			}
			
			if(errorString.equals("")) { //If no errors are found, creates appropriate passenger
				if(membershipBox.getSelectedItem().equals("None")){
					createNewMember(new NotMember(), passwordString);
				}
				else if(membershipBox.getSelectedItem().equals("Silver")) {
					createNewMember(new SilverMember(), passwordString);
				}
				else if(membershipBox.getSelectedItem().equals("Gold")) {
					createNewMember(new GoldMember(), passwordString);
				}
				else if(membershipBox.getSelectedItem().equals("Platinum")) {
					createNewMember(new PlatinumMember(), passwordString);
				}
				JOptionPane.showMessageDialog(null, "Your account with "
						+ "Acme Airlines has successfully been created.", "Account successfully created" , JOptionPane.INFORMATION_MESSAGE);
				closeWithoutConfirm();
			}
			else {
				//If errors are found, creates a JOptionPane that lists all the errors and then resets error string.
				JOptionPane.showMessageDialog(null, errorString, "Error" , JOptionPane.ERROR_MESSAGE);
				errorString = "";
			}
		}
		
		/**
		 * Converts the array of chars from the getPassword method to a single string for each source
		 * Compares to see if the two are identical
		 * @return true If the passwords are equal
		 */
		public boolean matchingPasswords() {
			String passOne = new String(passwordText.getPassword());
			String passTwo = new String(passwordConfirmText.getPassword());
			
			if (passOne.equals(passTwo)) {
				return true;
			}
			else {
				return false;
			}
		}
		
	}
	
	/**
	 * Listener for the Exit button
	 * @author Matthew Gimbut
	 *
	 */
	private class ExitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			confirmClose();
		}
		
	}
	
	
	/**
	 * TextInputListener looks at the text in the password field, changing the label to red if currently invalid,
	 * or green if valid.
	 * @author Matthew Gimbut
	 *
	 */
	private class TextInputListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			updateColors(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			updateColors(e);
		}

		@Override
		public void keyTyped(KeyEvent e) {
			updateColors(e);
		}
		
	}
	
	/**
	 * Custom WindowListener class so that we can confirm the user wants to leave when they click the red x.'
	 * Not sure what to do with the 6 other required methods at this point
	 * @author Matthew Gimbut
	 *
	 */
	private class WindowExitListener implements WindowListener {

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void windowClosing(WindowEvent e) {
			confirmClose();			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO I don't know 
		}		
	}
}
